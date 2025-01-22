//Piece.java
package model;
import utils.Global;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;


public abstract class Piece {

    private int col, row;
    private int xPos, yPos;

    private boolean isBlue;
    private String name;

    public static Model model; 

    BufferedImage sprite;

    public Piece(int col, int row, boolean isBlue, String name){ //1211106208
        this.col = col;
        this.row = row;
        this.xPos = col * Global.titleSize;
        this.yPos = row * Global.titleSize;
        this.isBlue = isBlue;
        this.name = name;
    }

    public BufferedImage loadSprite(String path) {
        try {
            return ImageIO.read(Piece.class.getResourceAsStream(path));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load the sprite image from: " + path);
        }
    }

    public void setCol(int col){
        this.col = col;
    }

    public void setRow(int row){
        this.row = row;
    }

    public void setXPos(int xPos){
        this.xPos = xPos;
    }

    public void setYPos(int yPos){
        this.yPos = yPos;
    }

    public void setIsBlue(boolean isBlue){
        this.isBlue = isBlue;
    }

    public void setName(String name){
        this.name = name;
    }

    public BufferedImage getSprite(){
        return sprite;
    }

    public int getCol(){
        return col;
    }

    public int getRow(){
        return row;
    }

    public int getXPos(){
        return xPos;
    }

    public int getYPos(){
        return yPos;
    }

    public boolean getIsBlue(){
        return isBlue;
    }
    
    public String getName(){
        return name;
    }

    // public boolean canMoveTo(int newCol, int newRow){
    //     return false;
    // }

    public abstract boolean canMoveTo(int newCol, int newRow);
}
