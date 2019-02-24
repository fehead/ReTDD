package kr.retdd.lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Lotto {
	public static final int	MAX_LOTTO_NUMBER = 45;
	public	static final int	WIN_LOTTO_SIZE = 6;
	
	private	static final int	MIN_LOTTO_NUMBER = 1;

	private	Random generator = new Random();
	private	List<Integer> candiNumbers;
	private	List<Integer> lottoNumbers;
	private	Integer			bonusNumber;

	public Lotto() {
		initCandidateNumbers();
	}

	public void pickNumbers() {
		lottoNumbers = new ArrayList<Integer>();
		for(int i = 0 ; i < WIN_LOTTO_SIZE ; ++i)
			addLottoNumber(pickNumber());
		setBonusNumber(pickNumber());
	}

	public List<Integer> getLottoNumbers() {
		return lottoNumbers;
	}

	public Integer getBonusNumber() {
		return bonusNumber;
	}
	
	public void printNumbers() {
		System.out.print("LottoNumber: ");
		for(Integer l : lottoNumbers)
			System.out.print(l + " ");
		
		System.out.print("Bonus: " + this.bonusNumber);
		System.out.println("");
	}
	
	private void addLottoNumber(Integer newNumber) {
		for(int i = 0 ; i < getLottoNumbers().size() ; ++i) {
			Integer l = getLottoNumbers().get(i);
			if(newNumber < l) {
				getLottoNumbers().add(i, newNumber);
				return;
			}
		}
		getLottoNumbers().add(newNumber);
	}
	
	private void initCandidateNumbers() {
		candiNumbers = new ArrayList<>();
		for(int i = MIN_LOTTO_NUMBER ; i <= MAX_LOTTO_NUMBER ; ++i)
			candiNumbers.add(i);
	}
	
	private int getRandomIndex(int boundrayNum) {		
		return generator.nextInt(boundrayNum);
	}
	
	private int pickNumber() {
		return candiNumbers.remove(getRandomIndex(candiNumbers.size()));
	}

	private void setBonusNumber(Integer bonusNumber) {
		this.bonusNumber = bonusNumber;
	}

	public Integer lottery(List<Integer> numbers) {
		Set<Integer>	lottoNumberSet = new HashSet<>(getLottoNumbers());
		Set<Integer>	numbersSet = new HashSet<>(numbers);
		
		lottoNumberSet.retainAll(numbersSet);
		
		int rightCnt = lottoNumberSet.size();
		
		switch(rightCnt) {
		case 3:
			return 5;
		case 4:
			return 4;
		case 5:
			if(numbers.stream().anyMatch(l -> l == getBonusNumber()))
				return 2;
			return 3;
		case 6:
			return 1;
		default:
			return 0;
		}

	}

}
