
package objekter;

import java.awt.Image;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 *
 * @author Odd, Marthe, Sist endet 17.05.2015.
 */
public class Skademelding implements Serializable
{
    
    private final Forsikring forsikring;
    private Calendar dato;
    private final Calendar opprettetdato;
    private final int skadenummer;
    private static int nestenr = 200000000;
    private String skadetype;
    private String beskrivelse;
    private Image[] bilder;
    private List<Vitne> vitner;
    private int takseringsbelop;
    private double erstatningsbelop;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private final Locale norge;
    private static final long serialVersionUID = 512341233333L;
    
    public Skademelding( Forsikring forsikring, GregorianCalendar dato, String skadetype, String beskrivelse, int takseringsbelop, double erstatingsbelop )
    {
        this.norge = new Locale( "no" );
        this.forsikring = forsikring;
        this.dato = dato;
        this.skadetype = skadetype;
        this.beskrivelse = beskrivelse;
        this.takseringsbelop = takseringsbelop;
        this.erstatningsbelop = erstatingsbelop;
        skadenummer = nestenr++;
        Calendar testdato = new GregorianCalendar(norge);
        Random util = new Random();
        testdato.setTime(new Date(Math.abs(System.currentTimeMillis() - util.nextLong())));
        testdato.set(Calendar.YEAR, 2014);
        testdato.set(Calendar.MONTH, Calendar.JANUARY);
        opprettetdato = testdato;
    }
    
    // set metoder
    public List<Vitne> getVitner()
    {
        return vitner;
    }
    
    public void setVitner(List<Vitne> v)
    {
        vitner = v;
    }
    public Forsikring getForsikring()
    {
        return forsikring;
    }
    
    public void setBeskrivelse( String input )
    {
        this.beskrivelse = input;
    }
    
    public void setSkadetype(String input)
    {
        skadetype = input;
    }
    
    public void setBilder( Image[] photo )
    {
        bilder = photo;
    }
    
    public static void setLøpenummer( int n)
    {
        nestenr = n;
    }
    
    // get metoder
    
    public Image[] getBilder()
    {
        return bilder;
    }
    
    public String getBeskrivelse()
    {
        return beskrivelse;
    }
    
    public String getSkadetype()
    {
        return skadetype;
    }
    
    
    public Calendar getOpprettetDato()
    {
        return opprettetdato;
    }
    
    public Calendar getSkadeDato()
    {
        return dato;
    }
    
    public double getErstatningsbelop()
    {
        return erstatningsbelop;
    }
    
    public int getTakseringsbelop()
    {
        return takseringsbelop;
    }
    
    public int getSkadenummer()
    {
        return skadenummer;
    }
    
    public static int getLøpenummer()
    {
        return nestenr;
    }
    
    @Override
    public String toString()
    {
        String ut = "\nOpprettet dato: " + sdf.format(opprettetdato.getTime()) + "\nSkadenummer: " + skadenummer
                + "\nDato for skaden: " + sdf.format(dato.getTime()) + "\nSkadetype: " + skadetype +  
                "\nBeskrivelse av skaden: " + beskrivelse + "\nTakseringsbeløp: "
                + takseringsbelop + "\nErstatningsbeløp: " + erstatningsbelop;
        return ut;
    }
    
    
}
