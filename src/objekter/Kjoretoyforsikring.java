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
    private String fabrikant;
    private String modell;
    private String type;
    private int hestekrefter;
    private int arsmodell;
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
    

    

    public Person getEier() 
    {
        if (eier == null)
        {
            return super.getKunde();
        }
        else
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
    
    public String getType()
    {
        return type;
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
    public void setArsmodell(int a)
    {
        arsmodell = a;
    }
    
    public void setHestekrefter( int hestekrefter )
    {
        this.hestekrefter = hestekrefter;
    }
    public void setFabrikant(String f)
    {
        fabrikant = f;
    }
    public void setType(String t)
    {
        type = t;
    }
    public void setModell(String m)
    {
        modell = m;
    }
    public void setBelop(int b)
    {
        belop = b;
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
