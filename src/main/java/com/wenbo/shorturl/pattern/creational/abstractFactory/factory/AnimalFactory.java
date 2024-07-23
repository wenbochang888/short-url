package com.wenbo.shorturl.pattern.creational.abstractFactory.factory;

import com.wenbo.shorturl.pattern.Animal;
import com.wenbo.shorturl.pattern.creational.abstractFactory.AnimalFood;

/**
 * @author changwenbo
 * @date 2024/7/3 09:52
 */
public interface AnimalFactory {
    Animal createAnimal();
    AnimalFood createAnimalFood();
}
