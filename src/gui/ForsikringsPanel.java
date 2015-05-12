/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Component;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JTextField;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public interface ForsikringsPanel
{
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
    
    default String velgVilkår( String filsti )
    {
        
        try (BufferedReader innfil = new BufferedReader( new FileReader( filsti +".txt")))
        {
            StringBuilder vilkårBygger = new StringBuilder();
            String vilkår;
            
            do
            {
                vilkår = innfil.readLine();
                if( vilkår != null )
                    vilkårBygger.append(vilkår).append("\n");
            } while( vilkår != null);
            
            return vilkårBygger.toString();
        } 
        catch (FileNotFoundException ex) 
        {
            return "finner ikke fil.";
        }
        catch( IOException e)
        {
            return "feil ved lesing av fil";
        }
    }
}
