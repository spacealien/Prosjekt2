/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


/**
 *
 * @author Odd, Thomas, Marthe
 */
public abstract class Forsikring
{
    
    //private final Ansatt ansatt;            Legg til datafelt for ansatt.
    private final Kunde kunde;
    private int egenandel;
    private final Calendar startdato;
    private Calendar sluttdato;
    private double arligPremie = 0;
    private double totalbelop = 0;
    private String vilkår;
    private final int forsikringsnummer;
    private static int løpenummer = 1000000;
    private final List<Integer> skademeldingsnøkkler = new ArrayList<>();
    private boolean aktiv = true;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    java.util.Locale norge = new java.util.Locale( "no" );
    private Date stDato;
                   //lokalitetsobjekt for norsk standard
    
    public Forsikring( Kunde k, int e_andel )
    {
        egenandel = e_andel;
        startdato = Calendar.getInstance(norge);
        stDato = startdato.getTime();
        forsikringsnummer =  løpenummer++;
        //this.vilkår = vilkår;
        kunde = k;
    }
    
    public String getType()
    {
        if( this instanceof Bilforsikring)
            return "Bilforsikring";
        else if( this instanceof BatForsikring)
            return "Båtforsikring";
        else if( this instanceof Eiendomsforsikring)
            return "Eiendomsforsikring";
        else if( this instanceof Fritidsboligforsikring)
            return "Fritidsboligforsikring";
        else
            return "Husforsikring";
    }
    
    public boolean erAktiv()
    {
        return aktiv;
    }
    
    
    public int getEgenandel()
    {
        return egenandel;
    }
    
    public void setEgenandel(int e)
    {
        egenandel = e;
    }
    
    public double getTotalbelop() 
    {
        return totalbelop;
    }

    public void setTotalbelop(double totalbelop)
    {
        this.totalbelop = totalbelop;
    }

    public static int getLøpenummer() 
    {
        return løpenummer;
    }

    public static void setLøpenummer(int løpenummer) 
    {
        Forsikring.løpenummer = løpenummer;
    }

    public boolean isAktiv() 
    {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) 
    {
        this.aktiv = aktiv;
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
    
    public Calendar getStartdato()
    {
        return startdato;
    }
    
    public Calendar getSluttdato()
    {
        return sluttdato;
    }
    
    public void setSluttdato(GregorianCalendar d)
    {
        sluttdato = d;
    }
    
    public void beregnPris()
    {
        
    }
    
    @Override
    public String toString()
    {
        String utskrift;
        utskrift = "\nStartdato:" + sdf.format(stDato) + 
                   //"\nSluttdato: " + sluttdato.toString() +   
                    "\nÅrlig Premie: " + arligPremie + 
                   "\nTotal beløp: " + totalbelop +
                   "\nForsikringsnummer: " + forsikringsnummer + 
                   "\nVilkår: " + vilkår;
        return utskrift;
    }
    
    // må arves av alle ikke abstracte subklasser.
    //abstract void beregnPris( Kunde kunde);
}//end of class
