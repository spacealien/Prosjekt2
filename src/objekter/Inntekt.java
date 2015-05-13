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
 * @author Marthe
 */
public class Inntekt
{
    private final Date dato;
    private final double sum;
    private final Forsikring forsikring;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    
    public Inntekt(Date datoen, double summen, Forsikring forsikringen)
    {
        dato = datoen;
        sum = summen;
        forsikring = forsikringen;
    }

    public Date getDato()
    {
        return dato;
    }

    public double getSum()
    {
        return sum;
    }

    public Forsikring getForsikring()
    {
        return forsikring;
    }
    
    
    
    @Override
    public String toString()
    {
        return "\nDato for innbetaling: " + sdf.format(dato) + "\nBeløp: " + sum + "\nPremie fra forsikring: " + forsikring.getForsikringsnummer();
    }
}
