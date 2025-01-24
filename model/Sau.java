//Sau.java
package model;
public class Sau extends Piece {

    public Sau(int col, int row, boolean isBlue)
    {
        super(col, row, isBlue, "Sau");
        
        if (isBlue) {
            this.sprite = loadSprite("src/bSau.png");
        }
        else {
            this.sprite = loadSprite("src/rSau.png");
        }
    }
    
    //Ngo,Sia,Yeoh
    @Override
    public boolean canMoveTo(int newCol, int newRow) {
        int rowDiff = Math.abs(newRow - this.getRow());
        int colDiff = Math.abs(newCol - this.getCol());

        // One step in any direction
        if (rowDiff <= 1 && colDiff <= 1) {
            return true;
        }

        return false;
    }

}
