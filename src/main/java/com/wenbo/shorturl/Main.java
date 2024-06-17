package com.wenbo.shorturl;

import cn.hutool.core.util.RandomUtil;

public class Main {
	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			System.out.println(RandomUtil.randomString(5));
		}

	}
}
