## Pokemon Calculator 

Have you ever worked on a project that is aimed to replace another piece of software, bug-by-bug? No? Well, we have a treat for you! As you know, using off-the-shelf libraries for stuff is always a good idea. But reinventing stuff like parsing numbers sounds like too much fun to leave to other people. 

In November 2021, Pokemon Brilliant Diamond and Shining Pearl were introduced. In those games, there is an in-built calculator that you can use for 
figuring out something around stats and effort-value training stuff. (A colleague tried to explain it to me how it works, but I couldn't understand. Anyway, it is not relevant for this assignment...)

<img src="/public/asset/PokemonCalculatorBug/assets/pokemon-calculator.png" style="height: 300px;"/>

This calculator has some weird bugs. The bugs only occurs in specific countries (locales) like e.g. Germany,
in other countries it works fine. Can you find out what the problem is and recreate the bug yourself? Some examples of the bug are: 

In the US Locale it works fine:
* 10 / 4 = 2.5
* 10 / 8 = 1.25


But in Germany?
* 10 / 4 = 002
* 10 / 8 = 0001


Notice a couple of things: in the US Locale it works fine. In the German locale, it somehow only shows the digits before the decimal point and prefixes it with some zeroes? Maybe this has something to do with whether the thousands separator and the decimal separator are reversed? (I.e. a thousand can be 1,000.00 or 1.000,00 depending on your locale).

You get 200 points if you can replicate this bug.

But there is more! When you enter 7 / 3 you get 2.3333333333333333 in US. But in Germany the calculator will not know
what to return, so it returns ??????????. Note that the calculator can only return a maximum 10 characters, since the display isn't any bigger. Again, 200 points to score for this.



