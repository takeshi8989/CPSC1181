package lab7;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Coin extends Group {
	private Circle body;
	private Rectangle line;
	private final double COIN_SIZE = 30;
	private final double LINE_WIDTH = 10;
	private final double LINE_HEIGHT = 40;
	
	public Coin(double x, double y) {
		this.body = new Circle(x, y, COIN_SIZE);
		body.setFill(Color.YELLOW);
		body.setStroke(Color.BLACK);
		this.line = new Rectangle(x - LINE_WIDTH/2, y-(COIN_SIZE*2 - LINE_HEIGHT), LINE_WIDTH, LINE_HEIGHT);
		line.setFill(Color.GOLD);
		this.getChildren().addAll(body, line);
	}
}
