package kr.retdd.lotto;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.common.collect.Lists;


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
		List<LottoNumber>  lottoNumbers = lotto.getLottoNumbers();
		assertThat(lottoNumbers).isNotNull();
		assertThat(lottoNumbers.size()).isEqualTo(6);
		lotto.printNumbers();
	}

	@Test
	public void test로또_중복_없는지_체크() {
		Lotto	lotto = new Lotto();
		lotto.pickNumbers();
		List<LottoNumber>  lottoNumbers = lotto.getLottoNumbers();
		Set<LottoNumber>	numberSet = new HashSet<>(lottoNumbers);
		numberSet.add(lotto.getBonusNumber());
		assertThat(numberSet.size()).isEqualTo(7);
		lotto.printNumbers();
	}
	
	
	@Test
	public void 정렬_검사() {
		Lotto	lotto = new Lotto();
		lotto.pickNumbers();
		List<LottoNumber>  lottoNumbers = lotto.getLottoNumbers();
		assertThat(lottoNumbers).isNotNull();
		assertThat(lottoNumbers.size()).isEqualTo(6);
		LottoNumber	before = LottoNumber.valueOf(1);
				
		for(LottoNumber l : lottoNumbers) {
			assertThat(l).isGreaterThanOrEqualTo(before);
			before = l;
		}
		
		lotto.printNumbers();
	}
	
	
	@Test
	public void 로또_등수_검사() {
		Lotto	lotto = new Lotto();
		lotto.pickNumbers();
		List<LottoNumber>  lottoNumbers = lotto.getLottoNumbers();
		assertThat(lottoNumbers).isNotNull();
		assertThat(lottoNumbers.size()).isEqualTo(6);
		lotto.printNumbers();
		
		List<LottoNumber> lottoR0 = 맞는것_가져오기(lottoNumbers, 0);
		Integer r0 = lotto.lottery(lottoR0);
		printNumbers("0개", lottoR0);
		assertThat(r0).isEqualTo(0);

		List<LottoNumber> lottoR1 = 맞는것_가져오기(lottoNumbers, 1);
		Integer r1 = lotto.lottery(lottoR1);
		printNumbers("1개", lottoR1);
		assertThat(r1).isEqualTo(0);

		List<LottoNumber> lottoR2 = 맞는것_가져오기(lottoNumbers, 2);
		Integer r2 = lotto.lottery(lottoR2);
		printNumbers("2개", lottoR2);
		assertThat(r2).isEqualTo(0);

		List<LottoNumber> lottoR3 = 맞는것_가져오기(lottoNumbers, 3);
		Integer r3 = lotto.lottery(lottoR3);
		printNumbers("3개", lottoR3);
		assertThat(r3).isEqualTo(5);

		List<LottoNumber> lottoR4 = 맞는것_가져오기(lottoNumbers, 4);
		Integer r4 = lotto.lottery(lottoR4);
		printNumbers("4개", lottoR4);
		assertThat(r4).isEqualTo(4);

		List<LottoNumber> lottoR5 = 맞는것_가져오기(lottoNumbers, 5);
		Integer r5 = lotto.lottery(lottoR5);
		printNumbers("5개", lottoR5);
		assertThat(r5).isEqualTo(3);

		List<LottoNumber> lottoR6 = 맞는것_가져오기(lottoNumbers, 6);
		Integer r6 = lotto.lottery(lottoR6);
		printNumbers("6개", lottoR6);
		assertThat(r6).isEqualTo(1);

		List<LottoNumber> lottoRBouns = new ArrayList<>(lottoNumbers);
		lottoRBouns.remove(0);
		lottoRBouns.add(lotto.getBonusNumber());
		Integer rBouns = lotto.lottery(lottoRBouns);
		printNumbers("5개 + 보너스", lottoRBouns);
		assertThat(rBouns).isEqualTo(2);
		
	}
	
	@Test
	public void Set_교집합() {
		Set<Integer> l1 = new HashSet<>(Arrays.asList(1, 2, 3));
		Set<Integer> l2 = new HashSet<>(Arrays.asList(3, 4, 5));

		l1.retainAll(l2);
		System.out.println(l1);

		l2.retainAll(l1);
		System.out.println(l2);
		
		assertThat(l1).isNotNull();
		assertThat(l1.size()).isEqualTo(1);
	}
	
	private List<LottoNumber> 맞는것_가져오기(List<LottoNumber> lottoNumbers, int rightCnt) {
		List<LottoNumber> ret = new ArrayList<>();
		for(int i = 0 ; i < rightCnt ; ++i)
			ret.add(lottoNumbers.get(i));
		
		for(int i = 1 ; i <= Lotto.MAX_LOTTO_NUMBER; ++i) {
			if(ret.size() == Lotto.WIN_LOTTO_SIZE)
				break;
			if(!lottoIn(lottoNumbers, LottoNumber.valueOf(i)))
				ret.add(LottoNumber.valueOf(i));
		}
		
		return ret;
	}
	
	private boolean lottoIn(List<LottoNumber> lottoNumbers, LottoNumber num) {
		return lottoNumbers.stream().anyMatch(l -> l.equals(num));
	}
	
	public void printNumbers(String msg, List<LottoNumber> lottoNumbers) {
		System.out.print(msg + " LottoNumber: ");
		for(LottoNumber l : lottoNumbers)
			System.out.print(l + " ");
		System.out.println("");
	}	

}
