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

import com.google.common.cache.CacheStats;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author vlad
 */
public class App {

    private static final String ISBN_1 = "9781935182351";
    private static final String ISBN_2 = "9781932394887";
    
    public static void main(String[] args) throws IOException, ExecutionException {
        Book book = BookService.getBookDetails(ISBN_1).get();
        System.out.println(NetworkUtil.getObjectMapper().writeValueAsString(book));
        book = BookService.getBookDetails(ISBN_1).get();
        book = BookService.getBookDetails(ISBN_2).get();
        System.out.println(NetworkUtil.getObjectMapper().writeValueAsString(book));
        book = BookService.getBookDetails(ISBN_1).get();
        book = BookService.getBookDetails(ISBN_2).get();
        book = BookService.getBookDetails(ISBN_1).get();
        book = BookService.getBookDetails(ISBN_1).get();
        book = BookService.getBookDetails(ISBN_1).get();
        book = BookService.getBookDetails(ISBN_2).get();
        book = BookService.getBookDetails(ISBN_2).get();
        CacheStats cacheStats = BookService.getCacheStats();
        System.out.println(cacheStats.toString());
    }
    

}
