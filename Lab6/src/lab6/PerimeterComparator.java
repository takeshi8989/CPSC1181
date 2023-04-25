/**
 * 
 */
package lab6;

import java.util.Comparator;

/**
 * @author takes
 * This class implements Comparator interface that compares two object
 * It has compare method to compare GeometricShape objects by their perimeters
 */
public class PerimeterComparator implements Comparator<GeometricShape> {

	/**
	 * compare method to compare two GeometricShape objects by perimeter
	 * @return compared value
	 */
	@Override
	public int compare(GeometricShape o1, GeometricShape o2) {
		return Integer.compare(o2.getPerimeter(), o1.getPerimeter());
	}
	
}
