package com.wenbo.shorturl.pattern.creational.factory;

import com.wenbo.shorturl.pattern.Animal;

/**
 * @author changwenbo
 * @date 2024/7/2 20:05
 */
public class Main {
    public static void main(String[] args) {

        AnimalFactory dogFactory = getFactory("dog");
        Animal dog = dogFactory.createAnimal();
        dog.speak();

        AnimalFactory catFactory = getFactory("cat");
        Animal cat = catFactory.createAnimal();
        cat.speak();

    }

    // 一般创建dogFactory还是catFactory，一般可以用type来判断，每个类都有一个type
    // 或者根绝配置，环境变量读取等等都可以
    public static AnimalFactory getFactory(String animalType) {
        if ("dog".equals(animalType)) {
            return new DogFactory();
        } else if ("cat".equals(animalType)) {
            return new CatFactory();
        }
        throw new IllegalArgumentException("Unknown animal type");
    }
}
