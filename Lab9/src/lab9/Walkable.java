package lab9;

/**
 * Interface for walkable objects such as people and ufos(moving horizontally)
 * @author Takeshi Hashimoto
 *
 */
public interface Walkable {
	public double getInitialX();
	
	public double getSpeed();
	
	public void setSpeed(double x);
	
	public double getLayoutX();
	
	public void setLayoutX(double x);
}
