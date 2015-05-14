/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
        
        GregorianCalendar fdato = new GregorianCalendar(1991,6,6);
        Kunde test_1 = new Kunde("Rolf", "Hestman", "Kongleknaggen 18", "22260906", fdato, "hesterolf@yahoo.no", "08206049937");
        Kunde test_2 = new Kunde("Bjørn", "Dæhlie", "Trysil 23B", "22260906", fdato, "epost@epost.no", "12048449997");
        Kunde test_3 = new Kunde("Marit", "Bjørgen", "Trondheim 23B", "22260906", fdato, "marit@langrenn.no", "02029449964");
        Kunde test_4 = new Kunde("Bjørn", "Dæhlie", "Trysil 23B", "22260906", fdato, "epost@epost.no", "01258449963");
        Kunde test_5 = new Kunde("Ole-Einar", "Bjørndalen", "Trysil 23B", "22260906", fdato, "kongen@skiskytter.no", "01258446816");
        Kunde test_6 = new Kunde("Luke", "Skywalker", "Tatooine", "22260906", fdato, "luke@cantina.com", "03306849785");
        Kunde test_7 = new Kunde("Han", "Solo", "Millenium Falcon", "22260906", fdato, "hansolo@smuggler.org", "03306844899");
        Kunde test_8 = new Kunde("Darth", "Vader", "Deathstar", "22260906", fdato, "darkfather@deathstar.gov", "04215348879");
        Kunde test_9 = new Kunde("Emperor", "Palpatine", "Coruscant", "22260906", fdato, "palpatine@emperor.gov", "04215338644");
        Kunde test_10 = new Kunde("Leia", "Organa", "Coruscant", "22260906", fdato, "alderaan@galacticsenate.gov", "05299049681");
        Kunde test_11 = new Kunde("Lando", "Calrissian", "Cloud City", "22260906", fdato, "lando@cloudcity.gov", "05299248072");
        Kunde test_12 = new Kunde("Tony", "Stark", "Pasadena", "22260906", fdato, "tony@starkindustries.com", "06049946838");
        Kunde test_13 = new Kunde("Pepper", "Pots", "Pasadena", "22260906", fdato, "pepper@starkindustries.com", "06047647884");
        Kunde test_14 = new Kunde("Bruce", "Wayne", "Gotham City", "22260906", fdato, "imbatman@waynemanor.net", "07097547933");
        
        Kunde test_15 = new Kunde("Bruce", "Hestman", "Kongleknaggen 18", "22260906", fdato, "hesterolf@yahoo.no", "08205049937");
        Kunde test_16 = new Kunde("Rolf", "Dæhlie", "Trysil 23B", "22260906", fdato, "epost@epost.no", "12048448997");
        Kunde test_17 = new Kunde("Bjørn", "Dæhlie", "Trondheim 23B", "22260906", fdato, "marit@langrenn.no", "02025449964");
        Kunde test_18 = new Kunde("Marit", "Dæhlie", "Trysil 23B", "22260906", fdato, "epost@epost.no", "01258443963");
        Kunde test_19 = new Kunde("Ole-Einar", "Skywalker", "Trysil 23B", "22260906", fdato, "kongen@skiskytter.no", "01258456816");
        Kunde test_20 = new Kunde("Ole", "Skywalker", "Tatooine", "22260906", fdato, "luke@cantina.com", "03307849785");
        Kunde test_21 = new Kunde("Luke", "Solo", "Millenium Falcon", "22260906", fdato, "hansolo@smuggler.org", "03307844899");
        Kunde test_22 = new Kunde("Darth", "Organa", "Deathstar", "22260906", fdato, "darkfather@deathstar.gov", "04115348879");
        Kunde test_23 = new Kunde("Darth", "Hestman", "Coruscant", "22260906", fdato, "palpatine@emperor.gov", "04115338644");
        Kunde test_24 = new Kunde("Tony", "Organa", "Coruscant", "22260906", fdato, "alderaan@galacticsenate.gov", "05269049681");
        Kunde test_25 = new Kunde("Tony", "Calrissian", "Cloud City", "22260906", fdato, "lando@cloudcity.gov", "05259248072");
        Kunde test_26 = new Kunde("Pepper", "Calrissian", "Pasadena", "22260906", fdato, "tony@starkindustries.com", "06043946838");
        Kunde test_27 = new Kunde("Pepper", "Ås", "Pasadena", "22260906", fdato, "pepper@starkindustries.com", "06067647884");
        Kunde test_28 = new Kunde("Bruce", "Ødegård", "Gotham City", "22260906", fdato, "imbatman@waynemanor.net", "07094547933");
        
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
        kunderegister.add(test_15);
        kunderegister.add(test_16);
        kunderegister.add(test_17);
        kunderegister.add(test_18);
        kunderegister.add(test_19);
        kunderegister.add(test_20);
        kunderegister.add(test_21);
        kunderegister.add(test_22);
        kunderegister.add(test_23);
        kunderegister.add(test_24);
        kunderegister.add(test_25);
        kunderegister.add(test_26);
        kunderegister.add(test_27);
        kunderegister.add(test_28);
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
