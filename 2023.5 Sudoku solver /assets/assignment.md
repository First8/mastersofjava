## Sudoku solver

We haven't done Sudoko stuff during the Masters of Java since 2015! So, this puzzle
classic is long overdue for an assignment again. No, we're not going to validate a sudoku, that is soooo 2015. We are going to solve them!

As you probably know, a Sudoku is a 9x9 Grid, divided in 3x3 Boxes, each Box containing 3x3 Cells.
A Region is either a 9-cell Row, Column, or Box. Within a region, the numbers 1-9 must appear
exactly once. Below you can find three examples of a region:

<img src="/public/asset/sudoku solver/assets/sudoko-region.jpg" alt="sudoku region example" style="height: 300px; width:300px;"/>

For the experts out there, a solving strategy involves things like singles, pairs, triples and quads.
Each cell (that hasn't been given) starts out with all possibilities, represented as a List of `{1,2,3,4,5,6,7,8,9}`.
A Region is thus a List of those Lists. 

If you can simplify a Region, you often can solve the Sudoko. Simplifying means that you want to reduce 
the possibilities per Cell.

Given a region (e.g. a row, column or box):

<pre>
List&lt;List&lt;Integer&gt;&gt; region =

{
{1}, {2,3}, {2,3}, {4,5}, {4,5,6}, {6,7}, {6,7,8}, {6,7,8}, {1,2,3,4,5,6,7,8,9}
}
</pre>

You can see that the first Cell only has one option (it has to be a `1`). That is a single!
And that also means that you can reduce all other cells (e.g., the last cell) in that region to exclude the `1`.

Something similar happens at cells 2 and 3: these two cells have only two options between them. That means that the '2' and '3' must be in cells 2 and 3, and thus not in any other cell. That's a pair!

Now look at cells 6,7 and 8. These have 3 options amongst three cells. Same thing applies, that's a triple. (And you can figure out quads etc from here...)

So, the given region can be reduced to:

```
{
{1}, {2,3}, {2,3}, {4,5}, {4,5}, {6,7}, {6,7,8}, {6,7,8}, {9}
}
```

Can you implement this solving strategy? You are already given a function that generates all groups (potential singles, pairs, triples...). 
You'll already get 200 points if you at least can solve the singles (groups of size 1). Then, another 100 points for solving pairs and another 100 points for the generic case. 

