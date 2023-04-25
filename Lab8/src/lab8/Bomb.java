package lab8;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

public class Bomb extends Group {
	private Circle body;
	private Arc fuse;
	private Ellipse fire;
	private final double BOMB_SIZE = 30;
	
	public Bomb(double x, double y) {
		setBody(x, y);
		setFuse(x, y);
		setFire(x, y);
		this.getChildren().addAll(body, fuse, fire);
	}
	
	private void setBody(double x, double y) {
		this.body = new Circle(x, y, BOMB_SIZE);
		this.body.setFill(Color.BLACK);
	}
	
	private void setFuse(double x, double y) {
		this.fuse = new Arc(x+BOMB_SIZE, y-BOMB_SIZE/2, BOMB_SIZE, BOMB_SIZE, 90, 90);
		fuse.setType(ArcType.OPEN);
		fuse.setFill(Color.TRANSPARENT);
		fuse.setStrokeWidth(BOMB_SIZE/10);
		fuse.setStroke(Color.rgb(200, 100, 100));
	}
	
	private void setFire(double x, double y) {
		this.fire = new Ellipse(x+BOMB_SIZE, y-BOMB_SIZE*1.5, 5, 10);
		fire.setFill(Color.RED);
		fire.setRotate(75);
		
	}
	
	
}
