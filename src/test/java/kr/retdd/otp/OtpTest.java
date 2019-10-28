package kr.retdd.otp;

import java.util.Scanner;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OtpTest {

	@Test
	public void otpTest1() {
		// 여기에서 otp key값이 생성된다 예) "PQJ4EBLEVN4ULPSC"
		log.info("OTP key {}", OTPUtil.generateSecreKey());
		log.info("OTP URL {}", OTPUtil.getQRBarcodeURL("test", "devmon.co.kr", "PQJ4EBLEVN4ULPSC"));

		final String sk = OTPUtil.generateSecreKey();
		log.info("new OTP URL {}", OTPUtil.getQRBarcodeURL("testR", "devmon.co.kr", sk));
	}
	
	@Test
	public void otpTest2() {
		
		try(Scanner in = new Scanner(System.in)) {
			System.out.print("Enter userCode :");
			Long userCode = in.nextLong();
			// 구글 OTP에서 나온 6자리 숫자를 앞에 OTP key값을 뒤에 넣어주어 체크한다.
			log.info("OTP check {}", OTPUtil.checkCode(userCode.toString(), "PQJ4EBLEVN4ULPSC"));
		}
	}
}
