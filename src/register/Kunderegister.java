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
 * @author Odd, Thomas, Marthe
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
    
    public boolean erKunde( Kunde kunde)
    {
        return kunderegister.contains(kunde);
    }
    
    public Kunde leggTil(  Kunde kunde )
    {
        kunderegister.add( kunde );
        return kunde;
    }
    
    public void slett( String personnummer )
    {
        kunderegister.remove(finnKundeEtterPersonnummer( personnummer ));
    }
    
    public Kunde finnKundeEtterPersonnummer( String personnummer )
    {
        for( Kunde kunde :kunderegister)
        {
            if(kunde.getPersonnummer().equals(personnummer))
                return kunde;
        }
        return null;
    }
    
    public List<Kunde> finnAlleKundeEtterPersonnummer(String personnummer)
    {
        return kunderegister.stream().filter( x -> x.getPersonnummer()
                .equalsIgnoreCase(personnummer)).collect(Collectors.toList());
    }
    
    
    public Kunde finnKundeEtterTelefonnummer( String telefonnummer )
    {
        for( Kunde kunde: kunderegister )
        {
            if(kunde.getTlfnr().equals(telefonnummer ))
                return kunde;
        }
        return null;
    }
    
    public List<Kunde> finnKundeEtterFornavn( String fornavn )
    {
        return kunderegister.stream().filter( (x) ->
                x.getFornavn().contains(fornavn)).collect(Collectors.toList());
    }
    
    public List<Kunde> finnKundeEtterEtternavn( String etternavn )
    {
        
        return kunderegister.stream().filter( (x) -> 
                x.getEtternavn().contains(etternavn)).collect(Collectors.toList());
    }
    
    //ok, testet
    public List<Kunde> finnKunderEtterNavn( String fornavn, String etternavn )
    {
        return kunderegister.stream().filter( (x) -> x.getEtternavn().contains(etternavn) &&
                x.getFornavn().contains(fornavn)).collect(Collectors.toList());
    }
    
    public List<Kunde> alleKunder()
    {
        if( kunderegister.size() > 0)
            return kunderegister.stream().filter( x -> x != null ).collect(Collectors.toList());
        else
            return null;
    }
    
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
