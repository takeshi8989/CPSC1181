/**
 * 
 */
package lab3;

import java.util.Arrays;

/**
 * @author Takeshi Hashimoto
 * Train Class
 * An object has name, locomotive power, freight cars
 * freight cars contain their weights as an array
 * freight cars can be added, and removed.
 * this can merge other train
 */
public class Train {
	private String name;
	private double locPower;
	private int[] freightCars;
	
	/**
	 * Constructor
	 * @param name
	 * @param locPower
	 * freight cars is set to empty string of integer
	 */
	public Train(String n, double l) {
		this.name = n;
		this.locPower = l;
		this.freightCars = new int[0];
	}
	
	/**
	 * This method returns its name
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * This method set name
	 * @param newName
	 */
	public void setName(String newName) {
		this.name = newName;
	}
	
	/**
	 * This method returns its locomotive power
	 * @return locPower
	 */
	public double getLocPower() {
		return this.locPower;
	}
	
	/**
	 * This method set its power
	 * power must be positive value
	 * @param newPower
	 */
	public void setLocPower(double newPower) {
		if(newPower < 0) {
			throw new IllegalArgumentException("Locomotive power cannot be negative");
		}
		this.locPower = newPower;
	}
	
	/**
	 * This method returns its freight cars
	 * returns new array of them with same values
	 * @return freight cars
	 */
	public int[] getFreightCars() {
		return Arrays.copyOf(this.freightCars, this.freightCars.length);
	}
	
	/**
	 * This method adds cars to back of its freight cars
	 * weights must not be null
	 * @param weights
	 */
	public void addCars(int... weights) {
		if(weights == null) {
			throw new IllegalArgumentException("cars must exist");
		}
		int[] newCars = new int[this.getNumberOfCars() + weights.length];
		int i = 0;
		for(; i < this.getNumberOfCars(); i++) {
			newCars[i] = this.freightCars[i];
		}
		for(; i < newCars.length; i++) {
			newCars[i] = weights[i - this.getNumberOfCars()];
		}
		this.freightCars = newCars;
	}
	
	/**
	 * This method removes all cars of this train
	 */
	public void removeAll() {
		this.freightCars = new int[0];
	}
	
	/**
	 * This method merges other train to this train
	 * the number of cars of this train is added, other train's cars are removed
	 * @param other
	 */
	public void mergeTrains(Train other) {
		if(other == null) {
			throw new IllegalArgumentException("cannot merge null object");
		}
		this.addCars(other.getFreightCars());
		other.removeAll();
	}
	
	/**
	 * This method returns the total weight of freight cars
	 * @return total weight of cars
	 */
	public int getTotalWeightOfCars() {
		int sum = 0;
		for(int i = 0; i < this.freightCars.length; i++) {
			sum += this.freightCars[i];
		}
		return sum;
	}
	
	/**
	 * This method returns the number of cars the train has
	 * @return the number of freight cars
	 */
	public int getNumberOfCars() {
		return this.freightCars.length;
	}
	
	/**
	 * This method returns its max speed calculated by (maxSpeed = locPower - totalWeight)
	 * the limit of its max speeed is 150
	 * @param the max speed
	 */
	public double getMaxSpeed() {
		int totalWeight = this.getTotalWeightOfCars();
		double maxSpeed = this.locPower - totalWeight;
		if(maxSpeed > 150) maxSpeed = 150;
		if(maxSpeed < 0) maxSpeed = 0;
		return maxSpeed;
	}
	
	/**
	 * this method returns the information of the train
	 * name, locomotive power, max speed, total weight, number of trains, and weight of each freight cars
	 * @return string information
	 */
	public String toString() {
		String str = "Train Information\n\n";
		str += "Name: " + this.getName() + "\n";
		str += "Locomotive power: " + this.getLocPower() + "\n";
		str += "Its max speed: " + this.getMaxSpeed() + "\n";
		str += "Its total weight: " + this.getTotalWeightOfCars() + "\n";
		str += "The train has " + this.getNumberOfCars() + " cars\n";
		str += "Freight car weights:\n[";
		for(int i = 0; i < this.getNumberOfCars(); i++) {
			str += this.freightCars[i];
			if(i < this.freightCars.length - 1) str += ", ";
		}
		str += "]\n";
		return str;
	}
}
