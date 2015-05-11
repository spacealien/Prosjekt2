/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
    private Person eieren;
    private Bilforsikring bilforsikring;
    
    private final JTextField eierFornavn;
    private final JTextField eierEtternavn;
    private final JTextField eierTlf;
    private final JTextField eierAdresse;
    
    private final JTextField bilRegnr;
    private final JTextField bilRegAr;
    private final JTextField bilHk;
    private final JTextField bilVerdi;
    private final JTextField bilMerke;
    private final JTextField bilModell;
    private final JTextField bilKmstand;
    private final JLabel tilbudLabel;
    private final JTextField bilTilbud;
    private final JRadioButton garasjeJa;
    private final JRadioButton garasjeNei;
    private final JRadioButton alarmJa;
    private final JRadioButton alarmNei;
    private final JRadioButton espJa;
    private final JRadioButton espNei;
    private final JRadioButton gjenkjenningJa;
    private final JRadioButton gjenkjenningNei;
    private final JButton annenEier;
    private final JButton bilGiTilbud;
    private final JButton beregnPris;
    String[] biltype = {"", "Personbil", "Lastebil", "Vogntog", "Varebil", "SUV"};
    JComboBox<String> biltypevelger;
    
    String[] kjorelengde = {"", "8000", "12000", "16000", "20000", "25000", "30000",
                            "Ubegrenset"};
    JComboBox<String> kjorelengdevelger;
    String[] foreralder = {"", "Bilfører < 23 år", "Bilfører mellom 23 - 25 år", 
                           "Bilfører > 25 år"};
    JComboBox<String> aldervelger;
    String[] dekning = {"", "Ansvar", "Delkasko", "Kasko", "Superkasko"};
    JComboBox<String> dekningvelger;
    String[] egenandel = {"", "4000", "8000", "12000", "16000", "20000", 
                            "30000"};
    JComboBox<String> egenandelsvelger;
    String[] bonus = {"", "-50%", "-40%", "-30%", "-20%", "-10%", "0%", "10%", 
                     "20%", "30%", "40%", "50%", "60%", "70%", "70% 2 år",
                     "70% 3 år", "70% 4 år", "70% 5 år", "75%", "75% 2 år",
                     "75% 3 år", "75% 4 år", "75% 5 år", "75% >5 år"};
    JComboBox<String> bonusvelger;
    private final Kunde kunde;
    private final JButton vilkårKnapp;
    private String regnr;
    private String modell;
    private String merke;
    private int hk;
    private int ar;
    private int kmstand;  
    private String typevalget;
    private int lengdevalget;
    private String bonusTekst;
    private double bonusen;
    private int egenandelvalget;
    private boolean garasje;
    private boolean alarm_b;
    private boolean esp_b;
    private boolean gjenkjenning_b;
    private int antAr = 1;
    private int belop;
    private String forer;
    private String dekningvalget;
    private final JButton rediger = new JButton("Rediger forsikringinfo");
    private final JButton lagreNyInfo = new JButton("Lagre forsikring");
    private final JButton deaktiver = new JButton("Si opp forsikring");
    private JPanel knappePanel = new JPanel();
    private JPanel eierPanel;
    
    public BilforsikringPanel(Kunde k, AnsattVindu v)
    {
        vindu = v;
        register = vindu.getRegister();
                
        eierFornavn = new JTextField(20);
        eierEtternavn = new JTextField(20);
        eierTlf = new JTextField(8);
        eierAdresse = new JTextField(15);
        
        eierPanel = new JPanel();
        eierPanel.setLayout(new GridLayout(4,2,1,1));
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
        bilVerdi = new JTextField(8);
        bilMerke = new JTextField( 14 );
        bilModell = new JTextField( 10 );
        bilHk = new JTextField( 7 );
        bilKmstand = new JTextField( 6 );
        bilTilbud = new JTextField( 6 );
        bilTilbud.setVisible(true);
        tilbudLabel = new JLabel("Foreslått tilbud: ");
        tilbudLabel.setVisible(true);
        garasjeJa = new JRadioButton("Ja");
        garasjeNei = new JRadioButton("Nei");
        garasjeJa.setMnemonic(KeyEvent.VK_J);
        garasjeNei.setMnemonic(KeyEvent.VK_N);
        ButtonGroup garasjen = new ButtonGroup();
        garasjen.add(garasjeJa);
        garasjen.add(garasjeNei);
        espJa = new JRadioButton("Ja");
        espNei = new JRadioButton("Nei");
        espJa.setMnemonic(KeyEvent.VK_J);
        espNei.setMnemonic(KeyEvent.VK_N);
        ButtonGroup esp = new ButtonGroup();
        esp.add(espJa);
        esp.add(espNei);
        gjenkjenningJa = new JRadioButton("Ja");
        gjenkjenningNei = new JRadioButton("Nei");
        gjenkjenningJa.setMnemonic(KeyEvent.VK_J);
        gjenkjenningNei.setMnemonic(KeyEvent.VK_N);
        ButtonGroup gjenkjenning = new ButtonGroup();
        gjenkjenning.add(gjenkjenningJa);
        gjenkjenning.add(gjenkjenningNei);
        alarmJa = new JRadioButton("Ja");
        alarmNei = new JRadioButton("Nei");
        alarmJa.setMnemonic(KeyEvent.VK_J);
        alarmNei.setMnemonic(KeyEvent.VK_N);
        ButtonGroup alarm = new ButtonGroup();
        alarm.add(alarmJa);
        alarm.add(alarmNei);
        bilGiTilbud = new JButton("Tegn forsikring");
        beregnPris = new JButton("Beregn pris");
        annenEier = new JButton("Trykk her for annen eier");
        biltypevelger = new JComboBox<>(biltype);
        kjorelengdevelger = new JComboBox<>(kjorelengde);
        egenandelsvelger = new JComboBox<>(egenandel);
        dekningvelger = new JComboBox<>(dekning);
        aldervelger = new JComboBox<>(foreralder);
        bonusvelger = new JComboBox<>(bonus);
        vilkårKnapp = new JButton("Vilkår");
        
    
        JPanel garasjePanel = new JPanel();
        JPanel alarmPanel = new JPanel();
        JPanel espPanel = new JPanel();
        JPanel gjenkjenningPanel = new JPanel();
        JPanel tegnBilPanel1 = new JPanel();
        JPanel tegnBilPanel2 = new JPanel();
        JPanel hovedPanel = new JPanel();
        tegnBilPanel1.setLayout(new GridLayout(11,2,1,1));
        tegnBilPanel2.setLayout(new GridLayout(10,2,1,1));
        garasjePanel.add(garasjeJa);
        garasjePanel.add(garasjeNei);
        alarmPanel.add(alarmJa);
        alarmPanel.add(alarmNei);
        espPanel.add(espJa);
        espPanel.add(espNei);
        gjenkjenningPanel.add(gjenkjenningJa);
        gjenkjenningPanel.add(gjenkjenningNei);
        tegnBilPanel1.add(new JLabel("Registreringsnummer: "));
        tegnBilPanel1.add(bilRegnr);
        tegnBilPanel1.add(new JLabel("Registreringsår: "));
        tegnBilPanel1.add(bilRegAr);
        tegnBilPanel1.add(new JLabel("Verdi: "));
        tegnBilPanel1.add(bilVerdi);
        tegnBilPanel1.add(new JLabel("Velg fabrikant: "));
        tegnBilPanel1.add(bilMerke);
        tegnBilPanel1.add(new JLabel("Modell: "));
        tegnBilPanel1.add(bilModell);
        tegnBilPanel1.add(new JLabel("Hestekrefter: "));
        tegnBilPanel1.add(bilHk);
        tegnBilPanel1.add(new JLabel("Kilometerstand: "));
        tegnBilPanel1.add(bilKmstand);
        tegnBilPanel1.add(new JLabel("Velg biltype: "));
        tegnBilPanel1.add(biltypevelger);
        tegnBilPanel1.add(new JLabel("Årlig forventet kjørelengde: "));
        tegnBilPanel1.add(kjorelengdevelger);
        tegnBilPanel1.add(new JLabel("Yngste bilførers alder: "));
        tegnBilPanel1.add(aldervelger);
        tegnBilPanel1.add(new JLabel("Er eier annen enn kunde?"));
        tegnBilPanel1.add(annenEier);
        tegnBilPanel2.add(new JLabel("Garasje: "));
        tegnBilPanel2.add(garasjePanel);
        tegnBilPanel2.add(new JLabel("FG-godkjent alarm: "));
        tegnBilPanel2.add(alarmPanel);
        tegnBilPanel2.add(new JLabel("ESP antiskrens: "));
        tegnBilPanel2.add(espPanel);
        tegnBilPanel2.add(new JLabel("<html>FG-godkjent søk- og<br> gj.kjenningssystem: </html>"));
        tegnBilPanel2.add(gjenkjenningPanel);
        tegnBilPanel2.add(new JLabel("Dekning: "));
        tegnBilPanel2.add(dekningvelger);
        tegnBilPanel2.add(new JLabel("Bonus: "));
        tegnBilPanel2.add(bonusvelger);
        tegnBilPanel2.add(new JLabel("Velg egenandel: "));
        tegnBilPanel2.add(egenandelsvelger);
        tegnBilPanel2.add(new JLabel());
        tegnBilPanel2.add(beregnPris);
        tegnBilPanel2.add(tilbudLabel);
        tegnBilPanel2.add(bilTilbud);
        tegnBilPanel2.add(new JLabel());
        tegnBilPanel2.add(bilGiTilbud);
        hovedPanel.setLayout(new BoxLayout(hovedPanel, BoxLayout.LINE_AXIS));
        hovedPanel.add(tegnBilPanel1);
        hovedPanel.add(Box.createHorizontalStrut(5));
        hovedPanel.add(new JSeparator(SwingConstants.VERTICAL));
        hovedPanel.add(Box.createHorizontalStrut(5));
        hovedPanel.add(tegnBilPanel2);
        setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        hovedPanel.add(new JSeparator(SwingConstants.VERTICAL));
        add(hovedPanel);
        
        
        
        bilGiTilbud.addActionListener(this);
        beregnPris.addActionListener(this);
        annenEier.addActionListener(this);
        rediger.addActionListener(this);
        lagreNyInfo.addActionListener(this);
        deaktiver.addActionListener(this);
        vilkårKnapp.addActionListener(this);
        
    } // slutt på konstuktør
    
    private Component[] getKomponenter(Component pane)
     {
        ArrayList<Component> liste = null;

        try
        {
            liste = new ArrayList<>(Arrays.asList(
                  ((Container) pane).getComponents()));
            for (int i = 0; i < liste.size(); i++)
            {
            for (Component currentComponent : getKomponenter(liste.get(i)))
            {
                liste.add(currentComponent);
            }
            }
        } catch (ClassCastException e) {
            liste = new ArrayList<>();
        }

        return liste.toArray(new Component[liste.size()]);
        
    }
    public void visForsikring( Forsikring f)
    {
        this.bilforsikring = (Bilforsikring) f;
        System.out.println(bilforsikring.getMaxKjorelengde());
        bilRegnr.setText(bilforsikring.getRegistreringsnmmer());
        bilRegAr.setText(String.valueOf(bilforsikring.getArsmodell()));
        bilVerdi.setText("");
        bilModell.setText(bilforsikring.getModell());
        bilMerke.setText(bilforsikring.getFabrikant());
        bilHk.setText(String.valueOf(bilforsikring.getHestekrefter()));
        bilKmstand.setText(String.valueOf(bilforsikring.getKmstand()));
        egenandelsvelger.setSelectedItem(String.valueOf(bilforsikring.getEgenandel()));
        kjorelengdevelger.setSelectedItem(String.valueOf(bilforsikring.getMaxKjorelengde()));
        biltypevelger.setSelectedItem(bilforsikring.getType());
        aldervelger.setSelectedItem(bilforsikring.getForerAlder());
        dekningvelger.setSelectedItem(bilforsikring.getVilkar());
        //bilTilbud.setText(bilforsikring.getArligPremie());
        if(bilforsikring.getGarasje())
            garasjeJa.setSelected(true);
        else
            garasjeNei.setSelected(true);
        
        if(bilforsikring.getAlarm())
            alarmJa.setSelected(true);
        else
            alarmNei.setSelected(true);
        
        if(bilforsikring.getESP())
            espJa.setSelected(true);
        else
            espNei.setSelected(true);
        
        if(bilforsikring.getGjenkjenning())
            gjenkjenningJa.setSelected(true);
        else
            gjenkjenningNei.setSelected(true);
        
        annenEier.setText("Vis eier");
        
        
        double d = bilforsikring.getBonus();
        int j = (int)(d*100);
        int a = bilforsikring.getAntallAr();
        for (int i = 1; i< bonus.length; i++)
        {
            if (bonus[i].matches(j + ".*"))
            {
                
                    if (j == 70)
                    {
                        if (bonus[i].matches(".+" + a + " år" + ".*"))
                        {
                            bonusvelger.setSelectedItem(bonusvelger.getItemAt(i));
                        }
                    }
                    else if(j == 75)
                    {
                        if (bonus[i].matches(".+" + a + " år" + ".*"))
                        {
                            bonusvelger.setSelectedItem(bonusvelger.getItemAt(i));
                        } 
                    }
                    else
                    {
                        bonusvelger.setSelectedItem(bonusvelger.getItemAt(i)); 
                    } 
            }
            
        }
        if(bilforsikring.erAktiv())
        {
            knappePanel.setLayout(new BoxLayout(knappePanel, BoxLayout.PAGE_AXIS));
            knappePanel.add(rediger);
            knappePanel.add(deaktiver);
            add(knappePanel);
        }
        
            tilbudLabel.setText("Årlig premie: ");
            tilbudLabel.setVisible(true);
            bilTilbud.setVisible(true);
        
        for(Component component : getKomponenter(this))
                {
                    if((component instanceof JTextField))
                    {
                        JTextField tf = (JTextField)component;
                        tf.setEditable(false);
                    }
                    else if(component instanceof JComboBox)
                    {
                        JComboBox cb = (JComboBox)component;
                        cb.setEnabled(false);
                    }
                    else if(component instanceof JRadioButton)
                    {
                        JRadioButton rb = (JRadioButton)component;
                        rb.setEnabled(false);
                    }
                    else if (component.equals(bilGiTilbud))
                    {
                        component.setVisible(false);
                    }
                    else if (component.equals(beregnPris))
                    {
                        component.setVisible(false);
                    }
                }
    }
    // ikke ferdig
    
    public boolean hentInfo()
    {
            int type_n = biltypevelger.getSelectedIndex();
            int lengde_n = kjorelengdevelger.getSelectedIndex();
            int bonus_n = bonusvelger.getSelectedIndex();
            int egenandel_n = egenandelsvelger.getSelectedIndex();
            int alder_n = aldervelger.getSelectedIndex();
            int dekning_n = dekningvelger.getSelectedIndex();
            
            
            if (lengde_n == 0 || type_n == 0 || egenandel_n == 0 
               || alder_n == 0 || dekning_n == 0 || bonus_n == 0 || 
                    (!garasjeJa.isSelected() && !garasjeNei.isSelected()) || 
                    (!alarmJa.isSelected() && !alarmNei.isSelected()) || 
                    (!espJa.isSelected() && !espNei.isSelected()) || 
                    (!gjenkjenningJa.isSelected() && !gjenkjenningNei.isSelected()))
            {
                String ut = "Det mangler informasjon om:\n";
                if (lengde_n == 0)
                {ut += "Maximum kjørelengde\n";}
                
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
                    {ut += "Garasjevalg\n";}
                    
                    if(!alarmJa.isSelected() && !alarmNei.isSelected())
                    {ut += "Alarmvalg\n";}
                    
                    if(!espJa.isSelected() && !espNei.isSelected())
                    {ut += "ESPvalg\n";}
                    
                    if(!gjenkjenningJa.isSelected() && !gjenkjenningNei.isSelected())
                    {ut += "Gjenkjenningssystemvalg\n";}
                    ut += "\nVennligst fyll ut denne informasjonen og prøv igjen.";
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
            if (garasjeJa.isSelected() && !garasjeNei.isSelected())
                    garasje = true;
        else if (!garasjeJa.isSelected() && garasjeNei.isSelected())
                    garasje = false;
            if (espJa.isSelected() && !espNei.isSelected())
                    esp_b = true;
        else if (!espJa.isSelected() && espNei.isSelected())
                    esp_b = false;
            if (alarmJa.isSelected() && !alarmNei.isSelected())
                    alarm_b = true;
        else if (!alarmJa.isSelected() && alarmNei.isSelected())
                    alarm_b = false;
            if (gjenkjenningJa.isSelected() && !gjenkjenningNei.isSelected())
                    gjenkjenning_b = true;
        else if (!gjenkjenningJa.isSelected() && gjenkjenningNei.isSelected())
                    gjenkjenning_b = false;
            
                forer = aldervelger.getItemAt(alder_n);
                belop = Integer.parseInt(bilVerdi.getText());
                typevalget = biltypevelger.getItemAt(type_n);
                bonusTekst = bonusvelger.getItemAt(bonus_n);
                dekningvalget = dekningvelger.getItemAt(dekning_n);
                egenandelvalget = Integer.parseInt(egenandelsvelger.getItemAt(egenandel_n));
                regnr = bilRegnr.getText();
                modell = bilModell.getText();
                merke = bilMerke.getText();
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
            Bilforsikring forsikring = new Bilforsikring(kunde, egenandelvalget, dekningvalget, regnr, belop,
                                    merke,modell, typevalget, hk, ar,
                                    kmstand, forer, bonusen, antAr, garasje, alarm_b, esp_b, gjenkjenning_b, lengdevalget);
            
            
            double foreslåttPris = ForsikringsKalulator.beregnBilforsikring(forsikring);
            NumberFormat formatter = new DecimalFormat("#0.00"); 
            bilTilbud.setVisible(true);
            bilTilbud.setText(formatter.format(foreslåttPris) + " Kr/År");
        }
    }
    
    public void tegnNy()
    {
        if (hentInfo())
        {   
            if( vindu.getRegister().getKundeliste().erKunde(kunde) == false )
            {
                vindu.getAnsatt().leggTilKundenøkel(kunde.getPersonnummer());
                register.nyKunde(kunde);
            }
            
            Bilforsikring forsikring = new Bilforsikring(kunde, egenandelvalget, dekningvalget, regnr, belop,
                                    merke,modell, typevalget, hk, ar,
                                    kmstand, forer, bonusen, antAr, garasje, alarm_b, esp_b, gjenkjenning_b, lengdevalget);
            
            vindu.getRegister().nyForsikring(forsikring);
            
            Eier eier = (Eier)eieren;
            forsikring.setEier(eier);

            
            JOptionPane.showMessageDialog(null, "Du har nå tegnet bilforsikring med nummer " 
                                          + forsikring.getForsikringsnummer() 
                                          + " på " + kunde.getFornavn() + " " 
                                          + kunde.getEtternavn() , "Bekreftelse", 
                                            JOptionPane.INFORMATION_MESSAGE);
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
        else if (e.getSource() == annenEier)
        {
            if (annenEier.getText().equals("Trykk her for annen eier"))
            {
                int result = JOptionPane.showConfirmDialog(null, eierPanel, 
                             "Vennligst fyll ut bileiers kontaktinformasjon:",
                                        JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION)
                {
                    eieren = new Eier(eierFornavn.getText(), eierEtternavn.getText(), 
                    eierAdresse.getText(), eierTlf.getText());
                }  
            }
            else if (annenEier.getText().equals("Vis eier"))
            {
               JOptionPane.showMessageDialog( null, bilforsikring.getEier().toString(), 
                      "Kjøretøyets registrerte eier:", JOptionPane.PLAIN_MESSAGE);
            }
        }
        else if( e.getSource() == rediger)
        {
            for(Component component : getKomponenter(this))
                {
                    if((component instanceof JTextField))
                    {
                        JTextField tf = (JTextField)component;
                        tf.setEditable(true);
                    }
                    else if(component instanceof JComboBox)
                    {
                        JComboBox cb = (JComboBox)component;
                        cb.setEnabled(true);
                    }
                    else if(component instanceof JRadioButton)
                    {
                        JRadioButton rb = (JRadioButton)component;
                        rb.setEnabled(true);
                    }
                    else if (component.equals(beregnPris))
                    {
                        component.setVisible(true);
                    }
                    knappePanel.add(lagreNyInfo);
                    tilbudLabel.setText("Foreslått tilbud: ");
                    beregnPris.setText("Beregn ny pris");
                    annenEier.setText("Trykk her for annen eier");
                    revalidate();
                    repaint();
                    
                }
        }
        else if(e.getSource() == lagreNyInfo)
        {
            if(hentInfo())
            { 
                bilforsikring.setAntallAr(antAr);
                bilforsikring.setGjenkjenning(gjenkjenning_b);
                bilforsikring.setESP(esp_b);
                bilforsikring.setGarasje(garasje);
                bilforsikring.setKmstand(kmstand);
                bilforsikring.setForerAlder(forer);
                bilforsikring.setEier((Eier)eieren);
                bilforsikring.setHestekrefter(hk);
                bilforsikring.setRegistreringsnummer(regnr);
                bilforsikring.setEgenandel(egenandelvalget);
                bilforsikring.setAlarm(alarm_b);
                bilforsikring.setFabrikant(merke);
                bilforsikring.setType(typevalget);
                bilforsikring.setModell(modell);
                bilforsikring.setBelop(belop);
                bilforsikring.setMaxKjorelengde(lengdevalget);
                bilforsikring.setBonus(bonusen);
                bilforsikring.setArsmodell(ar);
                bilforsikring.setVilkar(dekningvalget);
                
                //Må beregne pris på nytt!
            }
        }
        else if (e.getSource() == deaktiver)
        {
           
            int svar = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil deaktivere denne forsikringen?", "Forsikring " + String.valueOf(bilforsikring.getForsikringsnummer()), JOptionPane.YES_NO_OPTION);
            if (svar == JOptionPane.YES_OPTION)
            {
                knappePanel.remove(rediger);
                knappePanel.remove(lagreNyInfo);
                knappePanel.remove(beregnPris);
                bilforsikring.setAktiver(false);
                JOptionPane.showMessageDialog(null, "Forsikring " + String.valueOf(bilforsikring.getForsikringsnummer()) + " er ikke lenger aktiv.", "Bekreftelse", JOptionPane.PLAIN_MESSAGE);
                repaint();
                revalidate();
            }
        }
        
    }
}
