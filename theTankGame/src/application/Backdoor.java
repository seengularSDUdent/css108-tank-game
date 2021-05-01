package application;

import java.io.File;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Backdoor {
	public class Main extends Application {
		@Override
		public void start(Stage primaryStage) {
			try {
				java.io.File file = new java.io.File("map.txt");
			    Scanner input = new Scanner(file);
			    Map map = new Map(input);
			    map.print();
			    Game tankGame = new Game(map);
			    MyPlayer newPlayer = new MyPlayer();
			    tankGame.addPlayer(newPlayer);
				
				Pane root = new StackPane();
				//root.setMaxSize(map.getX() * 16, map.getY() * 16);
				root.setStyle("-fx-background-color: white;");
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				
				GridPane gameField = new GridPane();
				root.getChildren().add(gameField);
				gameField.setMaxSize(map.getX() * 16, map.getY() * 16);
				gameField.setStyle("-fx-background-color: black;");
				
				/* Image zeroField = new Image(new File("images/zeroField.png").toURI().toString());
				Image playerField = new Image(new File("images/playerField.png").toURI().toString());
				Image brickField = new Image(new File("images/brickField.png").toURI().toString());
				Image steelField = new Image(new File("images/steelField.png").toURI().toString());
				Image waterField = new Image(new File("images/waterField.png").toURI().toString());
				Image treeField = new Image(new File("images/treeField.png").toURI().toString()); */
				
				for(int i = 0; i < map.getY(); i++) {
					for(int j = 0; j < map.getX(); j++) {
						ImageView imageView;
						if(map.getValueAt(j, i) == '1') {
							imageView = new ImageView(new Image(new File("images/brickField.png").toURI().toString(), 100, 100, false, false));
							gameField.add(imageView, j, i);
							zoomFit(imageView, map, root, gameField);
						}
						else if(map.getValueAt(j, i) == '0') {
							imageView = new ImageView(new Image(new File("images/zeroField.png").toURI().toString(), 100, 100, false, false));
							gameField.add(imageView, j, i);
							zoomFit(imageView, map, root, gameField);
						}
						else if(map.getValueAt(j, i) == 'P') {
							ImageView playerView = new ImageView(new Image(new File("images/playerField.png").toURI().toString(), 100, 100, false, false));
							gameField.add(playerView, j, i);
							zoomFit(playerView, map, root, gameField);
						}
						else if(map.getValueAt(j, i) == 'S') {
							imageView = new ImageView(new Image(new File("images/steelField.png").toURI().toString(), 100, 100, false, false));
							gameField.add(imageView, j, i);
							zoomFit(imageView, map, root, gameField);
						}
						else if(map.getValueAt(j, i) == 'W') {
							imageView = new ImageView(new Image(new File("images/waterField.png").toURI().toString(), 100, 100, false, false));
							gameField.add(imageView, j, i);
							zoomFit(imageView, map, root, gameField);
						}
						else if(map.getValueAt(j, i) == 'T') {
							imageView = new ImageView(new Image(new File("images/treeField.png").toURI().toString(), 100, 100, false, false));
							gameField.add(imageView, j, i);
							zoomFit(imageView, map, root, gameField);
						}
					}
				}
				
				Position previousPosition = newPlayer.getPosition();
				
				scene.setOnKeyPressed(e -> {
					switch (e.getCode()) {
						case DOWN:
							System.out.println("work1");
							previousPosition.setX(newPlayer.getPosition().getX());
							previousPosition.setY(newPlayer.getPosition().getY());
							newPlayer.moveDown();
							turnByDirection(gameField.getChildren().get(gameField.getChildren().indexOf(getNodeFromGridPane(gameField, newPlayer.getPosition().getX(), newPlayer.getPosition().getY()))), newPlayer);
							//changePosition(gameField, gameField.getChildren().get(gameField.getChildren().indexOf(getNodeFromGridPane(gameField, previousPosition.getX(), previousPosition.getY()))), newPlayer, newPlayer.getPosition());
							changePosition(gameField, newPlayer, previousPosition);
							turnByDirection(gameField.getChildren().get(gameField.getChildren().indexOf(getNodeFromGridPane(gameField, newPlayer.getPosition().getX(), newPlayer.getPosition().getY()))), newPlayer);
							break;
						case UP:
							System.out.println("work2");
							previousPosition.setX(newPlayer.getPosition().getX());
							previousPosition.setY(newPlayer.getPosition().getY());
							newPlayer.moveUp();
							turnByDirection(gameField.getChildren().get(gameField.getChildren().indexOf(getNodeFromGridPane(gameField, newPlayer.getPosition().getX(), newPlayer.getPosition().getY()))), newPlayer);
							//changePosition(gameField, gameField.getChildren().get(gameField.getChildren().indexOf(getNodeFromGridPane(gameField, previousPosition.getX(), previousPosition.getY()))), newPlayer, newPlayer.getPosition());
							changePosition(gameField, newPlayer, previousPosition);
							turnByDirection(gameField.getChildren().get(gameField.getChildren().indexOf(getNodeFromGridPane(gameField, newPlayer.getPosition().getX(), newPlayer.getPosition().getY()))), newPlayer);
							break;
						case LEFT:
							System.out.println("work3");
							previousPosition.setX(newPlayer.getPosition().getX());
							previousPosition.setY(newPlayer.getPosition().getY());
							newPlayer.moveLeft();
							turnByDirection(gameField.getChildren().get(gameField.getChildren().indexOf(getNodeFromGridPane(gameField, newPlayer.getPosition().getX(), newPlayer.getPosition().getY()))), newPlayer);
							
							changePosition(gameField, newPlayer, previousPosition);//changePosition(gameField, gameField.getChildren().get(gameField.getChildren().indexOf(getNodeFromGridPane(gameField, previousPosition.getX(), previousPosition.getY()))), newPlayer, newPlayer.getPosition());
							turnByDirection(gameField.getChildren().get(gameField.getChildren().indexOf(getNodeFromGridPane(gameField, newPlayer.getPosition().getX(), newPlayer.getPosition().getY()))), newPlayer);
							break;
						case RIGHT:
							System.out.println("work4");
							previousPosition.setX(newPlayer.getPosition().getX());
							previousPosition.setY(newPlayer.getPosition().getY());
							newPlayer.moveRight();
							turnByDirection(gameField.getChildren().get(gameField.getChildren().indexOf(getNodeFromGridPane(gameField, newPlayer.getPosition().getX(), newPlayer.getPosition().getY()))), newPlayer);
							changePosition(gameField, newPlayer, previousPosition);
							turnByDirection(gameField.getChildren().get(gameField.getChildren().indexOf(getNodeFromGridPane(gameField, newPlayer.getPosition().getX(), newPlayer.getPosition().getY()))), newPlayer);//changePosition(gameField, gameField.getChildren().get(gameField.getChildren().indexOf(getNodeFromGridPane(gameField, previousPosition.getX(), previousPosition.getY()))), newPlayer, newPlayer.getPosition());
							break;
						case SPACE:
					}
				});
				
				primaryStage.setTitle("Tank game");
				primaryStage.setResizable(true);
				primaryStage.setFullScreen(true);
				primaryStage.getIcons().add(new Image(new File("images/tankGameIcon.png").toURI().toString()));
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			launch(args);
		}
		
		public static void zoomFit(ImageView image, Map map, Pane root, GridPane gap) {
			if(map.getX() > map.getY()) {
				image.fitWidthProperty().bind(root.widthProperty().divide(map.getX()));;
				image.fitHeightProperty().bind(root.widthProperty().divide(map.getX()));
				image.setSmooth(false);
				gap.setHgap(0);
				gap.setVgap(0);
			}
			else if(map.getX() < map.getY()) {
				image.fitHeightProperty().bind(root.heightProperty().divide(map.getY()));;
				image.fitWidthProperty().bind(root.heightProperty().divide(map.getY()));
				image.setSmooth(false);
				gap.setHgap(0);
				gap.setVgap(0);
			}
			else {
				image.fitHeightProperty().bind(root.heightProperty().divide(map.getY()));;
				image.fitWidthProperty().bind(root.heightProperty().divide(map.getY()));
				image.setSmooth(false);
				gap.setHgap(0);
				gap.setVgap(0);
			}
		}
		
		public static Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
		    for (Node node : gridPane.getChildren()) {
		        if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
		            return node;
		        }	
		    }
		    return null;
		}
		
		public static void turnByDirection(Node node, MyPlayer player) {
			if(player.getDirection() == 8) {
				node.setRotate(0);
			}
			else if(player.getDirection() == 2) {
				node.setRotate(180);
			}
			else if(player.getDirection() == 6) {
				node.setRotate(90);
			}
			else if(player.getDirection() == 4) {
				node.setRotate(270);
			}
		}
		
		public static void changePosition(GridPane gameField, MyPlayer newPos, Position prevPos) {
			System.out.println(prevPos.toString());
			System.out.println(newPos.getPosition().toString());
			 //gameField.getChildren().removeAll(gameField.getChildren().get(gameField.getChildren().indexOf(getNodeFromGridPane(gameField, prevPos.getPosition().getX(), prevPos.getPosition().getY()))), gameField.getChildren().get(gameField.getChildren().indexOf(getNodeFromGridPane(gameField, newPos.getX(), newPos.getY()))));
			 gameField.add(new ImageView(new Image(new File("images/zeroField.png").toURI().toString(), 75, 75, false, false)) , prevPos.getX(), prevPos.getY());
			 gameField.add(new ImageView(new Image(new File("images/playerField.png").toURI().toString(), 75, 75, false, false)), newPos.getPosition().getX(), newPos.getPosition().getY());
			 gameField.getChildren().remove(gameField.getChildren().indexOf(getNodeFromGridPane(gameField, newPos.getPosition().getX(), newPos.getPosition().getY())));
			 gameField.getChildren().remove(gameField.getChildren().indexOf(getNodeFromGridPane(gameField, prevPos.getX(), prevPos.getY())));
		}
		
		/* public static void loadMap(Map map, GridPane gameField, Pane root) {
			for(int i = 0; i < map.getY(); i++) {
				for(int j = 0; j < map.getX(); j++) {
					ImageView imageView;
					if(map.getValueAt(j, i) == '1') {
						imageView = new ImageView(new Image(new File("images/brickField.png").toURI().toString(), 100, 100, false, false));
						gameField.add(imageView, j, i);
						zoomFit(imageView, map, root, gameField);
					}
					else if(map.getValueAt(j, i) == '0') {
						imageView = new ImageView(new Image(new File("images/zeroField.png").toURI().toString(), 100, 100, false, false));
						gameField.add(imageView, j, i);
						zoomFit(imageView, map, root, gameField);
					}
					else if(map.getValueAt(j, i) == 'P') {
						ImageView playerView = new ImageView(new Image(new File("images/playerField.png").toURI().toString(), 100, 100, false, false));
						gameField.add(playerView, j, i);
						zoomFit(playerView, map, root, gameField);
					}
					else if(map.getValueAt(j, i) == 'S') {
						imageView = new ImageView(new Image(new File("images/steelField.png").toURI().toString(), 100, 100, false, false));
						gameField.add(imageView, j, i);
						zoomFit(imageView, map, root, gameField);
					}
					else if(map.getValueAt(j, i) == 'W') {
						imageView = new ImageView(new Image(new File("images/waterField.png").toURI().toString(), 100, 100, false, false));
						gameField.add(imageView, j, i);
						zoomFit(imageView, map, root, gameField);
					}
					else if(map.getValueAt(j, i) == 'T') {
						imageView = new ImageView(new Image(new File("images/treeField.png").toURI().toString(), 100, 100, false, false));
						gameField.add(imageView, j, i);
						zoomFit(imageView, map, root, gameField);
					}
				}
			}
		} */
	}
}