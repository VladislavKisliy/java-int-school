/*
 * Copyright (C) 2016 Weigandt Consulting
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.weigandtconsulting.javaschool.threads.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vlad
 */
public class CompletionServiceExample {

    private static final Logger LOG = Logger.getLogger(CompletionServiceExample.class.getName());
    private final ExecutorService executor;

    public CompletionServiceExample(ExecutorService executor) {
        this.executor = executor;
    }

    void useCompletionService() {

    }

    interface ImageInfo {

        String downloadImage();
    }

    private class ImageInfoImpl implements ImageInfo {

        private final String threadName = "Thread";
        private final int delay;

        public ImageInfoImpl(int delay) {
            this.delay = delay;
        }

        @Override
        public String downloadImage() {
            try {
                TimeUnit.SECONDS.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(CompletionServiceExample.class.getName()).log(Level.SEVERE, null, ex);
            }
            return threadName + " " + delay;
        }

    }

    public static void main(String[] args) {
//        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService threadPool = Executors.newFixedThreadPool(9);
        CompletionServiceExample completionServiceExample = new CompletionServiceExample(threadPool);
        CompletionService<String> completionService = new ExecutorCompletionService<>(threadPool);

        System.out.println("-- init list");
        final List<ImageInfo> info = new ArrayList<>();
        info.add(completionServiceExample.new ImageInfoImpl(16));
        int threadCounter = 10;
        for (int i = 0; i < threadCounter; i++) {
            info.add(completionServiceExample.new ImageInfoImpl(12 - i));
        }
        info.add(completionServiceExample.new ImageInfoImpl(1));

        System.out.println("-- submit tasks");
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        System.out.println("-- before submit. threads =" + threadSet.size());
        for (Thread thread : threadSet) {
            System.out.println("threads before =" + thread);
        }
        for (final ImageInfo imageInfo : info) {
            completionService.submit(new Callable<String>() {
                @Override
                public String call() {
                    return imageInfo.downloadImage();
                }
            });
        }
        
        threadSet = Thread.getAllStackTraces().keySet();
        System.out.println("-- after submit. threads =" + threadSet.size());
        for (Thread thread : threadSet) {
            System.out.println("threads after =" + thread);
        }
        try {
            for (int t = 0, n = info.size(); t < n; t++) {
                System.out.println("-- take");
                Future<String> f = completionService.take();
                System.out.println("Result =" + f.get());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            Logger.getLogger(CompletionServiceExample.class.getName()).log(Level.SEVERE, null, e);
//            throw launderThrowable(e.getCause());
        }
        System.out.println("-- THE END");
        threadSet = Thread.getAllStackTraces().keySet();
        System.out.println("-- after end, before shut down. threads =" + threadSet.size());
        for (Thread thread : threadSet) {
            System.out.println("threads after =" + thread);
        }

        try {
            threadPool.shutdown();
            LOG.log(Level.INFO, "awaitTermination 2 sec");
            if (!threadPool.awaitTermination(2, TimeUnit.SECONDS)) {
                LOG.log(Level.WARNING, "shutdown processes now");
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(CompletionServiceExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        threadSet = Thread.getAllStackTraces().keySet();
        System.out.println("-- after end, after shut down. threads =" + threadSet.size());
        for (Thread thread : threadSet) {
            System.out.println("threads after =" + thread);
        }
    }
}
