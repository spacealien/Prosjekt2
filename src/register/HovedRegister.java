
package register;

import gui.AnsattVindu;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javax.swing.JOptionPane;
import objekter.*;

/*
 * Klassen har som hensikt å samle alle registerene i en klasse for å gjøre
 * det enkelt å alle registerne i en fil. Alternativet ville vært å hatt metodene
 * i denne klassen i AnsattVindu klassen. Vi har valgt å gjøre det på denne måten
 * for å prøve å gjøre AnsattVindu klassen så kort og ryddig som mulig.
 * 
 * Vi ønsket å holde AnsattVinduet så fri for metoder som ikke er direkte relatert
 * til det grafiske grensesnittet.
 *
 * @author Odd, Marthe. Sist endret 15.05.2015.
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
    }
    
    // sier opp kundeforholdet.
    public boolean siOppKundeforhold( Kunde kunde )
    {
        if( JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Er du sikker på at du ønsker å si opp kundeforholdet",
                                                                            "Bekreftelse", JOptionPane.YES_NO_OPTION ))
        {
            kunde.setAktiv(false);
            List<Forsikring> kundeForsikringer = getAlleKundensForsikringer(kunde);
        
            for( Forsikring forsikring : kundeForsikringer)
                forsikring.setAktiver(false);
        
            vindu.visInformasjon("Beskjed", kunde.getFornavn() + " " + kunde.getEtternavn() + " er deaktivert.");
            vindu.oppdaterTabell(getKundeliste().alleKunder());
            return true;
        }
        return false;
    }
    
    
    //Returnerer alle skademelding som hører til kunden som blir send med som parameter.
    public List<Skademelding> getAlleKundensSkademeldinger( Kunde kunde )
    {
        return skademeldingsregister.getKundensSkademeldinger(forsikringsregister.getKundensForsikringer(kunde));
    }
    
    //returnerer alle forsikringer som hører til kunden som blir send med som parameter.
    public List<Forsikring> getAlleKundensForsikringer(Kunde kunde)
    {
        return forsikringsregister.getKundensForsikringer(kunde);
    }
    
    /*Metodens hensikt er å registrere årlige forsikringspremieinntekter for 
    aktive forsikringer, samt oppdatere bonusen til bilforsikringer*/
    public final void sjekkTid2()
    {
        Calendar ettÅrSiden = Calendar.getInstance();
        ettÅrSiden.set(kalender.get(Calendar.YEAR) - 1, kalender.get(Calendar.MONTH),
                kalender.get(Calendar.DATE));
        if(!getForsikringrsliste().alleForsikringer().isEmpty())
        {
            for (Forsikring forsikring : getForsikringrsliste().alleForsikringer())
            {
                if(forsikring.erAktiv() && 
                        forsikring.getSistBetalt().before(ettÅrSiden.getTime()));
                {
                    if(forsikring.getForsikringsType().equals("Bilforsikring"))
                    {
                        Bilforsikring bilforsikring = (Bilforsikring)forsikring;
                        double bonusFør = bilforsikring.getBonus();
                        double originalPris = bilforsikring.getArligPremie() /
                                bonusFør * 100;
                        bilforsikring.korrigerArligBonus();
                        bilforsikring.setArligPremie(originalPris * 
                                (100-bilforsikring.getBonus()));
                        innbetalinger.add(new Inntekt(kalender.getTime(),
                                forsikring.getArligPremie(), forsikring));
                        forsikring.setSistBetalt(kalender.getTime());
                    }
                    else
                    {
                        innbetalinger.add(new Inntekt(kalender.getTime(), 
                                forsikring.getArligPremie(), forsikring));
                        forsikring.setSistBetalt(kalender.getTime());
                    }
                }
            }
        }
    }
    
    //Returnerer en liste med alle inntektsobjektene
    public List<Inntekt> getAlleInntekter()
    {
        return innbetalinger;
    }
    
    // returner alle kunder som har blitt registrert av ansatt som blir oppgitt i parameter
    public List<Kunde> getAnsattKunde( Ansatt ansatt )
    {
        return kunderegister.getAnsattesKunder(ansatt);
    }
    
    /*Tar i mot et kundeobjekt som parameter, legger til denne nye kunden i 
    registeret og oppdaterer deretter kundetabellen med denne nye kunden*/
    public void nyKunde( Kunde nyKunde )
    {   
        kunderegister.leggTil(nyKunde);
        vindu.oppdaterTabell( kunderegister.alleKunder() );
        skrivTilFil();
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
    
    // returnerer kunderegisteret.
    public Kunderegister getKundeliste()
    {
        return kunderegister;
    }
    
    
    
    /*  Metoden returnerer kunder som har en gitt forsikringstype.
     *  Parameteren forsikringstype motar en String som representerer forsikringstypen.
     */
    
    public List<Kunde> getAlleKunderMedForsikring(String forsikringstype)
    {
        List<Kunde> kunderMedForsikring = new ArrayList<>();
        for (Forsikring forsikring : forsikringsregister.alleForsikringer())
        {
            if (forsikring.getForsikringsType().equals(forsikringstype))
            {
                if (!kunderMedForsikring.contains(forsikring.getKunde()))
                kunderMedForsikring.add(forsikring.getKunde());
            }
        }
        return kunderMedForsikring;
    }
    
    // Returnerer skademeldingsregisteret.
    public SkademeldingRegister getSkademeldingsregister()
    {
        return skademeldingsregister;
    }
    
    // Returnerer forsikringsregisteret.
    public Forsikringsliste getForsikringrsliste()
    {
        return forsikringsregister;
    }
    
    // Returnerer ansattregisteret.
    public Ansattregister getAnsattregister()
    {
        return ansattregister;
    }
    
    /*
     * Metoden teller antall måneder en forsikring har vært aktiv,
     * så ganges antall måneder med forsikringens månedlig pris.
     * og returnerer prisen.
    */
    
    public double getNåværendeInntjening( Kunde kunde )
    {
        double sum = 0.0;
        List<Forsikring> kundeForsikringer = forsikringsregister.getKundensForsikringer(kunde);
        double prisPrMåned;
        int differanseMnd = 0;
        int differanseÅr;
        
        for(Forsikring forsikring :  kundeForsikringer)
        {    
            if( forsikring.getSluttdato() == null)
            {
                Calendar startdato = forsikring.getStartdato();
                differanseÅr = Calendar.getInstance().get(Calendar.YEAR) - startdato.get(Calendar.YEAR);
                differanseMnd = differanseÅr * 12 + Calendar.getInstance().get(Calendar.MONTH) - startdato.get(Calendar.MONTH);  
            }
            else
            {
                Calendar startDato = forsikring.getStartdato();
                differanseÅr = forsikring.getSluttdato().get(Calendar.YEAR) - startDato.get(Calendar.YEAR);
                differanseMnd = differanseÅr * 12 + forsikring.getSluttdato().get(Calendar.MONTH) - startDato.get(Calendar.MONTH);
                
            }
            prisPrMåned = forsikring.getArligPremie() / 12;
            sum += differanseMnd * prisPrMåned;
        }
        return sum;
    }
    
    /*
     * Henter total sum for utgifter forbundet med kunden som blir send med som parameter. 
     * returnerer en int, utgifter forbundet med en kunde.
     */
    public int getUtgifter( Kunde kunde )
    {
        int sum = 0;
        
        List <Skademelding> skademeldinger = skademeldingsregister.getKundensSkademeldinger( forsikringsregister.getKundensForsikringer(kunde));
        for( Skademelding skademelding: skademeldinger )
            sum+= skademelding.getErstatningsbelop();
        
        return sum;
    }
    
    /*
     * Returnerer totale summen av av alle aktive forsikrings premier til en kunde.
     * motar kunden som parameter. 
     */
    public double getInntekter( Kunde kunde )
    {
        double sum = 0 ;
        List<Forsikring> forsikringsliste = forsikringsregister.getKundensForsikringer(kunde);
        
        for(Forsikring forsikring : forsikringsliste )
            if( forsikring.erAktiv())
                sum += forsikring.getArligPremie();
        
        return sum;
    }
    
    
    // returnerer total erstatningsbeløp for alle skademeldinger som er registrert.
    public double getUtgifter()
    {
        double totalSum = 0.0;
        
        for( Skademelding skademelding : skademeldingsregister.alleSkademeldinger() )
        {
            totalSum += skademelding.getErstatningsbelop();
        }
        return totalSum;
    }
    
    /*
     * Legger inn ny forsikring i forsikringsregisteret. Kjøerer en test for å 
     * se hvor mange unike aktive forsikringer en kunde har, dersom kunden har mer
     * enn tre aktive forsikringer settes kunden som totalkunde.
     */
    
    public void nyForsikring( Forsikring nyForsikring  )
    {
        forsikringsregister.leggTil( nyForsikring.getKunde(), nyForsikring);
        Date dato = new Date();
        
        if(forsikringsregister.antallUnikeAktiveForsikringer(nyForsikring.getKunde()).size() >= 3)
        {
            nyForsikring.getKunde().setTotalKunde(true);
            innbetalinger.add(new Inntekt(dato, (nyForsikring.getArligPremie() * 0.9), nyForsikring));
           //if(forsikringsregister.antallUnikeAktiveForsikringer(nyForsikring.getKunde()).size() == 3)
               //vindu.visInformasjon("Beskjed", nyForsikring.getKunde().getFornavn() + " " + nyForsikring.getKunde().getEtternavn() + " er nå totalkunde. ");
            
            skrivTilFil();
        }
        else
        {
            innbetalinger.add(new Inntekt(dato, nyForsikring.getArligPremie(), nyForsikring));
        }
    }
    
    
    /*
     * Metoden sier opp en forsikring og tester om kunden mister status som totalkunde dersom
     * forsikringen deaktiveres.
     * Motar forsikringsnummer som parameter.
     */
    
    public void deaktiverForsikring( Integer forsikringsnummer )
    {
        Forsikring forsikring = forsikringsregister.getForsikring(forsikringsnummer);
        if( forsikringsregister.antallUnikeAktiveForsikringer(forsikring.getKunde()).size() == 3)
        {
            forsikring.getKunde().setTotalKunde(false);
        }
        forsikring.setAktiver(false);
        vindu.oppdaterTabell( kunderegister.alleKunder() );
        vindu.visInformasjon("Beskjed", "Forsikringen er deaktivert. ");
        skrivTilFil();
    }
    
    /*
     * Legger inn ny skademelding i skademeldingsregisteret, dersom 
     * skademeldingen tilhører en bilforsikring, korrigeres bonusen automatisk.
     * Motar den nye skademeldingen i parameter.
     */
    
    public boolean nySkademelding( Skademelding nySkademelding )
    {
        if(nySkademelding.getForsikring().erAktiv())
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
        else
            return false;
    }
    
    // returnerer ansatt som har matchede brukernavn og passord. Brukes i LoginVindu.
    public Ansatt login( String brukernavn, String passord )
    {
        for( Ansatt arbeidstaker: ansattregister.getAlleAnsatte())
        {
            if( arbeidstaker.getBrukernavn().equals(brukernavn) && arbeidstaker.getPassord().equals(passord))
                return arbeidstaker;
        }
        return null;
    }
    
    // skriver data til fil.
    public void skrivTilFil()
    {
        String mappeSti = "Data\\";
        
        if(System.getProperty("os.name").toLowerCase().contains("mac") || System.getProperty("os.name").toLowerCase().contains("lin"))
            mappeSti = "Data/";
        
        try( ObjectOutputStream utfil = new ObjectOutputStream(
                new FileOutputStream(mappeSti + "ForsikringsData.dta")) )
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
        catch( FileNotFoundException e )
        {
            vindu.visFeilmelding("Feilmelding", "Finner ikke fil. ");
        }
        catch( IOException e )
        {
            
        }
    }
    
    // leser data fra fil.
    public void lesFraFil()
    {
        String mappeSti = "Data\\";
        
        if(System.getProperty("os.name").toLowerCase().contains("mac") || System.getProperty("os.name").toLowerCase().contains("lin"))
            mappeSti = "Data/";
        
        try( ObjectInputStream innfil = new ObjectInputStream(
              new FileInputStream(mappeSti +"ForsikringsData.dta")))
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
        catch( FileNotFoundException e)
        {
            vindu.visFeilmelding("Feilmelding", "Finner ikke fil. ");
        }
        catch( IOException | ClassNotFoundException e )
        {
           
        }
    }
}


