
# Let's talk about Chess!

### Introduction

Did you know there are numerous Chess variants? According to [wikipedia](https://en.wikipedia.org/wiki/List_of_chess_variants), many thousands of chess variants exist; the 2007 catalogue _The Encyclopedia of Chess Variants_ estimates 
that there are well over 2.000, with the author noting that many more known variants were considered too 
trivial for inclusion. 

The best known Chess variant is called _Hexagonal Chess_, moving the 
same game to a more complex board. A hexagonal chessboard is a symmetric 6-side board with on each side 6 cells and
each cell having 6 borders. This board allows pieces to move from one field to a bordering field in 6 directions.
A hexagon figure is symmetric with 6 lines and 6 dots (like this character &#11041;).

### Assignment
Implement an Hexagonal Chess board (see image).
- Hexagonal involves all well-known chess pieces. A hexagonal board has 91 cells colored in black, white and gray.
- The one cell in the middle is always gray.
- The hexagonal board has 11 ranks, known by the 11 letters a - l (the letter j is not used) and 11 numbered rows.
- Rank 1 to 6 has 11 cells, rank 7 has 9 cells, rank 8 has 7 cells etc. Rank 11 has exactly 1 cell: f11.

### Hints
- The code already contains an implementation for a normal chessboard in class ChessBoards.
- Add the hexagonal chessboard according to the requirements above (hint: see also unit tests).
- Implement the algorithm for composing the fields on hexagonal chess board (hint: see similarities with the normal chess board)

<img src='/public/assignment_image/moj-HexagonalChess/public_Hexagonal_chess.svg'>
