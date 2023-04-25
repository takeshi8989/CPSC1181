package lab7;
	
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;


public class Main extends Application {
	private Pane root;
	private final int WINDOW_WIDTH = 1000;
	private final int WINDOW_HEIGHT = 600;
	private final int GROUND_HEIGHT = 100;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			root = new Pane();
			
			// background items sky, ground, cloud, mountain, buildings, people
			Background bg = new Background();
			bg.addBuildings(30);
			bg.addPeople(8);
			root.getChildren().addAll(bg);
			
			// foreground items ufos, bombs, coin
			ArrayList<Ufo> ufos = createUfos(3);
			ArrayList<Bomb> bombs = createBombs(5);
			ArrayList<Coin> coins = createCoins(5);
			root.getChildren().addAll(ufos);
			root.getChildren().addAll(bombs);
			root.getChildren().addAll(coins);
		
			Coin coin = new Coin(100, 100);
			root.getChildren().addAll(coin);

			Scene scene = new Scene(root,WINDOW_WIDTH, WINDOW_HEIGHT);
			primaryStage.setTitle("Explode Future City Game");
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
			double x = Math.random() * (WINDOW_WIDTH - 100) + 100;
			double y = Math.random() * 50 + 200;
			list.add(new Bomb(x, y));
		}
		return list;
	}
	
	private ArrayList<Coin> createCoins(int n) {
		ArrayList<Coin> list = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			double x = Math.random() * (WINDOW_WIDTH - 100) + 100;
			double y = Math.random() * 50 + 200;
			list.add(new Coin(x, y));
		}
		return list;
	}
	
	private class Background extends Group {
		private Rectangle sky;
		private Rectangle ground;
		private Mountain mountain;
		private Circle sun;
		private ArrayList<Cloud> clouds;
		
		public Background() {
			sky = createRectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, Color.SKYBLUE);
			ground = createRectangle(0, WINDOW_HEIGHT-GROUND_HEIGHT, 
												WINDOW_WIDTH, GROUND_HEIGHT, Color.BROWN);
			sun = new Circle(WINDOW_WIDTH, 0, 80);
			sun.setFill(Color.YELLOW);
			mountain = new Mountain();
			this.getChildren().addAll(sky, mountain, ground, sun);
			
			this.clouds = createClouds();
			this.getChildren().addAll(clouds);
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
		
		public void addBuildings(int n) {
			for(int i = 0; i < n; i++) {
				addBuilding();
			}
		}
		
		private void addBuilding() {
			Random rand = new Random();
			Building building = new Building();
			building.setWidth(Math.random() * 200 + 100);
			building.setHeight(Math.random() * 300 + 100);
			building.setX(Math.random() * WINDOW_WIDTH);
			building.setY(WINDOW_HEIGHT - GROUND_HEIGHT / 2 - building.getHeight());
			building.setColor(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
			building.addWindows();
			this.getChildren().addAll(building);
		}
		
		public void addPeople(int n) {
			for(int i = 0; i < n; i++) {
				addPerson();
			}
		}
		
		private void addPerson() {
			double height = Math.random() * 20 + 40;
			double x = Math.random() * WINDOW_WIDTH;
			double y = Math.random() * 20 + WINDOW_HEIGHT - GROUND_HEIGHT / 2 - height*2;
			Person p = new Person(x, y, height);
			this.getChildren().addAll(p);
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
