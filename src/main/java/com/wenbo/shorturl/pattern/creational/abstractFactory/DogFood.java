package com.wenbo.shorturl.pattern.creational.abstractFactory;

/**
 * @author changwenbo
 * @date 2024/7/3 09:52
 */
public class DogFood implements AnimalFood {
    @Override
    public void eat() {
        System.out.println("Dog is eating dog food.");
    }
}
