//Tor.java
package model;
public class Tor extends Piece
{
    public Tor(int col, int row, boolean isBlue)
    {
        super(col, row, isBlue, "Tor");
        
        if (isBlue) {
            this.sprite =  loadSprite("src/bTor.png");
        }
        else {
            this.sprite = loadSprite("src/rTor.png"); /*1211106208*/ 
        }
    }
    
    //Lee,Sia,Yeoh
    private boolean isPathClear(int newCol, int newRow) {
        int col = this.getCol();
        int row = this.getRow();
    
        int colStep = Integer.signum(newCol - col);
        int rowStep = Integer.signum(newRow - row);
    
        col += colStep;
        row += rowStep;
    
        while (col != newCol || row != newRow) {
            if (model.getPiece(col, row) != null) {
                return false;
            }
            col += colStep;
            row += rowStep;
        }
    
        return true;
    }
    
    //Lee,Sia,Yeoh
    @Override
    public boolean canMoveTo(int newCol, int newRow) {

        int rowDiff = Math.abs(newRow - this.getRow());
        int colDiff = Math.abs(newCol - this.getCol());
    
        // Orthogonal movement for Tor
        if (this.getName().equals("Tor") && (rowDiff == 0 || colDiff == 0)) {
            return isPathClear(newCol, newRow);
        }
    
        return false;
    }
    
}

