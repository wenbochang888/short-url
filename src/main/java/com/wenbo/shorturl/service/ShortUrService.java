package com.wenbo.shorturl.service;

import cn.hutool.cache.impl.LRUCache;
import cn.hutool.core.codec.Base62;
import cn.hutool.core.util.HashUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wenbo.shorturl.dao.ShortUrlDAO;
import com.wenbo.shorturl.modle.ShortUrl;
import com.wenbo.shorturl.utils.CacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShortUrService {
	// hash冲突Map
	private static LRUCache<String, String> hashFailMap = new LRUCache<>(100);

	// 防止同一个longUrl 短时间生成不同的shortUrl
	private static LRUCache<String, String> longCache = new LRUCache<>(1000);

	// 添加缓存，加快热点数据查询速度
	private static LRUCache<String, String> shortCache = new LRUCache<>(1000);


	@Autowired
	private ShortUrlDAO shortUrlDAO;

	public String generateShortUrl(String longUrl) {
		if (StringUtils.isEmpty(longUrl)) {
			throw new RuntimeException("longUrl 不能为空");
		}

		String shortUrl = CacheUtils.get(longCache, longUrl);
		if (StringUtils.isNotEmpty(shortUrl)) {
			return shortUrl;
		}

		return getShortUrl(longUrl);
	}

	public String getLongUrl(String shortUrl) {
		if (StringUtils.isEmpty(shortUrl)) {
			throw new RuntimeException("shortUrl 不能为空");
		}

		String longUrl = CacheUtils.get(shortCache, shortUrl);
		if (StringUtils.isNotEmpty(longUrl)) {
			return longUrl;
		}

		LambdaQueryWrapper<ShortUrl> wrapper = new QueryWrapper<ShortUrl>().lambda().eq(ShortUrl::getSUrl, shortUrl);
		ShortUrl url = shortUrlDAO.selectOne(wrapper);
		CacheUtils.put(shortCache, shortUrl, url.getLUrl());
		return url.getLUrl();
	}

	private String getShortUrl(String longUrl) {
		int hash = HashUtil.murmur32(longUrl.getBytes());
		String base62 = Base62.encode(hash + "");
		log.info("longUrl = {}, hash = {}, base62 = {}", longUrl, hash, base62);
		if (StringUtils.isEmpty(base62)) {
			throw new RuntimeException("hash 算法有误");
		}

		String shortUrl = StringUtils.substring(base62, 0, 6);
		ShortUrl url = new ShortUrl(longUrl, shortUrl);
		try {
			int insert = shortUrlDAO.insert(url);
			if (insert == 1) {
				CacheUtils.put(longCache, longUrl, shortUrl);
			}
		} catch (DuplicateKeyException e) {
			// Hash冲突
			log.warn("hash冲突 触发唯一索引 longUrl = {}, shortUrl = {}, e = {}", longUrl, shortUrl, e.getMessage(), e);
			CacheUtils.put(hashFailMap, longUrl, shortUrl);
			return getShortUrl(shortUrl);
		} catch (Exception e) {
			log.error("未知错误 e = {}", e.getMessage(), e);
			throw new RuntimeException("msg = " + e.getMessage());
		}

		return shortUrl;
	}
}





















