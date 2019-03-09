package kr.retdd.baseball;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

public class BallJugiment {
	private	int	strike = 0;
	private	int	ball = 0;
	private	int out = 0;
	
	private	BallJugiment(int strike,int ball, int out) {
		this.strike = strike;
		this.ball = ball;
		this.out = out;
	}
	
	public static BallJugiment jugiment(BallSet own, BallSet other) {
		List<Ball> ownBalls = own.getBalls();
		List<Ball> otherBalls = other.getBalls();
		
		return new BallJugiment(strike(ownBalls, otherBalls),
				ball(ownBalls, otherBalls),
				out(ownBalls, otherBalls));
		
	}
	
	private static int strike(List<Ball> ownBalls, List<Ball> otherBalls) {
		int ret = 0;
		for(int i = 0 ; i < ownBalls.size() ; ++i) {
			if(ownBalls.get(i).equals(otherBalls.get(i)))
				ret++;
		}
		return ret;
	}
	
	private static int ball(List<Ball> ownBalls, List<Ball> otherBalls) {
		int ret = 0;
		for(int i = 0 ; i < ownBalls.size() ; ++i) {
			List<Ball> copyOther = new ArrayList<>(otherBalls);
			copyOther.remove(i);		
			if(copyOther.contains(ownBalls.get(i)))
				ret++;
		}
		return ret;
	}
	
	private static int out(List<Ball> ownBalls, List<Ball> otherBalls) {
		int ret = 0;
		for(Ball b : ownBalls) {
			if(!otherBalls.contains(b))
				ret++;
		}
		
		return ret;
	}

	public Integer getStrike() {
		return this.strike;
	}

	public Integer getBall() {
		return this.ball;
	}
	
	public Integer getOut() {
		return this.out;
	}
}
