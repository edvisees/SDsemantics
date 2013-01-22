/**
 * 
 */
package edvisees.cs.cmu.edu.SDsemantics.Frame;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * @author Sujay
 *
 */
public class Relation {
	private Hashtable<String,ArrayList<Integer>> surfaceForm;
	private Hashtable<String,ArrayList<Integer>> POS;
	private Hashtable<String,ArrayList<Integer>> NER;
	private Hashtable<String,ArrayList<Integer>> SST;
	
	public Relation(){
		this.surfaceForm = new Hashtable<String,ArrayList<Integer>>();
		this.POS = new Hashtable<String,ArrayList<Integer>>();
		this.NER = new Hashtable<String,ArrayList<Integer>>();
		this.SST = new Hashtable<String,ArrayList<Integer>>();
	}
	
	public Relation(Hashtable<String,ArrayList<Integer>> surfaceForm, Hashtable<String,ArrayList<Integer>> POS, Hashtable<String,ArrayList<Integer>> NER, Hashtable<String,ArrayList<Integer>> SST){
		this.surfaceForm = surfaceForm;
		this.POS = POS;
		this.NER = NER;
		this.SST = SST;
	}

	public Hashtable<String, ArrayList<Integer>> getSurfaceForm() {
		return surfaceForm;
	}

	public void setSurfaceForm(Hashtable<String, ArrayList<Integer>> surfaceForm) {
		this.surfaceForm = surfaceForm;
	}

	public Hashtable<String, ArrayList<Integer>> getPOS() {
		return POS;
	}

	public void setPOS(Hashtable<String, ArrayList<Integer>> pOS) {
		POS = pOS;
	}

	public Hashtable<String, ArrayList<Integer>> getNER() {
		return NER;
	}

	public void setNER(Hashtable<String, ArrayList<Integer>> nER) {
		NER = nER;
	}

	public Hashtable<String, ArrayList<Integer>> getSST() {
		return SST;
	}

	public void setSST(Hashtable<String, ArrayList<Integer>> sST) {
		SST = sST;
	}
	
	
}
