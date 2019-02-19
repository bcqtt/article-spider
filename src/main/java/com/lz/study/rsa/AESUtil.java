package com.lz.study.rsa;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AESUtil {

	private AESUtil() {
	}

	public static final String ENCODING = "UTF-8";

	/**
	 * 密钥算法.
	 */
	private static final String ALGORITHM = "AES";
	/**
	 * 加解密算法/工作模式/填充方式.
	 */
	private static final String ALGORITHM_STR = "AES/ECB/PKCS5Padding"; // 无偏移量

	/**
	 * 加密.
	 * 
	 * @param src  default
	 * @param skey default
	 * @return byte.
	 * @throws Exception exception
	 */
	public static byte[] encrypt(String src, String skey) throws UnsupportedEncodingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] key = {};
		if (skey.isEmpty()) {
			System.out.println("Key为空null");
			return key;
		}
		// 判断Key是否为16位
		if (skey.length() != 16) {
			System.out.println("Key长度不是16位");
			return key;
		}
		key = skey.getBytes(ENCODING);
		SecretKeySpec skeySpec = new SecretKeySpec(key, ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM_STR);// "算法/模式/补码方式"
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

		return cipher.doFinal(src.getBytes(ENCODING));
	}

	/**
	 * 解密.
	 * 
	 * @param src  default
	 * @param skey default
	 * @return byte[]
	 * @throws Exception           Exception
	 * @throws BadPaddingException byte[]
	 */
	public static byte[] decrypt(String src, String skey) throws UnsupportedEncodingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] key = {};
		// 判断Key是否正确
		if (skey.isEmpty()) {
			System.out.println("Key为空null");
			return key;
		}
		// 判断Key是否为16位
		if (skey.length() != 16) {
			System.out.println("Key长度不是16位");
			return key;
		}
		key = skey.getBytes(ENCODING);
		SecretKeySpec skeySpec = new SecretKeySpec(key, ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		return cipher.doFinal(parseHexStr2Byte(src));

	}

	/**
	 * 将二进制转换成16进制.
	 * 
	 * @param buf defualt
	 * @return
	 */
	public static String parseByte2HexStr(byte[] buf) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制.
	 * 
	 * @param hexStr default
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		byte[] result = {};
		if (hexStr.length() < 1) {
			return result;
		}
		result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		/*
		 * 此处使用AES-128-ECB加密模式，key需要为16位。
		 */
		String cKey = "1234567890123456";
		// 需要加密的字串
		String cSrc = "www.gowhere.so";
		System.out.println(cSrc);
		// 加密
		byte[] enString = AESUtil.encrypt(cSrc, cKey);
		System.out.println("加密后的字串是：" + new String(enString, "UTF-8"));

		// 解密
		byte[] DeString = AESUtil.decrypt(AESUtil.parseByte2HexStr(enString), cKey);
		System.out.println("解密后的字串是：" + new String(DeString, "UTF-8"));
	}

}
