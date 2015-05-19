/**
 * Dette interfacet inneholder kode som ellers ville blitt gjenntagene
 * i de forskjellige panelene som er relatert til forsikringene.
 * Klassene som implementerer dette interfacet får tilgang til alle metodene.
 * 
 */
package gui;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import javax.swing.JOptionPane;
import objekter.Forsikring;

/**
 * @author Odd, Marthe
 */

public interface ForsikringsPanel
{
    public boolean hentInfo();
    public void visForsikring( Forsikring f);
    public void leggTilKundePanel( KundePanel panel );
    public void beregnPris();
    public void tegnNy();
    public void oppdaterForsikring();
    public void redigerForsikring();
    public void deaktiverForsikring();
    public void visVilkår();
    public void velgVilkår();
    
     
    /**
     * Metoden oppretter et nytt vindu for visning av vilkår.
     * 
     * Parameteren overskrift endrer teksten i feltet over utskriftsområdet, ikke selve
     * tittelen for JFrame.
     * 
     * Parameteren vilkårInnhold tar i mot vilkårene i form av en String,
     * som videre blir brukt til å fylle utskriftsområdet.
     */
    
    default void visForsikringensVilkår(String overskrift, String vilkårInnhold )
    {
            VilkårVindu vilkårVindu = new VilkårVindu("Vilkår");
            vilkårVindu.setOverskrift(overskrift);
            vilkårVindu.getUtskriftområdet().setText(vilkårInnhold);
            vilkårVindu.getUtskriftområdet().setCaretPosition(0);
    }
    
    /**
     * Henter vilkårene som er lagret i en textfil i programmets mappe.
     * Metoden kjører en test på hvilket operativsystem brukeren har 
     * og korrigerer filstien deretter.
     */
    
    default String velgVilkår( String filsti )
    {
        String mappeSti = "Vilkår\\";
        
        if(System.getProperty("os.name").toLowerCase().contains("mac") || System.getProperty("os.name").toLowerCase().contains("lin"))
            mappeSti = "Vilkår/";

        try (BufferedReader innfil = new BufferedReader( new InputStreamReader ( new FileInputStream( mappeSti + filsti + ".txt"),"UTF8")))                                                                                                               
        {
            StringBuilder vilkårBygger = new StringBuilder();
            String vilkår;
            
            do
            {
                vilkår = innfil.readLine();
                if( vilkår != null )
                    vilkårBygger.append(vilkår).append("\n");
            } while( vilkår != null);
            
                innfil.close();            
                return vilkårBygger.toString();
        } 
        catch (FileNotFoundException e) 
        {
            JOptionPane.showMessageDialog(null, "Feilmelding", "Kunne ikke finne fil", JOptionPane.ERROR_MESSAGE);
            return null;
        } 
        catch (UnsupportedEncodingException e) 
        {
            JOptionPane.showMessageDialog(null, "Feilmelding", "Feil filformat. ikke UTF-8.", JOptionPane.ERROR_MESSAGE);
            return null;
        } 
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "Feilmelding", "Feil under lesing av fil. ", JOptionPane.ERROR_MESSAGE);
            return null;
         }
    }
}

