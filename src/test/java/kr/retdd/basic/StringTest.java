package kr.retdd.basic;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringTest {

	private String fourCharJoin(String invcNo) {
		List<String> strList = new ArrayList<>();
		for(int i = 0 ; invcNo != null && i < invcNo.length() ; i += 4) {
			String str = StringUtils.substring(invcNo, i, i+4);
			if(str.isEmpty()) {
				break;
			}
			strList.add(str);
		}
		return String.join("-", strList);
	}


	@Test
	public void joinTest() {
		assertThat(fourCharJoin(null)).isEqualTo("");
		assertThat(fourCharJoin("")).isEqualTo("");
		assertThat(fourCharJoin("0")).isEqualTo("0");
		assertThat(fourCharJoin("01")).isEqualTo("01");
		assertThat(fourCharJoin("012")).isEqualTo("012");
		assertThat(fourCharJoin("0123")).isEqualTo("0123");
		assertThat(fourCharJoin("01234")).isEqualTo("0123-4");
		assertThat(fourCharJoin("012345")).isEqualTo("0123-45");
		assertThat(fourCharJoin("0123456")).isEqualTo("0123-456");
		assertThat(fourCharJoin("01234567")).isEqualTo("0123-4567");
		assertThat(fourCharJoin("012345678")).isEqualTo("0123-4567-8");
		assertThat(fourCharJoin("0123456789")).isEqualTo("0123-4567-89");
		assertThat(fourCharJoin("01234567890")).isEqualTo("0123-4567-890");
		assertThat(fourCharJoin("012345678901")).isEqualTo("0123-4567-8901");
		assertThat(fourCharJoin("0123456789012")).isEqualTo("0123-4567-8901-2");
	}

	@Test
	public void lengthTest() {
		String aa =	"AB(중형) 웨더비 프리미엄 라키나 Ver.2-화이트-x";
		String bb = "CC(소형) 우야꼬 블루투스 스피커M2-다크월넛-x";
		String cc = "123456789012345678901234567890";
		String dd = "1234567890123456789012345678901234567890";
		String ee =	"일이삼사오";
		String ff =	"일이삼사오육칠";

		log.info("aa{}, {}", aa.length(), aa.getBytes().length);
		log.info("bb{}, {}", bb.length(), bb.getBytes().length);
		assertThat(aa.length()).isLessThan(aa.getBytes().length);
		assertThat(bb.length()).isLessThan(bb.getBytes().length);
		assertThat(cc.length()).isEqualTo(cc.getBytes().length);
		assertThat(dd.length()).isEqualTo(dd.getBytes().length);

		log.info("ee{}, {}", ee.length(), ee.getBytes().length);
		assertThat(ee.length()*3).isEqualTo(ee.getBytes().length);
		assertThat(ff.length()*3).isEqualTo(ff.getBytes().length);

		log.info("aa sub{}", StringUtils.substring(aa, 25));
		log.info("aa sub{}", StringUtils.left(aa, 25));
	}

}
