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
import register.*;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class FritidsboligforsikringPanel extends JPanel implements ActionListener
{
    HovedRegister register;
    private final JTextField fritidAdresse;
    private final JTextField belopFritid;
    private final JTextField belopFritidInnbo;
    private final JTextField fritidTilbud;
    private final JTextField fritidKvm;
    private final JTextField fritidAr;
    private final JButton fritidGiTilbud;
    private final JButton beregnPris;
    private final JRadioButton utleidJa;
    private final JRadioButton utleidNei;
    JComboBox<String> fritidtypevelger;
    private final String[] fritidtype = {"","Hus/Hytte", "Rekkehus", "Leilighet"};
    JComboBox<String> fritidmaterialevelger;
    private final String[] fritidmateriale = {"","Mur", "Tre", "Brannfast", "Laftet tømmer"};
    private final String[] fritidstandard = {"","Normal standard", "Bedre standard", "Høy standard"};
    JComboBox<String> fritidstandardvelger;
    String[] egenandel = {"", "2000", "4000", "8000", "12000", "16000", "20000", "30000"};
    JComboBox<String> egenandelsvelger;
    
    private String typevalget;  
    private String materialevalget;
    private String standardvalget;
    private int egenandelvalget;
    private boolean utleid_b;
    private boolean alarm_b;
    private String adr;
    private int ar;
    private int kvm;
    private int belop;
    private int belopInnbo;
    
    private final Kunde kunde;
    
    public FritidsboligforsikringPanel(Kunde k)
    {
        kunde = k;
        register = new HovedRegister();
        fritidAdresse = new JTextField( 7 );
        fritidKvm = new JTextField(5);
        fritidAr = new JTextField( 4 );
        belopFritid = new JTextField( 7 );
        belopFritidInnbo = new JTextField( 7 );
        fritidTilbud = new JTextField( 7 );
        fritidtypevelger = new JComboBox<>(fritidtype);
        fritidmaterialevelger = new JComboBox<>(fritidmateriale);
        fritidstandardvelger = new JComboBox<>(fritidstandard);
        egenandelsvelger = new JComboBox<>(egenandel);
        utleidJa = new JRadioButton("Ja");
        utleidJa.setMnemonic(KeyEvent.VK_J);
        utleidNei = new JRadioButton("Nei");
        utleidNei.setMnemonic(KeyEvent.VK_N);
        ButtonGroup utleid = new ButtonGroup();
        utleid.add(utleidJa);
        utleid.add(utleidNei);
        fritidGiTilbud = new JButton("Tegn forsikring");
        beregnPris = new JButton("Beregn pris");
        
        JPanel tegnFritidPanel1 = new JPanel();
        JPanel utleie = new JPanel();
        tegnFritidPanel1.setLayout(new GridLayout(7,4,1,5));
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
        tegnFritidPanel1.add(new JLabel("Egenandel: "));
        tegnFritidPanel1.add(egenandelsvelger);
        tegnFritidPanel1.add(new JLabel("Forsikringsbeløp: "));
        tegnFritidPanel1.add(belopFritid);
        tegnFritidPanel1.add(new JLabel("Forsikringsbeløp innbo: "));
        tegnFritidPanel1.add(belopFritidInnbo);
        tegnFritidPanel1.add(beregnPris);
        tegnFritidPanel1.add(new JLabel("Foreslått tilbud: "));
        tegnFritidPanel1.add(fritidTilbud);
        tegnFritidPanel1.add(fritidGiTilbud);
        add(tegnFritidPanel1);
        
        fritidGiTilbud.addActionListener(this);
        beregnPris.addActionListener(this);
    }
    
    public void beregnPris()
    {
        if(hentInfo())
        {
            //Beregn pris
            //fritidTilbud.setText();
        }
    }
    
    public boolean hentInfo()
    {
           
            int type_n = fritidtypevelger.getSelectedIndex(); 
            int materiale_n = fritidmaterialevelger.getSelectedIndex();
            int standard_n = fritidstandardvelger.getSelectedIndex();
            int egenandel_n = egenandelsvelger.getSelectedIndex();
            if (type_n == 0 || materiale_n == 0 || standard_n == 0 || egenandel_n == 0 || (!utleidJa.isSelected() && !utleidNei.isSelected()))
            {
                String ut = "Det mangler informasjon om:\n";
                    if (type_n == 0)
                    {ut += "Boligtype\n";}
                    if (materiale_n == 0)
                    {ut += "Byggemateriale\n";}
                    if (standard_n == 0)
                    {ut += "Standard\n";}
                    if (egenandel_n == 0)
                    {ut += "Egenandel\n";}
                    if (!utleidJa.isSelected() && !utleidNei.isSelected())
                    {ut += "Utleievalg\n";}
                    
                    ut += "\nVennligst fyll ut denne informasjonen og prøv igjen.";
                            JOptionPane.showMessageDialog(null, ut, "Feilmelding",
                                                JOptionPane.ERROR_MESSAGE);
            return false;
            }
            else
            {
                if (utleidJa.isSelected() && !utleidNei.isSelected())
                    utleid_b = true;
                else if (!utleidJa.isSelected() && utleidNei.isSelected())
                    utleid_b = false;
                
                egenandelvalget = Integer.parseInt(egenandelsvelger.getItemAt(egenandel_n));
                typevalget = fritidtypevelger.getItemAt(type_n); 
                materialevalget = fritidmaterialevelger.getItemAt(materiale_n);
                standardvalget = fritidstandardvelger.getItemAt(standard_n);
                adr = fritidAdresse.getText();
                ar = Integer.parseInt(fritidAr.getText());
                kvm = Integer.parseInt(fritidKvm.getText());
                belop = Integer.parseInt(belopFritid.getText());
                belopInnbo = Integer.parseInt(belopFritidInnbo.getText());
                return true;
                    
            }
            
            
    }
    
    public void tegnNy()
    {
        if (hentInfo())
        {
            Forsikring forsikringen = register.nyFritidsboligforsikring(kunde, egenandelvalget, adr, ar, 
                       typevalget, materialevalget, standardvalget, kvm, belop, belopInnbo, alarm_b, utleid_b);
            JOptionPane.showMessageDialog(null, "Du har nå tegnet fritidsboligforsikring med nummer " + forsikringen.getForsikringsnummer() + " på " + kunde.getFornavn() + " " + kunde.getEtternavn() , "Bekreftelse", JOptionPane.INFORMATION_MESSAGE);
        
            System.out.println(forsikringen);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == fritidGiTilbud)
        {
            tegnNy();
        }
        else if (e.getSource() == beregnPris)
        {
            beregnPris();
        }
    }
}
