import java.awt.Point;

import nl.moj.client.anim.LayeredAnim;

public class PipePanelImpl implements PipePanel {

	private Pipe[][] panel;
	private Point start;
	private Point end;
	
	private LayeredAnim anim;
	private LayeredAnim.SpriteResource[][] sprite;
	private int renderCount;

	public PipePanelImpl(String[] p,LayeredAnim anim,LayeredAnim.BitMapResource[] bmp) {
		this(p);
		this.anim=anim;
		sprite=new LayeredAnim.SpriteResource[getHeight()][getWidth()];
		for (int y=0;y<panel.length;y++) {		
			for (int x=0;x<panel[0].length;x++) {
				sprite[y][x]=anim.createSpriteResource(bmp[panel[y][x].ordinal()]);
			}
		}
		render();
	}
	
	private void render() {
		if (renderCount++>100) throw new RuntimeException("Too many frames.");
		LayeredAnim.AnimFrame frame=anim.createNewFrame();
		for (int y=0;y<panel.length;y++) {		
			for (int x=0;x<panel[0].length;x++) {
				frame.add(sprite[y][x],40+x*5,40+y*5,0);
			}
		}
	}
	
	public PipePanelImpl(String[] p) {
		panel=new Pipe[p.length][p[0].length()];
		for (int y=0;y<panel.length;y++) {		
			for (int x=0;x<panel[0].length;x++) {
				panel[y][x]=fromChar(p[y].charAt(x));
			}
		}
		for (int y=0;y<getHeight();y++) {
			if (Pipe.HORIZONTAL.equals(panel[y][0])) {
				start=new Point(0,y);
			}
			if (Pipe.HORIZONTAL.equals(panel[y][getWidth()-1])) {
				end=new Point(getWidth()-1,y);
			}
		}
		if (start==null) throw new NullPointerException("No start");
		if (end==null) throw new NullPointerException("No end");
	}
	
	private Pipe fromChar(char c) {
		switch (c) {
		case '#': return Pipe.BLOCK;
		case '|': return Pipe.VERTICAL;
		case '-': return Pipe.HORIZONTAL;
		case '\\': return Pipe.LEFT2BOTTOM;
		case '/': return Pipe.RIGHT2BOTTOM;
		case '`': return Pipe.RIGHT2TOP;
		case ',': return Pipe.LEFT2TOP;
		default: throw new RuntimeException("Unknown Char '"+c+"'");
		}
	}
	
	public int count(Pipe p) {
		int cnt=0;
		for (int y=0;y<getHeight();y++) {
			for (int x=0;x<getWidth();x++) {
				if (panel[y][x].equals(p)) cnt++;
			}
		}
		return cnt;
	}
	public Pipe get(int x, int y) {
		return panel[y][x];
	}
	private void set(int x,int y,Pipe p) {
		if (p==null) throw new NullPointerException("Cannot set a NULL Pipe");
		panel[y][x]=p;
	}
	public Pipe get(Point p) {
		return get(p.x,p.y);
	}
	public Point getEnd() {
		return new Point(end);
	}
	public int getHeight() {
		return panel.length;
	}
	public Point getStart() {
		return new Point(start);
	}
	public int getWidth() {
		return panel[0].length;
	}
	public boolean isHacked() {
		Point current=getStart();
		Point prev=null;
		try {
			do {
				Point next=flow(current,prev);
				if (next.equals(end)) return true;
				prev=current;
				current=next;
			} while (true);
		} catch (DeadEndException ex) {
			return false;
		}		
	}
	public Point flow(Point current,Point previous) {
		if (current==null) throw new NullPointerException("Null current position.");
		return flow(current,previous,get(current.x,current.y));
	}
	public Point flow(Point current,Point previous,Pipe atCurrent) {
		if (current==null) throw new NullPointerException("Null current position.");
		Point next=new Point(current);
		if (previous==null) {
			previous=new Point(current);
			previous.x--;
		}
		int dx=current.x-previous.x;
		int dy=current.y-previous.y;
		// Check sanity of points.
		if ((dx==0)&&(dy==0)) throw new RuntimeException("Two points are the same.");
		if (Math.abs(dx)>1) throw new RuntimeException("Points are not adjacent.");
		if (Math.abs(dy)>1) throw new RuntimeException("Points are not adjacent.");
		if ((dx!=0)&&(dy!=0)) throw new RuntimeException("Cannot move diagonally.");
		// 
		if (dx==1) {
			// move left->right
			switch (atCurrent) {
			case LEFT2BOTTOM: next.y++;break;
			case HORIZONTAL: next.x++;break;
			case LEFT2TOP:next.y--;break;
			case RIGHT2BOTTOM:
			case RIGHT2TOP:
			case VERTICAL :
			case BLOCK: throw new DeadEndException(current);
			}
		} else if (dx==-1) {
			// move right->left
			switch (atCurrent) {
			case RIGHT2BOTTOM: next.y++;break;
			case RIGHT2TOP: next.y--;break;
			case HORIZONTAL: next.x--;break;
			case VERTICAL : 
			case LEFT2BOTTOM: 
			case LEFT2TOP:
			case BLOCK: throw new DeadEndException(current);
			}
		} else if (dy==1) {
			// move top->bottom
			switch (atCurrent) {
			case LEFT2TOP: next.x--;break;
			case RIGHT2TOP: next.x++;break;
			case VERTICAL : next.y++;break;
			case HORIZONTAL: 
			case LEFT2BOTTOM: 
			case RIGHT2BOTTOM: 
			case BLOCK: throw new DeadEndException(current);
			}
		} else if (dy==-1) {
			// move bottom->top
			switch (atCurrent) {
			case LEFT2BOTTOM: next.x--;break; 
			case RIGHT2BOTTOM: next.x++;break; 
			case VERTICAL : next.y--;break;
			case HORIZONTAL: 
			case LEFT2TOP: 
			case RIGHT2TOP: 
			case BLOCK: throw new DeadEndException(current);
			}
		}
		return next;
	}
	
	public void swap(int x1, int y1, int x2, int y2) {
		if ((x1==0)||(x2==0)) throw new NullPointerException("Cannot swap the left most tiles.");
		if ((x1==getWidth()-1)||(x2==getWidth()-1)) throw new NullPointerException("Cannot swap the right most tiles.");
		Pipe p1=get(x1,y1);
		Pipe p2=get(x2,y2);
		if (Pipe.BLOCK.equals(p1)) throw new NullPointerException("Cannot swap a BLOCK.");
		if (Pipe.BLOCK.equals(p2)) throw new NullPointerException("Cannot swap a BLOCK.");
		set(x1,y1,p2);
		set(x2,y2,p1);
		//
		if (anim!=null) {
			LayeredAnim.SpriteResource s1=sprite[y1][x1];
			LayeredAnim.SpriteResource s2=sprite[y2][x2];
			sprite[y2][x2]=s1;
			sprite[y1][x1]=s2;
			render();
		}
		//
	}
	public void swap(Point start, Point end) {
		swap(start.x,start.y,end.x,end.y);
	}
	
	public String toString() {
		StringBuffer sb=new StringBuffer();
		for (int y=0;y<getHeight();y++) {
			StringBuffer sb1=new StringBuffer();
			StringBuffer sb2=new StringBuffer();
			StringBuffer sb3=new StringBuffer();
			for (int x=0;x<getWidth();x++) {
				switch (get(x,y)) {
				case BLOCK:       sb1.append("###");sb2.append("###");sb3.append("###");break;
				case HORIZONTAL:  sb1.append("   ");sb2.append("---");sb3.append("   ");break;
				case VERTICAL:  sb1.append(" | ");sb2.append(" | ");sb3.append(" | ");break;
				case LEFT2BOTTOM: sb1.append("   ");sb2.append("-\\ ");sb3.append(" | ");break;
				case LEFT2TOP:    sb1.append(" | ");sb2.append("-/ ");sb3.append("   ");break;
				case RIGHT2BOTTOM:sb1.append("   ");sb2.append(" /-");sb3.append(" | ");break;
				case RIGHT2TOP:   sb1.append(" | ");sb2.append(" \\-");sb3.append("   ");break;
				}
			}
			sb.append(sb1.toString());
			sb.append("\n");
			sb.append(sb2.toString());
			sb.append("\n");
			sb.append(sb3.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}
