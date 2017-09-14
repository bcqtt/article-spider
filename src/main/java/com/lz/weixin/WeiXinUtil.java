package com.lz.weixin;

import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.lz.weixin.vo.SendRedPackPo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 微信消息工具类
 */
public class WeiXinUtil {


	/**
	 * 解析微信发来的请求（XML）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();

		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getText());

		// 释放资源
		inputStream.close();
		inputStream = null;

		return map;
	}



//	/**
//	 * 文本消息对象转换成xml
//	 * 
//	 * @param textMessage
//	 *            文本消息对象
//	 * @return xml
//	 */
//	public static String textMessageToXml(TextMessage textMessage) {
//		xstream.alias("xml", textMessage.getClass());
//		return xstream.toXML(textMessage);
//	}
//
//	/**
//	 * 音乐消息对象转换成xml
//	 * 
//	 * @param musicMessage
//	 *            音乐消息对象
//	 * @return xml
//	 */
//	public static String musicMessageToXml(MusicMessage musicMessage) {
//		xstream.alias("xml", musicMessage.getClass());
//		return xstream.toXML(musicMessage);
//	}
//
//	/**
//	 * 图文消息对象转换成xml
//	 * 
//	 * @param newsMessage
//	 *            图文消息对象
//	 * @return xml
//	 */
//	public static String newsMessageToXml(NewsMessage newsMessage) {
//		xstream.alias("xml", newsMessage.getClass());
//		xstream.alias("item", new Article().getClass());
//		return xstream.toXML(newsMessage);
//	}

	public static String messageToXml(SendRedPackPo sendRedPackPo) {
		xstream.alias("xml", sendRedPackPo.getClass());
		return xstream.toXML(sendRedPackPo);
	}

	/**
	 * 扩展xstream，使其支持CDATA块
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
	
	
	/**
	    * 生成6位或10位随机数
	    * param codeLength(多少位)
	    * @return
	    */
	    public static String createCode(int codeLength) {
		    String code="";
		    for(int i=0; i<codeLength; i++) {
		    	code += (int)(Math.random() * 9);
		    }
		    return code;
	    }
	    public static boolean isValidChar(char ch) {  
			if ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z')|| (ch >= 'a' && ch <= 'z'))  
			    return true;  
			if ((ch >= 0x4e00 && ch <= 0x7fff) || (ch >= 0x8000 && ch <= 0x952f))  
			    return true;// 简体中文汉字编码  
			return false;  
	    }
	    
	    /** 
	     * 除去数组中的空值和签名参数
	     * @param sArray 签名参数组
	     * @return 去掉空值与签名参数后的新签名参数组
	     */
	    public static Map<String, String> paraFilter(Map<String, String> sArray) {
	        Map<String, String> result = new HashMap<String, String>();
	        if (sArray == null || sArray.size() <= 0) {
	            return result;
	        }
	        for (String key : sArray.keySet()) {
	            String value = sArray.get(key);
	            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
	                || key.equalsIgnoreCase("sign_type")) {
	                continue;
	            }
	            result.put(key, value);
	        }
	        return result;
	    }
	    
	    /** 
	     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	     * @param params 需要排序并参与字符拼接的参数组
	     * @return 拼接后字符串
	     */
	    public static String createLinkString(Map<String, String> params) {
	        List<String> keys = new ArrayList<String>(params.keySet());
	        Collections.sort(keys);
	        String prestr = "";
	        for (int i = 0; i < keys.size(); i++) {
	            String key = keys.get(i);
	            String value = params.get(key);
	            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
	                prestr = prestr + key + "=" + value;
	            } else {
	                prestr = prestr + key + "=" + value + "&";
	            }
	        }
	        return prestr;
	    }



}