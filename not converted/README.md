Assignments ##
===================

Assignments for Masters of Java

# Ideeen

## International business:
Persoon x werkt _overal_.

Ze/hij heeft een werkschema van 7 dagen verdeeld over meerdere landen over de wereld (geen volle werkdagen, meerdere plaatsen op 1 dag!).
Inkomen is overal in lokale currency, opgeslagen als uren bij DAGtarief bij lokale tijd. (kan ingewikkeld gemaakt worden door maandinkomen te vertellen, met effectieve werkdagen bijvoorbeeld)
(btw etc. zien we over het hoofd)

Wat heeft zij/hij verdient op dag 3?

- problemen zitten dus in de geld api's en dagen icm. tijdzones.
- eventueel problemen met afronding bedragen (bankers rounding uiteraard)

## Treinen:
Een vorm van the travelling sales man.

Treinen rijden over baanvakken tussen stations, 1 spoor heen, 1 spoor terug.
Stations hebben een beperkt aantal paralelle banen (normale baanvakken zijn niet parallel).

Treinen moeten 'rusten' (koffiepauzes, wisselen personeel en schoonmaak).

5 startstations zijde 1
5 eindstations zijde 2

1 station in het midden waar iedereen langs moet.

## Sleepnet cluedo:
De NSA wil weten wie er verantwoordelijk is voor bericht x.
Omdat het ondoenlijk voor een mens om is alle berichten door te nemen, moet er automatisch herkend worden.
De verdachten gebruiken sowieso codewoorden, scannen op specifieke woorden is daarom niet mogelijk.

Aan de hand van 'metadata' kan een typering gegeven worden aan een bericht.
Typische berichten zijn waarschijnlijk afkomstig van ... (profielen bedenken)

De profielen definieren scores op op bepaalde eigenschappen.

Denk aan:
- aanhef
- gebruik interpunctie
- wel/geen hoofdletters
- lengte bericht
- aanwezigheid vreemde tekens
- witregels

De test geeft een aantal berichten die gescoord moeten worden.
Er moeten zeker een aantal tests zijn die niet getoond worden.

- suggestie: wellicht de profielen in code al uitschrijven?
- wellicht de text als byte[] aanleveren, dan is de encoding al metadata (wel simpel maken om te detecteren)