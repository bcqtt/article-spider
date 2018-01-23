package com.lz.image.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class GaussianBlur {
	public static void main(String[] args) {  
        try{  
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  
              
            Mat src=Imgcodecs.imread("C:\\Users\\gionee\\Desktop\\123860572759110476.jpg");  
            //读取图像到矩阵中  
            if(src.empty()){  
                throw new Exception("no file");  
            }  
              
            Mat dst = src.clone();  
            //复制矩阵进入dst  
              
            //图像模糊化处理1
            Imgproc.GaussianBlur(src,dst,new Size(13,13),10,10);  
            Imgcodecs.imwrite("D:/data/img/gaussianblur1.jpg", dst);  
              
            //图像模糊化处理2
            Imgproc.GaussianBlur(src,dst,new Size(151,151),25,25);  
            Imgcodecs.imwrite("D:/data/img/gaussianblur2.jpg", dst);  
            
            //图像模糊化处理3
            Imgproc.GaussianBlur(src,dst,new Size(51,101),25,25);  
            Imgcodecs.imwrite("D:/data/img/gaussianblur3.jpg", dst);  
            
            //图像模糊化处理4
            Imgproc.GaussianBlur(src,dst,new Size(161,161),1000,1000);  
            Imgcodecs.imwrite("D:/data/img/gaussianblur4.jpg", dst);  
            System.out.println("模糊完毕...");  
        }catch(Exception e){  
            System.out.println("例外：" + e);  
        }  
  
    } 
}
