package com.jfinal.ext.rapid.kit.dialect;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.jfinal.ext.rapid.designer.Column;
import com.jfinal.ext.rapid.designer.Table;
import com.jfinal.kit.StringKit;

public class OracleDialect extends Dialect {

	private ResultSet rs;
	private PreparedStatement pstmt;
	static Dialect dialect = new OracleDialect();
	
	public static Dialect getDialect() {
		return dialect;
	}

	public static void setDialect(Dialect dialect) {
		OracleDialect.dialect = dialect;
	} 

	@Override
	public void commentForTables(Table table,Connection connection) {
		try {
			String sql = "select table_name,comments from user_tab_comments  where table_name=?";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			table.setComment(table.getName());
			while (rs.next()) {
				if (table.getName().equals(rs.getString("table_name"))) {
					String comment = rs.getString("comments");
					if (StringKit.notBlank(comment)) {
						table.setComment(comment);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void commentForColumns(Table table, Connection connection) {
		try {
			String sql = "select * from user_col_comments where TABLE_NAME = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, table.getName());
			rs = pstmt.executeQuery();
			List<Column> columns = table.getColumns();
			for (Column column : columns) {
				column.setComment(column.getName());
				while (rs.next()) {
					if (column.getName().equals(rs.getString("COLUMN_NAME"))) {
						String comment = rs.getString("COMMENTS");
						if (StringKit.notBlank(comment)) {
							column.setComment(comment);
							continue;
						}
					}
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
        return "oracle.jdbc.driver.OracleDriver";
    }
}
