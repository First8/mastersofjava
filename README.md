# NLJUG Masters of Java
## Powered by [First8](http://www.first8.nl)
Masters of Java, welke zichzelf respecterende Nederlandse Javaan
kent het officieuze NK programmeren niet? Al enkele jaren strijden de beste Java programmeurs van Nederland om deze prestigieuze titel. Dit jaar zal er naast een individuele competitie ook een bedrijvencompetitie zijn, om nog meer dynamiek te geven.

Voor meer info, zie de officiële pagina van [Masters of Java](http://www.nljug.org/moj/2017/).

## Inhoud

Deze repository bevat alle opgaven van de afgelopen jaren. Deze zijn geformatteerd voor de server versie vanaf 2017. 

## Indeling

Elke opdracht is ontwikkeld op basis van Maven maar dat is niet perse nodig om de opdracht te kunnen runnen. De opdracht wordt 
omschreven met een assignment.yaml file die in de root van de assignment moet staan. Per conventie is er een _assets_ directory 
waarin de omschrijving van de opdracht en de oplossing staan. In de assignment.yaml file moeten alle java en resource files benoemd 
worden die nodig zijn voor het compilen en runnen van de assignment. Verder kunnen additionele spelregels opgegeven worden.


    assignment-files:
      assignment: assets/assignment.txt
      sources:
        base: src/main/java
        editable:
        - EditableJavaFile.java
        readonly:
        - ReadOnlyJavaFile.java
      test-sources:
        base: src/test/java
        tests:
        - TestCase1.java
        - TestCase2.java
        hidden-test:
        - TestCaseHidden.java
      test-resources:
        base: src/test/resources
        files:
        - SomeResourceRequiredToTest.bin  
      solution:
      - assets/TheSolution.java


#### Opdracht
Onder _sources_ worden de Java bestanden die zichtbaar zijn tijdens de Masters of Java competitie gelist, zowel bewerkbaar als 
alleen-lezen.

#### Opdrachtomschrijving
De opgave zelf kan worden gevonden in _assignment_. Dit is de omschrijving die aan de deelnemers wordt getoond gedurende de 
competitie.

#### Tests

Onder _test-sources_ zijn de tests terug te vinden. Deze bestaan uit zowel zichtbare tests (_tests_) als de 'verborgen' test(s) 
(_hidden-test_). De zichtbare tests worden op aanvraag gedurende de competitie uitgevoerd zodat deelnemers weten dat ze in goede 
richting zoeken (of juist niet). Ook de inhoud van de testcase wordt getoond. De verborgen tests worden gebruikt als de deelnemers 
hun antwoord indienen; veelal zijn dit extra tests om te controleren dat deelnemers niet tegen de tests aan programmeren.

#### Oplossing

De oplossing wordt benoemd in _solution_. Deze kan gebruikt worden bij de evaluatie nadat een assignment is uitgevoerd. Hierbij moet 
benadrukt worden dat dit logischerwijs niet de enige oplossing is.

---

## Opgaven
Hieronder een korte uitleg van de opdrachten.

##### example VirtualCPU
Implement a CPU :)
Used for debugging so out of the box it spams a lot. You may want to clean up VirtualCPU.java before running it in a contest.

##### example WorkLoadBalancer
Distribute workload orders.
Used for debugging so out of the box it is already implemented. You may want to clean up WorkloadBalancerImpl.java before running it in a contest.

##### 2017 Duke Race
_not yet converted_
Figure out what racer is winning, which depends on the length of the track, the max speed, and acceleration of each racer.

_Author: Koen Aben_,
_Category: Algorithms_

##### 2017 IntegerWalk
_not yet converted_
Calculate the number of routes possible to get to an X,Y coordinate, provided that traversion can only be done on either the X or Y axis (x+1,y) or (x, y+1).


_Author: Koen Aben_,
_Category: Algorithms_

##### 2017 Candy Crush
_not yet converted_
Validate if the Candy Crush game board is in a valid state.

_Author: Koen Aben_,
_Category: Algorithms_

##### 2017 WebServer
_not yet converted_
Roll your own REST webserver!

_Author: Frank de Jong_,
_Category: APIs_

##### 2017 SwapPairBits
_not yet converted_
Several steps must be undertaken, including swapping bits, to get the correct output from a given number (input).

_Author: Koen Aben_,
_Category: Algorithms_

