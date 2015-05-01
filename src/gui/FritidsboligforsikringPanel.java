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
<<<<<<< HEAD
 * @author Marthejansonskogen
=======
 * @author Odd, Thomas, Marthe
>>>>>>> origin/master
 */
public class FritidsboligforsikringPanel extends JPanel implements ActionListener
{
    private final JTextField fritidAdresse;
    private final JTextField belopFritid;
    private final JTextField belopFritidInnbo;
    private final JTextField fritidTilbud;
    private final JTextField fritidKvm;
    private final JTextField fritidAr;
    private final JButton fritidGiTilbud;
    private final JRadioButton utleidJa;
    private final JRadioButton utleidNei;
    JComboBox<String> fritidtypevelger;
    private final String[] fritidtype = {"","Hus/Hytte", "Rekkehus", "Leilighet"};
    JComboBox<String> fritidmaterialevelger;
    private final String[] fritidmateriale = {"","Mur", "Tre", "Brannfast", "Laftet tømmer"};
    private final String[] fritidstandard = {"","Normal standard", "Bedre standard", "Høy standard"};
    JComboBox<String> fritidstandardvelger;
    private final Kunde kunde;
    
    public FritidsboligforsikringPanel(Kunde k)
    {
        kunde = k;
        fritidAdresse = new JTextField( 7 );
        fritidKvm = new JTextField(5);
        fritidAr = new JTextField( 4 );
        belopFritid = new JTextField( 7 );
        belopFritidInnbo = new JTextField( 7 );
        fritidTilbud = new JTextField( 7 );
        fritidtypevelger = new JComboBox<>(fritidtype);
        fritidmaterialevelger = new JComboBox<>(fritidmateriale);
        fritidstandardvelger = new JComboBox<>(fritidstandard);
        utleidJa = new JRadioButton("Ja");
        utleidJa.setMnemonic(KeyEvent.VK_J);
        utleidNei = new JRadioButton("Nei");
        utleidNei.setMnemonic(KeyEvent.VK_N);
        ButtonGroup utleid = new ButtonGroup();
        utleid.add(utleidJa);
        utleid.add(utleidNei);
        fritidGiTilbud = new JButton("Tegn forsikring");
        
        JPanel tegnFritidPanel1 = new JPanel();
        JPanel utleie = new JPanel();
        tegnFritidPanel1.setLayout(new GridLayout(6,4,1,5));
        utleie.add(utleidJa);
        utleie.add(utleidNei);
        tegnFritidPanel1.add(new JLabel("Adresse: "));
        tegnFritidPanel1.add(fritidAdresse);
        tegnFritidPanel1.add(new JLabel("Byggeår: "));
        tegnFritidPanel1.add(fritidAr);
        tegnFritidPanel1.add(new JLabel("Kvadratmeter: "));
        tegnFritidPanel1.add(fritidKvm);
        tegnFritidPanel1.add(new JLabel("Boligtype: "));
        tegnFritidPanel1.add(fritidtypevelger);
        tegnFritidPanel1.add(new JLabel("Byggemateriale: "));
        tegnFritidPanel1.add(fritidmaterialevelger);
        tegnFritidPanel1.add(new JLabel("Standard: "));
        tegnFritidPanel1.add(fritidstandardvelger);
        tegnFritidPanel1.add(new JLabel("Utleid: "));
        tegnFritidPanel1.add(utleie);
        tegnFritidPanel1.add(new JLabel("Forsikringsbeløp: "));
        tegnFritidPanel1.add(belopFritid);
        tegnFritidPanel1.add(new JLabel("Forsikringsbeløp innbo: "));
        tegnFritidPanel1.add(belopFritidInnbo);
        tegnFritidPanel1.add(new JLabel("Foreslått tilbud: "));
        tegnFritidPanel1.add(fritidTilbud);
        tegnFritidPanel1.add(fritidGiTilbud);
        add(tegnFritidPanel1);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }
}
