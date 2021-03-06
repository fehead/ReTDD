package kr.retdd.lotto;

import java.util.Random;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class LottoNumber implements Comparable<LottoNumber>{
	public	static final int	MIN_NUMBER = 1;
	public	static final int	MAX_NUMBER = 45;

	final private	int	number;

	private	LottoNumber(int num) {
		number = num;
	}
	
	static public LottoNumber of(String numberString) {
		int number = Integer.parseInt(numberString);
		return of(number);
	}

	static public LottoNumber of(int number) {
		if(number < MIN_NUMBER || MAX_NUMBER < number)
			throw new IllegalArgumentException("로또번호는 " + MIN_NUMBER + "과 " + MAX_NUMBER + "사이 값 이어야 합니다.");

		return new LottoNumber(number);
	}

	static public LottoNumber ofRandom() {
		int number = new Random().nextInt(MAX_NUMBER - MIN_NUMBER) + MIN_NUMBER;
		return of(number);
	}
	
	@Override
	public String toString() {
		return String.valueOf(number);
	}

	@Override
	public int compareTo(LottoNumber o) {
		return this.number - o.number;
	}
}
