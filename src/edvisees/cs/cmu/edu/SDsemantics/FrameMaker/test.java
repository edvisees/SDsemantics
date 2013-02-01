package edvisees.cs.cmu.edu.SDsemantics.FrameMaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class test {
	public static void main(String args[]){
	Tuple t1 = new Tuple("a","b");
	Tuple t2 = new Tuple("a","b");
	Hashtable<Tuple,Integer> ht = new Hashtable<Tuple,Integer>();
	ht.put(t1, 1);
	System.out.println(t1.equals(t2));
	System.out.println(ht.containsKey(t1));
	
	}
}
