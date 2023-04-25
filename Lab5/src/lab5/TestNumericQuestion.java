package lab5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lab5.Question.Difficulty;

class TestNumericQuestion {
	
	@Test
	void testNumericQuestion() {
		NumericQuestion q = new NumericQuestion("What is the value of pie?", 3.14, 0.01, Difficulty.EASY);
		assertTrue(q.getQuestion().equals("What is the value of pie?"));
		assertTrue(q.getAnswer().equals("3.14"));
		assertEquals(q.getDifficulty(), Difficulty.EASY);
	}

	@Test
	void testCopy() {
		NumericQuestion q = new NumericQuestion("What is the value of pie?", 3.14, 0.01, Difficulty.EASY);
		Question copy = q.copy();
		assertTrue(copy.getQuestion().equals("What is the value of pie?"));
		assertTrue(copy.getAnswer().equals("3.14"));
		assertTrue(copy instanceof Question);
	}

	@Test
	void testCheckAnswer() {
		NumericQuestion q = new NumericQuestion("What is the value of pie?", 3.14, 0.01, Difficulty.EASY);
		assertTrue(q.checkAnswer("3.14"));
		assertTrue(q.checkAnswer("3.15"));
		assertTrue(q.checkAnswer("3.131"));
		assertFalse(q.checkAnswer("3.129"));
	}

}
