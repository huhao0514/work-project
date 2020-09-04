package com.chinadass.api.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class MD5ChecksumUtils {

	/**
	 * 生成签名
	 * 
	 * @param date
	 *            需要签名的数据
	 * @return 数据的md5签名
	 * @throws Exception
	 *             获得签名失败
	 */
	public static byte[] createChecksum(byte[] date) throws Exception {
		MessageDigest complete = MessageDigest.getInstance("MD5");
		complete.update(date);
		return complete.digest();
	}

	/**
	 * 将签名结果变成16进制的字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	/**
	 * 创建文件的签名
	 * 
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public static byte[] createChecksum(String filename) throws Exception {
		InputStream fis = new FileInputStream(filename);

		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("MD5");
		int numRead;
		do {
			numRead = fis.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);
		fis.close();
		return complete.digest();
	}

	/**
	 * 创建文件的md5签名
	 * 
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public static String getMD5Checksum(String filename) throws Exception {
		byte[] b = createChecksum(filename);
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	/**
	 * 计算字符串的MD5值
	 * 
	 * @param message
	 *            给定的字符串
	 * @return
	 * @throws Exception
	 */
	public static String getMD5(String message) throws Exception {
		byte[] b = message.getBytes();
		return byte2hex(createChecksum(b));
	}

	public static void main(String[] args) {
		String password = "123456";
		try {
			password = MD5ChecksumUtils.getMD5(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(password.toUpperCase());
	}

}
