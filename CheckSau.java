package controller;

import model.Piece;
import java.util.ArrayList;

public class CheckSau {

    private ArrayList<Piece> pieceList;

    public CheckSau(ArrayList<Piece> pieceList){                                                                          
        this.pieceList = pieceList;
    }
    
    //Sia,Yeoh
    public boolean isCheck(boolean isBlue) {
        Piece sau = findSau(isBlue); 

        for (Piece piece : pieceList) {
            if (piece.getIsBlue() != isBlue) { // Opponent's piece
                if (piece.canMoveTo(sau.getCol(), sau.getRow())) {
                    return true; // Sau is in check
                }
            }
        }

        return false; // No opponent piece can attack Sau
    }

    //Sia,Yeoh
    private Piece findSau(boolean isBlue) {
        for (Piece piece : pieceList) {
            if (piece.getName().equals("Sau") && piece.getIsBlue() == isBlue) {
                    return piece;
            }
        }
        return null; // Sau not found
    }
}
