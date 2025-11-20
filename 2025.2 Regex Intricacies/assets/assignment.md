## Regex intricacies

Ahh, regular expressions. We love 'em, we hate 'em, we can't get enough of them. 
I am sure we are all familiar with the official RFC 5322 Email Regex:
```
(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])
```
Did you know you can also use this trick to check if a number is prime?
```java
public static boolean prime(int n) {
  return !new String(new char[n]).matches(".?|(..+?)\\1+");
}
```
(We never said it was smart, just that you _could_)  

## The assignment

How well do _you_ know Regex?

This assignment is quite simple, all you have to do is create a regular expression based on a String. The created regex
will then be used to find and replace that specific String. 

A few more things:
- We do not care if it's part of a word, we want it to be separated by boundaries
- You only have to create the regex, usage of the regex and finding/replacing words is taken care of in the tests
- The beginning is simple, don't overthink it!

The setup of using the regex in a `java.util.regex.Pattern.compile` will be done in the test class `BaseTest.java`, so you only have to 
care about creating the perfect regex for this assignment. 

## Fun fact 

StackExchange took its own site down with a regex in 2016 (1). 
They had a regex (`^[\s\u200c]+|[\s\u200c]+$`) that trimmed unicode spaces from the start and end of a line.
At one point, a malformed post arrived at the homepage that contained 20.000 space characters at the start of the post.
This meant that every time the frontpage was loaded, the regex would run. It would fail at the end of the 20.000 characters, and then check again starting at character 1 - and thus checking 19.999 characters. Then it would run from character 2, etc etc.
This took down their website for half an hour before they fixed it - by replacing it with a substring function.


1: https://stackstatus.tumblr.com/post/147710624694/outage-postmortem-july-20-2016