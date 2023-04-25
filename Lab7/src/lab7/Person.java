package lab7;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Person extends Group{
	private Circle head;
	private Line body;
	private Line[] limbs;
	
	public Person(double x, double y, double height) {
		this.head = new Circle(x, y, height/5);
		this.body = new Line(x, y, x, y+height);
		this.limbs = new Line[] {
				new Line(x, y+height/2, x+height/2, y+height), 
				new Line(x, y+height/2, x-height/2, y+height),
				new Line(x, y+height, x+height/2, y+height*2), 
				new Line(x, y+height, x-height/2, y+height*2)
		};
		
		setStroke((int)height / 10);
		
		this.getChildren().addAll(head, body);
		this.getChildren().addAll(limbs);
	}
	
	private void setStroke(int width) {
		body.setStrokeWidth(width);
		for(Line limb: limbs) {
			limb.setStrokeWidth(width);
		}
	}

}
