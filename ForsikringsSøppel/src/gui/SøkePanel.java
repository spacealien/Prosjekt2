/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import objekter.Kunde;

/**
 *
 * @author Odd
 */
public class SøkePanel extends JPanel implements ActionListener
{
    private final JTextField søkeFelt;
    private final JButton søkeKnapp;
    private final JPanel wrapper_1;
    private final JPanel wrapper_2;
    private final AnsattVindu vindu;
    
    public SøkePanel( AnsattVindu vindu )
    {
        this.vindu = vindu;
        setLayout(new FlowLayout() );
        søkeFelt = new JTextField(25);
        søkeKnapp = new JButton("Søk");
        søkeKnapp.addActionListener(this);
        wrapper_1 = new JPanel();
        wrapper_2 = new JPanel();
        
        wrapper_1.setLayout( new FlowLayout() );
        wrapper_1.add(søkeFelt);
        wrapper_1.add(søkeKnapp);
        this.setLayout( new BorderLayout() );
        this.add(wrapper_1, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == søkeKnapp )
        {
            String søkeord = søkeFelt.getText();
            Kunde kunde = vindu.getMetoder().finnKundeMedPersonnummer(søkeord);

        }
            
            
    }
}
