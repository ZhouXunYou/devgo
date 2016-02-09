package devgo.util;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

public class EncodeUtil {
	/**
	 * 3Des加密+Base64加密
	 * 
	 * @param enStr	需要加密的字符串
	 * @param key	密钥
	 * @return
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws Exception
	 */
	public static String encodeDES(String enStr, String key) throws Exception {
		SecretKey deskey = new SecretKeySpec(key.getBytes(), "DESede");
		Cipher cipher = Cipher.getInstance("DESede");
		cipher.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] encodeValue = cipher.doFinal(enStr.getBytes());
		String encodeString = StringUtils.newStringUtf8(Base64.encodeBase64(encodeValue, false));
		return encodeString;
	}
	public static String md5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString().toUpperCase();
	}

	public static String md5(String inStr, int startIndex, int offset) {
		return md5(inStr).substring(startIndex, offset);
	}
	public static void main(String[] args) {
		System.out.println(md5("1").length());
	}
}
