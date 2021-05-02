package application;

public class BotPlayer implements Player{
	
	private Map currentMap;
	private Position currentPosition;
	private Bullet bullet = new Bullet(null, null, null);
	private int playerLife = 1;
	private int playerDirection = 8;

	@Override
	public void setMap(Map theMapForPlayerSet) {
		this.currentMap = theMapForPlayerSet;
	}

	@Override
	public void moveRight() {
		if((currentMap.getX() !=  currentPosition.getX() + 1) && (currentMap.getValueAt(currentPosition.getX() + 1, currentPosition.getY()) == '0' || currentMap.getValueAt(currentPosition.getX() + 1, currentPosition.getY()) == 'P' || currentMap.getValueAt(currentPosition.getX() + 1, currentPosition.getY()) == 'T' || currentMap.getValueAt(currentPosition.getX() + 1, currentPosition.getY()) == 'N') && playerDirection == 6){
	        currentPosition.setX(currentPosition.getX() + 1);
	      }
	      else{
	        playerDirection = 6;
	      }
	}

	@Override
	public void moveLeft() {
		if((currentPosition.getX() != 0) && (currentMap.getValueAt(currentPosition.getX() - 1, currentPosition.getY()) == '0' || currentMap.getValueAt(currentPosition.getX() - 1, currentPosition.getY()) == 'P' || currentMap.getValueAt(currentPosition.getX() - 1, currentPosition.getY()) == 'T') && playerDirection == 4){
	        currentPosition.setX(currentPosition.getX() - 1);
	      }
	      else{
	        playerDirection = 4;
	      }
	}

	@Override
	public void moveUp() {
		if((currentPosition.getY() != 0) && (currentMap.getValueAt(currentPosition.getX(), currentPosition.getY() - 1) == '0' || currentMap.getValueAt(currentPosition.getX(), currentPosition.getY() - 1) == 'P' || currentMap.getValueAt(currentPosition.getX(), currentPosition.getY() - 1) == 'T') && playerDirection == 8){
	        currentPosition.setY(currentPosition.getY() - 1);
	      }
	      else{
	        playerDirection = 8;
	      }
	}

	@Override
	public void moveDown() {
		if((currentMap.getY() != currentPosition.getY() + 1) && (currentMap.getValueAt(currentPosition.getX(), currentPosition.getY() + 1) == '0' || currentMap.getValueAt(currentPosition.getX(), currentPosition.getY() + 1) == 'P' || currentMap.getValueAt(currentPosition.getX(), currentPosition.getY() + 1) == 'T') && playerDirection == 2){
	        currentPosition.setY(currentPosition.getY() + 1);
	      }
	      else{
	        playerDirection = 2;
	      }
	}
	
	public int getDirection() {
		  return this.playerDirection;
	  }

	@Override
	public void makeFire() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Position getPosition() {
		return currentPosition;
	}

}
