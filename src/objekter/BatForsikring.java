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
        
    public BatForsikring(  Kunde k, int e_andel, String vilkar, String registreringsnummer, int belop,
                           String fabrikant, String modell, String type, int hestekrefter, 
                           int arsmodell, boolean vekter, int lengde)
    {
        super( k, e_andel, vilkar, registreringsnummer, belop, fabrikant, modell, type, hestekrefter, arsmodell );
        this.vekter = vekter;
        this.lengde = lengde;
        arsModell = arsmodell;
        hk = hestekrefter;
        egenandel = e_andel;
        takst = belop;
    }

    public boolean getVekter() 
    {
        return vekter;
    }
    
    public void setVekter(boolean v) {
        
        vekter = v;
    }

    public int getLengde() 
    {
        return lengde;
    }

    public void setLengde(int lengde) 
    {
        this.lengde = lengde;
    }

    public int getArsModell() 
    {
        return arsModell;
    }

    public int getInnevarendeAr() 
    {
        return innevarendeAr;
    }

    public int getHk() 
    {
        return hk;
    }

    public int getTakst() {
        return takst;
    }
    
    public int getEgenandel() {
        return egenandel;
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
