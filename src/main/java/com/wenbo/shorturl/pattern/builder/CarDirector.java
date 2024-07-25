package com.wenbo.shorturl.pattern.builder;

import com.wenbo.shorturl.pattern.Car;

/**
 * @author changwenbo
 * @date 2024/7/25 17:15
 */
public class CarDirector {
    private BaseBuilder builder;

    public CarDirector(BaseBuilder builder) {
        this.builder = builder;
    }

    public Car constructCar() {
        builder.buildBranch();
        builder.buildPrice();
        builder.buildSpeed();
        return builder.getCar();
    }
}
