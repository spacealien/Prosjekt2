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
public abstract class Eiendomsforsikring extends Forsikring
{
    private String husadresse;
    private final int byggear;
    private String boligtype;
    private String materiale;
    private String standard;
    private int kvadratmeter;
    private int forsikringsbelopBygning;
    private int forsikringsbelopInnbo;
    
    public Eiendomsforsikring( Kunde k, String hadresse, int byggar, String bt, String mat, String stand, int kvm, int belopByg,
                             int belopInn )
    {
        super(k);
        husadresse = hadresse;
        byggear = byggar;
        boligtype = bt;
        materiale = mat;
        standard = stand;
        kvadratmeter = kvm;
        forsikringsbelopBygning = belopByg;
        forsikringsbelopInnbo = belopInn;
    }
   
    
    public String getHusadresse()
    {
        return husadresse;
    }
    
    public int getKvadratmeter()
    {
        return kvadratmeter;
    }
    
    public void setKvadratmeter(int kv)
    {
        kvadratmeter = kv;
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

    public void setForsikringsbelopBygning(int fbBygning) {
        forsikringsbelopBygning = fbBygning;
    }

    public void setForsikringsbelopInnbo(int fbInnbo) {
        forsikringsbelopInnbo = fbInnbo;
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
