package rsa;

import java.math.BigInteger;

import org.junit.Test;

import com.lz.study.rsa.AESUtil;
import com.lz.study.rsa.KeyUtil;
import com.lz.study.rsa.RSAUtils;

public class RSATest {
	
	@Test
	public void test1(){
		byte[] hold = {0x11,0x00};
		String data = binary(hold, 10);
		System.out.println(data);
		
	}
	
	public static String binary(byte[] bytes, int radix) {
	    return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
	  }
	
	
	@Test
	public void encryptTest() throws Exception{
		
		String aesKey = "1111111111111111";
		
		String data="{\r\n" + 
				"    \"Command\": \"COM_DEV_REGISTER \",\r\n" + 
				"    \"Data\": [\r\n" + 
				"        {\r\n" + 
				"            \"Type \": 1,\r\n" + 
				"            \"deviceID\": \"1001201600FF81992F49\",\r\n" + 
				"            \"manufacturer\": \"XXX厂商\",\r\n" + 
				"            \"macNO\": 102,\r\n" + 
				"            \"locationAddr\": \"XXX地址\",\r\n" + 
				"            \"name\": \"XXX设备\",\r\n" + 
				"            \"ip\": \"192.168.10.88\",\r\n" + 
				"            \"gateWay\": \"00000000000000000\",\r\n" + 
				"            \"mac\": \"00:FF:81:99:2F:49\",\r\n" + 
				"            \"mask\": \"255.255.255.0\",\r\n" + 
				"            \"aesKey\": \"1111111111111111\",\r\n" + 
				"            \"version\": \"V1.0.16_20171225001\"\r\n" + 
				"        }\r\n" + 
				"    ]\r\n" + 
				"}";
		
		//AES秘钥方式
		byte[] aesDataByte = AESUtil.encrypt(data, aesKey);
		System.out.println(AESUtil.parseByte2HexStr(aesDataByte));
		
		
		//将Base64编码后的Server公钥转换成PublicKey对象
		byte[] aesKeyByRSA = RSAUtils.encryptByPublicKey(aesKey.getBytes(), KeyUtil.SERVER_PUBLIC_KEY);
		
		byte[] resultDecode = RSAUtils.decryptByPrivateKey(aesKeyByRSA, KeyUtil.SERVER_PRIVATE_KEY);
		//用Server公钥加密AES秘钥
		
		String aes = new String(resultDecode,"utf-8");
		System.out.println("AES秘钥明文：" + aes);
		
		byte[] resultData = AESUtil.decrypt(AESUtil.parseByte2HexStr(aesDataByte), aes);
		System.out.println(new String(resultData,"utf-8"));
		
		//单独RSA方式
		byte[] dataBytes = data.getBytes();
		System.out.println("data的字节数：" + dataBytes.length);
		byte[] rsaDataByte = RSAUtils.encryptByPublicKey(data.getBytes(), KeyUtil.SERVER_PUBLIC_KEY);
		System.out.println(AESUtil.parseByte2HexStr(rsaDataByte));
		
		byte[] decryData = RSAUtils.decryptByPrivateKey(rsaDataByte, KeyUtil.SERVER_PRIVATE_KEY);
		System.out.println(new String(decryData,"utf-8"));
	}
	

}
