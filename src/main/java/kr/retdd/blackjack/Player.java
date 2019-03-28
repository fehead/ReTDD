package kr.retdd.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
	final private	static	int DEFAULT_MONEY = 100;
	final private	static	int DEFAULT_BETTINGMONEY = 0;
	
	private	List<Card>	cards = new ArrayList<>();
	private	int	money = DEFAULT_MONEY;
	private	int	bettingMoney = DEFAULT_BETTINGMONEY;
	
}
