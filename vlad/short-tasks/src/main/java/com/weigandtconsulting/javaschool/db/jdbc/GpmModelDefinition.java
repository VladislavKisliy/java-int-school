/*
 * Copyright (C) 2016 Weigandt Consulting
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
package com.weigandtconsulting.javaschool.db.jdbc;

/**
 *
 * @author vlad
 */
public class GpmModelDefinition {

    private final long id;
    private final int modelId;
    private final String columnName;
    private final String label;
    private final String description;

    public GpmModelDefinition(long id, int modelId, String columnName, String label, String description) {
        this.id = id;
        this.modelId = modelId;
        this.columnName = columnName;
        this.label = label;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public int getModelId() {
        return modelId;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "GpmModelDefinition{" + "id=" + id + ", modelId=" + modelId + ", columnName=" + columnName + ", label=" + label + ", description=" + description + '}';
    }
}
