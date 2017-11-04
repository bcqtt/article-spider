package com.lz.pattern.commend.base;

public class Receiver {
	/**
     * 真正执行命令相应的操作。负责具体实施和执行请求。
     * 任何一个类都可以成为接收者，实施和执行请求的方法叫做行动方法。
     */
    public void action(){
        System.out.println("执行操作");
    }
}
