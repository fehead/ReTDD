package kr.retdd.lotto;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class LottoProvider {
	private	Lotto	lotto; 	
	LottoNumber	bonusNumber;
	
	protected LottoProvider(Lotto lotto, LottoNumber bonusNumber) {
		this.lotto = lotto;
		this.bonusNumber= bonusNumber;
	}

	public static LottoProvider of(Lotto lotto, LottoNumber	bonusNumber) {
		if(lotto.contain(bonusNumber)) {
			throw new IllegalArgumentException("중복된 번호는 보너스 번호로 설정할수 없습니다.");
		}
		return new LottoProvider(lotto, bonusNumber);
	}
	
	public void setBonusNumber(LottoNumber b) {
		if(lotto.contain(b)) {
			throw new IllegalArgumentException("중복된 번호는 보너스 번호로 설정할수 없습니다.");
		}
		this.bonusNumber = b;
	}

	public void setRandomBonusNumber() {
		LottoNumber	bonusNumber = null;
		while(true) {
			bonusNumber = LottoNumber.ofRandom();
			if(!lotto.contain(bonusNumber)) {
				setBonusNumber(bonusNumber);
				break;
			}
		}
	}
	
	public LottoRank match(Lotto l2) {
		int matchCount = countMatch(l2);
		boolean matchBonus = matchBounus(l2);
		return LottoRank.of(matchCount, matchBonus);

	}
	
	private int countMatch(Lotto l2) {
		return lotto.countMatch(l2);
	}

	private boolean matchBounus(Lotto l2) {
		return l2.getLottoNumbers().contains(bonusNumber);
	}
	
	@Override
	public String toString() {
		return super.toString() + " b:" + bonusNumber.toString();
	}
}
