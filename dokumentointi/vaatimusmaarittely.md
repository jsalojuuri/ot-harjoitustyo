# Vaatimusmäärittely - Tic-tac-toe

## Sovelluksen tarkoitus

Tic-tac-toe on perinteinen ristinolla-peli, jossa kaksi pelaajaa yrittävää päihittää toisensa saamalla pelilaudalle *viisi* peräkkäistä x- tai o-merkkiä. Peli voi myös päättyä tasan. 

## Käyttäjät

Pelissä on vain yksi käyttäjärooli: *pelaaja*.

## Suunnitellut toiminnallisuudet

### Ennen peliä

* Valitaan pelaajat pelimerkeille x ja o 
    * Joko luodaan uusi pelaaja tai valitaan jo aikaisemmin luotu pelaaja. Peliin ei ainakaan toistaiseksi vaadita kirjautumista, joten kuka tahansa voi valita vapaasti minkä tahansa aiemmin luodun pelaajan. Pelaajia voi myös halutessaan poistaa ohjelman pysyväistallennuksesta.

* Määritetään pelilaudan sivun pituus ruuduissa. 
    * Pituudelle asetetaan minimi- ja maksimipituudet. Syöte tarkistetaan ja pyydetään tarvittaessa antamaan sallitun rajoissa oleva pituus.
    * Pelilaudan koko on syöte * syöte, eli lauta on symmetrinen.

### Pelin aikana ja lopuksi

* Vuoron pelaaminen
    * Pelaaja x aloittaa pelin valitsemalla pelilaudalta ruudun, johon asettaa x-merkin hiiren vasenta nappia painamalla
    * Pelaajat jatkavat peliä vuorotellen kunnes löytyy voittaja tai tyhjiä ruutuja ei ole enää jäljellä (jolloin tulee tasapeli). Voittoon tarvitaan *viisi* samaa merkkiä joko vaaka-, pysty- tai vinoriville. 
* Pelin jälkeen
    * Voittajan nimi tulostetaan tai tasapelissä ilmoitetaan pelin päättyneen tasan. 
    * Valitaan joko uusi peli tai poistutaan ohjelmasta

## Toimintaympäristön rajoitteet ja käyttöliittymä

*  Toteutetaan Java FX-kirjaston avulla

## Jatkokehitysideoita

* Tilastoidaan pelaajien voitot, tappiot ja tasapelit ja lisätään käyttöliittymään listaus näistä aina pelin jälkeen.
* Luodaan valmiiksi eritasoisia tekoälypelaajia, jolloin ihminen voi pelata konetta vastaan
* Toiminnallisuus, jolla pelin voi keskeyttää ja palata siihen halutessaan myöhemmin aloitusvalikosta 
* Pelaajalistauksen sivutus ja/tai hakutoiminnallisuus
* Tilastoja kahden eri pelaajan välisistä peleistä
