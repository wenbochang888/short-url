package com.wenbo.shorturl.pattern.visitor;

import com.wenbo.shorturl.pattern.Cat;
import com.wenbo.shorturl.pattern.Dog;

/**
 * @author changwenbo
 * @date 2024/8/5 17:35
 */
public interface AnimalVisitor {
    void visit(Dog dog);

    void visit(Cat cat);
}

