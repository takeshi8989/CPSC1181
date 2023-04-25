package lab9;
	
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * This JavaFX application is a game to click as much as coins to build buildings.
 * You can build a building if you click 2 coins. However, if you miss or click a bomb,
 * you lose a building.
 * The goal of this game is to build as many buildings as possible. 
 * Getting over 10000 points (100 coins) is good score.
 * 
 * For a bonus part, I added a lot of functionality and design for background. 
 * Coins' speed to fall increase as time passes, also the size of coins will be smaller.
 * 
 * @author Takeshi Hashimoto
 *
 */

public class Main extends Application {
	private Pane root;
	private boolean isGameOver = false;
	private boolean isGameStopped = false;
	private double gameTimer = 0;
	private int totalPoints = 0;
	private int missCoins = 0;
	private Text scoreText;
	private Text timerText;
	private Text missText;
	private Text gameOverText;
	private VBox gameInfoBox;
	private int fallObjInterval = 60;
	private int intervalTimer = 0;
	private double coinMaxSpeed = 1;
	private double coinSize = 1;
	private final int WINDOW_WIDTH = 1000;
	private final int WINDOW_HEIGHT = 600;
	private final int GROUND_HEIGHT = 100;
	private Background bg;
	private ArrayList<Ufo> ufos;
	private ArrayList<Coin> coins;
	private ArrayList<Bomb> bombs;
	private ArrayList<Person> people;
	private AnimationTimer generateFallingObjAnimation;
	private AnimationTimer ufoAnimation;
	private AnimationTimer coinAnimation;
	private Button pauseBtn;
	private Button resetBtn;
	private HBox buttonsBox;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			root = new Pane();
			
			// background items sky, ground, cloud, mountain, buildings	
			// foreground items  people, ufos, bombs, coin
			regenerateObjects();
			
			// Animations
			ufoAnimation = new MoveUfoAnimation();
			ufoAnimation.start();
			coinAnimation = new FallObjectsAnimation();
			coinAnimation.start();
			generateFallingObjAnimation = new GenerateFallableObjectAnimation();
			generateFallingObjAnimation.start();
			
			// Game Info Text
			scoreText = new Text("Your Score: " + totalPoints);
			timerText = new Text("Time: " + gameTimer);
			missText = new Text("You missed " + missCoins + " coins");
			gameInfoBox = new VBox(10, scoreText, timerText, missText);
			gameInfoBox.setLayoutY(20);
			root.getChildren().addAll(gameInfoBox);
	
			// GameOver Text
			gameOverText = new Text("Game Over");
			gameOverText.setFont(Font.font(40));
			double width = gameOverText.getBoundsInLocal().getWidth();
			gameOverText.setX(WINDOW_WIDTH / 2 - width / 2);
			gameOverText.setLayoutY(WINDOW_HEIGHT / 2);
			gameOverText.setVisible(false);
			root.getChildren().add(gameOverText);
			
			// pause, reset Button
			pauseBtn = new Button("Pause");
			pauseBtn.setOnAction(new GameStopListner());
			resetBtn = new Button("Reset");
			resetBtn.setOnAction(new GameResetListner());
			buttonsBox = new HBox(30, pauseBtn, resetBtn);
			buttonsBox.setLayoutX(WINDOW_WIDTH / 2 - 50);
			buttonsBox.setLayoutY(WINDOW_HEIGHT - 50);
			root.getChildren().addAll(buttonsBox);
			
			// Scene
			Scene scene = new Scene(root,WINDOW_WIDTH, WINDOW_HEIGHT);
			primaryStage.setTitle("Click for Buildings Game");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	// Event Listeners
	private class GameStopListner implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			if(isGameOver) return;
			if(isGameStopped) {
				restartAllAnimations();
				pauseBtn.setText("Pause");
			}
			else {
				stopAllAnimations();
				pauseBtn.setText("Restart");
			}
		}
	}
	
	private void stopAllAnimations() {
		isGameStopped = true;
		generateFallingObjAnimation.stop();
		ufoAnimation.stop();
		coinAnimation.stop();
	}
	
	private void restartAllAnimations() {
		isGameStopped = false;
		generateFallingObjAnimation.start();
		ufoAnimation.start();
		coinAnimation.start();
	}
	
	private class GameResetListner implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			resetAll();
		}
	}
	
	private void resetAll() {
		resetGameData();
		regenerateObjects();
		resetBtnsTexts();
		restartAllAnimations();
	}
	
	private void resetGameData() {
		isGameOver = false;
		isGameStopped = false;
		gameTimer = 0;
		totalPoints = 0;
		missCoins = 0;
		scoreText.setText("Your Score: " + totalPoints);
		timerText.setText("Time: " + gameTimer);
		missText.setText("You missed " + missCoins + " coins");;
		gameOverText.setVisible(false);
		fallObjInterval = 60;
		intervalTimer = 0;
		coinMaxSpeed = 1;
		coinSize = 1;
	}
	
	private void regenerateObjects() {
		bg = new Background();
		people = createPeople(8);
		ufos = createUfos(8);
		bombs = createBombs(5);
		coins = createCoins(5);
		root.getChildren().clear();
		root.getChildren().addAll(bg);
		root.getChildren().addAll(people);
		root.getChildren().addAll(ufos);
		root.getChildren().addAll(bombs);
		root.getChildren().addAll(coins);
	}
	
	private void resetBtnsTexts() {
		root.getChildren().addAll(gameInfoBox);
		root.getChildren().add(gameOverText);
		root.getChildren().addAll(buttonsBox);
		pauseBtn.setText("Pause");
	}
	

	private class ClickCoinListner implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent e) {
			if(isGameOver || isGameStopped) return;
			removeClickedObj(e);
			totalPoints += 100;
			scoreText.setText("Your Score: " + totalPoints);
			if(totalPoints % 200 == 0) bg.addBuilding();
		}
	}
	
	private class ClickBombListener implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent e) {
			if(isGameOver || isGameStopped) return;
			removeClickedObj(e);
			bg.removeBuilding();
			
		}
	}
	
	private void removeClickedObj(MouseEvent e) {
		root.getChildren().remove(((Node)e.getTarget()).getUserData());
	}
	
	
	// Animation
	// Horizontal move
	private class MoveUfoAnimation extends AnimationTimer {
		@Override
		public void handle(long arg0) {
			// TODO Auto-generated method stub
			for(Ufo ufo: ufos) {
				objWalk(ufo);
			}
			for(Person person: people) {
				objWalk(person);
			}
		}
	}
	
	private void objWalk(Walkable ufo) {
		boolean isRightBound = ufo.getLayoutX() + ufo.getSpeed() > WINDOW_WIDTH - ufo.getInitialX();
		boolean isLeftBound = ufo.getLayoutX() + ufo.getSpeed() <  -ufo.getInitialX();
		if(isRightBound || isLeftBound) {
			ufo.setSpeed(ufo.getSpeed() * -1);
		}
		ufo.setLayoutX(ufo.getLayoutX() + ufo.getSpeed());
	}
	
	// Vertical Move
	private class FallObjectsAnimation extends AnimationTimer {
		@Override
		public void handle(long arg0) {
			ArrayList<Fallable> fallObjList = new ArrayList<>();
			for(Coin coin: coins) fallObjList.add(coin);
			for(Bomb bomb: bombs) fallObjList.add(bomb);
			fallWindowObj(fallObjList);
		}
	}
	
	private void fallWindowObj(ArrayList<Fallable> list) {
		for(Fallable obj: list) {
			if(obj.getLayoutY() > WINDOW_HEIGHT) {
				if(obj instanceof Coin && root.getChildren().indexOf((Node)obj) != -1) {
					bg.removeBuilding();
					missCoins++;
					missText.setText("You missed " + missCoins + " coins");
				}
				root.getChildren().remove((Node)obj);
			} else {
				obj.setLayoutY(obj.getLayoutY() + obj.getSpeed());
			}
		}
	}
	
	// generate Fallable Objects (coins, bombs)
	private class GenerateFallableObjectAnimation extends AnimationTimer {
		@Override
		public void handle(long arg0) {
			intervalTimer++;
			gameTimer += 1 / 60.0;
			timerText.setText("Time: " + Math.floor(gameTimer * 100) / 100.0);
			if(intervalTimer / fallObjInterval > 10 && fallObjInterval > 30) {
				fallObjInterval -= 5;
				intervalTimer = 0;
			} 
			if(coinSize == 1 && fallObjInterval == 45) coinSize = 0.8;
			coinMaxSpeed = (int)(gameTimer) / 30;
			if(intervalTimer % fallObjInterval == 0) {
				ArrayList<Coin> newCoins = createCoins(1);
				ArrayList<Bomb> newBombs = createBombs(1);
				coins.addAll(newCoins);
				bombs.addAll(newBombs);
				root.getChildren().addAll(newCoins);
				root.getChildren().addAll(newBombs);
			}
		}
	}
	
	
	// create Instances
	public ArrayList<Person> createPeople(int n) {
		ArrayList<Person> peopleList = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			peopleList.add(createPerson());
		}
		return peopleList;
	}
	
	private Person createPerson() {
		double height = Math.random() * 20 + 40;
		double x = Math.random() * WINDOW_WIDTH;
		double y = Math.random() * 20 + WINDOW_HEIGHT - GROUND_HEIGHT / 2 - height*2;
		Person p = new Person(x, y, height);
		return p;
	}
	
	public ArrayList<Ufo> createUfos(int n) {
		ArrayList<Ufo> ufoList = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			double x = Math.random() * WINDOW_WIDTH;
			Ufo ufo = new Ufo(x, 50);
			ufoList.add(ufo);
		}
		return ufoList;
	}
	
	
	private ArrayList<Bomb> createBombs(int n) {
		ArrayList<Bomb> list = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			double x = Math.random() * (WINDOW_WIDTH - 100) + 50;
			double y = Math.random() * 50 + 80;
			Bomb newBomb = new Bomb(x, y);
			newBomb.setOnMouseClicked(new ClickBombListener());
			list.add(newBomb);
		}
		return list;
	}
	
	
	private ArrayList<Coin> createCoins(int n) {
		ArrayList<Coin> list = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			double x = Math.random() * (WINDOW_WIDTH - 100) + 50;
			double y = 80;
			Coin newCoin = new Coin(x, y, coinSize, coinMaxSpeed);
			newCoin.setOnMouseClicked(new ClickCoinListner());
			list.add(newCoin);
		}
		return list;
	}
	
	
	// stop animations, remove all coins and bombs
	// display game over text
	private void gameOver() {
		generateFallingObjAnimation.stop();
		root.getChildren().removeAll(coins);
		root.getChildren().removeAll(bombs);
		isGameOver = true;
		gameOverText.setVisible(true);
	}
	
	
	/**
	 * Background Class which includes sky, ground, mountain, sun, clouds, buildings
	 * It can add or remove buildings from root
	 * @author Takeshi Hashimoto
	 */
	private class Background extends Group {
		private Rectangle sky;
		private Rectangle ground;
		private Mountain mountain;
		private Circle sun;
		private ArrayList<Cloud> clouds;
		private ArrayList<Building> buildings;
		
		/**
		 * Constructor generates objects on the background such as sky and buildings
		 */
		public Background() {
			this.getChildren().add(createRectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT+100, Color.GRAY));
			sky = createRectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, Color.SKYBLUE);
			ground = createRectangle(0, WINDOW_HEIGHT-GROUND_HEIGHT, 
												WINDOW_WIDTH, GROUND_HEIGHT, Color.BROWN);
			sun = new Circle(WINDOW_WIDTH, 0, 80);
			sun.setFill(Color.YELLOW);
			mountain = new Mountain();
			this.getChildren().addAll(sky, sun, mountain, ground);
			
			this.clouds = createClouds();
			this.getChildren().addAll(clouds);
			this.buildings = this.createBuildings(5);
			this.getChildren().addAll(buildings);
		}
		
		private Rectangle createRectangle(int x, int y, int width, int height, Color color) {
			Rectangle rectangle = new Rectangle(x, y, width, height);
			rectangle.setFill(color);
			return rectangle;
		}
		
		private ArrayList<Cloud> createClouds() {
			ArrayList<Cloud> list = new ArrayList<>();
			for(int i = 0; i < 3;i++){
				double x = Math.random() * WINDOW_WIDTH;
				double y = Math.random() * 100;
				list.add(new Cloud(x, y));
			}
			return list;
		}
		
		private Building createBuilding() {
			Random rand = new Random();
			Building building = new Building();
			building.setWidth(Math.random() * 200 + 100);
			building.setHeight(Math.random() * 300 + 100);
			building.setX(Math.random() * WINDOW_WIDTH);
			building.setY(WINDOW_HEIGHT - GROUND_HEIGHT / 2 - building.getHeight());
			building.setColor(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
			building.addWindows();
			return building;
		}
		
		public ArrayList<Building> createBuildings(int n) {
			ArrayList<Building> blds = new ArrayList<>();
			for(int i = 0; i < n; i++) {
				blds.add(createBuilding());
			}
			return blds;
		}
		
		public void addBuilding() {
			Building newBuild = createBuilding();
			this.buildings.add(newBuild);
			this.getChildren().add(newBuild);
		}
		
		public void removeBuilding() {
			if(buildings.size() <= 0) {
				gameOver();
				return;
			}
			int index = this.getChildren().indexOf(buildings.get(buildings.size()-1));
			buildings.remove(buildings.size()-1);
			this.getChildren().remove(index);
		}
		
		
		
		private class Cloud extends Group{
			private ArrayList<Circle> surface;
			private Ellipse body;
			public Cloud(double x, double y) {
				this.body = new Ellipse(x, y, 80, 30);
				body.setFill(Color.WHITE);
				this.surface = createCloudSurface(x, y);
				this.getChildren().addAll(body);
				this.getChildren().addAll(surface);
			}
			
			private ArrayList<Circle> createCloudSurface(double x, double y) {
				ArrayList<Circle> clouds = new ArrayList<>();
				for(int i = 0; i < 10;i++){
					double randX = Math.random() * 160 - 80 + x;
					double randY = Math.random() * 40 - 20 + y;
					Circle cloud = new Circle(randX, randY, 30);
					cloud.setFill(Color.WHITE);
					clouds.add(cloud);
				}
				return clouds;
			}
		}
		
		private class Mountain extends Group {
			private Polygon body;
			private ArrayList<Tree> trees;
			public Mountain() {
				this.body = new Polygon(0, WINDOW_HEIGHT,   WINDOW_WIDTH/2, 0,   WINDOW_WIDTH, WINDOW_HEIGHT);
				double offsetX = Math.random() * WINDOW_WIDTH * 2 - WINDOW_WIDTH;
				body.setLayoutX(offsetX);
				body.setFill(Color.GREEN);
				
				
				this.getChildren().addAll(body);
				addTrees(offsetX, 100);
			}
			
			private class Tree extends Group {
				private Rectangle trunk;
				private Ellipse leaves;
				
				public Tree(double x, double y) {
					trunk = new Rectangle(x-5, y-50, 10, 50);
					trunk.setFill(Color.SADDLEBROWN);
					
					leaves = new Ellipse(x, y-50, 20, 30);
					leaves.setFill(Color.rgb(30, 120, 80));
					
					this.getChildren().addAll(trunk, leaves);
				}
			}
			
			public void addTrees(double offset, int n) {
				double mountainCenter = offset+WINDOW_WIDTH/2;
				double windowRatio = (double)WINDOW_WIDTH / WINDOW_HEIGHT;
				double x, y;
				for(int i = 0; i < n; i++) {
					y = Math.random() * WINDOW_HEIGHT;
					if(y < WINDOW_HEIGHT/2 && y % 2 == 0) y *= 2;
					x =  mountainCenter + Math.random() * y * windowRatio - y * windowRatio/2;
					Tree tree = new Tree(x, y);
					this.getChildren().add(tree);
				}
			}
		}
	}
	
}

