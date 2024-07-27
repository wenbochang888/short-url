package com.wenbo.shorturl.pattern.chain;

import com.wenbo.shorturl.pattern.Dog;

public class Main {
	public static void main(String[] args) {
		// 1. cat
		Handler catHandler = new CatHandler();
		// 2. dog
		Handler dogHandler = new DogHandler();
		// 3. 设置下一个handler
		catHandler.setNextHandler(dogHandler);


		//catHandler.handler(new Cat());
		System.out.println("==============");
		catHandler.handler(new Dog());
	}
}
