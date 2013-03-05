package com.jfinal.ext.rapid.kit;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.ext.rapid.designer.Table;
import com.jfinal.ext.rapid.kit.dialect.Dialect;

/**
 * 非线程安全，仅仅作为生成器读去metadata时使用
 * @author kid
 *
 */
public class JdbcKit {

    private static Connection connection;
    
    private static void init(String jdbcurl, String driver, String username, String password) {
        if (connection == null) {
            try {
            	Class.forName(driver).newInstance();
                connection = DriverManager.getConnection(jdbcurl, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection connection(String jdbcurl, String driver, String username, String password)
                                                                                                        throws Exception {

        init(jdbcurl, driver, username, password);
        return connection;
    }
    
    public static void update(String sql) throws SQLException{
        connection.createStatement().execute(sql);
    }
    public static DatabaseMetaData getMetaData(String jdbcurl, String driver,String username, String password)
                                                                                                               throws Exception {
        init(jdbcurl, driver, username, password);
        return connection.getMetaData();
    }

    public static List<Table> readMetadataOfTables(String jdbcurl,Dialect dbType, String username, String password)
                                                                                                           throws Exception {
        List<Table> tables = new ArrayList<Table>();
        DatabaseMetaData dbmd = JdbcKit.getMetaData(jdbcurl,dbType.getDriver(), username, password);
        ResultSet rs = dbmd.getTables(null, "%", "%", null);
        while (rs.next()) {
            Table table = new Table();
            table.setName(rs.getString("TABLE_NAME"));
            dbType.commentForTables(table, connection);
            dbType.columnsForTables(table, dbmd);
            dbType.commentForColumns(table, connection);
            tables.add(table);
        }
        return tables;
    }
    
    public static void generateTasks(String jdbcurl, String driver, String username, String password ,String taskName){
    	init(jdbcurl,driver,username,password);
    	String sql = "select * from"+""+taskName;
    	try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    			
    }
}
