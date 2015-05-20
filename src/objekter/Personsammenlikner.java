
package objekter;

import java.io.Serializable;
import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.Comparator;

/**
 *
 * @author Odd, Marthe, sist endret 10.05.2015.
 */
public class Personsammenlikner implements Comparator<Kunde>, Serializable
{
    private final String sorteringsrekkefølge = "<\0<0<1<2<3<4<5<6<7<8<9" +
           "<A,a<B,b<C,c<D,d<E,e<F,f<G,g<H,h<I,i<J,j" +
           "<K,k<L,l<M,m<N,n<O,o<P,p<Q,q<R,r<S,s<T,t" +
           "<U,u<V,v<W,w<X,x<Y,y<Z,z<Æ,æ<Ø,ø<Å=AA,å=aa;AA,aa";
    
    private Collator kollator;   
    
    public Personsammenlikner()
    {
        try
        {
            kollator = new RuleBasedCollator(sorteringsrekkefølge);
        } 
        catch(ParseException e)
        {
            
        }  
    }
    
    @Override
    public int compare(Kunde kunde_1, Kunde kunde_2) 
    {
        String etternavn_1 = kunde_1.getEtternavn();
        String etternavn_2 = kunde_2.getEtternavn();
        String fornavn_1 = kunde_1.getFornavn();
        String fornavn_2 = kunde_2.getFornavn();
        
        int d = kollator.compare(etternavn_1, etternavn_2);
        if (d != 0)  
            return d;
        else  
            return kollator.compare(fornavn_1, fornavn_2);
    }
}
