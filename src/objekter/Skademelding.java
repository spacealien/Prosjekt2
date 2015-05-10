/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class Skademelding
{
    
    private final Forsikring forsikring;
    private Date dato;
    private GregorianCalendar opprettetdato;
    private final int skadenummer;
    private static int nestenr = 200000000;
    private String skadetype;
    //private String skademeldingsskjema;
    private String beskrivelse;
    private File[] bilder;
    private Vitne vitne;
    private int takseringsbelop;
    private int erstatningsbelop;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private boolean aktiv = true;
    
    public Skademelding( Forsikring forsikring)
    {
        this.forsikring = forsikring;
        skadenummer = nestenr;
    }
    
    public Skademelding( Forsikring forsikring, Date dato, String skadetype, String beskrivelse, int takseringsbelop, int erstatingsbelop )
    {
        this.forsikring = forsikring;
        this.dato = dato;
        this.skadetype = skadetype;
        this.beskrivelse = beskrivelse;
        this.takseringsbelop = takseringsbelop;
        this.erstatningsbelop = erstatingsbelop;
        skadenummer = nestenr++;
        opprettetdato = new GregorianCalendar();
    }
    
    public Forsikring getForsikring()
    {
        return forsikring;
    }
    
    public String getBeskrivelse()
    {
        return beskrivelse;
    }
    
    public boolean getAktiv()
    {
        return aktiv;
    }
    
    public void setAktiv(boolean b)
    {
        aktiv = b;
    }
    
    public GregorianCalendar getOpprettetDato()
    {
        return opprettetdato;
    }
    
    public Date getSkadeDato()
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
    
    public void setBeskrivelse( String input )
    {
        this.beskrivelse = input;
    }
    
    public void setBilder( File[] photo )
    {
        bilder = photo;
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
