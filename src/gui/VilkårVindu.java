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
    
    public VilkårVindu( String vinduTittel )
    {
        super(vinduTittel);
        setVisible(true);
        setSize(600,600);
        
        utskrift = new JTextArea();        
        utskrift.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(utskrift);
        
        JPanel menyPanel = new JPanel();
        menyPanel.add( new JLabel("Velg: ") );

        
        Container c =  getContentPane();
        c.setLayout( new BorderLayout() );
        c.add( menyPanel, BorderLayout.NORTH );
        c.add( scroll, BorderLayout.CENTER );
    }
    
    public void visVilkår( String vilkår )
    {
        utskrift.setText(vilkår);
    }
    
    private class VilkårVinduLytter implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
        
        }
    }
}
