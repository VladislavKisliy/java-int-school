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
package com.weigandtconsulting.javaschool.beans;

/**
 *
 * @author vlad
 */
public class NetworkRequest {
    
    public enum State {

        ACCEPTED, REQUEST, REJECTED, PROCESSING
    }
    
    private Request request;
    private State requestState;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public State getRequestState() {
        return requestState;
    }

    public void setRequestState(State requestState) {
        this.requestState = requestState;
    }
}
