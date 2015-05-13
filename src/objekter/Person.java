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
public abstract class Person implements Serializable
{
    private String fornavn;
    private String etternavn;
    private String adresse;
    private String telefonnummer;
    
    public Person(String fnavn, String enavn, String adr, String tlf)
    {
        fornavn = fnavn;
        etternavn = enavn;
        telefonnummer = tlf;
        adresse = adr;
         
    }

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

    public String getTlfnr() {
        return telefonnummer;
    }

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

    @Override
    public String toString()
   {
      String utskrift = "\nFornavn: " + fornavn + "\nEtternavn: " + etternavn +
                "\nAdresse: " + adresse + "\nTelefonnummer: " + telefonnummer;
      return utskrift;
   }
}
