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
import register.*;

/**
 * @author Odd, Thomas, Marthe
 */
public class HusforsikringPanel extends JPanel implements ActionListener
{
    private HovedRegister register;
    private final JTextField husAdresse;
    private final JTextField husAr;
    private final JTextField husKvm;
    private final JTextField belopHus;
    private final JTextField belopHusInnbo;
    private final JTextField husTilbud;
    private final JTextField prisenar;
    private final JTextField prisenmnd;
    JComboBox<String> hustypevelger;
    private final String[] hustype = {"","Enebolig", "Tomannsbolig", "Tremannsbolig", "Firemannsbolig", "Rekkehus"};
    JComboBox<String> husmaterialevelger;
    private final String[] husmateriale = {"","Mur", "Tre", "Brannfast", "Laftet tømmer"};
    private final String[] husstandard = {"","Normal standard", "Bedre standard", "Høy standard"};
    JComboBox<String> husstandardvelger;
    String[] egenandel = {"", "2000", "4000", "8000", "12000", "16000", "20000", "30000"};
    JComboBox<String> egenandelsvelger;
    private final JRadioButton alarmJa, alarmNei;
    private final JButton husGiTilbud;
    private final JButton beregnPris;
    private final Kunde kunde;
    
    private String hustypevalget;
    private String husmaterialevalget;
    private String husstandardvalget;
    private int egenandelvalget;
    private String adr;
    private int ar;
    private int kvm;
    private int belop;
    private int belopInnbo;
    private boolean alarm_b;
    
    public HusforsikringPanel(Kunde k)
    {
        register = new HovedRegister();
        kunde = k;
        husAdresse = new JTextField( 7 );
        belopHus = new JTextField( 7 );
        belopHusInnbo = new JTextField( 7 );
        husTilbud = new JTextField( 7 );
        husAr = new JTextField( 4 );
        husKvm = new JTextField( 4 );
        prisenar = new JTextField( 6 );
        prisenmnd = new JTextField( 6 );
        hustypevelger = new JComboBox<>(hustype);
        husmaterialevelger = new JComboBox<>(husmateriale);
        husstandardvelger = new JComboBox<>(husstandard);
        egenandelsvelger = new JComboBox<>(egenandel);
        alarmJa = new JRadioButton("Ja");
        alarmJa.setMnemonic(KeyEvent.VK_J);
        alarmNei = new JRadioButton("Nei");
        alarmNei.setMnemonic(KeyEvent.VK_N);
        ButtonGroup utleid = new ButtonGroup();
        utleid.add(alarmJa);
        utleid.add(alarmNei);
        beregnPris = new JButton("Beregn pris");
        husGiTilbud = new JButton("Tegn forsikring");
        
        JPanel tegnHusPanel1 = new JPanel();
        JPanel alarmPanel = new JPanel();
        alarmPanel.add(alarmJa);
        alarmPanel.add(alarmNei);
        tegnHusPanel1.setLayout(new GridLayout(8,4,5,10));
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
        tegnHusPanel1.add(new JLabel("Alarm "));
        tegnHusPanel1.add(alarmPanel);
        tegnHusPanel1.add(new JLabel("Egenandel: "));
        tegnHusPanel1.add(egenandelsvelger);
        tegnHusPanel1.add(new JLabel("Forsikringsbeløp: "));
        tegnHusPanel1.add(belopHus);
        tegnHusPanel1.add(new JLabel("Forskringsbeløp innbo: "));
        tegnHusPanel1.add(belopHusInnbo);
        tegnHusPanel1.add(new JLabel());
        tegnHusPanel1.add(new JLabel());
        tegnHusPanel1.add(new JLabel());
        tegnHusPanel1.add(new JLabel());
        tegnHusPanel1.add(beregnPris);
        tegnHusPanel1.add(new JLabel());
        tegnHusPanel1.add(new JLabel("Årlig pris: "));
        tegnHusPanel1.add(husTilbud);
        tegnHusPanel1.add(new JLabel());
        tegnHusPanel1.add(new JLabel());
        tegnHusPanel1.add(new JLabel());
        tegnHusPanel1.add(husGiTilbud);
        add(tegnHusPanel1);
        
        husGiTilbud.addActionListener(this);
        beregnPris.addActionListener(this);
    }
    
    public boolean hentInfo()
    {
      
            int hustype_n = hustypevelger.getSelectedIndex();
            int materiale_n = husmaterialevelger.getSelectedIndex();
            int husstandard_n = husstandardvelger.getSelectedIndex();
            int egenandel_n = egenandelsvelger.getSelectedIndex();
            
            if (hustype_n == 0 || materiale_n == 0 || husstandard_n == 0 || egenandel_n == 0 || (!alarmJa.isSelected() && !alarmNei.isSelected()))
            {
                String ut = "Det mangler informasjon om:\n";
            
                if (hustype_n == 0)
                {ut += "Hustype\n";}
                
                if (materiale_n == 0)
                {ut += "Byggemateriale\n";}
                
                if (husstandard_n == 0)
                    {ut += "Husstandard\n";}
                if (egenandel_n == 0)
                    {ut += "Egenandel\n";}
                if (!alarmJa.isSelected() && !alarmNei.isSelected())
                {
                    ut += "Alarmvalg\n";
                }
                ut += "\nVennligst fyll ut denne informasjonen og prøv igjen.";
            JOptionPane.showMessageDialog(null, ut, "Feilmelding",
                                                JOptionPane.ERROR_MESSAGE);
            return false;
            }
            else
            {
                
            hustypevalget = hustypevelger.getItemAt(hustype_n);
            husmaterialevalget = husmaterialevelger.getItemAt(materiale_n);
            husstandardvalget = husstandardvelger.getItemAt(husstandard_n);
            egenandelvalget = Integer.parseInt(egenandelsvelger.getItemAt(egenandel_n));
            adr = husAdresse.getText();
            ar = Integer.parseInt(husAr.getText());
            kvm = Integer.parseInt(husKvm.getText());
            belop = Integer.parseInt(belopHus.getText());
            belopInnbo = Integer.parseInt(belopHusInnbo.getText());
            
            if (alarmJa.isSelected() && !alarmNei.isSelected())
                    alarm_b = true;
                else if (!alarmJa.isSelected() && alarmNei.isSelected())
                    alarm_b = false;
            
            return true;
            }    
    }
    
    public void beregnPris()
    {
      if (hentInfo())
      {
            //Beregner prisen
      }
             
    }
    public void tegnNy()
    {
            if (hentInfo())
            {
            
            Forsikring forsikringen = register.nyHusforsikring(kunde, 
                    egenandelvalget, adr, ar, hustypevalget, husmaterialevalget, 
                    husstandardvalget, kvm, belop, belopInnbo, alarm_b);
            System.out.println(forsikringen);
            }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == husGiTilbud)
        {
            tegnNy();
        }
        else if(e.getSource() == beregnPris)
        {
            beregnPris();
        }
    }
}
