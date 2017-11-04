package com.lz.other;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImgGaussianBlur {
	
	public static BufferedImage img;
	
	public static void main(String[] args) {
		readImage();
		blur(10,img.getWidth()*img.getHeight());
		writeImage();
	}
	
	public static void readImage(){
		ImgGaussianBlur.img = null;
		try {
			img = ImageIO.read(new File("C:\\Users\\gionee\\Desktop\\广告图片\\图片集\\0101-3.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void blur(double sigma,int kernelSize){
		double kernel[] = createKernel(sigma,kernelSize);
		for(int i=0; i<img.getWidth(); i++){
			for(int j=0;j<img.getHeight(); j++){
				float overflow = 0;
				int counter = 0;
				int kernelhalf = (kernelSize-1)/2;
				double red = 0;
				double green = 0;
				double blue = 0;
				for(int k=i-kernelhalf; k<i+kernelSize; k++){
					for(int l=j-kernelhalf; l<j+kernelSize; l++){
						if(k<0 || k>=img.getWidth() || l<0 || l>=img.getHeight()){
							counter++;
							overflow += kernel[counter];
							continue;
						}
						
						Color c = new Color(img.getRGB(k, l));
						red += c.getRed() * kernel[counter];
						green += c.getGreen() * kernel[counter];
						blue += c.getBlue() * kernel[counter];
						counter++;
					}
					counter++;
				}
				
				if(overflow > 0){
					red = 0;
					green = 0;
					blue = 0;
					counter = 0;
					for(int k=i-kernelhalf; k<i+kernelSize; k++){
						for(int l=j-kernelhalf; l<j+kernelSize; l++){
							if(k<0 || k>=img.getWidth() || l<0 || l>=img.getHeight()){
								counter++;
								continue;
							}
							
							Color c = new Color(img.getRGB(k, l));
							red += c.getRed() * kernel[counter]* (1/(1-overflow));
							green += c.getGreen() * kernel[counter]* (1/(1-overflow));
							blue += c.getBlue() * kernel[counter]* (1/(1-overflow));
							counter++;
						}
						counter++;
					}
				}
				
				ImgGaussianBlur.img.setRGB(i, i, new Color((int)red,(int)green,(int)blue).getRGB());
			}
		}
	}
	
	public static double[] createKernel(double sigma,int kernelSize){
		double kernel[] = new double[kernelSize*kernelSize];
		for(int i=0;i<kernelSize;i++){
			double x = i - (kernelSize-1)/2;
			for(int j=0;j<kernelSize;j++){
				double y = j - (kernelSize-1)/2;
				kernel[j + i*kernelSize] = 1/(Math.PI*sigma*sigma) * Math.exp(-(x*x + y*y)/(2*sigma*sigma));
			}
		}
		
		float sum = 0;
		for(int i=0; i<kernelSize;i++){
			for(int j=0;j<kernelSize;j++){
				sum += kernel[j + i*kernelSize];
			}
		}
		for(int i=0; i<kernelSize;i++){
			for(int j=0;j<kernelSize;j++){
				kernel[j + i*kernelSize] /= sum;
			}
		}
		
		return kernel;
	}
	
	public static void lineareAbbildung(double a,double b){
		for(int x = 0 ; x < img.getWidth(); x++){
			for(int y=0; y<img.getHeight(); y++){
				int rgb = (int) (a*ImgGaussianBlur.img.getRGB(x, y) + b);
				ImgGaussianBlur.img.setRGB(x, y, rgb);
			}
		}
	}
	
	public static void writeImage(){
		try {
			ImageIO.write(ImgGaussianBlur.img, "jpeg", new File("d:/data/test.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}


