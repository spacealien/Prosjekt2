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
public class Husforsikring extends Eiendomsforsikring
{
    private static final long serialVersionUID = 812341213213L;
    
    public Husforsikring( Kunde k, int e_andel, String vilkar, String hadresse, int byggar, String bt, String mat, String stand, int kvm, int belopByg,
                             int belopInn, boolean alarmen)
    {
        super( k, e_andel, vilkar, hadresse, byggar, bt, mat, stand, kvm, belopByg, belopInn, alarmen);
    }
    
    // set metoder

    
    // get metoder

    
    @Override
    public String toString()
    {
        String utskrift = super.toString();
        return utskrift;
    }
}//end of class
