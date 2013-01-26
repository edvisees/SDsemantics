package edvisees.cs.cmu.edu.SDsemantics.Composition;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import edvisees.cs.cmu.edu.SDsemantics.Frame.Frame;
import edvisees.cs.cmu.edu.SDsemantics.Frame.Relation;
/**
 * @author Mrinmaya Sachan
 * I think that compositions should be implemented as functions in Frame.java ??
 */
public class Composition{
	private Frame frame1;
	private Frame frame2;
	
	public Composition(){}
	
	public Composition(Frame frame1, Frame frame2) {
		super();
		this.frame1 = frame1;
		this.frame2 = frame2;
	}

	public Frame getFrame1() {
		return frame1;
	}

	public void setFrame1(Frame frame1) {
		this.frame1 = frame1;
	}

	public Frame getFrame2() {
		return frame2;
	}

	public void setFrame2(Frame frame2) {
		this.frame2 = frame2;
	}
	
	public Frame add(){
		Hashtable <String,Relation> relationalFrame = new Hashtable<String,Relation>();
		Hashtable <String,Relation> relationalFrame1 = frame1.getRelationalFrame();
		Hashtable <String,Relation> relationalFrame2 = frame2.getRelationalFrame();
		
		Set<String> keys = new HashSet<String>();
		keys.addAll(relationalFrame1.keySet());
		keys.addAll(relationalFrame2.keySet());
		
		Iterator<String> itr = keys.iterator();
		while(itr.hasNext()) {
	         String key = itr.next();
	         if(!relationalFrame1.containsKey(key)) relationalFrame.put(key, relationalFrame2.get(key));
	         else if(!relationalFrame2.containsKey(key)) relationalFrame.put(key, relationalFrame1.get(key));
	         else{
	        	 Relation rel = relationalFrame1.get(key).add(relationalFrame2.get(key));
	        	 relationalFrame.put(key, rel);
	         }
	      }
		return new Frame(frame1.getName()+"*"+frame2.getName(),relationalFrame);
	}

	public Frame multiply(){
		Hashtable <String,Relation> relationalFrame = new Hashtable<String,Relation>();
		Hashtable <String,Relation> relationalFrame1 = frame1.getRelationalFrame();
		Hashtable <String,Relation> relationalFrame2 = frame2.getRelationalFrame();
		
		Set<String> keys = new HashSet<String>();
		keys.addAll(relationalFrame1.keySet());
		keys.addAll(relationalFrame2.keySet());
		
		Iterator<String> itr = keys.iterator();
		while(itr.hasNext()) {
	         String key = itr.next();
	         if(!relationalFrame1.containsKey(key)) relationalFrame.put(key, relationalFrame2.get(key));
	         else if(!relationalFrame2.containsKey(key)) relationalFrame.put(key, relationalFrame1.get(key));
	         else{
	        	 Relation rel = relationalFrame1.get(key).multiply(relationalFrame2.get(key));
	        	 relationalFrame.put(key, rel);
	         }
	      }
		return new Frame(frame1.getName()+"*"+frame2.getName(),relationalFrame);
	}
	
}
