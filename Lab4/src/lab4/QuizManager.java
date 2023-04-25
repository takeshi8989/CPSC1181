/**
 * 
 */
package lab4;

import java.util.ArrayList;
import java.util.Scanner;

import lab4.Question.Difficulty;

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
		quiz1.add(new Question("How many times does the average person laugh in a day?\nA. 7\tB. 13\tC. 22\tD. 42", "B", Difficulty.EASY));
		quiz1.add(new Question("Which is the second biggest city in Japan?\nA. Osaka\tB. Sapporo\tC. Kobe\tD. Yokohama", "D", Difficulty.EASY));
		quiz1.add(new Question("What is the population of Yokohama 2022?\nA. 3.7 millions\tB. 11.1 millions\tC. 890 thousands\tD. 2.2 millions", "A", Difficulty.EASY));
		quiz1.add(new Question("Which animal does spends most of their time in water?\nA. Turtle\tB. Tortoise", "A", Difficulty.EASY));
		quiz1.add(new Question("Which is made of milk?\nA. Butter\tB. Margarine", "A", Difficulty.EASY));
		quiz1.add(new Question("How much does a hamster run per day?\nA. 100m\tB. 1km\tC. 5km", "C", Difficulty.EASY));
		quiz1.add(new Question("How many brothers are the Wright Brothers, famous for inventing the airplane?", "7", Difficulty.NORMAL));
		quiz1.add(new Question("How many minutes of sleep does a giraffe get per day?", "20", Difficulty.NORMAL));
		quiz1.add(new Question("How many brains does an octopus have?", "9", Difficulty.NORMAL));
		quiz1.add(new Question("How many legs does a squid have in total?", "8", Difficulty.NORMAL));
		quiz1.add(new Question("What is the gold medal made of?", "Copper", Difficulty.HARD));
		quiz1.add(new Question("What is the skin color of polar bears?", "Black", Difficulty.HARD));
		quiz1.add(new Question("What is the most widely produced fruit in the world?", "Grape", Difficulty.HARD));
		quiz1.add(new Question("Pufferfish swell up when they are threatening, but where do they swell up?", "Stomach", Difficulty.HARD));
		quiz1.add(new Question("What is the unit of measurement for the distance moved by the computer mouse?", "Mickey", Difficulty.HARD));
		
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
			System.out.println("Question #" + (i+1) + " (" + q.getDifficulty() + ")");
			System.out.println(q.getQuestion());
			System.out.print("Your Answer: ");
			String answer = input.next();
			if(answer.toLowerCase().equals(q.getAnswer().toLowerCase())) {
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
