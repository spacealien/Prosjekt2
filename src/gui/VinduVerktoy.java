
package gui;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/*
 * 
 * @author Odd. Sist endret 19.05.2015.
 */
public interface VinduVerktoy 
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
    
    /**
     * 
     * @param pane
     * @param knapp1
     * @param knapp2 
     */
    
    
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
    
    /**
     * 
     * @param pane
     * @param knapp2 
     */
    
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
    
}
