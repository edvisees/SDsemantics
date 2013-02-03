package edvisees.cs.cmu.edu.SDsemantics.CompositionRunner;

import edvisees.cs.cmu.edu.SDsemantics.Composition.Composition;
import edvisees.cs.cmu.edu.SDsemantics.Frame.Frame;
import edvisees.cs.cmu.edu.SDsemantics.Frame.FrameLoader;

public class CompositionRunner {
	public static void main(String args[]){
		Frame frame1 = FrameLoader.makeFrame(args[0]);
		System.out.println("Frame 1 is \n"+frame1.toString());
		
		Frame frame2 = FrameLoader.makeFrame(args[1]);
		System.out.println("Frame 2 is \n"+frame2.toString());
		
		Composition comp = new Composition(frame1, frame2);
		
		Frame sum = comp.add();
		System.out.println("Sum frame is \n"+sum.toString());
		
		Frame product = comp.multiply();
		System.out.println("Product frame is \n"+product.toString());
		
//		Frame relCompose = comp.relationalComposer1(frameDB, goldDB, frame1, frame2);
//		System.out.println("Realtional Composed frame is \n"+relCompose.toString());
	}
}
