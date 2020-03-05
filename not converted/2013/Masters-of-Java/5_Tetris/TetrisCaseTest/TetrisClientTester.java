public class TetrisClientTester extends TetrisTester {

    @Override
    protected TetrisTestCase[] getTestCases() {
        
        TetrisTestCase[] cases = new TetrisTestCase[5];
        
        TetrisTestCase testCase;
        Game game;
        Block block;
        Row[] rows;
        int i = 0;

        // Example
        
        rows = new Row[4];
        rows[0] = new Row("        ");
        rows[1] = new Row("X  X X  ");
        rows[2] = new Row("XX XXXXX");
        rows[3] = new Row("XX XXX X");
        game = new Game(rows);
        
        rows = new Row[3];
        rows[0] = new Row("XX");
        rows[1] = new Row("X ");
        rows[2] = new Row("X ");
        block = new Block(rows);
        
        testCase = new TetrisTestCase();
        testCase.setName("Example");
        testCase.setDescription("The same game as shown in the example.");
        testCase.setGame(game);
        testCase.setBlock(block);
        testCase.setRows(1);
        cases[i++] = testCase;
        
        // Simple
        
        rows = new Row[4];
        rows[0] = new Row("        ");
        rows[1] = new Row("        ");
        rows[2] = new Row("        ");
        rows[3] = new Row("XX XXXXX");
        game = new Game(rows);
        
        rows = new Row[1];
        rows[0] = new Row("X");
        block = new Block(rows);
        
        testCase = new TetrisTestCase();
        testCase.setName("Simple");
        testCase.setDescription("One row with one open space and a small block that fits.");
        testCase.setGame(game);
        testCase.setBlock(block);
        testCase.setRows(1);
        cases[i++] = testCase;
        
        // Two rows
        
        rows = new Row[4];
        rows[0] = new Row("X       ");
        rows[1] = new Row("XX  X   ");
        rows[2] = new Row("XXXXXX X");
        rows[3] = new Row("XXXXXX X");
        game = new Game(rows);
        
        rows = new Row[2];
        rows[0] = new Row("X");
        rows[1] = new Row("X");
        block = new Block(rows);
        
        testCase = new TetrisTestCase();
        testCase.setName("Two rows");
        testCase.setDescription("There could be two rows to get.");
        testCase.setGame(game);
        testCase.setBlock(block);
        testCase.setRows(2);
        cases[i++] = testCase;
        
        // Another two rows
        
        rows = new Row[4];
        rows[0] = new Row("        ");
        rows[1] = new Row("XXXXXX X");
        rows[2] = new Row("XXXXXX X");
        rows[3] = new Row("XX XXXXX");
        game = new Game(rows);
        
        rows = new Row[2];
        rows[0] = new Row("X");
        rows[1] = new Row("X");
        block = new Block(rows);
        
        testCase = new TetrisTestCase();
        testCase.setName("Another two rows");
        testCase.setDescription("There could be two rows to get.");
        testCase.setGame(game);
        testCase.setBlock(block);
        testCase.setRows(2);
        cases[i++] = testCase;
        
        // Block with angle
        
        rows = new Row[4];
        rows[0] = new Row("        ");
        rows[1] = new Row("        ");
        rows[2] = new Row("XXXXX  X");
        rows[3] = new Row("XXXXXX X");
        game = new Game(rows);
        
        rows = new Row[3];
        rows[0] = new Row("X ");
        rows[1] = new Row("XX");
        rows[2] = new Row(" X");
        block = new Block(rows);
        
        testCase = new TetrisTestCase();
        testCase.setName("Block with angle");
        testCase.setDescription("A block that is not rectangular.");
        testCase.setGame(game);
        testCase.setBlock(block);
        testCase.setRows(2);
        cases[i++] = testCase;
        
        return cases;
    }

}
