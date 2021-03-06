/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

//Nødvendige import-setninger
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;

/**
 * @author Odd, Marthe, sist endret 16.05.2015.
 */

//Klassens hensikt er å legge til en lukkeknapp for fanene
public class Fanepanel extends JPanel
{
    private JTabbedPane fanekort;
    private JComponent panel;
    private final JToggleButton lukkeknapp;
    private String teksten;
    
    //Tar imot JTabbedPane, panel komponent og overskrift tekst
    public Fanepanel(JTabbedPane t, JComponent k, String tekst)
    {
        fanekort = t;
        panel = k;
        teksten = tekst;
        lukkeknapp = new JCheckBox();
        lukkeknapp.addActionListener(new Lukkelytter());
        lukkeknapp.addChangeListener( new ChangeListener()
	{
            @Override
            public void stateChanged(ChangeEvent e)
            {
                lukkeknapp.setSelected(false);
            }
	}); // slutt på anonym lytter
        
        add(new JLabel(teksten));
        add(lukkeknapp);
    } // slutt på konstuktør.
    
    //Klassens lytterklasse
    private class Lukkelytter implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            fanekort.remove(panel);
	    fanekort.setSelectedIndex(fanekort.getTabCount()-1);
        }
    }
} // slutt på klasse.
