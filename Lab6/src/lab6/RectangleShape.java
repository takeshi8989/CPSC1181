/**
 * 
 */
package lab6;

/**
 * @author takes
 * This class inherit GeometricShape class.
 * It has a width and height.
 */
public class RectangleShape extends GeometricShape {
	private int width;
	private int height;
	
	/**
	 * Constructor with width, height, filledIn
	 * If width or height are negative value, throw an exception
	 * @param w
	 * @param h
	 * @param filledIn
	 */
	public RectangleShape(int w, int h, boolean filledIn) {
		super(filledIn);
		if(w < 0 || h < 0) {
			throw new IllegalArgumentException("Rentangle cannot accept negative arguments");
		}
		this.width = w;
		this.height = h;
	}
	
	/**
	 * getter for width
	 * @return width
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * setter for width
	 * @param w
	 */
	public void setWidth(int w) {
		this.width = w;
	}
	
	/**
	 * getter for height
	 * @return height
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * setter for height
	 * @param h
	 */
	public void setHeight(int h) {
		this.height = h;
	}
	
	/**
	 * override from super class
	 * This method returns the area of the Rectangle
	 * @return area
	 */
	@Override
	public int getArea() {
		return this.width * this.height;
	}

	/**
	 * override from super class
	 * This method returns the perimeter of the Rectangle
	 * @return perimeter
	 */
	@Override
	public int getPerimeter() {
		return 2 * (this.width + this.height);
	}

	/**
	 * override from super class
	 * This method draws the object as ASCII
	 * @return ASCII object
	 */
	@Override
	public String drawAsASCII() {
		String shape = "";
		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) {
				if(!this.filledIn && !(i == 0 || i == this.height-1 || j == 0 || j == this.width-1))
					shape += " ";
				else
					shape += "#";
			}
			shape += "\n";
		}
		return shape;
	}
	
	/**
	 * toString method including the width, the height, the area, the perimeter of the Rectangle
	 * @return str
	 */
	public String toString() {
		String str = "width: " + this.width;
		str += "\nheight: " + this.height;
		str += "\narea: " + this.getArea();
		str += "\nperimeter: " + this.getPerimeter();
		return str;
	}

}
