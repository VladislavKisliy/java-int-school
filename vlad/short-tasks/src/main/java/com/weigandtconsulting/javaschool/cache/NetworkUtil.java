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

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
/**
 *
 * @author vlad
 */
public class NetworkUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Properties properties = null;

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

//    public static Properties getProperties() throws IOException {
//        if (properties != null) {
//            return properties;
//        }
//        properties = new Properties();
//        InputStream inputStream = NetworkUtil.class.getClassLoader().getResourceAsStream("application.properties");
//        properties.load(inputStream);
//        return properties;
//    }

    public static String getHttpResponse(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setConnectTimeout(5000);
        //conn.setReadTimeout(20000);

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        StringBuilder outputBuilder = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            outputBuilder.append(output);
        }
        conn.disconnect();
        return outputBuilder.toString();
    }
}
