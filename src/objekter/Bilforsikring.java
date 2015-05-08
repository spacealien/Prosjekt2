/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class Bilforsikring extends Kjoretoyforsikring
{
    private double bonusen;
    private String bonustekst;
    private boolean garasje;
    private boolean alarmen;
    private boolean esp;
    private boolean gjenkjenningen;
    private int maxKjorelengd;
    private int antallAr;
    private String foreralder;
    private int kmst;
    //---test for på beregnPris
    private int belopet;
    private int ar;
    private int hk;
    private int kmlengde;
    private int egenAndel;
    private final boolean garasjen;
    private String fAlder;
    
    public Bilforsikring(  Kunde k, int e_andel, String registreringsnummer, int belop,
                           String fabrikant, String modell, String type, int hestekrefter, 
                           int arsmodell, int kilometerstand, String foreralder, double bonus,
                           int antAr, boolean garasje, boolean alarm, boolean esp, boolean gjenkjenning, int km )
    {
        super( k, e_andel, registreringsnummer, belop, fabrikant, modell, type, hestekrefter, arsmodell);
        //this.bonustekst = bonustekst;
        antallAr = antAr;
        this.garasje = garasje;
        alarmen = alarm;
        this.esp = esp;
        gjenkjenningen = gjenkjenning;
        maxKjorelengd = km;
        this.foreralder = foreralder;
        kmst = kilometerstand;
        //--test for beregnPris
        belopet = belop;
        ar = arsmodell;
        bonusen = bonus;
        hk = hestekrefter;
        kmlengde = km;
        garasjen = garasje;
        fAlder = foreralder;
        egenAndel = e_andel;
    }
    
    public String getBonusTekst()
    {
        return bonustekst;
    }
    public void setBonusTekst(String bt)
    {
        bonustekst = bt;
    }
    
    public void korrigerBonusVedSkade()
    {
        GregorianCalendar dato = new GregorianCalendar();
        if (bonusen < 0.75)
        {
            bonusen -= 0.30;
            antallAr = 1;
        }
        
        else if (bonusen == 0.75)
        {
            switch (antallAr)
            {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    bonusen -=0.15;
                    antallAr = 1;
                    break;
                case 6:
                    antallAr = 1;
                    break;
            }
        }
        //beregnPris(kunde); For å oppdatere prisen
    }
    
    public void korrigerArligBonus()
    {
        if (bonusen < 0.70)
        {
            bonusen += 0.10;
            antallAr = 1;
        }
        else if (bonusen == 0.70)
        {
            switch (antallAr)
            {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    antallAr++;
                    break;
                case 6:
                    bonusen +=0.05;
                    antallAr = 1;
                    break;
            }
        }
        else if (bonusen == 0.75)
        {
            switch (antallAr)
            {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    antallAr++;
                    break;
            }
        }
        //beregnPris(kunde); For å oppdatere prisen
    }

    public int getKmstand()
    {
        return kmst;
    }
    
    public void setKmstand(int kms)
    {
        kmst = kms;
    }
    public int getMaxKjorelengde()
    {
        return maxKjorelengd;
    }
    public void setMaxKjorelengde(int mkl)
    {
        maxKjorelengd = mkl;
    }

    public boolean getAlarm()
    {
        return alarmen;
    }
    public boolean getESP()
    {
        return esp;
    }
    public boolean getGjenkjenning()
    {
        return gjenkjenningen;
    }
    public void setAlarm(boolean a)
    {
        alarmen = a;
    }
    
    public void setESP(boolean e)
    {
        esp = e;
    }
    public void setGjenkjenning(boolean g)
    {
        gjenkjenningen = g;
    }
    public boolean getGarasje() {
        return garasjen;
    }

    public String getForerAlder() {
        return foreralder;
    }
    public void setForerAlder(String f)
    {
        foreralder = f;
    }
    
    
    public void setBonus( double b )
    {
        bonusen = b;
        /*switch (b)
                {
                    case "-50%":
                        bonusen = -0.50;
                        break;
                    case "-40%":
                        bonusen = -0.40;
                        break;   
                    case "-30%":
                        bonusen = -0.30;
                        break;
                    case "-20%":
                        bonusen = -0.20;
                        break;
                    case "-10%":
                        bonusen = -0.10;
                        break;
                    case "0%":
                        bonusen = 0.00;
                        break;   
                    case "10%":
                        bonusen = 0.10;
                        break;
                    case "20%":
                        bonusen = 0.20;
                        break;
                    case "30%":
                        bonusen = 0.30;
                        break;   
                    case "40%":
                        bonusen = 0.40;
                        break;
                    case "50%":
                        bonusen = 0.50;
                        break;
                    case "60%":
                        bonusen = 0.60;
                        break;
                    case "70%": 
                        bonusen = 0.70;
                        break;
                    case "70% 2 år":
                        bonusen = 0.70;
                        antallAr = 2;
                        break;
                    case "70% 3 år":  
                        bonusen = 0.70;
                        antallAr = 3;
                        break; 
                    case "70% 4 år":
                        bonusen = 0.70;
                        antallAr = 4;
                        break;
                    case "70% 5 år":
                        bonusen = 0.70;
                        antallAr = 5;
                        break;
                    case "75%":
                        bonusen = 0.75;
                        break; 
                    case "75% 2 år":
                        bonusen = 0.75;
                        antallAr = 2;
                        break; 
                    case "75% 3 år":
                        bonusen = 0.75;
                        antallAr = 3;
                        break; 
                    case "75% 4 år":
                        bonusen = 0.75;
                        antallAr = 4;
                        break; 
                    case "75% 5 år":  
                        bonusen = 0.75;
                        antallAr = 5;
                        break;  
                    case "75% >5 år":
                        bonusen = 0.75;
                        antallAr = 6;
                    break;
            }*/
        //beregnPris(kunde); For å oppdatere prisen
    }
    
    public double getBonus()
    {
        return bonusen;
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
    
    @Override
    public void beregnPris()
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
      //kunde.setÅrligPremieblablabla for å oppdatere prisen (årlig)
    }
    
    @Override
    public String toString()
    {
        String g;
        if (garasje)
            g = "Ja";
        else
            g = "Nei";
        
        String ut = super.toString();
        ut += "\nMax kjørelengde: " + maxKjorelengd + "\nBonus: " + bonusen + "\nGarasje: " + g;
        return ut;
    }
}
