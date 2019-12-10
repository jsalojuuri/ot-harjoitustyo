# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattaa perinteistä kolmitasoista kerrosarkkitehtuuria oheisella pakkausrakenteella:

![Koodin pakkausrakenne](./assets/tictactoe_packages.png)

Pakkaus *tictactoe.main* sisältää JavaFX:llä toteutetun käyttöliittymän, *tictactoe.service* sovelluslogiikan ja -palvelun apuluokkineen ja *tictactoe.dao* pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliiittymä sisältää kolme näkymää

* Aloitus
* Uuden käyttäjän luominen
* Pelilauta

Jokainen näistä on toteutettu omana JavaFX Scene-oliona. näkymistä aina yksi kerrallaan on näkyvissä, eli sijoitettu sovelluksen stageen. Käyttöliittymä on rakennettu ohjelmallisesti luokassa *tictactoe.main.TicTacToeApp*.

Käyttöliittymä on eristetty täysin sovelluslogiikasta, se kutsuu aina tarvittaessa sovelluslogiikan toteuttavan olion *gameService* metodeja.

## Sovelluslogiikka

Ohjelman luokkien väliset suhteet luokkakaaviona:

![Luokkakaaviona](./assets/tictactoe.png)


## Tiedostojen pysyväistallennus

Pakkauksen *tictactoe.dao* luokka *FilePlayerDao* huolehtii pelaajatietojen tallennuksesta tiedostoihin.

Luokat noudattavat Data Access Object -suunnittelumallia ja voidaan tarvittaessa vaihtaa, jos tallennutapaa haluaa vaihtaa esim. tietokantatallennukseksi. Luokka on eristetty rajapinnan *Dao* taakse ja sovelluslogiikka ei käytä luokkia suoraan.


## Tiedostot

Sovellus tallentaa käyttäjien tiedot omaan tiedostoonsa. Sovelluksen juureen sijoitettu config.properties konfigurointitiedosto määrittelee tiedostojen nimet.


## Päätoiminnallisuudet

(tulossa myöhemmin)