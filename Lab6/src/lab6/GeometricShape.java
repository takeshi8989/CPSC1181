/**
 * 
 */
package lab6;
import java.util.*;

/**
 * @author takes
 * 
 * This abstract class implements Comparable and ASCIIDrawable interfaces.
 * the object has member variable called filledIn as boolean value
 * unimplemented methods are getArea, getPerimeter, drawAsASCII
 *
 */
public abstract class GeometricShape implements Comparable<GeometricShape>, ASCIIDrawable {
	public boolean filledIn;
	
	/**
	 * Constructor with filled in or not
	 * @param f
	 */
	public GeometricShape(boolean f) {
		this.filledIn = f;
	}
	
	/**
	 * No argument constructor. default filledIn value is true
	 */
	public GeometricShape() {
		this(true);
	}
	
	/**
	 * getter for filledIn
	 * @return filled in
	 */
	public boolean getFilledIn() {
		return this.filledIn;
	}
	
	/**
	 * setter for filledIn
	 * @param f
	 */
	public void setFilledIn(boolean f) {
		this.filledIn = f;
	}
	
	/**
	 * this method returns the area of the shape
	 * @return area
	 */
	public abstract int getArea();
	
	/**
	 * this method returns the perimeter of the shape
	 * @return perimeter
	 */
	public abstract int getPerimeter();
	
	/**
	 * Comparator method that compares by areas of shapes
	 * @return compared value
	 */
	public int compareTo(GeometricShape other) {
		return Integer.compare(this.getArea(), other.getArea());
	}
	
	/**
	 * This method returns Shape of the GeometricShape object as ASCII
	 * @return shape
	 */
	public abstract String drawAsASCII();
}
