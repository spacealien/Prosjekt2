/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Component;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
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
    
    default void velgVilkår( String filsti, JTextArea utskrift ) 
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
            utskrift.append(vilkårBygger.toString());
        }
        catch (UnsupportedEncodingException ex) 
        {
            JOptionPane.showMessageDialog(null, "Sørg for at textfield er av riktig format. UTF-8", "Feilmelding", JOptionPane.ERROR_MESSAGE);
        } 
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Feil ved lesing av fil.", "Feilmelding", JOptionPane.ERROR_MESSAGE);
        }
    }
}
