## Fix the radio

### Omschrijving voor de Gamemaster

In deze opdracht implementeren de deelnemers een algoritme voor het decoderen van radio signalen. 

De ontvanger in je radio is kapot en je wil deze fixen. Je maakt hierom een implementatie van de bi-phase decoder. 

Uitleg van dit algoritme staat in de assignment.txt en in de solution file.

## Stap 1: Bouw de bi-phase decoder van radio signalen

## Stap 2: Verwerk de inputstream en bewaar de goede messages.

#### REFERENCES
* GNU radio. A free & open-source software development toolkit that provides signal processing blocks 
  - https://www.gnuradio.org/
* dit algoritme staat nauwelijks op het web, het is echt voor insiders/academici 
  - https://en.wikipedia.org/wiki/Manchester_code
* in 2015 werd software defined radio gepresenteerd op de Java conferentie Devoxx
  - https://www.slideshare.net/BertJanSchrijver/devoxx-uk-2015-decoding-the-air-around-you-with-java-and-7-hardware
* Technisch ontwerp tbv software defined radio (in het Nederlands). 
  - https://lib.ugent.be/fulltxt/RUG01/001/887/015/RUG01-001887015_2012_0001_AC.pdf

#### UITBREIDINGEN
* meer radio grappen
* moderne java io features. 
* meer domein kennis van het radio domein

### OPMERKINGEN
- deze opdracht heeft complexe IO streams, deze worden expliciet gesloten in RadioSignalUtils om memory leaks te voorkomen. 
