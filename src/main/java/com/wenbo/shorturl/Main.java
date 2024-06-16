package com.wenbo.shorturl;

import cn.hutool.cache.impl.LRUCache;

public class Main {
	public static void main(String[] args) {

		LRUCache<String, String> cache = new LRUCache<>(100);
	}
}
