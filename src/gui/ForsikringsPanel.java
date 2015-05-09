/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Component;
import java.awt.Container;
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
}
