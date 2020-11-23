package com.lz.pattern.memo;

public class Client {

    public static void main(String[] args) {
        Originator originator = new Originator();// 创建发起人对象
        originator.setState("On");    //设置Originator初始状态
        originator.show();

        Caretaker caretaker = new Caretaker();   // 创建管理员对象
        caretaker.setMemento(originator.createMento());  // 通过发起人创建备忘录，并已经保存了发起人的当前状态

        originator.setState("Off");    //发起人Originator状态变为Off
        originator.show();

        originator.setMemento(caretaker.getMemento());    //回复初始状态，这里面的备忘录是已经保存好上一个状态的了，直接读取就能获得
        originator.show();
    }

}