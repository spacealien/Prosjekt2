/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Odd, Thomas, Marthe
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
            for (Component currentComponent : getKomponenter(liste.get(i)))
            {
                liste.add(currentComponent);
            }
            }
        } catch (ClassCastException e) {
            liste = new ArrayList<>();
        }
        return liste.toArray(new Component[liste.size()]);
    }
    
    
    default void disableFelter( Container pane )
    {
        Component[] liste = pane.getComponents();
        for( Component komponent: liste )
        {
            if(komponent instanceof JTextField)
            {
                JTextField tf = (JTextField)komponent;
                tf.setEditable(false);
            }
        }
    }
    
    default void enableFelter( Container pane )
    {
        Component[] liste = pane.getComponents();
        for( Component komponent: liste )
        {
            if(komponent instanceof JTextField)
            {
                JTextField tf = (JTextField)komponent;
                tf.setEditable(true);
            }
        }
    }
    
    default void visForsikringensVilkår(String overskrift, String vilkårInnhold )
    {
            VilkårVindu vilkårVindu = new VilkårVindu("Vilkår");
            vilkårVindu.setOverskrift(overskrift);
            vilkårVindu.getUtskriftområdet().setText(vilkårInnhold);
    }
    
    default String velgVilkår( String filsti )
    {
        try (BufferedReader innfil = new BufferedReader( new InputStreamReader ( new FileInputStream(filsti + ".txt"),
        "UTF8")))
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
        catch (FileNotFoundException ex) 
        {
            JOptionPane.showMessageDialog(null, "Feilmelding", "Kunne ikke finne fil", JOptionPane.ERROR_MESSAGE);
            return null;
        } 
        catch (UnsupportedEncodingException ex) 
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
