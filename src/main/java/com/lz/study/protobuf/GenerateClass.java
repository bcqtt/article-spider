package com.lz.study.protobuf;

import java.io.IOException;

public class GenerateClass {
	
	public static void main(String[] args) {
        String strCmd = "D:/protobuf-3.6.1/src/protoc.exe -I=./proto --java_out=./src/main/java/com/lz/study/protobuf ./LoginApply.proto";  
        try {
            Runtime.getRuntime().exec(strCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }//通过执行cmd命令调用protoc.exe程序  
    }

}
