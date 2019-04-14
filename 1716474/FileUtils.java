/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//1716474 Chongfeng Ling17

package cw3;

import static cw3.UserInterface.setPatternNum;
import static cw3.UserInterface.showMenu;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Scott
 */
public class FileUtils {
    public static ArrayList<String> list = new ArrayList();
    // 按照路径加载图片
    public static BufferedImage loadImage(String imagePath){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imagePath));
//            System.out.println("Image loaded: "+imagePath);
        } catch (IOException e) {
            System.out.println("Problem loading image: "+imagePath);
            e.printStackTrace();
        }
        return image;
    }
    
    public static ArrayList<String> getFileNames(String dirPath){//get the files' name(in /res)
        ArrayList<String> names = new ArrayList<String>();
        File dir = new File(dirPath);
        if(dir.isDirectory()){
            for(File f: dir.listFiles()){
                names.add(f.getPath());
            }
        }
        return names;
    }
    public static boolean saveFToFile(ArrayList<String> toWrite) {
        try (FileWriter fw = new FileWriter("flower.txt", false);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
                    for(String im: toWrite){
                        out.println(im.toString());
                    }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Flower saved to file");
        return true; 
    }
    
    public static void readFFromFile(){
        ArrayList list = new ArrayList(30);
        int i = 0;
        Path filePath = Paths.get("flower.txt");
        if(!Files.exists(filePath)){
            System.out.println("There is no file to read from.");
        }
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if(line.startsWith("bDisplay")){
                    imageFromString(line); // 显示图片 一条line
                }
                else{
                    System.out.println("Bad line in file: "+line);
                }   
            }
        } catch (IOException e) {
            System.out.println("Error reading file:" + filePath);
            e.printStackTrace();
        }
    }
    
    public static void imageFromString(String line){//translate the line(String) to image
        String[] data = line.split(",");//分割line 赋值给变量
        int x = Integer.parseInt(data[1].substring(2));
        int y = Integer.parseInt(data[2].substring(2));
        int h= Integer.parseInt(data[3].substring(6));
        int w = Integer.parseInt(data[4].substring(7));
        
        int n = data.length - 1;//总长�?
        ArrayList<String> f = new ArrayList<>();
        for(int i = 6; i < n; i++){//读取种在bed上的�? 路径、数�?
            f.add(data[i]);         
        }
        setPatternNum(Integer.parseInt(data[n]));
       CW3.addImage(x, y, w, h, f);
    }
    
    public static String getBedmessage(String line){ //return the x,y,width,height of flowerbed
        String[] data = line.split(",");
        return data[1] + " " + data[2] + " " + data[3] + " " + data[4];
    }
        
    public static void showList(ArrayList<String> f){//store bedmassage in the list(ArrayList) and show them in order
        list.clear();
        if(!(f.isEmpty())){
            for(String s : f){
                list.add(getBedmessage(s));
            }       
        }else{
            System.out.println("my flower house is empty already!");
            showMenu();
        }
        int i = 1;
        for(String m : list){
            System.out.println(i + ". BedImage: " + m);
            i++;
        }
        
    }

}
