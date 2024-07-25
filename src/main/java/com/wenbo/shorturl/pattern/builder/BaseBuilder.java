package com.wenbo.shorturl.pattern.builder;

import com.wenbo.shorturl.pattern.Car;

public interface BaseBuilder {

    void buildBranch();

    void buildSpeed();

    void buildPrice();

    Car getCar();

}
