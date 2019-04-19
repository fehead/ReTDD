package kr.retdd.lotto;

import java.util.Set;
import java.util.TreeSet;

public class LottoProvider extends Lotto {
	LottoNumber	bonusNumber;
	
	protected LottoProvider(Set<LottoNumber> numberSet, LottoNumber bonusNumber) {
		super(numberSet);
		this.bonusNumber= bonusNumber;
	}

	public void setBonusNumber(LottoNumber b) {
		if(getLottoNumbers().contains(b)) {
			throw new IllegalArgumentException("중복된 번호는 보너스 번호로 설정할수 없습니다.");
		}
		this.bonusNumber = b;
	}

	public void setRandomBonusNumber() {
		LottoNumber	bonusNumber = null;
		while(true) {
			bonusNumber = LottoNumber.ofRandom();
			if(!getLottoNumbers().contains(bonusNumber)) {
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
		Set<LottoNumber> thisLottoNumber = new TreeSet<>(getLottoNumbers());
		thisLottoNumber.retainAll(l2.getLottoNumbers());
		return thisLottoNumber.size();
	}

	private boolean matchBounus(Lotto l2) {
		return l2.getLottoNumbers().contains(bonusNumber);
	}
	
	@Override
	public String toString() {
		return super.toString() + " b:" + bonusNumber.toString();
	}
}
