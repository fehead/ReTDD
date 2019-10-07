package kr.retdd.basic;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IntegerTest {
	@Test
	public void intgerTest() {
		Integer	a = 10;
		Integer b = new Integer(10);
		Integer c = 10;
		
		log.info("a == b : {}", a.equals(b));
		assertThat(a.equals(b)).isTrue();
		
		log.info("b : " + System.identityHashCode(b));
		log.info("a : " + System.identityHashCode(a) + "    c : " + System.identityHashCode(c));
		assertThat(a == c).isTrue();
	}
	
	@Test
	public void finalTest() {
		final Integer d;
		d = 10;
		assertThat(d.equals(10)).isTrue();
	}
	
	@Test
	public void intgerTest2() {
		Random random = new Random();
		
		int		r = random.nextInt();
		Integer	a =  new Integer(r);		
		Integer b = new Integer(a);
		
		log.info("a == b : {}", a == b);
		log.info("a.equals(b) : {}", a.equals(b));
		assertThat(a.equals(b)).isTrue();
		
		assertThat(a == b).isFalse();
		
		
		log.info("b == r : {}", b == r);
		log.info("r == b : {}", r == b);
	}
	
	
	@Test
	public void intgerTest3() {
		Random random = new Random();
		
		for(int i = 0 ; i < 100000  ; ++i) {
			int		r = random.nextInt();
			Integer	a =  new Integer(r);		
			Integer b = new Integer(a);

			if(b != r) {
				log.info(">>> b == r : {}", b == r);
				log.info(">>> r == b : {}", r == b);	
			}
			
			if(a == b) {
				log.info(">>> a == b : {}", a == b);
			}
		}
	}
}
