package com.lz.pattern.memo;

/**
 * 管理员角色
 * 对备忘录进行管理、保存和提供备忘录，只能将备忘录传递给其他角色。
 */
public class Caretaker {

    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }

}