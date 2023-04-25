/**
 * 
 */
package lab4;

/**
 * @author Takeshi Hashimoto
 * This class is Question that has a question, the answer, and difficulty depending on how difficult the question is.
 * It has setter and getter for these variables. 
 */
public class Question {
	enum Difficulty{ EASY, NORMAL, HARD};
	private String question;
	private String answer;
	private Difficulty difficulty;
	
	/**
	 * Constructor, create a new Question
	 * @param q Question
	 * @param a Answer
	 * @param d Difficulty
	 */
	public Question(String q, String a, Difficulty d) {
		this.question = q;
		this.answer = a;
		this.difficulty = d;
	}
	
	/**
	 * Create a new Question with values and normal difficulty
	 * @param q Question
	 * @param a Answer
	 */
	public Question(String q, String a) {
		this(q, a, Difficulty.NORMAL);
	}
	
	/**
	 * this method returns its question
	 * @return question
	 */
	public String getQuestion() {
		return this.question;
	}
	
	/**
	 * this methods sets question
	 * @param q question
	 */
	public void setQuestion(String q) {
		this.question = q;
	}
	
	/**
	 * this methods returns its answer
	 * @return answer
	 */
	public String getAnswer() {
		return this.answer;
	}
	
	/**
	 * this method sets answer
	 * @param a answer
	 */
	public void setAnswer(String a) {
		this.answer = a;
	}
	
	/**
	 * this method returns its difficulty
	 * @return difficulty
	 */
	public Difficulty getDifficulty() {
		return this.difficulty;
	}
	
	/**
	 * this method sets difficulty
	 * @param d difficulty
	 */
	public void setDifficulty(Difficulty d) {
		this.difficulty = d;
	}
	
	/**
	 * this method returns its information as String value
	 * @return str Information of the question
	 */
	public String toString() {
		String str = "";
		str += "The question is \"" + this.getQuestion() + "\"\n";
		str += "The answer is \"" + this.getAnswer() + "\"\n";
		str += "Difficulty: " + this.getDifficulty() + "\n";
		return str;
	}
	
	/**
	 * this method creates a copy of the object, but different reference
	 * @return new Question
	 */
	public Question copy() {
		return new Question(this.getQuestion(), this.getAnswer(), this.getDifficulty());
	}
	

}
