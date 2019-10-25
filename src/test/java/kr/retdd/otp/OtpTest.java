package kr.retdd.otp;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OtpTest {

	@Test
	public void otpTest1() {
		log.info("OTP key {}", OTPUtil.generate("fehead", "devmon.co.kr"));
	}
	
	@Test
	public void otpTest2() {
		log.info("OTP check {}", OTPUtil.checkCode("064699", "PQJ4EBLEVN4ULPSC"));
	}
}
