package kr.retdd.lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Lotto {
	public	static final int	MAX_LOTTO_NUMBER = 45;
	public	static final int	WIN_LOTTO_SIZE = 6;
	
	private	static final int	MIN_LOTTO_NUMBER = 1;

	private	Random generator = new Random();
	private	List<LottoNumber> candiNumbers;
	private	List<LottoNumber> lottoNumbers;
	private	LottoNumber			bonusNumber;

	public Lotto() {
		initCandidateNumbers();
	}

	public void pickNumbers() {
		lottoNumbers = new ArrayList<>();
		for(int i = 0 ; i < WIN_LOTTO_SIZE ; ++i)
			addLottoNumber(pickNumber());
		setBonusNumber(pickNumber());
	}

	public List<LottoNumber> getLottoNumbers() {
		return lottoNumbers;
	}

	public LottoNumber getBonusNumber() {
		return bonusNumber;
	}
	
	public void printNumbers() {
		System.out.print("LottoNumber: ");
		for(LottoNumber l : lottoNumbers)
			System.out.print(l + " ");
		
		System.out.print("Bonus: " + this.bonusNumber);
		System.out.println("");
	}
	
	private void addLottoNumber(LottoNumber newNumber) {
		for(int i = 0 ; i < getLottoNumbers().size() ; ++i) {
			LottoNumber l = getLottoNumbers().get(i);
			if(newNumber.compareTo(l) < 0) {
				getLottoNumbers().add(i, newNumber);
				return;
			}
		}
		getLottoNumbers().add(newNumber);
	}
	
	private void initCandidateNumbers() {
		candiNumbers = new ArrayList<>();
		for(int i = LottoNumber.MIN_NUMBER ; i <= LottoNumber.MAX_NUMBER ; ++i)
			candiNumbers.add(LottoNumber.valueOf(i));
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

	public Integer lottery(List<LottoNumber> numbers) {
		Set<LottoNumber>	lottoNumberSet = new HashSet<>(getLottoNumbers());
		Set<LottoNumber>	numbersSet = new HashSet<>(numbers);
		
		lottoNumberSet.retainAll(numbersSet);
		
		int rightCnt = lottoNumberSet.size();
		
		switch(rightCnt) {
		case 3:
			return 5;
		case 4:
			return 4;
		case 5:
			if(numbers.stream().anyMatch(l -> l.equals(getBonusNumber())))
				return 2;
			return 3;
		case 6:
			return 1;
		default:
			return 0;
		}

	}

}
