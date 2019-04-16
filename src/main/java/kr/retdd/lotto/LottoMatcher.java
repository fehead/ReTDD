package kr.retdd.lotto;

public class LottoMatcher {

	public static LottoRank match(Lotto l1, Lotto l2) {
		int matchCount = l1.countMatch(l2);
		boolean matchBonus = l1.matchBounus(l2);
		return LottoRank.of(matchCount, matchBonus);
	}

}
