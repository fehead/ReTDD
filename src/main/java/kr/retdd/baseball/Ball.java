package kr.retdd.baseball;

public class Ball implements Comparable<Ball>{
	private	int ball;	// 0 ~ 9사이의 숫자만 허용
	
	private	Ball() {
	}

	public static Ball valueOf(String ballStr) {
		int ball = Integer.parseInt(ballStr);
		return valueOf(ball);
	}
	
	public static Ball valueOf(int ball) {
		if(ball < 0)
			throw new IllegalArgumentException("마이너스 값으로 설정할수 없습니다.");
		if(9 < ball)
			throw new IllegalArgumentException("9 이상의 값으로 설정할수 없습니다.");
		
		Ball ret = new Ball();
		ret.setBall(ball);
		return ret;
	}

	private void setBall(int ball) {
		this.ball = ball;
	}
	
	@Override
	public int compareTo(Ball o) {
		return this.ball - o.ball; 
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		
		if(!(obj instanceof Ball))
			return false;
		
		Ball o = (Ball)obj;
		
		return this.ball == o.ball;
	}
	
	@Override
	public String toString() {
		return String.valueOf(ball);
	}

}
