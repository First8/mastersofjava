import java.awt.Color;

import nl.moj.client.anim.Anim;
import nl.moj.client.anim.LayeredAnim;
import nl.moj.model.Tester;

public class LifeAsWeKnowItTester implements Tester.AnimatedTestable {
	
	private static final String[] NAMES=new String[] {
			"Birth",
			"Existence",
			"Existence II",
			"Death",
			"Death revisited",
			"Too many cells",
			"Blinker",
			"Glider",
			"F-Pentomino"
	};
	
	private static final String[][] INITIAL_BOARDS=new String[][] {
		{
			"....",
			".##.",
			".#..",
			"...."
		},{
			"....",
			".##.",
			" ##.",
			"...."
		},{
			".....",
			"..#..",
			" #.#.",
			"..#..",
			"....."
		},{
			"...",
			".#.",
			"...",
		},{
			"...",
			".##.",
			"....",
		},{
			".....",
			"..#..",
			" ###.",
			"..#..",
			"....."
		},{
			".....",
			"..#..",
			"..#..",
			"..#..",
			"....."
		},{
			".....",
			".....",
			".##..",
			"#.#..",
			"..#.."
		},{
			".........",
			".........",
			".........",
			"....##...",
			"...##....",
			"....#....",
			".........",
			".........",
			"........."
		}
	};
	
	private final int[] GENERATIONS=new int[] {
		1,2,2,1,2,4,4,6,64	
	};
	
	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}
	
	public String getTestDescription(int nr) {
		StringBuilder sb=new StringBuilder();
		//
		boolean[][] p=getBoard(nr);
		sb.append("Given the following board, evaluate the "+GENERATIONS[nr]+" future generations according to Conway's Laws.\n");
        for (int y=0;y<p.length;y++) {
            for (int x=0;x<p[0].length;x++) {
                if (p[y][x]) {
                    sb.append("#");
                } else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }	
        sb.append("The expected result is ");
        switch (nr) {
        case 0: sb.append("the birth of a cell.\n");
                break;
        case 2:
        case 1: sb.append("the continued existence of the cells.\n");
                break;
        case 3: sb.append("the death of a cell.\n");
                break;
        case 4: sb.append("the death of the two cells.\n");
                break;
        case 5: sb.append("the death of the middle cell and an explosion of life.\n");
                break;
        case 6: sb.append("an oscillator with cycle of 2.\n");
                break;
        case 7: sb.append("a glider that moves to the top-right.\n");
                break;
        case 8: sb.append("an explosion of life.\n");
                break;
        }
		//
		return sb.toString();
	}
	
	public boolean performTest(int nr) throws Throwable {
		return performTest(nr,new Anim[NAMES.length]);
	}
	
	private boolean[][] getBoard(int nr) {
		String[] b=INITIAL_BOARDS[nr];
		boolean[][] board=new boolean[b.length][b[0].length()];
		for (int y=0;y<board.length;y++) {
			for (int x=0;x<board[0].length;x++) {
				if (b[y].charAt(x)=='#') {
					board[y][x]=true;
				}
			}
		}
		return board;
	}
	
	private void renderDual(boolean[][] p,boolean[][] r) {
		StringBuilder sb=new StringBuilder();
		for (int y=0;y<p.length;y++) {
			for (int x=0;x<p[0].length;x++) {
				if (p[y][x]!=r[y][x]) {
					if (p[y][x]) {
						sb.append("*");
					} else {
						sb.append("+");
					}
				} else if (p[y][x]) {
					sb.append("#");
				} else {
					sb.append(".");
				}
			}
			if (y==0) {
				sb.append("   # and . : correct. \n");
			} else if (y==1) {
				sb.append("   * : true but should be false. \n");
			} else if (y==2) {
				sb.append("   + : false but should be true. \n");
			} else {
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	private boolean compareBoards(boolean[][] p,boolean[][] r) {
		if (p.length!=r.length) {
			System.out.println("X Board height incorrect. Got "+p.length+" but expected "+r.length);
			return false;
		}
		for (int t=0;t<p.length;t++) {
			if (p[t].length!=r[t].length) {
				System.out.println("X Board width incorrect. Got "+p.length+" but expected "+r.length+" at row "+t);
				return false;
			}
		}
		boolean result=true;
		for (int y=0;y<p.length;y++) {
			for (int x=0;x<p[0].length;x++) {
				if (p[y][x]!=r[y][x]) {
					System.out.println("X Mismatch at cell ("+x+","+y+"). Got "+p[y][x]+" but expected "+r[y][x]);
					result=false;
				}
			}
		}
		if (!result) {
			renderDual(p,r);
		}
		return result;
	}
	
	private void render(LayeredAnim.AnimFrame f,LayeredAnim.ShapeResource[][] shape,LayeredAnim.ShapeResource[][] bad,boolean[][] board,boolean[][] reference) {
		for (int y=0;y<shape.length;y++) {
			for (int x=0;x<shape.length;x++) {
				if (reference[y][x]==board[y][x]) {
					if (board[y][x]) {
						f.add(shape[y][x],10+x*10,10+y*10,0);
					}
				} else if ((reference[y][x])||(board[y][x])) {				    
					f.add(bad[y][x],10+x*10,10+y*10,0);
				}
			}
		}
	}

	public boolean performTest(int nr, Anim[] a) throws Throwable {
		//
		// Set up the animation
		//
		LayeredAnim anim=new LayeredAnim();
		a[nr]=anim;
		//
		// Create some resources.
		//
		LayeredAnim.ShapeResource[][] shape=new LayeredAnim.ShapeResource[INITIAL_BOARDS[nr].length][INITIAL_BOARDS[nr][0].length()];
		LayeredAnim.ShapeResource[][] bad=new LayeredAnim.ShapeResource[INITIAL_BOARDS[nr].length][INITIAL_BOARDS[nr][0].length()];
		for (int y=0;y<shape.length;y++) {
			for (int x=0;x<shape.length;x++) {
				shape[y][x]=anim.createShapeResource();
				shape[y][x].set(LayeredAnim.ShapeResource.SHAPE_CIRCLE,Color.GREEN,(short)10,(short)10);		
				bad[y][x]=anim.createShapeResource();
				bad[y][x].set(LayeredAnim.ShapeResource.SHAPE_CIRCLE,Color.RED,(short)8,(short)8);		
			}
		}
		//
		//
		//
		boolean result=true;
		int badCnt=0;
		//
		// Create a new Instance for each test.
		//
		try {
			//
			Life player=new LifeImpl();
			boolean[][] playerBoard=getBoard(nr);
			LifeReference reference=new LifeReference();
			boolean[][] referenceBoard=getBoard(nr);
			//
			LayeredAnim.AnimFrame frame=anim.createNewFrame();
			render(frame,shape,bad,playerBoard,referenceBoard);
			//
			for (int t=0;t<GENERATIONS[nr];t++) {
				playerBoard=player.getNextGeneration(playerBoard);
				referenceBoard=reference.getNextGeneration(referenceBoard);
				//
				render(anim.createNewFrame(),shape,bad,playerBoard,referenceBoard);
				//
				if (!compareBoards(playerBoard,referenceBoard)) {
					result=false;
					badCnt++;
					if (badCnt>10) {
					    System.out.println("Aborted.");
					    break;
					}
				}
				//
			}
			//
		} catch (Exception ex) {
			//
			// Catch the exception, so that other tests may
			// still be executed. Do print the stacktrace. 
			//
		    System.out.println("Oops, the world has come to an end..");
			ex.printStackTrace();
			//
			return false;
		}
		//
		return result;
	}
	
}
