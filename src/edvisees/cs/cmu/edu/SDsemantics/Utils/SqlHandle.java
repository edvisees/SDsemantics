package edvisees.cs.cmu.edu.SDsemantics.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



import edvisees.cs.cmu.edu.SDsemantics.Frame.Frame;

public class SqlHandle {
	
	private String sqlDb;
	
		
	public SqlHandle() {
		super();
		this.sqlDb = "lib/NewPropstore1.db";
	}
	
	
	
	public Connection sqlInit() throws Exception{
		String sDriverName = "org.sqlite.JDBC";
		Class.forName(sDriverName);
		String sJdbc = "jdbc:sqlite";
		String sDbUrl = sJdbc + ":" + sqlDb;
		Connection conn = DriverManager.getConnection(sDbUrl);
		return conn;
	}
	
	

	public ResultSet sqlGet(Connection conn, String query) throws Exception{
		Statement stmt = conn.createStatement();
		try{
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		}finally{
			try { stmt.close(); } catch (Exception ignore) {}
		}
		
	}



	public String getSqlDb() {
		return sqlDb;
	}



	public void setSqlDb(String sqlDb) {
		this.sqlDb = sqlDb;
	}
}