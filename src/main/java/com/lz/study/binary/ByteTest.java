package com.lz.study.binary;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.lz.util.ByteUtils;

public class ByteTest {
	
	private static float num = 123456.78f;
	
	public static void main(String[] args) {
//		byte[] numByte = new byte[4];
//		numByte[0] = 0x78;
//		numByte[1] = 0x56;
//		numByte[2] = 0x34;
//		numByte[3] = 0x12;
//		System.out.println("电表数据：" + num);
//		System.out.println("===============数据加上0x33之后再传输====================");
//		for(int i=0;i<numByte.length;i++) {
//			numByte[i] = (byte) (numByte[i] + 0x33);
//		}
//		
//		
//		System.out.println("===============数据减去0x33之后再解析====================");
//		for(int i=0;i<numByte.length;i++) {
//			numByte[i] = (byte) (numByte[i] - 0x33);
//		}
//		
//		
//		System.out.println("解析结果：" + "");
		int n = 20001;
		for(int i=1;i<=9000;i++) {
			String uuid = UUID.randomUUID().toString().replaceAll("-","");  
			System.out.println(uuid + "\t" + n++);
	    }
		
	}

}
