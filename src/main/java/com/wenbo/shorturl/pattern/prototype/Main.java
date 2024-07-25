package com.wenbo.shorturl.pattern.prototype;

import cn.hutool.core.bean.BeanUtil;
import com.wenbo.shorturl.pattern.Car;
import com.wenbo.shorturl.pattern.builder.CarDirector;
import com.wenbo.shorturl.pattern.builder.MiCarBuilder;
import org.springframework.beans.BeanUtils;

/**
 * @author changwenbo
 * @date 2024/7/25 17:53
 */
public class Main {
    public static void main(String[] args) {

        CarDirector director = new CarDirector(new MiCarBuilder());
        Car car = director.constructCar();

        // 1. 原型模式
        // car1 和 car2 是两个不同的对象
        Car car2 = car.clone();
        System.out.println(car == car2);
        System.out.println("car1 hashCode: " + System.identityHashCode(car));
        System.out.println("car2 hashCode: " + System.identityHashCode(car2));

        System.out.println("=====================================");

        Car car3 = BeanUtil.toBean(car, Car.class);
        System.out.println(car == car3);
        System.out.println("car1 hashCode: " + System.identityHashCode(car));
        System.out.println("car2 hashCode: " + System.identityHashCode(car3));

        System.out.println("=====================================");

        Car car4 = new Car();
        BeanUtils.copyProperties(car, car4);
        System.out.println(car == car4);
        System.out.println("car1 hashCode: " + System.identityHashCode(car));
        System.out.println("car2 hashCode: " + System.identityHashCode(car4));
    }

}
