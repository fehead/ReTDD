package kr.retdd.survey;

import lombok.Getter;
import lombok.Setter;

/**
 * 선택항목
 * @author chaehoon1
 *
 */
public class Item {
	@Getter
	private	String	item;

	@Getter @Setter
	private	int	answerCnt = 0;

	public Item(String item) {
		this.item = item;
		answerCnt = 0;
	}

	public void addAnswerCnt() {
		answerCnt++;
	}
}
