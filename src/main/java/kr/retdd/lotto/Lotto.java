package kr.retdd.lotto;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {
	public	static final int	LOTTO_SIZE = 6;

	private	Random generator = new Random();
	private	List<LottoNumber> candiNumbers;
	private	Set<LottoNumber> lottoNumbers;
	private	LottoNumber			bonusNumber;

	private	Lotto() {
	}

	static public Lotto generate() {
		Lotto ret = new Lotto();
		ret.lottery();
		return ret;
	}

	static public Lotto of(Integer... numbers) {
		if(numbers.length != LOTTO_SIZE)
			throw new IllegalArgumentException("로또번호는 " + LOTTO_SIZE + "개 이여야 합니다.");

		Lotto ret = new Lotto();
		ret.from(numbers);
		return ret;
	}

	private void from(Integer [] numberArr) {
		lottoNumbers = Arrays.stream(numberArr)
			.map(LottoNumber::of)
			.collect(Collectors.toCollection(TreeSet::new));
	}
	
	private void lottery() {
		initCandidateNumbers();
		pickNumbers();
	}

	private void pickNumbers() {
		lottoNumbers = IntStream.range(0, LOTTO_SIZE)
			.mapToObj(i -> pickNumber())
			.collect(Collectors.toCollection(TreeSet::new));
		setBonusNumber(pickNumber());
	}

	public Set<LottoNumber> getLottoNumbers() {
		return lottoNumbers;
	}

	public LottoNumber getBonusNumber() {
		return bonusNumber;
	}

	public void printNumbers() {
		System.out.print("LottoNumber: ");
		lottoNumbers.stream()
			.map(l -> l.toString() + " ")
			.forEach(System.out::print);

		System.out.print("Bonus: " + this.bonusNumber);
		System.out.println("");
	}

	private void initCandidateNumbers() {
		candiNumbers = IntStream
				.rangeClosed(LottoNumber.MIN_NUMBER, LottoNumber.MAX_NUMBER)
				.mapToObj(LottoNumber::of)
				.collect(Collectors.toList());
	}

	private int getRandomIndex(int boundrayNum) {
		return generator.nextInt(boundrayNum);
	}

	private LottoNumber pickNumber() {
		return candiNumbers.remove(getRandomIndex(candiNumbers.size()));
	}

	private void setBonusNumber(LottoNumber bonusNumber) {
		this.bonusNumber = bonusNumber;
	}

	public LottoRank lookAt(Set<LottoNumber> numbers) {
		Set<LottoNumber>	lottoNumberSet = new TreeSet<>(getLottoNumbers());
		lottoNumberSet.retainAll(numbers);

		int matchCount = lottoNumberSet.size();
		boolean	matchBonus = numbers.stream().anyMatch(l -> l.equals(getBonusNumber()));
		return LottoRank.of(matchCount, matchBonus);
	}

}
