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
    if(this.getX == thePositionForTest.getX){
      if(this.getY == thePositionForTest.getY){
        return true;
      }
      else{
        return false;
      }
    }
    return false;
  }

  public String toString(){
    return "X is " + this.getX + " , Y is " + this.getY + ".";
  }
}
