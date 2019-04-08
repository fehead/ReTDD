package kr.retdd.lotto;

import java.util.Arrays;

public enum LottoRank {
	RANK_1(6, false),
	RANK_2(5, true),
	RANK_3(5, false),
	RANK_4(4, false),
	RANK_5(3, false),
	RANK_ETC(0, false);

	private	int		matchCount;
	private	boolean	matchBonus;

	private LottoRank(int matchCount, boolean matchBonus) {
		this.matchCount = matchCount;
		this.matchBonus = matchBonus;
	}

	public static LottoRank of(int matchCount, boolean matchBonus) {
		return Arrays.stream(values())
			.filter(lottoRank -> lottoRank.isMatch(matchCount, matchBonus))
			.findFirst()
			.orElse(RANK_ETC);
//
//		for(LottoRank l : values()) {
//			if(l.isMatch(matchCount, matchBonus))
//				return l;
//		}
//		return RANK_ETC;
	}

	private boolean isMatch(int matchCount, boolean matchBonus) {
		if(this.matchCount != matchCount)
			return false;

		if(this.equals(RANK_2) && this.matchBonus != matchBonus)
			return false;
		if(this.equals(RANK_3) && this.matchBonus != matchBonus)
			return false;
		return true;
	}
}
