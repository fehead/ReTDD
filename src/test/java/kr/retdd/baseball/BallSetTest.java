package kr.retdd.baseball;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.analysis.solvers.IllinoisSolver;
import org.junit.Before;
import org.junit.Test;

public class BallSetTest {
	BallSet	ballSet;
	
	@Before
	public void 볼셋팅() {
		ballSet = BallSet.valueOf("1,2,3");
	}
	
	@Test
	public void 볼테스트() {
		BallSet.valueOf("1,2,3");
	}

	@Test(expected=IllegalArgumentException.class)
	public void 볼초과테스트() {
		BallSet.valueOf("1,2,3,4");
	}

	@Test(expected=IllegalArgumentException.class)
	public void 볼부족테스트() {
		BallSet.valueOf("1,2");
	}
}
