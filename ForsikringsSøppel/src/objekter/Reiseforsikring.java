/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

/**
 * @author Marthejansonskogen
*/

public class Reiseforsikring extends Personligforsikring
{
    private int sone;
    private int soneBeregn;
    private double forsorgerpris = 1;
    private double premie;
    
    
    
    public Reiseforsikring(Kunde k, boolean fs, int antallBarn, int son )
    {
        
        super(k, fs, antallBarn);
        sone = son;                
    }

    public int getSone()
    {
        return sone;
    }

    public void setSone(int s)
    {
        sone = s;
    }

    public int getSoneBeregn()
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
    
    void beregnPris(Kunde kunde)
    {
        if (super.isForsorger())
        {
           forsorgerpris = 1.5;//Multiplikator for familie -> Kan gjøres i Personligforsikring?
          
        }
            
            
        switch (sone)
        {
            case 1: soneBeregn = 1;//Hva man skal gange prisen med
                    break;
            case 2: soneBeregn = 2;//Hva man skal gange prisen med
                    break;
            case 3: soneBeregn = 3;//Hva man skal gange prisen med
                    break;
        }
        //premie =
    }
    
    public String toString()
    {
        String utskrift = super.toString();
        utskrift += "\nPremie: " + premie;
        return utskrift;
    }
    
} 
    
