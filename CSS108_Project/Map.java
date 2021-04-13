package game_project;
class Map extends InvalidMapException{

  char[][] mainMap;
  int howManyLines = 0;

  public Map(Scanner input){
    input = new Scanner(new File(new Scanner(System.in).nextLine()));
    while(input.nextLine() != ""){
      howManyLines++;
    }
    mainMap = new char[howManyLines][input.nextLine().length()];

    for(int i = 0; i < howManyLines; i++){
      for(int j = 0; j < input.nextLine().length(); j++){
        mainMap[i][j] = input.next();
      }
    }
  }

  public int getSize(){
    return input.nextLine().length() * howManyLines;
  }

  public char getValueAt(int theX, int theY){
    return mainMap[theY][theX];
  }

  public void print(){
    for(int i = 0; i < howManyLines; i++){
      for(int j = 0; j < input.nextLine().length(); j++){
        System.out.print(mainMap[i][j]);
      }
    }
  }
}
