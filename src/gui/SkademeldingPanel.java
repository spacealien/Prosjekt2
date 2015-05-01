/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import objekter.*;

/**
 *
 * @author Marthejansonskogen
 */
public class SkademeldingPanel extends JPanel implements ActionListener
{
    private final JTextField skadeDato;
    private final JTextField skadeType;
    private final JTextArea skadeBeskrivelse;
    private final JTextField skadeTakst;
    private final JButton sendInnSkade;
    private Kunde kunde;
    
    public SkademeldingPanel(Kunde k)
    {
        kunde = k;
        skadeDato = new JTextField( 7 );
        skadeType = new JTextField( 7 );
        skadeBeskrivelse = new JTextArea( 100, 100 );
        skadeTakst = new JTextField( 7 );
        sendInnSkade = new JButton("Send inn skade");  
        
        JPanel tegnSkaden = new JPanel();
        JPanel tegnSkade = new JPanel();
        tegnSkade.setLayout(new GridLayout(4,2,1,5));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        tegnSkade.add(new JLabel("Dato: (ddmmåååå) "));
        tegnSkade.add(skadeDato);
        tegnSkade.add(new JLabel("Type: "));
        tegnSkade.add(skadeType);
        tegnSkade.add(new JLabel("Takst: "));
        tegnSkade.add(skadeTakst);
        tegnSkaden.add(new JLabel("Beskrivelse av skaden: "));
        tegnSkaden.add(skadeBeskrivelse);
        tegnSkade.setMaximumSize(new Dimension(300,100));
        tegnSkaden.setMaximumSize(new Dimension(400,300));
        add(tegnSkade);
        add(tegnSkaden);
        add(Box.createRigidArea(new Dimension(40, 40)));
        add(sendInnSkade);
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }
}
