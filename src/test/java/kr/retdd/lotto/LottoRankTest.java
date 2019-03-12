package kr.retdd.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class LottoRankTest {

	@Test
	public void RANK1_TEST() {
		LottoRank l  = LottoRank.from(6, false);
		assertThat(l).isEqualTo(LottoRank.RANK_1);
	}

	@Test
	public void RANK2_TEST() {
		LottoRank l  = LottoRank.from(5, true);
		assertThat(l).isEqualTo(LottoRank.RANK_2);
	}
	
	@Test
	public void RANK3_TEST() {
		LottoRank l  = LottoRank.from(5, false);
		assertThat(l).isEqualTo(LottoRank.RANK_3);
	}
	
	@Test
	public void RANK4_TEST() {
		LottoRank l  = LottoRank.from(4, false);
		assertThat(l).isEqualTo(LottoRank.RANK_4);
	}
	@Test
	public void RANK5_TEST() {
		LottoRank l  = LottoRank.from(3, false);
		assertThat(l).isEqualTo(LottoRank.RANK_5);
	}
	
	@Test
	public void RANKETC_TEST() {
		assertThat(LottoRank.from(2, false)).isEqualTo(LottoRank.RANK_ETC);
		assertThat(LottoRank.from(1, false)).isEqualTo(LottoRank.RANK_ETC);
	}
	
}
