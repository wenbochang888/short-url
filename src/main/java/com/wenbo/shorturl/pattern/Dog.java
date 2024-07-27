package com.wenbo.shorturl.pattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @author changwenbo
 * @date 2024/7/2 19:45
 */
@Slf4j
public class Dog implements Animal {
    @Override
    public void speak() {
        log.info("Dog speak!");
    }

    @Override
    public String getType() {
        return "Dog";
    }
}
