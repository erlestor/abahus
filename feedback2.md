# Tilbakemelding på innlevering 2

## Bygging

- bygget greit
- hoved-pom-fil har ikke satt opp checkstyle, spotbugs eller jacoco, og inneholder javafx-avhengigheter som bare trengs i ui-modulen

## Dokumentasjon

## Design

## Kodegjennomgang

- litt merkelig oppdeling i core- og jsonworker-moduler, hvorfor er ikke House og User i core?

### JsonWorker

- tror ikke dette er god måte å lage filsti på, bruk en forutsigbar og portabel absolutt sti
- bruk grensesnitt-typer som deklarert type der det går an (f.eks. readFileAsArray og getAllUsers)

### Main

- dere låser koden veldig til filbasert lagring, blir det ikke vanskelig å gå over til å bruke en REST-tjeneste?

### ui

- burde fokusert mer på "housing" og mindre på brukerhåndtering

### AppTest

- ingen test av MainController
