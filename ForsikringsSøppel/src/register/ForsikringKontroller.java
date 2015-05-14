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
import java.util.Calendar;
import java.util.List;
import objekter.Ansatt;
import objekter.BatForsikring;
import objekter.Bilforsikring;
import objekter.Eier;
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
public class ForsikringKontroller
{
    private Kunderegister kunderegister = new Kunderegister();
    private Forsikringsliste forsikringsregister = new Forsikringsliste();
    private SkademeldingRegister skademeldingsregister = new SkademeldingRegister();
    private Ansattregister ansattregister = new Ansattregister();

    public ForsikringKontroller( )
    {

    }
    
    public Kunde nyKunde( String fnavn, String enavn, String adr, String tlf, String email, String persnummer)
    {
        
        Calendar fd = Calendar.getInstance();
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
        return kunderegister.finnKunde(fornavn, etternavn);
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
    /**
    public Kunde finnKundeMedNavn( String fornavn, String etternavn )
    {
        retrun null;
    }
    * */
   
    
    public List<Kunde> getKundeliste()
    {
        return kunderegister.alleKunder();
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
    
    public void nyBilForsikring( Kunde k,  String vilkår, Eier eier, String registreringsnummer,
                                 String fabrikant, String modell, int hestekrefter, 
                                 int arsmodell, int kilometerstand, double bonus, 
                                 boolean garasje, int km)
    {
        Forsikring nyForsikring = new Bilforsikring( k, vilkår, registreringsnummer,
                                                     fabrikant,modell, hestekrefter, arsmodell,
                                                     kilometerstand, bonus, garasje, km );
        
        forsikringsregister.leggTil( k, nyForsikring);
    }
    
    
    public void nyHusforsikring( Kunde k, String vilkar, String adresse, int byggar, String bt, String mat, String stand,int kvm, int belopByg, int belopInn)
    {
        Forsikring nyForsikring = new Husforsikring( k, adresse, byggar, bt, mat, stand, kvm, belopByg, belopInn);
        forsikringsregister.leggTil( k, nyForsikring);
    }
    
    public void nyFritidsboligforsikring( Kunde k, String vilkar, String hadresse, int byggar,
                                          String bt, String mat, String stand, int kvm,
                                          int belopByg, int belopInn, boolean utl)
    {
        //Forsikring nyForsikring = new Fritidsboligforsikring();
        //forsikringsregister.leggTil( k, nyForsikring);
    }
    
    public void nyReiseforsikring()
    {
        //Forsikring nyForsikring = new Reiseforsirking();
        //forsikringsregister.leggTil( k, nyForsikring);
    }
    
    public void nySkademelding( Forsikring forsikring, String skadetype, String beskrivelse, int takseringsbelop, int erstatingsbelop )
    {
        Skademelding nySkademedling = new Skademelding( forsikring, skadetype, beskrivelse, takseringsbelop, erstatingsbelop );
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

}
