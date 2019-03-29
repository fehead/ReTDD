package kr.retdd.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
	final private	static	int DEFAULT_MONEY = 100;
	final private	static	int DEFAULT_BETTINGMONEY = 0;
	
	private	List<Card>	cards = new ArrayList<>();
	private	int	money = DEFAULT_MONEY;
	private	int	bettingMoney = DEFAULT_BETTINGMONEY;
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	private int calcScore() {
		int secondScore = cards.stream()
			.mapToInt(card -> card.secondScore())
			.sum();
		
		if(secondScore <= 21)
			return secondScore;

		return  cards.stream()
				.mapToInt(card -> card.firstScore())
				.sum();
	}
	
	@Override
	public String toString() {
		String cardsString = cards.stream()
			.map(Object::toString)
			.collect(Collectors.joining(","));
		
		StringBuffer buffer = new StringBuffer(512);
		buffer.append("money:")
			.append(money)
			.append("    bettingMoney:")
			.append(bettingMoney)
			.append("    your card:")
			.append(cardsString)
			.append("    your score:")
			.append(calcScore());

		return buffer.toString();
	}

	public void addCard(List<Card> deckCards, int cnt) {
		deckCards.stream()
			.limit(cnt)
			.forEach(deckCard -> addCard(deckCard))
			;
	}
}
