
package objekter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;


/*
 *  Den abstrakte klassen har som hensikt å ta vare på datafelter og metoder
 *  som er nødvendig for alle typer forsikringer.
 *
 *  @author Odd, Marthe. Sist endret 15.05.2015.
 */
public abstract class Forsikring implements Serializable
{
    private final Kunde kunde;
    private int egenandel;
    private final Calendar startdato;
    private Calendar sluttdato = null;
    private Date sistBetalt;
    private double arligPremie = 0;
    private double totalbelop = 0;
    private String vilkar;
    private final int forsikringsnummer;
    private static int løpenummer = 1000000;
    private final List<Integer> skademeldingsnøkkler;
    private boolean aktiv = true;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private Locale norge = new Locale( "no" );
    private String dekning;
    private static final long serialVersionUID = 12345678910L;
    
    public Forsikring( Kunde k, int e_andel, String betingelser )
    {
        egenandel = e_andel;        
        startdato = new GregorianCalendar(norge);
        vilkar = betingelser;
        forsikringsnummer =  løpenummer++;
        kunde = k;
        skademeldingsnøkkler = new ArrayList<>();
        sistBetalt = startdato.getTime();
    }
    
    /*
        Metoden returnerer en Sting som representerer hvilket type forsikrings
        objekt sub klasse objektet er.
    */
    
    public String getForsikringsType()
    {
        if( this instanceof Bilforsikring)
            return "Bilforsikring";
        else if( this instanceof BatForsikring)
            return "Båtforsikring";
        else if( this instanceof Husforsikring)
            return "Husforsikring";
        else if( this instanceof Fritidsboligforsikring)
            return "Fritidsboligforsikring";
        else
            return "Reiseforsikring";
    }
    
    public void setDekning( String type )
    {
        dekning = type;
    }
    
    public void setSistBetalt(Date d)
    {
        sistBetalt = d;
    }
    
    public Date getSistBetalt()
    {
        return sistBetalt;
    }
    
    public String getDekning()
    {
        return dekning;
    }
    
    public Kunde getKunde()
    {
        return kunde;
    }
    
    public boolean erAktiv()
    {
        return aktiv;
    }
    
    public String erAktivTekst()
    {
        if (aktiv)
            return "Ja";
        else
            return "Nei";
    }
    
    public int getEgenandel()
    {
        return egenandel;
    }
    
    public void setEgenandel(int e)
    {
        egenandel = e;
    }
    
    public double getTotalbelop() 
    {
        return totalbelop;
    }

    public void setTotalbelop(double totalbelop)
    {
        this.totalbelop = totalbelop;
    }

    public static int getLøpenummer() 
    {
        return løpenummer;
    }

    public static void setLøpenummer(int n) 
    {
        løpenummer = n;
    }

    public String getVilkar()
    {
        return vilkar;
    }

    public void setVilkar(String v)
    {
        vilkar = v;
    }
    
    public void setAktiver( boolean ok )
    {
        aktiv = ok;
        if(!aktiv)
        {
            kunde.trekkFraÅrligForsikringsPremie(totalbelop);
            sluttdato = new GregorianCalendar(norge);
            sluttdato = new GregorianCalendar();
        }
    }
    
    public void setArligPremie( double beløp )
    {
        kunde.leggTilÅrligForsikringsPremie(beløp);
        this.arligPremie = beløp;
    }
    
    public void setTotalbeløp( double beløp )
    {
        this.totalbelop = beløp;
    }
    
    public void setSluttdato(GregorianCalendar d)
    {
        sluttdato = d;
    }
    
    // get metoder
    public double getArligPremie()
    {
        return arligPremie;
    }
    
    public double getTotalbeløp()
    {
        return totalbelop;
    }
    
    public int getForsikringsnummer()
    {
        return forsikringsnummer;
    }
    
    public void leggTilNøkkel( int i)
    {
        skademeldingsnøkkler.add(i);
    }
    
    public List<Integer> getNøkkelliste()
    {
        return skademeldingsnøkkler;
    }
    
    public Calendar getStartdato()
    {
        return startdato;
    }
    
    public Calendar getSluttdato()
    {
        return sluttdato;
    }
    
    @Override
    public String toString()
    {
        String utskrift;
        utskrift = "\nStartdato:" + sdf.format(startdato.getTime()) + 
                   //"\nSluttdato: " + sluttdato.toString() +   
                    "\nÅrlig Premie: " + arligPremie + 
                   "\nTotal beløp: " + totalbelop +
                   "\nForsikringsnummer: " + forsikringsnummer + 
                   "\nVilkår: \n" + vilkar;
        return utskrift;
    }
}//end of class
