package com.lz.weixin.message.req;

/**
 * 文本消息
 * Function:
 * @author : laizhiwen
 * Date: 2017年8月31日
 */
public class TextMessage extends BaseMessage {
	// 消息内容  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }  
}
