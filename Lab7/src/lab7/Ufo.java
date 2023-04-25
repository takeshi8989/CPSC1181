package lab7;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Ufo extends Group{
	private Arc top;
	private Polygon body;
	private Circle[] circles;
	private final double SIZE = 50;
	public Ufo(double x, double y) {
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
}
