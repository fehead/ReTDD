package kr.retdd.blackjack;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Suit {
	SPADE("♠"),
	HEART("♥"),
	DIAMOND("♦"),
	CLUB("♣");

	private	String	value;
	
	public String	value() {		
		return value;
	}
}
