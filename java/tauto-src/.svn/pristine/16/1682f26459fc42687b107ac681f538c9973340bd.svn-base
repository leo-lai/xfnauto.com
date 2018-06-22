package main.com.utils.weixin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * 
 * @author LEE.SIU.WAH
 * @version 1.0
 */
public final class MD5 {

    public static String getMD5(String source) throws NoSuchAlgorithmException {
	MessageDigest md = MessageDigest.getInstance("MD5");
	md.update(source.getBytes());
	byte[] md5Bytes = md.digest();
	StringBuilder res = new StringBuilder();
	for (int i = 0; i < md5Bytes.length; i++) {
	    int t = md5Bytes[i] & 0xff;
	    if (t <= 0xf) {
		res.append("0");
	    }
	    res.append(Integer.toHexString(t));
	}
	return res.toString();
    }

}
