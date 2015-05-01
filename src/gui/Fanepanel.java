/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author Marthejansonskogen
 */
public class Fanepanel extends JPanel
{
    private JTabbedPane fanekort;
    private JComponent panel;
    private final JToggleButton lukkeknapp;
    private String teksten;
        
    public Fanepanel(JTabbedPane t, JComponent k, String tekst)
    {
        fanekort = t;
        panel = k;
        lukkeknapp = new JCheckBox();
        lukkeknapp.addActionListener(new Lukkelytter());
        lukkeknapp.addChangeListener(
	new ChangeListener()
	{
        @Override
	public void stateChanged(ChangeEvent e)
	{
            lukkeknapp.setSelected(false);
	}
	});
        add(new JLabel(tekst));
        add(lukkeknapp);
        
    }
    
    	private class Lukkelytter implements ActionListener
	{
            @Override
            public void actionPerformed(ActionEvent e)
            {
		fanekort.remove(panel);
		fanekort.setSelectedIndex(fanekort.getTabCount()-1);
            }
	}
}
