/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

import java.util.Calendar;
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
    private int bilensVerdi;
    //---test for på beregnPris
    private int belopet;
    private int innevarendeAr = Calendar.getInstance().get(Calendar.YEAR);
    private int arsModellen;
    private int ar = innevarendeAr - arsModellen;
    private int hk;
    private int kmlengde;
    private int egenAndel;
    private final boolean garasjen;
    private int fAlder;
    
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
        // ar = arsmodell;
        bonusen = bonus;
        hk = hestekrefter;
        kmlengde = km;
        garasjen = garasje;
//        fAlder = Integer.parseInt(foreralder);
        egenAndel = e_andel;
        arsModellen = arsmodell;
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
        int bpTakst = 0;
        double bpBilAlder = 0;
        double bpKjorelengde = 0;
        double bpHk = 0;
        double bpGarasje = 0;
        double bpEgenandel = 0;
        double bpForerAlder = 0;
        double bpEsp = 0;
        double bpAlarm = 0;
        double bpGps = 0;
        double bpTilbud = 0;
        
        //Henter faktor for bilens grunnbeløp.
        if (belopet > 0 && belopet <= 50000) 
        {
            bpTakst = 3500;
        }
        else if (belopet > 50000 && belopet <= 100000)
        {
            bpTakst = 4000;
        }
        else if (belopet > 100000 && belopet <= 150000)
        {
            bpTakst = 4500;
        }
        else if (belopet > 150000 && belopet <= 200000)
        {
            bpTakst = 5000;
        }
        else if (belopet > 200000 && belopet <= 250000)
        {
            bpTakst = 5500;
        }
        else if (belopet > 250000 && belopet <= 300000)
        {
            bpTakst = 6000;
        }
        else if (belopet > 300000 && belopet <= 400000)
        {
            bpTakst = 7000;
        }
        else if (belopet > 400000 && belopet <= 500000)
        {
            bpTakst = 8000;
        }
        else if (belopet > 500000 && belopet <= 600000)
        {
            bpTakst = 9000;
        }
        else if (belopet > 600000 && belopet <= 600000)
        {
            bpTakst = 10500;
        }
        else if (belopet > 750000 && belopet <= 1000000)
        {
            bpTakst = 13000;
        }
        else if (belopet > 1000000 && belopet <= 1500000)
        {
            bpTakst = 18000;
        }
        else if (belopet > 1500000 && belopet <= 2000000)
        {
            bpTakst = 23000;
        }
        else if (belopet > 2000000)
        {
            bpTakst = 33000;
        }
        
        // Henter faktor for bilens alder.
        if (ar > 0 && ar <= 8)
        {
            bpBilAlder = 0.2;
        }
        else if (ar > 8 && ar <= 16)
        {
            bpBilAlder = 0.3;
        }
        else if (ar > 16)
        {
            bpBilAlder = 0.5;
        }
        
        // Henter faktor for forventet kjørelengde.
        if (kmlengde == 8000)
        {
            bpKjorelengde = 0.2;
        }
        else if (kmlengde == 12000)
        {
            bpKjorelengde = 0.3;
        }
        else if (kmlengde == 16000)
        {
            bpKjorelengde = 0.4;
        }
        else if (kmlengde == 20000)
        {
            bpKjorelengde = 0.5;
        }
        else if (kmlengde == 25000)
        {
            bpKjorelengde = 0.6;
        }
        else if (kmlengde == 30000)
        {
            bpKjorelengde = 0.7;
        }
        
        // Henter faktor for bilens hestekrefter.
        if (hk > 0 && hk <= 100)
        {
            bpHk = 0.2;
        }
        else if (hk > 100 && hk <= 200)
        {
            bpHk = 0.4;
        }
        else if (hk > 200 && hk <= 300)
        {
            bpHk = 0.6;
        }
        else if (hk > 300 && hk <= 400)
        {
            bpHk = 0.8;
        }
        else if (hk > 400 && hk <= 500)
        {
            bpHk = 1.0;
        }
        else if (hk > 500)
        {
            bpHk = 1.2;
        }
        
        //Henter faktor for garasje.
        if (garasje == true)
        {
            bpGarasje = 0.0;
        }
        else
        {
            bpGarasje = 0.2;
        }
        
        //Henter faktor for egenandel.
        if (egenAndel == 2000)
        {
            bpEgenandel = 1.0;
        }
        else if (egenAndel == 4000)
        {
            bpEgenandel = 0.85;
        }
        else if (egenAndel == 8000)
        {
            bpEgenandel = 0.7;
        }
        else if (egenAndel == 12000)
        {
            bpEgenandel = 0.55;
        }
        else if (egenAndel == 16000)
        {
            bpEgenandel = 0.4;
        }
        else if (egenAndel == 20000)
        {
            bpEgenandel = 0.25;
        }
        else if (egenAndel == 30000)
        {
            bpEgenandel = 0.1;
        }
        
        // Henter faktor for førers alder. 
        if (fAlder >= 18 && fAlder <= 23)
        {
            bpForerAlder = 0.6;
        }
        else if (fAlder > 23 && fAlder <= 30)
        {
            bpForerAlder = 0.3;
        }
        else if (fAlder > 30)
        {
            bpForerAlder = 0.1;
        }
        
        // Henter faktor for om bilen har Antiskrens/ESP.
        if (esp == true)
        {
            bpEsp = 0.0;
        }
        else
        {
            bpEsp = 0.2;
        }
        
        // Henter faktor for om bilen har godkjent alarm. 
        if (alarmen == true)
        {
            bpAlarm = 0.0;
        }
        else
        {
            bpAlarm = 0.2;
        }
        
        // Henter faktor for om bilen har GPS tracking. 
        if (gjenkjenningen == true)
        {
            bpGps = 0.0;
        }
        else
        {
            bpGps = 0.2;
        }
        
        bpTilbud = bpTakst*(bpBilAlder+bpKjorelengde+bpHk+bpGarasje+bpEgenandel+bpForerAlder+bpEsp+bpAlarm+bpGps);
        
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
