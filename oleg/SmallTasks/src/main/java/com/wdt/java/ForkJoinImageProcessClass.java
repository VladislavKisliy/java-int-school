/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wdt.java;
import java.awt.image.BufferedImage;
import java.util.concurrent.RecursiveAction;

/**
 *
 * @author Oleg 
 */
public class ForkJoinImageProcessClass extends RecursiveAction {
    private int xStart;
    private int length;
    private int yLength;
    private final BufferedImage inImage;
    private final BufferedImage outImage;// = new BufferedImage(inImage.getWidth(),inImage.getHeight(),BufferedImage.TYPE_INT_ARGB);
    private final static int computeThreshold=1000;

    public static int getComputeThreshold() {
        return computeThreshold;
    }

    public BufferedImage getOutImage() {
        return outImage;
    }
    public ForkJoinImageProcessClass(int iStart, int iLength, BufferedImage inImage) {
        this.xStart = iStart;
        this.length = iLength;
        this.inImage=inImage;
        outImage=new BufferedImage(inImage.getWidth(),inImage.getHeight(),BufferedImage.TYPE_INT_ARGB);
    }
    
    public void makeGrayDirect()
    {
        for (int x = this.xStart; x < this.length && x < inImage.getWidth() ; ++x)
        {
            for(int y = 0; y < outImage.getHeight(); ++y)
            {
                int rgb = this.inImage.getRGB(x, y);
//                int a = (rgb >> 24) & 0xFF;
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb & 0xFF);
                int grayLevel = (r + g + b) / 3;
                int gray = (grayLevel << 16)+(grayLevel << 8)+grayLevel; 
//                System.out.print("RGB is:"+rgb);
                outImage.setRGB(x, y, gray);
//                System.out.println("X: "+x+"; "+"Y: "+y+" Gray is: "+gray);
//                tempColor = new Color(grayScaleVal, grayScaleVal, grayScaleVal);
//                image.setRGB(x, y, tempColor.getRGB());   
            }
        }
    }
    @Override
    protected void compute() {
        if (this.length < computeThreshold) {
            makeGrayDirect();
            return;
        }
        int nLength = this.length/2;
        invokeAll(new ForkJoinImageProcessClass(this.xStart, nLength, this.inImage),
                new ForkJoinImageProcessClass(this.xStart+nLength+1, nLength, this.inImage));
    }
}
