package com.wenbo.shorturl.pattern.creational.simpleFactory;

import com.wenbo.shorturl.pattern.Animal;
import com.wenbo.shorturl.pattern.Cat;
import com.wenbo.shorturl.pattern.Dog;

import java.util.HashMap;
import java.util.Map;

/**
 * @author changwenbo
 * @date 2024/7/2 19:46
 */
public class AnimalFactory {
    // 在实际开发中可以使用ApplicationContext 配合@PostConstruct，来获取Animal的所有实现类
    private static Map<String, Animal> map = new HashMap<>();

    static {
        map.put("dog", new Dog());
        map.put("cat", new Cat());
    }


    public static Animal createAnimal(String type) {
        if (map.containsKey(type)) {
            return map.get(type);
        }
        throw new IllegalArgumentException("Unknown animal type");
    }

}

