package kr.retdd.basic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ELangTest {
	@Test
	public void enumTest1() {
		assertThat(ELang.valueOf("kr") == ELang.kr).isTrue();
		assertThat(ELang.valueOf("jp") == ELang.jp).isTrue();
		assertThat(ELang.valueOf("en") == ELang.en).isTrue();
		assertThat(ELang.valueOf("cn") == ELang.cn).isTrue();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void enumTest2() {
		assertThat(ELang.valueOf("KR") == ELang.kr).isTrue();
	}
	
	@Test
	public void enumTest3() {
		assertThat(ELang.kr.toString()).isEqualTo("kr");
		assertThat(ELang.jp.toString()).isEqualTo("jp");
		assertThat(ELang.en.toString()).isEqualTo("en");
		assertThat(ELang.cn.toString()).isEqualTo("cn");
	}
}
