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
package com.weigandtconsulting.javaschool.kryonet.beans;

import java.util.List;

/**
 *
 * @author vlad
 */
public class RequestWithList {
    
    private String name;
    private String description;
    private List<String> items;

    public RequestWithList() {
    }

    public RequestWithList(String name, String description, List<String> items) {
        this.name = name;
        this.description = description;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "SomeRequest{" + "name=" + name + ", description=" + description + ", items=" + items + '}';
    }
}
