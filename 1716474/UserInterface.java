/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//1716474 Chongfeng Ling17

package cw3;

import static cw3.CW3.getmyFlowers;
import static cw3.CW3.getmyWindow;
import static cw3.CW3.jframeClear;
import static cw3.CW3.setmyFlowers;
import static cw3.FileUtils.getBedmessage;
import static cw3.FileUtils.getFileNames;
import static cw3.FileUtils.imageFromString;
import static cw3.FileUtils.list;
import static cw3.FileUtils.readFFromFile;
import static cw3.FileUtils.saveFToFile;
import static cw3.FileUtils.showList;
import java.awt.Image;
import static java.lang.Integer.sum;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Scott
 */
public class UserInterface {
    private static Scanner kb;
    private static ArrayList<String> names;
    private static ArrayList<String> flowers;
    private static int bx;
    private static int by;
    private static int bw;
    private static int bh;
    private static int kinds;
    private static int patternNum;
        
    public static void showMenu(){ //menu
        kb = new Scanner(System.in);
        System.out.println("\nWelcome to my flower house!\n");
        System.out.println("Please select:");
        System.out.println("1.\tAdd flowerbed");
        System.out.println("2.\tRemove flowerbed");  
        System.out.println("3.\tSave and exit");
        int command = getIntInput(); //获得输入
        while(!(command>=1 & command<=3)){
            System.out.println("choose 1 to 3 please");
            command = getIntInput();
        }
        switch(command){
            case 1:
                getBedInput();
                getFlowerInput();
                CW3.addImage(bx, by, bw, bh, flowers);
                showMenu();
                break;
            case 2:
                System.out.println("which bed with its flowers do you want to move?");
                removeFlowerAndBed();
                showMenu();
                break;
            case 3:
                CW3.saveFlower();   
                getmyWindow().dispose();
                break;
        }
    }
    
    //判断输入是不是数�?
    private static int getIntInput(){//make sure the input is int
        int input = 0;
        try{
            input = Integer.parseInt(kb.nextLine());
        }
        catch(NumberFormatException e){
            System.out.println("That is not an int; "
                                + "please try again");
            input = getIntInput();
        }
        finally{
            return input;    
        }
    }
    
    private static void getBedInput(){//get the x,y,width,height of flowerbed
        System.out.println("Add bed.");
        
        System.out.println("Please input image width:");
        int width = getIntInput();
        while(!(width>= 100 & width<= 1880)){// make sure the input in an reasonable range
            System.out.println("You make an unsafe chosen, input from 100 and 1880, please! ");
            width = getIntInput();
        }
        bw = width;
        
        System.out.println("Please input image height:");
        int height = getIntInput();
        while(!(height>= 100 & height<= 1300)){// make sure the input in an reasonable range
            System.out.println("You make an unsafe chosen, input from 100 and 1300, please! ");
            height = getIntInput();
        }
        bh = height;
        
        System.out.println("Please input location x:");
        int x = getIntInput();
        while(!(sum(x,width)<= 1980)){// make sure the input in an reasonable range
            int max = 1980 - width;
            System.out.println("You make an unsafe chosen, input less than " + max);
            x = getIntInput();
        }
        bx = x;
        
        System.out.println("Please input location y:");
        int y = getIntInput();
        while(!(sum(y,height)<= 1400)){// make sure the input in an reasonable range
            int max = 1400 - height;
            System.out.println("You make an unsafe chosen, input less than " + max);
            y = getIntInput();
        }
        by = y;
        
        }
    
    public static void getFlowerInput(){//make decisions about the kinds ,pattern of flowers
        int i = 1;
        ArrayList<String> names = (ArrayList)getFileNames("res").clone();
        for(String s : names){//show the flowers user can use
            if(s.endsWith(".png")){
                System.out.println(i + " " + s);
                i++;
            }            
        }
        
        System.out.println("how many kinds of flowers do you want to input( no more than 7)?");
        kinds = getIntInput();
        while(!(kinds>= 1 & kinds<=7)){
            System.out.println("choose 1 to 7 please.");
            kinds = getIntInput();
        }
        
        flowers = new ArrayList(kinds);//store the path of flowers user chosen
        for(int a = 0; a < kinds; a++){
            System.out.println("which flower do you want to add?");
            i = 1;
            for(String s : names){
                if(s.endsWith(".png")){
                    System.out.println(i + " " + s);
                    i++;
                }
            }                                
            int id = getIntInput();
            while(!(id>= 1 & id<=7)){
            System.out.println("choose 1 to 7 please.");
            id = getIntInput();
        }
            String b = names.get(id);
            flowers.add(b);
            
            System.out.println("you choose " + b);
        }
        
        pattern();//choose the pattern( vertical or horizontal)
        
        System.out.println("Loading......");
    }
    public static void pattern(){
        System.out.println("What pattern do you want to choose?");
        System.out.println("1.Vertical stripes");
        System.out.println("2.Horizontal stripes");
        patternNum = getIntInput();
        while(!(patternNum>= 1 & patternNum<=2)){
            System.out.println("choose 1 or 2 please.");
            System.out.println("1.Vertical");
            System.out.println("2.Horizontal");
            patternNum = getIntInput();
        }
    }
    public static int getPatternNum(){
        return patternNum;
    }
    public static void setPatternNum(int n){
        patternNum = n;
    }
   
    private static void removeFlowerAndBed(){//remove
        showList(getmyFlowers());//show the x,y,width,height of flowerbeds
        int input = getIntInput();
        while(!(input>=1 & input <=getmyFlowers().size())){// make sure the input in an reasonable range
            System.out.println("choose from 1 to " + getmyFlowers().size());
            input = getIntInput();
        }
        String path = list.get(input - 1);
        System.out.println("You selected " + path);
        remove(input);
    }
    
    private static void remove(int input){//do the remove work
        String re = list.get(input - 1);
        int n = getmyFlowers().size();
        
        Iterator iterator = getmyFlowers().iterator();
        while(iterator.hasNext()){
            if(getBedmessage((String) iterator.next()).equals(re)){//remove the String in myFlower
                iterator.remove();
                break;
            }
        }

        jframeClear();//clear the jFrame
        
        ArrayList<String> newZoo = (ArrayList)getmyFlowers().clone();//clone from myFlower
        
        getmyFlowers().clear();// clear myFlower    aim to store the new message
        for(String r : newZoo){
            imageFromString(r);//translate the String to image
            getmyWindow().repaint();
        }
        
    }
        
}


    


        
    
    

