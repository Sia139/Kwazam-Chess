package model;

public class PieceFactory {
    public Piece getPiece(String name, int col, int row, boolean isBlue){
        switch(name){                                                                                                                              //1211106319
            case "Tor" : 
                return new Tor(col, row, isBlue);
            case "Xor" :
                return new Xor(col, row, isBlue);
            case "Ram" :
                return new Ram(col, row, isBlue);
            case "Biz" :
                return new Biz(col, row, isBlue);
            case "Sau" :
                return new Sau(col, row, isBlue);
        }
        return null;
    }
}