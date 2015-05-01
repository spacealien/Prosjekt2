/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Odd, Thomas, Marthe
 */
public abstract class Forsikring
{
    
    //private final Ansatt ansatt;            Legg til datafelt for ansatt.
    private final Kunde kunde;
    private final Date startdato;
    private Date sluttdato;
    private double arligPremie = 0;
    private double totalbelop = 0;
    private String vilkår;
    private final String forsikringsnummer;
    private static int løpenummer = 1;
    private final List<Integer> skademeldingsnøkkler = new ArrayList<>();
    private boolean aktiv = true;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    
    public Forsikring( Kunde k )
    {
        startdato = new Date();
        forsikringsnummer = "F" + løpenummer++;
        //this.vilkår = vilkår;
        kunde = k;
    }

    public double getTotalbelop() {
        return totalbelop;
    }

    public void setTotalbelop(double totalbelop) {
        this.totalbelop = totalbelop;
    }

    public static int getLøpenummer() {
        return løpenummer;
    }

    public static void setLøpenummer(int løpenummer) {
        Forsikring.løpenummer = løpenummer;
    }

    public boolean isAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
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
    
    public String getForsikringsnummer()
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
    
    public Date getStartdato()
    {
        return startdato;
    }
    
    
    
    public Date getSluttdato()
    {
        return sluttdato;
    }
    
    public void setSluttdato(Date d)
    {
        sluttdato = d;
    }
     
    
    public String toString()
    {
        String utskrift;
        utskrift = "\nStartdato:" + sdf.format(startdato) + 
                   //"\nSluttdato: " + sluttdato.toString() +   
                    "\nÅrlig Premie: " + arligPremie + 
                   "\nTotal beløp: " + totalbelop +
                   "\nForsikringsnummer: " + forsikringsnummer + 
                   "\nVilkår: " + vilkår;
        return utskrift;
    }
    
    // må arves av alle ikke abstracte subklasser.
    abstract void beregnPris( Kunde kunde);
}//end of class
