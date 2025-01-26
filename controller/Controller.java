//Controller.java
package controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.Model;
import model.Piece;
import model.PieceFactory;
import model.Tor;
import model.Xor;
import model.Ram;
import utils.Global;
import view.View;

public class Controller {

    private Model model;
    private View view;
    private ArrayList<Piece> pieceList;
    private Input input;
    private boolean winFlag;
    public Piece selectedPiece;
    private CheckSau checkSau;
    public int turn;

    public Controller(){
        this.view = new View(this);
        this.model = new Model();
        this.input = new Input(this);
        turn = 0;
        gameStart();
        addListener();
    }

    //Ngo
    public void addListener(){
        view.getPanel().addMouseListener(input); //use controller add mouse listener to panel
        view.getPanel().addMouseMotionListener(input);
        view.getFrame().addComponentListener(input);
        view.getMp().getNewMenuItem().addActionListener(input);
        view.getMp().getSaveMenuItem().addActionListener(input);
        view.getMp().getOpenMenuItem().addActionListener(input);
        view.getMp().getExitMenuItem().addActionListener(input);
        view.getFrame().addWindowListener(new java.awt.event.WindowAdapter(){ //1211106865
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                view.handleExit();
            }
        });
    }
    
    //1211106818
    //Lee
    public void gameStart(){
    
        winFlag = false;
        turn = 0;
        selectedPiece = null;
        Global.isFlip = false;

        model.initialize();
        pieceList = model.getArrayList(); // Get pieces from the model
        checkSau = new CheckSau(pieceList);
        view.paint(pieceList);
    }

    //1211106208
    //Lee,Sia
    public boolean isVaildMove(Move move) {
        if (sameTeam(move.piece, move.capture)) {
            return false;
        }
    
        if (!move.piece.canMoveTo(move.newCol, move.newRow)) {
            return false;
        }


        return true;
    }
    
    //Lee
    public boolean sameTeam(Piece p1, Piece p2){
        if (p1 == null || p2 ==null) 
            return false;
        return (p1.getIsBlue() == p2.getIsBlue());
    }

    //Sia
    public void changePiecePosition(Move move){
        move.piece.setCol(move.newCol);
        move.piece.setRow(move.newRow);
        move.piece.setXPos(move.newCol * Global.titleSize);
        move.piece.setYPos(move.newRow * Global.titleSize);
        capture(move.capture);
    }
    
    //Sia
    public void capture(Piece piece){
        if ( piece != null && piece.getName().equals("Sau")){
            winFlag = true;
            System.out.println("Sau captured! Game over.");
        }
        pieceList.remove(piece); //null or piece
    }

    //Sia
    public void winOrNextTurn(Move move){

        if(winFlag){
            view.printWinner(turn%2);
            winFlag = false;
        }

        else{ //next turn

            turn++;
            toggleFlip();
            if (turn % 4 == 0 && turn != 0){
                transformPieces();
            }
        
            //Check Sau
            boolean isInCheck = checkSau.isCheck(!move.piece.getIsBlue()); //isBlue = true                  
    
            if (isInCheck){
                view.printSauIsInCheck(!move.piece.getIsBlue());
            }

        }
    }

    //Sia
    public void setResetSelectedPiece(Piece piece){
        this.selectedPiece = piece; //set up controller selected piece
        view.setSelectedPiece(piece); // set up view -> panel selected piece
    }

    //Ngo
    public Model getModel() {
        return model;
    }

    //Ngo
    public View getView() {
        return view;
    }

    //Ngo
    public ArrayList<Piece> getPieceList(){
        return pieceList;
    }

    //Ngo
    public Piece getPiece(int col, int row){
        return model.getPiece(col, row);
    }

    //Lee
    public void transformPieces() {
        for (Piece x : new ArrayList<>(pieceList)) { //deep copy
            if (x.getName().equals("Xor")) {
                transform(x);
            }
            if (x.getName().equals("Tor")) {
                transform(x);
            }
        }
    }

    //Lee
    public void transform(Piece piece) { // controller
        if (piece.getName() == "Xor")
            pieceList.add(new Tor(piece.getCol(), piece.getRow(), piece.getIsBlue()));

        else if (piece.getName() == "Tor")
            pieceList.add(new Xor(piece.getCol(), piece.getRow(), piece.getIsBlue()));
        
        capture(piece);
    }

    //Lee
    public void toggleFlip() {
        Global.isFlip = !Global.isFlip;
        view.getPanel().repaint();
    }
    
    //1211106319
    //Yeoh
    public void saveGame(){
        String name, col, row, isBlue;

        try (BufferedWriter clear = new BufferedWriter(new FileWriter("model\\src\\data.txt", false))) {/*1211106208*/
            clear.write("");
        } 
        catch (IOException e) {
            System.out.println("Error writing to data file: " + e.getMessage());
        }

        //Append new data
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("model\\src\\data.txt", true))) {
            
            for(int i = 0; i < pieceList.size(); i ++){
                Piece piece = pieceList.get(i);
                name = piece.getName();
                col = String.valueOf(piece.getCol());
                row = String.valueOf(piece.getRow());
                isBlue = Boolean.toString(piece.getIsBlue());
                bw.write(name + "," + col + "," + row + "," + isBlue);
                bw.newLine();
            }
            bw.write(String.valueOf(turn));
            bw.newLine();
        }
        catch (IOException e) {
            System.out.println("Error writing to data file: " + e.getMessage());
        }
    }

    //Yeoh
    public void loadGame(){
        String name;
        int col;
        int row;
        boolean isBlue;
        int loadTurn = 0;
        PieceFactory pieceFactory = new PieceFactory();
        
        try(BufferedReader br = new BufferedReader(new FileReader("model\\src\\data.txt"))){
            pieceList.clear();
            String line;
            while((line = br.readLine())!= null){
                String[] parts = line.split(",");

                if(parts.length == 4){
                    name = parts[0];
                    col = Integer.parseInt(parts[1]);
                    row = Integer.parseInt(parts[2]);
                    isBlue = Boolean.parseBoolean(parts[3]);
                    Piece piece = pieceFactory.getPiece(name,col,row,isBlue);
                    // pieceList.add(pieceFactory.getPiece(name,col,row,isBlue));
                    pieceList.add(piece);

                    if (name.equals("Ram")){
                        Ram ram = (Ram) piece;
                        if (ram.getRow() == 0 || ram.getRow() == 7){
                            ram.setReachEnd();
                        }
                    }
                }

                else if(parts.length == 1){ //get turn 
                    loadTurn = Integer.parseInt(parts[0]);
                }

                //Only flip the chessboard when needed
                if((loadTurn % 2 == 1 && turn % 2 == 0)||(loadTurn % 2 == 0 && turn % 2 == 1)){
                    toggleFlip();
                }

                //Update current turn
                turn = loadTurn;

                view.paint(pieceList);
            }
        }
        
        catch(IOException e){
            System.out.println("Error reading the file");
        }
    }

}
