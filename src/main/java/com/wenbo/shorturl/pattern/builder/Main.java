package com.wenbo.shorturl.pattern.builder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wenbo.shorturl.pattern.Car;

public class Main {
	public static void main(String[] args) {


		GsonBuilder builder = new GsonBuilder()
				.setPrettyPrinting()
				.serializeNulls()
				.setVersion(1.0)
				.setDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = builder.create();

		Car car = new Car();
		car.setBranch("BMW");
		car.setSpeed(200);
		car.setPrice(300000);

		System.out.println(gson.toJson(car));

	}
}
