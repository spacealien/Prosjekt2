/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class Kunde extends Bruker
{
    private final List<Integer> forsikringsnøkkel = new ArrayList<>();
    private boolean totalkunde = false;
    private double årligForsikringsPremie = 0;
    
    public Kunde(String fnavn, String enavn, String adr, String tlf, GregorianCalendar fd,
                 String email, String persnummer)
    {
        super( fnavn,  enavn,  adr,  tlf,  fd, email, persnummer);
    }
    
    public void setTotalKunde( boolean total )
    {
        totalkunde = total;
    }
    
    public void leggTilÅrligForsikringsPremie(double beløp)
    {
        årligForsikringsPremie += beløp;
    }
    
    public void trekkFraÅrligForsikringsPremie( double beløp )
    {
        årligForsikringsPremie -= beløp;
    }
    
    public double getÅrligForsikringsPremie()
    {
        return årligForsikringsPremie;
    }
    
    public int finnForsikringsNøkkel( int n )
    {
        return Collections.binarySearch(forsikringsnøkkel, n);
    }
    
    public void leggTilNøkkel( int i)
    {
        forsikringsnøkkel.add(i);
    }
    
    public List<Integer> getNøkkelliste()
    {
        return forsikringsnøkkel;
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
    public void setTotalkunde(boolean tk)
    {
        totalkunde = tk;
    }
    
    @Override
    public String toString()
    {
        String utskrift = super.toString();  //kall på superklassens toString-metode
        return utskrift;
    }    

}//end of class
