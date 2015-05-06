/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import objekter.Ansatt;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class Ansattregister 
{
    Set<Ansatt> register = new HashSet<>();
    
    public Ansattregister()
    {
        GregorianCalendar fodedat = new GregorianCalendar();
        Ansatt test_1 = new Ansatt( "fnavn", "Bjarne", "adresse", "22260906", fodedat , "email", "22031548526");
        Ansatt test_2 = new Ansatt( "fnavn", "Henriksen", "adresse", "22260906", fodedat, "email", "22031548526");
        Ansatt test_3 = new Ansatt( "fnavn", "Kåresen", "adresse", "22260906", fodedat, "email", "22031548526");
        Ansatt test_4= new Ansatt( "fnavn", "Ivarsen", "adresse", "22260906", fodedat, "email", "22031548526");
        Ansatt test_5 = new Ansatt( "fnavn", "Ludviksen", "adresse", "22260906", fodedat, "email", "22031548526");
        register.add(test_5);
        register.add(test_4);
        register.add(test_3);
        register.add(test_2);
        register.add(test_1);
    }
    
    public boolean leggTIl( Ansatt nyAnsatt )
    {
        return register.add(nyAnsatt);
    }
    
    // søk på ansatt ved ansattnummer
    public Ansatt finnAnsattAnsattnr( int ansattnr )
    {
        for( Ansatt ansatt :  register)
        {
            if (ansatt.getAnsattnr() == ansattnr)
                return ansatt;
        }
        return null;
    }
    
    // søk på ansat ved fornavn
    public Ansatt finnAnsattFornavn( String fornavn )
    {
        for( Ansatt ansatt :  register)
        {
            if (ansatt.getFornavn().equals(fornavn))
                return ansatt;
        }
        return null;
    }
    
    // Søk på ansatt ved etternavn
    public Ansatt finnAnsattEtternavn( String etternavn )
    {
        for( Ansatt ansatt :  register)
        {
            if (ansatt.getEtternavn().equals(etternavn))
                return ansatt;
        }
        return null;
    }
            
    // søk på ansatt ved telefon
    public Ansatt finnAnsattTelefon( String telefon )
    {
        for( Ansatt ansatt :  register)
        {
            if (ansatt.getTlfnr().equals(telefon))
                return ansatt;
        }
        return null;
    }
    
    public void slett( int ansattnr )
    {
        register.remove(finnAnsattAnsattnr(ansattnr));
    }
    
    public List<Ansatt> getAlleAnsatte()
    {
        return register.stream().collect(Collectors.toList());
    }
    
    @Override
    public String toString() 
    {
        StringBuilder bygger = new StringBuilder();
        for( Ansatt ansatt :  register)
        {
            bygger.append(ansatt.toString());
        }
        return bygger.toString();
    }
    
}
