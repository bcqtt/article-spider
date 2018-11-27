package com.lz.study.netty.serial.test;

import java.util.Arrays;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public class Test3 {

	public static void main(String[] args) {

		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		buffer.writeInt(101);
		buffer.writeDouble(80.1);

		byte[] bytes = new byte[buffer.writerIndex()];
		buffer.readBytes(bytes);
		
		System.out.println(Arrays.toString(bytes));
		
		"abc".getBytes();
		
		//================================================
		ChannelBuffer wrappedBuffer = ChannelBuffers.wrappedBuffer(bytes);
		System.out.println(wrappedBuffer.readInt());
		System.out.println(wrappedBuffer.readDouble());
		
	}

}
