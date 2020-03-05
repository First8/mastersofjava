import java.awt.geom.AffineTransform;
import java.util.Collection;

public class BigTwisterSister {

	public void dance(BigSister littleSister,Collection<Move> danceMoves) {
		//
		AffineTransform a=new AffineTransform();
		for (Move m:danceMoves) {
			switch (m) {
			case BIGGER:
				a.scale(2.0,2.0);
				break;
			case SMALLER:
				a.scale(0.5,0.5);
				break;
			case STEP_LEFT:
				a.translate(-1.0,0.0);
				break;
			case STEP_RIGHT:
				a.translate(1.0,0.0);
				break;
			case TURN_LEFT:
				a.rotate(Math.PI/2);
				break;
			case TURN_RIGHT:
				a.rotate(-Math.PI/2);
				break;
			}
			//
			littleSister.moveToo(a.getTranslateX(),a.getTranslateY());
			//
		}
		//
	}
	
}
