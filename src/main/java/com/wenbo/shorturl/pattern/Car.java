package com.wenbo.shorturl.pattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Car implements Cloneable {

	// 品牌
	private String branch;

	// 速度
	private int speed;

	// 价格
	private double price;


	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public Car clone() {
		try {
			Car copy = (Car) super.clone();
			return copy;
		} catch (Exception e) {
			log.warn("e = {}", e.getMessage(), e);
			return null;
		}
	}
}
