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
package com.weigandtconsulting.javaschool.kryonet.examples.rmi;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

public class Razor {

    static int port = 2551;
    static String send = null;

    // Data Creator, creates data of size s.getBytes().length * 10
    static String createMsg(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(s);
        }
        System.out.println(sb.toString().getBytes().length);
        return sb.toString();
    }

    // Test Interface

    public static interface TestInterface {

        public String getName(String name);

    }

    //Class implementing the test interface.
    static private class TestImpl implements TestInterface {

        public String getName(String name) {
            return send;
        }
    }

    public static void main(String[] args) throws Exception {

        // Creating data of size 160 bytes
        send = createMsg("FooAndBarAndLazy");
        Server server = new Server();
        Kryo kryo = server.getKryo();
        ObjectSpace.registerClasses(kryo);
        kryo.register(TestInterface.class);
        server.start();
        server.bind(port);
        System.out.println("Server started on " + port);
        TestImpl test = new TestImpl();
        final ObjectSpace objectSpace = new ObjectSpace();
        objectSpace.register(123, test);

        server.addListener(new Listener() {
            @Override
            public void connected(final Connection connection) {
                objectSpace.addConnection(connection);

            }

        });

    }

}
