package com.wenbo.shorturl.pattern.visitor;

import com.wenbo.shorturl.pattern.Cat;
import com.wenbo.shorturl.pattern.Dog;
import lombok.extern.slf4j.Slf4j;

/**
 * @author changwenbo
 * @date 2024/8/5 17:46
 */
@Slf4j
public class AnimalAgeVisitor implements AnimalVisitor {
    @Override
    public void visit(Dog dog) {
        int age = calculateDogAge(dog);
        log.info("Dog's age is: " + age);
    }

    @Override
    public void visit(Cat cat) {
        int age = calculateCatAge(cat);
        log.info("Cat's age is: " + age);
    }

    private int calculateDogAge(Dog dog) {
        // 假设狗的年龄是固定的，比如5岁
        return 5;
    }

    private int calculateCatAge(Cat cat) {
        // 假设猫的年龄是固定的，比如3岁
        return 3;
    }
}

