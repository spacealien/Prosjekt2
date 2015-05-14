/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class Kunde extends Bruker
{
    //private Agent agent;  //implementere senre?
    private final int kundenummer;
    private static int nestenr = 100000;
    private final List<Integer> forsikringsnøkkel = new ArrayList<>();
    private final boolean totalkunde = false;
    private double årligForsikringsPremie = 0;
    
    public Kunde(String fnavn, String enavn, String adr, String tlf, Calendar fd,
                 String email, String persnummer)
    {
        super( fnavn,  enavn,  adr,  tlf,  fd, email, persnummer);              
        kundenummer = nestenr++;
    }

    public int getKundenr()
    {
        return kundenummer;
    }
    
    public int getNestenr()
    {
        return nestenr;
    }
    
    public void setNestenr(int nr)
    {
        nestenr = nr;
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
    
    public int finnForsikring( int n )
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
        //return forsikringsnøkkel.toArray(new String[forsikringsnøkkel.size()]);
    }
    
    public boolean erTotalkunde()
    {
        return totalkunde;
    }
    
    @Override
    public String toString()
    {
        String utskrift = super.toString();  //kall på superklassens toString-metode
        utskrift += "\nKundenummer: " + kundenummer;
        return utskrift;
    }    
/**
    @Override
    public int compareTo(Kunde k) 
    {
        int personnummer_1 = Integer.parseInt(this.getPersonnummer());
        int personnummer_2 = Integer.parseInt(k.getPersonnummer());
        
        final int STORRE = 1;
        final int LIK = 0;
        final int MINDRE = -1;
        
        if( personnummer_1 > personnummer_2 )
        {
            return STORRE;
        }
        else if(personnummer_1 == personnummer_2 )
        {
            return LIK;
        }
        else
        {
            return MINDRE;
        }
    }
   */
}//end of class
