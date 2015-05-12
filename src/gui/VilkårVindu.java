/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Odd
 */
public class VilkårVindu extends JFrame
{
    private final JTextArea utskrift;
    private final JButton velgKnapp;
    private final JButton lesKnapp;
    private final JComboBox<String> dropDown;
    
    
    public VilkårVindu( String[] dropDownValg )
    {
        setVisible(true);
        setSize(600,600);
        
        VilkårVinduLytter vilkårLytter = new VilkårVinduLytter();
        utskrift = new JTextArea();
        velgKnapp = new JButton("Velg");
        lesKnapp = new JButton("Les");
        dropDown = new JComboBox<>(dropDownValg);
        
        utskrift.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(utskrift);
        
        JPanel menyPanel = new JPanel();
        menyPanel.add( new JLabel("Velg: ") );
        menyPanel.add(dropDown);
        menyPanel.add(lesKnapp);
        menyPanel.add(velgKnapp);
        
        Container c =  getContentPane();
        c.setLayout( new BorderLayout() );
        c.add( menyPanel, BorderLayout.NORTH );
        c.add( scroll, BorderLayout.CENTER );
        velgKnapp.addActionListener(vilkårLytter);
        lesKnapp.addActionListener(vilkårLytter);
    }
    
    private class VilkårVinduLytter implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if( e.getSource() == velgKnapp )
            {
                
            }
            else if( e.getSource() == lesKnapp )
            {
                
            }
        }   
    }
}
