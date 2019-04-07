package kr.retdd.lotto;

public class LottoNumber implements Comparable<LottoNumber>{
	public	static final int	MIN_NUMBER = 1;
	public	static final int	MAX_NUMBER = 45;
	
	final private	int	number;
	
	private LottoNumber(int number) {
		this.number = number;
	}

	static public LottoNumber of(String numberString) {
		int number = Integer.parseInt(numberString);
		return of(number);		
	}

	
	static public LottoNumber of(int number) {
		if(number < MIN_NUMBER || MAX_NUMBER < number)
			throw new IllegalArgumentException("로또번호는 " + MIN_NUMBER + "과 " + MAX_NUMBER + "사이 값 이어야 합니다.");
		
		return new LottoNumber(number);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof LottoNumber))
			return false;
		LottoNumber v = (LottoNumber)obj;
		if(obj == this || this.number == v.number)			
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return String.valueOf(number);
	}
	
	@Override
	public int compareTo(LottoNumber o) {
		return this.number - o.number;
	}
}
