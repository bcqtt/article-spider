package com.lz.pattern.decorator.example2;

/**
 * 装饰模式应用
 */
public class UseDecorator {
    public static void main(String[] args){
        //1.创建具体构建角色
        Component p=new ConcreteComponent();
        //2.调用具体构建角色的方法
        p.operation();
        System.out.println("---------------------------------");

        //3.创建具体装饰角色，具体装饰角色继承抽象装饰角色，重写抽象装饰类的operation(),在里面是用super.operation()执行原有功能，再扩展新功能
        Component d=new ConcreteDecorator(p);
        d.operation();
    }
}
