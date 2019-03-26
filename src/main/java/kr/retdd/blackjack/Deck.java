package kr.retdd.blackjack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
	private	List<Card>	cards;
	
	private Deck(List<Card> cards) {
		this.cards = cards;
	}

	public static Deck create() {
		List<Card>	cardList = Arrays.stream(Suit.values())
			.map(Deck::createOf)
			.flatMap(cards -> cards.stream())
			.collect(Collectors.toList());
		
		return new Deck(cardList);
	}
	
	private	static List<Card> createOf(Suit suit) {
		return Arrays.stream(Rank.values())
			.map(rank -> Card.of(suit, rank))
			.collect(Collectors.toList());
	}
	
	public String toString() {
		StringBuffer	sb = new StringBuffer();
		cards.stream()
			.forEach(card -> sb.append(card.toString() + "\n"));
		return sb.toString();
	}
}
