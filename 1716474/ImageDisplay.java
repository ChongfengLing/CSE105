/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//1716474 Chongfeng Ling17

package cw3;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author Scott
 */
public class ImageDisplay extends JPanel {//extend from JPanl. add the inage with variables
    private int myX, myY;
    private int myWidth, myHeight;
    private Image myImage;
    private String imagePath;
    
    
    //图片输入
    public ImageDisplay(String imagePath,
                        int myX, int myY, 
                        int myWidth, int myHeight) {
        super();
        this.myX = myX;
        this.myY = myY;
        this.myWidth = myWidth;
        this.myHeight = myHeight;
        this.imagePath = imagePath;
        this.setOpaque(false);//不透明
        myImage = FileUtils.loadImage(imagePath);
        
    }
    
        @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(myImage, 0, 0, myWidth, myHeight, this);
    }

    @Override
    public int getX() {
        return myX;
    }

    @Override
    public int getY() {
        return myY;
    }

    @Override
    public int getWidth() {
        return myWidth;
    }

    @Override
    public int getHeight() {
        return myHeight;
    }
    
//    public void setX(int n){
//        myX = n;
//    }
//    public void setY(int n){
//        myY = n;
//    }
//    public void setH(int n){
//        myHeight = n;
//    }
//    public void setW(int n){
//        myWidth = n;
//    }

    public String toString(){
        return imagePath.charAt(4) + "Display," + "X=" + myX + ",Y=" + myY + ",Width=" + myWidth + ",Height=" + myHeight + ",myPath=" + imagePath; 
    }
}
