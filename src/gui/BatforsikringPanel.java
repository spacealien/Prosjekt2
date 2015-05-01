/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Marthejansonskogen
 */
public class BatforsikringPanel extends JPanel implements ActionListener
{
    private final JTextField batRegnr;
    private final JTextField batModell;
    private final JTextField batTilbud;
    private final JTextField batMerke;
    private final JTextField batLengde;
    private final JTextField batHk;
    private final JTextField batArsmodell;
    private final JRadioButton vekterJa;
    private final JRadioButton vekterNei;
    private final String[] battype = {"","Skjærgårdsjeep", "Cabin cruiser", "Rib", "Annen småbåt",
                        "Speedbåt", "Seilbåt", "Snekke"};
    JComboBox<String> battypevelger;
    private final JButton batGiTilbud;
    
    public BatforsikringPanel()
    {
        batRegnr = new JTextField( 7 );
        batModell = new JTextField( 7 );
        batTilbud = new JTextField( 7 );
        batMerke = new JTextField( 7 );
        batLengde = new JTextField( 7 );
        batArsmodell = new JTextField( 7 );
        batHk = new JTextField(4);
        vekterJa = new JRadioButton("Ja");
        vekterNei = new JRadioButton("Nei");
        vekterJa.setMnemonic(KeyEvent.VK_J);
        vekterNei.setMnemonic(KeyEvent.VK_N);
        ButtonGroup vektere = new ButtonGroup();
        vektere.add(vekterJa);
        vektere.add(vekterNei);
        battypevelger = new JComboBox<>(battype);
        batGiTilbud = new JButton("Tegn forsikring");
        
        JPanel vekt = new JPanel();
        JPanel tegnBatPanel1 = new JPanel();
        tegnBatPanel1.setLayout(new GridLayout(6,4,2,10));
        vekt.add(vekterJa);
        vekt.add(vekterNei);
        tegnBatPanel1.add(new JLabel("Reg.nummer: "));
        tegnBatPanel1.add(batRegnr);
        tegnBatPanel1.add(new JLabel("Merke: "));
        tegnBatPanel1.add(batMerke);
        tegnBatPanel1.add(new JLabel("Modell: "));
        tegnBatPanel1.add(batModell);
        tegnBatPanel1.add(new JLabel("Lengde: "));
        tegnBatPanel1.add(batLengde);
        tegnBatPanel1.add(new JLabel("Årsmodell: "));
        tegnBatPanel1.add(batArsmodell);
        tegnBatPanel1.add(new JLabel("Hestekrefter: "));
        tegnBatPanel1.add(batHk);
        tegnBatPanel1.add(new JLabel("Type båt: "));
        tegnBatPanel1.add(battypevelger);
        tegnBatPanel1.add(new JLabel("Vekter: "));
        tegnBatPanel1.add(vekt);
        tegnBatPanel1.add(new JLabel());
        tegnBatPanel1.add(new JLabel("Foreslått tilbud: "));
        tegnBatPanel1.add(batTilbud);
        tegnBatPanel1.add(batGiTilbud);
        add(tegnBatPanel1);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }
}
