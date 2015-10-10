package com.wdt.java.ForkJoinTask;
import java.awt.image.BufferedImage;
import java.util.concurrent.RecursiveAction;

/**
 *
 * @author Oleg 
 */
public class ForkJoinImageProcessClass extends RecursiveAction {
    private int xStart;
    private int length;
    private final BufferedImage inImage;
    private final BufferedImage outImage;
    private final static int computeThreshold=400;

    public static int getComputeThreshold() {
        return computeThreshold;
    }

    public ForkJoinImageProcessClass(int iStart, int iLength, BufferedImage inImage, BufferedImage outImage ) {
        this.xStart = iStart;
        this.length = iLength;
        this.inImage=inImage;
        this.outImage= outImage ;
    }
    
    public void makeGrayDirect()
    {
        int xLimit = this.xStart+this.length;
        for (int x = this.xStart; x < xLimit && x < inImage.getWidth() ; ++x)
        {
            for(int y = 0; y < inImage.getHeight(); ++y)
            {
                int rgb = this.inImage.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb & 0xFF);
                int grayLevel = (r + g + b) / 3;
                int gray = (grayLevel << 16)+(grayLevel << 8)+grayLevel; 
                outImage.setRGB(x, y, gray);
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
        invokeAll(new ForkJoinImageProcessClass(this.xStart, nLength, this.inImage, this.outImage ),
                new ForkJoinImageProcessClass(this.xStart+nLength, this.length-nLength, this.inImage, this.outImage));
    }
}
