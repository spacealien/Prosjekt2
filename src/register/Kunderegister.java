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
        
        Kunde test_1 = new Kunde("Rolf", "Hestman", "Kongleknaggen 18", "22260906", Calendar.getInstance(), "hesterolf@yahoo.no", "08206049937");
        Kunde test_2 = new Kunde("Bjørn", "Dæhlie", "Trysil 23B", "22260906", Calendar.getInstance(), "epost@epost.no", "12048449997");
        Kunde test_3 = new Kunde("Marit", "Bjørgen", "Trondheim 23B", "22260906", Calendar.getInstance(), "marit@langrenn.no", "02029449964");
        Kunde test_4 = new Kunde("Bjørn", "Dæhlie", "Trysil 23B", "22260906", Calendar.getInstance(), "epost@epost.no", "01258449963");
        Kunde test_5 = new Kunde("Ole-Einar", "Bjørndalen", "Trysil 23B", "22260906", Calendar.getInstance(), "kongen@skiskytter.no", "01258446816");
        Kunde test_6 = new Kunde("Luke", "Skywalker", "Tatooine", "22260906", Calendar.getInstance(), "luke@cantina.com", "03306849785");
        Kunde test_7 = new Kunde("Han", "Solo", "Millenium Falcon", "22260906", Calendar.getInstance(), "hansolo@smuggler.org", "03306844899");
        Kunde test_8 = new Kunde("Darth", "Vader", "Deathstar", "22260906", Calendar.getInstance(), "darkfather@deathstar.gov", "04215348879");
        Kunde test_9 = new Kunde("Emperor", "Palpatine", "Coruscant", "22260906", Calendar.getInstance(), "palpatine@emperor.gov", "04215338644");
        Kunde test_10 = new Kunde("Leia", "Organa", "Coruscant", "22260906", Calendar.getInstance(), "alderaan@galacticsenate.gov", "05299049681");
        Kunde test_11 = new Kunde("Lando", "Calrissian", "Cloud City", "22260906", Calendar.getInstance(), "lando@cloudcity.gov", "05299248072");
        Kunde test_12 = new Kunde("Tony", "Stark", "Pasadena", "22260906", Calendar.getInstance(), "tony@starkindustries.com", "06049946838");
        Kunde test_13 = new Kunde("Pepper", "Pots", "Pasadena", "22260906", Calendar.getInstance(), "pepper@starkindustries.com", "06047647884");
        Kunde test_14 = new Kunde("Bruce", "Wayne", "Gotham City", "22260906", Calendar.getInstance(), "imbatman@waynemanor.net", "07097547933");
        
        kunderegister.add(test_1);
        kunderegister.add(test_2);
        kunderegister.add(test_3);
        kunderegister.add(test_4);
        kunderegister.add(test_5);
        kunderegister.add(test_6);
        kunderegister.add(test_7);
        kunderegister.add(test_8);
        kunderegister.add(test_9);
        kunderegister.add(test_10);
        kunderegister.add(test_11);
        kunderegister.add(test_12);
        kunderegister.add(test_13);
        kunderegister.add(test_14);
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
        return kunderegister.stream().filter( x -> x != null ).collect(Collectors.toList());
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
