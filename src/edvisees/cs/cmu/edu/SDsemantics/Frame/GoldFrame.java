package edvisees.cs.cmu.edu.SDsemantics.Frame;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class GoldFrame {
	private Frame frame1;
	private Frame frame2;
	private String relation;
	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public GoldFrame(){}
	
	public GoldFrame(Frame frame1, Frame frame2, String relation) {
		super();
		this.frame1 = frame1;
		this.frame2 = frame2;
		this.relation = relation;
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

	public Frame compose(){
		Hashtable <String,FrameComponent> relationalFrame = new Hashtable<String,FrameComponent>();
		Hashtable <String,FrameComponent> relationalFrame1 = frame1.getRelationalFrame();
		Hashtable <String,FrameComponent> relationalFrame2 = frame2.getRelationalFrame();
		
		Set<String> keys = new HashSet<String>();
		keys.addAll(relationalFrame1.keySet());
		keys.addAll(relationalFrame2.keySet());
		
		Iterator<String> itr = keys.iterator();
		while(itr.hasNext()) {
	         String key = itr.next();
	         if(!relationalFrame1.containsKey(key)) relationalFrame.put(key, relationalFrame2.get(key));
	         else if(!relationalFrame2.containsKey(key)) relationalFrame.put(key, relationalFrame1.get(key));
	         else{
	        	 FrameComponent rel = relationalFrame1.get(key).add(relationalFrame2.get(key));
	        	 relationalFrame.put(key, rel);
	         }
	      }
		return new Frame(frame1.getName()+"$"+frame2.getName(),relationalFrame);
	}
}
