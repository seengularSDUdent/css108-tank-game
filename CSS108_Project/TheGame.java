import java.util.*;
import java.io.*;

public class TheGame {
  public static void main(String[] args) throws Exception{
    java.io.File file = new java.io.File("map.txt");
    Scanner input = new Scanner(file);
/*  Map map = new Map(input);
    map.print();
    System.out.println(map.getValueAt(map.getX()/2, map.getY()/2)); */

    
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
