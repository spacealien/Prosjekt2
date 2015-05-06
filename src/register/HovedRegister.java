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
import java.util.GregorianCalendar;
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
    private GregorianCalendar kalender;

    public HovedRegister( )
    {
        kalender = new GregorianCalendar();
        Kunde kunde_1 = kunderegister.finnKundeEtterPersonnummer("08206049937");
        Kunde kunde_2 = kunderegister.finnKundeEtterPersonnummer("01258446816");
        Kunde kunde_3 = kunderegister.finnKundeEtterPersonnummer("02029449964");
        
        Forsikring forsikring_1 = new Bilforsikring( kunde_1, 2000, "DH12345", 1600000, 
                                                    "Volvo", "XC90", "SUV", 340, 
                                                    2014, 30000, "Bilfører < 23 år", 0.50, 1, false, 
                                                    10000 );
        Forsikring forsikring_2 = new BatForsikring( kunde_1, 20000, "DK54321", 600000, 
                                                    "Tresfjord", "Ultra 360 FB", 
                                                    "Cabin cruiser", 120, 2014, false, 30 );
        Forsikring forsikring_3 = new Bilforsikring( kunde_2, 2000, "CD67890", 270000, 
                                                    "Volvo", "Sonett", "Småbil", 
                                                    800, 1968, 300000, "Bilfører < 23 år", 0.10, 1, 
                                                    true, 50000 );
        Forsikring forsikring_4 = new Husforsikring( kunde_2, 8000, "Fjollesvingen 32", 
                                                    1970, "Tremannsbolig", 
                                                    "Laftet tømmer", 
                                                    "Høy standard", 320, 
                                                    4500000, 1200000, false);
        Forsikring forsikring_5 = new Reiseforsikring( kunde_3, 4000, false, 0, 1, 
                                                    40000 );
        Forsikring forsikring_6 = new Fritidsboligforsikring( kunde_3, 4000, 
                                                    "Hardangervidda", 1899, 
                                                    "Hus/Hytte", "Tre", 
                                                    "Normal standard", 13, 
                                                    250000, 125000, true, false);
        
        forsikringsregister.leggTil(kunde_1, forsikring_1);
        forsikringsregister.leggTil(kunde_1, forsikring_2);
        forsikringsregister.leggTil(kunde_2, forsikring_3);
        forsikringsregister.leggTil(kunde_2, forsikring_4);
        forsikringsregister.leggTil(kunde_3, forsikring_5);
        forsikringsregister.leggTil(kunde_3, forsikring_6);
        
        Skademelding test_1 = new Skademelding( forsikring_1, new Date() , "Skadetype" , "Beskrivelse", 2000, 30000 );
        skademeldingsregister.leggTil(forsikring_1, test_1);
    }
    
    
    public List<Skademelding> getAlleKundensSkademeldinger( Kunde kunde )
    {
        return skademeldingsregister.getKundensSkademeldinger(forsikringsregister.getKundensForsikringer(kunde));
       
    }
    
    public List<Forsikring> getAlleKundensForsikringer(Kunde kunde)
    {
        return forsikringsregister.getKundensForsikringer(kunde);
    }
    
    public void sjekkTid()
    {
      
       //GregorianCalendar kalender = vindu.getKalender();
      for( Kunde kunde : kunderegister.alleKunder() )
        {
           List<Forsikring> forsikringsliste = getAlleKundensForsikringer( kunde );
           for( Forsikring forsikring : forsikringsliste )
            {
               
                    if(Math.abs(( kalender.getTime().getTime() - forsikring.getStartdato().getTime().getTime())) > (1000*60*60*24*365.25) ) 
                    {
                        forsikring.beregnPris();
                    }
               
            }
            
        }  
    }
    
    public List<Kunde> getAnsattKunde( Ansatt ansatt )
    {
        return kunderegister.getAnsattesKunder(ansatt);
    }
    
    public Kunde nyKunde( String fnavn, String enavn, String adr, String tlf, String email, String persnummer)
    {
        
        GregorianCalendar fd = new GregorianCalendar();
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
    
    public Forsikring nyBilForsikring( Kunde k, int e_andel, String registreringsnummer, int belop,
                                 String fabrikant, String modell, String type, int hestekrefter, 
                                 int arsmodell, int kilometerstand, String forer, double bonus, int antAr, 
                                 boolean garasje, int km)
    {
        Forsikring nyForsikring = new Bilforsikring( k, e_andel, registreringsnummer, belop,
                                                     fabrikant,modell, type, hestekrefter, arsmodell,
                                                     kilometerstand, forer, bonus, antAr, garasje, km );
        
        forsikringsregister.leggTil( k, nyForsikring);
        return nyForsikring;
    }
    
    public Forsikring nyBatForsikring( Kunde k, int e_andel, String registreringsnummer, int belop,
                                 String fabrikant, String modell, String type, int hestekrefter, 
                                 int arsmodell, 
                                 boolean vekter, int lengde)
    {
        Forsikring nyForsikring = new BatForsikring( k, e_andel, registreringsnummer, belop,
                                                     fabrikant,modell, type, hestekrefter, arsmodell, vekter, lengde);
        
        forsikringsregister.leggTil( k, nyForsikring);
        return nyForsikring;
    }
    
    public Forsikring nyHusforsikring( Kunde k, int e_andel, String adresse, int byggar, String bt, String mat, String stand,int kvm, int belopByg, int belopInn, boolean alarm)
    {
        Forsikring nyForsikring = new Husforsikring( k , e_andel, adresse,
                                                     byggar, bt, mat, stand, 
                                                     kvm, belopByg, belopInn, alarm );
        forsikringsregister.leggTil( k, nyForsikring);
        return nyForsikring;
    }
    
    public Forsikring nyFritidsboligforsikring( Kunde k, int e_andel, String hadresse, int byggar,
                                          String bt, String mat, String stand, int kvm,
                                          int belopByg, int belopInn, boolean alarm, boolean utl)
    {
        Forsikring nyForsikring = new Fritidsboligforsikring(k, e_andel, hadresse, byggar, bt, mat, stand, kvm, belopByg, belopInn, alarm, utl);
        forsikringsregister.leggTil( k, nyForsikring);
        return nyForsikring;
    }
    
    public Forsikring nyReiseforsikring(Kunde k, int e_andel, boolean forsorger, int antBarn, int belop, int sone)
    {
        Forsikring nyForsikring = new Reiseforsikring(k, e_andel, forsorger, antBarn, belop, sone);
        forsikringsregister.leggTil( k, nyForsikring);
        return nyForsikring;
    }
    
    public void nySkademelding( Forsikring forsikring, Date dato, String skadetype, String beskrivelse, int takseringsbelop, int erstatingsbelop )
    {
        Skademelding nySkademedling = new Skademelding( forsikring, dato, skadetype, beskrivelse, takseringsbelop, erstatingsbelop );
        skademeldingsregister.leggTil( forsikring, nySkademedling );
        if (forsikring instanceof Bilforsikring)
        {
            Bilforsikring bilforsikring = (Bilforsikring)forsikring;
            bilforsikring.korrigerBonusVedSkade();
        }
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
