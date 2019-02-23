package kr.retdd.lotto;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LottoTest {
	
	@Test
	public void testRandom() throws Exception {
		Random generator = new Random();
		for(int i = 0 ; i < 100 ; ++i) {
			Integer ret = generator.nextInt(2);
			assertThat(ret).isLessThan(2);
		}
	}
	
	@Test
	public void 로또_6개나오게_하기() {
		Lotto	lotto = new Lotto();
		lotto.pickNumbers();
		List<Integer>  lottoNumbers = lotto.getLottoNumbers();
		assertThat(lottoNumbers).isNotNull();
		assertThat(lottoNumbers.size()).isEqualTo(6);
		lotto.printNumbers();
	}

	@Test
	public void test로또_중복_없는지_체크() {
		Lotto	lotto = new Lotto();
		lotto.pickNumbers();
		List<Integer>  lottoNumbers = lotto.getLottoNumbers();
		Set<Integer>	numberSet = new HashSet<>(lottoNumbers);
		numberSet.add(lotto.getBonusNumber());
		assertThat(numberSet.size()).isEqualTo(7);
		lotto.printNumbers();
	}
	
	
	@Test
	public void 정렬_검사() {
		Lotto	lotto = new Lotto();
		lotto.pickNumbers();
		List<Integer>  lottoNumbers = lotto.getLottoNumbers();
		assertThat(lottoNumbers).isNotNull();
		assertThat(lottoNumbers.size()).isEqualTo(6);
		Integer	before = 0;
				
		for(Integer l : lottoNumbers) {
			assertThat(l).isGreaterThan(before);
			before = l;
		}
		
		lotto.printNumbers();
	}
	
	
	@Test
	public void 로또_등수_검사() {
		Lotto	lotto = new Lotto();
		lotto.pickNumbers();
		List<Integer>  lottoNumbers = lotto.getLottoNumbers();
		assertThat(lottoNumbers).isNotNull();
		assertThat(lottoNumbers.size()).isEqualTo(6);
		lotto.printNumbers();
		
		List<Integer> lottoR0 = 맞는것_가져오기(lottoNumbers, 0);
		Integer r0 = lotto.lottery(lottoR0);
		printNumbers("0개", lottoR0);
		assertThat(r0).isEqualTo(0);

		List<Integer> lottoR1 = 맞는것_가져오기(lottoNumbers, 1);
		Integer r1 = lotto.lottery(lottoR1);
		printNumbers("1개", lottoR1);
		assertThat(r1).isEqualTo(0);

		List<Integer> lottoR2 = 맞는것_가져오기(lottoNumbers, 2);
		Integer r2 = lotto.lottery(lottoR2);
		printNumbers("2개", lottoR2);
		assertThat(r2).isEqualTo(0);

		List<Integer> lottoR3 = 맞는것_가져오기(lottoNumbers, 3);
		Integer r3 = lotto.lottery(lottoR3);
		printNumbers("3개", lottoR3);
		assertThat(r3).isEqualTo(5);

		List<Integer> lottoR4 = 맞는것_가져오기(lottoNumbers, 4);
		Integer r4 = lotto.lottery(lottoR4);
		printNumbers("4개", lottoR4);
		assertThat(r4).isEqualTo(4);

		List<Integer> lottoR5 = 맞는것_가져오기(lottoNumbers, 5);
		Integer r5 = lotto.lottery(lottoR5);
		printNumbers("5개", lottoR5);
		assertThat(r5).isEqualTo(3);

		List<Integer> lottoR6 = 맞는것_가져오기(lottoNumbers, 6);
		Integer r6 = lotto.lottery(lottoR6);
		printNumbers("6개", lottoR6);
		assertThat(r6).isEqualTo(1);

		List<Integer> lottoRBouns = new ArrayList<>(lottoNumbers);
		lottoRBouns.remove(0);
		lottoRBouns.add(lotto.getBonusNumber());
		Integer rBouns = lotto.lottery(lottoRBouns);
		printNumbers("5개 + 보너스", lottoRBouns);
		assertThat(rBouns).isEqualTo(2);
		
	}
	
	
	private List<Integer> 맞는것_가져오기(List<Integer> lottoNumbers, int rightCnt) {
		List<Integer> ret = new ArrayList<Integer>();
		for(int i = 0 ; i < rightCnt ; ++i)
			ret.add(lottoNumbers.get(i));
		
		for(int i = 1 ; i <= Lotto.MAX_LOTTO_NUMBER; ++i) {
			if(ret.size() == Lotto.WIN_LOTTO_SIZE)
				break;
			if(!lottoIn(lottoNumbers, i))
				ret.add(i);
		}
		
		return ret;
	}
	
	private boolean lottoIn(List<Integer> lottoNumbers, int num) {
		return lottoNumbers.stream().anyMatch(l -> l == num);
	}
	
	public void printNumbers(String msg, List<Integer> lottoNumbers) {
		System.out.print(msg + " LottoNumber: ");
		for(Integer l : lottoNumbers)
			System.out.print(l + " ");
		System.out.println("");
	}	

}
