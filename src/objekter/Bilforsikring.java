/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

import java.util.Date;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class Bilforsikring extends Kjoretoyforsikring
{
    private double bonus;
    private boolean garasje;
    private int maxKjorelengd;
    private int antallAr;
    private String foreralder;
    //---test for på beregnPris
    private int belopet;
    private int ar;
    private int kmst;
    private double bonusen;
    private int hk;
    private int kmlengde;
    private int egenAndel;
    private boolean garasjen;
    private String fAlder;
    
    public Bilforsikring(  Kunde k, int e_andel, String registreringsnummer, int belop,
                           String fabrikant, String modell, String type, int hestekrefter, 
                           int arsmodell, int kilometerstand, String foreralder, double bonus, int antAr, boolean garasje, int km )
    {
        super( k, e_andel, registreringsnummer, belop, fabrikant, modell, type, hestekrefter, arsmodell);
        this.bonus = bonus;
        antallAr = antAr;
        this.garasje = garasje;
        maxKjorelengd = km;
        this.foreralder = foreralder;
        //--test for beregnPris
        belopet = belop;
        ar = arsmodell;
        kmst = kilometerstand;
        bonusen = bonus;
        hk = hestekrefter;
        kmlengde = km;
        garasjen = garasje;
        fAlder = foreralder;
        egenAndel = e_andel;
    }
    
    public void setBonus( double b )
    {
        bonus = b;
    }
    
    public void setAntallAr(int a)
    {
        antallAr = a;
    }
    
    public int getAntallAr()
    {
        return antallAr;
    }
    
    public void setGarasje( boolean bekreftelse )
    {
        this.garasje = bekreftelse;
    }
    
    /**
     * Denne metoden mottar kunde som parameter og gjør prisberegninger 
     * utifra kundeinformasjon.
     * @param kunde 
     */
    
    //@Override
    public void beregnPris( Kunde kunde )
    {
        int hkvar = 0;
        double forsikringspris;
      
        if ((hk > 0) && (hk < 80))
      {
          hkvar = 1;
      }
      else if (80 < hk && hk  < 110)
      {
          hkvar = 2;
      }
      else if (110 < hk && hk < 140)
      {
          hkvar = 3;
      }
      else if (140 < hk && hk  < 170)
      {
          hkvar = 4;
      }
      else if (170 < hk && hk < 200)
      {
          hkvar = 5;
      }
      double var = belopet * 0.012; //kasko: * 0.015, superkasko: * 0.02
      double arspris = var*0.09*(2015-ar); //Må bruke Calendar i stedet for 2015
      double hkpris = (var*0.1*hkvar);
      double lengdepris = (var*0.00005*kmlengde);
      double kmstandpris = (var*0.00004*kmst);
      double garasjepris = 0;
      if(!garasje)
      {
          garasjepris = var*0.5;
      }
      double andelsPris = (egenAndel / (egenAndel/1000));
      forsikringspris = var + arspris + hkpris + lengdepris + kmstandpris + garasjepris + andelsPris;
      double forerpris;
      if (fAlder.equals("Bilfører < 23 år"))
      forsikringspris = forsikringspris*1.29;
      else if (fAlder.equals("Bilfører mellom 23 - 25 år"))
      forsikringspris = forsikringspris*1.09;
      
      System.out.println(forsikringspris);
      
    }
    
    public String toString()
    {
        String g;
        if (garasje)
            g = "Ja";
        else
            g = "Nei";
        
        String ut = super.toString();
        ut += "\nMax kjørelengde: " + maxKjorelengd + "\nBonus: " + bonus + "\nGarasje: " + g;
        return ut;
    }
}
