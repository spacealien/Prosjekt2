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
public abstract class Kjoretoyforsikring extends Forsikring
{
    private Eier eier;
    private String registreringsnmmer;
    private final String fabrikant;
    private final String modell;
    private final String type;
    private int hestekrefter;
    private final int arsmodell;
    private int belop;
        
    public Kjoretoyforsikring( Kunde k, int e_andel, String registreringsnummer, int belop, String fabrikant, 
                               String modell, String type, int hestekrefter, int arsmodell)
    {
        super(k, e_andel);
        this.registreringsnmmer = registreringsnummer;
        this.fabrikant = fabrikant;
        this.modell = modell;
        this.hestekrefter = hestekrefter;
        this.arsmodell = arsmodell;  
        this.type = type;
    }
    

    

    public Eier getEier() 
    {
        return eier;
    }

    public String getRegistreringsnmmer() 
    {
        return registreringsnmmer;
    }

    public String getFabrikant() 
    {
        return fabrikant;
    }

    public String getModell() 
    {
        return modell;
    }

    public int getHestekrefter() 
    {
        return hestekrefter;
    }

    public int getArsmodell() 
    {
        return arsmodell;
    }

    public int getBelop() 
    {
        return belop;
    }
    
    public void setEier( Eier e )
    {
        eier = e;
    }
    
    public void setRegistreringsnummer( String registreringsnmmer )
    {
        this.registreringsnmmer = registreringsnmmer;
    }
    
    public void setHestekrefter( int hestekrefter )
    {
        this.hestekrefter = hestekrefter;
    }

    @Override
    public void beregnPris()
    {
        
    }
    @Override
    public String toString()
    { 
        String ut = super.toString();
        ut += "\nRegistreringsnummer: " +  registreringsnmmer + "\nFabrikant: " + fabrikant + "\nModell: " + modell + "\nHestekrefter: " + hestekrefter + "\nÅrsmodell: " + arsmodell;
        return ut;
    }
    
}
