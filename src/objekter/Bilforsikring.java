/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

import java.util.GregorianCalendar;

/**
 *
 * @author Odd, Marthe
 */
public class Bilforsikring extends Kjoretoyforsikring
{
    private double bonusen;
    private boolean skadefri = true;
    private boolean garasje;
    private boolean alarmen;
    private boolean esp;
    private boolean gjenkjenningen;
    private int maxKjorelengd;
    private int antallAr;
    private String foreralder;
    private int kmst;
    private static final long serialVersionUID = 5123421325123L;
    
    
    public Bilforsikring(  Kunde k, int e_andel, String vilkar, String registreringsnummer, int belop,
                           String fabrikant, String modell, String type, int hestekrefter, 
                           int arsmodell, int kilometerstand, String foreralder, double bonus,
                           int antAr, boolean garasje, boolean alarm, boolean esp, boolean gjenkjenning, int km )
    {
        super( k, e_andel, vilkar, registreringsnummer, belop, fabrikant, modell, type, hestekrefter, arsmodell);
        
        antallAr = antAr;
        this.garasje = garasje;
        alarmen = alarm;
        this.esp = esp;
        gjenkjenningen = gjenkjenning;
        maxKjorelengd = km;
        this.foreralder = foreralder;
        kmst = kilometerstand;
        
    }
    public void korrigerBonusVedSkade()
    {
        skadefri = false;
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
        if(skadefri)
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
        }
        skadefri = true;
    }
    
    //set metode
    public void setBonus( double b )
    {
        bonusen = b;
    }
    
    public double getBonus()
    {
        return bonusen;
    }
    
    public void setAntallAr(int a)
    {
        antallAr = a;
    }
    
    public void setKmstand(int kms)
    {
        kmst = kms;
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
    public boolean getGarasje() 
    {
        return garasje;
    }
   
    public void setForerAlder(String f)
    {
        foreralder = f;
    }
    
    // get metoder
    public int getKmstand()
    {
        return kmst;
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

    public String getForerAlder() 
    {
        return foreralder;
    }
    
    public int getAntallAr()
    {
        return antallAr;
    }
    
    public void setGarasje( boolean bekreftelse )
    {
        this.garasje = bekreftelse;
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
