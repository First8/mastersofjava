import java.awt.Color;

public class Shapes {

	public static final Color[][] COLORS = new Color[][] {
		{ Color.RED,Color.PINK },
		{ Color.WHITE,Color.BLACK },
		{ Color.WHITE,Color.BLACK },
		{ Color.WHITE,Color.BLACK },
		{ Color.WHITE,Color.BLACK },
		{ Color.WHITE,Color.BLACK },
		{ Color.WHITE,Color.BLACK }		
	};
	
	public static final byte[][][] SHAPES=new byte[][][] {
		{
			{ 0,1,0,1,0 },
			{ 1,0,1,0,1 },
			{ 0,1,0,1,0 },
			{ 1,0,1,0,1 },
			{ 0,1,0,1,0 }
		}, {
			{ 0,0,0,0,0 },
			{ 1,1,1,1,1 },
			{ 1,1,1,1,1 },
			{ 1,1,1,1,1 },
			{ 0,0,0,0,0 }
		}, {
			{ 0,1,1,1,0 },
			{ 1,1,1,1,0 },
			{ 1,1,1,1,0 },
			{ 1,1,1,0,0 },
			{ 0,0,0,0,0 }
		}, {
			{ 0,0,0,0,0 },
			{ 1,1,1,0,0 },
			{ 1,1,1,1,0 },
			{ 1,1,1,1,0 },
			{ 0,1,1,1,0 }
		}, {
			{ 0,1,1,1,0 },
			{ 0,1,1,1,1 },
			{ 0,1,1,1,1 },
			{ 0,0,1,1,1 },
			{ 0,0,0,0,0 }
		}, {
			{ 0,0,0,0,0 },
			{ 0,0,1,1,1 },
			{ 0,1,1,1,1 },
			{ 0,1,1,1,1 },
			{ 0,1,1,1,0 }
		}, {
			{ 0,1,1,1,0 },
			{ 0,1,1,1,0 },
			{ 0,1,1,1,0 },
			{ 0,1,1,1,0 },
			{ 0,1,1,1,0 }
		}
	};
}
