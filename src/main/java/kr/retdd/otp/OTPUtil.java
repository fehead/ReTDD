package kr.retdd.otp;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;

public class OTPUtil {
	public static HashMap<String, String> generate(String name, String host) {
        HashMap<String, String> map = new HashMap<String, String>();
        byte[] buffer = new byte[5 + 5 * 5];        //버퍼에 할당
        new Random().nextBytes(buffer);				//버퍼를 난수로 셋팅
        Base32 codec = new Base32();
        byte[] secretKey = Arrays.copyOf(buffer, 10);
        byte[] bEncodedKey = codec.encode(secretKey);

        String encodedKey = new String(bEncodedKey);        //생성된 Key (해당키로 OTP확인)

        map.put("encodedKey", encodedKey);
        map.put("url", host);

        return map;
    }

    //OTP코드 6자리 Check
    public static boolean checkCode(String userCode, String otpkey) {
        long otpnum = Integer.parseInt(userCode); // OTP 앱에 표시되는 6자리 숫자
        long wave = new Date().getTime() / 30000; // OTP의 주기(초)
        boolean result = false;
        try {
             Base32 codec = new Base32();
             byte[] decodedKey = codec.decode(otpkey);
             int window = 3;
             for (int i = -window; i <= window; ++i) {
                 long hash = verify_code(decodedKey, wave + i);
                 if (hash == otpnum) result = true;
             }
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
             e.printStackTrace();
        }
     return result;
    }

    private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
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
        
        return (int) truncatedHash;
    }
}
