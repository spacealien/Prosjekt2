
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

/* 
 * Klassens hensikt er å holde skademeldingene i et register. 
 *
 *  @author Odd, Marthe. Sist endret 15.05.2015.
 */
public class SkademeldingRegister implements Serializable
{
    private final Map<Integer,Skademelding> skademeldinger;
    
    public SkademeldingRegister()
    {
        skademeldinger = new HashMap<>();
    }// slutt på konstruktør.
    
    /*
     * Tar i mot et forsirkings og skademeldings objekt.
     * metoden legger til skademeldingens skadenummer i en ArrayList
     * på forsikrings objektet, på denne måten kan man hente opp verdier fra hashmapet.
     * 
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
    
    /*
     * Henter alle skademeldinger som hører til en kunde. Metoden tar i mot liste med forsikringer
     * som hører til kunden. Deretter henter nøkklene som fungerer som Key i hashmapet
     * fra hver forsikring i lista, for og så hente ut tilhørende skademelding ved hjelp av nøkkelen.
     * 
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
    
    /*
     * Henter først nøkklene som ligger i forsikringen som kommer som parameter,
     * videre opprettes det en List som fylles på med skademeldinger som hører til
     * gitt forsikring.
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
    
    /*
     * henter alle verdiene(skademeldinger) fra hashmapet, og returnerer i from av List<Skademeldig.
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
    /*
     * Henter første skademelding fra registeret, brukes for å hente et tilfeldig
     * objekt og deretter hente løpenummeret ved skriving til fil. 
     * returnerer en vilkårlig skademelding eller null hvis det ikke finnes noen forsikringer i registeret.
     */
    
    public Skademelding getSkademelding()
    {
        Iterator<Skademelding> iterator = skademeldinger.values().iterator();
        if( iterator.hasNext() && iterator.next() != null)
            return iterator.next();
        else
            return null;
    }
} // slutt på klasse.
