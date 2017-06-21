package com.lz.art.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  
@RequestMapping("download")
public class DownloadController {

	@RequestMapping(value = "/app")
	public void downloadSth(HttpServletRequest req, HttpServletResponse resp){
		String model = req.getParameter("model");  //机型
		String imei = req.getParameter("imei");    //imei
		String pkg = req.getParameter("pkg");      //报名
		String link = req.getParameter("link");    //下载链接
		String nt = req.getParameter("nt");        //网络类型
		
		BufferedInputStream bis = null;
		try {
			URL fileUrl = new URL(link);
	    	HttpURLConnection conn = (HttpURLConnection)fileUrl.openConnection();
	    	conn.connect();
			resp.setContentType("application/force-download");
			resp.addHeader("Content-Disposition", "attachment;fileName=" + pkg);
			byte[] buffer = new byte[1024];
			try {
				bis = new BufferedInputStream(conn.getInputStream());
				OutputStream os = resp.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				bis.close();
			}
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**  
     * 把一个文件转化为字节  
     * @param link  
     * @return byte[]  
     * @throws Exception  
     */  
	private byte[] getFileByte(String link) throws Exception {  
    	URL fileUrl = new URL(link);
    	HttpURLConnection conn = (HttpURLConnection)fileUrl.openConnection();
    	conn.connect();
		InputStream is = conn.getInputStream();
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = 0;  
        while ((len = is.read(b, 0, 100)) > 0) {  
            swapStream.write(b, 0, len);  
        }  
        return swapStream.toByteArray();  
    }  
	
}
