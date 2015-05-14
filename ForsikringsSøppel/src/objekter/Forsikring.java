/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public abstract class Forsikring
{
    
    //private final Ansatt ansatt;            Legg til datafelt for ansatt.
    private final Kunde kunde;
    private final Calendar startdato;
    private Calendar sluttdato;
    private double arligPremie = 0;
    private double totalbelop = 0;
    private String vilkår;
    private final int forsikringsnummer;
    private static int løpenummer = 1;
    private final List<Integer> skademeldingsnøkkler = new ArrayList<>();
    private boolean aktiv = true;
    
    public Forsikring( Kunde k )
    {
        startdato = Calendar.getInstance();
        forsikringsnummer = løpenummer++;
        //this.vilkår = vilkår;
        kunde = k;
    }
    
    public void setAktiver( boolean ok )
    {
        aktiv = ok;
        if(!aktiv)
        {
            kunde.trekkFraÅrligForsikringsPremie(totalbelop);
        }
    }
    
    public void setArligPremie( double beløp )
    {
        this.arligPremie = beløp;
        kunde.leggTilÅrligForsikringsPremie(beløp);
    }
    
    public void setTotalbeløp( double beløp )
    {
        this.totalbelop = beløp;
    }
    
    public void setVilkår( String vilkår )
    {
        this.vilkår = vilkår;
    }
    
    public double getArligPremie()
    {
        return arligPremie;
    }
    
    public double getTotalbeløp()
    {
        return totalbelop;
    }
    
    public int getForsikringsnummer()
    {
        return forsikringsnummer;
    }
    
    public void leggTilNøkkel( int i)
    {
        skademeldingsnøkkler.add(i);
    }
    
    public List<Integer> getNøkkelliste()
    {
        return skademeldingsnøkkler;
        //return forsikringsnøkkel.toArray(new String[forsikringsnøkkel.size()]);
    }
     
    
    public String toString()
    {
        String utskrift;
        utskrift = "\nStartdato:" + startdato.toString() + 
                   "\nSluttdato: " + sluttdato.toString() +   
                    "\nÅrlig Premie: " + arligPremie + 
                   "\nTotal beløp: " + totalbelop +
                   "\nForsikringsnummer: " + forsikringsnummer + 
                   "\nVilkår: " + vilkår;
        return utskrift;
    }
    
    // må arves av alle ikke abstracte subklasser.
    abstract void beregnPris( Kunde kunde);
}//end of class
