/**
 * 
 */
package lab6;

import java.util.Arrays;

/**
 * @author takes
 * This class tests GeometricShape and Billboard class.
 * 
 */
public class GeometricShapeTester {
	public static void main(String[] args) {
		// Create an array of geometricShape and sort it.
		GeometricShape[] shapes = new GeometricShape[4];
		
		GeometricShape r1 = new RectangleShape(8, 4, false);
		GeometricShape r2 = new RectangleShape(3, 6, true);
		GeometricShape r3 = new RectangleShape(15, 8, false);
		GeometricShape r4 = new RectangleShape(4, 2, true);
		shapes[0] = r1;
		shapes[1] = r2;
		shapes[2] = r3;
		shapes[3] = r4;
		
		System.out.println("------ Before Sorting By Area -----");
		
		for (int i = 0; i < shapes.length; i++) {
			System.out.println(shapes[i].toString());
			System.out.println();
		}
		Arrays.sort(shapes);
		
		System.out.println("------ Sorted -----");
		
		for (int i = 0; i < shapes.length; i++) {
			System.out.println(shapes[i].toString());
			System.out.println();
		}
		
		// Create another array of ASCIIDrawable objects and draw them as ASCII
		ASCIIDrawable[] arr = new ASCIIDrawable[shapes.length + 3];
		for (int i = 0; i < shapes.length; i++) {
			arr[i] = shapes[i];
		}
		
		Billboard b1 = new Billboard("Hello World!");
		Billboard b2 = new Billboard("My Message");
		Billboard b3 = new Billboard("Displaying long message...");
		arr[4] = b1;
		arr[5] = b2;
		arr[6] = b3;
		
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i].drawAsASCII());
			System.out.println();
		}
		
		// Sort the GeometricShape array by their perimeters descendingly
		System.out.println("------ Before Sorting By Perimeter -----");
		
		for (int i = 0; i < shapes.length; i++) {
			System.out.println(shapes[i].toString());
			System.out.println();
		}
		
		Arrays.sort(shapes, new PerimeterComparator());
		
		System.out.println("------ Sorted Descendingly -----");
		
		for (int i = 0; i < shapes.length; i++) {
			System.out.println(shapes[i].toString());
			System.out.println();
		}
		
	}
}
