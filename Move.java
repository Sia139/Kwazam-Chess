//Move.java
package controller;
import model.Piece;

public class Move {
    protected int oldCol;
    protected int oldRow;

    protected int newCol;
    protected int newRow;

    protected Piece piece;
    protected Piece capture;
    
    //Ngo
    public Move(Controller controller, int newCol, int newRow){ //constructor
        this.piece = controller.selectedPiece;

        this.oldCol = controller.selectedPiece.getCol();
        this.oldRow = controller.selectedPiece.getRow();

        this.newCol = newCol;
        this.newRow = newRow;

        this.capture = controller.getModel().getPiece(newCol, newRow);
    }
    
}
