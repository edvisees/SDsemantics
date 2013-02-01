/**
 * 
 */
package edvisees.cs.cmu.edu.SDsemantics.Frame;
import java.io.Serializable;
import java.util.Hashtable;

/**
* @author Mrinmaya Sachan & Sujay Kumar Jauhar
 *
 */
public class Frame implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3895655797236013992L;
	private String name;
	private Hashtable <String,Relation> relationalFrame;
	
	public Frame(){
		this.relationalFrame = new Hashtable<String,Relation>();
	}
	
	@Override
	public String toString() {
		String str = "\nFrame [\n\tname = " + name + "\n\trelationalFrame =\n" + relationalFrame.toString() + "\n\t]\n";
		return str;
	}

	public Frame(String name, Hashtable<String,Relation> relationalFrame){
		this.name = name;
		this.relationalFrame = relationalFrame;
	}
	
	public Hashtable<String, Relation> getRelationalFrame() {
		return relationalFrame;
	}

	public void setRelationalFrame(Hashtable<String, Relation> relationalFrame) {
		this.relationalFrame = relationalFrame;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
