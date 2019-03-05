package kr.retdd.lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

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
	
	static public Lotto generateFrom(String numbers) {
		Lotto ret = new Lotto();
		ret.lotteryFrom(numbers);
		return ret;
	}
	
	private void lotteryFrom(String numbers) {
		String [] numberArr = numbers.split(",");
		if(numberArr.length != LOTTO_SIZE)
			throw new IllegalArgumentException("로또번호는 " + LOTTO_SIZE + "개 이여야 합니다.");
		
		lottoNumbers = new TreeSet<>();
		for(String n : numberArr)
			addLottoNumber(LottoNumber.valueOf(n));
			
	}

	private void lottery() {
		initCandidateNumbers();
		pickNumbers();
	}

	private void pickNumbers() {
		lottoNumbers = new TreeSet<>();
		for(int i = 0 ; i < LOTTO_SIZE ; ++i)
			addLottoNumber(pickNumber());
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
		for(LottoNumber l : lottoNumbers)
			System.out.print(l + " ");
		
		System.out.print("Bonus: " + this.bonusNumber);
		System.out.println("");
	}
	
	private void addLottoNumber(LottoNumber newNumber) {
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

	public Integer lookAt(Set<LottoNumber> numbers) {
		Set<LottoNumber>	lottoNumberSet = new TreeSet<>(getLottoNumbers());		
		lottoNumberSet.retainAll(numbers);
		
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
