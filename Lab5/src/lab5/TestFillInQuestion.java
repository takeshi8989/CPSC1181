package lab5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lab5.Question.Difficulty;

class TestFillInQuestion {
	
	@Test
	void testFillInQuestion() {
		FillInQuestion q = new FillInQuestion("The biggest city in Canada is _-Toronto-_.", Difficulty.EASY);
		assertTrue(q.getQuestion().equals("The biggest city in Canada is ___________."));
		assertTrue(q.getAnswer().equals("Toronto"));
		assertEquals(q.getDifficulty(), Difficulty.EASY);
	}

	@Test
	void testCopy() {
		FillInQuestion q = new FillInQuestion("The biggest city in Canada is _-Toronto-_.", Difficulty.EASY);
		Question copy = q.copy();
		assertTrue(q.getQuestion().equals("The biggest city in Canada is ___________."));
		assertTrue(q.getAnswer().equals("Toronto"));
		assertTrue(copy instanceof Question);
	}
}
