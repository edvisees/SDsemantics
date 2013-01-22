/**
 * 
 */
package edvisees.cs.cmu.edu.SDsemantics.Frame;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
/**
 * @author Mrinmaya & Sujay
 *
 */
public class Frame {
	private Hashtable <String,Relation> relationalFrame;
	
	public Frame(){
		this.relationalFrame = new Hashtable<String,Relation>();
	}
	
	public Frame(Hashtable<String,Relation> relationalFrame){
		this.relationalFrame = relationalFrame;
	}
	
	public Hashtable<String, Relation> getRelationalFrame() {
		return relationalFrame;
	}

	public void setRelationalFrame(Hashtable<String, Relation> relationalFrame) {
		this.relationalFrame = relationalFrame;
	}
	
	/*
	public Frame reduceFrame(Hashtable<String,Constraint> constraints){
		Frame frame = new Frame();
		 Iterator it = constraints.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        String constKey = (String) pairs.getKey();
		        Constraint cons = (Constraint) pairs.getValue();
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		
		return frame;
	}
	*/
}
