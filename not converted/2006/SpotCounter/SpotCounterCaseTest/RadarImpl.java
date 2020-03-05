
public class RadarImpl implements Radar {

	private Hit[] hits;
	
	public RadarImpl(boolean[] h) {
		hits=new HitImpl[h.length];
		for(int t=0;t<h.length;t++) {
			hits[t]=new HitImpl(h[t]);
		}
	}
	
	public Hit[] getHits() { return hits; }
	
	int expectedResult() {
		int cnt=0;
		for (Hit h:getHits()) if (h.isSpot()) cnt++;
		return cnt;
	}
	
}
