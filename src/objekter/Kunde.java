/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Odd, Marthe
 */
public class Kunde extends Bruker
{
    private final List<Integer> forsikringsnøkkel = new ArrayList<>();
    private boolean totalkunde = false;
    private double årligForsikringsPremie = 0;
    private static final long serialVersionUID = 88811111123L;
    
    public Kunde(String fnavn, String enavn, String adr, String tlf, GregorianCalendar fd,
                 String email, String persnummer)
    {
        super( fnavn,  enavn,  adr,  tlf,  fd, email, persnummer);
    }
    
    public void leggTilNøkkel( int i)
    {
        forsikringsnøkkel.add(i);
    }
    
    public void leggTilÅrligForsikringsPremie(double beløp)
    {
        årligForsikringsPremie += beløp;
    }
    
    public void trekkFraÅrligForsikringsPremie( double beløp )
    {
        årligForsikringsPremie -= beløp;
    }
    
    public int finnForsikringsNøkkel( int n )
    {
        return Collections.binarySearch(forsikringsnøkkel, n);
    }
    
    public boolean erTotalkunde()
    {
        return totalkunde;
    }
    
    public String erTotalkundeTekst()
    {
        if (totalkunde)
            return "Ja";
        else
            return "Nei";
    }
    
    // set metoder
    public void setTotalKunde( boolean total )
    {
        totalkunde = total;
    }
    
    public void setTotalkunde(boolean tk)
    {
        totalkunde = tk;
    }
    
    // get metoder
    public double getÅrligForsikringsPremie()
    {
        return årligForsikringsPremie;
    }
    
    public List<Integer> getNøkkelliste()
    {
        return forsikringsnøkkel;
    }
    
    @Override
    public String toString()
    {
        String utskrift = super.toString();  //kall på superklassens toString-metode
        return utskrift;
    }    

}//end of class
