# Counting blobs.

We are going to count blobs in an image. You are given an black-and-white image, 
represented by 1's (black) and 0's (white), as a nice simple, int[][] array.
Your task is to count how many distinct black area's there are.

So, this image has 1 blob:
```
0000
0010
0000
```

While this one has 2 blobs:
```
00000
01110
00010
01010
00010
01110
00000
```
Some assumptions that you may make:
* the border all around are always 0's
* the blobs are fully filled in, there are no blobs or holes within blobs
* the blobs never touch at an edge, the following does not happen:
```
0000
0100
0010
0000
```

That's it, go count!

But, maybe some hints on how to approach this. You can implement this with
recursion and flood-fill kinda things. But, you can also take the easy way using
the local-global behavior of Euler characteristics. With a bit of inspiration of 
Lefschetz fixed point theorem and Guess-Bonnet theorems you can easily build it. 

Since you probably don't have time to read up on those, let me summarize:
* set a counter to 0
* scan the entire image, once, looking at each 2x2 pixel window;
* increase the counter if the pixel window looks like:
```
10
00
```
* decrease the counter if the pixel window looks like:
```
11
10
```
That is it.





