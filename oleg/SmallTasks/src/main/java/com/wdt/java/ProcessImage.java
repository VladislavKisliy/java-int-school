package com.wdt.java;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Oleg 
 */
public class ProcessImage {
    private final String pathToInFile;
    private final String pathToOutFile="D:/I_KNOW_GRAY.JPG";
    public String getPathToOutFile() {
        return pathToOutFile;
    }
    public ProcessImage(String path) {
        this.pathToInFile=path;
    }
    public String getPathToInFile() {
        return pathToInFile;
    }
    
    public BufferedImage openImage(String path) {
        System.out.println("I'm openImage");
        BufferedImage image = null;
        FileInputStream fis=null;
        File file=new File(this.getPathToInFile());
        try{
            fis=new FileInputStream(file);
        }catch (FileNotFoundException ex){
            System.err.println(ex);
            exit(-1);
        }
        try {
            image = ImageIO.read(fis);
        } catch (IOException ex) {
            Logger.getLogger(ProcessImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }
    
    public void saveToFile(BufferedImage image) throws IOException{
        BufferedImage img = image;
        File outputfile = new File(this.getPathToOutFile());
        ImageIO.write(img, "jpg", outputfile);
    }     
    //http://stackoverflow.com/questions/9131678/convert-a-rgb-image-to-grayscale-image-reducing-the-memory-in-java

    public static void main (String[] argc) throws IOException{
        System.out.println("Starting RGB");
        ProcessImage procInstance = new ProcessImage("D:/I_KNOW.JPG");
        BufferedImage image=procInstance.openImage(procInstance.getPathToInFile());
        BufferedImage outImage = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_BYTE_GRAY);
        ForkJoinImageProcessClass imageProcessInstance = new ForkJoinImageProcessClass(0, image.getWidth(), image, outImage);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(imageProcessInstance);
        procInstance.saveToFile(outImage);
    }
}
