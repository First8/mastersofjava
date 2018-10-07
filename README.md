# NLJUG Masters of Java
## Powered by [First8](http://www.first8.nl)
Masters of Java, welke zichzelf respecterende Nederlandse Javaan
kent het officieuze NK programmeren niet? Al enkele jaren strijden de beste Java programmeurs van Nederland om deze prestigieuze titel. Dit jaar zal er naast een individuele competitie ook een bedrijvencompetitie zijn, om nog meer dynamiek te geven.

Voor meer info, zie de officiële pagina van [Masters of Java](http://www.nljug.org/moj/2017/).

## Inhoud
Deze repository bevat alle opgaven van 2017 en de bijbehorende oplossingen. Deze zijn geformatteerd voor de server versie van 2017. 

---

## Indeling
Elke opdracht is gebouwd op basis van Maven. De instructies voor de opdracht, zoals het definiëren van bewerkbare bestanden worden in het _properties_-segment van de POM opgenomen.

#### Puzzel
De puzzel is te vinden in _src/main_. Dit zijn de Java bestanden die zichtbaar zijn tijdens de Masters of Java competitie, zowel bewerkbaar als alleen-lezen.

#### Opdrachtomschrijving
De opgave zelf kan worden gevonden in _src/main/resources/{puzzelNaam}Case.txt. Dit is de omschrijving die aan de deelnemers wordt getoond gedurende de competitie.

#### Tests
Onder _src/test_ zijn de tests terug te vinden. Deze bestaan uit zowel zichtbare tests als de 'verborgen' test(s). De zichtbare tests worden op aanvraag gedurende de competitie uitgevoerd zodat deelnemers weten dat ze in goede richting zoeken (of juist niet). De verborgen tests worden gebruikt als de deelnemers hun antwoord indienen; veelal zijn dit extra tests om te controleren dat deelnemers niet tegen de tests aan programmeren.

#### Oplossing
De oplossing staat in _src/main/resources/_ veelal met bestandsnaam _{puzzelNaam}.solution_. Hierbij moet benadrukt worden dat dit logischerwijs niet de enige oplossing is.

---

## Opgaven
Hieronder een korte uitleg van de opdrachten.

##### Duke Race
Figure out what racer is winning, which depends on the length of the track, the max speed, and acceleration of each racer.

_Author: Koen Aben_,
_Category: Algorithms_

##### IntegerWalk
Calculate the number of routes possible to get to an X,Y coordinate, provided that traversion can only be done on either the X or Y axis (x+1,y) or (x, y+1).

_Author: Koen Aben_,
_Category: Algorithms_

##### Candy Crush
Validate if the Candy Crush game board is in a valid state.

_Author: Koen Aben_,
_Category: Algorithms_

##### WebServer
Roll your own REST webserver!

_Author: Frank de Jong_,
_Category: APIs_

##### SwapPairBits
Several steps must be undertaken, including swapping bits, to get the correct output from a given number (input).

_Author: Koen Aben_,
_Category: Algorithms_
