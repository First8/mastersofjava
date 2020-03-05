
public class LifeReference {
	
	public boolean[][] getNextGeneration(boolean[][] current) {
		boolean[][] next=new boolean[current.length][current[0].length];
		for (int y=0;y<current.length;y++) {
			for (int x=0;x<current[y].length;x++) {
				//
				int cnt=0;
				for (int yy=-1;yy<=1;yy++) {
					for (int xx=-1;xx<=1;xx++) {
						// Skip Self.
						if ((yy==0)&&(xx==0)) continue;
						//
						int xxx=x+xx;
						int yyy=y+yy;
						if (xxx<0) continue;
						if (yyy<0) continue;
						if (xxx>=current[0].length) continue;
						if (yyy>=current.length) continue;
						//
						if (current[yyy][xxx]) {
							cnt++;
						}
					}
				}
				//
				switch (cnt) {
				case 2: next[y][x]=current[y][x];break; // continued existence
				case 3: next[y][x]=true;break;			// birth or existence.
				default: next[y][x]=false;break;        // death           
				}
				//
			}
		}
		return next;
	}
	
}
