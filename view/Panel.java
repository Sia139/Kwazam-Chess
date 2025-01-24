//Panel.java
package view;
import model.Piece;
import model.Ram;
import utils.Global;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class Panel extends JPanel {

    private int cols;
    private int rows;

    protected Piece selectedPiece; // Currently selected piece
    private ArrayList<Point> validMoves; // Valid moves for the selected piece (point is coordinte)
    private View view;

    public Panel(View view) {
        cols = 5;
        rows = 8;
        selectedPiece = null;
        validMoves = new ArrayList<>();
        this.view = view;
        setPreferredSize(new Dimension(Global.titleSize*cols, Global.titleSize*rows));
    }

    //Ngo
    private void calculateValidMoves(){
        validMoves.clear();

        if (selectedPiece != null){
            int currentRow = selectedPiece.getRow();
            int currentCol = selectedPiece.getCol(); 

            for (int row = 0; row < rows; row++){
                for (int col = 0; col < cols; col++) {

                    if ( col == currentCol && row == currentRow){ //self position
                        continue; 
                    }

                    boolean canMove = selectedPiece.canMoveTo(col, row);

                    Piece thatPositionPiece = view.controller.getPiece(col, row);

                    if (canMove && (thatPositionPiece == null || thatPositionPiece.getIsBlue() !=  selectedPiece.getIsBlue()) ){
                        validMoves.add(new Point(col, row));
                    }

                }
            }
        }
    }

    //Ngo
    public void paintValidMove(){
        calculateValidMoves();
        repaint();
    }

    //Lee,Sia
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Clear the panel and prepare for drawing
        Graphics2D g2d = (Graphics2D) g;

        if (Global.isFlip) {
            // Flip the board
            g2d.translate(cols * Global.titleSize, rows * Global.titleSize);
            g2d.scale(-1, -1);
        }

        // Draw the chessboard
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                g2d.setColor((c + r) % 2 == 0 ? new Color(227, 198, 181) : new Color(157, 105, 53));
                g2d.fillRect(c * Global.titleSize, r * Global.titleSize, Global.titleSize, Global.titleSize);
            }
        }

        // Highlight valid moves
        g2d.setColor(new Color(0, 255, 0, 128));
        for (Point move : validMoves) {
            g2d.fillRect(move.x * Global.titleSize, move.y * Global.titleSize, Global.titleSize, Global.titleSize);
        }

        // Draw the pieces
        for (Piece piece : view.pieceList) {
            BufferedImage sprite = piece.getSprite();
            if (sprite != null) {
                int xPos = piece.getXPos();
                int yPos = piece.getYPos();

                //  Check if the piece is a Ram and if it needs rotation
                if (piece instanceof Ram) {
                    Ram ram = (Ram) piece; //downCasting
                    if (ram.getReachEnd() == -1) { // Rotate inversely
                        g2d.rotate(Math.toRadians(180), xPos + Global.titleSize / 2, yPos + Global.titleSize / 2);
                    }
                }

                g2d.drawImage(sprite, xPos, yPos, Global.titleSize, Global.titleSize, null);

                // Reset rotation after drawing the Ram
                if (piece instanceof Ram && ((Ram) piece).getReachEnd() == -1) {
                    g2d.rotate(Math.toRadians(-180), xPos + Global.titleSize / 2, yPos + Global.titleSize / 2);
                }

            }
            else {
                System.err.println("Sprite is null for piece: " + piece.getName());
            }
        }

        if (Global.isFlip)
            g2d.setTransform(new java.awt.geom.AffineTransform());
    }

    //Sia
    public int getCols(){
        return cols;
    }

    //Sia
    public int getRows(){
        return rows;
    }
}
