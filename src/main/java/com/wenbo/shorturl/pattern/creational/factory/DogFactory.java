package com.wenbo.shorturl.pattern.creational.factory;

import com.wenbo.shorturl.pattern.Animal;
import com.wenbo.shorturl.pattern.Dog;

/**
 * @author changwenbo
 * @date 2024/7/2 19:57
 */
public class DogFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Dog();
    }
}
