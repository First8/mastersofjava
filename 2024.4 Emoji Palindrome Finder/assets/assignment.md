## Emoji Palindrome Finder

An emoji is worth more than a thousand words, that's why everyone uses emojis to express themselves nowadays.
Luckily, there are emojis for EVERYONE, so that we can all express ourselves accordingly, regardless of color. 
And, since Java 21 made some improvements to Emoji handling (like adding `Character::isEmojiModifierBase`), 
we couldn't resist making an assignment about it.  (Be careful to read the tips below before diving into the assignment!)

## The Assignment
Write a method to find the longest palindrome in a given string. A palindrome is a string of glyphs (or _graphemes_), that is symmetrical, the same from left to right as right to left.
The string can contain emojis, text, or both text and emojis:

| String input                                              | String output  |
|:----------------------------------------------------------|:---------------|
| The racecar has reached level thousand                    | racecar        |
| 🧀🌮 👺🏯🎎🏯👺 🍕🍔                                      | 👺🏯🎎🏯👺    |

Characters (or as Unicode calls them: graphemes) are identified by code points, basically a number that refers to a glyph that can be drawn. Some of those code points are emojis.
Some emojis can be modified with another codepoint (a modifier code point). If that is possible, that code is a 'modifier base' which can thus be combined with a 'modifier'. These modifiers are unique for each base.

| Emoji  | Code   | Modifier |
|:-------|:-------|:---------|
| 👩     | 128105 | -        |
| 👩🏿     | 128105 | 127999   |
| 👩🏽     | 128105 | 127997   |


We consider everyone equal regardless of skin color. Therefore, you can consider emojis equal when they share a common code. So, the following strings are
both valid palindromes:
- "👩🏽👩🏿👩🏽"
- "👩👩🏿👩🏽"

# Tips
* You may assume all palindrome solutions are full, single words, if that helps. 
* You can use `Character.isEmoji` to check if a character is an emoji.
* You can use `Character.isEmojiModifierBase` to check if a character is an emoji modifier base.
* You can use `String.split` to split on a Unicode extended grapheme cluster boundary (using `\b{g}`) to get a List of Strings that represent the individual graphemes (emojis and characters) that you would expect.

**Also note**: we wanted men, women and non-binaries to be considered equal as well, but unicode has made that a lot harder than it should be. 
So, apologies for that 😔. (Maybe in Masters of Java 2025...?)







