/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

/**
 *
 * @author Odd, Marthe
 */
public abstract class Personligforsikring extends Forsikring
{
    private boolean forsorger;
    private int antBarn;
    private String forsorgeren;
    private static final long serialVersionUID = 77741235123L;
    
    
    public Personligforsikring(Kunde k, int e_andel, String vilkar, boolean fs, int antallBarn)
    {
        super( k, e_andel, vilkar );
        forsorger = fs;
        antBarn = antallBarn;
    }

    public boolean isForsorger()
    {
        return forsorger;
    }
    
    // set metoder
    public void setForsorger(boolean f)
    {
        forsorger = f;
    }
    
    public void setAntBarn(int a)
    {
        antBarn = a;
    }
    
    // get metoder
    public int getAntBarn()
    {
        return antBarn;
    }
    
    @Override
    public String toString()
    {
        if (forsorger)
            forsorgeren = "Ja";
        else
            forsorgeren = "Nei";
        
        String utskrift = super.toString();
        utskrift += "\nForsørger: " + forsorgeren + "\nAntall barn: " + antBarn;
        return utskrift;
    }
    
}
