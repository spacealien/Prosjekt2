
package objekter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Marthe
 */
public class Inntekt
{
    private final GregorianCalendar dato;
    private final double sum;
    private final Forsikring forsikring;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    
    public Inntekt(Date datoen, double summen, Forsikring forsikringen)
    {
        dato = new GregorianCalendar();
        dato.setTime(datoen);
        sum = summen;
        forsikring = forsikringen;
    }
    //Get metoder
    public double getSum()
    {
        return sum;
    }

    public GregorianCalendar getDato()
    {
        return dato;
    }
    
    public Forsikring getForsikring()
    {
        return forsikring;
    }
    
    
    
    @Override
    public String toString()
    {
        return "\nDato for innbetaling: " + sdf.format(dato.getTime()) + "\nBeløp: " + sum + "\nPremie fra forsikring: " + forsikring.getForsikringsnummer();
    }
}
