/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import objekter.Ansatt;
import objekter.Kunde;
import objekter.Personsammenlikner;


/**
 *
 * @author Odd, Marthe
 * 
 */
public class Kunderegister implements Serializable
{
    private List<Kunde> kunderegister;  
    
    public Kunderegister()
    {
        kunderegister = new ArrayList<>();
        sorter();
    }
    
    /**
     * Metoden returnerer alle kunder som tilhører ansatt som bil tilsendt i parameter
     * i form av List<Kunde>
     */
    
    public List<Kunde> getAnsattesKunder( Ansatt ansatt )
    {
        List<String> nøkkler = ansatt.getKundenøkler();
        List<Kunde> funnetKunder = new ArrayList<>();
        for( Kunde kunde :  kunderegister)
        {
            for( String nøkkel : nøkkler)
            {
                if( nøkkel.equals(kunde.getPersonnummer()))
                    funnetKunder.add(kunde);
            }
        }
        return funnetKunder;
    }
    
    /**
     * Metoden skjekker om det allerede eksisterer tilsvarene kundeobjekt i registeret.
     * returnerer true hvis kunden er i registeret, returnerer false hvis det ikke finnes et tilsvarede objekt
     * i registeret.
     */
    
    public boolean erKunde( Kunde kunde)
    {
        return kunderegister.contains(kunde);
    }
    
    // legger en kunde inn i listen.
    public boolean leggTil(  Kunde kunde )
    {
        return kunderegister.add( kunde );
    }
    
    // sletter en kunde fra registeret ved hjelp av personummer som bil tilsendt som parameter.
    public void slett( String personnummer )
    {
        kunderegister.remove(finnKundeEtterPersonnummer( personnummer ));
    }
    
    // returnerer Kunden som har personnummer lik parameteren personnummer.
    public Kunde finnKundeEtterPersonnummer( String personnummer )
    {
        for( Kunde kunde :kunderegister)
        {
            if(kunde.getPersonnummer().equals(personnummer))
                return kunde;
        }
        return null;
    }
    
    /**
     * returnerer en List<Kunde> som inneholde kunden som har personnummer lik parameter,
     * Denne metoden brukes for å mate TabellModell klassen med data, siden TabellModell
     * kun motar List som parameter.
     */
    public List<Kunde> finnAlleKundeEtterPersonnummer(String personnummer)
    {
        return kunderegister.stream().filter( x -> x.getPersonnummer()
                .equalsIgnoreCase(personnummer)).collect(Collectors.toList());
    }
    
    // returnerer første kunde med matchede telefonnummer.
    public Kunde finnKundeEtterTelefonnummer( String telefonnummer )
    {
        for( Kunde kunde: kunderegister )
        {
            if(kunde.getTlfnr().equals(telefonnummer ))
                return kunde;
        }
        return null;
    }
    
    // returnerer alle kundeobjekt som med fornavn som inneholder Stringen som blir mottatt i parameter.
    public List<Kunde> finnKundeEtterFornavn( String fornavn )
    {
        return kunderegister.stream().filter( (x) ->
                x.getFornavn().contains(fornavn)).collect(Collectors.toList());
    }
    
    // returnerer alle kundeobjekter som har etternavn som inneholder Stringen som blir motatt i parameter.
    public List<Kunde> finnKundeEtterEtternavn( String etternavn )
    {
        return kunderegister.stream().filter( (x) -> 
                x.getEtternavn().contains(etternavn)).collect(Collectors.toList());
    }
    
    // returnerer en liste med alle kunder som har både fornavn og etternavn som matcher søkeordene i parameterlsiten.
    public List<Kunde> finnKunderEtterNavn( String fornavn, String etternavn )
    {
        return kunderegister.stream().filter( (x) -> x.getEtternavn().contains(etternavn) &&
                x.getFornavn().contains(fornavn)).collect(Collectors.toList());
    }
    
    // returner en liste med alle kundene.
    public List<Kunde> alleKunder()
    {
        if( kunderegister.size() > 0)
            return kunderegister.stream().filter( x -> x != null ).collect(Collectors.toList());
        else
            return null;
    }
    
    
    // sorterer alfabetisk ved hjelp av fornavn og etternavn.
    public void sorter()
    {
        Personsammenlikner test = new Personsammenlikner();
        kunderegister.sort(test);
    }
    
    
    /**
     * Returnerer første kundeobjekt i lista, brukes til å hente it 
     * hjelpevariablen for kundenummber ved skriving til fil.
     * */
    public Kunde getKunde()
    {
        Iterator<Kunde> iterator = kunderegister.iterator();
        if( iterator.hasNext() && iterator.next() != null )
            return iterator.next();
        return null;
    }
    
    @Override
    public String toString()
    {
        StringBuilder bygger = new StringBuilder();
        kunderegister.stream().forEach((kunde) -> {
            bygger.append(kunde.toString());
        });
        
        return bygger.toString();
    }
}
