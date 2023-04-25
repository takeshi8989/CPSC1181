package lab4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lab4.Question.Difficulty;

class TestQuestion {

	@Test
	void testQuestion() {
		Question q1 = new Question("How old am I?", "20", Difficulty.EASY);
		assertTrue(q1.getAnswer().equals("20"));
		Question q2 = new Question("Where am I from?", "Japan");
		assertEquals(q2.getDifficulty(), Difficulty.NORMAL);
	}

	@Test
	void testGetQuestion() {
		Question q = new Question("How old am I?", "20", Difficulty.EASY);
		assertTrue(q.getQuestion().equals("How old am I?"));
	}

	@Test
	void testSetQuestion() {
		Question q = new Question("How old am I?", "20", Difficulty.EASY);
		q.setQuestion("Where am I from?");
		assertTrue(q.getQuestion().equals("Where am I from?"));
	}

	@Test
	void testGetAnswer() {
		Question q = new Question("How old am I?", "20", Difficulty.EASY);
		assertTrue(q.getAnswer().equals("20"));
	}

	@Test
	void testSetAnswer() {
		Question q = new Question("How old am I?", "20", Difficulty.EASY);
		q.setAnswer("21");
		assertTrue(q.getAnswer().equals("21"));
	}

	@Test
	void testGetDifficulty() {
		Question q = new Question("How old am I?", "20", Difficulty.EASY);
		assertEquals(q.getDifficulty(), Difficulty.EASY);
	}

	@Test
	void testSetDifficulty() {
		Question q = new Question("How old am I?", "20", Difficulty.EASY);
		q.setDifficulty(Difficulty.HARD);
		assertEquals(q.getDifficulty(), Difficulty.HARD);
	}

	@Test
	void testCopy() {
		Question q = new Question("How old am I?", "20", Difficulty.EASY);
		Question copy = q.copy();
		assertFalse(q == copy);
		assertTrue(q.getQuestion().equals(copy.getQuestion()));
		assertTrue(q.getAnswer().equals(copy.getAnswer()));
		assertEquals(q.getDifficulty(), copy.getDifficulty());
	}

}
