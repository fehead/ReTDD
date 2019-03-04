package kr.retdd.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LottoNumberTest {
	
	@Before
	public void setUp() throws Exception {		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=IllegalArgumentException.class)
	public void 작은수_예외() {
		LottoNumber.valueOf(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void 커다란수_예외() {
		LottoNumber.valueOf(46);
	}

	@Test
	public void 알맞은_값() {
		for(int i = LottoNumber.MIN_NUMBER ; i <= LottoNumber.MAX_NUMBER ; ++i) {
			assertThat(LottoNumber.valueOf(i)).isNotNull();
		}
	}
	
	@Test(expected=NumberFormatException.class)
	public void 숫자가_아닌수() {
		LottoNumber.valueOf("A");		
	}
}
