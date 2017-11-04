package com.lz.pattern.commend.base;

/**
 * 具体命令角色
 * Function:
 * @author : laizhiwen
 * Date: 2017年9月30日
 */
public class ConcreteCommand implements Command {
	
	 //持有相应的接收者对象
    private Receiver receiver = null;
    
    /**
     * 构造方法
     */
    public ConcreteCommand(Receiver receiver){
        this.receiver = receiver;
    }

	@Override
	public void execute() {
		receiver.action();
	}

}
