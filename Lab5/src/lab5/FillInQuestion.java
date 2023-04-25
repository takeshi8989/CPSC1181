/**
 * 
 */
package lab5;
import lab5.Question.Difficulty;

/**
 * @author takes
 * This class is a Fill in question class that inherited Question class
 * sentence is given to this class with a format of ~~~~~-_answer_-~~~?
 */
public class FillInQuestion extends Question{
	
	/**
	 * Constructor, create a new FillInQuestion. It parses question and answer from sentence
	 * @param sentence sentence that contains question and answer
	 * @param d difficulty
	 */
	public FillInQuestion(String sentence, Difficulty d) {
		super(FillInQuestion.parseQ(sentence), FillInQuestion.parseA(sentence), d);
	}
	
	/**
	 * This method takes sentence including answer, then return only question part with underline
	 * @param sentence
	 * @return question
	 */
	public static String parseQ(String sentence) {
		String parsedQ = "";
		boolean isAnswer = false;
		for (int i = 0; i < sentence.length(); i++) {
			if(sentence.charAt(i) == '_')
				isAnswer = !isAnswer;
			if(isAnswer)
				parsedQ += '_';
			else
				parsedQ += sentence.charAt(i);
		}
		return parsedQ;
	}
	
	/**
	 * This method takes sentence including question, then return only answer part
	 * @param sentence
	 * @return answer
	 */
	public static String parseA(String sentence) {
		int start = sentence.indexOf("_-") + 2;
		int end = sentence.indexOf("-_");
		if(start == -1 || end == -1) {
			throw new IllegalArgumentException("The sentence is not correct format");
		}
		return sentence.substring(start, end);
	}
	
	/**
	 * This method create a copy of fillInQuestion using polymorphism. return FillInQuestion as Question type
	 * @return Question
	 */
	public Question copy() {
		String sentence = this.getQuestion();
		int start = sentence.indexOf("_");
		int end = sentence.lastIndexOf("_") + 1;
		sentence = sentence.substring(0, start) + "_-" + this.getAnswer() + "-_" + sentence.substring(end);
		
		return new FillInQuestion(sentence, this.getDifficulty());
	}
}
