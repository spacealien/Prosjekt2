/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
/**
 *
 * @author Odd, Marthe
 */
public abstract class Bruker extends Person
{
    private String epost;
    private final String personnummer;
    private final Calendar fodtdato;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private final Date startdato;
    java.util.Locale norge = new java.util.Locale( "no" );
    private Date fDato;
    private static final long serialVersionUID = 54212334121113L;
    
    public Bruker(String fnavn, String enavn, String adr, String tlf, Calendar fd, String email, String persnummer)
    {
        super(fnavn, enavn, adr, tlf);
        epost = email;
        personnummer = persnummer;
        fodtdato = fd;
        Calendar testdato = new GregorianCalendar(norge);
        Random util = new Random();
        testdato.setTime(new Date(Math.abs(System.currentTimeMillis() - util.nextLong())));
        testdato.set(Calendar.YEAR, 2014);
        startdato =  testdato.getTime();
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
        utskrift += "\nPersonnummer: " + personnummer + "\nFødesldato: " + sdf.format(fDato) 
                  + "\nEpostadresse: " + epost;
        
        return utskrift;
    }
}//end of class
