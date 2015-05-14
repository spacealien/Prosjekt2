/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import objekter.Kunde;


/**
 *
 * @author Odd, Thomas, Marthe
 * 
 */
public class Kunderegister
{
    private Set<Kunde> kunderegister;  
    
    public Kunderegister()
    {
        kunderegister = new HashSet<>();
        
        Kunde test_1 = new Kunde("Bjørn", "Dærlie" ,  "adresse",  "22260906",  Calendar.getInstance(), "epost", "25079139524");
        Kunde test_2 = new Kunde("Bjørn", "Dærlie" ,  "adresse",  "22260906",  Calendar.getInstance(), "epost", "25145625878");
        Kunde test_3 = new Kunde("Bjørn", "Dærlie" ,  "adresse",  "22260906",  Calendar.getInstance(), "epost", "65214582548");
        Kunde test_4 = new Kunde("Bjørn", "Dærlie" ,  "adresse",  "22260906",  Calendar.getInstance(), "epost", "84852154878");
        Kunde test_5 = new Kunde("Bjørn", "Dærlie" ,  "adresse",  "22260906",  Calendar.getInstance(), "epost", "98554848752");
        
        kunderegister.add( test_1 );
        kunderegister.add( test_2 );
        kunderegister.add( test_3 );
        kunderegister.add( test_4 );
        kunderegister.add( test_5 );
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
                x.getFornavn().equals(fornavn)).collect(Collectors.toList());
    }
    
    public List<Kunde> finnKundeEtterEtternavn( String etternavn )
    {
        
        return kunderegister.stream().filter( (x) -> 
                x.getEtternavn().equals(etternavn)).collect(Collectors.toList());
    }
    
    //ok, testet
    public List<Kunde> finnKunde( String fornavn, String etternavn )
    {
        return kunderegister.stream().filter( (x) -> x.getEtternavn().equals(etternavn) &&
                x.getFornavn().equals(fornavn)).collect(Collectors.toList());
    }
    
    public List<Kunde> alleKunder()
    {
        return kunderegister.stream().collect(Collectors.toList());
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
