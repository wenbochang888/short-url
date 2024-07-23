package com.wenbo.shorturl.pattern.creational.simpleFactory;

import com.wenbo.shorturl.pattern.Animal;

/**
 * @author changwenbo
 * @date 2024/7/2 20:05
 */
public class Main {
    public static void main(String[] args) {

        // 一般创建dog还是cat，一般可以用type来判断，每个类都有一个type
        // 或者根据配置，环境变量读取等等都可以
        Animal dog = AnimalFactory.createAnimal("dog");
        dog.speak();

        Animal cat = AnimalFactory.createAnimal("cat");
        cat.speak();
    }
}
