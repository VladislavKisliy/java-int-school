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

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author vlad
 */
public class BookService {

    private static LoadingCache<String, Optional<Book>> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterAccess(24, TimeUnit.HOURS)
            .recordStats()
            .build(new CacheLoader<String, Optional<Book>>() {
                @Override
                public Optional<Book> load(String s) throws IOException {
                    return getBookDetailsFromGoogleBooks(s);
                }
            });

    public static Optional<Book> getBookDetailsFromGoogleBooks(String isbn13) throws IOException {
//        Properties properties = NetworkUtil.getProperties();
//        String key = properties.getProperty(Constants.GOOGLE_API_KEY);
        String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn13;
        String response = NetworkUtil.getHttpResponse(url);
        Map bookMap = NetworkUtil.getObjectMapper().readValue(response, Map.class);
        Object bookDataListObj = bookMap.get("items");
        Book book = null;
        if (bookDataListObj == null || !(bookDataListObj instanceof List)) {
            return Optional.fromNullable(book);
        }

        List bookDataList = (List) bookDataListObj;
        if (bookDataList.size() < 1) {
            return Optional.fromNullable(null);
        }

        Map bookData = (Map) bookDataList.get(0);
        Map volumeInfo = (Map) bookData.get("volumeInfo");
        book = new Book();
        book.setTitle(getFromJsonResponse(volumeInfo, "title", ""));
        book.setPublisher(getFromJsonResponse(volumeInfo, "publisher", ""));
        List authorDataList = (List) volumeInfo.get("authors");
        for (Object authorDataObj : authorDataList) {
            Author author = new Author();
            author.setName(authorDataObj.toString());
            book.addAuthor(author);
        }
        book.setIsbn13(isbn13);
        book.setSummary(getFromJsonResponse(volumeInfo, "description", ""));
        book.setPageCount(Integer.parseInt(getFromJsonResponse(volumeInfo, "pageCount", "0")));
        book.setPublishedDate(getFromJsonResponse(volumeInfo, "publishedDate", ""));

        return Optional.fromNullable(book);
    }

    private static String getFromJsonResponse(Map jsonData, String key, String defaultValue) {
        return Optional.fromNullable(jsonData.get(key)).or(defaultValue).toString();
    }

    public static Optional<Book> getBookDetails(String isbn13) throws IOException, ExecutionException {
        Optional<Book> book = cache.get(isbn13);
        return book;
    }

    public static CacheStats getCacheStats() {
        return cache.stats();
    }
}
