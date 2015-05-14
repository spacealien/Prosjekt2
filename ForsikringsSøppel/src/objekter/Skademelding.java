/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

import java.util.Calendar;

/**
 *
 * @author Odd, Thomas Marthe
 */
public class Skademelding
{
    private final Forsikring forsikring;
    private Calendar dato;
    private Calendar opprettetdato;
    private final int skadenummer;
    private static int nestenr = 20000;
    private String skadetype;
    //private String skademeldingsskjema;
    private String beskrivelse;
    //private FIL bilde;
    Vitne vitne;
    private int takseringsbelop;
    private int erstatningsbelop;
    
    public Skademelding( Forsikring forsikring, String skadetype, String beskrivelse, int takseringsbelop, int erstatingsbelop )
    {
        this.forsikring = forsikring;
        this.skadetype = skadetype;
        this.beskrivelse = beskrivelse;
        this.takseringsbelop = takseringsbelop;
        this.erstatningsbelop = erstatingsbelop;
        skadenummer = nestenr++;
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

    
    
}
