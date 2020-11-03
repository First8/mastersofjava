package moj;

public class Demo {



    public static void main(String[] args) {
        System.out.println("First print our beloved default chessboard:");

        new ChessBoards().createNormalBoard().print();

        System.out.println("Let's show the Hexagonal chessboard (first fix unit tests)!");

        new ChessBoards().createHexagonalBoard().print();

        System.out.println("For more chess variants, please visit wikipedia");

        System.out.println("3 funny variants:");
        System.out.println("- Republican Chess: no king, no queen." );
        System.out.println("- Atheist Chess: no bishhop." );
        System.out.println("- Marxist Chess: just pawns." );
    }
}
