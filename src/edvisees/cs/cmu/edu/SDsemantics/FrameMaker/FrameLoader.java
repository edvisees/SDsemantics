/**
 * 
 */
package edvisees.cs.cmu.edu.SDsemantics.FrameMaker;

import java.io.File;

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
 * @author Sujay Kumar Jauhar
 *
 */
public class FrameLoader {
	public static Frame makeFrame(String w){
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
		        // key
		        DatabaseEntry key = new DatabaseEntry();
		        // set the current word as key
		        StringBinding.stringToEntry(w, key);
		        DatabaseEntry data = new DatabaseEntry();
		        frameDB.get(null, key, data, null);
		        Frame f = new Frame();
		        f = (Frame) dataBinding.entryToObject(data);				
				frameDB.close();
				dbEnv.close();
				return f;
	}
	public static void main(String args[]) throws Exception{
        String w = args[0];
        Frame f = makeFrame(w);
        System.out.println(f.toString());
	}

}
