
package objekter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

/**
 *
 * @author Odd, Marthe, sist endret 10.20.2015
 */

public abstract class Bruker extends Person
{
    private String epost;
    private final String personnummer;
    private final Calendar fodtdato;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private final Date startdato;
    private final Locale norge = new Locale( "no" );
    private Date fDato;
    private static final long serialVersionUID = 54212334121113L;
    
    public Bruker(String fnavn, String enavn, String adr, String tlf, Calendar fd, String email, String persnummer)
    {
        super(fnavn, enavn, adr, tlf);
        epost = email;
        personnummer = persnummer;
        fodtdato = fd;
        startdato =  new GregorianCalendar(norge).getTime();
        fDato = fodtdato.getTime();
    }
    
    //Set metoder
    public void setEpost(String epost)
    {
        this.epost = epost;
    }
    
    // get metoder
    public Date getStartdato()
    {
        return startdato;
    }
    public String getEpost()
    {
        return epost;
    }
    
    public String getPersonnummer()
    {
        return personnummer;
    }
    
    public Date getFodtdato()
    {
        return fDato;
    }
    
    
    @Override
    public String toString()
    {
        String utskrift = super.toString();  //kall på superklassens toString-metode
        utskrift += "\nPersonnummer: " + personnummer + "\nFødselsdato: " + sdf.format(fDato) 
                  + "\nEpostadresse: " + epost;
        
        return utskrift;
    }
}//end of class
