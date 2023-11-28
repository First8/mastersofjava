## The Browser Accept-Language Header

Internationalization is one of those topics that everyone has to deal with and noone really wants to do it.
Because, let's face it, the Americans were right to assume that we should only have to deal with the English on the internet. Unfortunately, in practice, we have to deal with all kinds of languages. This concept of a language and all its facets is called a `Locale`. There are many forms of it but typically it is an language code (`en` for English), or a language code in combination with a country code. For example, `nl-nl` is the Dutch (nl) spoken in the Netherlands (nl), and `nl-be` is Flemish (nl) as spoken in Belgium (be).


But since budgets typically are limited, only a few languages
will be supported for a typical website. For that, the Accept-Language header was invented, explaining what languages
the user can read, with a priority (weight, quantity) given.

Your assignment is: for a given Accept-Language and a set of supported languages, find the best match.

We're not going to ask you to fully implement IETF BCP 47 (but you are free to do so!), so here's the simplified version:

The Accept-Language header looks something like this:
* `en` : I only understand English
* `en,fr` : I understand English and French
* `en;q=1.0,fr;q=0.5` : I prefer English with a weight of 1, but if need be I will accept French with a weight of 0.5
* `en,fr;q=0.5` : I prefer English with a weight of 1 (as that is the default), but if need be I will accept French with a weight of 0.5 
* `en-us,en-gb;q=0.5` : I prefer American English but will accept British English if need be 


If there are multiple languages that match, the one with the highest weight, and then in order as given in the header, should be returned. Note that if `en` is supported, that will match an accept header for `en-ca` or `en-us`. In reverse, if only `en-us` is supported, the generic `en` accept header does _not_ match. 

For solving the simple language only variant, you'll get 100 points. If you also support countries, another 150 points. And for the full solution, again 150 points. In total 400 points, and for every second left, 1 additional point.
