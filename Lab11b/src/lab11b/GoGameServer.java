package lab11b;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
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



public class GoGameServer extends Application {
	
	private static final int SCENE_WIDTH = 500; 
	private static final int SCENE_HEIGHT = 580;
	private static final int BOARD_LENGTH = 450;
	private static final int NUMBER_OF_ROWS = 9;
	private static final int SQUARE_LENGTH = BOARD_LENGTH / NUMBER_OF_ROWS;  // 50
	private final static int STONE_RADIUS = 15;

	Pane root;
	DrawBoard board;
	
	private boolean[][] added = new boolean[NUMBER_OF_ROWS][NUMBER_OF_ROWS];
	private static Ellipse[][] stones = new Ellipse[NUMBER_OF_ROWS][NUMBER_OF_ROWS];
	
	private Text timeViolations;
	private int noOfTimeViolations = 0;
	
	private final int REFRESH_LIMIT = 10; // 10/60 seconds
	private int refreshCountDown = REFRESH_LIMIT;
	private Refresh refreshCount;
	
	private static TextField textField;
	private Button sitBtn;
	
	private static Text youText;
	private static Text oppText;

	private String color = "Black";
	private static GoGameServerService service;
	private static boolean yourTurn = true;
	private static boolean gameStart = false;
	private double violationTimer = 0; 
	private static boolean readyClient = false;
	private static boolean readyServer = false;
	
	@Override
	public void start(Stage primaryStage) {
		service = new GoGameServerService();
		Thread thread = new Thread(service);
		thread.start();
		
		
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
			if(gameStart && yourTurn) violationTimer += 1.0/60;
			if(violationTimer >= 5) {
				violatePlayer();
			}
			
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
		
		public void violatePlayer() {
			noOfTimeViolations++;
			violationTimer = 0;
			timeViolations.setText("Time Violations: " + noOfTimeViolations);
		}
		
	}
	
	private class setUsernameListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {

			try {
				service.setServerPlayerName();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
	}
	
	private class cellClickedListener implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent e) {
			if(!readyClient || !readyServer) return;
			if(!yourTurn) return;
			
			gameStart = true;
			try {
				service.serverPlay(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
	}

	
	public static void main(String[] args) {
		Application.launch(args);
	
	}
	
	
	private static class GoGameServerService implements Runnable {
		private Socket client;
		private DataInputStream in;
		private DataOutputStream out;
		
		public GoGameServerService() {
			
		}

		@Override
		public void run() {
			try {
				ServerSocket server = new ServerSocket(1181);
				System.out.println("Waiting for client to connect...");
			
				while(true) {
					try {
						Socket client = server.accept();
						this.client = client;
						in = new DataInputStream(client.getInputStream());
						out = new DataOutputStream(client.getOutputStream());
						System.out.println("Client connected.");
						
						while(true) {
							String[] command = in.readUTF().split(" ");
							String commandType = command[0];
							System.out.println(command);
							switch(commandType) {
								case "PLAYER": 
									setClientPlayerName(command[1]);
									break;
								case "PLAY":
									clientPlay(command[1], command[2]);
							}
						}

					} catch(IOException e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		public void setClientPlayerName(String name) {
			oppText.setText("Opponent name: " + name);
			readyClient = true;
		}
		
		public void setServerPlayerName() throws IOException {
			String name = textField.getText().split(" ")[0];
			youText.setText("Your name: " + name);
			textField.setText("");
			out.writeUTF("PLAYER " + name);
			readyServer = true;
		}
		
		public void serverPlay(MouseEvent e) throws IOException {
			double x = e.getX();
			double y = e.getY();
			boolean emptyCell = putStone(x, y, 0);
			
			if(emptyCell) {
				out.writeUTF("PLAY " + x + " " + y);
				yourTurn = false;
			}
		}
		
		public void clientPlay(String mouseX, String mouseY) {
			putStone(Double.parseDouble(mouseX), Double.parseDouble(mouseY), 1);
			yourTurn = true;
		}
		
		public boolean putStone(double mouseX, double mouseY, int color) {
			// 0 -> black(server), 1 -> white(client)
			int row = (int)(mouseY / SQUARE_LENGTH);
			int col = (int)(mouseX / SQUARE_LENGTH);
			if(stones[row][col] != null) return false;
			double offset = SQUARE_LENGTH / 2.0;
			double x = col*SQUARE_LENGTH + offset;
			double y = row*SQUARE_LENGTH + offset;
			Ellipse stone = new Ellipse(x, y, STONE_RADIUS, STONE_RADIUS);
			if(color == 1) stone.setFill(Color.WHITE);
			stones[row][col] = stone;
			return true;
		}
		
	}
}

