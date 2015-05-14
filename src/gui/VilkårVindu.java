/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Container;
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

    private JLabel overskrift;
    
    public VilkårVindu( String vinduTittel )
    {
        super(vinduTittel);
        setVisible(true);
        setSize(600,600);
        
        utskrift = new JTextArea();        
        utskrift.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(utskrift);
        
        overskrift = new JLabel("");
        JPanel menyPanel = new JPanel();
        menyPanel.add(overskrift);
        
        Container c =  getContentPane();
        c.setLayout( new BorderLayout() );
        c.add( menyPanel, BorderLayout.NORTH );
        c.add( scroll, BorderLayout.CENTER );
    }
    
    public void setOverskrift( String tekst )
    {
        overskrift.setText(tekst);
    }
    
    public void visVilkår( String vilkår )
    {
        utskrift.setText(vilkår);
    }
    
    public JTextArea getUtskriftområdet()
    {
        return utskrift;
    }
}
