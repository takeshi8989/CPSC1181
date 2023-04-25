/**
 * 
 */
package lab5;

import java.util.ArrayList;
import java.util.Scanner;

import lab5.Question.Difficulty;

/**
 * @author Takeshi Hashimoto
 *  This class creates two quizes. One is all quizes for all difficulties (Easy, Normal, Hard). Another one is only Easy quiz.
 *  User can answer to each question, and after answering all of them, the user can see the result of the quiz
 *
 */
public class QuizManager {

	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuizMaker quiz1 = new QuizMaker("quiz1");
		quiz1.addQuestion(new NumericQuestion("How many times does the average person laugh in a day?", 13, 1, Difficulty.EASY));
		quiz1.addQuestion(new Question("Which is the second biggest city in Japan?\nA. Osaka\tB. Sapporo\tC. Kobe\tD. Yokohama", "D", Difficulty.EASY));
		quiz1.addQuestion(new NumericQuestion("How many millions is the population of Yokohama 2022?", 3.7, 0.1, Difficulty.EASY));
		quiz1.addQuestion(new Question("Which animal does spends most of their time in water?\nA. Turtle\tB. Tortoise", "A", Difficulty.EASY));
		quiz1.addQuestion(new Question("Which is made of milk?\nA. Butter\tB. Margarine", "A", Difficulty.EASY));
		quiz1.addQuestion(new NumericQuestion("How much does a hamster run per day?", 1000, 500, Difficulty.EASY));
		quiz1.addQuestion(new NumericQuestion("How many brothers are the Wright Brothers, famous for inventing the airplane?", 7, 0, Difficulty.NORMAL));
		quiz1.addQuestion(new NumericQuestion("How many minutes of sleep does a giraffe get per day?", 20, 2, Difficulty.NORMAL));
		quiz1.addQuestion(new NumericQuestion("How many brains does an octopus have?", 9, 0, Difficulty.NORMAL));
		quiz1.addQuestion(new NumericQuestion("How many legs does a squid have in total?", 8, 0, Difficulty.NORMAL));
		quiz1.addQuestion(new FillInQuestion("The gold medal made of _-Copper-_.", Difficulty.HARD));
		quiz1.addQuestion(new FillInQuestion("The color _-Black-_ is the skin color of polar bears", Difficulty.HARD));
		quiz1.addQuestion(new FillInQuestion("The most widely produced fruit in the world is _-Grape-_.", Difficulty.HARD));
		quiz1.addQuestion(new FillInQuestion("Pufferfish swell up its _-Stomach-_ when they are threatening.", Difficulty.HARD));
		quiz1.addQuestion(new FillInQuestion("The unit of measurement for the distance moved by the computer mouse is _-Mickey-_.", Difficulty.HARD));
		
		giveQuiz(quiz1.createQuiz());
		System.out.println("\n-----Review Easy Questions-----\n");
		giveQuiz(quiz1.createQuiz(Difficulty.EASY));

	}
	
	/**
	 * this method gives a user a quiz by using ArrayList of Questions.
	 * For each question, User can answer by keyboard.
	 * @param questions quiz's questions
	 */
	public static void giveQuiz(ArrayList<Question> questions) {
		if(questions.size() == 0) {
			throw new IllegalArgumentException("Empty Questions");
		}
		int score = 0;
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < questions.size(); i++) {
			Question q = questions.get(i);
			System.out.println("#" + (i+1));
			System.out.println(q.displayQuestion());
			System.out.println();
			System.out.print("Enter Your Answer: ");
			String answer = input.next();
			if(q.checkAnswer(answer)) {
				score++;
				System.out.println("Correct!");
			} else {
				System.out.println("InCorrect. Answer is " + q.getAnswer());
			}
			
			System.out.println();
		}
		System.out.println("Your Score: " + score + "/" + questions.size());
		System.out.println();
	}
	

}
