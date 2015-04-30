/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import objekter.Forsikring;
import objekter.Kunde;

/**
 *
 * @author Odd
 * @param <Forsikringer>
 */


public class Forsikringsliste
{
    Map<String,Forsikring> liste = new HashMap<>();
    
    public Forsikringsliste()
    {

    }
    
    public void leggTil( Kunde kunde, Forsikring forsikring )
    {
        kunde.leggTilNøkkel( forsikring.getForsikringsnummer() );
        liste.put(forsikring.getForsikringsnummer(), forsikring);
    }
    
    public Forsikring getForsikring( Integer nøkkel )
    {
        return liste.get(nøkkel);
    }
    
    public void slett( Integer nøkkel )
    {
        liste.remove(nøkkel);
    }
    
    public List<Forsikring> getKundensForsikringer( Kunde kunde )
    {
        List<String> nøkkler  = kunde.getNøkkelliste();
        List<Forsikring> funnetForsikringer = new ArrayList<>( nøkkler.size() );
        
        for( String n: nøkkler)
        {
            funnetForsikringer.add(liste.get(n));
        }
        return funnetForsikringer;
    }
    
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
