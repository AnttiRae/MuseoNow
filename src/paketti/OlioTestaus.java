package paketti;

import java.util.*;


public class OlioTestaus {

	public static void main(String[] args) {
		KysymysMaker kysymys = new KysymysMaker();
		kysymys.createQuestions();
		System.out.println(kysymys.showQuestion(1));
		System.out.println("\nVastaus:");
		Scanner lukija = new Scanner(System.in);
		kysymys.giveAnswers(lukija.nextLine());
		if (kysymys.checkAnswer(1)) {
			System.out.println("OIKEIN");
		}else {
			System.out.println("VÄÄRIN");
		}
	}

}