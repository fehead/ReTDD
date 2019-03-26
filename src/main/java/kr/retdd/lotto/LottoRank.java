package kr.retdd.lotto;

public enum LottoRank {
	RANK_1(6, false),
	RANK_2(5, true),
	RANK_3(5, false),
	RANK_4(4, false),
	RANK_5(3, false),
	RANK_ETC(0, false);
	
	private	int		matchCount;
	private	boolean	matchBonus;
	
	LottoRank(int matchCount, boolean matchBonus) {
		this.matchCount = matchCount;
		this.matchBonus = matchBonus;
	}
	
	public static LottoRank from(int matchCount, boolean matchBonus) {
		for(LottoRank l : values()) {
			if(l.getMatchCount() == matchCount) {
				if(l == RANK_2 && !matchBonus)
					continue;
				return l;
			}
		}
		return RANK_ETC;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public boolean isMatchBonus() {
		return matchBonus;
	}
}
