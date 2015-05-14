
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private List<Inntekt> innbetalinger = new ArrayList<>();
    private Calendar kalender;
    private AnsattVindu vindu;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public HovedRegister(AnsattVindu v) 
    {
        vindu = v;  
        kalender = Calendar.getInstance();  
        lesFraFil();

        //sjekkTid2();
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
    
    public final void sjekkTid2()
    {
        Calendar ettÅrSiden = Calendar.getInstance();
        ettÅrSiden.set(kalender.get(Calendar.YEAR) - 1, kalender.get(Calendar.MONTH), kalender.get(Calendar.DATE));
        System.out.print(sdf.format(ettÅrSiden.getTime()));
        if(!getForsikringrsliste().alleForsikringer().isEmpty())
        {
            for (Forsikring forsikring : getForsikringrsliste().alleForsikringer())
            {
                if(forsikring.getSistBetalt().before(ettÅrSiden.getTime()));
                {
                    if(forsikring.getForsikringsType().equals("Bilforsikring"))
                    {
                        Bilforsikring bilforsikring = (Bilforsikring)forsikring;
                        double bonusFør = bilforsikring.getBonus();
                        double originalPris = bilforsikring.getArligPremie() / bonusFør * 100;
                        bilforsikring.korrigerArligBonus();
                        bilforsikring.setArligPremie((originalPris * (100-bilforsikring.getBonus())));
                        innbetalinger.add(new Inntekt(kalender.getTime(), forsikring.getArligPremie(), forsikring));
                        forsikring.setSistBetalt(kalender.getTime());
                    }
                    else
                    {
                        innbetalinger.add(new Inntekt(kalender.getTime(), forsikring.getArligPremie(), forsikring));
                        forsikring.setSistBetalt(kalender.getTime());
                    }
                }
            }
        }
    }
           
    
    
    public List<Inntekt> getAlleInntekter()
    {
        return innbetalinger;
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
    
    public List<Kunde> getAlleKunderMedForsikring(String f)
    {
        List<Kunde> kunderMedForsikring = new ArrayList<>();
        for (Forsikring forsikring : forsikringsregister.alleForsikringer())
        {
            if (forsikring.getForsikringsType().equals(f))
            {
                if (!kunderMedForsikring.contains(forsikring.getKunde()))
                kunderMedForsikring.add(forsikring.getKunde());
            }
        }
       
        return kunderMedForsikring;
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
    
    public double getNåværendeInntjening( Kunde kunde )
    {
        double sum = 0.0;
        List<Forsikring> kundeForsikringer = forsikringsregister.getKundensForsikringer(kunde);
        double prisPrMåned;
        
        for(Forsikring forsikring :  kundeForsikringer)
        {    
            Calendar startdato = forsikring.getStartdato();
            Calendar sluttdato = forsikring.getSluttdato();
            int differanseÅr = 0;
            int differanseMnd = 0;
            if( forsikring.getSluttdato() != null)
            {
                differanseÅr = sluttdato.get( Calendar.YEAR ) - startdato.get( Calendar.YEAR );
                differanseMnd = sluttdato.get( Calendar.MONTH ) - startdato.get( Calendar.MONTH );
            }
            prisPrMåned= forsikring.getTotalbelop() / 12;
            differanseMnd += differanseÅr * 12;
            sum += differanseMnd * prisPrMåned;
        }
        return sum;
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
        Date dato = new Date();
        System.out.print(sdf.format(dato));
        if(forsikringsregister.antallAktiveForsikringer(nyForsikring.getKunde()).size() >= 3)
        {
            nyForsikring.getKunde().setTotalKunde(true);
            innbetalinger.add(new Inntekt(dato, (nyForsikring.getArligPremie() * 0.9), nyForsikring));
            if(forsikringsregister.antallAktiveForsikringer(nyForsikring.getKunde()).size() == 3)
                vindu.visInformasjon("Beskjed", nyForsikring.getKunde().getFornavn() + " " + nyForsikring.getKunde().getEtternavn() + " er nå totalkunde. ");
            
            vindu.oppdaterTabell(kunderegister.alleKunder());
            skrivTilFil();
        }
        else
        {
            innbetalinger.add(new Inntekt(dato, nyForsikring.getArligPremie(), nyForsikring));
        }
    }
    
    public void deaktiverForsikring( Integer forsikringsnummer )
    {
        Forsikring forsikring = forsikringsregister.getForsikring(forsikringsnummer);
        if( forsikringsregister.antallAktiveForsikringer(forsikring.getKunde()).size() == 3)
        {
            forsikring.getKunde().setTotalKunde(false);
        }
        forsikring.setAktiver(false);
        vindu.oppdaterTabell( kunderegister.alleKunder() );
        vindu.visInformasjon("Beskjed", "Forsikringen er deaktivert. ");
        skrivTilFil();
    }
    
    public boolean nySkademelding( Skademelding nySkademelding )
    {
        skademeldingsregister.leggTil( nySkademelding.getForsikring(), nySkademelding );
        if ( nySkademelding.getForsikring() instanceof Bilforsikring )
        {
            Bilforsikring bilforsikring = (Bilforsikring) nySkademelding.getForsikring();
            bilforsikring.korrigerBonusVedSkade();
        }
        skrivTilFil();
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
    
    public void skrivTilFil()
    {
        try( ObjectOutputStream utfil = new ObjectOutputStream(
                new FileOutputStream("Data\\ForsikringsData.txt")) )
        {
            utfil.writeObject(kunderegister);
            utfil.writeObject(forsikringsregister);
            utfil.writeObject(skademeldingsregister);
            utfil.writeObject(ansattregister);
            utfil.writeObject(innbetalinger);
            utfil.writeInt(Forsikring.getLøpenummer());
            utfil.writeInt(Skademelding.getLøpenummer());
            utfil.close();
        }
        catch( IOException e )
        {
            
        }
    }
    
    public void lesFraFil()
    {
        try( ObjectInputStream innfil = new ObjectInputStream(
              new FileInputStream("Data\\ForsikringsData.txt")))
        {
            kunderegister = (Kunderegister)innfil.readObject();
            forsikringsregister = (Forsikringsliste) innfil.readObject();
            skademeldingsregister = (SkademeldingRegister) innfil.readObject();
            ansattregister = (Ansattregister) innfil.readObject();
            innbetalinger = (List<Inntekt>) innfil.readObject();
            Forsikring.setLøpenummer(innfil.readInt());
            Skademelding.setLøpenummer(innfil.readInt());
            innfil.close();
        }
        catch( IOException | ClassNotFoundException e )
        {
            
        }
    }
}


