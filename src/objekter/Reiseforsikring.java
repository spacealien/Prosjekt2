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
    private int sone;
    private int belopet;
    private double soneBeregn;
    private double forsorgerpris = 1;
    private double premie;
    private double sum;
        
    public Reiseforsikring(Kunde k, int e_andel, boolean fs, int antallBarn, int son, int belop )
    {
        
        super(k, e_andel, fs, antallBarn);
        sone = son;  
        belopet = belop;
    }

    public int getSone()
    {
        return sone;
    }

    public void setSone(int s)
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

    public int getBelopet() {
        return belopet;
    }
    
    @Override
    public void beregnPris()
    {
        if (super.isForsorger())
        {
           forsorgerpris = 1.5;//Multiplikator for familie -> Kan gjøres i Personligforsikring?
          
        }
            
            
        switch (sone)
        {
            case 1: soneBeregn = 1;//Hva man skal gange prisen med
                    break;
            case 2: soneBeregn = 1.10;//Hva man skal gange prisen med
                    break;
            case 3: soneBeregn = 1.20;//Hva man skal gange prisen med
                    break;
        }
        premie = belopet*0.20*soneBeregn*forsorgerpris;
    }
    
    @Override
    public String toString()
    {
        String utskrift = super.toString();
        utskrift += "\nForsikringsbeløp: " + belopet + "\nPremie: " + premie + "\nSone: " + sone;
        return utskrift;
    }
    
} 
    
