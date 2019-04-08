package kr.retdd.lotto;

import java.util.Arrays;
import java.util.Optional;

public enum LottoRank {
	RANK_1(6),
	RANK_2(5, true),
	RANK_3(5),
	RANK_4(4),
	RANK_5(3),
	RANK_ETC(0);

	private	int		matchCount;
	private	Optional<Boolean> matchBonus;

	private LottoRank(int matchCount) {
		this(matchCount, null);
	}
	
	private LottoRank(int matchCount, Boolean matchBonus) {
		this.matchCount = matchCount;
		this.matchBonus = Optional.ofNullable(matchBonus);
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

		return this.matchBonus
				.map(m -> m.equals(matchBonus))
				.orElse(true);
		
		/*
		if(this.equals(RANK_2) && this.matchBonus != matchBonus)
			return false;
		if(this.equals(RANK_3) && this.matchBonus != matchBonus)
			return false;
		return true;
		*/
	}
}
