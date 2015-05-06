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
import objekter.Skademelding;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class SkademeldingRegister 
{
    Map<Integer,Skademelding> skademeldinger = new HashMap<>();
    
    public SkademeldingRegister()
    {

    }
    
    public void leggTil( Forsikring forsikring, Skademelding skademelding )
    {
        forsikring.leggTilNøkkel(skademelding.getSkadenummer());
        skademeldinger.put( skademelding.getSkadenummer() , skademelding );
    }
    
    public Skademelding getSkademelding( int skademeldingnummer )
    {
        return skademeldinger.get(skademeldingnummer);
    }
    
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
    
    public List<Skademelding> alleSkademeldinger()
    {
        return skademeldinger.values().stream().collect(Collectors.toList());
    } 
}
