/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

/**
 *
 * @author Odd, Marthe, sist endret 15.04.2015
 */
public class Vitne extends Person
{
    private static final long serialVersionUID = 1111741235123L;

    public Vitne(String fnavn, String enavn, String adr, String tlf)
    {
        super(fnavn, enavn, adr, tlf);
    }
 
    @Override
    public String toString()
    {
        String utskriften = super.toString();  //kall på superklassens toString-metode
    return utskriften;
    }
}
