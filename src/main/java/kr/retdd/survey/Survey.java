package kr.retdd.survey;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 설문
 * @author chaehoon1
 *
 */
public class Survey {
	private	List<Question>		questionList = new ArrayList<>();

	public void regQuestion(Question qs) {
		questionList.add(qs);
	}

	public static void main(String[] args) {
		// 설문 문항을 입력받는다.
		Survey survey = new Survey();
		while(survey.inputQuestion() == true) {
			;
		}

		// 설문
		survey.survey();

		// 설문내용 출력.
		survey.printResult();

	}

	// 설문내용 출력.
	private void printResult() {
		for(Question qs : questionList) {
			qs.printResult();
		}
	}

	// 설문
	private void survey() {
		try (Scanner sc = new Scanner(System.in)) {
			for(Question qs : questionList) {
				// 설문 내용을 출력한다.
				qs.printQuestion();
				// 답변을 받는다.
				qs.inputAnswer(sc);
			}
		}

	}

	private boolean inputQuestion() {
		try (Scanner sc = new Scanner(System.in)) {
			// 질문을 입력 받음.
			Question qs = Question.of(sc);
			if (qs == null)
				return false;
			// 설문 항목을 입력 받음
			qs.addItemFrom(sc);
		}
		return true;
	}
}
