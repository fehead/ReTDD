package kr.retdd.baseball;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaseBall {
	private	BallSet	ballSet;
	private	boolean	isQuit = false;
	
	public static void main(String[] args) {
		BaseBall baseBall = new BaseBall();
		while(!baseBall.isQuit())
			baseBall.start();
	}

	private void start() {
		ballSet = BallSet.valueOf("123");
		printTitle();
		boolean isWin = false;
		for(int round = 1; round <= 9 || !isWin ; ++round) {
			BallSet gamersBallSet = readBallSet();
			if(isQuit)
				break;
			BallJugiment j = BallJugiment.jugiment(ballSet, gamersBallSet);
			isWin = j.isWin();
			printRound(round, j);
		}
		printBye();
	}

	private void printRound(int round, BallJugiment j) {
		System.out.println(round + "회 :" + j.toString());
		if(j.isWin())
			System.out.println("당신이 이겼습니다.");
	}

	private BallSet readBallSet() {
		BallSet	ret = null;		
		while(ret == null && !isQuit) {
			String ballStr = readFromConsole();
			isQuit = checkQuit(ballStr);
			try {
				ret = BallSet.valueOf(ballStr);
			} catch (IllegalArgumentException e) {
				ret = null;
			}
		}
		return ret;
	}

	private boolean checkQuit(String ballStr) {
		if(ballStr == null || ballStr.isEmpty())
			return false;
		return ballStr.toLowerCase().startsWith("q");
	}

	private	String readFromConsole() {
		String ret = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			while((ret= reader.readLine()) == null) {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ret;
	}

	private boolean isQuit() {
		return isQuit;
	}
	
	private void printTitle() {
		System.out.println("=======================================");		
		System.out.println("3자리 숫자를 입력하세요");
		System.out.println("게임 종료는 q를 입력하세요.");
		System.out.println("야구게임 시작");
	}

	private void printBye() {
		System.out.println("=======================================");		
	}
	
}
