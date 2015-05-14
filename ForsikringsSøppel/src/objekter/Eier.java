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
public class Eier extends Person
{
    public Eier(String fnavn, String enavn, String adr, String tlf)
    {
        super(fnavn, enavn, adr, tlf);
    }
    
    
 
     @Override
    public String toString()
    {
       String utskrift = super.toString();  //kall på superklassens toString-metode
       return utskrift;
    }
}
