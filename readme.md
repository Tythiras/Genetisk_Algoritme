Jeg har implementeret min genetisk algoritme ved hjælp af classes. \
Det er selvfølgelig en "overhead" der kan fjernes for et mere effektivt program, men for lettere læsbarhed har jeg lavet det sådan her. \
Jeg har følgende:  \
LoadFile - Står for at læse backpack indeholdet fra data.csv filen \
Main - Står for alt loggikken og håndtering af rendering samt generationerne og nye generationer \
Generation - Generations funktioner samt indeholder alle solutions \
Solution - Indeholder en bag som er en solution eller løsningsforslag. Løsningen er repræsenteret som booleans i bagRepresentation
Item - Simpel represæntation af en item \
\
Min udvælgelse af parents kan helt sikkert optimeres, jeg har i det her tilfælde brugt en metode som i princippet repræsentere "spind hjulet" \
Hvor genstande med høj fitness så har højere sandsynlighed for at blive valgt.
\
For at forbedre den kan det overvejes at tilføje en elite gruppe hvor ex. de bedste 5 løsninger direkte bliver taget med videre i næsten generation. \
\
Derudover kan selve parringsprocessen måske også optimeres, ved nuværende implementation bruges cross over hvor de 2 børn får 50% 50% fra hver forældre. Her kunne der overvejes at reducere mutationssandsynligheden men tilgængel krydse generne med mere tilfældighed. (krydsningspunktet ikke altid ligger i midten).\
\
Jeg havde bedst erfaringer med relative lave populationsstørrelser med end relativ høj mutationsrate. \
Eksempelvis 200 i populationen og 15% chance for mutation. Men det kan stadig opleves at det løber op i rigtig mange generationer før den lander på det korrekte resultat