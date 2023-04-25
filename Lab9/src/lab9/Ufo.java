package lab9;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

/**
 * Ufo class that moves horizontally and change speed and direction when the ufo hits the bound
 * @author Takeshi Hashimoto
 *
 */

public class Ufo extends Group implements Walkable{
	private double initialX;
	private Arc top;
	private Polygon body;
	private Circle[] circles;
	private final double SIZE = 50;
	private double speed = Math.random() + 2;
	
	public Ufo(double x, double y) {
		this.initialX = x;

		this.top = new Arc(x, y, SIZE, SIZE-20, 0, 180);
		top.setFill(Color.GRAY);
		this.body = new Polygon(x-SIZE, y, x+SIZE, y , x+SIZE*1.2, y+SIZE*0.3, x-SIZE*1.2, y+SIZE*0.3);
		body.setFill(Color.BLUE);
		
		this.circles = new Circle[] {
				new Circle(x-SIZE+10, y+SIZE/3, SIZE/5),
				new Circle(x-SIZE/3, y+SIZE/3, SIZE/5),
				new Circle(x+SIZE/3, y+SIZE/3, SIZE/5),
				new Circle(x+SIZE-10, y+SIZE/3, SIZE/5)
		};
		for(Circle c: circles) c.setFill(Color.YELLOW);
		this.getChildren().addAll(circles);
		this.getChildren().addAll(top, body);
	}
	
	public double getInitialX() {
		return this.initialX;
	}
	
	public double getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(double x) {
		this.speed = x + Math.random();
	}
}
