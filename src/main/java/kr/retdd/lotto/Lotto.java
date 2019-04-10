package kr.retdd.lotto;

import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Lotto {
	public	static final int	LOTTO_SIZE = 6;
	private	Set<LottoNumber> lottoNumbers;

	private Lotto(Set<LottoNumber> numberSet) {
		this.lottoNumbers = numberSet;
	}

	public static Lotto of(Set<Integer> lottoNumbers) {
		Set<LottoNumber> numberSet = 
				lottoNumbers.stream()
					.map(LottoNumber::of)
					.collect(Collectors.toCollection(TreeSet::new));
		return new Lotto(numberSet);
	}

	public void printNumbers() {
		System.out.print("LottoNumber: ");
		lottoNumbers.stream()
			.map(l -> l.toString() + " ")
			.forEach(System.out::print);

		// System.out.print("Bonus: " + this.bonusNumber);
		System.out.println("");
	}
	
	@Override
	public String toString() {
		StringJoiner	sj = new StringJoiner(",", "[", "]");
		lottoNumbers.stream()
			.forEach(l -> sj.add(l.toString()));
		
			// .map(l -> sj.add(l.toString()))
		return sj.toString();
			
	}
}
