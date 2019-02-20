package kr.retdd.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lotto {
	static final int	MIN_LOTTO_NUMBER = 1;
	static final int	MAX_LOTTO_NUMBER = 45;
	
	static final int	WIN_LOTTO_SIZE = 7;

	private	Random generator = new Random();
	private	List<Integer> candiNumbers;

	public Lotto() {
		initCandidateNumbers();
	}

	public List<Integer> pickNumbers() {
		List<Integer> ret = new ArrayList<Integer>();
		for(int i = 0 ; i < WIN_LOTTO_SIZE ; ++i)
			ret.add(pickNumber());
		return ret;
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

}
