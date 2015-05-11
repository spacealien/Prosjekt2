/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import gui.AnsattVindu;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import objekter.*;

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
    private AnsattVindu vindu;

    public HovedRegister(AnsattVindu v) 
    {
        vindu = v;
        
        kalender = new GregorianCalendar();
        Kunde kunde_1 = kunderegister.finnKundeEtterPersonnummer("08206049937");
        Kunde kunde_2 = kunderegister.finnKundeEtterPersonnummer("01258446816");
        Kunde kunde_3 = kunderegister.finnKundeEtterPersonnummer("02029449964");
        
        
        Forsikring forsikring_1 = new Bilforsikring( kunde_1, 4000, "Delkasko", "DH12345", 1600000, 
                                                    "Volvo", "XC90", "SUV", 340, 
                                                    2014, 30000, "Bilfører < 23 år", 0.50, 1, 
                                                    false, false, false, false, 12000 );
        Eier eier = new Eier("Hans", "Hansen", "Heiveien 3", "97612312");
        Kjoretoyforsikring forsikring1 = (Kjoretoyforsikring)forsikring_1;
        forsikring1.setEier(eier);
        Forsikring forsikring_2 = new BatForsikring( kunde_1, 20000, "Vilkår 1", "DK54321", 600000, 
                                                    "Tresfjord", "Ultra 360 FB", 
                                                    "Cabin cruiser", 120, 2014, false, 30 );
        Forsikring forsikring_3 = new Bilforsikring( kunde_2, 4000, "Ansvar", "CD67890", 270000, 
                                                    "Volvo", "Sonett", "Personbil", 
                                                    800, 1968, 300000, "Bilfører < 23 år", -0.10, 1, 
                                                    true, true, true, false, 8000 );
        Forsikring forsikring_4 = new Husforsikring( kunde_2, 8000, "Vilkår 1", "Fjollesvingen 32", 
                                                    1970, "Tremannsbolig", 
                                                    "Laftet tømmer", 
                                                    "Høy standard", 320, 
                                                    4500000, 1200000, false);
        Forsikring forsikring_5 = new Reiseforsikring( kunde_3, 4000, "Vilkår 1", false, 0, "Norden", 
                                                    40000 );
        Forsikring forsikring_6 = new Fritidsboligforsikring( kunde_3, 4000, "Vilkår 1", 
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
        
        System.out.println(forsikring_1.getArligPremie());
        
        
        Skademelding test_1 = new Skademelding( forsikring_1, new Date() , "Skadetype" , "Beskrivelse", 2000, 30000 );
        skademeldingsregister.leggTil(forsikring_1, test_1);
        sjekkTid();
        
    }
    
    
    public List<Skademelding> getAlleKundensSkademeldinger( Kunde kunde )
    {
        return skademeldingsregister.getKundensSkademeldinger(forsikringsregister.getKundensForsikringer(kunde));
    }
    
    public List<Forsikring> getAlleKundensForsikringer(Kunde kunde)
    {
        return forsikringsregister.getKundensForsikringer(kunde);
    }
    
    public final void sjekkTid()
    {
      
       //GregorianCalendar kalender = vindu.getKalender();
      for( Kunde kunde : kunderegister.alleKunder() )
        {
           List<Forsikring> forsikringsliste = getAlleKundensForsikringer( kunde );
           for( Forsikring forsikring : forsikringsliste )
            {
               
                    /*if(Math.abs(( kalender.getTime().getTime() - forsikring.getStartdato().getTime().getTime())) > (1000*60*60*24*365.25) ) 
                    {
                        forsikring.beregnPris();
                    }*/
            }
            
        }  
    }
    
    public List<Kunde> getAnsattKunde( Ansatt ansatt )
    {
        return kunderegister.getAnsattesKunder(ansatt);
    }
    
    public void nyKunde( Kunde nyKunde )
    {   
        kunderegister.leggTil(nyKunde);
        vindu.oppdaterTabell( kunderegister.alleKunder() );
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
    
    public int getUtgifter( Kunde kunde )
    {
        int sum = 0;
        
        List <Skademelding> skademeldinger = skademeldingsregister.getKundensSkademeldinger( forsikringsregister.getKundensForsikringer(kunde));
        for( Skademelding skademelding: skademeldinger )
            sum+= skademelding.getErstatningsbelop();
        
        return sum;
    }
    
    public double getInntekter( Kunde kunde )
    {
        double sum = 0 ;
        List<Forsikring> forsikringsliste = forsikringsregister.getKundensForsikringer(kunde);
        
        for(Forsikring forsikring : forsikringsliste )
            sum += forsikring.getArligPremie();
        
        return sum;
    }
    
    public double getInntekter()
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
    
    public void nyForsikring( Forsikring nyForsikring  )
    {
        forsikringsregister.leggTil( nyForsikring.getKunde(), nyForsikring);
        if(forsikringsregister.erTotalKunde(nyForsikring.getKunde()))
        {
            nyForsikring.getKunde().setTotalKunde(true);
            vindu.visInformasjon("Beskjed", nyForsikring.getKunde().getFornavn() + " " + nyForsikring.getKunde().getEtternavn() + "er nå totalkunde. ");
            vindu.oppdaterTabell(kunderegister.alleKunder());
        }
    }
    
    public void deaktiverForsikring( Integer forsikringsnummer )
    {
        Forsikring forsikring = forsikringsregister.getForsikring(forsikringsnummer);
        forsikring.setAktiver(false);
        if( forsikringsregister.erTotalKunde(forsikring.getKunde()) )
        {
            forsikring.getKunde().setTotalKunde(false);
        }
        
        vindu.visInformasjon("Beskjed", "Forsikringen er deaktivert. ");
    }
    
    public boolean nySkademelding( Skademelding nySkademelding )
    {
        skademeldingsregister.leggTil( nySkademelding.getForsikring(), nySkademelding );
        if ( nySkademelding.getForsikring() instanceof Bilforsikring )
        {
            Bilforsikring bilforsikring = (Bilforsikring) nySkademelding.getForsikring();
            bilforsikring.korrigerBonusVedSkade();
        }
        return true;
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
        try( ObjectOutputStream utfil = new ObjectOutputStream(
                new FileOutputStream("")) )
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
