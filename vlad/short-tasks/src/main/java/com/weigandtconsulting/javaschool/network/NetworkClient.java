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
package com.weigandtconsulting.javaschool.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * The simplest TCP client
 * @author vlad
 */
public class NetworkClient {

    private final static String HOST_NAME = "127.0.0.1";
    private final static int PORT = 8080;

    public static void main(String argv[]) throws Exception {
        String userLine;
        boolean isActive = true;
        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

        // Example try-with-resources
        // https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        try (Socket clientSocket = new Socket(HOST_NAME, PORT)) {
            while (isActive) {
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                if (consoleInput.ready()) {
                    userLine = consoleInput.readLine();
                    outToServer.writeBytes(userLine + '\n');
                    if ("quit".equals(userLine)) {
                        isActive = false;
                    }
                }
                if (inFromServer.ready()) {
                    System.out.println(inFromServer.readLine());
                }
                // Reduce load of CPU
                TimeUnit.MILLISECONDS.sleep(300);
            }
        }
    }
}
