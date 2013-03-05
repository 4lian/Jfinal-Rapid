package com.jfinal.ext.rapid.kit.dialect;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import com.jfinal.ext.rapid.designer.Table;

public abstract class Dialect {
	
    public  abstract String getDriver();
	public  abstract void commentForTables(Table table,Connection connection);
	public  abstract void commentForColumns(Table table ,Connection connection);
	public  abstract void columnsForTables(Table table ,DatabaseMetaData dbmd);
	
}
