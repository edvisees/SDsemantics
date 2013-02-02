/**
 * 
 */
package edvisees.cs.cmu.edu.SDsemantics.Frame;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Mrinmaya Sachan & Sujay Kumar Jauhar
 *
 */
public class FrameComponent implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5662105077704023617L;
	private Hashtable<String, ArrayList<String>> surfaceForm;
	private Hashtable<String,ArrayList<String>> POS;
	private Hashtable<String,ArrayList<String>> NER;
	private Hashtable<String,ArrayList<String>> SST;
	private Hashtable<String,ArrayList<String>> lemma;
	
	public FrameComponent(){
		this.surfaceForm = new Hashtable<String,ArrayList<String>>();
		this.POS = new Hashtable<String,ArrayList<String>>();
		this.NER = new Hashtable<String,ArrayList<String>>();
		this.SST = new Hashtable<String,ArrayList<String>>();
		this.lemma = new Hashtable<String,ArrayList<String>>();
	}
	
	public FrameComponent(Hashtable<String, ArrayList<String>> surfaceForm, Hashtable<String, ArrayList<String>> POS, Hashtable<String, ArrayList<String>> NER, Hashtable<String, ArrayList<String>> SST, Hashtable<String, ArrayList<String>> lemma){
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

	public Hashtable<String, ArrayList<String>> htUnion(Hashtable<String, ArrayList<String>> surfaceForm1, Hashtable<String, ArrayList<String>> surfaceForm2){
		Hashtable<String, ArrayList<String>> surfaceForm = new Hashtable<String, ArrayList<String>>();
		Set<String> keys = new HashSet<String>();
		keys.addAll(surfaceForm1.keySet());
		keys.addAll(surfaceForm2.keySet());
		Iterator<String> itr = keys.iterator();
		while(itr.hasNext()) {
	         String key = itr.next();
	         if(!surfaceForm1.containsKey(key)) surfaceForm.put(key, surfaceForm2.get(key));
	         else if(!surfaceForm2.containsKey(key)) surfaceForm.put(key, surfaceForm1.get(key));
	         else{
	        	 ArrayList<String> addList = surfaceForm1.get(key);
	        	 addList.addAll(surfaceForm2.get(key));
	        	 surfaceForm.put(key, addList);
	         }
	      }
		return surfaceForm;
	}
	
	public Hashtable<String, ArrayList<String>> htIntersect(Hashtable<String, ArrayList<String>> surfaceForm1, Hashtable<String, ArrayList<String>> surfaceForm2){
		Hashtable<String, ArrayList<String>> surfaceForm = new Hashtable<String, ArrayList<String>>();
		Set<String> keys = new HashSet<String>();
		keys.addAll(surfaceForm1.keySet());
		keys.addAll(surfaceForm2.keySet());
		Iterator<String> itr = keys.iterator();
		while(itr.hasNext()) {
	         String key = itr.next();
	         if(!surfaceForm1.containsKey(key));
	         else if(!surfaceForm2.containsKey(key));
	         else{
	        	 ArrayList<String> addList = surfaceForm1.get(key);
	        	 addList.retainAll(surfaceForm2.get(key));
	        	 surfaceForm.put(key, addList);
	         }
	      }
		return surfaceForm;
	}
	public FrameComponent union(FrameComponent rel){
		Hashtable<String, ArrayList<String>> surfaceForm = htUnion(this.getSurfaceForm(),rel.getSurfaceForm());
		Hashtable<String, ArrayList<String>> POS = htUnion(this.getPOS(),rel.getPOS());
		Hashtable<String, ArrayList<String>> NER = htUnion(this.getNER(),rel.getNER());
		Hashtable<String, ArrayList<String>> SST = htUnion(this.getSST(),rel.getSST());
		Hashtable<String, ArrayList<String>> lemma = htUnion(this.getLemma(),rel.getLemma());
		return new FrameComponent(surfaceForm,POS,NER,SST,lemma);
	}
	
	public FrameComponent intersect(FrameComponent rel){
		Hashtable<String, ArrayList<String>> surfaceForm = htIntersect(this.getSurfaceForm(),rel.getSurfaceForm());
		Hashtable<String, ArrayList<String>> POS = htIntersect(this.getPOS(),rel.getPOS());
		Hashtable<String, ArrayList<String>> NER = htIntersect(this.getNER(),rel.getNER());
		Hashtable<String, ArrayList<String>> SST = htIntersect(this.getSST(),rel.getSST());
		Hashtable<String, ArrayList<String>> lemma = htIntersect(this.getLemma(),rel.getLemma());
		return new FrameComponent(surfaceForm,POS,NER,SST,lemma);
	}
	
	@Override
	public String toString() {
		return "\tRelation [\n\tsurfaceForm=" + surfaceForm.toString() + "\n\tPOS=" + POS.toString()
				+ "\n\tNER=" + NER.toString() + "\n\tSST=" + SST.toString() + "\n\tlemma=" + lemma.toString() + "\n]\n";
	}
	
}
