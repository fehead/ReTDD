package kr.retdd.lotto;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

public class Lotto {
	public	static final int	LOTTO_SIZE = 6;
	
	@Getter
	private	Set<LottoNumber> lottoNumbers;	

	protected Lotto(Set<LottoNumber> numberSet) {
		this.lottoNumbers = numberSet;
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
	
	public boolean contain(LottoNumber n) {
		return this.lottoNumbers.contains(n);
	}

	@Override
	public String toString() {
		StringJoiner	sj = new StringJoiner(",", "[", "]");
		lottoNumbers.stream()
			.forEach(l -> sj.add(l.toString()));
		
			// .map(l -> sj.add(l.toString()))
		return sj.toString();
			
	}

	public int countMatch(Lotto l2) {
		Set<LottoNumber> thisLottoNumber = new TreeSet<>(lottoNumbers);
		thisLottoNumber.retainAll(l2.getLottoNumbers());
		return thisLottoNumber.size();
	}
}
