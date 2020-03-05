import java.util.Arrays;

import nl.moj.client.anim.Anim;
import nl.moj.client.anim.LayeredAnim;
import nl.moj.model.Tester;

public class TwisterSisterTester implements Tester.AnimatedTestable {
	
	private static final String[] NAMES=new String[] {
			"Left",
			"Right",
			"Small Left",
			"Big Left",
			"Rotate Left",
			"Rotate Right",
			"Quick Step",
			"Square Dance #1",
			"Square Dance #2",
			"Shaking Sisters",
	};
	
	private static final Move[][] MOVES=new Move[][] {
		{ Move.STEP_LEFT },
		{ Move.STEP_RIGHT },
		{ Move.SMALLER,Move.STEP_LEFT },
		{ Move.BIGGER,Move.STEP_LEFT },
		{ Move.TURN_LEFT, Move.STEP_LEFT, Move.STEP_RIGHT },
		{ Move.TURN_RIGHT, Move.STEP_LEFT, Move.STEP_RIGHT },
		{ Move.STEP_LEFT,Move.STEP_RIGHT,Move.STEP_LEFT,Move.STEP_RIGHT,Move.STEP_LEFT,Move.STEP_RIGHT },
		{ Move.STEP_LEFT,Move.TURN_LEFT,Move.STEP_LEFT,Move.TURN_LEFT,Move.STEP_LEFT,Move.TURN_LEFT,Move.STEP_LEFT },
		{ Move.STEP_LEFT,Move.TURN_RIGHT,Move.STEP_LEFT,Move.TURN_RIGHT,Move.STEP_LEFT,Move.TURN_RIGHT,Move.STEP_LEFT },
		{ Move.SMALLER,Move.SMALLER,Move.STEP_LEFT,Move.STEP_RIGHT,Move.BIGGER,Move.STEP_LEFT,Move.STEP_RIGHT,Move.BIGGER,Move.STEP_LEFT,Move.STEP_RIGHT },
	};
	
	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}
	
	public String getTestDescription(final int nr) {
		final StringBuffer sb=new StringBuffer();
		sb.append("Your sisters are going to perform the following dance routine:\n");
		StringBuffer m=new StringBuffer();
		for (int t=0;t<MOVES[nr].length;t++) {
			if (t>0) m.append(", ");
			if ((m.length()>64)&&(m.indexOf("\n")<0)) m.append("\n");
			m.append(MOVES[nr][t]);
		}
		m.append("\n");
		sb.append(m.toString());
		sb.append("\nThe expected coordinates after each step are :\n");
		sb.append("INITIAL       0.0  0.0\n");
		BigTwisterSister bt=new BigTwisterSister();
		bt.dance(new BigSister() {
			int cnt=0;
			public void moveToo(double x, double y) {
				String s=MOVES[nr][cnt].toString();
				sb.append(s);
				for (int t=s.length();t<12;t++) sb.append(" ");
				sb.append(" "+(x>=0?" ":"")+x+" "+(y>=0?" ":"")+y+"\n");
				cnt++;
			}
		},Arrays.asList(MOVES[nr]));
		//
		// TODO: Generate useful test description.
		//
		return sb.toString();
	}
	
	public boolean performTest(int nr) throws Throwable {
		return performTest(nr,new Anim[getTestCount()]);
	}
	
	public boolean performTest(int nr,Anim[] anims) throws Throwable {
		//
		LayeredAnim anim=new LayeredAnim();
		anims[nr]=anim;
		BigTwisterSister ref=new BigTwisterSister();
		LittleSisterImpl sis=new LittleSisterImpl(anim);
		TwisterSisterImpl instance=new TwisterSisterImpl();
		ref.dance(sis,Arrays.asList(MOVES[nr]));
		try {
			instance.dance(sis,Arrays.asList(MOVES[nr]));
			//
			return sis.check();
		} catch (Exception ex) {
			System.out.println("Oh dear. Little Sister cries : '"+ex+"'");
			ex.printStackTrace();
			return false;
		}
	}
	
}
