package com.wenbo.shorturl;

import java.text.DecimalFormat;

public class Main {
	public static void main(String[] args) {

		DecimalFormat decimalFormat = new DecimalFormat("0.##############################");

		System.out.println(decimalFormat.format(Math.pow(2, 32) - 1));
		System.out.println(decimalFormat.format(Math.pow(2, 64) - 1));
	}
}
