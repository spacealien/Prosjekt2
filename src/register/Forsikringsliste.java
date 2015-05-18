/* 
 * 
 */
package register;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import objekter.Forsikring;
import objekter.Kunde;

/**
 *
 * @author Odd, Marthe
 */
public class Forsikringsliste implements Serializable
{
    private Map<Integer,Forsikring> liste = new HashMap<>();
    
    public Forsikringsliste()
    {

    }
    
    /**
     * Metoden legger en en forsikring i registeret.
     */
    
    public void leggTil( Kunde kunde, Forsikring forsikring )
    {
        kunde.leggTilNøkkel( forsikring.getForsikringsnummer() );
        liste.put(forsikring.getForsikringsnummer(), forsikring);
    }
    
    // henter ut en forsikring utifra forsikringsnummer.
    public Forsikring getForsikring( Integer nøkkel )
    {
        return liste.get(nøkkel);
    }
    
    // sletter en forsikring ved hjelp av forsikringsnummer.
    public void slett( Integer nøkkel )
    {
        liste.remove(nøkkel);
    }
    
    /**
     * metoden motar en kunde som parameter og returnerer alle forsikringer som er registert på
     * gitt kunde.
     */
    
    public List<Forsikring> getKundensForsikringer( Kunde kunde )
    {
        List<Integer> nøkkler  = kunde.getNøkkelliste();
        List<Forsikring> funnetForsikringer = new ArrayList<>( nøkkler.size() );
        
        nøkkler.stream().forEach((n) -> {
            funnetForsikringer.add(liste.get(n));
        });
        return funnetForsikringer;
    }
    
    
    // teller antall aktive forsikringer på kunden som blir send med som parameter.
    public int tellKundensAktiveForsikringer( Kunde kunde )
    {
        List<Forsikring> forsikringer =  getKundensForsikringer( kunde );
        
        int teller = 0;
        for(Forsikring forsikring : forsikringer)
            if(forsikring.erAktiv())
                teller++;
        
        return teller;
    }
    
    /**
     * skjekker hvor mange forskjellige unike forsikringer en kunde har.
     * @param kunde
     * @return 
     */
    public Set<String> antallUnikeAktiveForsikringer( Kunde kunde )
    {
        List<Forsikring> kundeForsikringer = getKundensForsikringer( kunde );
        Set<String> set = new HashSet<>();
        for( Forsikring forsikring :  kundeForsikringer)
        {
            if(forsikring.erAktiv())
                set.add(forsikring.getForsikringsType());
        }
        return set;
    }
    
    // returnerer en vilkårlig forsikring fra registerert, brukes for å hente løpenummer ved lagring til fil.
    public Forsikring getForsikring()
    {
        Iterator<Forsikring> iterator = liste.values().iterator();
        if( iterator.hasNext() && iterator.next() != null)
            return iterator.next();
        else
            return null;
    }
    
    // returnerer en List<Forsikring> alle forsikringer.
    public List<Forsikring> alleForsikringer()
    {
        return liste.values().stream().collect(Collectors.toList());
    }
    
    @Override
    public String toString()
    {
        return liste.values().stream().toString();
    }
}
