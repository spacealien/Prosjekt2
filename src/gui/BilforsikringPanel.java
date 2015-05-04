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
 
    HovedRegister register;
    Eier eier;
    
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
    
    public BilforsikringPanel(Kunde k)
    {
        register = new HovedRegister();
                
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
        tegnBilPanel1.setLayout(new GridLayout(8,4,2,10));
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
        
    }
    
    public void tegnNy()
    {
        
        boolean garasje = false;
        
             
        String regnr = bilRegnr.getText();
        String modell = bilModell.getText();
        int hk = Integer.parseInt(bilHk.getText());
        int ar = Integer.parseInt(bilRegAr.getText());
        int kmstand = Integer.parseInt(bilKmstand.getText());  
        
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
            
            
            if (lengde_n == 0 || merke_n == 0 || type_n == 0 || egenandel_n == 0 || alder_n == 0 || dekning_n == 0|| bonus_n == 0|| (!garasjeJa.isSelected() && !garasjeNei.isSelected()))
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
            
                    JOptionPane.showMessageDialog(null, ut, "Feilmelding", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
             
            String typevalget = biltypevelger.getItemAt(type_n);
            String merkevalget = bilmerkevelger.getItemAt(merke_n);
            int lengdevalget = Integer.parseInt(kjorelengdevelger.getItemAt(lengde_n));
            String b = bonusvelger.getItemAt(bonus_n);
            double bonusen = (Double.parseDouble(b.substring(0,2)) / 100);
            
                   Forsikring forsikring = register.nyBilForsikring( kunde, regnr,
                                                     merkevalget,modell, typevalget, hk, ar,
                                                     kmstand, bonusen, garasje, lengdevalget ); 
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
            System.out.println("test");
        }
        
    }
}
