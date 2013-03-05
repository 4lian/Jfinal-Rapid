package com.jfinal.ext.rapid.designer;

import java.util.ArrayList;
import java.util.List;

public class Table {

    String       name;

    String       comment;

    List<Column> columns = new ArrayList<>();

    public void addColumn(Column column) {
        columns.add(column);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getComment() {
        return comment;
    }

    
    public void setComment(String comment) {
        this.comment = comment;
    }

    
    public List<Column> getColumns() {
        return columns;
    }

    
    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "Table [name=" + name + ", comment=" + comment + ", columns=" + columns + "]";
    }

   
}
