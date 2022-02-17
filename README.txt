


Objectius:

Crear un joc que es conecti a un servidor.
El servidor serà TCP i cada client serà un jugador.
Cada jugador envia la seva posició i rep la posició dels altres jugadors
Els jugadors es mouen per la pantalla amunt, avall, dreta, esquerre i poden atacar. Ataquen a una posicio just al davant del personatge
Si un jugador ocupa la posicio atacada perd una vida.
Cada jugador té tres vides.
Si un jugador mor segueix veient en multicast udp la partida.

El servidor accepta fins a 4 jugadors. Els restants enntren a una llista d'espera i es conecten per multicast per veure la partida