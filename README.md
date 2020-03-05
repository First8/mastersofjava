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

