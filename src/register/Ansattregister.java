/*
 * Klassen holder ansatt objektene i et register.
 */

package register;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import objekter.Ansatt;

/**
 *
 * @author Odd, Marthe
 */
public class Ansattregister implements Serializable
{
    private List<Ansatt> register = new ArrayList<>();
    
    public Ansattregister()
    {
        GregorianCalendar fodedat = new GregorianCalendar();
        Ansatt test_1 = new Ansatt( "Bjarne", "Bjarnesen", "adresse", "22260906", fodedat , "email", "22031548526");
        Ansatt test_2 = new Ansatt( "Henrik", "Henriksen", "adresse", "22260906", fodedat, "email", "22031548526");
        Ansatt test_3 = new Ansatt( "Kåre", "Kåresen", "adresse", "22260906", fodedat, "email", "22031548526");
        Ansatt test_4= new Ansatt( "Ivar", "Ivarsen", "adresse", "22260906", fodedat, "email", "22031548526");
        Ansatt test_5 = new Ansatt( "Ludvig", "Ludviksen", "adresse", "22260906", fodedat, "email", "22031548526");
        register.add(test_5);
        register.add(test_4);
        register.add(test_3);
        register.add(test_2);
        register.add(test_1);
    }
    
    
    // metode for å registrere en ny ansatt.
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
    
    // søk på ansatt ved fornavn
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
    
    // sletter en ansatt fra registere ved hjelp av ansattnummer.
    public void slett( int ansattnr )
    {
        register.remove(finnAnsattAnsattnr(ansattnr));
    }
    
    // returnerer en liste med alle ansatte.
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
