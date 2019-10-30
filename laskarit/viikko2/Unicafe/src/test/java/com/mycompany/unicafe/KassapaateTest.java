/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salojuur
 */
public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti maksukortti;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        maksukortti = new Maksukortti(1000);
    }

    @Test
    public void luodunKassapaatteenRahamaaraJaMyytyjenLounaidenMaaraOikea() {
        assertTrue(kassapaate!=null);
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoToimiiEdullistenLounaidenOsaltaJosMaksuRiittava() {
        // maksetaan käteisellä 3 euroa, vaihtorahaa tulisi saada 60 senttiä
        assertEquals(60,kassapaate.syoEdullisesti(300));
        assertEquals(1,kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100240, kassapaate.kassassaRahaa());
    }

    @Test
    public void kateisostoToimiiEdullistenLounaidenOsaltaJosMaksuEiRiittava() {
        // maksetaan käteisellä 2 euroa, joten osto ei onnistu ja palautetaan koko summa
        assertEquals(200,kassapaate.syoEdullisesti(200));
        assertEquals(0,kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kateisostoToimiiMaukkaidenLounaidenOsaltaJosMaksuRiittava() {
        // maksetaan käteisellä 5 euroa, vaihtorahaa tulisi saada 100 senttiä
        assertEquals(100,kassapaate.syoMaukkaasti(500));
        assertEquals(1,kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100400, kassapaate.kassassaRahaa());
    }

    @Test
    public void kateisostoToimiiMaukkaidenLounaidenOsaltaJosMaksuEiRiittava() {
        // maksetaan käteisellä 2 euroa, joten osto ei onnistu ja palautetaan koko summa
        assertEquals(200,kassapaate.syoMaukkaasti(200));
        assertEquals(0,kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maksukorttiostoToimiiEdullistenLounaidenOsaltaJosKortinSaldoRiittava() {
        // maksetaan kortilla
        assertEquals(true,kassapaate.syoEdullisesti(maksukortti));
        assertEquals(1,kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maksukorttiostoToimiiMaukkaidenLounaidenOsaltaJosKortinSaldoRiittava() {
        // maksetaan kortilla
        assertEquals(true,kassapaate.syoMaukkaasti(maksukortti));
        assertEquals(1,kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maksukorttiostoToimiiEdullistenLounaidenOsaltaJosKortinSaldoEiRiittava() {
        // otetaan ensin kortilta 8 euroa pois saldoa, jotta kortille ei jää tarpeeksi saldoa ostosta varten
        maksukortti.otaRahaa(800);
        assertEquals(false,kassapaate.syoEdullisesti(maksukortti));
        assertEquals(0,kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maksukorttiostoToimiiMaukkaidenLounaidenOsaltaJosKortinSaldoEiRiittava() {
        // otetaan ensin kortilta 8 euroa pois saldoa, jotta kortille ei jää tarpeeksi saldoa ostosta varten
        maksukortti.otaRahaa(800);
        assertEquals(false,kassapaate.syoMaukkaasti(maksukortti));
        assertEquals(0,kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kassapaatteestaMaksukortilleRahaaLadattaessaKortinSaldoMuuttuu() {
        kassapaate.lataaRahaaKortille(maksukortti, 100);
        assertEquals(1100,maksukortti.saldo());
    }
    
    @Test
    public void kassapaatteestaMaksukortilleRahaaLadattaessaKassassaOlevaRahamaaraKasvaaLadatullaSummalla() {
        kassapaate.lataaRahaaKortille(maksukortti, 100);
        assertEquals(100100,kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kassapaatteestaMaksukortilleRahaaLadattaessaKortinSaldoEiMuuttuJosLadattavaRahamaaraNegatiivinen() {
        kassapaate.lataaRahaaKortille(maksukortti, -1);
        assertEquals(1000,maksukortti.saldo());
    }
    
}
