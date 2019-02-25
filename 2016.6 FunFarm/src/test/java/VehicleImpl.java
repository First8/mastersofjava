

public class VehicleImpl implements Vehicle {

	private boolean horizontal,player;
	private ParkingAreaImpl area;
	private int x,y,l;
	
	protected VehicleImpl(ParkingAreaImpl pa,VehicleImpl v) {
	    this.area=pa;
	    this.x=v.x;
	    this.y=v.y;
	    this.l=v.l;
	    this.horizontal=v.horizontal;
	    this.player=v.player;
	}
	
	public VehicleImpl(ParkingAreaImpl pa,int x,int y,int l,boolean hor,boolean player) {
		this.x=x;
		this.y=y;
		this.l=l; 
		this.horizontal=hor;
		this.player=player;
		this.area=pa;
		pa.testEmpty(this);
	}
	
	@Override
	public boolean canMove(int steps) {
		return area.canMove(this,steps);
	}
	public boolean canMove() {
		return area.canMove(this,-1)||area.canMove(this,1);
	}
	
	@Override
	public void move(int steps) {
		if (area.canMove(this,steps)) {
			if (isHorizontal()) {
				x=x+steps;
			} else {
				y=y+steps;
			}
			area.render();
		} else {
			throw new RuntimeException("Unable to move '"+steps+"' for "+this);
		}
	}
	
	//
	//
	//
	
	@Override
	public int getLength() {
		return l;
	}
	
	@Override
	public int getX() {
		return x;
	}
	@Override
	public int getY() {
		return y;
	}
	@Override
	public boolean isHorizontal() {
		return horizontal;
	}
	@Override
	public boolean isVertical() {
		return !horizontal;
	}
	@Override
	public boolean isPlayer() {
		return player;
	}
	
	//
	//
	//
	
	protected boolean isAt(int xx,int yy) {
		if (isVertical()) {
			if (xx==x) {
				if ((yy>=y)&&(yy<y+l)) return true;
			} 
		} else if (yy==y) {
			if ((xx>=x)&&(xx<x+l)) return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "V(x="+x+",y="+y+",l="+l+",h="+horizontal+")";
	}
	
}
