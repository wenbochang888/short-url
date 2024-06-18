package com.wenbo.shorturl.service;

import cn.hutool.core.codec.Base62;
import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wenbo.shorturl.dao.ShortUrlDAO;
import com.wenbo.shorturl.modle.MapConstants;
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

	@Autowired
	private ShortUrlDAO shortUrlDAO;

	public String generateShortUrl(String longUrl) {
		if (StringUtils.isEmpty(longUrl)) {
			throw new RuntimeException("longUrl 不能为空");
		}

		String shortUrl = CacheUtils.get(MapConstants.longCache, longUrl);
		if (StringUtils.isNotEmpty(shortUrl)) {
			return shortUrl;
		}

		return getShortUrl(longUrl, getLongUrlRandom(longUrl));
	}

	public String getLongUrl(String shortUrl) {
		if (StringUtils.isEmpty(shortUrl)) {
			throw new RuntimeException("shortUrl 不能为空");
		}

		String longUrl = CacheUtils.get(MapConstants.shortCache, shortUrl);
		if (StringUtils.isNotEmpty(longUrl)) {
			return longUrl;
		}

		LambdaQueryWrapper<ShortUrl> wrapper = new QueryWrapper<ShortUrl>().lambda().eq(ShortUrl::getSUrl, shortUrl);
		ShortUrl url = shortUrlDAO.selectOne(wrapper);
		CacheUtils.put(MapConstants.shortCache, shortUrl, url.getLUrl());
		return url.getLUrl();
	}

	private String getShortUrl(String rawUrl, String longUrl) {
		long hash = HashUtil.murmur64(longUrl.getBytes());
		String base62 = Base62.encode(hash + "");
		log.info("longUrl = {}, hash = {}, base62 = {}", longUrl, hash, base62);
		if (StringUtils.isEmpty(base62)) {
			throw new RuntimeException("hash 算法有误");
		}

		String shortUrl = StringUtils.substring(base62, 6);
		ShortUrl url = new ShortUrl(rawUrl, shortUrl);
		try {
			int insert = shortUrlDAO.insert(url); // 这里进行分库分表 提高性能
			if (insert == 1) {
				CacheUtils.put(MapConstants.longCache, rawUrl, shortUrl);
			}
		} catch (DuplicateKeyException  e) {
			// Hash冲突
			log.warn("hash冲突 触发唯一索引 rawUrl = {}, longUrl = {}, shortUrl = {}, e = {}", rawUrl, longUrl, shortUrl, e.getMessage(), e);
			CacheUtils.put(MapConstants.hashFailMap, rawUrl, shortUrl);
			return getShortUrl(rawUrl, getLongUrlRandom(shortUrl));
		} catch (Exception e) {
			log.error("未知错误 e = {}", e.getMessage(), e);
			throw new RuntimeException("msg = " + e.getMessage());
		}

		return shortUrl;
	}

	private String getLongUrlRandom(String longUrl) {
		return longUrl + RandomUtil.randomString(6);  // 解决冲突多的问题，随机字符串
	}
}