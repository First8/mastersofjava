import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import nl.moj.client.anim.LayeredAnim;


public class ParkingAreaImpl implements ParkingArea {
	
	private int w,h;
	private LayeredAnim a;
	private int frameCount=0;
	private List<VehicleImpl> vehicles=new ArrayList<VehicleImpl>();
	private List<LayeredAnim.ShapeResource> shapes=new ArrayList<LayeredAnim.ShapeResource>();
	
	private ParkingAreaImpl(ParkingAreaImpl p) {
	    this.w=p.w;
	    this.h=p.h;
	    this.vehicles=new ArrayList<VehicleImpl>();
	    for (int t=0;t<p.vehicles.size();t++) {
	        vehicles.add(new VehicleImpl(this,p.vehicles.get(t)));
	    }
	}
	
	public ParkingAreaImpl(int w,int h,int[][] v) {
		this.w=w;
		this.h=h;
		for (int t=0;t<v.length;t++) {
			// x,y,l,horizontal==0,player
			vehicles.add(new VehicleImpl(this,v[t][0],v[t][1],v[t][2],v[t][3]==0,t==0));
		}
		if (!vehicles.get(0).isHorizontal()) {
			throw new RuntimeException("First vehicle must be horizontal.");
		}
	}
	
	protected boolean canMove(VehicleImpl v,int steps) {
		VehicleImpl z=vehicles.get(0);
		int x=v.getX();
		int y=v.getY();
		int tst=(steps<0?0:v.getLength()-1);
		boolean h=v.isHorizontal();
		tst=(h?x+tst:y+tst);
		for (int t=0;t<Math.abs(steps);t++) {			
			tst=steps<0?tst-1:tst+1;
			int xx=h?tst:x;
			int yy=h?y:tst;
			if (xx<0) return false;
			if (yy<0) return false;
			if (yy>=this.h) return false;
			if ((xx>=this.w)&&(!z.equals(v))) return false;
			if ((xx>this.w+z.getLength())&&(z.equals(v))) return false;
			if (getVehicleAt(xx,yy)!=null) {
				return false;
			}
		}
		return true;
	}
	
	protected void testEmpty(VehicleImpl unplaced) {
		int x=unplaced.getX();
		int y=unplaced.getY();
		int l=unplaced.getLength();
		boolean h=unplaced.isHorizontal();
		for (int t=0;t<l;t++) {
			if (h) x++; else y++;
			if (getVehicleAt(x,y)!=null) {
				throw new RuntimeException(x+","+y+" is not empty.");
			}
		}
	}
	@Override
	public Vehicle[] getAllVehicles() {
		return vehicles.toArray(new Vehicle[vehicles.size()]);
	}
	@Override
	public int getHeight() {
		return h;
	}
	@Override
	public Vehicle getVehicleAt(int x, int y) {
		for (VehicleImpl v:vehicles) {
			if (v.isAt(x,y)) return v;
		}
		return null;
	}
	@Override
	public int getWidth() {
		return w;
	}
	@Override
	public boolean isCompleted() {
		return getPlayerVehicle().getX()>=w;
	}

	public String toString() {
		StringBuilder sb=new StringBuilder();
		for (int y=0;y<getHeight();y++) {
			for (int x=0;x<getWidth();x++) {
				Vehicle v=getVehicleAt(x, y);
				if (v!=null) {
					int o=vehicles.indexOf(v);
					sb.append(1+o);
				} else {
					sb.append(".");
				}
			}
			if (vehicles.get(0).getY()==y) {
				sb.append(" --> exit");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	@Override
	public Vehicle getPlayerVehicle() {
		return vehicles.get(0);
	}
	
	protected void createShapes(LayeredAnim anim) {
		this.a=anim;
		int cnt=0;
		for (Vehicle v:vehicles) {
			LayeredAnim.ShapeResource s=anim.createShapeResource();
			Color c=null;
			if (v.isPlayer()) {
				c=Color.RED;
			} else {
				switch (cnt%4) {
				case 0: c=Color.PINK;break;
				case 1: c=Color.MAGENTA;break;
				case 2: c=Color.GREEN;break;
				case 3: c=Color.CYAN;break;
				}
			}
			s.set(LayeredAnim.ShapeResource.SHAPE_RECT,c,(short)(v.isHorizontal()?v.getLength()*4:4),(short)(v.isVertical()?v.getLength()*4:4));
			shapes.add(s);
			cnt++;
		}
		LayeredAnim.ShapeResource b=anim.createShapeResource();
		b.set(LayeredAnim.ShapeResource.SHAPE_RECT,Color.DARK_GRAY,(short)(w*4),(short)(h*4));
		shapes.add(b);
		//
		LayeredAnim.ShapeResource a=anim.createShapeResource();
		a.set(LayeredAnim.ShapeResource.SHAPE_TRIANGLE,Color.RED,(short)4,(short)4);
		shapes.add(a);
	}
	protected void renderFrame(LayeredAnim anim) {
		LayeredAnim.AnimFrame frame=anim.createNewFrame();
		frame.add(shapes.get(vehicles.size()),38+w*2,38+h*2,0);
		frame.add(shapes.get(vehicles.size()+1),38+(w+1)*4,38+getPlayerVehicle().getY()*4+2,90);
		for (int t=0;t<vehicles.size();t++) {
			VehicleImpl v=vehicles.get(t);
			frame.add(shapes.get(t),38+v.getX()*4+(v.isHorizontal()?v.getLength()*2:2),38+v.getY()*4+(v.isVertical()?v.getLength()*2:2),0);
		}
	}

	protected void render() {
	    if (a!=null) {
	        if (frameCount++>420) throw new RuntimeException("More than 420 moves - aborted");
	        renderFrame(a);
	    }
	}
	
	public ParkingArea copy() {
	    return new ParkingAreaImpl(this);
	}
}
