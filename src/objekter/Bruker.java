/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Odd, Thomas, Marthe
 */
public abstract class Bruker extends Person
{
    private String epost;
    private final String personnummer;
    private final Date fodtdato;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    


    public Bruker(String fnavn, String enavn, String adr, String tlf, Date fd, String email, String persnummer)
    {
        super(fnavn, enavn, adr, tlf);
        epost = email;
        personnummer = persnummer;
        fodtdato = fd;
         
    }

    public String getEpost()
    {
        return epost;
    }

    public void setEpost(String epost)
    {
        this.epost = epost;
    }

    public String getPersonnummer()
    {
        return personnummer;
    }
    
    public Date getFodtdato()
    {
        return fodtdato;
    }
    
    
    @Override
    public String toString()
    {
        String utskrift = super.toString();  //kall på superklassens toString-metode
        utskrift += "\nPersonnummer: " + personnummer + "\nFødesldato: " + sdf.format(fodtdato) 
                  + "\nEpostadresse: " + epost;
        
        return utskrift;
    }
}//end of class
