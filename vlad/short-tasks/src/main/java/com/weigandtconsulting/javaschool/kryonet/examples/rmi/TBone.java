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
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

public class TBone {

    static int port = 2551;

    // Bytes to MB converter
    public static float b2mb(long bytes) {
        float bb = bytes;
        float ll = 1024 * 1024;
        return bb / ll;
    }

    // Nono Seconds to seconds converter
    public static float ns2s(long nsecs) {

        float f = nsecs;
        float t = 1000000000;

        return f / t;
    }

    public static void main(String[] args) throws Exception {

        Client client = new Client();
        Kryo kryo = client.getKryo();
        ObjectSpace.registerClasses(kryo);
        kryo.register(Razor.TestInterface.class);

        client.start();
        client.connect(50000, "localhost", port);
        Razor.TestInterface test = ObjectSpace.getRemoteObject(client, 123, Razor.TestInterface.class);
        
        String profile = null;
        int bytes = 0;
        long stime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            profile = test.getName("");
            bytes = bytes + profile.getBytes().length;
            if (i % 100 == 0) {
                System.out.println("Done : " + i);
            }
        }
        long etime = System.nanoTime();
        System.out.println("Total bytes(MB) : " + b2mb(bytes) + " , " + "Total time : " + ns2s((etime - stime)) + " seconds");

        client.stop();

    }

}
