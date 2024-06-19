package com.wenbo.shorturl;

import cn.hutool.core.codec.Base62;
import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;

import java.util.*;

/**
 * 测试hash冲突的比例  100W数据
 *
 * 结论  HashUtil.murmur32算法   // 产生一个32位的hash值
 * i =  100000,  conflictSize = 1
 * i =  200000,  conflictSize = 6
 * i =  300000,  conflictSize = 12
 * i =  400000,  conflictSize = 19
 * i =  500000,  conflictSize = 32
 * i =  600000,  conflictSize = 46
 * i =  700000,  conflictSize = 54
 * i =  800000,  conflictSize = 76
 * i =  900000,  conflictSize = 94
 * i =  1000000,  conflictSize = 121
 *
 *
 * HashUtil.murmur64算法  // 产生一个64位的hash值
 * 冲突为0，不会有一个冲突
 * 500w数据 不会有一个冲突
 *
 * @author changwenbo
 * @date 2024/6/18 20:19
 */
@Slf4j
public class HashTest {
    public static void main(String[] args) {
        List<String> urlList = new ArrayList<>(5000_000);
        for (int i = 0; i <= 5_000_000; i++) {
            String s = RandomUtil.randomString(50);
            String url = "https://m.weibo.cn/comments/hotflow?id=" + s;
            urlList.add(url);
        }

        System.out.println("url size = " + urlList.size());

        int cnt = 1;
        for (int i = 0; i < urlList.size(); i++) {
            if (i == (cnt * 1000_000)) {
                System.out.println("cnt =  " + i + ",  conflictSize = " + conflictMap.size());
                cnt++;
            }
            generateShortUrl(urlList.get(i));
        }

    }

    // 模拟数据库唯一索引
    private static Set<String> uniqueSet= new HashSet<>(1_000_000); // 100W数据

    // 冲突map
    private static Map<String, String> conflictMap = new HashMap<>();

    public static String generateShortUrl(String longUrl) {
        if (StringUtils.isEmpty(longUrl)) {
            throw new RuntimeException("longUrl 不能为空");
        }

        return getShortUrl(longUrl, getLongUrlRandom(longUrl));
    }

    private static String getShortUrl(String rawUrl, String longUrl) {
//        int hash = HashUtil.murmur32(longUrl.getBytes());
        long hash = HashUtil.murmur64(longUrl.getBytes());
        String base62 = Base62.encode(hash + "");
        //log.info("longUrl = {}, hash = {}, base62 = {}", longUrl, hash, base62);
        if (StringUtils.isEmpty(base62)) {
            throw new RuntimeException("hash 算法有误");
        }

        String shortUrl = StringUtils.substring(base62, 6);
        try {
            if (uniqueSet.contains(shortUrl)) {
                throw new DuplicateKeyException("DuplicateKeyException");
            }
            uniqueSet.add(shortUrl);
        } catch (DuplicateKeyException e) {
            // Hash冲突
            //log.warn("hash冲突 触发唯一索引 rawUrl = {}, longUrl = {}, shortUrl = {}, e = {}", rawUrl, longUrl, shortUrl, e.getMessage(), e);
            conflictMap.put(rawUrl, shortUrl);
            return getShortUrl(rawUrl, getLongUrlRandom(shortUrl));
        } catch (Exception e) {
            log.error("未知错误 e = {}", e.getMessage(), e);
            throw new RuntimeException("msg = " + e.getMessage());
        }

        return shortUrl;
    }

    private static String getLongUrlRandom(String longUrl) {
        return longUrl + RandomUtil.randomString(6);  // 解决冲突多的问题，随机字符串
    }
}
