/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wdt.java;

import java.awt.image.BufferedImage;

/**
 *
 * @author Oleg 
 */
public class ForkJoinImageProcessClass {
    int xStart;
    int yStart;
    int xEnd;
    int yEnd;
    private final static int workPixels=1000;
    public static int getWorkPixels() {
        return workPixels;
    }
    public ForkJoinImageProcessClass(int xStart, int yStart, int xEnd, int yEnd) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }
    
    public static BufferedImage makeGray(BufferedImage image)
    {
        for (int x = 0; x < image.getWidth(); ++x)
        for (int y = 0; y < image.getHeight(); ++y)
        {
            int rgb = image.getRGB(x, y);
            int r = (rgb >> 16) & 0xFF;
            int g = (rgb >> 8) & 0xFF;
            int b = (rgb & 0xFF);
            int grayLevel = (r + g + b) / 3;
            int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel; 
            image.setRGB(x, y, gray);
        }
        return image;
    }
    public BufferedImage makeGrayForkJoin(BufferedImage image)
    {  
        if ((this.xStart - this.xEnd)< getWorkPixels() && (this.yStart - this.yEnd) < getWorkPixels()){
            for (int x = this.xStart; x < this.xEnd; ++x)
            for (int y = this.yStart; y < this.yEnd; ++y)
            {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb & 0xFF);
                int grayLevel = (r + g + b) / 3;
                int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel; 
                image.setRGB(x, y, gray);
            }
        } else{
            int lxStart = this.xStart;
            int lxEnd = (this.xStart-this.xEnd)/2-1;
            int lyStart = this.yStart;
            int lyEnd = (this.yStart-this.yEnd)/2-1;
            
            
            
        }
        return image;
    }
}
