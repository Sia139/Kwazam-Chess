//Input.java
package controller;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import model.Piece;
import model.Ram;
import utils.Global;


public class Input extends MouseAdapter implements ActionListener, ComponentListener{

    private Controller controller;

    public Input(Controller controller){
        this.controller = controller;
    }

    @Override
    public void mousePressed(MouseEvent e){
        
        int col = e.getX() / Global.titleSize;
        int row = e.getY() / Global.titleSize;
        
        if (Global.isFlip) {
            col = 4 - col;
            row = 7 - row;
        }
        
        // System.out.println("1QWQWQWQ2WQWQWQ1WQWQ1WQ1WQWQWQ0WQ6WQWQWQ2WQWQWQWQW0QWQQ8WQW");

        Piece pieceXY = controller.getModel().getPiece(col, row);

        boolean blueTurn = controller.turn % 2 == 0 ? true : false;

        if (pieceXY != null && blueTurn == pieceXY.getIsBlue()){
            controller.setResetSelectedPiece(pieceXY); //set selected piece to pieceXY
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
        
        int col = e.getX();
        int row = e.getY();
        
        if (Global.isFlip) {
            col = 5 * Global.titleSize - col;
            row = 8 * Global.titleSize - row;
        }

        if (controller.selectedPiece != null){
            controller.selectedPiece.setXPos(col - Global.titleSize / 2); 
            controller.selectedPiece.setYPos(row - Global.titleSize / 2);
            controller.getView().paintValidMove();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e){
    
        int col = e.getX() / Global.titleSize;
        int row = e.getY() / Global.titleSize;
        
        if (Global.isFlip) {
            col = 4 - col;
            row = 7 - row;
        }
        

        if (controller.selectedPiece != null){
            
            Move move = new Move(controller, col, row); //set up move to pass parameter

            if (controller.isVaildMove(move) && (col >= 0 && col < 5 && row >= 0 && row < 8)) {

                if(row == 0 || row == 7){
                    if (controller.selectedPiece instanceof Ram){
                        Ram ram = (Ram) controller.selectedPiece;
                        ram.setReachEnd();
                    }
                }

                controller.changePiecePosition(move);
                controller.setResetSelectedPiece(null);
                controller.getView().getPanel().paintValidMove();
                controller.winOrNextTurn(move); 
                
            }
            else{
                controller.selectedPiece.setXPos(controller.selectedPiece.getCol() * Global.titleSize);
                controller.selectedPiece.setYPos(controller.selectedPiece.getRow() * Global.titleSize);
            }
            
        }
        
        controller.setResetSelectedPiece(null);
        controller.getView().getPanel().paintValidMove(); //in order to clear the valid move's green colour and repaint
    }
    
    @Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == controller.getView().getMp().getNewMenuItem()) {
            controller.gameStart();
        }
        else if (e.getSource() == controller.getView().getMp().getSaveMenuItem()) {
            JOptionPane.showMessageDialog(null, "Game saved");
            controller.saveGame();
        }
        else if (e.getSource() == controller.getView().getMp().getOpenMenuItem()){
            controller.loadGame();
        }
        else if (e.getSource() == controller.getView().getMp().getExitMenuItem()) {
            controller.getView().handleExit();
        }
	}

    @Override
    public void componentResized(ComponentEvent e) { 

        Dimension newSize = e.getComponent().getBounds().getSize();
        double frameWidth = newSize.getWidth();
        double frameHeight = newSize.getHeight();
    
        int widthSize = (int) (frameWidth * 0.1545); // (85/550 = 0.1545)
        int heightSize = (int) ((frameHeight - (controller.getView().getMp().getHeight() + 37)) * 0.1056); // (85/805 = 0.1056) total 100 for the header 23 for menu panel and 37 for the title header so total need to offset [mp.getHeight + 37(fixed)]
    
        Global.titleSize = widthSize >= heightSize ? heightSize : widthSize;

        controller.getView().getPanel().setPreferredSize(new Dimension(Global.titleSize*5, Global.titleSize*8));

        for(Piece piece : controller.getPieceList()){
            piece.setXPos(piece.getCol() * Global.titleSize);
            piece.setYPos(piece.getRow() * Global.titleSize);
        }

        controller.getView().getPanel().revalidate();
    }

    @Override
    public void componentMoved(ComponentEvent e) { }

    @Override
    public void componentShown(ComponentEvent e) { }

    @Override
    public void componentHidden(ComponentEvent e) { }
    
}
