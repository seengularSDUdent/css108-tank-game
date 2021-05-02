package application;

import java.io.File;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BulletTask implements Runnable{
	private int bulletDistance = 8;
	private Image bulletImage;
	private Position bulletPosition;
	private GridPane gameField;
	private GridPane bulletField;
	private int bulletDirection = 5;
	private Map gameMap;
	private Pane root;
	private int counter = 0;
	
	/*public BulletTask(GridPane gameField, Pane root, GridPane bulletField) {
		this.gameField = gameField;
		this.bulletField = bulletField;
		this.root = root;
	}*/

	public BulletTask(GridPane gameField, Pane root, GridPane bulletField, Position playerPosition, int playerDirection, Map map) {
		this.gameField = gameField;
		this.bulletField = bulletField;
		this.root = root;
		this.bulletPosition = playerPosition;
		this.bulletDirection = playerDirection;
		this.gameMap = map;
	}

	public void setBulletImage(String pathToImg){
		bulletImage = new Image(new File(pathToImg).toURI().toString(), 75, 75, false, false);
	}
	
	public void setBulletDistance(int distance) {
		this.bulletDistance = distance;
	}
	
	public void run() {
		System.out.println("Runnable starts");
		doFire(bulletPosition, bulletDirection, gameMap);
	}
	
	public synchronized void doFire(Position playerPosition, int playerDirection, Map playerMap) {
		bulletPosition = new Position(playerPosition.getX(), playerPosition.getY());
		
		switch(playerDirection) {
			case 8:
				for(int i = 0; i < this.bulletDistance; i++) {
					if((bulletPosition.getY() != 0) && ((playerMap.getValueAt(bulletPosition.getX(), bulletPosition.getY() - 1) == '0') || (playerMap.getValueAt(bulletPosition.getX(), bulletPosition.getY() - 1) == 'W') || (playerMap.getValueAt(bulletPosition.getX(), bulletPosition.getY() - 1) == 'T') || (playerMap.getValueAt(bulletPosition.getX(), bulletPosition.getY() - 1) == 'P'))) {
						this.makeMove(playerDirection, playerMap);
						bulletPosition.setY(bulletPosition.getY() - 1);
						System.out.print(i + " ");
					}
					else {
						this.makeExplosion(bulletPosition, playerDirection, playerMap);
						break;
					}
				}
				break;
			case 6:
				for(int i = 0; i < this.bulletDistance; i++) {
					if((playerMap.getX() !=  bulletPosition.getX() + 1) && ((playerMap.getValueAt(bulletPosition.getX() + 1, bulletPosition.getY()) == '0') || (playerMap.getValueAt(bulletPosition.getX() + 1, bulletPosition.getY()) == 'W') || (playerMap.getValueAt(bulletPosition.getX() + 1, bulletPosition.getY()) == 'T') || (playerMap.getValueAt(bulletPosition.getX() + 1, bulletPosition.getY()) == 'P'))) {
						this.makeMove(playerDirection, playerMap);
						bulletPosition.setX(bulletPosition.getX() + 1);
						System.out.print(i + " ");
					}
					else {
						this.makeExplosion(bulletPosition, playerDirection, playerMap);
						break;
					}
				}
				break;
			case 2:
				for(int i = 0; i < this.bulletDistance; i++) {
					if((playerMap.getY() !=  bulletPosition.getY() + 1) && ((playerMap.getValueAt(bulletPosition.getX(), bulletPosition.getY() + 1) == '0') || (playerMap.getValueAt(bulletPosition.getX(), bulletPosition.getY() + 1) == 'W') || (playerMap.getValueAt(bulletPosition.getX(), bulletPosition.getY() + 1) == 'T') || (playerMap.getValueAt(bulletPosition.getX(), bulletPosition.getY() + 1) == 'P'))) {
						this.makeMove(playerDirection, playerMap);
						bulletPosition.setY(bulletPosition.getY() + 1);
						System.out.print(i + " ");
					}
					else {
						this.makeExplosion(bulletPosition, playerDirection, playerMap);
						break;
					}
				}
				break;
			case 4:
				for(int i = 0; i < this.bulletDistance; i++) {
					if((bulletPosition.getX() != 0) && ((playerMap.getValueAt(bulletPosition.getX() - 1, bulletPosition.getY()) == '0') || (playerMap.getValueAt(bulletPosition.getX() - 1, bulletPosition.getY()) == 'W') || (playerMap.getValueAt(bulletPosition.getX() - 1, bulletPosition.getY()) == 'T') || (playerMap.getValueAt(bulletPosition.getX() - 1, bulletPosition.getY()) == 'P'))) {
						this.makeMove(playerDirection, playerMap);
						bulletPosition.setX(bulletPosition.getX() - 1);
						System.out.print(i+ " ");
					}
					else {
						this.makeExplosion(bulletPosition, playerDirection, playerMap);
						break;
					}
				}
				break;
		}
	}

	private void makeExplosion(Position position, int direction, Map map) {
		// TODO Auto-generated method stub
		System.out.println("BOOM!");
		int x = position.getX();
		int y = position.getY();
		int moverX = 0;
		int moverY = 0;
			
			System.out.println("bullet x=" + x + " y=" +y);
		
		switch(direction) {
		case 8:
			if(position.getY() != 0) {
				y = y - 1;
				moverY = -1;
			}
			break;
		case 6:
			if(position.getX() + 1 != map.getX()) {
				x = x + 1;
				moverX = 1;
			}
			break;
		case 2:
			if(position.getY() + 1 != map.getY()) {
				y = y + 1;
				moverY = 1;
			}
			break;
		case 4:
			if(position.getX() != 0) {
				x = x - 1;
				moverX = -1;
			}
			break;
	}
		
		System.out.println(map.getValueAt(x, y));
		
		ImageView imageView;
		
		final int finalX = x;
		final int finalY = y;
		final int finalMoverX = moverX;
		final int finalMoverY = moverY;
		final ImageView emptyView = new ImageView(new Image(new File("images/emptyField.png").toURI().toString(), 75, 75, false, false));
		zoomFit(emptyView, map, root, gameField);
		switch(map.getValueAt(x, y)) {
			case '1':
				System.out.println("BOOM WORK! x=" + x + " y=" + y);
				map.changeElementAt(y, x, '2');
				System.out.println(map.getValueAt(x, y));
				imageView = new ImageView(new Image(new File("images/brickDemaged1Field.png").toURI().toString(), 50, 50, false, false));
				Platform.runLater(new Runnable() {
				    @Override
				    public void run() {
				    	gameField.add(imageView, finalX, finalY);
						zoomFit(imageView, map, root, gameField);
						gameField.getChildren().remove(gameField.getChildren().indexOf(getNodeFromGridPane(gameField, finalX, finalY)));
				    }
				});
				break;
			case '2':
				map.changeElementAt(y, x, '3');
				System.out.println(map.getValueAt(x, y));
				imageView = new ImageView(new Image(new File("images/brickDemaged2Field.png").toURI().toString(), 50, 50, false, false));
				Platform.runLater(new Runnable() {
				    @Override
				    public void run() {
				    	gameField.add(imageView , finalX, finalY);
						zoomFit(imageView, map, root, gameField);
						gameField.getChildren().remove(gameField.getChildren().indexOf(getNodeFromGridPane(gameField, finalX, finalY)));
				    }
				});
				break;
			case '3':
				map.changeElementAt(y, x, '0');
				System.out.println(map.getValueAt(x, y));
				imageView = new ImageView(new Image(new File("images/zeroField.png").toURI().toString(), 50, 50, false, false));
				Platform.runLater(new Runnable() {
				    @Override
				    public void run() {
				    	gameField.add(imageView , finalX, finalY);
						zoomFit(imageView, map, root, gameField);
						gameField.getChildren().remove(gameField.getChildren().indexOf(getNodeFromGridPane(gameField, finalX, finalY)));
				    }
				});
				break;
		}
		
	
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
				bulletField.getChildren().remove(getNodeFromGridPane(bulletField, bulletPosition.getX() + finalMoverX, bulletPosition.getY() + finalMoverY));
	    		bulletField.getChildren().remove(getNodeFromGridPane(bulletField, bulletPosition.getX() + finalMoverX, bulletPosition.getY() + finalMoverY));
	    		bulletField.add(emptyView, bulletPosition.getX() + finalMoverX, bulletPosition.getY() + finalMoverY);
		    }
		});
		
		System.out.println("BOOM DONE!");
	}

	private void makeMove(int playerDirection, Map map) {
		// TODO Auto-generated method stub
		System.out.println("Moving!");
		ImageView imageView = null;
		PathTransition lineWay = null;
		int moverX = 0;
		int moverY = 0;
		switch(playerDirection) {
			case 8:
				if(bulletPosition.getY() != 0) {
					this.bulletImage = new Image(new File("images/bulletFieldUp.png").toURI().toString(), 75, 75, false, false);
					imageView = new ImageView(this.bulletImage);
					moverY = -1;
				}
				break;
			case 6:
				if(bulletPosition.getX() + 1 != map.getX()) {
					this.bulletImage = new Image(new File("images/bulletFieldRight.png").toURI().toString(), 75, 75, false, false);
					imageView = new ImageView(this.bulletImage);
					moverX = 1;
				}
				break;
			case 2:
				if(bulletPosition.getY() + 1 != map.getY()) {
					this.bulletImage = new Image(new File("images/bulletFieldDown.png").toURI().toString(), 75, 75, false, false);
					imageView = new ImageView(this.bulletImage);
					moverY = 1;
				}
				break;
			case 4:
				if(bulletPosition.getX() != 0) {
					this.bulletImage = new Image(new File("images/bulletFieldLeft.png").toURI().toString(), 75, 75, false, false);
					imageView = new ImageView(this.bulletImage);
					moverX = -1;
				}
				break;
		}
		zoomFit(imageView, map, root, gameField);
		
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		        // Update UI here.
		    }
		});
		
		final ImageView finalImageView = imageView;
		final ImageView emptyView = new ImageView(new Image(new File("images/emptyField.png").toURI().toString(), 75, 75, false, false));
		final ImageView longTesterView = new ImageView(new Image(new File("images/emptyField.png").toURI().toString(), 75, 75, false, false));
		zoomFit(emptyView, map, root, gameField);
		final int finalMoverX = moverX;
		final int finalMoverY = moverY;
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Platform.runLater(new Runnable() {
		    @Override
		    public synchronized void run() {
		    	if(counter == 0) {
		    		if(bulletPosition.getX() + finalMoverX > -1 && bulletPosition.getX() + finalMoverX < map.getX() && bulletPosition.getY() + finalMoverY > -1 && bulletPosition.getY() + finalMoverY < map.getY()) {
		    			bulletField.add(finalImageView, bulletPosition.getX() + finalMoverX, bulletPosition.getY() + finalMoverY);
		    		}
		    	}
		    	else if(counter == 7) {
		    		bulletField.getChildren().remove(getNodeFromGridPane(bulletField, bulletPosition.getX(), bulletPosition.getY()));
		    		bulletField.getChildren().remove(getNodeFromGridPane(bulletField, bulletPosition.getX(), bulletPosition.getY()));
		    		bulletField.add(emptyView, bulletPosition.getX(), bulletPosition.getY());
		    	}
		    	else {
		    		bulletField.getChildren().remove(getNodeFromGridPane(bulletField, bulletPosition.getX(), bulletPosition.getY()));
		    		bulletField.getChildren().remove(getNodeFromGridPane(bulletField, bulletPosition.getX(), bulletPosition.getY()));
		    		bulletField.add(emptyView, bulletPosition.getX(), bulletPosition.getY());
		    		if(bulletPosition.getX() + finalMoverX > -1 && bulletPosition.getX() + finalMoverX < map.getX() && bulletPosition.getY() + finalMoverY > -1 && bulletPosition.getY() + finalMoverY < map.getY()) {
		    			bulletField.add(finalImageView, bulletPosition.getX() + finalMoverX, bulletPosition.getY() + finalMoverY);
		    		}
		    		System.out.println("final movers here " + finalMoverX + " " + finalMoverY);
		    	}
		    	
		    	counter++;
		    }
		});
	}
	
	private static Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
	    for (Node node : gridPane.getChildren()) {
	        if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
	            return node;
	        }	
	    }
	    return null;
	}
	
	private static void zoomFit(ImageView image, Map map, Pane root, GridPane gap) {
		image.setPreserveRatio(true);
		image.fitWidthProperty().bind(root.widthProperty().divide(map.getX()));
		image.fitHeightProperty().bind(root.heightProperty().divide(map.getY()));
		gap.setHgap(0);
		gap.setVgap(0);
	}
}
