/*
 * Copyright (C) 2015 Weigandt Consulting
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
package com.weigandtconsulting.javaschool.cache;

import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import static com.weigandtconsulting.javaschool.cache.BookService.getBookDetailsFromGoogleBooks;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author vlad
 */
public class ManualCache {

    private static final Cache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(20)
            .expireAfterAccess(1, TimeUnit.HOURS)
            .recordStats()
            .build();

    static String getLine(String key) {
        String result = cache.getIfPresent(key);
        if (result == null) {
            cache.put(key, "Mega-result");
        }
        return result;
    }
    
     public static void main(String[] args) throws IOException, ExecutionException {
         System.out.println("result ="+getLine("1"));
         System.out.println("result ="+getLine("10"));
         System.out.println("result ="+getLine("2"));
         System.out.println("result ="+getLine("1"));
         System.out.println("result ="+getLine("2"));
         System.out.println("result ="+getLine("1"));
         System.out.println("result ="+getLine("2"));
         System.out.println("result ="+getLine("3"));
         System.out.println("result ="+getLine("1"));
         System.out.println("result ="+getLine("10"));
         System.out.println("result ="+getLine("1"));
         System.out.println("result ="+getLine("4"));
         System.out.println("result ="+getLine("1"));
         System.out.println("result ="+getLine("2"));
         System.out.println("result ="+getLine("5"));
         System.out.println("result ="+getLine("1"));
         System.out.println("result ="+getLine("10"));
         System.out.println("result ="+getLine("1"));
         System.out.println("result ="+getLine("6"));
         System.out.println("result ="+getLine("1"));
         System.out.println("result ="+getLine("7"));
         System.out.println("result ="+getLine("3"));
         System.out.println("result ="+getLine("1"));
         System.out.println("result ="+getLine("10"));
         System.out.println("result ="+getLine("1"));
         System.out.println("result ="+getLine("3"));
         System.out.println("result ="+getLine("10"));
         System.out.println("result ="+getLine("1"));
         System.out.println("result ="+getLine("4"));
         System.out.println("result ="+getLine("1"));
         System.out.println("result ="+getLine("2"));
         System.out.println("result ="+getLine("5"));
         System.out.println("result ="+getLine("1"));
         
         System.out.println("------------");
         System.out.println("stats ="+cache.stats());
    }
    
}
