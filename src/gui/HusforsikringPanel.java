/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import objekter.*;

/**
 * @author Odd, Thomas, Marthe
 */
public class HusforsikringPanel extends JPanel implements ActionListener
{
    private final JTextField husAdresse;
    private final JTextField husAr;
    private final JTextField husKvm;
    private final JTextField belopHus;
    private final JTextField belopHusInnbo;
    private final JTextField husTilbud;
    JComboBox<String> hustypevelger;
    private final String[] hustype = {"","Enebolig", "Tomannsbolig", "Tremannsbolig", "Firemannsbolig", "Rekkehus"};
    JComboBox<String> husmaterialevelger;
    private final String[] husmateriale = {"","Mur", "Tre", "Brannfast", "Laftet tømmer"};
    private final String[] husstandard = {"","Normal standard", "Bedre standard", "Høy standard"};
    JComboBox<String> husstandardvelger;
    String[] egenandel = {"", "2000", "4000", "8000", "12000", "16000", "20000", "30000"};
    JComboBox<String> egenandelsvelger;
    private final JButton husGiTilbud;
    private final Kunde kunde;
    
    public HusforsikringPanel(Kunde k)
    {
        kunde = k;
        husAdresse = new JTextField( 7 );
        belopHus = new JTextField( 7 );
        belopHusInnbo = new JTextField( 7 );
        husTilbud = new JTextField( 7 );
        husAr = new JTextField( 4 );
        husKvm = new JTextField( 4 );
        hustypevelger = new JComboBox<>(hustype);
        husmaterialevelger = new JComboBox<>(husmateriale);
        husstandardvelger = new JComboBox<>(husstandard);
        egenandelsvelger = new JComboBox<>(egenandel);
        husGiTilbud = new JButton("Tegn forsikring");
        
        JPanel tegnHusPanel1 = new JPanel();
        tegnHusPanel1.setLayout(new GridLayout(7,4,5,10));
        tegnHusPanel1.add(new JLabel("Adresse: "));
        tegnHusPanel1.add(husAdresse);
        tegnHusPanel1.add(new JLabel("Byggemateriale: "));
        tegnHusPanel1.add(husmaterialevelger);
        tegnHusPanel1.add(new JLabel("Hustype: "));
        tegnHusPanel1.add(hustypevelger);
        tegnHusPanel1.add(new JLabel("Standard: "));
        tegnHusPanel1.add(husstandardvelger);
        tegnHusPanel1.add(new JLabel("Kvadratmeter: "));
        tegnHusPanel1.add(husKvm);
        tegnHusPanel1.add(new JLabel("Byggeår: "));
        tegnHusPanel1.add(husAr);
        tegnHusPanel1.add(new JLabel("Egenandel: "));
        tegnHusPanel1.add(egenandelsvelger);
        tegnHusPanel1.add(new JLabel("Forsikringsbeløp: "));
        tegnHusPanel1.add(belopHus);
        tegnHusPanel1.add(new JLabel("Forskringsbeløp innbo: "));
        tegnHusPanel1.add(belopHusInnbo);
        tegnHusPanel1.add(new JLabel());
        tegnHusPanel1.add(new JLabel());
        tegnHusPanel1.add(new JLabel("Foreslått tilbud: "));
        tegnHusPanel1.add(husTilbud);
        tegnHusPanel1.add(new JLabel());
        tegnHusPanel1.add(husGiTilbud);
        add(tegnHusPanel1);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }
}
