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
package com.weigandtconsulting.javaschool.kryonet.examples.chatrmi;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

public class Network {

    static public final int port = 54777;

    // These IDs are used to register objects in ObjectSpaces.
    static public final short PLAYER = 1;
    static public final short CHAT_FRAME = 2;

    // This registers objects that are going to be sent over the network.
    static public void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        // This must be called in order to use ObjectSpaces.
        ObjectSpace.registerClasses(kryo);
        // The interfaces that will be used as remote objects must be registered.
        kryo.register(IPlayer.class);
        kryo.register(IChatFrame.class);
		// The classes of all method parameters and return values
        // for remote objects must also be registered.
        kryo.register(String[].class);
    }
}
