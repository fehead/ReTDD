package kr.retdd.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lotto {

	public List<Integer> makeLotto() {
		List<Integer> numbers = new ArrayList<>();
		for(int i = 0 ; i < 37 ; ++i)
			numbers.add(i);
		
		Random generator = new Random();       
		List<Integer> ret = new ArrayList<Integer>();
		for(int i = 0 ; i < 7 ; ++i)
			ret.add(numbers.remove(generator.nextInt(numbers.size())));
		return ret;
	}

}
