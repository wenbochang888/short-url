package com.wenbo.shorturl.pattern.singleton;

import cn.hutool.core.lang.Singleton;

/**
 * @author changwenbo
 * @date 2024/7/25 21:11
 */
public class SingletonDemo {
    public static void main(String[] args) {
        SingletonDemo5 instance = SingletonDemo5.getInstance();

        SingletonDemo6 instance1 = SingletonDemo6.INSTANCE;
        instance1.doSomeThing();

        SingletonDemo6 instance2 = SingletonDemo6.INSTANCE;
        instance2.doSomeThing();
    }
}


//懒汉 线程不安全
class SingletonDemo1 {

    private SingletonDemo1(){}

    private static SingletonDemo1 instance = null;

    public static SingletonDemo1 getInstance() {
        if (instance == null) {
            instance = new SingletonDemo1();
        }
        return instance;
    }
}

//懒汉 线程安全 直接加锁
class SingletonDemo2 {

    private SingletonDemo2() {}

    private static SingletonDemo2 instance = null;

    public static synchronized SingletonDemo2 getInstance() {
        if (instance == null) {
            instance = new SingletonDemo2();
        }
        return instance;
    }
}

// Double Check
class SingletonDemo3 {
    private volatile static SingletonDemo3 instance;

    private SingletonDemo3() {
    }

    public static SingletonDemo3 getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new SingletonDemo3();
                }
            }
        }
        return instance;
    }
}


//饿汉 线程安全
class SingletonDemo4 {

    private SingletonDemo4() {}

    private static SingletonDemo4 instance = new SingletonDemo4();

    public static SingletonDemo4 getInstance() {
        return instance;
    }
}

//内部类 线程安全，并且懒加载
class SingletonDemo5 {

    private SingletonDemo5() {}

    private static class SingletonDemo5Holder {
        private static final SingletonDemo5 instance = new SingletonDemo5();
    }

    public static final SingletonDemo5 getInstance() {
        return SingletonDemo5Holder.instance;
    }
}

// 枚举方式。最为推荐的一种方式
enum SingletonDemo6 {
    INSTANCE;

    SingletonDemo6() {
        System.out.println("init");
    }


    public void doSomeThing() {
        System.out.println("do some thing");
    }
}
