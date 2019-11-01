package kr.retdd.basic;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.OptionalInt;

import org.junit.Test;

public class OptionalTest {
	
	@Test
	public void optionalIntTest() {
		OptionalInt opt1 = OptionalInt.of(0); // OptionalInt에 0을 저장 
		OptionalInt opt2 = OptionalInt.empty(); // 빈 OptionalInt객체. OptionalInt에 0이 저장됨 
		assertThat(opt1.equals(opt2)).isFalse();
	}

	@Test
	public void optionalOfNullableTest() {
		Optional<String> opt3 = Optional.ofNullable(null); // null이 저장된 Optional객체 
		Optional<String> opt4 = Optional.empty(); // 빈 Optional객체. null이 저장됨
		assertThat(opt3.equals(opt4)).isTrue();
	}
}
