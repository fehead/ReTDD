package kr.retdd.lotto;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class LottoGenerator {
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
	
	public static Lotto of() {
		Random random = new Random();
		Set<Integer> numberSet = new TreeSet<>();
		while(numberSet.size() < Lotto.LOTTO_SIZE) {
			numberSet.add(random.nextInt(LottoNumber.MAX_NUMBER - LottoNumber.MIN_NUMBER) + LottoNumber.MIN_NUMBER);
		}
		return Lotto.of(numberSet);
	}
}
