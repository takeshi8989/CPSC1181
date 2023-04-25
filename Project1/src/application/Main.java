package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	private Pane root;
	private TextField messageTextField;
	private Text message;
	private CheckBox boldCheckBox;
	@Override
	public void start(Stage primaryStage) {
		try {
			root = new Pane();
			Rectangle ground = createRectangle(0, 300, 300, 100, Color.DARKGREEN, null);
			
			Tree t1 = new Tree(50, 320);
			
			Tree t2 = new Tree(150, 340);
			
			root.getChildren().addAll(ground, t1, t2);
			// addRainbow(root, 230);
			
			
			// message
			message = new Text(25, 50, "Hello!!");
			messageTextField = new TextField("Hello!");
			
			Button updateMsgBtn = new Button("Update message");
			updateMsgBtn.setLayoutX(30);
			updateMsgBtn.setLayoutY(50);
			updateMsgBtn.setOnAction(new MessageEventHandler());
			
			// check box
			boldCheckBox = new CheckBox("Bold");
			boldCheckBox.setLayoutX(100);
			boldCheckBox.setLayoutY(100);
			
			root.getChildren().addAll(message, messageTextField, updateMsgBtn, boldCheckBox);
			
			Scene scene = new Scene(root,300,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("FX Trees");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private class MessageEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			message.setText(messageTextField.getText());
			if(boldCheckBox.isSelected()) {
				message.setFont(Font.font("Arial", FontWeight.BOLD, 72));
			}
			
		}

	}
	
	
	private class Tree extends Group {
		private Rectangle trunk;
		private Ellipse leaves;
		
		public Tree(int x, int y) {
			trunk = new Rectangle(x-10, y-100, 20, 100);
			trunk.setFill(Color.SADDLEBROWN);
			
			leaves = new Ellipse(x, y-100, 40, 60);
			leaves.setFill(Color.rgb(30, 120, 80));
			
			this.getChildren().addAll(trunk, leaves);
			
		}
	}
	
	public Rectangle createRectangle(double x, double y, double w, double h, Color fill, Color stroke) {
		Rectangle r = new Rectangle(x, y, w, h);
		r.setFill(fill);
		r.setStroke(stroke);
		return r;
	}
	
	public Ellipse createEllipse(double x, double y, double xr, double yr, Color fill, Color stroke) {
		Ellipse e = new Ellipse(x, y, xr, yr);
		e.setFill(fill);
		e.setStroke(stroke);
		return e;
	}
	
	public void addRainbow(BorderPane root, double height) {
		Color[] colors = new Color[] {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.VIOLET, Color.PURPLE, Color.WHITE};
		for (int i = 0; i < colors.length; i++) {
			Ellipse e = createEllipse(150, height, 200, 120, colors[i], null);
			root.getChildren().add(e);
			height += 5;
			
		}
	}
}
