package kr.retdd.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import lombok.extern.java.Log;

@Log
public class LottoGeneratorTest {

	@Test
	public void 로또_자동_생성() {
		Lotto lotto = LottoGenerator.of();
		assertThat(lotto).isNotNull();
		log.info(lotto.toString());
	}
	
	@Test
	public void 로또_수동_생성() {
		Lotto lotto = LottoGenerator.of(1,2,3,4,5,6);
		assertThat(lotto).isNotNull();
		log.info(lotto.toString());
	}

	@Test(expected=IllegalArgumentException.class)
	public void 로또_수동_생성_개수많음_예외() {
		Lotto lotto = LottoGenerator.of(1,2,3,4,5,6,7);
		log.info(lotto.toString());
	}

	@Test(expected=IllegalArgumentException.class)
	public void 로또_수동_생성_개수적음_예외() {
		LottoGenerator.of(1,2,3,4,5);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void 로또_수동_생성_잘못된숫자_예외() {
		LottoGenerator.of(1,2,3,4,5,LottoNumber.MAX_NUMBER+1);
	}
	
}
