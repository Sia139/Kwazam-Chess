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
    BufferedImage sprite;

    public static Model model; 


    public Piece(int col, int row, boolean isBlue, String name){ //1211106208
        this.col = col;
        this.row = row;
        this.xPos = col * Global.titleSize;
        this.yPos = row * Global.titleSize;
        this.isBlue = isBlue;
        this.name = name;
    }

    //Ngo
    public BufferedImage loadSprite(String path) {
        try {
            return ImageIO.read(Piece.class.getResourceAsStream(path));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load the sprite image from: " + path);
        }
    }

    //Ngo
    public void setCol(int col){
        this.col = col;
    }

    //Ngo
    public void setRow(int row){
        this.row = row;
    }

    //Ngo
    public void setXPos(int xPos){
        this.xPos = xPos;
    }

    //Ngo
    public void setYPos(int yPos){
        this.yPos = yPos;
    }

    //Ngo
    public void setIsBlue(boolean isBlue){
        this.isBlue = isBlue;
    }

    //Ngo
    public void setName(String name){
        this.name = name;
    }

    //Yeoh
    public BufferedImage getSprite(){
        return sprite;
    }

    //Yeoh
    public int getCol(){
        return col;
    }

    //Yeoh
    public int getRow(){
        return row;
    }

    //Yeoh
    public int getXPos(){
        return xPos;
    }

    //Yeoh
    public int getYPos(){
        return yPos;
    }

    //Yeoh
    public boolean getIsBlue(){
        return isBlue;
    }
    
    //Yeoh
    public String getName(){
        return name;
    }

    //Lee
    public abstract boolean canMoveTo(int newCol, int newRow);
}
