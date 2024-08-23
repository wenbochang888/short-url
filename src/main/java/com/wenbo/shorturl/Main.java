package com.wenbo.shorturl;


import java.util.function.Supplier;

public class Main {
	public static void main(String[] args) {
		Supplier<Integer> supplier = () -> {
			int x = 0;
			return x;
		};


		System.out.println(supplier.get());

	}
}
