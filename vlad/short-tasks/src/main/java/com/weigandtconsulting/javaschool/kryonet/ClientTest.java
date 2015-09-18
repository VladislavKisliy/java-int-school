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
package com.weigandtconsulting.javaschool.kryonet;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.weigandtconsulting.javaschool.kryonet.beans.Message;
import com.weigandtconsulting.javaschool.kryonet.beans.MessageImpl;
import com.weigandtconsulting.javaschool.kryonet.beans.RequestWithEnum;
import com.weigandtconsulting.javaschool.kryonet.beans.RequestWithList;
import com.weigandtconsulting.javaschool.kryonet.beans.SimpleResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author vlad
 */
public class ClientTest {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Client client = new Client();
        client.start();
        client.connect(5000, "127.0.0.1", 54555, 54777);

        Kryo kryo = client.getKryo();
        kryo.register(RequestWithList.class);
        kryo.register(SimpleResponse.class);
        kryo.register(ArrayList.class);
        kryo.register(RequestWithEnum.class);
        kryo.register(RequestWithEnum.Type.class);
        kryo.register(Message.class);

        client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof SimpleResponse) {
                    SimpleResponse response = (SimpleResponse) object;
                    System.out.println("Answer from server: " + response);
                }
            }
        });

        List<String> items = new ArrayList<String>();
        items.add("one item");
        items.add("2 item");
        items.add("3 item");
        items.add("4 item");
        items.add("5 item");
        RequestWithList request = new RequestWithList("request N1", "Test request", items);
        System.out.println("-- request 1");
        client.sendTCP(request);
        TimeUnit.SECONDS.sleep(1);
        if (client.isConnected()) {
            System.out.println("I am connected");
        } else {
            System.out.println("I was disconnected");
        }
        RequestWithEnum requestWithEnum = new RequestWithEnum();
        requestWithEnum.setName("requestWithEnum");
        requestWithEnum.setDescription("new requestWithEnum");
        requestWithEnum.setType(RequestWithEnum.Type.LONG);
        client.sendTCP(requestWithEnum);
        System.out.println("-- request 2");
        TimeUnit.SECONDS.sleep(1);
        
        Message message = new MessageImpl();
        client.sendTCP(message);
        System.out.println("-- request 3");
        TimeUnit.SECONDS.sleep(1);
    }
}
