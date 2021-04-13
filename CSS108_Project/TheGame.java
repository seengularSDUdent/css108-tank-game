import java.util.*;
import java.io.*;

public class TheGame {
  public static void main(String[] args) throws Exception{
    java.io.File file = new java.io.File("map.txt");
    Scanner input = new Scanner(file);
    Map map = new Map(input);

    map.print();

    Game tankGame = new Game(map);
    Player newPlayer = new MyPlayer();

    tankGame.addPlayer(newPlayer);
//    newPlayer.setMap(map);
    System.out.println(newPlayer.getPosition());
    newPlayer.moveLeft();
    newPlayer.moveLeft();
    System.out.println(newPlayer.getPosition());
  }
}

class Map{

  char[][] mainMap;
  String[] lines = new String[0];
  String[] linesCopy;
  int howManyLines = 0;
  int howManyColumns = 0;

  public Map(Scanner input){
    try{
      checkMapEmpty(input);
    }
    catch(Exception e){
      System.out.println("A problem occured: " +e);
      System.exit(1);
    }

    while(input.hasNextLine()){
      linesCopy = new String[lines.length + 1];
      for(int i = 0; i < lines.length; i++){
        linesCopy[i] = lines[i];
      }
      linesCopy[linesCopy.length - 1] = input.nextLine();
      lines = linesCopy;
    }
    howManyLines = lines.length;
    howManyColumns = lines[0].length();
    mainMap = new char[howManyLines][howManyColumns];

    for(int i = 0; i < howManyLines; i++){
      for(int j = 0; j < howManyColumns; j++){
        mainMap[i][j] = lines[i].charAt(j);
      }
    }

    try{
      checkMapElement(mainMap, howManyLines, howManyColumns);
    }
    catch (Exception s) {
      System.out.println("A problem occured: " +s);
      System.exit(1);
    }
  }

  private static void checkMapEmpty(Scanner input)throws InvalidMapException{
    if(!input.hasNext()){
      throw new InvalidMapException("Map can't be empty!");
    }
  }

  private static void checkMapElement(char[][] checkMap, int numLines, int numColumns)throws InvalidMapException{

    boolean thereIsZero = false;
    boolean thereIsOne = false;
    boolean thereIsPlayer = false;

    for(int i = 0; i < numLines; i++){
      for(int j = 0; j < numColumns; j++){
        if(checkMap[i][j] != '0' && checkMap[i][j] != '1' && checkMap[i][j] != 'P'){
          throw new InvalidMapException("Invalid element found! --> (" + checkMap[i][j] + ")");
        }
        else if(checkMap[i][j] == '0'){
          thereIsZero = true;
        }
        else if(checkMap[i][j] == '1'){
          thereIsOne = true;
        }
        else if(checkMap[i][j] == 'P'){
          thereIsPlayer = true;
        }
        if((!thereIsOne || !thereIsZero || !thereIsPlayer) && (j + 1 == numColumns && i + 1 == numLines)){
          throw new InvalidMapException("Map should contain all related elements!");
        }
      }
    }
  }

  public int getSize(){
    return howManyColumns * howManyLines;
  }

  public int getX(){
    return howManyColumns;
  }

  public int getY(){
    return howManyLines;
  }

  public char getValueAt(int theX, int theY){
    return mainMap[theY][theX];
  }

  public void print(){
    for(int i = 0; i < howManyLines; i++){
      for(int j = 0; j < howManyColumns; j++){
        System.out.print(mainMap[i][j]);
      }
      System.out.println();
    }
  }
}

class InvalidMapException extends Exception{
  InvalidMapException(String message){
    super(message);
  }
}

class Game {

  Map gameMap;
  Player gamePlayer;

  public Game(Map theMapForGame){
    gameMap = theMapForGame;
  }

  public void setMap(Map theMapForGameSet){
    gameMap = theMapForGameSet;
  }

  public void addPlayer(Player thePlayerForGameAdd){
    gamePlayer = thePlayerForGameAdd;
    gamePlayer.setMap(gameMap);
  }
}

interface Player{
  public void setMap(Map theMapForPlayerSet);
  public void moveRight();
  public void moveLeft();
  public void moveUp();
  public void moveDown();
  public Position getPosition();
}

class MyPlayer implements Player{

  private Map currentMap;
  private Position currentPosition;
  private int playerDirection = 5;

  @Override
    public void setMap(Map theMapForPlayerSet){
     this.currentMap = theMapForPlayerSet;
     System.out.print(currentMap.getX() + " " + currentMap.getY() + " ");
      for(int i = 0; i < currentMap.getY(); i++){
        for(int j = 0; j < currentMap.getX(); j++){
          if(currentMap.getValueAt(j, i) == 'P'){
            System.out.println(currentMap.getValueAt(j, i));
            currentPosition = new Position(j, i);
            break;
          }
        }
      }
      System.out.println();
    }

  @Override
    public void moveRight(){
      if((currentMap.getX() !=  currentPosition.getX() + 1) && (currentMap.getValueAt(currentPosition.getX() + 1, currentPosition.getY()) == '0') && playerDirection == 6){
        currentPosition.setX(currentPosition.getX() + 1);
      }
      else{
        playerDirection = 6;
      }
    }

  @Override
    public void moveLeft(){
      if((currentPosition.getX() != 0) && (currentMap.getValueAt(currentPosition.getX() - 1, currentPosition.getY()) == '0') && playerDirection == 4){
        currentPosition.setX(currentPosition.getX() - 1);
      }
      else{
        playerDirection = 4;
      }
    }

  @Override
    public void moveUp(){
      if((currentPosition.getY() != 0) && (currentMap.getValueAt(currentPosition.getX(), currentPosition.getY() - 1) == '0') && playerDirection == 8){
        currentPosition.setY(currentPosition.getY() - 1);
      }
      else{
        playerDirection = 8;
      }
    }

  @Override
    public void moveDown(){
      if((currentMap.getY() != currentPosition.getY() + 1) && (currentMap.getValueAt(currentPosition.getX(), currentPosition.getY() + 1) == '0') && playerDirection == 2){
        currentPosition.setY(currentPosition.getY() + 1);
      }
      else{
        playerDirection = 2;
      }
    }

  @Override
    public Position getPosition(){
      return currentPosition;
    }
}

class Position{

  int mainX, mainY;

  public Position(int theX, int theY){
    this.mainX = theX;
    this.mainY = theY;
  }

  public void setX(int theXForPositionSet){
    this.mainX = theXForPositionSet;
  }

  public void setY(int theYForPositionSet){
    this.mainY = theYForPositionSet;
  }

  public int getX(){
    return this.mainX;
  }

  public int getY(){
    return this.mainY;
  }

  public boolean equals(Position thePositionForTest){
    if(this.getX() == thePositionForTest.getX()){
      if(this.getY() == thePositionForTest.getY()){
        return true;
      }
      else{
        return false;
      }
    }
    return false;
  }

  public String toString(){
    return "X is " + this.getX() + " , Y is " + this.getY() + ".";
  }
}
