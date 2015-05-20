
package objekter;

/**
 *
 * @author Odd, Marthe. Sist endret 14.05.2015.
 */
public abstract class Kjoretoyforsikring extends Forsikring
{
    private Eier eier = null;
    private String registreringsnmmer;
    private String fabrikant;
    private String modell;
    private String kjøretøytype;
    private int hestekrefter;
    private int arsmodell;
    private int verdi;
    private static final long serialVersionUID = 51222222223L;
        
    public Kjoretoyforsikring( Kunde k, int e_andel, String vilkar, String registreringsnummer, int belop, String fabrikant, 
                               String modell, String type, int hestekrefter, int arsmodell)
    {
        super(k, e_andel, vilkar);
        this.registreringsnmmer = registreringsnummer;
        this.fabrikant = fabrikant;
        this.modell = modell;
        this.hestekrefter = hestekrefter;
        this.arsmodell = arsmodell;  
        this.kjøretøytype = type;
        verdi = belop;
    }
    
    public Person getEier() 
    {
        if (eier == null)
        {
            return super.getKunde();
        }
        else
        return eier;
    }
    
    // set metoder
    public void setEier( Eier e )
    {
        eier = e;
    }
    
    public void setRegistreringsnummer( String registreringsnmmer )
    {
        this.registreringsnmmer = registreringsnmmer;
    }
    public void setArsmodell(int a)
    {
        arsmodell = a;
    }
    
    public void setHestekrefter( int hestekrefter )
    {
        this.hestekrefter = hestekrefter;
    }
    public void setFabrikant(String f)
    {
        fabrikant = f;
    }
    public void setType(String t)
    {
        kjøretøytype = t;
    }
    public void setModell(String m)
    {
        modell = m;
    }
    public void setBelop(int b)
    {
        verdi = b;
    }
    
    // get metoder
    public String getRegistreringsnmmer() 
    {
        return registreringsnmmer;
    }

    public String getFabrikant() 
    {
        return fabrikant;
    }

    public String getModell() 
    {
        return modell;
    }
    
    public String getkjøretøytype()
    {
        return kjøretøytype;
    }

    public int getHestekrefter() 
    {
        return hestekrefter;
    }

    public int getArsmodell() 
    {
        return arsmodell;
    }

    public int getVerdi() 
    {
        return verdi;
    }
    
    
    @Override
    public String toString()
    { 
        String ut = super.toString();
        ut += "\nRegistreringsnummer: " +  registreringsnmmer + "\nFabrikant: " + fabrikant + "\nModell: " + modell + "\nHestekrefter: " + hestekrefter + "\nÅrsmodell: " + arsmodell;
        return ut;
    }
    
}
