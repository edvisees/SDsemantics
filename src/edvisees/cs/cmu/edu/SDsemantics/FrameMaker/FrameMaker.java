/**
 * 
 */
package edvisees.cs.cmu.edu.SDsemantics.FrameMaker;

import java.util.Arrays;
import java.util.Hashtable;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.bind.tuple.StringBinding;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

import edvisees.cs.cmu.edu.SDsemantics.Frame.*;

/**
 * @author Mrinmaya Sachan & Sujay Kumar Jauhar
 *
 */
public class FrameMaker {
	
	public static Frame relationalFrameMaker (Connection conn, String w1) throws Exception{
		String sMakeSelect = "SELECT * from Triples WHERE w1 = '"+w1+"'";
		//int iTimeout = 30;
		// create a database connection
		Statement stmt = conn.createStatement();
		Hashtable<String,Relation> relTable = new Hashtable<String,Relation>();
		try {
			//stmt.setQueryTimeout(iTimeout);
			//stmt.executeUpdate( sMakeTable );
			//stmt.executeUpdate( sMakeInsert );
			ResultSet rs = stmt.executeQuery(sMakeSelect);
			try {
				while(rs.next()){
					String sResultW2 = rs.getString("w2");
					String sResultRel = rs.getString("rel");
					String temp[] = rs.getString("sentenceIDs").replace("-0","").split("_");
					String sentenceIds[] = Arrays.copyOfRange(temp,1,temp.length);
					//Create if required
					Relation reltn;
					if(!relTable.containsKey(sResultRel)){
						reltn = new Relation();
					} else {
						reltn = relTable.get(sResultRel);
					}
					//Update reltn
					reltn.update(sResultW2, sentenceIds);
					relTable.put(sResultRel, reltn);
				}
			} finally {
				try { rs.close(); } catch (Exception ignore) {}
			}
		} finally {
			try { stmt.close(); } catch (Exception ignore) {}
		}
		return new Frame(w1,relTable);
	}
	
	public static void main(String args[]) throws Exception{
		//Read a database
		//register the driver 
		String sDriverName = "org.sqlite.JDBC";
		Class.forName(sDriverName);

		// now we set up a set of fairly basic string variables to use in the body of the code proper
		String sTempDb = "/Users/Sujay/test.db";
		String sJdbc = "jdbc:sqlite";
		String sDbUrl = sJdbc + ":" + sTempDb;
		Connection conn = DriverManager.getConnection(sDbUrl);
		
		// create a configuration for DB environment
		EnvironmentConfig envConf = new EnvironmentConfig();
		// environment will be created if not exists
		envConf.setAllowCreate(true);
		// open/create the DB environment using config
		Environment dbEnv = new Environment(new File("/Users/Sujay/Documents/workspace/SDsemantics/lib/bdb"), envConf);
		// create a configuration for DB
		DatabaseConfig dbConf = new DatabaseConfig();
		// db will be created if not exits
		dbConf.setAllowCreate(true);
		// create/open testDB using config
		Database frameDB = dbEnv.openDatabase(null, "frameDB", dbConf);
		// create a class catalog for the database
		StoredClassCatalog javaCatalog = new StoredClassCatalog(frameDB);
		// Need a serial binding for the data
        EntryBinding<Frame> dataBinding = new SerialBinding<Frame>(javaCatalog, Frame.class);
		
		
		//Iterate over all words in the database
		String[] words = {"run","eat","play"};
		//Construct their frame and store to database
		for (String w : words){
			Frame f = relationalFrameMaker (conn, w);
			//System.out.println(f.toString());
			
			// key
		    DatabaseEntry key = new DatabaseEntry();
		    // set the current word as key
		    StringBinding.stringToEntry(w, key);
		    // data
		    DatabaseEntry data = new DatabaseEntry();
		    // set the current frame object as value
		    dataBinding.objectToEntry(f, data);
		    
		    // insert (or update) key/value pair to database
		    frameDB.put(null, key, data);
			
		}
		
		//Check if frames have been properly inserted in database
//		for (String w : words){
//			DatabaseEntry key = new DatabaseEntry();
//			StringBinding.stringToEntry(w, key);
//			DatabaseEntry data = new DatabaseEntry();
//		    frameDB.get(null, key, data, null);
//		    Frame f = new Frame();
//		    f = (Frame) dataBinding.entryToObject(data);
//		    System.out.println(f.toString());
//		}
		
		frameDB.close();
		dbEnv.close();
	}

}
