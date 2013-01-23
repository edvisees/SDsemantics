/**
 * 
 */
package edvisees.cs.cmu.edu.SDsemantics.Frame;

import java.util.ArrayList;

/**
 * @author Mrinmaya Sachan & Sujay Kumar Jauhar
 *
 */
public class Constraint {
	private ArrayList<String> surfaceForm;
	private ArrayList<String> POS;
	private ArrayList<String> NER;
	private ArrayList<String> SST;
	
	public Constraint() {
		this.surfaceForm = new ArrayList<String>();
		this.POS = new ArrayList<String>();
		this.NER = new ArrayList<String>();
		this.SST = new ArrayList<String>();
	}
	public ArrayList<String> getSurfaceForm() {
		return surfaceForm;
	}
	public void setSurfaceForm(ArrayList<String> surfaceForm) {
		this.surfaceForm = surfaceForm;
	}
	public ArrayList<String> getPOS() {
		return POS;
	}
	public void setPOS(ArrayList<String> pOS) {
		POS = pOS;
	}
	public ArrayList<String> getNER() {
		return NER;
	}
	public void setNER(ArrayList<String> nER) {
		NER = nER;
	}
	public ArrayList<String> getSST() {
		return SST;
	}
	public void setSST(ArrayList<String> sST) {
		SST = sST;
	}
	public Constraint(ArrayList<String> surfaceForm, ArrayList<String> POS, ArrayList<String> NER, ArrayList<String> SST) {
		this.surfaceForm = surfaceForm;
		this.POS = POS;
		this.NER = NER;
		this.SST = SST;
	}
	
}
