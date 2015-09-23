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
    private BufferedImage inImage;
    private BufferedImage outImage;
    private final static int computeThreshold=10000000;

    public static int getComputeThreshold() {
        return computeThreshold;
    }

    public ForkJoinImageProcessClass(int iStart, int iLength, BufferedImage inImage) {
        this.xStart = iStart;
        this.length = iLength;
        this.inImage=inImage;
        this.outImage=new BufferedImage(inImage.getWidth(),inImage.getHeight(),BufferedImage.TYPE_BYTE_GRAY);
        
//        this.src=iSrc;
//        this.dst=iDst;
    }
    
    public void makeGrayDirect()
    {
        for (int x = this.xStart; x < this.length ; x++)
        {
            for(int y = 0; y < outImage.getHeight(); y++)
            {
                int rgb = this.inImage.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb & 0xFF);
                int grayLevel = (r + g + b) / 3;
                int gray = (grayLevel << 16)+(grayLevel << 8)+grayLevel; 
                this.outImage.setRGB(x, y, gray);
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
