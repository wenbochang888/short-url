package com.wenbo.shorturl.pattern.builder;

import com.wenbo.shorturl.pattern.Car;

public class MiCarBuilder implements BaseBuilder {

    private Car car;

    public MiCarBuilder() {
        car = new Car();
    }

    @Override
    public void buildBranch() {
        car.setBranch("Mi");
    }

    @Override
    public void buildSpeed() {
        car.setSpeed(200);
    }

    @Override
    public void buildPrice() {
        car.setPrice(20000);
    }

    @Override
    public Car getCar() {
        return car;
    }
}
