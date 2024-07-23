package com.wenbo.shorturl.pattern.creational.abstractFactory;

import com.wenbo.shorturl.pattern.Animal;
import com.wenbo.shorturl.pattern.creational.abstractFactory.factory.AnimalFactory;
import com.wenbo.shorturl.pattern.creational.abstractFactory.factory.CatFactory;
import com.wenbo.shorturl.pattern.creational.abstractFactory.factory.DogFactory;

/**
 * @author changwenbo
 * @date 2024/7/23 21:39
 */
public class Main {
    public static void main(String[] args) {

        AnimalFactory catFactory = new CatFactory();
        Animal cat = catFactory.createAnimal();
        AnimalFood catFood = catFactory.createAnimalFood();

        cat.speak();
        catFood.eat();

        //---------------------------------------//

        AnimalFactory dogFactory = new DogFactory();
        Animal dog = dogFactory.createAnimal();
        AnimalFood dogFood = dogFactory.createAnimalFood();

        dog.speak();
        dogFood.eat();
    }
}

