package com.lz.art.common;

public class DataNotyOptions {
	
	/**
	 * 提示内容
	 */
	private String text;
	
	/**
	 * 以下值供选择：
	 * topLeft/topCenter/topRight
	 * top/center/bottom
	 * bottomLeft/bottomCenter/bottomRight
	 */
	private String layout;
	
	/**
	 * 提示类型
	 * success、error、information、alert
	 */
	private String type;
	
	public DataNotyOptions(String type){
		if(type.equals("success")){
			this.text = "操作成功";
		}else if(type.equals("error")){
			this.text = "操作失败";
		}
		this.type = type;
		this.layout="top";
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
