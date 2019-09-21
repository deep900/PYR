/**
 * 
 */
package com.pry.security.utility;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @author pradheep.p
 *
 */
public class PublicUtility {

	private long serialVersionID = 934993400923l;

	private PublicUtility() {

	}

	private static PublicUtility publicUtility = new PublicUtility();

	public static PublicUtility getInstance(String appKey) {
		if (_is_KeyValid(appKey)) {
			return publicUtility;
		}
		return null;
	}

	public static int operateBinary(int a, int b, IntegerMath op) {
		return op.operation(a, b);
	}

	private static interface IntegerMath {
		int operation(int a, int b);
	}

	private static boolean _is_KeyValid(String appKey) {
		boolean flag = false;
		String arg = publicUtility.DecryptText(appKey);
		IntegerMath division_ = (divident, divisor) -> divident % divisor;
		Integer _divident = Integer.parseInt(arg);
		Integer _divisor = 3400 / 200;
		int ans = operateBinary(_divident, _divisor, division_);
		if (ans == 0) {
			flag = true;
		}
		return flag;
	}

	private final String key_ = "45&dfrgYutQA3$#ZxcFGLoUi";

	private static byte[] sharedvector_ = { 0x01, 0x02, 0x03, 0x05, 0x07, 0x0B, 0x0D, 0x11 };

	public String EncryptText(String RawText) {
		String EncText = "";
		byte[] keyArray = new byte[24];
		byte[] temporaryKey;

		byte[] toEncryptArray = null;

		try {

			toEncryptArray = RawText.getBytes("UTF-8");
			MessageDigest m = MessageDigest.getInstance("MD5");
			temporaryKey = m.digest(key_.getBytes("UTF-8"));

			if (temporaryKey.length < 24) // DESede require 24 byte length key
			{
				int index = 0;
				for (int i = temporaryKey.length; i < 24; i++) {
					keyArray[i] = temporaryKey[index];
				}
			}

			Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector_));
			byte[] encrypted = c.doFinal(toEncryptArray);
			EncText = Base64.encodeBase64String(encrypted);

		} catch (NoSuchAlgorithmException NoEx) {

		}

		catch (UnsupportedEncodingException ex) {

		} catch (NoSuchPaddingException ex) {

		} catch (InvalidKeyException ex) {

		} catch (InvalidAlgorithmParameterException ex) {

		} catch (IllegalBlockSizeException ex) {

		} catch (BadPaddingException ex) {

		}

		return EncText;
	}

	public String DecryptText(String EncText) {

		String RawText = "";
		byte[] keyArray = new byte[24];
		byte[] temporaryKey;

		byte[] toEncryptArray = null;

		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			temporaryKey = m.digest(key_.getBytes("UTF-8"));

			if (temporaryKey.length < 24) // DESede require 24 byte length key
			{
				int index = 0;
				for (int i = temporaryKey.length; i < 24; i++) {
					keyArray[i] = temporaryKey[index];
				}
			}

			Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector_));
			byte[] decrypted = c.doFinal(Base64.decodeBase64(EncText));

			RawText = new String(decrypted, "UTF-8");
		} catch (NoSuchAlgorithmException NoEx) {

		} catch (UnsupportedEncodingException ex) {

		} catch (NoSuchPaddingException ex) {

		} catch (InvalidKeyException ex) {

		} catch (InvalidAlgorithmParameterException ex) {

		} catch (IllegalBlockSizeException ex) {

		} catch (BadPaddingException ex) {

		}
		return RawText;
	}
	
	public static void main(String args[]) {		
		PublicUtility pub = new PublicUtility();		
	}

}
