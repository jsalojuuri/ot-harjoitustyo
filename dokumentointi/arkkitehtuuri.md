# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattaa perinteistä kolmitasoista kerrosarkkitehtuuria oheisella pakkausrakenteella:

![Koodin pakkausrakenne](./assets/tictactoe_packages.png)

Pakkaus *tictactoe.main* sisältää JavaFX:llä toteutetun käyttöliittymän, *tictactoe.service* sovelluslogiikan ja -palvelun apuluokkineen ja *tictactoe.dao* pysyväistallennuksesta vastaavan koodin.

Koodin kaikkien luokkien suhteet ohessa:

![Alustava rakenne luokkakaaviona](./assets/tictactoe.png)

## Käyttöliittymä

Käyttöliiittymä sisältää kolme näkymää

* Aloitus
* Uuden käyttäjän luominen
* Pelilauta

Jokainen näistä on toteutettu omana JavaFX Scene-oliona. näkymistä aina yksi kerrallaan on näkyvissä, eli sijoitettu sovelluksen stageen. Käyttöliittymä on rakennettu ohjelmallisesti luokassa *tictactoe.main.TicTacToeApp*.




