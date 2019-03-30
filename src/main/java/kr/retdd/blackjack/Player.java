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
	private	UserState	state = UserState.HIT;
	
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
		while(0 < cnt--) {
			addCard(deckCards.remove(0));
		}
		
		if(21 <= calcScore())
			state = UserState.STAND;
	}

	public boolean isStand() {
		return state.equals(UserState.STAND);
	}

	public void setStand() {
		state = UserState.STAND;
	}

	public boolean isHit() {
		return state.equals(UserState.HIT);
	}
	
	public void setBettingMoney(int b) {
		bettingMoney = b;
	}
}
