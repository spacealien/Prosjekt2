/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

import java.util.Calendar;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class BatForsikring extends Kjoretoyforsikring
{
    private boolean vekter;
    private int lengde;
    private int arsModell;
    private int innevarendeAr = Calendar.getInstance().get(Calendar.YEAR);
    private int hk;
    private int egenandel;
    private int takst;
        
    public BatForsikring(  Kunde k, int e_andel, String registreringsnummer, int belop,
                           String fabrikant, String modell, String type, int hestekrefter, 
                           int arsmodell, boolean vekter, int lengde)
    {
        super( k, e_andel, registreringsnummer, belop, fabrikant, modell, type, hestekrefter, arsmodell );
        this.vekter = vekter;
        this.lengde = lengde;
        arsModell = arsmodell;
        hk = hestekrefter;
        egenandel = e_andel;
        takst = belop;
    }

    public boolean getVekter() {
        return vekter;
    }

    public void setVekter(boolean v) {
        vekter = v;
    }

    public int getLengde() {
        return lengde;
    }

    public void setLengde(int lengde) {
        this.lengde = lengde;
    }
    
    
    
    /**
     * Denne metoden mottar kunde som parameter og gjør prisberegninger 
     * utifra kundeinformasjon.
     * @param kunde 
     */
    
    @Override
    public void beregnPris()
    {
        int bpTakst = 0;
        double bpEgenandel = 0;
        double bpBatAlder = 0;
        int bpBatAlderBeregn = innevarendeAr - arsModell;
        double bpHestekrefter = 0;
        double bpVekter = 0;
        double bpTilbud = 0;
        
        // Henter faktor for taksert verdi.
        if (takst > 0 && takst <= 10000)
        {
            bpTakst = 3500;
        }
        else if (takst > 10000 && takst <= 50000)
        {
            bpTakst = 4000;
        }
        else if (takst > 50000 && takst <= 200000)
        {
            bpTakst = 4500;
        }
        else if (takst > 200000 && takst <= 300000)
        {
            bpTakst = 5000;
        }
        else if (takst > 300000 && takst <= 400000)
        {
            bpTakst = 6000;
        }
        else if (takst > 400000 && takst <= 500000)
        {
            bpTakst = 7000;
        }
        else if (takst > 500000 && takst <= 600000)
        {
            bpTakst = 8000;
        }
        else if (takst > 600000 && takst <= 750000)
        {
            bpTakst = 9000;
        }
        else if (takst > 750000 && takst <= 1000000)
        {
            bpTakst = 10500;
        }
        else if (takst > 1000000 && takst <= 1500000)
        {
            bpTakst = 13000;
        }
        else if (takst > 1500000 && takst <= 2000000)
        {
            bpTakst = 18000;
        }
        else if (takst > 2000000 && takst <= 5000000)
        {
            bpTakst = 23000;
        }
        else if (takst > 5000000)
        {
            bpTakst = 33000;
        }
        
        // Henter faktor for egenandel. 
        if (egenandel == 2000)
        {
            bpEgenandel = 1;
        }
        else if (egenandel == 4000)
        {
            bpEgenandel = 0.85;
        }
        else if (egenandel == 8000)
        {
            bpEgenandel = 0.7;
        }
        else if (egenandel == 12000)
        {
            bpEgenandel = 0.55;
        }
        else if (egenandel == 16000)
        {
            bpEgenandel = 0.4;
        }
        else if (egenandel == 20000)
        {
            bpEgenandel = 0.25;
        }
        else if (egenandel == 30000)
        {
            bpEgenandel = 0.1;
        }
        
        // Henter faktor for båtens alder. 
        if (bpBatAlderBeregn > 0 && bpBatAlderBeregn <= 8)
        {
            bpBatAlder = 0.2;
        }
        else if (bpBatAlderBeregn > 8 && bpBatAlderBeregn <= 16)
        {
            bpBatAlder = 0.3;
        }
        else if (bpBatAlderBeregn > 17)
        {
            bpBatAlder = 0.5;
        }
        
        // Henter faktor for hestekrefter. 
        if (hk > 0 && hk <= 50)
        {
            bpHestekrefter = 0.2;
        }
        else if (hk > 50 && hk <= 100)
        {
            bpHestekrefter = 0.4;
        }
        else if (hk > 100 && hk <= 200)
        {
            bpHestekrefter = 0.6;
        }
        else if (hk > 200 && hk <= 300)
        {
            bpHestekrefter = 0.8;
        }
        else if (hk > 300 && hk <= 500)
        {
            bpHestekrefter = 1.0;
        }
        else if (hk > 500)
        {
            bpHestekrefter = 1.2;
        }
        
        // Henter faktor for om båtplass er bevoktet.
        if (vekter == true)
        {
            bpVekter = 0.0;
        }
        else
        {
            bpVekter = 0.2;
        }
        
        bpTilbud = bpTakst*(bpEgenandel+bpBatAlder+bpHestekrefter+bpVekter);
        
    }
    
    public String toString()
    {  
        String vekt;
        if (vekter)
            vekt = "Ja";
        else
            vekt = "Nei";
        
        String ut = super.toString();
        ut +=  "\nVekter: " + vekt + "\nLengde: " + lengde;
        return ut;
    }
    
   
}
