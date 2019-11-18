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
		
}
