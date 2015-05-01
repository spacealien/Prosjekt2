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
<<<<<<< HEAD
 * @author Marthejansonskogen
=======
 * @author Odd, Thomas, Marthe
>>>>>>> origin/master
 */
public class BilforsikringPanel extends JPanel implements ActionListener
{
    private final JTextField bilRegnr;
    private final JTextField bilRegAr;
    private final JTextField bilHk;
    private final JTextField bilModell;
    private final JTextField bilKmstand;
    private final JTextField bilTilbud;
    private final JRadioButton garasjeJa;
    private final JRadioButton garasjeNei;
    private final JButton annenEier;
    private final JButton bilGiTilbud;
    String[] biltype = {"", "Småbil", "Lastebil", "Vogntog", "Varebil", "SUV"};
    JComboBox<String> biltypevelger;

    String[] bilmerke = {"", "Mercedes", "Toyota", "BMW", "Volkswagen", "Ford",
                        "Audi", "Opel", "Citroën", "Alfa Romeo", "Porsche",
                        "Lamborghini"};
    JComboBox<String> bilmerkevelger;
    
    String[] kjorelengde = {"", "8000", "12000", "16000", "20000", "25000", "30000", "Ubegrenset"};
    JComboBox<String> kjorelengdevelger;
    
    public BilforsikringPanel()
    {
        bilRegnr = new JTextField( 7 );
        bilRegAr = new JTextField( 4 );
        bilModell = new JTextField( 10 );
        bilHk = new JTextField( 7 );
        bilKmstand = new JTextField( 6 );
        bilTilbud = new JTextField( 6 );
        garasjeJa = new JRadioButton("Ja");
        garasjeNei = new JRadioButton("Nei");
        garasjeJa.setMnemonic(KeyEvent.VK_J);
        garasjeNei.setMnemonic(KeyEvent.VK_N);
        ButtonGroup garasje = new ButtonGroup();
        garasje.add(garasjeJa);
        garasje.add(garasjeNei);
        bilGiTilbud = new JButton("Tegn forsikring");
        annenEier = new JButton("Trykk her for annen eier");
        biltypevelger = new JComboBox<>(biltype);
        //biltypevelger.setSelectedIndex(4);
        bilmerkevelger = new JComboBox<>(bilmerke);
        //bilmerkevelger.setSelectedIndex(10);
        kjorelengdevelger = new JComboBox<>(kjorelengde);
        //kjorelengdevelger.setSelectedIndex(0);
    
        JPanel garasjen = new JPanel();
        JPanel tegnBilPanel1 = new JPanel();
        tegnBilPanel1.setLayout(new GridLayout(6,4,2,10));
        garasjen.add(garasjeJa);
        garasjen.add(garasjeNei);
        tegnBilPanel1.add(new JLabel("Registreringsnummer: "));
        tegnBilPanel1.add(bilRegnr);
        tegnBilPanel1.add(new JLabel("Registreringsår: "));
        tegnBilPanel1.add(bilRegAr);
        tegnBilPanel1.add(new JLabel("Modell: "));
        tegnBilPanel1.add(bilModell);
        tegnBilPanel1.add(new JLabel("Hestekrefter: "));
        tegnBilPanel1.add(bilHk);
        tegnBilPanel1.add(new JLabel("Kilometerstand: "));
        tegnBilPanel1.add(bilKmstand);
        tegnBilPanel1.add(new JLabel("Velg biltype: "));
        tegnBilPanel1.add(biltypevelger);
        tegnBilPanel1.add(new JLabel("Velg bilmerke: "));
        tegnBilPanel1.add(bilmerkevelger);
        tegnBilPanel1.add(new JLabel("Garasje: "));
        tegnBilPanel1.add(garasjen);
        tegnBilPanel1.add(new JLabel("Årlig forventet kjørelengde: "));
        tegnBilPanel1.add(kjorelengdevelger);
        tegnBilPanel1.add(new JLabel("Er eier annen enn kunde?"));
        tegnBilPanel1.add(annenEier);
        tegnBilPanel1.add(new JLabel());
        tegnBilPanel1.add(new JLabel("Foreslått tilbud: "));
        tegnBilPanel1.add(bilTilbud);
        tegnBilPanel1.add(bilGiTilbud);
        add(tegnBilPanel1);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }
}
