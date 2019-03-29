package kr.retdd.blackjack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

	public void handTo(Dealer dealer, int cnt) {
		dealer.addCard(cards, cnt);
//		IntStream.range(0, cnt)
//			.forEach(i -> dealer.addCard(cards.remove(0)));
					
	}

	public void handTo(Player player, int cnt) {
		player.addCard(cards, cnt);
//		IntStream.range(0, cnt)
//			.forEach(i -> player.addCard(cards.remove(0)));
	}
}
