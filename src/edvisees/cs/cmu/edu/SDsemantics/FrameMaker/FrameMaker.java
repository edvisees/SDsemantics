/**
 * 
 */
package edvisees.cs.cmu.edu.SDsemantics.FrameMaker;

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
 * @author  Sujay Kumar Jauhar
 *
 */
public class FrameMaker {
	
	public static Frame relationalFrameMaker (Connection conn, String w1, Hashtable<String, Integer> relationList, Hashtable<String, Integer> vocabList) throws Exception{
		
		String sMakeSelect = "SELECT * from Triples WHERE w1 = '"+w1+"'";
		
		// create a database connection
		Statement stmt = conn.createStatement();
		Hashtable<String,FrameComponent> relTable = new Hashtable<String,FrameComponent>();
		
		try {
			ResultSet rs = stmt.executeQuery(sMakeSelect);
			try {
				while(rs.next()){
					String sResultW2 = rs.getString("w2");
					String sResultRel = rs.getString("rel");
					if(vocabList.containsKey(sResultW2) && relationList.containsKey(sResultRel.split("_")[0])){
						String temp[] = rs.getString("sentenceIDs").replace("-0","").split("_");				
						String sentenceIds[] = Arrays.copyOfRange(temp,0,temp.length);
						//Create if required
						FrameComponent reltn;
						if(!relTable.containsKey(sResultRel)){
							reltn = new FrameComponent();
						} else {
							reltn = relTable.get(sResultRel);
						}
						//Update reltn
						reltn.update(sResultW2, sentenceIds);
						relTable.put(sResultRel, reltn);
					}
				}
			} finally {
				try { rs.close(); } catch (Exception ignore) {}
			}
		} finally {
			try { stmt.close(); } catch (Exception ignore) {}
		}
		return new Frame(w1,relTable);
	}
	
//public static Frame reducedRelationalFrameMaker (Connection conn, String w1, Hashtable <String,Integer> vocab) throws Exception{
//		
//		String sMakeSelect = "SELECT * from Triples WHERE w1 = '"+w1+"'";
//		
//		// create a database connection
//		Statement stmt = conn.createStatement();
//		Hashtable<String,Relation> relTable = new Hashtable<String,Relation>();
//		
//		Hashtable<Tuple, Hashtable<String,ArrayList<String>>> nerTable= new Hashtable<Tuple, Hashtable<String, ArrayList<String>>>();
//		
//		//Build relTable
//		try{
//			ResultSet rs = stmt.executeQuery(sMakeSelect);
//			while(rs.next()){
//				String sResultW2 = rs.getString("w2");
//				String sResultRel = rs.getString("rel");
//				String temp[] = rs.getString("sentenceIDs").replace("-0","").split("_");					
//				String sentenceIds[] = Arrays.copyOfRange(temp,1,temp.length);
//				ArrayList<String> sentenceList = new ArrayList<String>();
//				for (int i = 0;i<sentenceIds.length;i++) sentenceList.add(sentenceIds[i]);
//				
//				//Insert in nerTable
//				Tuple t = new Tuple(sResultW2,sResultRel);
//				if(sResultW2.endsWith("_NER")){
//					Hashtable<String,ArrayList<String>> temHt;
//					if(nerTable.containsKey(t)) temHt = nerTable.get(t);
//					else temHt = new Hashtable<String,ArrayList<String>>();
//					//Update temHt
//					temHt.put(sResultW2, sentenceList);
//					nerTable.put(t, temHt);
//				}
//				
//			}
//		}
//		catch(Exception e){System.out.println(e);}
//		
//		try {
//			ResultSet rs = stmt.executeQuery(sMakeSelect);
//			try {
//				while(rs.next()){
//					String sResultW2 = rs.getString("w2");
//					String sResultRel = rs.getString("rel");
//					String temp[] = rs.getString("sentenceIDs").replace("-0","").split("_");					
//					String sentenceIds[] = Arrays.copyOfRange(temp,1,temp.length);
//					
//					Tuple t = new Tuple(sResultW2,sResultRel);
//					if(sResultW2.endsWith("_root") && vocab.containsKey(sResultW2.split("_")[0])){
//						//Create frame here
//						String w2sent[] = rs.getString("sentenceIDs").replace("-0", "").split("_");
//						ArrayList<String> w2SentList = new ArrayList<String>();
//						for (int i = 0;i<w2sent.length;i++) w2SentList.add(sentenceIds[i]);
//						Hashtable<String,ArrayList<String>> iterTable = nerTable.get(t);
//						Iterator it = iterTable.entrySet().iterator();
//					    while (it.hasNext()) {
//					        Map.Entry pairs = (Map.Entry)it.next();
//					        String nerType = (String) pairs.getKey();
//					        ArrayList<String> sentList = (ArrayList<String>) pairs.getValue();
//					        
//					        //Remove from w2sentList elements in sentList
//					        
//					        it.remove(); // avoids a ConcurrentModificationException
//					    }
//					}
//					//Create if required
//					Relation reltn;
//					if(!relTable.containsKey(sResultRel)){
//						reltn = new Relation();
//					} else {
//						reltn = relTable.get(sResultRel);
//					}
//					//Update reltn
//					reltn.update(sResultW2, sentenceIds);
//					relTable.put(sResultRel, reltn);
//				}
//			} finally {
//				try { rs.close(); } catch (Exception ignore) {}
//			}
//		} finally {
//			try { stmt.close(); } catch (Exception ignore) {}
//		}
//		return new Frame(w1,relTable);
//	}

	public static void main(String args[]) throws Exception{
		
		//Read a database
		//register the driver 
		String sDriverName = "org.sqlite.JDBC";
		Class.forName(sDriverName);

		// now we set up a set of fairly basic string variables to use in the body of the code proper
		String sTempDb = "lib/NewPropstore1.db";
		String sJdbc = "jdbc:sqlite";
		String sDbUrl = sJdbc + ":" + sTempDb;
		Connection conn = DriverManager.getConnection(sDbUrl);
		
		// create a configuration for DB environment
		EnvironmentConfig envConf = new EnvironmentConfig();
		// environment will be created if not exists
		envConf.setAllowCreate(true);
		// open/create the DB environment using config
		Environment dbEnv = new Environment(new File("lib/bdb"), envConf);
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
		
        //Read relations and Wordlists from file
        Hashtable<String,Integer> vocabList = new Hashtable<String,Integer>();
        int vocabId = 0;
        
        try {
        	BufferedReader input =  new BufferedReader(new FileReader("lib/vocab.list"));
        	try {
        		String line = null; //not declared within while loop
        		while (( line = input.readLine()) != null){
        			vocabList.put(line, vocabId);
        			vocabId++;
        		}
        	}
        	finally {
        		input.close();
        	}
        }
        catch (IOException ex){
        	ex.printStackTrace();
        }
        
        Hashtable<String, Integer> relationList = new Hashtable<String, Integer>();
        int relId = 0;

        try {
        	BufferedReader input =  new BufferedReader(new FileReader("lib/rels.list"));
        	try {
        		String line = null; //not declared within while loop
        		while (( line = input.readLine()) != null){
        			relationList.put(line, relId);
        			relId++;
        		}
        	}
        	finally {
        		input.close();
        	}
        }
        catch (IOException ex){
        	ex.printStackTrace();
        }

		//Iterate over all words in the database
		String[] words = {"runnerer","eat"};
		//Construct their frame and store to database
		for (String w : words){
			if(vocabList.containsKey(w)){
				Frame f = relationalFrameMaker (conn, w, relationList, vocabList);
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
			else System.out.println("Word "+w+" is not in vocabulary. Building its frame should not be required.");
		}
		
		//Check if frames have been properly inserted in database
		for (String w : words){
			if(vocabList.containsKey(w)){
				DatabaseEntry key = new DatabaseEntry();
				StringBinding.stringToEntry(w, key);
				DatabaseEntry data = new DatabaseEntry();
				frameDB.get(null, key, data, null);
				Frame f = new Frame();
				f = (Frame) dataBinding.entryToObject(data);
				System.out.println(f.toString());
			}
			else System.out.println("Word "+w+" is not in vocabulary. It's frame is not in our database.");
		}
		
		frameDB.close();
		dbEnv.close();
	}

}
