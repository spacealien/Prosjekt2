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
public class Fritidsboligforsikring extends Eiendomsforsikring
{
    private boolean utleie;
    private String utleid = "";
    public Fritidsboligforsikring(Kunde k, String hadresse, int byggar,
                                  String bt, String mat, String stand, int kvm,
                                  int belopByg, int belopInn, boolean utl)
    {
    super( k, hadresse, byggar, bt, mat, stand, kvm, belopByg, belopInn);
    utleie = utl;
    }
    
    public void beregnPris( Kunde kunde)
    {
        
    }
    
    
    
    @Override
    public String toString()
    {
        if (utleie)
            utleid = "Ja";
        else
            utleid = "Nei";
        
        String utskrift = super.toString();
        utskrift += "\nUtleid: " + utleid;
        return utskrift;
    }
}//end of class



