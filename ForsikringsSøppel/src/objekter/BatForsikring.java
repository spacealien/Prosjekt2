/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class BatForsikring extends Kjoretoyforsikring
{
    private double bonus;
    private boolean vekter;
        
    public BatForsikring(  Kunde k, String registreringsnummer,
                           String fabrikant, String modell, String type, int hestekrefter, 
                           int arsmodell, double bonus, boolean vekter)
    {
        super( k, registreringsnummer, fabrikant, modell, type, hestekrefter, arsmodell );
        this.bonus = bonus;
        this.vekter = vekter;
    }
    
    public void setBonus( double bonus )
    {
        this.bonus = bonus;
    }
    
    public void setGarasje( boolean bekreftelse )
    {
        this.vekter = bekreftelse;
    }
    
    /**
     * Denne metoden mottar kunde som parameter og gjør prisberegninger 
     * utifra kundeinformasjon.
     * @param kunde 
     */
    
    @Override
    public void beregnPris( Kunde kunde )
    {
        
    }
    
    public String toString()
    {
        return "Bonus: " + bonus + "Garasje: " + vekter + super.toString();
    }
}
