package com.wenbo.shorturl.pattern.command.AbstractCommand;

/**
 * @author changwenbo
 * @date 2024/7/29 20:46
 */
public interface Command<T> {
    T execute();
}

