package kr.retdd.basic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import lombok.Getter;

public class EnumTest {

	@Getter
	static enum EAa{
		A(1), B(2);
		private	Integer	a;
		private EAa(Integer a ) {
			this.a = a;
		}
	}
	
	@Test
	public void test1() {
		EAa a = EAa.valueOf("A");
		assertThat(a.getA()).isEqualTo(1);
	}
	
}
