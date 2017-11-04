package com.lz.pattern.commend.base;

/**
 * 请求者角色类
 * Function:
 * @author : laizhiwen
 * Date: 2017年9月30日
 */
public class Invoker {
	 /**
     * 持有命令对象
     */
    private Command command = null;
    
    /**
     * 构造方法
     */
    public Invoker(Command command){
        this.command = command;
    }
    
    /**
     * 行动方法
     */
    public void action(){
        
        command.execute();
    }
}
