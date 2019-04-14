package kr.retdd.lotto;

import java.util.Arrays;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Lotto {
	public	static final int	LOTTO_SIZE = 6;
	private	Set<LottoNumber> lottoNumbers;
	LottoNumber	bonusNumber;

	private Lotto(Set<LottoNumber> numberSet) {
		this.lottoNumbers = numberSet;
	}

	private Lotto(Set<LottoNumber> numberSet, LottoNumber bonusNumber) {
		this.lottoNumbers = numberSet;
		this.bonusNumber= bonusNumber;
	}
	
	public static Lotto ofRandom() {
		Set<LottoNumber> numberSet = new TreeSet<>();
		while(numberSet.size() < Lotto.LOTTO_SIZE) {
			numberSet.add(LottoNumber.ofRandom());
		}
		
		return new Lotto(numberSet);
	}
	
	public static Lotto of(Integer... numbers) {
		if(numbers.length != Lotto.LOTTO_SIZE) {
			throw new IllegalArgumentException("로또번호는 " + Lotto.LOTTO_SIZE + "개 이여야 합니다.");
		}

		Set<Integer>	set = new TreeSet<>(Arrays.asList(numbers));
		if(set.size() != Lotto.LOTTO_SIZE) {
			throw new IllegalArgumentException("중복된 로또번호가 있습니다." + numbers.toString());			
		}
		
		return Lotto.of(set);
	}
	
	private static Lotto of(Set<Integer> lottoNumbers) {
		Set<LottoNumber> numberSet = 
				lottoNumbers.stream()
					.map(LottoNumber::of)
					.collect(Collectors.toCollection(TreeSet::new));
		return new Lotto(numberSet);
	}

	@Override
	public String toString() {
		StringJoiner	sj = new StringJoiner(",", "[", "]");
		lottoNumbers.stream()
			.forEach(l -> sj.add(l.toString()));
		
		if(bonusNumber != null) {
			sj.add("b:" + bonusNumber.toString());
		}
			// .map(l -> sj.add(l.toString()))
		return sj.toString();
			
	}

	public void setBonusNumber(LottoNumber b) {
		if(lottoNumbers.contains(b)) {
			throw new IllegalArgumentException("중복된 번호는 보너스 번호로 설정할수 없습니다.");
		}
		this.bonusNumber = b;
	}

	public LottoNumber getBonusNumber() {
		return this.bonusNumber;
	}

	public void setRandomBonusNumber() {
		LottoNumber	bonusNumber = null;
		while(true) {
			bonusNumber = LottoNumber.ofRandom();
			if(!lottoNumbers.contains(bonusNumber)) {
				this.bonusNumber = bonusNumber;
				break;
			}
		}
	}
}
