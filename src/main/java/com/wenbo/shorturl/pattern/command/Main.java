package com.wenbo.shorturl.pattern.command;

import com.wenbo.shorturl.pattern.*;

/**
 * @author changwenbo
 * @date 2024/7/29 20:46
 */
public class Main {
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal cat = new Cat();
        BaseCommand dogSpeakCommand = new SpeakCommand(dog);
        AnimalInvoker invoker = new AnimalInvoker(dogSpeakCommand);
        // 执行狗的命令
        invoker.executeCommand();


        // 减少子类的创建
        BaseCommand catSpeakCommand = () -> cat.speak();
        invoker = new AnimalInvoker(catSpeakCommand);
        invoker.executeCommand();
    }
}
