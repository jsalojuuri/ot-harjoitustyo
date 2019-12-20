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

Sovelluksen looginen datamalli koostuu luokasta **Player**, josta ilmenee toistaiseksi vain pelaajan nimi. 

![Player](./assets/player.png)

Toiminnallisista kokonaisuuksista vastaa luokka **GameService**, tarjoten käyttöliittymän toiminnoille useita metodeja:
* boolean createPlayer(String playerName)
* boolean deletePlayer(String playerName)
* boolean login(String playerName)
* void initGameBoard(int width)
* String[][] getGameBoard()
* void setGameSquare(int i, int j, String chip)
* void changeTurn()
* String checkStatus()
* boolean isTurnX()
* String getPlayerX()
* String getPlayerO()
* void setPlayerX(String playerX)
* void setPlayerO(String playerO)

GameService pääsee käsiksi pelaajiin pakkauksessa tictactoe.dao sijaitsevan rajapinnan **Dao** toteuttavan **FilePlayerDao** luokan kautta. Luokan toteutus injektoidaan GameService luokalle konstruktorikutsun yhteydessä.

Pelin sisäisestä tilanteesta vastaa luokka **GameState**, joka tarjoaa GameServicelle erilaisia pelitilanteen tarkistamiseen ja manipuloimiseen liittyviä metodeja.

Ohjelman luokkien väliset suhteet luokkakaaviona:

![Luokkakaaviona](./assets/tictactoe.png)


## Tiedostojen pysyväistallennus

Pakkauksen *tictactoe.dao* luokka *FilePlayerDao* huolehtii pelaajatietojen tallennuksesta tiedostoihin.

Luokat noudattavat Data Access Object -suunnittelumallia ja voidaan tarvittaessa vaihtaa, jos tallennutapaa haluaa vaihtaa esim. tietokantatallennukseksi. Luokka on eristetty rajapinnan *Dao* taakse ja sovelluslogiikka ei käytä luokkia suoraan.


## Tiedostot

Sovellus tallentaa käyttäjien tiedot omaan tiedostoonsa. Sovelluksen juureen sijoitettu config.properties konfigurointitiedosto määrittelee tiedostojen nimet.


## Päätoiminnallisuudet

(tulossa myöhemmin)