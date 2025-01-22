//Xor.java
package model;
public class Xor extends Piece
{
    public Xor(int col, int row, boolean isBlue)
    {
        super(col, row, isBlue, "Xor");
        
        if (isBlue) {
            this.sprite = loadSprite("src/bXor.png");
        }
        else {
            this.sprite = loadSprite("src/rXor.png");
        }
    }

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
    
    @Override
    public boolean canMoveTo(int newCol, int newRow) {
        int rowDiff = Math.abs(newRow - this.getRow());
        int colDiff = Math.abs(newCol - this.getCol());
    
        if (this.getName().equals("Xor") && rowDiff == colDiff) {
            return isPathClear(newCol, newRow);
        }
    
        return false;
    }
    
    
}
