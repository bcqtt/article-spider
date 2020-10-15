package com.lz.pattern.decorator.example2;

//具体装饰角色
public class ConcreteDecorator extends Decorator {
    public ConcreteDecorator(Component component) {
        super(component);
    }

    public void operation() {
        super.operation(); //调用原有业务方法
        addedFunction();   //调用新增的业务方法
    }

    public void addedFunction() {
        System.out.println("为具体构件角色增加额外的功能addedFunction()");
    }
}