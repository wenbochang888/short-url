package com.wenbo.shorturl.pattern;

import com.wenbo.shorturl.pattern.visitor.AnimalVisitor;

/**
 * @author changwenbo
 * @date 2024/7/2 19:45
 */
public interface Animal {
    void speak();

    String getType();

    void accept(AnimalVisitor visitor);
}
