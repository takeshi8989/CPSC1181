package lab9;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


/**
 * Coin class which has size, speed, x, y and can free fall.
 * @author Takeshi Hashimoto
 */
public class Coin extends Group implements Fallable {
	private Circle body;
	private Rectangle line;
	private double coinSize = 30;
	private double lineWidth = 10;
	private double lineHeight = 40;
	private double speed;
	
	public Coin(double x, double y) {
		this(x, y, 1, 1);
	}
	
	public Coin(double x, double y, double scale, double speed) {
		this.speed = Math.random() * speed + 1;
		this.body = new Circle(x, y, coinSize*scale);
		body.setFill(Color.YELLOW);
		body.setStroke(Color.BLACK);
		this.line = new Rectangle(x - lineWidth*scale/2, y-(coinSize*scale*2 - lineHeight*scale), 
									lineWidth*scale, lineHeight*scale);
		line.setFill(Color.GOLD);
		line.setRotate(Math.random() * 60 - 30);
		body.setUserData(this);
		line.setUserData(this);
		this.setUserData(this);
		this.getChildren().addAll(body, line);
	}
	
	public double getSpeed() {
		return this.speed;
	}
}
