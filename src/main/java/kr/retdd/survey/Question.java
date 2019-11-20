package kr.retdd.survey;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 질문
 * @author chaehoon1
 *
 */
public class Question {
	private	String	question;
	private	List<Item>	itemList;
	private	final int	qsNum;

	public static Question of(Scanner sc) {
		String qs = "";
		while(qs.isEmpty()) {
			System.out.println("질문을 입력하세요(q:종료)");
			qs = sc.nextLine();
			if(qs.trim().toLowerCase().equals("q"))
				return null;
		}

		int qsNum = 0;
		while(qsNum <= 0) {
			System.out.println("선택항목의 개수를 입력하세요: ");
			qsNum = sc.nextInt();
			sc.nextLine();
		}
		return new Question(qs, qsNum);
	}

	// ② 선택항목의 개수를 지정할 수 있다.
	private Question(String question, int qsNum) {
		this.question = question;
		this.qsNum = qsNum;
		itemList = new ArrayList<>(qsNum);
	}

	// ③ 선택항목을 등록할 수 있다.
	public void add(Item item) {
		if(qsNum <= itemList.size())
			return;
		itemList.add(item);
	}

	public boolean selectItem(int selectNum) {
		if(selectNum < 1 || qsNum < selectNum)
			return false;
		Item item = itemList.get(selectNum-1);
		item.addAnswerCnt();
		return true;
	}

	// ④ 설문 문항(질문과 선택항목)을 출력해 볼 수 있다.
	public String printQuestion() {
		StringBuilder	sb = new StringBuilder();
		sb.append(this.question);
		sb.append("%n");
		for(int i = 0 ; i < itemList.size() ; ++i) {
			sb.append("\t");
			sb.append(i+1 + ")");
			sb.append(itemList.get(i).getItem());
			sb.append("%n");
		}
		sb.append("%n");
		return sb.toString();
	}

	// 설문 응답 결과를 출력할 수 있다.
	public String printResult() {
		StringBuilder	sb = new StringBuilder();
		sb.append(this.question);
		sb.append("%n");
		for(int i = 0 ; i < itemList.size() ; ++i) {
			Item item = itemList.get(i);
			sb.append("\t");
			sb.append("응답자 : " + item.getAnswerCnt() + "명\t");
			sb.append(i+1 + ")");
			sb.append(item.getItem());
			sb.append("%n");
		}
		sb.append("%n");
		return sb.toString();
	}

	public void reset() {
		for(Item i : itemList) {
			i.setAnswerCnt(0);
		}
	}

	// 설문 문항(질문과 선택항목)을 입력한다.
	public void addItemFrom(Scanner sc) {
		while(itemList.size() < qsNum) {
			String qs = "";
			while(qs.trim().isEmpty()) {
				int nextItemNum = itemList.size() + 1 ;
				String tmp = String.format("%d개중 %d번 설문 문항을 입력하세요: ", qsNum, nextItemNum);
				System.out.println(tmp);
				qs = sc.nextLine();
			}
			itemList.add(new Item(qs));
		}
	}

	public void inputAnswer(Scanner sc) {
		int answerNum = 0;
		while(selectItem(answerNum)) {
			System.out.println("답변을 입력하시요:");
			answerNum = sc.nextInt();
		}
	}

}
