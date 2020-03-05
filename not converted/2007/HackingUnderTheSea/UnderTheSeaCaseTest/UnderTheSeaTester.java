import nl.moj.client.anim.Anim;
import nl.moj.client.anim.LayeredAnim;
import nl.moj.model.Tester;

public class UnderTheSeaTester implements Tester.AnimatedTestable {
	
	private static final String[] NAMES=new String[] {
			"Straight Pipe",
			"Single Swap",
			"The Bends",
			"Spinning",
			"ZigZag",
			"No Solution"
	};
	
	private static final String[][] PANELS=new String[][] {
		{
			"###",
			"---",
			"###",
		},{
			"###",
			"-|-",
			"#-#",
			"###"
		},{
			"####",
			"-|-#",
			"#\\`-",
			"####"
		},{
			"#####",
			"-/-\\#",
			"#|#|#",
			"#`-,-",
			"#####"
		}, {
			"#####",
			"#,`,-",
			"#`//#",
			"-\\\\-#",
			"#####"
		}, {
			"####",
			"--|#",
			"#|--",
			"####"
		}
	};
	
	private boolean[] SOLVABLE=new boolean[] {
		true,true,true,true,true,false
	};
	
	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}
	
	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		if (SOLVABLE[nr]) {
			sb.append("Rearrange the pipes so that a connection is formed between the entrance pipe (left)\n");
			sb.append("and the exit pipe (right). Blocks cannot be moved and neither can the exit and\n");
			sb.append("entrance pipes. The initial panel layout is as follows:\n");
		} else {
			sb.append("The following panel cannot be solved, but your algorithm should not crash on it.\n");
		}
		sb.append(new PipePanelImpl(PANELS[nr]).toString());
		return sb.toString();
	}
	
	public boolean performTest(int nr) throws Throwable {
		return performTest(nr,new Anim[NAMES.length]);
	}

	public boolean performTest(int nr, Anim[] a) throws Throwable {
		//
		// Set up the animation
		//
		LayeredAnim anim=new LayeredAnim();
		a[nr]=anim;
		//
		// Create some resources.
		//
		LayeredAnim.BitMapResource[] bmp=new LayeredAnim.BitMapResource[Pipe.values().length];
		for (int t=0;t<bmp.length;t++) {
			bmp[t]=anim.createBitmapResource();
			bmp[t].setImageData(Shapes.COLORS[t],Shapes.SHAPES[t]);
		}	
		//
		// Create a new Instance for each test.
		//
		PipePanelImpl pp=new PipePanelImpl(PANELS[nr],anim,bmp);
		try {
			AutoHackerImpl instance=new AutoHackerImpl();		
			//
			instance.hack(pp);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		//
		return SOLVABLE[nr]==pp.isHacked();
	}
	
	public static void main(String[] args) throws Throwable{
		System.out.println(new UnderTheSeaTester().performTest(4));
	}
	
}
