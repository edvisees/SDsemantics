/**
 * 
 */
package edvisees.cs.cmu.edu.SDsemantics.FrameMaker;

import java.util.Arrays;
import java.util.Hashtable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
		// register the driver 
		String sDriverName = "org.sqlite.JDBC";
		Class.forName(sDriverName);

		// now we set up a set of fairly basic string variables to use in the body of the code proper
		String sTempDb = "/Users/Sujay/test.db";
		String sJdbc = "jdbc:sqlite";
		String sDbUrl = sJdbc + ":" + sTempDb;
		Connection conn = DriverManager.getConnection(sDbUrl);
		//Iterate over all words in the database
		String[] words = {"run","eat","play"};
		//Construct their frame
		for (String w : words){
			Frame f = relationalFrameMaker (conn, w);
			System.out.println(f.toString());
			
		}
		//Store the frame in another database
	}

}
