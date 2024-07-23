package com.wenbo.shorturl.pattern.creational.abstractFactory.factory;

import com.wenbo.shorturl.pattern.Animal;
import com.wenbo.shorturl.pattern.Cat;
import com.wenbo.shorturl.pattern.creational.abstractFactory.AnimalFood;
import com.wenbo.shorturl.pattern.creational.abstractFactory.CatFood;

/**
 * @author changwenbo
 * @date 2024/7/3 09:53
 */
public class CatFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Cat();
    }

    @Override
    public AnimalFood createAnimalFood() {
        return new CatFood();
    }
}
