package com.wenbo.shorturl.pattern.creational.abstractFactory.factory;

import com.wenbo.shorturl.pattern.Animal;
import com.wenbo.shorturl.pattern.Dog;
import com.wenbo.shorturl.pattern.creational.abstractFactory.AnimalFood;
import com.wenbo.shorturl.pattern.creational.abstractFactory.DogFood;

/**
 * @author changwenbo
 * @date 2024/7/3 09:53
 */
public class DogFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Dog();
    }

    @Override
    public AnimalFood createAnimalFood() {
        return new DogFood();
    }
}
