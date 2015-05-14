/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

import java.io.Serializable;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public abstract class Eiendomsforsikring extends Forsikring implements Serializable
{
    private String husadresse;
    private boolean alarm;
    private int byggear;
    private String boligtype;
    private String materiale;
    private String standard;
    private int kvadratmeter;
    private int forsikringsbelopBygning;
    private int forsikringsbelopInnbo;
    private static final long serialVersionUID = 24512341235123L;
    
    public Eiendomsforsikring( Kunde k, int e_andel, String vilkar, String hadresse, int byggar, String bt, String mat, String stand, int kvm, int belopByg,
                             int belopInn, boolean alarmen )
    {
        super(k, e_andel, vilkar);
        husadresse = hadresse;
        alarm = alarmen;
        byggear = byggar;
        boligtype = bt;
        materiale = mat;
        standard = stand;
        kvadratmeter = kvm;
        forsikringsbelopBygning = belopByg;
        forsikringsbelopInnbo = belopInn;
    }
   
    // set metoder
    public void setAdresse(String a)
    {
        husadresse = a;
    }
    
    public void setBoligtype(String bt)
    {
        boligtype = bt;
    }
    
    public void setMateriale(String m) 
    {
        materiale = m;
    }
    
    public void setStandard(String s) 
    {
        standard = s;
    }
    
    public void setAlarm(boolean a)
    {
        alarm = a;
    }
    
    public void setKvadratmeter(int kv)
    {
        kvadratmeter = kv;
    }
    
    public void setByggeAr(int a)
    {
        byggear = a;
    }
    
    public void setForsikringsbelopBygning(int fbBygning) 
    {
        forsikringsbelopBygning = fbBygning;
    }

    public void setForsikringsbelopInnbo(int fbInnbo) 
    {
        forsikringsbelopInnbo = fbInnbo;
    }
    
    // get metoder
    public String getAdresse()
    {
        return husadresse;
    }
    public String getBoligtype()
    {
        return boligtype;
    }
    public boolean getAlarm()
    {
        return alarm;
    }

    public String getMateriale() 
    {
        return materiale;
    }
    
    public String getStandard() 
    {
        return standard;
    }
    
    public int getKvadratmeter()
    {
        return kvadratmeter;
    }
    
    public int getByggeAr()
    {
        return byggear;
    }
    
    public int getForsikringsbelopBygning()
    {
        return forsikringsbelopBygning;
    }
    
    public int getForsikringsbelopInnbo()
    {
        return forsikringsbelopInnbo;
    }
    
    @Override
    public String toString()
    {
        String utskrift = super.toString();
        utskrift += "\nAdresse: " + husadresse + "\nByggeår: " + byggear +
                    "\nBoligtype: " + boligtype + "\nStandard: " + standard +
                    "\nByggemateriale: " + materiale + "\nKvadratmeter: " +
                    kvadratmeter + "\nForsikringsbeløp for bygningen: " + 
                    forsikringsbelopBygning + "\nForsikringsbeløp for innbo: " + 
                    forsikringsbelopInnbo;
        return utskrift;
    }
    
}
