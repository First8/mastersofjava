import java.awt.Color;
import java.awt.geom.AffineTransform;

import nl.moj.client.anim.LayeredAnim;
import nl.moj.client.anim.LayeredAnim.ShapeResource;


public class BowlOfNoodlesImpl implements BowlOfNoodles {

	private Color[] cols=new Color[] {
			new Color(192,  0, 0),
			new Color(192,192, 0),
			new Color(  0,192, 0),
			new Color(  0,192,192),
			new Color(  0,  0,192),
			new Color(192,  0,192)
	};
	
	private LayeredAnim anim;
	private ShapeResource bowl;
	private ShapeResource[] p;
	private ShapeResource[][] c;
	private Runner[] r;
	private ChopstickImpl[] chops;
	private ThreadLocal<NoodleImpl> noodles=new ThreadLocal<NoodleImpl>();
	
	public BowlOfNoodlesImpl(LayeredAnim anim) {
		this.anim=anim;
	}
	
	void clearNoodle() {
		noodles.set(null);
	}
	
	NoodleImpl getNoodle() {
		return noodles.get();
	}
	
	public void setRunners(Runner[] r,ChopstickImpl[] chopz) {
		this.r=r;
		chops=new ChopstickImpl[chopz.length<=2?2:chopz.length-1];
		System.arraycopy(chopz,0,chops, 0, chops.length);
		p=new ShapeResource[r.length];
		for (int t=0;t<p.length;t++) {
			p[t]=anim.createShapeResource();
			p[t].set(LayeredAnim.ShapeResource.SHAPE_CIRCLE,cols[t],(short)10,(short)10);
		}
		c=new ShapeResource[chops.length][r.length+1];
		for (int t=0;t<c.length;t++) {
			c[t][0]=anim.createShapeResource();
			c[t][0].set(LayeredAnim.ShapeResource.SHAPE_TRIANGLE,Color.BLACK,(short)3,(short)12);
			for (int y=0;y<r.length;y++) {
				c[t][y+1]=anim.createShapeResource();
				c[t][y+1].set(LayeredAnim.ShapeResource.SHAPE_TRIANGLE,cols[y],(short)3,(short)12);
			}
		}
		bowl=anim.createShapeResource();
		bowl.set(LayeredAnim.ShapeResource.SHAPE_CIRCLE,Color.YELLOW,(short)15,(short)15);
		render();
	}
	
	public synchronized void render() {
		LayeredAnim.AnimFrame af=(LayeredAnim.AnimFrame)anim.createNewFrame();
		double rotc=Math.PI*2/c.length;
		double rot=Math.PI*2/p.length;
		double sumrot=0;
		AffineTransform at=new AffineTransform();
		at.translate(50,50);
		af.add(bowl,(int)at.getTranslateX(),(int)at.getTranslateY(),0);
		for (int t=0;t<p.length;t++) {
			at.rotate(rot);sumrot+=rot;
			at.translate(-35,0);
			af.add(p[t],(int)at.getTranslateX(),(int)at.getTranslateY(),0);
			at.translate(35,0);
		}
		if (c.length!=2) {
			sumrot+=-rot/2;
			at.rotate(-rot/2); 
		} else { 
			sumrot+=-rot/4;
			at.rotate(-rot/4);
		}
		for (int t=0;t<c.length;t++) {
			at.rotate(rotc);sumrot+=rotc;
			at.translate(-30,0);
			int i=whoOwns(t);
			af.add(c[t][i],(int)at.getTranslateX(),(int)at.getTranslateY(),(int)(90+180*sumrot/Math.PI));
			at.translate(30,0);
		}
	}
	
	private int whoOwns(int chop) {
		for (int t=0;t<r.length;t++) {
			Thread tr=r[t].getThread();
			if (tr==null) continue;
			OwnedReentrantLock l=(OwnedReentrantLock)chops[chop].getOuterLock();
			if (tr.equals(l.getOwner())) return t+1;
			l=(OwnedReentrantLock)chops[chop].getInnerLock();
			if (tr.equals(l.getOwner())) return t+1;
		}
		return 0;
	}
	
	@Override
	public Noodle take(Chopstick first, Chopstick second) {
		if (first==null) throw new NullPointerException(Thread.currentThread().getName()+" has no chopstick in his left hand.");
		if (first==null) throw new NullPointerException(Thread.currentThread().getName()+" has no chopstick in his right hand.");
		if (first.equals(second)) throw new RuntimeException(Thread.currentThread()+" tries to eat a noodle with one chopstick and two hands, failing miserably.");
		ChopstickImpl one=(ChopstickImpl)first;
		ChopstickImpl two=(ChopstickImpl)second;
		if (one.getInnerLock().tryLock()) try {
			if (two.getInnerLock().tryLock()) try {
				System.out.println(Thread.currentThread().getName()+" picks a noodle.");				
				Thread.yield();
				render();
				Runner.delay(100);
				Thread.yield();
				NoodleImpl noodle=new NoodleImpl();
				noodles.set(noodle);
				return noodle;				
			} finally {
				two.getInnerLock().unlock();
			} else {
				render();
				throw new RuntimeException(Thread.currentThread().getName()+" tries to take the second chopstick, but it is already gone.");
			}
		} finally {
			one.getInnerLock().unlock();
		} else {
			render();
			throw new RuntimeException(Thread.currentThread().getName()+" tries to take the first chopstick, but it is already gone.");
		}
	}
	
}
