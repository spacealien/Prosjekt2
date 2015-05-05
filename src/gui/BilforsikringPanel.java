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
public class BilforsikringPanel extends JPanel implements ActionListener
{
 
    private AnsattVindu vindu;
    private HovedRegister register;
    private Eier eier;
    
    private final JTextField eierFornavn;
    private final JTextField eierEtternavn;
    private final JTextField eierTlf;
    private final JTextField eierAdresse;
    
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
    private final JButton beregnPris;
    String[] biltype = {"", "Personbil", "Lastebil", "Vogntog", "Varebil", "SUV"};
    JComboBox<String> biltypevelger;

    String[] bilmerke = {"", "Mercedes", "Toyota", "BMW", "Volkswagen", "Ford",
                        "Audi", "Opel", "Citroën", "Alfa Romeo", "Porsche",
                        "Lamborghini"};
    JComboBox<String> bilmerkevelger;
    
    String[] kjorelengde = {"", "8000", "12000", "16000", "20000", "25000", "30000",
                            "Ubegrenset"};
    JComboBox<String> kjorelengdevelger;
    String[] foreralder = {"", "Bilfører < 23 år", "Bilfører mellom 23 - 25 år", 
                           "Bilfører > 25 år"};
    JComboBox<String> aldervelger;
    String[] dekning = {"", "Delkasko", "Kasko", "Superkasko"};
    JComboBox<String> dekningvelger;
    String[] egenandel = {"", "2000", "4000", "8000", "12000", "16000", "20000", 
                            "30000"};
    JComboBox<String> egenandelsvelger;
    String[] bonus = {"", "-50%", "-40%", "-30%", "-20%", "-10%", "0%", "10%", 
                     "20%", "30%", "40%", "50%", "60%", "70%", "70% 2 år",
                     "70% 3 år", "70% 4 år", "70% 5 år", "75%", "75% 2 år",
                     "75% 3 år", "75% 4 år", "75% 5 år", "75% >5 år"};
    JComboBox<String> bonusvelger;
    private final Kunde kunde;
    
    private String regnr;
    private String modell;
    private int hk;
    private int ar;
    private int kmstand;  
    private String typevalget;
    private String merkevalget;
    private int lengdevalget;
    private String b;
    private double bonusen;
    private int egenandelvalget;
    private boolean garasje;
    private int antAr = 1;
    
    public BilforsikringPanel(Kunde k, AnsattVindu v)
    {
        vindu = v;
        register = vindu.getRegister();
                
        eierFornavn = new JTextField(20);
        eierEtternavn = new JTextField(20);
        eierTlf = new JTextField(8);
        eierAdresse = new JTextField(15);
        
        JPanel eierPanel = new JPanel();
        eierPanel.add(new JLabel("Fornavn: "));
        eierPanel.add(eierFornavn);
        eierPanel.add(new JLabel("Etternavn: "));
        eierPanel.add(eierEtternavn);
        eierPanel.add(new JLabel("Telefonnummer: "));
        eierPanel.add(eierTlf);
        eierPanel.add(new JLabel("Adresse: "));
        eierPanel.add(eierAdresse);
        
        kunde = k;
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
        beregnPris = new JButton("Beregn pris");
        annenEier = new JButton("Trykk her for annen eier");
        biltypevelger = new JComboBox<>(biltype);
        //biltypevelger.setSelectedIndex(4);
        bilmerkevelger = new JComboBox<>(bilmerke);
        //bilmerkevelger.setSelectedIndex(10);
        kjorelengdevelger = new JComboBox<>(kjorelengde);
        //kjorelengdevelger.setSelectedIndex(0);
        egenandelsvelger = new JComboBox<>(egenandel);
        dekningvelger = new JComboBox<>(dekning);
        aldervelger = new JComboBox<>(foreralder);
        bonusvelger = new JComboBox<>(bonus);
    
        JPanel garasjen = new JPanel();
        JPanel tegnBilPanel1 = new JPanel();
        tegnBilPanel1.setLayout(new GridLayout(9,4,2,10));
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
        tegnBilPanel1.add(new JLabel("Velg fabrikant: "));
        tegnBilPanel1.add(bilmerkevelger);
        tegnBilPanel1.add(new JLabel("Garasje: "));
        tegnBilPanel1.add(garasjen);
        tegnBilPanel1.add(new JLabel("Årlig forventet kjørelengde: "));
        tegnBilPanel1.add(kjorelengdevelger);
        tegnBilPanel1.add(new JLabel("Yngste bilførers alder: "));
        tegnBilPanel1.add(aldervelger);
        tegnBilPanel1.add(new JLabel("Dekning: "));
        tegnBilPanel1.add(dekningvelger);
        tegnBilPanel1.add(new JLabel("Bonus: "));
        tegnBilPanel1.add(bonusvelger);
        tegnBilPanel1.add(new JLabel("Velg egenandel: "));
        tegnBilPanel1.add(egenandelsvelger);
        tegnBilPanel1.add(new JLabel("Er eier annen enn kunde?"));
        tegnBilPanel1.add(annenEier);
        tegnBilPanel1.add(beregnPris);
        tegnBilPanel1.add(new JLabel("Foreslått tilbud: "));
        tegnBilPanel1.add(bilTilbud);
        tegnBilPanel1.add(new JLabel());
        tegnBilPanel1.add(bilGiTilbud);
        add(tegnBilPanel1);
        
        annenEier.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent e)
        {
    if (e.getSource() == annenEier)
        {
            int result = JOptionPane.showConfirmDialog(null, eierPanel, 
               "Vennligst fyll ut bileiers kontaktinformasjon:", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION)
      {
          //Her skal det endres
         System.out.println("Fornavn: " + eierFornavn.getText());
         System.out.println("Etternavn: " + eierEtternavn.getText());
         System.out.println("Telefonnummer: " + eierTlf.getText());
         System.out.println("Addresse: " + eierAdresse.getText());
         eier = new Eier(eierFornavn.getText(), eierEtternavn.getText(), eierAdresse.getText(), eierTlf.getText());
      }    
                
        }}});
        
        bilGiTilbud.addActionListener(this);
        beregnPris.addActionListener(this);
    }
    
    public boolean hentInfo()
    {
        
             
        
        
        if (garasjeJa.isSelected() && !garasjeNei.isSelected())
                    garasje = true;
        else if (!garasjeJa.isSelected() && garasjeNei.isSelected())
                    garasje = false;
              
            int type_n = biltypevelger.getSelectedIndex();
            int merke_n = bilmerkevelger.getSelectedIndex();
            int lengde_n = kjorelengdevelger.getSelectedIndex();
            int bonus_n = bonusvelger.getSelectedIndex();
            int egenandel_n = egenandelsvelger.getSelectedIndex();
            int alder_n = aldervelger.getSelectedIndex();
            int dekning_n = dekningvelger.getSelectedIndex();
            
            
            if (lengde_n == 0 || merke_n == 0 || type_n == 0 || egenandel_n == 0 
               || alder_n == 0 || dekning_n == 0 || bonus_n == 0 || 
                    (!garasjeJa.isSelected() && !garasjeNei.isSelected()))
            {
                String ut = "Det mangler informasjon om:\n";
                if (lengde_n == 0)
                {ut += "Maximum kjørelengde\n";}
                
                    if (merke_n == 0)
                    {ut += "Fabrikant\n";}
                
                    if (type_n == 0)
                    {ut += "Biltype\n";}
                    
                    if (egenandel_n == 0)
                    {ut += "Egenandel\n";}
                    
                    if (alder_n == 0)
                    {ut += "Bilførers alder\n";}
                    
                    if (dekning_n == 0)
                    {ut += "Dekning\n";}
                    
                    if (bonus_n == 0)
                    {ut += "Bonus\n";}
                    
                    if(!garasjeJa.isSelected() && !garasjeNei.isSelected())
                    {ut += "Garasjevalg";}
            
                    JOptionPane.showMessageDialog(null, ut, "Feilmelding",
                                                JOptionPane.ERROR_MESSAGE);
                    return false;
            }
            else
            {
            switch (bonus_n)
            {
                case 1:
                    bonusen = -0.50;
                    break;
                case 2:
                     bonusen = -0.40;
                    break;   
                case 3:
                    bonusen = -0.30;
                    break;
                case 4:
                    bonusen = -0.20;
                    break;
                case 5:
                    bonusen = -0.10;
                    break;
                case 6:
                     bonusen = 0.00;
                    break;   
                case 7:
                   bonusen = 0.10;
                    break;
                case 8:
                    bonusen = 0.20;
                    break;
                case 9:
                     bonusen = 0.30;
                    break;   
                case 10:
                    bonusen = 0.40;
                    break;
                case 11:
                    bonusen = 0.50;
                    break;
                case 12:
                    bonusen = 0.60;
                    break;
                case 13: 
                    bonusen = 0.70;
                    break;
                case 14:
                    bonusen = 0.70;
                    antAr = 2;
                    break;
                case 15:  
                    bonusen = 0.70;
                    antAr = 3;
                    break; 
                case 16:
                    bonusen = 0.70;
                    antAr = 4;
                    break;
                case 17:
                    bonusen = 0.70;
                    antAr = 5;
                    break;
                case 18:
                     bonusen = 0.75;
                    break; 
                case 19:
                     bonusen = 0.75;
                     antAr = 2;
                    break; 
                case 20:
                     bonusen = 0.75;
                     antAr = 3;
                    break; 
                case 21:
                     bonusen = 0.75;
                     antAr = 4;
                    break; 
                case 22:  
                     bonusen = 0.75;
                     antAr = 5;
                    break;  
                case 23:
                     bonusen = 0.75;
                     antAr = 6;
                    break;   
                
            }
            
            switch(lengde_n)
            {
                case 1:
                    lengdevalget = 8000;
                    break;
                case 2:
                    lengdevalget = 12000;
                    break;
                case 3:
                    lengdevalget = 16000;
                    break;
                case 4:
                    lengdevalget = 20000;
                    break;
                case 5:
                    lengdevalget = 25000;
                    break;
                case 6:
                    lengdevalget = 30000;
                    break;
                case 7:
                    lengdevalget = 100000;
                    break;
            }
                
            typevalget = biltypevelger.getItemAt(type_n);
            merkevalget = bilmerkevelger.getItemAt(merke_n);
            b = bonusvelger.getItemAt(bonus_n);
            egenandelvalget = Integer.parseInt(egenandelsvelger.getItemAt(egenandel_n));
            regnr = bilRegnr.getText();
            modell = bilModell.getText();
            hk = Integer.parseInt(bilHk.getText());
            ar = Integer.parseInt(bilRegAr.getText());
            kmstand = Integer.parseInt(bilKmstand.getText());  
            return true;
            }
    }
    
    public void beregnPris()
    {
        if (hentInfo())
        {
            //Beregn pris
            //bilTilbud.setText();
        }
    }
    
    public void tegnNy()
    {
        if (hentInfo())
        {
            Forsikring forsikring = register.nyBilForsikring( kunde, egenandelvalget, regnr,
                                    merkevalget,modell, typevalget, hk, ar,
                                    kmstand, bonusen, antAr, garasje, lengdevalget ); 
            Kjoretoyforsikring forsikringen =(Kjoretoyforsikring)forsikring;
            forsikringen.setEier(eier);
            System.out.println(forsikringen);
            JOptionPane.showMessageDialog(null, "Du har nå tegnet bilforsikring med nummer " + forsikringen.getForsikringsnummer() + " på " + kunde.getFornavn() + " " + kunde.getEtternavn() , "Bekreftelse", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if( e.getSource() == bilGiTilbud)
        {
            tegnNy();
        }
        else if( e.getSource() == beregnPris)
        {
            beregnPris();
        }
        
    }
}
