package kr.retdd.baseball;

import org.junit.Before;
import org.junit.Test;

public class BallSetTest {
	BallSet	ballSet;
	
	@Before
	public void 볼셋팅() {
		ballSet = BallSet.valueOf("123");
	}
	
	@Test
	public void 볼테스트() {
		BallSet.valueOf("123");
	}

	@Test(expected=IllegalArgumentException.class)
	public void 볼초과테스트() {
		BallSet.valueOf("1234");
	}

	@Test(expected=IllegalArgumentException.class)
	public void 볼부족테스트() {
		BallSet.valueOf("12");
	}
}
