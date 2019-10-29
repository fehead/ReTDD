package kr.retdd.basic;

import org.junit.Test;

public class VarableArgumentTest {
	public void var1(int a, String ...strings) {
		var2(strings);
	}
	
	public void var2(String ...strings) {
		for(String s : strings)
			System.out.println(">> " + s);
	}

	@Test
	public void vArgu1() {
		var1(0, "1", "2", "3", "4");
	}

}
