package com.wenbo.shorturl.pattern.builder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wenbo.shorturl.pattern.Car;
import com.wenbo.shorturl.utils.GsonUtil;

public class Main {
	public static void main(String[] args) {

		// 1. 制造一个BMW
		CarDirector director = new CarDirector(new BMWCarBuilder());
		Car bmwCar = director.constructCar();
		System.out.println(GsonUtil.toJson(bmwCar));

		// 2. 制造一个小米汽车
		CarDirector director1 = new CarDirector(new MiCarBuilder());
		Car miCar = director1.constructCar();
		System.out.println(GsonUtil.toJson(miCar));



		// 生成器模式的一个潜在缺点是每个产品都需要一个具体的生成器类，这可能会导致类的数量增加，特别是在产品种类繁多的情况下
		// 3. 使用方法链(非标准生成器模式，属于生成器模式的变种)
		GsonBuilder builder = new GsonBuilder()
				.enableComplexMapKeySerialization()
				.disableHtmlEscaping()
				.setVersion(1.0);
		Gson gson = builder.create();


		System.out.println(gson.toJson(bmwCar));
		System.out.println(gson.toJson(miCar));
	}
}
