package kr.retdd.baseball;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class BallTest {

	@Test
	public void 정상적인_테스트() {
		Ball b = Ball.valueOf("0");
		assertThat(b).isNotNull();
		b = Ball.valueOf("1");
		assertThat(b).isNotNull();

		b = Ball.valueOf("8");
		assertThat(b).isNotNull();

		b = Ball.valueOf("9");
		assertThat(b).isNotNull();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void 범위를_넘어가는_테스트_1() {
		Ball.valueOf("-1");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void 범위를_넘어가는_테스트_2() {
		Ball.valueOf("10");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void 범위를_넘어가는_테스트_3() {
		Ball.valueOf("11");
	}

	@Test(expected=NumberFormatException.class)
	public void 숫자가_아닌_값를_테스트() {
		Ball.valueOf("abc");
	}
	
	@Test
	public void 정렬_테스트() {
		List<Ball>	ballList = new ArrayList<>();
		ballList.add(Ball.valueOf("2"));
		ballList.add(Ball.valueOf("5"));
		ballList.add(Ball.valueOf("3"));
		ballList.add(Ball.valueOf("7"));
		ballList.add(Ball.valueOf("1"));
		ballList.add(Ball.valueOf("9"));
		ballList.add(Ball.valueOf("8"));
		ballList.add(Ball.valueOf("4"));
		ballList.add(Ball.valueOf("6"));
		
		Collections.sort(ballList);
		
		assertThat(ballList.size()).isEqualTo(9);
		assertThat(ballList.get(0)).isEqualTo(Ball.valueOf("1"));
		
		for(Ball i : ballList) {
			System.out.print(i.toString() + ' ');			
		}
		System.out.println();
	}
	
	@Test
	public void equal_test() {
		Ball b1 = Ball.valueOf(1);
		Ball b2 = Ball.valueOf(2);
		Ball o1 = Ball.valueOf(1);
		
		assertThat(b1).isEqualTo(o1);
		assertThat(b1).isNotEqualTo(b2);
	}

}
