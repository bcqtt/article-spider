package com.lz.pattern.decorator.example2;

/**
 * 装饰模式应用
 */
public class UseDecorator {
    public static void main(String[] args){
        Component p=new ConcreteComponent();
        p.operation();
        System.out.println("---------------------------------");
        Component d=new ConcreteDecorator(p);
        d.operation();
    }
}
