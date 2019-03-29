package kr.retdd.blackjack;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Card {
	private	Suit	suit;
	private	Rank	rank;

	private	Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public static Card of(Suit suit, Rank rank) {
		return new Card(suit, rank);
	}
	
	public String	toString() {
		return String.format("%s %s", suit.toString(), rank.toString());
	}
	
	public int firstScore() {
		return rank.getFirstValue();
	}
	
	public int secondScore() {
		return rank.getSecondValue();
	}
}
