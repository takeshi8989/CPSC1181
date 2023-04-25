package midterm;

import java.util.ArrayList;

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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class GoGame extends Application {
	
	private final int SCENE_WIDTH = 1015;
	private final int SCENE_HEIGHT = 580;
	private final int BOARD_LENGTH = 450;
	private final int NUMBER_OF_ROWS = 9;
	private final int SQUARE_LENGTH = BOARD_LENGTH / NUMBER_OF_ROWS;  // 50
	private final int STONE_RADIUS = 15;
	
	private Text blackViolationsText;
	private int blackViolationsCount = 0;
	private Text blackPlayerNameText;
	private String blackPlayerName = "";
	private TextField blackTextField;
	private Button blackBtn;
	private Board blackBoard;
	
	private Text whiteViolationsText;
	private int whiteViolationsCount = 0;
	private Text whitePlayerNameText;
	private String whitePlayerName = "";
	private TextField whiteTextField;
	private Button whiteBtn;
	private Board whiteBoard;
	

	private Pane root;
	private String currentTurn = "black";
	private double gameTimer = 0;
	private AnimationTimer violationTimer;
	private boolean isGameStart = false;
	private boolean blackSitDown = false;
	private boolean whiteSitDown = false;
	
	@Override
	public void start(Stage primaryStage) {

		root = new Pane();
		
		// black
		Text black = new Text("Black");
		black.setFont(Font.font(30));
		blackViolationsText = new Text("Time Violations: " + blackViolationsCount);
		blackPlayerNameText = new Text("Player: " + blackPlayerName);
		HBox blackPlayerInfo = new HBox(BOARD_LENGTH - 200, blackViolationsText, blackPlayerNameText);
		blackBoard = new Board("black");
		blackTextField = new TextField("");
		
		
		blackTextField.setMaxWidth(150);
		blackBtn = new Button("Sit");
		blackBtn.setOnAction(new setblackPalyerNameListner());
		HBox blackTextFieldBox = new HBox(10, blackTextField, blackBtn);
		
		
		VBox blackLayout = new VBox(10, black, blackPlayerInfo, blackBoard, blackTextFieldBox);
		blackLayout.setAlignment(Pos.CENTER);
		
		
		// white
		Text white = new Text("White");
		white.setFont(Font.font(30));
		whiteViolationsText = new Text("Time Violations: " + whiteViolationsCount);
		whitePlayerNameText = new Text("Player: " + whitePlayerName);
		HBox whitePlayerInfo = new HBox(BOARD_LENGTH - 200, whiteViolationsText, whitePlayerNameText);
		whiteBoard = new Board("white");
		
		whiteTextField = new TextField("");
		whiteTextField.setMaxWidth(150);
		whiteBtn = new Button("Sit");
		whiteBtn.setOnAction(new setwhitePalyerNameListner());
		HBox whiteTextFieldBox = new HBox(10, whiteTextField, whiteBtn);
		
		VBox whiteLayout = new VBox(10, white, whitePlayerInfo, whiteBoard, whiteTextFieldBox);
		whiteLayout.setAlignment(Pos.CENTER);
		
		
		
		HBox boards = new HBox(30, blackLayout, whiteLayout);
		boards.setPadding(new Insets(0, 0, 30, 30));
		
		violationTimer = new ViolationTimer();
		violationTimer.start();
		
		root.getChildren().addAll(boards);
		
		
		Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
		primaryStage.setTitle("Go Game");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	// events
	private class setblackPalyerNameListner implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			blackPlayerName = blackTextField.getText();
			blackPlayerNameText.setText("Player: " + blackPlayerName);
			blackSitDown = blackPlayerName.length() > 0;
			
		}
	}
	
	private class setwhitePalyerNameListner implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			whitePlayerName = whiteTextField.getText();
			whitePlayerNameText.setText("Player: " + whitePlayerName);
			whiteSitDown = whitePlayerName.length() > 0;
		}
	}
	
	private class ClickCellListner implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent e) {
			if(!blackSitDown || !whiteSitDown) return;
			isGameStart = true;
			Cell c = (Cell)(e.getSource());
			if(!c.getColor().equals(currentTurn))  return;
			 if(c.isStonePut()) return;
			blackBoard.putStone(currentTurn, c.getI(), c.getJ());
			whiteBoard.putStone(currentTurn, c.getI(), c.getJ());
			if(currentTurn.equals("black")) {
				currentTurn = "white";
			} else {
				currentTurn = "black";
			}
			gameTimer = 0;
		}
		
	}
	
	// animation
	private class ViolationTimer extends AnimationTimer {
		@Override
		public void handle(long arg0) {
			if(!isGameStart) return;
			if(!blackSitDown || !whiteSitDown) return;
			gameTimer += 1/60.0;
			if(gameTimer >= 5) {
				violatePlayer();
				gameTimer = 0;
			}
		}
	}
	
	private void violatePlayer() {
		if(currentTurn.equals("black")) {
			blackViolationsCount++;
			blackViolationsText.setText("Time Violations: " + blackViolationsCount);
		} else {
			whiteViolationsCount++;
			whiteViolationsText.setText("Time Violations: " + whiteViolationsCount);
		}
			
	}
	
	// inner classes
	private class Board extends Group {
		private Cell[][] board;
		
		public Board(String color) {
			board = new Cell[NUMBER_OF_ROWS][NUMBER_OF_ROWS];
			this.getChildren().addAll(createBoard(board, color));
		}
		
		private ArrayList<Cell> createBoard(Cell[][] board, String color) {
			ArrayList<Cell> cells = new ArrayList<>();
			for(int i = 0; i < board.length; i++) {
				for(int j = 0; j < board[i].length; j++) {
					Cell c = new Cell(i, j, color);
					c.setOnMouseClicked(new ClickCellListner());
					board[i][j] = c;
					cells.add(c);
				}
			}
			return cells;
		}
		
		public void putStone(String color, int i, int j) {
			Cell cell = this.board[i][j];
			if(color.equals("white")) {
				cell.setStoneFill(Color.WHITE);
			}
			else if(color.equals("black")) {
				cell.setStoneFill(Color.BLACK);
			}
		}
	}
	
	private class Cell extends Group {
		private Rectangle box;
		private Circle stone;
		private int i;
		private int j;
		private String color;
		private boolean isPut = false;
		public Cell(int i, int j, String color) {
			this.i = i;
			this.j = j;
			this.color = color;
			double x = j * SQUARE_LENGTH;
			double y = i * SQUARE_LENGTH;
			box = new Rectangle(x, y, SQUARE_LENGTH, SQUARE_LENGTH);
			box.setStroke(Color.BLACK);
			box.setFill(Color.GREEN);
			
			
			double circleX = x + SQUARE_LENGTH / 2.0;
			double circleY = y + SQUARE_LENGTH / 2.0;
			stone = new Circle(circleX, circleY, STONE_RADIUS);
			stone.setFill(Color.TRANSPARENT);
			this.getChildren().addAll(box, stone);
		}
		
		public int getI() {
			return this.i;
		}
		
		public int getJ() {
			return this.j;
		}
		
		public void setStoneFill(Color color) {
			this.stone.setFill(color);
			this.isPut = true;
		}
		
		public String getColor() {
			return this.color;
		}
		
		public boolean isStonePut() {
			return this.isPut;
		}
		
	}

}