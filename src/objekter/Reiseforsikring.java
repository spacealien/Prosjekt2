/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

/**
 * @author Odd, Thomas, Marthe
*/

public class Reiseforsikring extends Personligforsikring
{
    private String sone;
    private int belopet;
    private double soneBeregn;
    private double forsorgerpris = 1;
    private double premie;
    private double sum;
        
    public Reiseforsikring(Kunde k, int e_andel, String vilkar, boolean fs, int antallBarn, String son, int belop )
    {
        
        super(k, e_andel, vilkar, fs, antallBarn);
        sone = son;  
        belopet = belop;
    }

    public String getSone()
    {
        return sone;
    }

    public void setSone(String s)
    {
        sone = s;
    }

    public double getSoneBeregn()
    {
        return soneBeregn;
    }

    public void setSoneBeregn(int sb)
    {
        soneBeregn = soneBeregn;
    }

    public double getForsorgerpris()
    {
        return forsorgerpris;
    }

    public void setForsorgerpris(double fp)
    {
        forsorgerpris = fp;
    }

    public double getPremie()
    {
        return premie;
    }

    public void setPremie(double p)
    {
        premie = p;
    }

    public int getBelopet()
    {
        return belopet;
    }
    public void setBelopet(int b)
    {
        belopet = b;
    }
    
    @Override
    public String toString()
    {
        String utskrift = super.toString();
        utskrift += "\nForsikringsbeløp: " + belopet + "\nPremie: " + premie + "\nSone: " + sone;
        return utskrift;
    }
    
} 
    
