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
 * @author Odd, Thomas, Marthe
 */
public class Forsikringsliste
{
    Map<Integer,Forsikring> liste = new HashMap<>();
    
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
        List<Integer> nøkkler  = kunde.getNøkkelliste();
        List<Forsikring> funnetForsikringer = new ArrayList<>( nøkkler.size() );
        
        nøkkler.stream().forEach((n) -> {
            funnetForsikringer.add(liste.get(n));
        });
        return funnetForsikringer;
    }
    
    public int tellKundensForsikringer( Kunde kunde )
    {
        List<Forsikring> forsikringer =  getKundensForsikringer( kunde );
        
        int teller = 0;
        for(Forsikring forsikring : forsikringer)
            if(forsikring.erAktiv())
                teller++;
        
        return teller;
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
