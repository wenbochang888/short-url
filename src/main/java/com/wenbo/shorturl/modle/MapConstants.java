package com.wenbo.shorturl.modle;

import cn.hutool.cache.impl.LRUCache;

/**
 * @author changwenbo
 * @date 2024/6/17 19:37
 */
public final class MapConstants {

    // hash冲突Map
    public static LRUCache<String, String> hashFailMap = new LRUCache<>(100);

    // 防止同一个longUrl 短时间生成不同的shortUrl
    public static LRUCache<String, String> longCache = new LRUCache<>(1000);

    // 添加缓存，加快热点数据查询速度
    public static LRUCache<String, String> shortCache = new LRUCache<>(1000);

}
