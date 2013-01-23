/**
 * 
 */
package edvisees.cs.cmu.edu.SDsemantics.Frame;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * @author Mrinmaya Sachan & Sujay Kumar Jauhar
 *
 */
public class Relation {
	private Hashtable<String, ArrayList<String>> surfaceForm;
	private Hashtable<String,ArrayList<String>> POS;
	private Hashtable<String,ArrayList<String>> NER;
	private Hashtable<String,ArrayList<String>> SST;
	private Hashtable<String,ArrayList<String>> lemma;
	
	public Relation(){
		this.surfaceForm = new Hashtable<String,ArrayList<String>>();
		this.POS = new Hashtable<String,ArrayList<String>>();
		this.NER = new Hashtable<String,ArrayList<String>>();
		this.SST = new Hashtable<String,ArrayList<String>>();
		this.lemma = new Hashtable<String,ArrayList<String>>();
	}
	
	public Relation(Hashtable<String, ArrayList<String>> surfaceForm, Hashtable<String, ArrayList<String>> POS, Hashtable<String, ArrayList<String>> NER, Hashtable<String, ArrayList<String>> SST, Hashtable<String, ArrayList<String>> lemma){
		this.surfaceForm = surfaceForm;
		this.POS = POS;
		this.NER = NER;
		this.SST = SST;
		this.lemma = lemma;
	}

	public Hashtable<String, ArrayList<String>> getSurfaceForm() {
		return surfaceForm;
	}

	public void setSurfaceForm(Hashtable<String, ArrayList<String>> surfaceForm) {
		this.surfaceForm = surfaceForm;
	}

	public Hashtable<String, ArrayList<String>> getPOS() {
		return POS;
	}

	public void setPOS(Hashtable<String, ArrayList<String>> pOS) {
		POS = pOS;
	}

	public Hashtable<String, ArrayList<String>> getNER() {
		return NER;
	}

	public void setNER(Hashtable<String, ArrayList<String>> nER) {
		NER = nER;
	}

	public Hashtable<String, ArrayList<String>> getSST() {
		return SST;
	}

	public void setSST(Hashtable<String, ArrayList<String>> sST) {
		SST = sST;
	}
	
	public Hashtable<String, ArrayList<String>> getLemma() {
		return lemma;
	}

	public void setLemma(Hashtable<String, ArrayList<String>> lemma) {
		this.lemma = lemma;
	}
	
	public void update(String w2, String[] sentenceIDs) {
		ArrayList<String> temp;
		if (w2.endsWith("_root")){
			if(!lemma.containsKey(w2))	temp = new ArrayList<String>();
			else	temp = lemma.get(w2);
			for (String s : sentenceIDs)	temp.add(s);
			lemma.put(w2, temp);
		}
		else if (w2.endsWith("_POSTAG")){
			if(!POS.containsKey(w2))	temp = new ArrayList<String>();
			else	temp = POS.get(w2);
			for (String s : sentenceIDs)	temp.add(s);
			POS.put(w2, temp);
		}
		else if (w2.endsWith("_NER")){
			if(!NER.containsKey(w2))	temp = new ArrayList<String>();
			else	temp = NER.get(w2);
			for (String s : sentenceIDs)	temp.add(s);
			NER.put(w2, temp);
		}
		else if (w2.endsWith("_sst")){
			if(!SST.containsKey(w2))	temp = new ArrayList<String>();
			else	temp = SST.get(w2);
			for (String s : sentenceIDs)	temp.add(s);
			SST.put(w2, temp);
		}
		else{//Basic Word Form
			if(!surfaceForm.containsKey(w2))	temp = new ArrayList<String>();
			else	temp = surfaceForm.get(w2);
			for (String s : sentenceIDs)	temp.add(s);
			surfaceForm.put(w2, temp);
		}
	}

	@Override
	public String toString() {
		return "\tRelation [\n\tsurfaceForm=" + surfaceForm.toString() + "\n\tPOS=" + POS.toString()
				+ "\n\tNER=" + NER.toString() + "\n\tSST=" + SST.toString() + "\n\tlemma=" + lemma.toString() + "\n]\n";
	}
	
}
