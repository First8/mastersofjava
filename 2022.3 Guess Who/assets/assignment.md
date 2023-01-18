## Guess who

Who hasn't played Guess who ("Wie is het?" in Dutch)? And, from a computer science
perspective it is quite interesting.

The goal of the the game is to find out who "it" is (it remains a bit unclear what
that person has done and why we need to find him/her/it, but that is irrelevant).
A player is allowed to ask a boolean question (there the computer science begins)
and receives a truthful yes or no. Of course, the best strategy, statistically 
speaking, is to find the _maximumally informative_ question (yet another computer science concept meaning the "best" question). That is, the question that divides the population as close to a 50/50 split as possible. (If you are already loosing, you may opt for another strategy, but that is not what we are aiming for.)

So, your task is to find that perfect question. In the _GameService_, you will 
get a _List_ of _Persons_. Each person has a few properties that you can ask
questions about. Find and return the maximally informative (the best) question, 
and you are done! Note: you should only query about 1 property per question (no composite questions), and a perfect 50/50 split may not be possible. 

Be careful: there are hidden tests, and those may rely on properties of persons 
that may not have been tested. So make sure you cover all fields. If you made a
mistake, you can resubmit this assignment twice, but it will costs you points!
 

