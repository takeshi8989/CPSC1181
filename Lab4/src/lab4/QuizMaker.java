/**
 * 
 */
package lab4;

import java.util.ArrayList;

import lab4.Question.Difficulty;

/**
 * @author Takeshi Hashimoto
 * This class is QuizMaker that creates a quiz (List of Questions) using ArrayList
 * it has name and questions. We can add a question to this object, and create quiz of all questions or particular difficulty
 */
public class QuizMaker {
	private String name;
	private ArrayList<Question> questions;
	
	/**
	 * Constructor. create a new Quiz
	 * @param n name
	 */
	public QuizMaker(String n) {
		this.name = n;
		this.questions = new ArrayList<Question>(0);
	}
	
	/**
	 * this method adds new question to the quiz
	 * @param q quiz
	 */
	public void add(Question q) {
		this.questions.add(q.copy());
	}
	
	/**
	 * this method returns its name
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * this method sets name
	 * @param n name
	 */
	public void setName(String n) {
		this.name = n;
	}
	
	/**
	 * this method creates a quiz of all questions in this quiz and returns ArrayList of Questions as quiz
	 * @return quiz 
	 */
	public ArrayList<Question> createQuiz() {
		ArrayList<Question> quiz = new ArrayList<Question>(this.questions.size());
		for(int i = 0; i < this.questions.size(); i++) {
			quiz.add(this.questions.get(i));
		}
		return quiz;
	}
	
	/**
	 * this method creates a quiz of questions in this quiz that have a difficulty of parameter and returns ArrayList of Questions as quiz
	 * @param d difficulty
	 * @return quiz 
	 */
	public ArrayList<Question> createQuiz(Difficulty d){
		ArrayList<Question> quiz = new ArrayList<Question>();
		for(int i = 0; i < this.questions.size(); i++) {
			if(this.questions.get(i).getDifficulty() == d){
				quiz.add(this.questions.get(i));
			}
		}
		return quiz;
	}
}

