# The Debugging of the Code:
*“One fix to rule them all”*

Long ago in the lands of **Middle-Code**, all code lived in harmony. But a darkness showed up... strange errors, ill-formatted arrays, broken sequences. The Code was fracturing.

You'll stumble upon three, by the dark powers manifested, bugs. The bugs are forged to invoke confusion. These aren't the most difficult bugs out there, 

but they’re tricky! Each one is the kind of bug that can keep you stuck for a long time simply because they’re subtle.

And so, a Fellowship was formed — tasked with fixing the bugs and destroying the darkness once and for all

---

## Chapter I: The Ruins of DateTimeFormat
*“Time is a strange thing… especially when it throws exceptions.”*

A wizard sought to summon the current time. But instead, he got this stack trace:

> `java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: YearOfEra`

### Your Task
Correct the now failing code -- Remove the cursed `YearOfEra` issue, to correctly display the time.

---

## Chapter II: Forbidden index
*“One does not simply walk into the Forbidden Index.”*

When figuring out the price of Lembas bread, our trusty Elves encountered a strange marking:

> `java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0`

This seems some weird effect caused by wrong usage of `String.split`.

### Your Task
Implement `StringSplit` the correct way and deliver the price as expected. 

---

## Chapter III: The passing of the numbers
*“And numbers that should've been included, are lost”*

The Fibonacci sequence, a series of numbers where each number is the sum of the two preceding ones (So 0-1-1-2-3-5-8 etc.), written by the Elves — is broken.

When we want a sequence up to 10:
> `[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]`
> 
It shows:
> `[0, 1, 1, 2, 3, 5, 8]`  

There are some language rules:

If a negative input is given, it should output an empty array, since the elves meant it to not include non-negative integers.

If only a 0 is given as an input, it should also display an empty array instead of [0]

### Your Task
Restore the full sequence for a given length. Remove inputs like `0` or negative numbers.

---