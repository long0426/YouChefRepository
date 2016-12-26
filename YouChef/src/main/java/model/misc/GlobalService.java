package model.misc;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class GlobalService {
	public static final String KEY="youchefverygoood"; 
	
	public static String getMD5Encoding(String message){
		final StringBuffer buffer = new StringBuffer();
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(message.getBytes());
			byte[] digest = md.digest();
			for (int i=0; i < digest.length; i++) {
				final byte b = digest[i];
				final int value =  (b & 0x7F) + (b < 0 ? 128 : 0);
				buffer.append(value < 16 ? "0" : "");
				buffer.append(Integer.toHexString(value));
			}
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return buffer.toString();
	}

	public static String encryptString (String message){
		String encryptedString ="";
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(),"AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			encryptedString = DatatypeConverter.printBase64Binary(cipher.doFinal(message.getBytes()));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return encryptedString;
	}
	
//	public static String decryptString (String key, String stringToDecrypt){
//		String decryptString="";
//		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//		SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), algorithm)
//		
//		
//		return decryptString;
//	}
	
	public static void main(String[] args){
		String message = "A@123456";
		encryptString(message);		
		System.out.println(encryptString(message));
		System.out.println(getMD5Encoding(message));

	}
}