package kr.retdd.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Dealer {
	private	List<Card>	cards = new ArrayList<>();
	private	UserState	state = UserState.HIT;

	private void addCard(Card card) {		
		cards.add(card);
	}

	public Card getFirstCard() {
		if(cards.isEmpty())
			return null;
		return cards.get(0);
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
			buffer.append("    dealer's card:")
				.append(cardsString)
				.append("    dealer's score:")
				.append(calcScore());

			return buffer.toString();
	}

	public void addCard(List<Card> deckCards, int cnt) {
		while(calcScore() < 17 && 0 < cnt--) {
			addCard(deckCards.remove(0));
		}
		
		if(17 <= calcScore())
			state = UserState.STAND;
	}

	public boolean isStand() {
		return state.equals(UserState.STAND);
	}

	public boolean isHit() {
		return state.equals(UserState.HIT);
	}
}
