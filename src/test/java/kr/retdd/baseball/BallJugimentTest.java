package kr.retdd.baseball;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class BallJugimentTest {
	BallSet	ballSet;
	
	@Before
	public void 볼셋팅() {
		ballSet = BallSet.valueOf("123");
	}
	
	@Test public void 판정_테스트1() {
		BallSet s3 = BallSet.valueOf("123");
		BallJugiment bj = BallJugiment.jugiment(ballSet, s3);
		assertThat(bj.getStrike()).isEqualTo(3);
	}
	
	@Test public void 판정_테스트2() {
		BallSet s3 = BallSet.valueOf("231");
		BallJugiment bj = BallJugiment.jugiment(ballSet, s3);
		assertThat(bj.getStrike()).isEqualTo(0);
		assertThat(bj.getBall()).isEqualTo(3);
		assertThat(bj.getOut()).isEqualTo(0);
	}
	
	@Test public void 판정_테스트3() {
		BallSet s3 = BallSet.valueOf("234");
		BallJugiment bj = BallJugiment.jugiment(ballSet, s3);
		assertThat(bj.getStrike()).isEqualTo(0);
		assertThat(bj.getBall()).isEqualTo(2);
		assertThat(bj.getOut()).isEqualTo(1);
	}
	
	@Test public void 판정_테스트4() {
		BallSet s3 = BallSet.valueOf("412");
		BallJugiment bj = BallJugiment.jugiment(ballSet, s3);
		assertThat(bj.getStrike()).isEqualTo(0);
		assertThat(bj.getBall()).isEqualTo(2);
		assertThat(bj.getOut()).isEqualTo(1);
	}
	
	@Test public void 판정_테스트5() {
		BallSet s3 = BallSet.valueOf("142");
		BallJugiment bj = BallJugiment.jugiment(ballSet, s3);
		assertThat(bj.getStrike()).isEqualTo(1);
		assertThat(bj.getBall()).isEqualTo(1);
		assertThat(bj.getOut()).isEqualTo(1);
	}
	
	@Test public void 판정_테스트6() {
		BallSet s3 = BallSet.valueOf("456");
		BallJugiment bj = BallJugiment.jugiment(ballSet, s3);
		assertThat(bj.getStrike()).isEqualTo(0);
		assertThat(bj.getBall()).isEqualTo(0);
		assertThat(bj.getOut()).isEqualTo(3);
	}
}
