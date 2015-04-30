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
public class Bilforsikring extends Kjoretoyforsikring
{
    private double bonus;
    private boolean garasje;
    private int maxKjorelengd;
    
    public Bilforsikring(  Kunde k, String registreringsnummer,
                           String fabrikant, String modell, String type, int hestekrefter, 
                           int arsmodell, int kilometerstand, double bonus, boolean garasje, int km )
    {
        super( k, registreringsnummer, fabrikant, modell, type, hestekrefter, arsmodell);
        this.bonus = bonus;
        this.garasje = garasje;
        maxKjorelengd = km;
    }
    
    public void setBonus( double bonus )
    {
        this.bonus = bonus;
    }
    
    public void setGarasje( boolean bekreftelse )
    {
        this.garasje = bekreftelse;
    }
    
    /*public void setMaxKjorelengde( MaxKjorelengde km)
    {
        maxKjorelengd = km;
    } */
    
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
        String g;
        if (garasje)
            g = "Ja";
        else
            g = "Nei";
        
        String ut = super.toString();
        ut += "\nMax kjørelengde: " + maxKjorelengd + "\nBonus: " + bonus + "\nGarasje: " + g;
        return ut;
    }
    
    public enum MaxKjorelengde 
    { 
        BRONZE(3000),
        SILVER(6000),
        GOLD(12000),
        PLATINUM(16000);
        
        private final int grense;
        
        private MaxKjorelengde(int km)
        {
            this.grense = km;
        }
        
        public int getKM()
        {
            return grense;
        }
    }; 
}
