package kr.retdd.lotto;

public enum LottoRank {
	RANK1(6, false),
	RANK2(5, true),
	RANK3(5, false),
	RANK4(4, false),
	RANK5(3, false);	
	
	private	int		rightCnt;
	private	boolean	eqBonusNum;
	
	LottoRank(int rightCnt, boolean eqBonusNum) {
		this.rightCnt = rightCnt;
		this.eqBonusNum = eqBonusNum;
	}

}
