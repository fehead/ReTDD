package kr.retdd.blackjack;

import static org.junit.Assert.*;

import org.assertj.core.util.Arrays;
import org.junit.Test;

public class SuitTest {

	@Test
	public void test() {
		Arrays.asList(Suit.values()).stream()
			.forEach(suit -> {
				System.out.println(suit.toString());
			});
	}

}
