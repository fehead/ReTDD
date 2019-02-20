package kr.retdd.lotto;
import static org.assertj.core.api.Assertions.assertThat;

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
		List<Integer>  lottoNumbers = lotto.pickNumbers();
		assertThat(lottoNumbers).isNotNull();
		assertThat(lottoNumbers.size()).isEqualTo(7);
		for(Integer l : lottoNumbers)
			System.out.print(l + " ");
	}

	@Test
	public void test로또_중복_없는지_체크() {
		Lotto	lotto = new Lotto();
		List<Integer>  lottoNumbers = lotto.pickNumbers();
		Set<Integer>	numberSet = new HashSet<>(lottoNumbers);
		assertThat(numberSet.size()).isEqualTo(7);
	}

}
