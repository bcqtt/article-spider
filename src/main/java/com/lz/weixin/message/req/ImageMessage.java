package com.lz.weixin.message.req;

/**
 * 图片消息
 * Function:
 * @author : laizhiwen
 * Date: 2017年8月31日
 */
public class ImageMessage extends BaseMessage {  
    // 图片链接  
    private String PicUrl;  
  
    public String getPicUrl() {  
        return PicUrl;  
    }  
  
    public void setPicUrl(String picUrl) {  
        PicUrl = picUrl;  
    }  
}  
