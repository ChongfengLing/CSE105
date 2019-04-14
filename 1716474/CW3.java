/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//1716474 Chongfeng Ling17
package cw3;


//import static cw3.UserInterface.getFlowers;
import static cw3.UserInterface.getPatternNum;
import static cw3.UserInterface.showMenu;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;


/**
 *
 * @author Scott
 */
public class CW3 {
    private static JFrame myWindow;
    private static ArrayList<String> myFlowers = new ArrayList<>();//控制输出到txt中 store the message of flowers that user chooses

    public static void main(String[] args) {// set a JFame control the project
        myWindow = new JFrame("FFFFFlowers!!!");
        myWindow.setVisible(true);
        myWindow.setSize(1980, 1400);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.getContentPane().setBackground(new Color(100,50,100));
        FileUtils.readFFromFile();
        showMenu();
    }
    
    public static void addImage(int x, int y, int width, int height, ArrayList<String> flowers){//add the flower and flowers together 
        ImageDisplay bedImage;
        bedImage = new ImageDisplay("res/bed.jpg", x, y, width, height);
        
        int num = flowers.size();//有几个图           
        
        if(getPatternNum() == 2){  //Vertical stripes
            int p = (height / 2) / num ;//每个图的高、宽
            int o = width / p;
        
            for(int i = 0; i < 2; i++){  //2 副  set 
                for(int j = 0; j < num; j++){//height/butongdehua
                    for(int k = 0; k < o; k++){//width
                        ImageDisplay flowerImage;
                        flowerImage = new ImageDisplay(flowers.get(j), x + k * p, y + num * p * i + p * j, p, p);
                        myWindow.add(flowerImage);
                        myWindow.repaint();
                    }
                }
            }
        }
        if(getPatternNum() == 1){//Horizontal stripes
            int p = (width / 2) / num ;//每个图的高、宽
            int o = height / p;
        
            for(int i = 0; i < 2; i++){  //2 副
                for(int j = 0; j < num; j++){//height/butongdehua
                    for(int k = 0; k < o; k++){//width
                        ImageDisplay flowerImage;
                        flowerImage = new ImageDisplay(flowers.get(j), x + + num * p * i + p * j, y + k * p, p, p);
                        myWindow.add(flowerImage);
                        myWindow.repaint();
                    }    
                }
            }
        }
        
        String s = "";//get the String(flowers' name)
        for(int q = 0; q < num; q++){
            s = s + "," + flowers.get(q);            
        }
        myWindow.add(bedImage);
        myWindow.repaint();
        myFlowers.add(bedImage + s + "," + getPatternNum());//store bed(x,y,width,height) flowers'name pattern together
        System.out.println("now my house has " +myFlowers);
    }
    
    public static void saveFlower(){//save
        FileUtils.saveFToFile(myFlowers);
    }
                
    public static ArrayList<String> getmyFlowers() {
        return myFlowers;
    }
    public static void setmyFlowers(ArrayList<String> n){
        ArrayList<String> newZoo = (ArrayList)getmyFlowers().clone();
        myFlowers = (ArrayList)n.clone();
    }
    
    public static void jframeClear(){//clear the jFrame
        myWindow.getContentPane().removeAll();
        myWindow.repaint();    
    }
    
    public static JFrame getmyWindow(){
        return myWindow;
    }
    
}
