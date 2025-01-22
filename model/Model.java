package model;

import java.util.ArrayList;

public class Model {

    private ArrayList<Piece> pieceList = new ArrayList<>();

    public void initialize(){
        pieceList.clear();
        addPieces();
    }

    private void addPieces() {
        pieceList.add(new Tor(0, 0, false));
        pieceList.add(new Biz(1, 0, false));
        pieceList.add(new Sau(2, 0, false));
        pieceList.add(new Biz(3, 0, false));
        pieceList.add(new Xor(4, 0, false));
        
        pieceList.add(new Ram(0, 1, false));
        pieceList.add(new Ram(1, 1, false));
        pieceList.add(new Ram(2, 1, false));
        pieceList.add(new Ram(3, 1, false));
        pieceList.add(new Ram(4, 1, false));

        pieceList.add(new Tor(0, 7, true));
        pieceList.add(new Biz(1, 7, true));
        pieceList.add(new Sau(2, 7, true));                                                                                                                                   /*1211106208*/
        pieceList.add(new Biz(3, 7, true));
        pieceList.add(new Xor(4, 7, true));
        
        pieceList.add(new Ram(0, 6, true));
        pieceList.add(new Ram(1, 6, true));
        pieceList.add(new Ram(2, 6, true));
        pieceList.add(new Ram(3, 6, true));
        pieceList.add(new Ram(4, 6, true));

        Piece.model = this;
    }

    public ArrayList<Piece> getArrayList(){
        return pieceList;
    }

    public Piece getPiece (int col, int row){
        for (Piece piece: pieceList){
            if (piece.getCol() == col && piece.getRow() == row){
                return piece;
            }
        }
        return null;
    }
    
}
