//Biz.java
package model;

public class Biz extends Piece {

    public Biz(int col, int row, boolean isBlue){
        super(col, row, isBlue, "Biz");
        
        if (isBlue) {
            this.sprite = loadSprite("src/bBiz.png");
        }
        else {
            this.sprite = loadSprite("src/rBiz.png");
        }
    }

    @Override //Biz
    public boolean canMoveTo(int newCol, int newRow) {
        int rowDiff = Math.abs(newRow - this.getRow());
        int colDiff = Math.abs(newCol - this.getCol());

        // Check L-shape move
        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            return true;
        }

        return false;
    }

    
}
