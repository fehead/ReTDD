package kr.retdd.basic;

import java.util.Arrays;

import org.junit.Test;

public class VarableArgumentTest {
	public void var1(int a, String ...strings) {
		var2(strings);
	}
	
	public void var2(String ...strings) {
		for(String s : strings)
			System.out.println(">> " + s);
	}

	public void var3(int a, String ...strings) {
		String [] addedArr = Arrays.copyOf(strings, strings.length + 1);
		addedArr[addedArr.length-1] = String.valueOf(a);
		var2(addedArr);
	}
	
	@Test
	public void vArgu1() {
		var1(0, "1", "2", "3", "4");
	}

	
	@Test
	public void vArgu2() {
		var3(0, "1", "2", "3", "4");
	}

}
