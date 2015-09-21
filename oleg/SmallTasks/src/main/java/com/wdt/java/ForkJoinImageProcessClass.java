/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wdt.java;
import java.util.concurrent.RecursiveAction;

/**
 *
 * @author Oleg 
 */
public class ForkJoinImageProcessClass extends RecursiveAction {
    private int start;
    private int length;
    private int src [];
    private int dst [];
    private final static int computeThreshold=10000000;

    public static int getComputeThreshold() {
        return computeThreshold;
    }

    public ForkJoinImageProcessClass(int iStart, int iLength, int iSrc [], int iDst [] ) {
        this.start = iStart;
        this.length = iLength;
        this.src=iSrc;
        this.dst=iDst;
    }
    
    public void makeGrayDirect()
    {
        for (int x = this.start; x <= this.length ; ++x)
        {
            int rgb = this.src[x];
            int alpha = (rgb >> 24) & 0xFF;
            int r = (rgb >> 16) & 0xFF;
            int g = (rgb >> 8) & 0xFF;
            int b = (rgb & 0xFF);
            int grayLevel = (r + g + b) / 3;
            int gray = (alpha << 24) + (grayLevel << 16) + (grayLevel << 8) + grayLevel; 
            this.dst[x]=gray;
        }
    }
    @Override
    protected void compute() {
        if (this.length < computeThreshold) {
            makeGrayDirect();
            return;
        }
        int nLength = this.length/2;
        invokeAll(new ForkJoinImageProcessClass(this.start, nLength, this.src, this.dst),
                new ForkJoinImageProcessClass(this.start+nLength+1, nLength, this.src, this.dst));
    }
}
