package lab3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestTrain {

	@Test
	void testTrain() {
		Train a = new Train("train 1", 38.2);
		assertEquals(a.getLocPower(), 38.2);
		assertEquals(a.getNumberOfCars(), 0);
	}

	@Test
	void testGetName() {
		Train train = new Train("my train", 123.88);
		assertTrue(train.getName().equals("my train"));
	}

	@Test
	void testSetName() {
		Train train = new Train("my train", 123.88);
		train.setName("new train");
		assertTrue(train.getName().equals("new train"));
		train.setName("");
		assertTrue(train.getName().equals(""));
	}

	@Test
	void testGetLocPower() {
		Train train = new Train("my train", 98.32);
		assertEquals(train.getLocPower(), 98.32);
	}

	@Test
	void testSetLocPower() {
		Train train = new Train("my train", 98.32);
		train.setLocPower(77.77);
		assertEquals(train.getLocPower(), 77.77);
		try {
			train.setLocPower(-43.1);
			fail("setting negative locPower");
		} catch(IllegalArgumentException e) {}
		assertEquals(train.getLocPower(), 77.77);
	}

	@Test
	void testGetFreightCars() {
		Train train = new Train("my train", 98.32);
		int[] newCars = new int[] {3,2,3,5,6,7};
		train.addCars(newCars);
		int[] cars = train.getFreightCars();
		for(int i = 0; i < newCars.length; i++) {
			if(newCars[i] != cars[i])
				fail("the car is different");
		}
	}

	@Test
	void testAddCars() {
		Train train = new Train("my train", 98.32);
		int[] newCars = new int[] {3,2,3};
		train.addCars(newCars);
		assertEquals(train.getNumberOfCars(), 3);
		try {
			train.addCars(null);
			fail("added null to freight cars");
		} catch(IllegalArgumentException e) {}
		train.addCars();
		assertEquals(train.getNumberOfCars(), 3);
	}

	@Test
	void testRemoveAll() {
		Train train = new Train("my train", 98.32);
		int[] newCars = new int[] {3,2,3};
		train.addCars(newCars);
		train.removeAll();
		assertEquals(train.getNumberOfCars(), 0);
	}

	@Test
	void testMergeTrains() {
		Train train1 = new Train("first", 98.32);
		Train train2 = new Train("second", 98.32);
		int[] cars1 = new int[] {3,2,3};
		int[] cars2 = new int[] {8,2,9,1};
		train1.addCars(cars1);
		train2.addCars(cars2);
		train1.mergeTrains(train2);
		assertEquals(train1.getNumberOfCars(), 7);
		assertEquals(train2.getNumberOfCars(), 0);
		try {
			train1.addCars(null);
		} catch(IllegalArgumentException e) {}
	}

	@Test
	void testGetTotalWeightOfCars() {
		Train train = new Train("my train", 98.32);;
		assertEquals(train.getTotalWeightOfCars(), 0);
		int[] cars = new int[] {3,2,3};
		train.addCars(cars);
		assertEquals(train.getTotalWeightOfCars(), 8);
		train.addCars(cars);
		assertEquals(train.getTotalWeightOfCars(), 16);
	}

	@Test
	void testGetNumberOfCars() {
		Train train = new Train("my train", 98.32);;
		assertEquals(train.getNumberOfCars(), 0);
		int[] cars = new int[] {3,2,3};
		train.addCars(cars);
		assertEquals(train.getNumberOfCars(), 3);
	}

	@Test
	void testGetMaxSpeed() {
		Train train = new Train("my train", 98.32);;
		assertEquals(train.getMaxSpeed(), 98.32);
		int[] cars = new int[] {3,2,3};
		train.addCars(cars);
		assertEquals(train.getMaxSpeed(), 90.32);
		train.setLocPower(1000);
		assertEquals(train.getMaxSpeed(), 150);
	}


}
