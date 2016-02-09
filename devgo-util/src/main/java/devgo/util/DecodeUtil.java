package devgo.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class DecodeUtil {
	/**
	 * Base64解密+3Des解密
	 * 
	 * @return
	 * @throws
	 * @throws Exception
	 */
	public static String decode(String deStr, String key) throws Exception {
		byte[] decodeBytes = Base64.decodeBase64(deStr);
		SecretKey deskey = new SecretKeySpec(key.getBytes(), "DESede");
		Cipher cipher = Cipher.getInstance("DESede");
		cipher.init(Cipher.DECRYPT_MODE, deskey);
		byte[] decodeValue = cipher.doFinal(decodeBytes);
		return new String(decodeValue);
	}
}
