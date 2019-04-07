package kr.retdd.lotto;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LottoTest {
	
	private	Lotto	lotto;
	
	@Before
	public void setUp() throws Exception {
		lotto = Lotto.generate();
	}
	
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
		Set<LottoNumber>  lottoNumbers = lotto.getLottoNumbers();
		assertThat(lottoNumbers).isNotNull();
		assertThat(lottoNumbers.size()).isEqualTo(6);
		lotto.printNumbers();
	}

	@Test
	public void test로또_중복_없는지_체크() {
		Set<LottoNumber>  lottoNumbers = lotto.getLottoNumbers();
		Set<LottoNumber>	numberSet = new HashSet<>(lottoNumbers);
		numberSet.add(lotto.getBonusNumber());
		assertThat(numberSet.size()).isEqualTo(7);
		lotto.printNumbers();
	}
	
	
	@Test
	public void 정렬_검사() {
		Set<LottoNumber>  lottoNumbers = lotto.getLottoNumbers();
		assertThat(lottoNumbers).isNotNull();
		assertThat(lottoNumbers.size()).isEqualTo(6);
		LottoNumber	before = LottoNumber.of(1);
				
		for(LottoNumber l : lottoNumbers) {
			assertThat(l).isGreaterThanOrEqualTo(before);
			before = l;
		}
		
		lotto.printNumbers();
	}
	
	
	@Test
	public void 로또_등수_검사() {
		Set<LottoNumber>  lottoNumbers = lotto.getLottoNumbers();
		assertThat(lottoNumbers).isNotNull();
		assertThat(lottoNumbers.size()).isEqualTo(6);
		lotto.printNumbers();
		
		Set<LottoNumber> lottoR0 = 맞는것_가져오기(lottoNumbers, 0);
		LottoRank r0 = lotto.lookAt(lottoR0);
		printNumbers("0개", lottoR0);
		assertThat(r0).isEqualTo(LottoRank.RANK_ETC);

		Set<LottoNumber> lottoR1 = 맞는것_가져오기(lottoNumbers, 1);
		LottoRank r1 = lotto.lookAt(lottoR1);
		printNumbers("1개", lottoR1);
		assertThat(r1).isEqualTo(LottoRank.RANK_ETC);

		Set<LottoNumber> lottoR2 = 맞는것_가져오기(lottoNumbers, 2);
		LottoRank r2 = lotto.lookAt(lottoR2);
		printNumbers("2개", lottoR2);
		assertThat(r2).isEqualTo(LottoRank.RANK_ETC);

		Set<LottoNumber> lottoR3 = 맞는것_가져오기(lottoNumbers, 3);
		LottoRank r3 = lotto.lookAt(lottoR3);
		printNumbers("3개", lottoR3);
		assertThat(r3).isEqualTo(LottoRank.RANK_5);

		Set<LottoNumber> lottoR4 = 맞는것_가져오기(lottoNumbers, 4);
		LottoRank r4 = lotto.lookAt(lottoR4);
		printNumbers("4개", lottoR4);
		assertThat(r4).isEqualTo(LottoRank.RANK_4);

		Set<LottoNumber> lottoR5 = 맞는것_가져오기(lottoNumbers, 5);
		LottoRank r5 = lotto.lookAt(lottoR5);
		printNumbers("5개", lottoR5);
		assertThat(r5).isEqualTo(LottoRank.RANK_3);

		Set<LottoNumber> lottoR6 = 맞는것_가져오기(lottoNumbers, 6);
		LottoRank r6 = lotto.lookAt(lottoR6);
		printNumbers("6개", lottoR6);
		assertThat(r6).isEqualTo(LottoRank.RANK_1);

		Set<LottoNumber> lottoRBouns = new TreeSet<>(lottoNumbers);
		LottoNumber l = (LottoNumber)lottoNumbers.toArray()[0];
		lottoRBouns.remove(l);		
		lottoRBouns.add(lotto.getBonusNumber());
		LottoRank rBouns = lotto.lookAt(lottoRBouns);
		printNumbers("5개 + 보너스", lottoRBouns);
		assertThat(rBouns).isEqualTo(LottoRank.RANK_2);
		
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

	@Test
	public void 수동입력_입력() {
		Lotto lotto = Lotto.generateFrom("1,2,3,4,5,6");
		assertThat(lotto.getLottoNumbers().size()).isEqualTo(Lotto.LOTTO_SIZE);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void 수동입력_입력갯수오버예외() {
		Lotto.generateFrom("1,2,3,4,5,6,7,8,9");
	}
	
	private Set<LottoNumber> 맞는것_가져오기(Set<LottoNumber> lottoNumbers, int rightCnt) {
		Set<LottoNumber> ret = lottoNumbers.stream()
				.limit(rightCnt)
				.collect(Collectors.toSet());
		
		for(int i = LottoNumber.MIN_NUMBER ; i <= LottoNumber.MAX_NUMBER; ++i) {
			if(ret.size() == Lotto.LOTTO_SIZE)
				break;
			if(!lottoIn(lottoNumbers, LottoNumber.of(i)))
				ret.add(LottoNumber.of(i));
		}
		
		return ret;
	}
	
	private boolean lottoIn(Set<LottoNumber> lottoNumbers, LottoNumber num) {
		return lottoNumbers.contains(num);
	}
	
	public void printNumbers(String msg, Set<LottoNumber> lottoNumbers) {
		System.out.print(msg + " LottoNumber: ");
		for(LottoNumber l : lottoNumbers)
			System.out.print(l + " ");
		System.out.println("");
	}	
	
	

}
