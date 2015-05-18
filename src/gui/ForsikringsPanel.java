/**
 * Dette interfacet inneholder kode som ellers ville blitt gjenntagene
 * i de forskjellige panelene som er relatert til forsikringene.
 * Klassene som implementerer dette interfacet får tilgang til alle metodene.
 * 
 */
package gui;

import java.awt.Component;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * @author Odd, Marthe
 */

public interface ForsikringsPanel
{
    default Component[] getKomponenter(Component pane)
    {
        ArrayList<Component> liste = null;
        try
        {
            liste = new ArrayList<>(Arrays.asList(
                  ((Container) pane).getComponents()));
            for (int i = 0; i < liste.size(); i++)
            {
                liste.addAll(Arrays.asList(getKomponenter(liste.get(i))));
            }
        } catch (ClassCastException e) 
        {
            liste = new ArrayList<>();
        }
        return liste.toArray(new Component[liste.size()]);
    }
    
    
    default void disableFelter( Container pane, JButton knapp1, JButton knapp2 )
    {
        for(Component component : getKomponenter(pane))
        {
            if((component instanceof JTextField))
            {
                JTextField tf = (JTextField)component;
                tf.setEditable(false);
            }
            else if(component instanceof JComboBox)
            {
                JComboBox cb = (JComboBox)component;
                cb.setEnabled(false);
            }
            else if(component instanceof JRadioButton)
            {
                JRadioButton rb = (JRadioButton)component;
                rb.setEnabled(false);
            }
            else if (component.equals(knapp1))
            {
                component.setVisible(false);
            }
            else if (component.equals(knapp2))
            {
                component.setVisible(false);
            }
        }
    }
    
    default void enableFelter( Container pane, JButton knapp2 )
    {
        for(Component component : getKomponenter(pane))
        {
            if((component instanceof JTextField))
            {
                JTextField tf = (JTextField)component;
                tf.setEditable(true);
            }
            else if(component instanceof JComboBox)
            {
                JComboBox cb = (JComboBox)component;
                cb.setEnabled(true);
            }
            else if(component instanceof JRadioButton)
            {
                JRadioButton rb = (JRadioButton)component;
                rb.setEnabled(true);
            }
            else if (component.equals(knapp2))
            {
                component.setVisible(true);
            }              
        }
    }
     
    /**
     * Metoden oppretter et nytt vindu for visning av vilkår.
     * 
     * Parameteren overskrift endrer teksten i feltet over utskriftsområdet, ikke selve
     * tittelen for JFrame.
     * 
     * parameteren vilkårInnhold tar i mot vilkårene i form av en String,
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

