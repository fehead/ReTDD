package kr.retdd.blackjack;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeckTest {

	@Test
	public void test() {
		Deck deck = Deck.create();
		System.out.println(deck.toString());
	}

}
