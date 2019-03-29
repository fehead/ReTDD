package kr.retdd.blackjack;

import java.util.Scanner;

public class Blackjack {
	private	Player	player = new Player();
	private	Dealer	dealer = new Dealer();
	private	Deck	deck;
	
	public Blackjack() {
		deck = Deck.create();
	}
	
	private	void start() {
		// help 출력
		System.out.println("당신의 소지 금액: 100");
		// 배팅금액을 입력받는다.
		Scanner scanner = new Scanner(System.in);
		System.out.println("배팅금액을 입력해주세요 종료하려면 q를 입력하세요: ");
		String line = scanner.nextLine();
		if(line.toLowerCase().startsWith("q")) {
			// 종료.
			return;
		}

		int bettingMoney = Integer.valueOf(line);
				
		// 딜러와 플레이어에게 카드 두장을 준다.
		deck.handTo(dealer, 2);
		deck.handTo(player, 2);
		
		
		// 딜러카드 한장을 보여준다.
		Card dealerCard = dealer.getFirstCard();
		System.out.println("Dealer's card : * " + dealerCard.toString());
		
		// 플레이어와 딜러의 점수를 계산하고 이를 보여준다.
		System.out.println("Your card : " + player.toString());
		
		// 플레이어에게 Hit or Stand 인지 입력을 받는다.
		System.out.println("Hit or Stand(h/s): ");
		String hitOrStand = scanner.nextLine();
		if(hitOrStand.toLowerCase().startsWith("h")) {
			deck.handTo(player, 1);
		}
		
		// 딜러의 점수가 17점 미만이면 카드를 한장 받는다.
		deck.handTo(dealer, 1);
		
		// 플레이어가 hit이면 플레이어에게 카드 한장을 준다.
		// 플레이어와 딜러의 점수를 계산한다.
		// 플레이어와 딜러 모두 Stand이면 게임을 종료한다.

	}
	
	public static void main(String [] argv) {
		Blackjack b = new Blackjack();
		b.start();
	}
}
