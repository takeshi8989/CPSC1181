package lab8;
	
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class Main extends Application {
	private Pane root;
	private final int WINDOW_WIDTH = 1000;
	private final int WINDOW_HEIGHT = 550;
	private final int GROUND_HEIGHT = 100;
	private Background bg;
	private CheckBox togglePplBox;
	private CheckBox toggleUfoBox;
	private CheckBox toggleCloudBox;
	private ToggleGroup coinSizeToggle;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			root = new Pane();
			
			// background items sky, ground, cloud, mountain, buildings
			bg = new Background();
			root.getChildren().addAll(bg);
			for(Node node: bg.getChildren()) {
				if(node instanceof lab8.Main.Background.Cloud) node.setVisible(false);
			}
			
			
			
			// foreground items  people, ufos, bombs, coin
			ArrayList<Person> people = createPeople(8);
			ArrayList<Ufo> ufos = createUfos(3);
			ArrayList<Bomb> bombs = createBombs(5);
			ArrayList<Coin> coins = createCoins(5);
			root.getChildren().addAll(people);
			root.getChildren().addAll(ufos);
			root.getChildren().addAll(bombs);
			root.getChildren().addAll(coins);
			
			// Event Buttons (building)
			Button removeBuildingBtn = new Button("Remove building");
			removeBuildingBtn.setOnAction(new RemoveBuildingListener());
			Button addBuildingBtn = new Button("Add building");
			addBuildingBtn.setOnAction(new AddBuildingListener());
			
			HBox buildingBtns = new HBox(30, removeBuildingBtn, addBuildingBtn);
			buildingBtns.setAlignment(Pos.CENTER);
			
			// checkbox (ufo, mountain, clouds)
			togglePplBox = new CheckBox("people");
			togglePplBox.setSelected(true);
			togglePplBox.setOnAction(new TogglePeopleListener());
			
			toggleUfoBox = new CheckBox("ufos");
			toggleUfoBox.setSelected(true);
			toggleUfoBox.setOnAction(new ToggleUfoListener());
			
			toggleCloudBox = new CheckBox("clouds");
			toggleCloudBox.setOnAction(new ToggleCloudListener());
			VBox toggleCheckBoxes = new VBox(10, togglePplBox, toggleUfoBox, toggleCloudBox);
			
			
			
			// Create coin with parameters
			coinSizeToggle = new ToggleGroup();
			RadioButton smallRadio = new RadioButton("Small");
			smallRadio.setToggleGroup(coinSizeToggle);
			smallRadio.setUserData(0.7);
			
			RadioButton mediumRadio = new RadioButton("Medium");
			mediumRadio.setToggleGroup(coinSizeToggle);
			mediumRadio.setUserData(1.0);
			
			RadioButton largeRadio = new RadioButton("Large");
			largeRadio.setToggleGroup(coinSizeToggle);
			largeRadio.setUserData(1.5);
			
			Button createCoinBtn = new Button("Create");
			createCoinBtn.setOnAction(new CreateCoinListener());
			coinSizeToggle.selectToggle(mediumRadio);
			
			VBox radioBtns = new VBox(10, smallRadio, mediumRadio, largeRadio);
			HBox createCoinDiv = new HBox(20, radioBtns, createCoinBtn);
			createCoinDiv.setAlignment(Pos.CENTER);
			
			
			
			// Layout for controller
			HBox itemController = new HBox(150, buildingBtns, toggleCheckBoxes, createCoinDiv);
			itemController.setLayoutY(WINDOW_HEIGHT+10);
			itemController.setMaxWidth(WINDOW_WIDTH);
			itemController.setAlignment(Pos.CENTER_RIGHT);
			root.getChildren().add(itemController);

			
			// Scene
			Scene scene = new Scene(root,WINDOW_WIDTH, WINDOW_HEIGHT + 100);
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
	
	// Event Listner
	private class RemoveBuildingListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			bg.removeBuilding();
			
		}
	}
	
	private class AddBuildingListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			bg.addBuilding();
		}
	}
	
	private class TogglePeopleListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			for(Node node: root.getChildren()) {
				if(node instanceof Person) {
					node.setVisible(togglePplBox.isSelected());
				}
			}
		}
	}
	
	private class ToggleUfoListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			for(Node node: root.getChildren()) {
				if(node instanceof Ufo) {
					node.setVisible(toggleUfoBox.isSelected());
				}
			}
		}
	}
	
	private class ToggleCloudListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			for(Node node: bg.getChildren()) {
				if(node instanceof lab8.Main.Background.Cloud) {
					node.setVisible(toggleCloudBox.isSelected());
				}
			}
		}
	}
	
	private class CreateCoinListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			double size = (double)coinSizeToggle.getSelectedToggle().getUserData();
			root.getChildren().addAll(createCoins(1, size));
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
			double x = Math.random() * (WINDOW_WIDTH - 100) + 100;
			double y = Math.random() * 50 + 200;
			list.add(new Bomb(x, y));
		}
		return list;
	}
	
	private ArrayList<Coin> createCoins(int n) {
		return createCoins(n, 1);
	}
	
	private ArrayList<Coin> createCoins(int n, double size) {
		ArrayList<Coin> list = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			double x = Math.random() * (WINDOW_WIDTH - 100) + 50;
			double y = Math.random() * 50 + 100;
			list.add(new Coin(x, y, size));
		}
		return list;
	}
	
	// Background Class
	private class Background extends Group {
		private Rectangle sky;
		private Rectangle ground;
		private Mountain mountain;
		private Circle sun;
		private ArrayList<Cloud> clouds;
		private ArrayList<Building> buildings;
		
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
			this.buildings = this.createBuildings(30);
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
