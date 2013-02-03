package edvisees.cs.cmu.edu.SDsemantics.Utils;
import edvisees.cs.cmu.edu.SDsemantics.Utils.*;

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
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteJDBCLoader;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.bind.tuple.StringBinding;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

import edvisees.cs.cmu.edu.SDsemantics.Frame.Frame;


public class test {
public static void main(String[] args) throws Exception{
	SqlHandle tsq= new SqlHandle("lib/test.db");
	String[] words = {"run"};
	System.out.println(SQLiteJDBCLoader.isNativeMode());
	for (String w : words){
		//try{
		ResultSet rs= tsq.sqlGet("SELECT * from Triples WHERE w1 = '"+w+"'");
		try{
			while(rs.next()){
				
				String sResultW2 = rs.getString("w2");
				String sResultRel = rs.getString("rel");
				System.out.println(w+" "+sResultW2+" "+ sResultRel);
			}
		}catch (Exception ignore){continue;}
		//}catch (Exception ignore) {	}
	
	}
		
}
}
