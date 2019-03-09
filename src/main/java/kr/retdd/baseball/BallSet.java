package kr.retdd.baseball;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class BallSet {
	List<Ball>	balls;

	public static BallSet valueOf(String ballStr) {
		String [] ballArr = ballStr.split("");
		if(ballArr.length != 3)
			throw new IllegalArgumentException("볼수는 3개이여야 합니다.");
		
		if(isDuplication(ballArr))
			throw new IllegalArgumentException("중복된 숫자가 있습니다.");
		
		return create(ballArr);
	}
	
	private static BallSet create(String[] ballArr) {
		List<Ball>	balls = new ArrayList<>();
		for(String b : ballArr)
			balls.add(Ball.valueOf(b));
		BallSet ret = new BallSet();
		ret.setBalls(balls);
		return ret;
	}

	private void setBalls(List<Ball> balls) {
		this.balls = balls;
	}

	private static boolean isDuplication(String [] ballArr) {
		Set<String> s = new TreeSet<>(Arrays.asList(ballArr));
		return s.size() != ballArr.length;
	}

	public List<Ball> getBalls() {
		return this.balls;
	}
}
