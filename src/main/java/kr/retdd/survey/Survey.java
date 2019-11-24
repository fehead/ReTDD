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
		try (Scanner sc = new Scanner(System.in)) {
			survey.inputQuestion(sc);

			do {
				// 설문
				survey.survey(sc);
	
				// 설문내용 출력.
				survey.printResult();
			} while(survey.retry(sc));
			
		}

	}

	// 재설문.
	private boolean retry(Scanner sc) {
		while(true) {
			System.out.println("재설문 하시겠습니까?(y/n)");
			String tmp = sc.nextLine();
			if(tmp.toLowerCase().startsWith("y")) {
				resetAnswer();
				return true;
			} else if(tmp.toLowerCase().startsWith("n")) {
				return false;
			}
		}
		// return false;	
	}

	private void resetAnswer() {
		for(Question qs : questionList) {
			qs.reset();
		}
	}

	// 설문내용 출력.
	private void printResult() {
		for(Question qs : questionList) {
			qs.printResult();
		}
	}

	// 설문
	private void survey(Scanner sc) {
		for(Question qs : questionList) {
			// 설문 내용을 출력한다.
			qs.printQuestion();
			// 답변을 받는다.
			qs.inputAnswer(sc);
		}
	
	}

	private void inputQuestion(Scanner sc) {
		while(true) {
			// 질문을 입력 받음.
			Question qs = Question.of(sc);
			if (qs == null)
				return;
			// 설문 항목을 입력 받음
			qs.addItemFrom(sc);
			questionList.add(qs);
		}
	}
}
