package kr.retdd.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lotto {
	static final int	MAX_LOTTO_COUNT = 37;
	static final int	WIN_LOTTO_SIZE = 7;
	
	public List<Integer> makeLotto() {
		List<Integer> numbers = getCandidateNumbers();
		return pickNumbers(numbers);
	}
	
	private List<Integer>	getCandidateNumbers() {
		List<Integer> ret = new ArrayList<>();
		for(int i = 0 ; i < MAX_LOTTO_COUNT ; ++i)
			ret.add(i);
		return ret;
	}
	
	private List<Integer> pickNumbers(List<Integer> candiNumbers) {
		List<Integer> ret = new ArrayList<Integer>();
		for(int i = 0 ; i < WIN_LOTTO_SIZE ; ++i)
			ret.add(candiNumbers.remove(getRandomIndex(candiNumbers.size())));
		return ret;
	}
	
	private int getRandomIndex(int boundrayNum) {
		Random generator = new Random();
		return generator.nextInt(boundrayNum);
	}

}
