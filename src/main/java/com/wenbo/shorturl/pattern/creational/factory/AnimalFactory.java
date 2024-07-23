package com.wenbo.shorturl.pattern.creational.factory;

import com.wenbo.shorturl.pattern.Animal;

/**
 * @author changwenbo
 * @date 2024/7/2 19:55
 */
public interface AnimalFactory {
    Animal createAnimal();
}



// 工厂方法模式（Factory Method Pattern）：定义一个创建对象的接口，但由子类决定要实例化的类是哪一个。工厂方法使一个类的实例化延迟到其子类。
// https://refactoringguru.cn/design-patterns/factory-method
