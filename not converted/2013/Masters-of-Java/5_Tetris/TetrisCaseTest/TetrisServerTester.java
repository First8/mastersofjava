public class TetrisServerTester extends TetrisTester {

    @Override
    protected TetrisTestCase[] getTestCases() {

    	TetrisTestCase[] cases = new TetrisTestCase[3];
        
        TetrisTestCase testCase;
        Game game;
        Block block;
        Row[] rows;
        int i = 0;

        // Server 1
        
        rows = new Row[4];
        rows[0] = new Row("        ");
        rows[1] = new Row("        ");
        rows[2] = new Row("        ");
        rows[3] = new Row("XXXXXX X");
        game = new Game(rows);
        
        rows = new Row[2];
        rows[0] = new Row("XXX");
        rows[1] = new Row(" X ");
        block = new Block(rows);
        
        testCase = new TetrisTestCase();
        testCase.setName("Server 1");
        testCase.setDescription("Server test 1.");
        testCase.setGame(game);
        testCase.setBlock(block);
        testCase.setRows(1);
        cases[i++] = testCase;
        
        // Server 2
        
        rows = new Row[4];
        rows[0] = new Row("        ");
        rows[1] = new Row("        ");
        rows[2] = new Row("XXXX  XX");
        rows[3] = new Row("XXXXX XX");
        game = new Game(rows);
        
        rows = new Row[3];
        rows[0] = new Row(" X");
        rows[1] = new Row("XX");
        rows[2] = new Row(" X");
        block = new Block(rows);
        
        testCase = new TetrisTestCase();
        testCase.setName("Server 2");
        testCase.setDescription("Server test 2.");
        testCase.setGame(game);
        testCase.setBlock(block);
        testCase.setRows(2);
        cases[i++] = testCase;
        
        // Server 3
        
        rows = new Row[4];
        rows[0] = new Row("        ");
        rows[1] = new Row("XX XXXXX");
        rows[2] = new Row("XX XXXXX");
        rows[3] = new Row("XX XXXXX");
        game = new Game(rows);
        
        rows = new Row[3];
        rows[0] = new Row("X");
        rows[1] = new Row("X");
        rows[2] = new Row("X");
        block = new Block(rows);
        
        testCase = new TetrisTestCase();
        testCase.setName("Server 3");
        testCase.setDescription("Server test 3.");
        testCase.setGame(game);
        testCase.setBlock(block);
        testCase.setRows(3);
        cases[i++] = testCase;
        
        return cases;
    }

}
