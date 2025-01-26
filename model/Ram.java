//Ram.java
package model;
public class Ram extends Piece {

    int reachEnd;

    public Ram(int col, int row, boolean isBlue){
        super(col, row, isBlue, "Ram");
        
        reachEnd = 1;
        
        if (isBlue) {
            this.sprite = loadSprite("src/bRam.png");
        }
        else {
            this.sprite = loadSprite("src/rRam.png");
        }
    }

    //Lee,Sia,Yeoh
    @Override // Ram
    public boolean canMoveTo(int newCol, int newRow) {
        int currentRow = this.getRow();
        int currentCol = this.getCol();

        // Calculate row and column differences
        int rowDiff = newRow - currentRow;
        int colDiff = newCol - currentCol;

        // Determine movement direction based on color
        int direction = this.getIsBlue() ? -1 : 1;
    
        if (this.getRow() == 0) {
            this.reachEnd = direction;
        }
        
        if (this.getRow() == 7) {
            this.reachEnd = -direction;
        }

        // Movement logic
        if (colDiff == 0 && rowDiff == direction * reachEnd) {
            return true;
        }

        // If no valid move
        return false;
    }

    //1211106818
    //Lee,Sia,Yeoh
    public void setReachEnd() {
        this.reachEnd = reachEnd == -1 ? 1 : -1;
    }
    
    //Lee,Sia,Yeoh
    public int getReachEnd() {
        return reachEnd;
    }

  
}