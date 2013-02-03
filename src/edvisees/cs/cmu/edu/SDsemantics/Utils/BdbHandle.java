package edvisees.cs.cmu.edu.SDsemantics.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

public class BdbHandle {
	
	private EnvironmentConfig envConf;
	// open/create the DB environment using config
	private Environment dbEnv; //= new Environment(new File("lib/bdb"), envConf);
	// create/open testDB using config
	private DatabaseConfig dbConf;
	private Database nameDB;
	private StoredClassCatalog javaCatalog;
	private EntryBinding<Object> dataBinding;
		

	public Environment getDbEnv() {
		return dbEnv;
	}

	public void setDbEnv(String location) {
		this.dbEnv = new Environment(new File(location), envConf);
	}

	public BdbHandle() {
		super();
		// create a configuration for DB environment
		envConf = new EnvironmentConfig(); //handle envconf.SetAllow
		// environment will be created if not exists
		envConf.setAllowCreate(true);
		dbEnv = new Environment(new File("lib/bdb"), envConf);
		// create a configuration for DB
		dbConf = new DatabaseConfig(); //handle dbConf.setAllowCreate(true)
		// db will be created if not exits
		dbConf.setAllowCreate(true);
		nameDB = dbEnv.openDatabase(null, "nameDB", dbConf);
		// create a class catalog for the database
		javaCatalog = new StoredClassCatalog(nameDB);
		// Need a serial binding for the data
		dataBinding = new SerialBinding<Object>(javaCatalog, Object.class);
	}
	
	public void store(){
		
	}

	public Object Load(String w, String location, String DBname){
		this.nameDB = dbEnv.openDatabase(null, DBname, dbConf);
		this.dbEnv = new Environment(new File(location), envConf);
		// key
        DatabaseEntry key = new DatabaseEntry();
        // set the current word as key
        StringBinding.stringToEntry(w, key);
        DatabaseEntry data = new DatabaseEntry();
        nameDB.get(null, key, data, null);
        //generalize
        Object f = new Object();
        f = dataBinding.entryToObject(data);				
		nameDB.close();
		dbEnv.close();
		return f;
	}
	
	public void Store(){
		
	}

	public Database getNameDB() {
		return nameDB;
	}

	public void setNameDB(String DBname) {
		this.nameDB = dbEnv.openDatabase(null, DBname, dbConf);
	}
}
