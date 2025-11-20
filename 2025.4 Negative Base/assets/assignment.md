## Negative Base

Oke, yeah, Java is cool and all. But you know what? It's kind of limiting sometimes too. 
For example, you can convert Strings to Integers, using base `10`, or any other positive base.
But you are not allowed to use negative numbers as a base! Even worse, if you try, Java just ignores you and uses base `10` 🤯.
What kind of silly limitation is that! Let's fix that. 

### Think positive
As you probably know, decimals work like this:

```
  1234 
= (1 * 10^3) + (2 * 10^2) + (3 * 10^1) + (4 * 10^0) 
= 1000 + 200 + 30 + 4 
= 1234
```
Where the base is `10`, and the exponent depends the position of the digit in the string. So since the `3` is at position `1` (counting from the back, starting at 0), it contributes the value `3 * 10^1` to the total sum. 

In a Base `2` system, using `1100` as an example. (we'll use `(2)` to denote base `2`, and `(10)` to denote base `10`):

```
   (2) 1100  
= (10) (1 * 2^3) + (1 * 2^2) + (0 * 2^1) + (0 * 2^0)  
= (10) 12 
```

And in Base `36` (that is using the numbers 0-9 and the letters a-z), with `k` being the 20'th character:
```
  (36) 1k 
= (10) (1 * 36^1) + (20 * 36^0)  
= (10) 56 
```

### Now, let's get negative
Base `1` or `0` don't make much sense (or are very boring). But how about base `-2`? Or `-10`? The thing about those negative bases is that it is possible to display all numbers, both positive and negative, with it.
And you never have to use the '-' sign!

Now, when we interpret `1100` with base `-2`, something funny happens: we end up with `-4`!:
```
  (-2) 1100  
= (10) (1 * (-2)^3) + (1 * (-2)^2) + (0 * (-2)^1) + (0 * (-2)^0) 
= (10) (1 * -8) + (1 * 4) + (0 * -2) + (0 * 1) 
= (10) -4 
```

Note that the values of the respective indexes oscillate between positive and negative. In the case of base `-2`:
`1, -2 , 4, -8, 16, -32, 64` and so on. Combining positive and negative values can create any positive or negative number in existence. Pretty cool!

For instance, if we do want to represent the decimal number `12` in base `-2`, we'd do it like this:

`11100 (-2) = 16 - 8 + 4 - 0 + 0 (10) = 12 (10)`.

The purpose of this assignment is to write the algorithm to convert a base `10` to a negative base representation
of that number.

Obviously you are free to come up with the algorithm yourself. But, since this is a programming contest and not a
math contest, we'll provide an algorithm that you can use.

Here it is:
* If you divide the decimal number by the base, you are left with a result and a remainder. The representation of that remainder will be the last character of your output.
  * for example, if the remainder is `13`, which can happen with bases below `-14`, the representation will be the 13th character in the character sequence, so a `d`.
  * keep the result since you need it again!
* However, with negative bases the remainder can also be negative. In that case you convert to a positive number by subtracting the base. 
  * For instance: If the remainder is `-1` in a base `-10`,  `-1 + 10 = 9`, so you put a `9` there. 
  * However, that's not all. Since you just converted a negative value to a positive value, you have to compensate for that
in the next character. You do that by adding `1` to the result of the division (the one that left a remainder of `-1`).
* For the next character, you need to divide the result of the first division again by the base. Again, you
are left with a remainder and a result. The remainder becomes the next character, and the result will again
be divided by the base.
* This repeats until the result of the division is `0`, in which case you just add the remainder at the end of the
output, and you are done! Congratulations!

### Example
An example? Sure. Let's do the decimal `36` in base `-36`. Should be fun, right?

`36 / -36 = -1`. Remainder is `0`, so our output is now `0`.
`-1 / - 36 = 0`. Remainder is `-1`. So the character is `-1 + 36 = 35 = 'z'` is added to the output, which is now: `z0`.
The result of the previous division was `0`, but since the remainder was negative, we added 1 to it, so that's what we use
now:
`1 / -36 = 0`. Remainder is `1`, so we add a `1` to the output: `1z0`.
The result of the previous division is `0`, so we're done! We can return the output.
```
number | base | division        | remainder     | output
    36 | -36  | 36 / -36 = -1   | 36 % -36 =  0 | "0"
    -1 | -36  | -1 / -36 = 0    | -1 % -36 = -1 | 
       |      |                 | -1 - -36 = 35 | "z0"
   0+1 | -36  |  1 / -36 = 0    |  1 % -36 =  1 | "1z0"
     0 |      |                 |               |   
```

One more, `-12345` in base `-13`:
```
number | base | division           | remainder         | output
-12345 | -13  | -12345 / -13 = 949 | -12345 % -13 = -8 | 
       |      |                    |     -8 - -13 =  5 | "5"
 949+1 | -13  |    950 / -13 = -73 |    950 % -13 =  1 | "15" 
   -73 | -13  |    -73 / -13 =  5  |    -73 % -13 = -8 | 
       |      |                    |     -8 - -13 =  5 | "515"
   5+1 | -13  |      6 / -13 =  0  |      6 % -13 = 6  | "6151"
     0 |      |                    |                   | 
```  

### Note
For the purpose of this assignment, we won't go below `-36`, so you can use just the numbers and lowercase letters.
(0123456789abcdefghijklmnopqrstuvwxyz).

