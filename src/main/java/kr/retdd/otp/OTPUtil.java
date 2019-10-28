package kr.retdd.otp;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;

// 참고 : https://zero-gravity.tistory.com/221
public class OTPUtil {
	public static String generateSecreKey() {
        byte[] secretKey = new byte[10];        //버퍼에 할당
        new Random().nextBytes(secretKey);		//버퍼를 난수로 셋팅
        Base32 codec = new Base32();
        byte[] bEncodedKey = codec.encode(secretKey);
        String encodedKey = new String(bEncodedKey);        //생성된 Key (해당키로 OTP확인)
        return encodedKey;
    }

	public static String getQRBarcodeURL(String user, String host, String secret) {
		// QR코드 주소 생성
		String fmt = "http://chart.apis.google.com/chart?cht=qr&chs=300x300"
				 + "&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s&chld=H|0";
		return String.format(fmt, user, host, secret);
	}
	
	// OTP코드 6자리 Check
	public static boolean checkCode(String userCode, String otpkey) {
		int otpnum = Integer.parseInt(userCode); // OTP 앱에 표시되는 6자리 숫자
		long wave = new Date().getTime() / 30000; // OTP의 주기(초)
		try {
			Base32 codec = new Base32();
			byte[] decodedKey = codec.decode(otpkey);
			int window = 3;
			for (int i = -window; i <= window; ++i) {
				long hash = verifyCode(decodedKey, wave + i);
				if (hash == otpnum)
					return true;
			}
		} catch (InvalidKeyException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return false;
	}

    private static int verifyCode(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        long value = t;
        for (int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte) value;
        }

        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);

        int offset = hash[20 - 1] & 0xF;

        // We're using a long because Java hasn't got unsigned int.
        long truncatedHash = 0;
        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;

            // We are dealing with signed bytes:
            // we just keep the first byte.
            truncatedHash |= (hash[offset + i] & 0xFF);
        }

        truncatedHash &= 0x7FFFFFFF;
        truncatedHash %= 1000000;
        
        return (int)truncatedHash;
    }
}
