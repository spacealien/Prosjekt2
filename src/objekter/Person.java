
package objekter;

import java.io.Serializable;

/**
 *
 * @author Odd, Marthe. Sist endret 15.05.2015.
 */
public abstract class Person implements Serializable
{
    private String fornavn;
    private String etternavn;
    private String adresse;
    private String telefonnummer;
    private static final long serialVersionUID = 123512351234L;
    
    public Person(String fnavn, String enavn, String adr, String tlf)
    {
        fornavn = fnavn;
        etternavn = enavn;
        telefonnummer = tlf;
        adresse = adr;
    } // slutt på konstruktør

    // set metoder
    public void setFornavn(String fnavn)
    {
        this.fornavn = fnavn;
    }

    public void setEtternavn(String enavn)
    {
        this.etternavn = enavn;
    }

    public void setAdresse(String adr)
    {
        this.adresse = adr;
    }

    public void setTlfnr(String tlfnr)
    {
        this.telefonnummer = tlfnr;
    }
    
    // get metoder
    public String getFornavn()
    {
        return fornavn;
    }

    public String getEtternavn()
    {
        return etternavn;
    }

    public String getAdresse()
    {
        return adresse;
    }

    public String getTlfnr() 
    {
        return telefonnummer;
    }
    
    @Override
    public String toString()
   {
      String utskrift = "\nFornavn: " + fornavn + "\nEtternavn: " + etternavn +
                "\nAdresse: " + adresse + "\nTelefonnummer: " + telefonnummer;
      return utskrift;
   }
} // slutt på klasse.
