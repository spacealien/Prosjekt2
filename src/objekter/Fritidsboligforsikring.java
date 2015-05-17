/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

import java.util.Calendar;

/**
 *
 * @author Odd, Marthe
 */
public class Fritidsboligforsikring extends Eiendomsforsikring
{
    private boolean utleie;
    private String utleid;
    private int innevarendeAr = Calendar.getInstance().get(Calendar.YEAR);
    private static final long serialVersionUID = 91123455123L;
    
    
    
    public Fritidsboligforsikring(Kunde k, int e_andel, String vilkar, String hadresse, int byggar,
                                  String bt, String mat, String stand, int kvm,
                                  int belopByg, int belopInn, boolean alarmen, boolean utl)
    {
    super( k, e_andel, vilkar, hadresse, byggar, bt, mat, stand, kvm, belopByg, belopInn, alarmen);
    utleie = utl;
    }
    
    // set metoder
    public void setUtleie(boolean u)
    {
        utleie = u;
    }
    
    // get metoder
    public boolean getUtleie()
    {
        return utleie;
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



