//View.java
package view;

import model.Piece;
import controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View {

    protected JFrame frame;
    private Panel panel;
    public Controller controller;
    protected ArrayList<Piece> pieceList;
    private MenuPanel mp;

    public View(Controller controller){

        this.controller = controller;
        this.pieceList = new ArrayList<>();
        
        frame = new JFrame("Chess Game");
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setSize(new Dimension(550, 805));
        
        panel = new Panel(this);
        mp = new MenuPanel();

        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0; // Small vertical weight for the title
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(mp, gbc);
        
        // Add Panel in the center
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; // Major vertical weight for the game panel
        gbc.fill = GridBagConstraints.CENTER;
        frame.add(panel, gbc);
                
        frame.setLocationRelativeTo(null); //display in center of the screen
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Panel getPanel(){
        return panel;
    }

    public MenuPanel getMp(){
        return mp;
    }

    public JFrame getFrame(){
        return frame;
    }

    public void paint(ArrayList<Piece> pieceList) {
        this.pieceList = pieceList;
        panel.paintPiece(); // Pass the pieces to the panel
    }

    public int getCols(){
        return panel.getCols();
    }

    public int getRows(){
        return panel.getRows();
    }
    
    public void setSelectedPiece (Piece selectedPiece){
        panel.selectedPiece = selectedPiece;
    }

    public void paintValidMove(){
        panel.paintValidMove();
    }

    public void printWinner(int winner){
        int choice = Integer.MAX_VALUE;
        if(winner == 0){
            choice = JOptionPane.showConfirmDialog(null,"Blue win!\nYes for new game\nNo for exit",
            "Exit Confirmation",JOptionPane.YES_NO_OPTION);
        }
        else if(winner == 1){
            choice = JOptionPane.showConfirmDialog(null,"Red win!\nYes for new game\nNo for exit",
            "Exit Confirmation",JOptionPane.YES_NO_OPTION);
        }
        
        if(choice == JOptionPane.YES_OPTION){
            controller.gameStart();
        }
        else if (choice == JOptionPane.NO_OPTION){
            System.exit(0);
        }
    }

    public void printSauIsInCheck(boolean isBlue){
        if(isBlue){
            JOptionPane.showMessageDialog(null, "Blue Sau is in check");
        }
        else{
            JOptionPane.showMessageDialog(null, "Red Sau is in check");
        }
    }

    public void handleExit() {
        // Confirm before exiting
        int choice = JOptionPane.showConfirmDialog(
            null,
            "Do you want to save your game before exiting?",
            "Exit Confirmation",
            JOptionPane.YES_NO_CANCEL_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            controller.saveGame(); // Save the game
            System.exit(0);
        } else if (choice == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

}