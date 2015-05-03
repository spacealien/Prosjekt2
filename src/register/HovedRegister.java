/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;
import objekter.Ansatt;
import objekter.BatForsikring;
import objekter.Bilforsikring;
import objekter.Forsikring;
import objekter.Fritidsboligforsikring;
import objekter.Husforsikring;
import objekter.Kunde;
import objekter.Reiseforsikring;
import objekter.Skademelding;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class HovedRegister
{
    private Kunderegister kunderegister = new Kunderegister();
    private Forsikringsliste forsikringsregister = new Forsikringsliste();
    private SkademeldingRegister skademeldingsregister = new SkademeldingRegister();
    private Ansattregister ansattregister = new Ansattregister();

    public HovedRegister( )
    {
        

    }
    
    
    public Kunde nyKunde( String fnavn, String enavn, String adr, String tlf, String email, String persnummer)
    {
        
        Date fd = new Date();
        // sette fødselsdato fødselsdato.set();
        Kunde nyKunde = new Kunde( fnavn,  enavn,  adr, tlf, fd ,
                                   email, persnummer);
        
        return kunderegister.leggTil(nyKunde);
    }
    
    public Kunde finnKunde()
    {
        return null;
    }
    
    
    public List<Kunde> finnKundeMedNavn(String fornavn, String etternavn)
    {
        return kunderegister.finnKunderEtterNavn(fornavn, etternavn);
    }
    
    
    public List<Kunde> finnKundeMedEtternavn(String etternavn)
    {
        return kunderegister.finnKundeEtterEtternavn(etternavn);
    }
    
    
    public List<Kunde> finnKundeMedFornavn(String fornavn)
    {
        return kunderegister.finnKundeEtterFornavn(fornavn);
    }
    
    
    public Kunde finnKundeMedPersonnummer( String personnummer )
    {
        return  kunderegister.finnKundeEtterPersonnummer(personnummer);
    }
    
    public Kunderegister getKundeliste()
    {
        return kunderegister;
    }
    
    public SkademeldingRegister getSkademeldingsregister()
    {
        return skademeldingsregister;
    }
    
    public Forsikringsliste getForsikringrsliste()
    {
        return forsikringsregister;
    }
    
    public Ansattregister getAnsattregister()
    {
        return ansattregister;
    }

    public double  getInntekter()
    {
        double totalSum = 0.0;
        
        for( Kunde kunde : kunderegister.alleKunder() )
        {
            totalSum += kunde.getÅrligForsikringsPremie();
        }
        return totalSum;
    }
    
    public double getUtgifter()
    {
        double totalSum = 0.0;
        
        for( Skademelding skademelding : skademeldingsregister.alleSkademeldinger() )
        {
            totalSum += skademelding.getErstatningsbelop();
        }
        return totalSum;
    }
    
    public void getAntallForsikringerEtterType()
    {
        int bilForsikring = 0;
        int båtForsikring = 0;
        int reiseForsikring = 0;
        int innboForsikring = 0;
        int fritidsboligForsikring = 0;
        
        for( Forsikring forsikring: forsikringsregister.alleForsikringer() )
        {
            if( forsikring instanceof Bilforsikring )
                bilForsikring++;
            else if( forsikring instanceof BatForsikring )
                båtForsikring++;
            else if( forsikring instanceof Reiseforsikring )
                reiseForsikring++;
            else if( forsikring instanceof Husforsikring )
                båtForsikring++;
            else if( forsikring instanceof Fritidsboligforsikring )
                fritidsboligForsikring++;
        }
    }
    
    public Forsikring nyBilForsikring( Kunde k, String registreringsnummer,
                                 String fabrikant, String modell, String type, int hestekrefter, 
                                 int arsmodell, int kilometerstand, double bonus, 
                                 boolean garasje, int km)
    {
        Forsikring nyForsikring = new Bilforsikring( k, registreringsnummer,
                                                     fabrikant,modell, type, hestekrefter, arsmodell,
                                                     kilometerstand, bonus, garasje, km );
        
        forsikringsregister.leggTil( k, nyForsikring);
        return nyForsikring;
    }
    
    public Forsikring nyBatForsikring( Kunde k, String registreringsnummer,
                                 String fabrikant, String modell, String type, int hestekrefter, 
                                 int arsmodell, double bonus, 
                                 boolean vekter, int lengde)
    {
        Forsikring nyForsikring = new BatForsikring( k, registreringsnummer,
                                                     fabrikant,modell, type, hestekrefter, arsmodell, bonus, vekter, lengde);
        
        forsikringsregister.leggTil( k, nyForsikring);
        return nyForsikring;
    }
    
    public Forsikring nyHusforsikring( Kunde k, String adresse, int byggar, String bt, String mat, String stand,int kvm, int belopByg, int belopInn)
    {
        Forsikring nyForsikring = new Husforsikring( k , adresse,
                                                     byggar, bt, mat, stand, 
                                                     kvm, belopByg, belopInn );
        forsikringsregister.leggTil( k, nyForsikring);
        return nyForsikring;
    }
    
    public Forsikring nyFritidsboligforsikring( Kunde k, String hadresse, int byggar,
                                          String bt, String mat, String stand, int kvm,
                                          int belopByg, int belopInn, boolean utl)
    {
        Forsikring nyForsikring = new Fritidsboligforsikring(k, hadresse, byggar, bt, mat, stand, kvm, belopByg, belopInn, utl);
        forsikringsregister.leggTil( k, nyForsikring);
        return nyForsikring;
    }
    
    public void nyReiseforsikring()
    {
        //Forsikring nyForsikring = new Reiseforsirking();
        //forsikringsregister.leggTil( k, nyForsikring);
    }
    
    public void nySkademelding( Forsikring forsikring, Date dato, String skadetype, String beskrivelse, int takseringsbelop, int erstatingsbelop )
    {
        Skademelding nySkademedling = new Skademelding( forsikring, dato, skadetype, beskrivelse, takseringsbelop, erstatingsbelop );
        skademeldingsregister.leggTil( forsikring, nySkademedling );
    }
    
    public Ansatt login( String brukernavn, String passord )
    {
        for( Ansatt arbeidstaker: ansattregister.getAlleAnsatte())
        {
            if( arbeidstaker.getBrukernavn().equals(brukernavn) && arbeidstaker.getPassord().equals(passord))
                return arbeidstaker;
        }
        return null;
    }
    
    public void skrivTilFIl()
    {
        try( ObjectOutputStream utfil = new ObjectOutputStream(new FileOutputStream("")) )
        {
            
        }
        catch( IOException e )
        {
            
        }
    }
    
    public void lesFraFil()
    {
        try( ObjectInputStream innfil = new ObjectInputStream(
              new FileInputStream("")))
        {
            
        }
        catch( IOException e )
        {
            
        }
    }

    void nyBilForsikring(Kunde kunden, String text, String bilmerk, String biltyp, int parseInt, int parseInt0, int parseInt1, int i, int i0, boolean b, int kjorelengd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
