/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class ReiseforsikringPanel extends JPanel implements ActionListener
{
    private final JTextField reiseBelop;
    private final JTextField reiseTilbud;
    private final JTextField antbarn;
    private final JLabel antbarnLabel;
    private final JRadioButton forsorgerJa;
    private final JRadioButton forsorgerNei;
    private final JButton reiseGiTilbud;
    String[] sone = {"", "Norden", "Europa", "Verden"};
    JComboBox<String> sonevelger;
    
    public ReiseforsikringPanel()
    {
        reiseBelop = new JTextField( 7 );
        reiseTilbud = new JTextField( 7 );
        antbarn = new JTextField(2);
        antbarnLabel = new JLabel("Forsørger antall barn: ");
        antbarn.setEnabled(false);
        antbarnLabel.setEnabled(false);
        reiseGiTilbud = new JButton("Tegn forsikring");
        sonevelger = new JComboBox<>(sone);
        forsorgerJa = new JRadioButton("Ja");
        forsorgerJa.setMnemonic(KeyEvent.VK_J);
        forsorgerNei = new JRadioButton("Nei");
        forsorgerNei.setMnemonic(KeyEvent.VK_N);
        ButtonGroup forsorger = new ButtonGroup();
        forsorger.add(forsorgerJa);
        forsorger.add(forsorgerNei);  
        
        JPanel tegnReisePanel1 = new JPanel();
        JPanel forsorgerP = new JPanel();
        tegnReisePanel1.setLayout(new GridLayout(5,4,1,5));
        forsorgerP.add(forsorgerJa);
        forsorgerP.add(forsorgerNei);
        tegnReisePanel1.add(new JLabel("Er kunde forsørger? "));
        tegnReisePanel1.add(forsorgerP);
        tegnReisePanel1.add(antbarnLabel);
        tegnReisePanel1.add(antbarn);
        tegnReisePanel1.add(new JLabel("Forsikringssone: "));
        tegnReisePanel1.add(sonevelger);
        tegnReisePanel1.add(new JLabel());
        tegnReisePanel1.add(new JLabel());
        tegnReisePanel1.add(new JLabel("Forsikringsbeløp: "));
        tegnReisePanel1.add(reiseBelop);
        tegnReisePanel1.add(new JLabel());
        tegnReisePanel1.add(new JLabel());
        tegnReisePanel1.add(new JLabel("Foreslått tilbud: "));
        tegnReisePanel1.add(reiseTilbud);
        tegnReisePanel1.add(new JLabel());
        tegnReisePanel1.add(new JLabel());
        tegnReisePanel1.add(reiseGiTilbud);
        add(tegnReisePanel1);
        
        forsorgerJa.addItemListener(new ItemListener()
        {
        @Override
        public void itemStateChanged(ItemEvent e)
        {
    if (e.getStateChange() == ItemEvent.SELECTED)
        {
        antbarn.setEnabled(true);
        antbarnLabel.setEnabled(true);
        }
    else if (e.getStateChange() == ItemEvent.DESELECTED)
    {
        antbarn.setEnabled(false);
        antbarnLabel.setEnabled(false);
    }
        
        }});
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }
}
