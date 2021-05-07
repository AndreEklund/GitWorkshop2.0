# GitWorkshop2.0
Grupp 22

Länk till arbetet: 
https://github.com/AndreEklund/Mazegen


Instruktioner för att köra vårat program.

1. Ladda ner hela projektet som en zip-fil och öppna det med inteliJ.

2. Lägg till javaFX till projektet enligt följande instruktioner: 
https://openjfx.io/openjfx-docs/  (Det finns en flik som visar hur man gör med intelliJ)

3. När ni är klara med javaFX-installationen måste ni åter igen gå in i VM-options och lägga till ytterligare en
textrad. Byt ut det sista stycket "--add-modules javafx.controls,javafx.fxml" mot "--add-modules javafx.controls,javafx.fxml,javafx.media".
Detta är för att få ljudet i programmet att fungera. 

4. Kör sedan programmet från Main-klassen. 

Instruktioner i spelet.

Finns två spellägen, Randomly Generated Maze och Mapcreator.

Randomly Generated Maze: 

1. För att aktivera spelrundan klickar man på rutan med stegen i ett hål, då aktiveras all kollision.

2. För att kunna gå till målet, som representeras av en stege, måste man plocka upp alla diamanter. 

3. Om man vidrör en vägg avaktiveras all kollision och man måste åter igen klicka på den första stegen. 

4. Efter att man går i mål genereras en ny maze med slumpvis vald grafik och layout. 

Mapcreator:

1. Rita din egen labyrint genom att klicka på rutorna.

2. Klicka på en ruta två gånger för att placera ett mål.

Spelläget är ofärdigt och kommer eventuellt tas bort...
