package com.wenbo.shorturl.utils;

import cn.hutool.cache.impl.LRUCache;

public class CacheUtils {
	private static LRUCache<String, String> defaultCache = new LRUCache<>(1000);

	public static void put(String key, String val) {
		defaultCache.put(key, val);
	}

	public static String get(String key) {
		return defaultCache.get(key);
	}

	public static void put(LRUCache<String, String> lruCache, String key, String val) {
		lruCache.put(key, val);
	}

	public static String get(LRUCache<String, String> lruCache, String key) {
		return lruCache.get(key);
	}
}



/**
 public enum CacheUtils {
 INSTANCE;

 private LRUCache<String, String> defaultCache;

 CacheUtils() {
 defaultCache = new LRUCache<>(1000);
 }

 public static void put(String key, String val) {
 INSTANCE.defaultCache.put(key, val);
 }

 public static String get(String key) {
 return INSTANCE.defaultCache.get(key);
 }

 // 如果仍然需要操作不同的 LRUCache 实例，可以保留这些方法
 public static void put(LRUCache<String, String> lruCache, String key, String val) {
 lruCache.put(key, val);
 }

 public static String get(LRUCache<String, String> lruCache, String key) {
 return lruCache.get(key);
 }
 }



 */