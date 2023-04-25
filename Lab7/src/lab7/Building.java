package lab7;


import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Building extends Group {
	private Body body;
	
	public Building(double x, double y, double w, double h, Color c) {
		this.body = new Body(x, y, w, h, c);
		this.getChildren().add(body);
	}
	
	public Building() {
		this(0, 0, 0, 0, Color.BLACK);
	}
	
	private class Body extends Group {
		private Rectangle frame;
		public Body(double x, double y, double w, double h, Color c) {
			this.frame = new Rectangle(x, y, w, h);
			this.frame.setFill(c);
			this.getChildren().addAll(frame);
		}

		
		public Rectangle getFrame() {
			return frame;
		}
		
		public void arrangeWindows() {
			int numOfWindows = frame.getWidth() > 200 ? 5 : 4;
			double margin = 10;
			
			double windowSize = (frame.getWidth() - margin * (numOfWindows+1)) / (double)numOfWindows;
			double posX = frame.getX() + margin;
			double posY = frame.getY() + windowSize;
			
			while(posY < frame.getY() + frame.getHeight() / 1.5) {
				while(posX + windowSize < frame.getX() + frame.getWidth()) {
					Rectangle window = new Rectangle(posX, posY, windowSize, windowSize);
					window.setFill(Color.GRAY);
					this.getChildren().addAll(window);
					posX += windowSize + margin;
				}
				posX = frame.getX() + margin;
				posY += windowSize * 2;
			}
			
			
		}
	}
	
	public void addWindows() {
		body.arrangeWindows();
	}
	
	public double getHeight() {
		return body.getFrame().getHeight();
	}
	
	public void setX(double x) {
		body.getFrame().setX(x);
	}
	
	public void setY(double y) {
		body.getFrame().setY(y);
	}
	
	public void setWidth(double w) {
		body.getFrame().setWidth(w);
	}
	
	public void setHeight(double h) {
		body.getFrame().setHeight(h);
	}
	
	public void setColor(Color color) {
		body.getFrame().setFill(color);
	}
}