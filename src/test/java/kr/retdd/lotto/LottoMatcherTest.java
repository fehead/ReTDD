package kr.retdd.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class LottoMatcherTest {
	@Test
	public void 매치_테스트1() {
		Lotto l1 = Lotto.of(1,2,3,4,5,6);
		l1.setBonusNumber(LottoNumber.of(7));
		Lotto l2 = Lotto.of(1,2,3,4,5,6);
		
		LottoRank r1 = LottoMatcher.match(l1, l2);
		assertThat(r1).isEqualTo(LottoRank.RANK_1);
	}
	
	@Test
	public void 매치_테스트2() {
		Lotto l1 = Lotto.of(1,2,3,4,5,6);
		l1.setBonusNumber(LottoNumber.of(7));
		Lotto l2 = Lotto.of(1,2,3,4,5,7);
		
		LottoRank r1 = LottoMatcher.match(l1, l2);
		assertThat(r1).isEqualTo(LottoRank.RANK_2);
	}
	
	@Test
	public void 매치_테스트3() {
		Lotto l1 = Lotto.of(1,2,3,4,5,6);
		l1.setBonusNumber(LottoNumber.of(7));
		Lotto l2 = Lotto.of(1,2,3,4,5,8);
		
		LottoRank r1 = LottoMatcher.match(l1, l2);
		assertThat(r1).isEqualTo(LottoRank.RANK_3);
	}
	
	@Test
	public void 매치_테스트4() {
		Lotto l1 = Lotto.of(1,2,3,4,5,6);
		l1.setBonusNumber(LottoNumber.of(7));
		Lotto l2 = Lotto.of(1,2,3,4,7,8);
		
		LottoRank r1 = LottoMatcher.match(l1, l2);
		assertThat(r1).isEqualTo(LottoRank.RANK_4);
	}

	@Test
	public void 매치_테스트5() {
		Lotto l1 = Lotto.of(1,2,3,4,5,6);
		l1.setBonusNumber(LottoNumber.of(7));
		Lotto l2 = Lotto.of(1,2,3,7,8,9);
		
		LottoRank r1 = LottoMatcher.match(l1, l2);
		assertThat(r1).isEqualTo(LottoRank.RANK_5);
	}

	@Test
	public void 매치_테스트_ETC() {
		Lotto l1 = Lotto.of(1,2,3,4,5,6);
		l1.setBonusNumber(LottoNumber.of(7));
		Lotto l2 = Lotto.of(1,2,7,8,9,10);
		
		LottoRank r1 = LottoMatcher.match(l1, l2);
		assertThat(r1).isEqualTo(LottoRank.RANK_ETC);
	}

}
