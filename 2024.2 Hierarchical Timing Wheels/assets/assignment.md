## Hierarchical Timing Wheels

Most of you with a few years of developer experience know a thing or two about time. 
Dealing with dates and times isn't easy, and designing an API for it is even harder 
(a fact unfortunately proven by IBM by designing `java.util.Calendar`). Luckily, these days we have `java.time` which works a lot better.
But, it doesn't solve all problems. 

We are going to look at a specific problem: managing a large number of expiry timers. 
And, since we are at Masters of Java, we are going to do that not only using Earth's clock, but allow Mars time as well.

### The problem

Imagine that you have a large number of requests, or some other events, that need to be expired or processed after a specific time. 
You could try a huge loop that constantly checks all timers, but that's not very efficient. If you want some precision with your timers,
that loop needs to execute in the smallest unit of time you want to support.

### The solution

The solution is a Hierarchical Timing Wheel. 
This is a data structure that allows you to manage a large number of timers by connecting 'timing wheels' together, sort of as a mechanical clock.
If you consider a normal clock, you would have a seconds wheel, a minutes wheel, an hours wheel and a days wheel. You can find the Timer class in the `DhmsTimer` (for days, hours, minutes and seconds).

By calling `advance()` on the seconds wheel, it rotates the wheel 1 step out of 60. When the seconds wheel completes a full rotation, it rotates the minutes wheel 1 step out of 60, etc.
Each time, the timers that expire on a larger unit wheel, are moved to the next wheel. This way, you only need to check the smallest unit wheel for expired timers.

Using these wheels, you can manage a large number of timers with a very small overhead. 
And you can use different units for each wheel. E.g. Mars has a day of 1480 minutes (instead of Earth's 1440), 
and has 670 sols (mars days) in a year, so scheduling a trigger in 2 days and 4 minutes means something different 
on Mars than on Earth...

### Your assignment

Your task is to implement `HierarchicalTimingWheel.advance()`. 
It should advance its inner `TimingWheel` and check if it did a full rotation. 
If so, it should then advance the next bigger wheel and retrieve its expired timers and add them to its own list of timers.
Finally, it should return the expired timers of this wheel.


