package com.wenbo.shorturl;

import org.apache.commons.lang3.StringUtils;

public class Main {
	public static void main(String[] args) {

		String base62 = "90";


		System.out.println(StringUtils.substring(base62, 0, 6));
		System.out.println(StringUtils.substring(base62, -6));
	}
}
