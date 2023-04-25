/**
 * 
 */
package lab5;

/**
 * @author takes
 * This class is a numeric question class that inherited Question class
 * This class has a question, answer, difficulty, and tolerance of the answer
 */
public class NumericQuestion extends Question{
	private double tolerance;
	
	/**
	 * Constructor, create a new NumericQuestion.
	 * @param question question
	 * @param answer answer of the question
	 * @param tolerance tolerance of the answer
	 * @param difficulty difficulty
	 */
	public NumericQuestion(String q, double a, double t, Difficulty d) {
		super(q, Double.toString(a), d);
		this.tolerance = t;
	}
	
	/**
	 * This method create a copy of NumericQuestion using polymorphism. return NumericQuestion as Question type
	 * @return Question
	 */
	public Question copy() {
		return new NumericQuestion(this.getQuestion(), Double.parseDouble(this.getAnswer()), this.tolerance, this.getDifficulty());
	}
	
	/**
	 * this method checks if the user answer is correct.
	 * @param ans user's answer
	 * @return true/false 
	 */
	public boolean checkAnswer(String ans) {
		double numAns;
		try {
			numAns = Double.parseDouble(ans);
		} catch(NumberFormatException e){
			return false;
		}
		double answer = Double.parseDouble(this.getAnswer());
		return Math.abs(numAns - answer) <= this.tolerance;
	}
}
