package com.jfinal.ext.rapid.kit.dialect;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.ext.rapid.designer.Column;
import com.jfinal.ext.rapid.designer.Table;
import com.jfinal.kit.StringKit;

public class MysqlDialect extends Dialect {

	private ResultSet rs;
	private PreparedStatement pstmt;
	static Dialect dialect = new MysqlDialect();
	
	public static Dialect getDialect() {
		return dialect;
	}

	public static void setDialect(Dialect dialect) {
		MysqlDialect.dialect = dialect;
	}
	
	@Override
	public void commentForTables(Table table,Connection connection) {
		try {
			String sql = "SHOW TABLE STATUS ";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			table.setComment(table.getName());
			while (rs.next()) {
				if (table.getName().equals(rs.getString("name"))) {
					String comment = rs.getString("comment");
					if (StringKit.notBlank(comment)) {
						table.setComment(rs.getString("comment"));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void commentForColumns(Table table,Connection connection) {
		try {
			String sql = "show full columns from" + " " + table.getName();
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Map<String, String> val = new HashMap<String, String>();
			while (rs.next()) {
				val.put(rs.getString("Field"), rs.getString("COMMENT"));
			}
			List<Column> columns = table.getColumns();
			for (Column column : columns) {
				column.setComment(column.getName());
				String comment = val.get(column.getName());
				if (StringKit.notBlank(comment)) {
					column.setComment(comment);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void columnsForTables(Table table, DatabaseMetaData dbmd) {
		try {
			rs = dbmd.getColumns(null, "%", table.getName(), null);
			ResultSet priamryKeyRs = dbmd.getPrimaryKeys(null, null, table.getName());
	        String priamryKey = null;
	        while (priamryKeyRs.next()) {
	            priamryKey = priamryKeyRs.getString("COLUMN_NAME");
	        }
	        while (rs.next()) {
	            Column column = new Column();
	            String name = rs.getString("COLUMN_NAME");
	            column.setName(name);
	            column.setType(rs.getString("TYPE_NAME"));
	            int columnSize = rs.getInt("COLUMN_SIZE");
	            int nullable = rs.getInt("nullable");
	            column.setNullable(nullable == 1);
	            column.setDataSize(columnSize);
	            if (column.getName().equals(priamryKey)) {
	                column.setPrimaryKey(true);
	            }
	            table.addColumn(column);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    @Override
    public String getDriver() {
        return "com.mysql.jdbc.Driver";
    }
}