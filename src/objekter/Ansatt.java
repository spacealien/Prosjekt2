/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class Ansatt extends Bruker
{
    private final int ansattnr;
    private static int nestenr = 1;
    private boolean admin = false;
    private final String brukernavn;
    private String passord;
    private List<String> kundenøkler = new ArrayList<>();

    public Ansatt(String fnavn, String enavn, String adr, String tlf, GregorianCalendar fd, String email, String persnummer)
    {   
        super(fnavn, enavn, adr, tlf, fd, email, persnummer);
        ansattnr = nestenr++;
        
        String brukernavnhjelper = String.valueOf(ansattnr);
        brukernavn = this.getEtternavn()+ brukernavnhjelper;
        passord = "passord";
    }
    
    public List<String> getKundenøkler()
    {
        return kundenøkler;
    }
    
    public void leggTilKundenøkel( String nøkkel)
    {
        kundenøkler.add(nøkkel);
    }
    
    public int getAnsattnr()
    {    
        return ansattnr;
    }
    
    public int getNestenr()
    {
        return nestenr;
    }
    
    public String getBrukernavn()
    {
        return brukernavn;
    }
    
    public String getPassord()
    {
        return passord;
    }
    
    public void setNestenr(int nr)
    {
        nestenr = nr;
    }
    
    public void setAdmin( boolean erAdmin )
    {
        admin = erAdmin;
    }
    
    public boolean erAdmin()
    {
        return admin;
    }
    
    @Override
    public String toString()
    {
        String utskrift = super.toString();  //kall på superklassens toString-metode
        utskrift += "\nAnsattnummer: " + ansattnr;
        return utskrift;
    }    

}
