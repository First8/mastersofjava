import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import nl.moj.client.anim.LayeredAnim;

public class LittleSisterImpl implements LittleSister,BigSister {

	private LayeredAnim anim;
	private List<LayeredAnim.AnimFrame> f=new ArrayList<LayeredAnim.AnimFrame>();
	private LayeredAnim.ShapeResource sis;
	private LayeredAnim.ShapeResource refsis;
	private int ref;
	private List<Point2D> littleSis=new ArrayList<Point2D>();
	private List<Point2D> bigSis=new ArrayList<Point2D>();
	
	public LittleSisterImpl(LayeredAnim anim) {
		this.anim=anim;
		sis=anim.createShapeResource();
		refsis=anim.createShapeResource();
		sis.set(LayeredAnim.ShapeResource.SHAPE_CIRCLE,Color.RED,(short)4,(short)4);
		refsis.set(LayeredAnim.ShapeResource.SHAPE_CIRCLE,Color.BLUE,(short)4,(short)4);
		moveToo(0,0);
		moveTo(0,0);
	}
	
	public void moveTo(double x, double y) {
		LayeredAnim.AnimFrame cf=(ref<f.size()?f.get(ref):anim.createNewFrame());
		cf.add(sis,(int)(25+10*x),(int)(50-10*y),0);
		ref++;
		littleSis.add(new Point2D.Double(x,y));
	}

	public void moveToo(double x, double y) {
		LayeredAnim.AnimFrame cf=anim.createNewFrame();
		cf.add(refsis,(int)(75+10*x),(int)(50-10*y),0);
		f.add(cf);
		bigSis.add(new Point2D.Double(x,y));
	}

	public boolean check() {
		if (bigSis.size()==littleSis.size()) {
			for (int t=0;t<bigSis.size();t++) {
				double d=bigSis.get(t).distance(littleSis.get(t));
				if (d>0.01) {
					System.out.println("In move "+t+" the two sisters are out of sync.");
					System.out.println("Little Sister is at "+littleSis.get(t));
					System.out.println("Big Sister is at    "+bigSis.get(t));
					return false;
				}
			}
			return true;
		} else {
			if (littleSis.size()==0) {
				System.out.println("Little Sister did not move at all.");
			} else {
				System.out.println("Little Sister made only "+littleSis.size()+" moves, but Big Sister made "+bigSis.size()+".");
			}
			return false;
		}
	}
	
	
}
