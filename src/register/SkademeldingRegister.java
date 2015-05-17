/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import objekter.Forsikring;
import objekter.Skademelding;

/**
 *
 * @author Odd, Marthe
 */
public class SkademeldingRegister implements Serializable
{
    private Map<Integer,Skademelding> skademeldinger;
    
    public SkademeldingRegister()
    {
        skademeldinger = new HashMap<>();
    }
    
    /**
     * Tar i mot et forsirkings og skademeldings objekt.
     * metoden legger til skademeldingens skadenummer i en ArrayList
     * på forsikrings objektet, på denne måten kan man hente opp verdier fra hashmapet.
     * 
     * @param forsikring - forsikringen skademeldingen skal registreres på.
     * @param skademelding - skademelding.
     */
    public void leggTil( Forsikring forsikring, Skademelding skademelding )
    {
        forsikring.leggTilNøkkel(skademelding.getSkadenummer());
        skademeldinger.put( skademelding.getSkadenummer() , skademelding );
    }
    
    // returnerer en Skademelding med likt skademeldingsnummer som parameter.
    public Skademelding getSkademelding( int skademeldingnummer )
    {
        return skademeldinger.get(skademeldingnummer);
    }
    
    /**
     * Henter alle skademeldinger som hører til en kunde, tar i mot liste med forsikringer
     * som hører til kunden.
     * @param forsikringer
     * @return 
     */
    
    public List<Skademelding> getKundensSkademeldinger( List<Forsikring> forsikringer )
    {
        List<Skademelding> funnetSkademeldinger = new ArrayList<>();
        for( Forsikring forsikring :forsikringer)
        {
            List<Integer> nøkler = forsikring.getNøkkelliste();
            for( Integer nøkkel :nøkler)
            {
                funnetSkademeldinger.add(skademeldinger.get(nøkkel));
            }
        }
        return funnetSkademeldinger;
    }
    
    /**
     * Henter først nøkklene som ligger i forsikringen som kommer som parameter,
     * videre opprettes det en List som fylles på med skademeldinger som hører til
     * gitt forsikring.
     * @param forsikring
     * @return Forsikring
     */
    
    public List<Skademelding> getSkademeldinger( Forsikring forsikring )
    {
        List<Integer> nøkkler = forsikring.getNøkkelliste();
        List<Skademelding> funnetSkademeldinger = new ArrayList<>();
        
        for( int n : nøkkler )
        {
            funnetSkademeldinger.add(skademeldinger.get(n));
        }
        return funnetSkademeldinger;
    }
    
    /**
     * henter alle verdiene fra hashmapet, og returnerer i from av List<Skademeldig>.
     * @return Skademelding
     */
    public List<Skademelding> alleSkademeldinger()
    {
        return skademeldinger.values().stream().collect(Collectors.toList());
    }
    
    // filtrer ut ut skademeldinger mello to datoer.
    public List<Skademelding> getSkademeldinger( Calendar startdato, Calendar sluttdato )
    {
        return skademeldinger.values().stream()
                .filter( x -> startdato.after(x.getSkadeDato()) && sluttdato.before(x.getSkadeDato()))
                .collect( Collectors.toList() );
    }
    /**
     * Henter første skademelding fra registeret, brukes for å hente et tilfeldig
     * objekt og deretter hente løpenummeret ved skriving til fil. 
     * @return Skademelding
     */
    public Skademelding getSkademelding()
    {
        Iterator<Skademelding> iterator = skademeldinger.values().iterator();
        if( iterator.hasNext() && iterator.next() != null)
            return iterator.next();
        else
            return null;
    }
}
