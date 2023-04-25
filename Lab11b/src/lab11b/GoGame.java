package lab11b;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;



public class GoGame extends Application {
	
	private static final int SCENE_WIDTH = 500; 
	private static final int SCENE_HEIGHT = 580;
	private static final int BOARD_LENGTH = 450;
	private static final int NUMBER_OF_ROWS = 9;
	private static final int SQUARE_LENGTH = BOARD_LENGTH / NUMBER_OF_ROWS;  // 50
	private final int STONE_RADIUS = 15;

	Pane root;
	DrawBoard board;
	
	private boolean[][] added = new boolean[NUMBER_OF_ROWS][NUMBER_OF_ROWS];
	private Ellipse[][] stones = new Ellipse[NUMBER_OF_ROWS][NUMBER_OF_ROWS];
	
	private Text timeViolations;
	private int noOfTimeViolations = 0;
	
	private final int REFRESH_LIMIT = 10; // 10/60 seconds
	private int refreshCountDown = REFRESH_LIMIT;
	private Refresh refreshCount;
	
	private TextField textField;
	private Button sitBtn;
	
	private Text youText;
	private Text oppText;

	private String color = "Black";
	
	@Override
	public void start(Stage primaryStage) {
		
		// Initialize all the stones to null;
		for(int row = 0; row < NUMBER_OF_ROWS; row++) {
			for(int col = 0; col < NUMBER_OF_ROWS; col++) {
				stones[row][col] = null;
			}
		}
		
		root = new Pane();
		drawAll();
	
		Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

		// Set up fresh and start
		refreshCount = new Refresh();
		refreshCount.start();
		
		primaryStage.setTitle("Go Game");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}


	/** Draw everything on the interface
	 * 
	 */
	public void drawAll() {
		
		// Set up the top
		Text playerColorText = new Text(color);
		playerColorText.setFont(Font.font(20));
		
		HBox playerColorTextBox = new HBox(playerColorText);
		
		timeViolations = new Text("Time Violations: " + noOfTimeViolations);	
		youText = new Text("Your name: ");
		HBox playerInfo = new HBox(BOARD_LENGTH / 2, timeViolations, youText);		
		
		board = new DrawBoard();
		
		// Layout text field and sit button
		textField = new TextField("Enter your name");	
		sitBtn = new Button("Sit");
		sitBtn.setOnAction(new setUsernameListener());
	
		oppText = new Text("Opponent name: ");
		HBox bottom = new HBox(10, textField, sitBtn, oppText);
		HBox.setMargin(oppText, new Insets(0, 0, 0, 100));
		
		VBox box = new VBox(10, playerColorTextBox, playerInfo, board, bottom);
		
		playerColorTextBox.setAlignment(Pos.CENTER);

		root.getChildren().addAll(box);
		
		root.setLayoutX(23);
			
	}
	
	/* Draw the board with squares
	 * 
	 */
	
	private class DrawBoard extends Group {
		
		public DrawBoard() {

			// Draw rows and columns of stones
			for(int row = 0; row < NUMBER_OF_ROWS; row++) {
				for(int col = 0; col < NUMBER_OF_ROWS; col++) {
					Rectangle r = new Rectangle(col * SQUARE_LENGTH, row * SQUARE_LENGTH, SQUARE_LENGTH, SQUARE_LENGTH);
					r.setFill(Color.GREEN);
					r.setStroke(Color.BLACK);
					r.setOnMouseClicked(new cellClickedListener());
					
					this.getChildren().add(r);
					
				}
			}	
		}
	}
	
	
	
	/* Every 1/6 seconds, the system refreshes to add new stones to the board if any
	 * 
	 */
	private class Refresh extends AnimationTimer {
		
		@Override
		public void handle(long arg0) {
		
			refreshCountDown--;
			
			if(refreshCountDown == 0) {
			
				refreshCountDown = REFRESH_LIMIT;
			
				// Add the new stones to the board
				for(int row = 0; row < NUMBER_OF_ROWS; row++) {
					for(int col = 0; col < NUMBER_OF_ROWS; col++) {
						if(stones[row][col] != null && !added[row][col]) {
							board.getChildren().add(stones[row][col]);
							added[row][col] = true;
						}
					}
				}
			}
		}
		
	}
	
	private class setUsernameListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			youText.setText("Your name: " + textField.getText());
			textField.setText("");
			// set opponent name
		}
	}
	
	private class cellClickedListener implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent e) {
			int row = (int)(e.getY() / SQUARE_LENGTH);
			int col = (int)(e.getX() / SQUARE_LENGTH);
			double offset = SQUARE_LENGTH / 2.0;
			Ellipse stone = new Ellipse(col*SQUARE_LENGTH+offset, row*SQUARE_LENGTH+offset, STONE_RADIUS, STONE_RADIUS);
			stones[row][col] = stone;
		}
		
	}

	
	public static void main(String[] args) {
		Application.launch(args);
		
	
	}
}

