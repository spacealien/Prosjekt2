/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Odd, Marthe
 */
public class Skademelding implements Serializable
{
    
    private final Forsikring forsikring;
    private Calendar dato;
    private Calendar opprettetdato;
    private final int skadenummer;
    private static int nestenr = 200000000;
    private String skadetype;
    private String beskrivelse;
    private Image[] bilder;
    private List<Vitne> vitner;
    private int takseringsbelop;
    private int erstatningsbelop;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private final Locale norge;
    private boolean aktiv = true;
    private static final long serialVersionUID = 512341233333L;
    
    public Skademelding( Forsikring forsikring, GregorianCalendar dato, String skadetype, String beskrivelse, int takseringsbelop, int erstatingsbelop )
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
        opprettetdato = testdato;
    }
    
    // set metoder
    public void setAktiv(boolean b)
    {
        aktiv = b;
    }
    
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
    public String getBeskrivelse()
    {
        return beskrivelse;
    }
    
    public String getSkadetype()
    {
        return skadetype;
    }
    
    public boolean getAktiv()
    {
        return aktiv;
    }
    
    public Calendar getOpprettetDato()
    {
        return opprettetdato;
    }
    
    public Calendar getSkadeDato()
    {
        return dato;
    }
    
    public int getErstatningsbelop()
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
        String ut = "\nOpprettet dato: " + sdf.format(opprettetdato) + "\nSkadenummer: " + skadenummer
                + "\nDato for skaden: " + dato + "\nSkadetype: " + skadetype +  
                "\nBeskrivelse av skaden: " + beskrivelse + "\nTakseringsbeløp: "
                + takseringsbelop + "\nErstatningsbeløp: " + erstatningsbelop;
        return ut;
    }
    
    
}
