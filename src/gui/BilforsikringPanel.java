
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
 * @author Odd, Marthe
 */

/*Klassens hensikt er å designe brukergrensesnittet for bilforsikringer, 
ta imot input fra brukeren og videre registrere en bilforsikring
hvis alle feltene er korrekt skrevet inn. Klassen kan også vise informasjon om
en allerede tegnet bilforsikring, og endre denne.
*/

public class BilforsikringPanel extends JPanel implements ActionListener, ForsikringsPanel, VinduVerktoy
{
 
    private final AnsattVindu vindu;
    private final HovedRegister register;
    private Eier eier;
    private Bilforsikring bilforsikring;
    private KundePanel kundePanel;
    
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
    private final String[] biltype = {"", "Personbil", "Lastebil", "Vogntog", "Varebil", "SUV"};
    private final JComboBox<String> biltypevelger;
    
    private final String[] kjorelengde = {"", "8000", "12000", "16000", "20000", "25000", "30000",
                            "Ubegrenset"};
    private final JComboBox<String> kjorelengdevelger;
    private final String[] foreralder = {"", "Bilfører < 23 år", "Bilfører mellom 23 - 30 år", 
                           "Bilfører > 30 år"};
    private final JComboBox<String> aldervelger;
    protected final String[] dekning = {"", "Ansvar", "Delkasko", "Kasko", "Superkasko"};
    private final JComboBox<String> dekningvelger;
    private final String[] egenandel = {"", "4000", "8000", "12000", "16000", "20000", 
                            "30000"};
    private final JComboBox<String> egenandelsvelger;
    private final String[] bonus = {"", "-50%", "-40%", "-30%", "-20%", "-10%", "0%", "10%", 
                     "20%", "30%", "40%", "50%", "60%", "70%", "70% 2 år",
                     "70% 3 år", "70% 4 år", "70% 5 år", "75%", "75% 2 år",
                     "75% 3 år", "75% 4 år", "75% 5 år", "75% >5 år"};
    private final JComboBox<String> bonusvelger;
    private final Kunde kunde;
    private final JButton vilkårKnapp;
    private String regnr;
    private String modell;
    private String merke;
    private int hk;
    private int ar;
    private int kmstand;  
    private String typevalget;
    protected String vilkår;
    private int lengdevalget;
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
    private final JButton rediger;
    private final JButton lagreNyInfo;
    private final JButton deaktiver;
    private final JPanel knappePanel = new JPanel();
    private final JPanel eierPanel;
    
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
        tilbudLabel = new JLabel("Foreslått tilbud: (Kr/år) ");
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
        bilGiTilbud.setVisible(false);
        beregnPris = new JButton("Beregn pris");
        annenEier = new JButton("Trykk her for annen eier");
        biltypevelger = new JComboBox<>(biltype);
        kjorelengdevelger = new JComboBox<>(kjorelengde);
        egenandelsvelger = new JComboBox<>(egenandel);
        dekningvelger = new JComboBox<>(dekning);
        aldervelger = new JComboBox<>(foreralder);
        bonusvelger = new JComboBox<>(bonus);
        vilkårKnapp = new JButton("Vis vilkår");
        vilkårKnapp.setVisible(false);
        rediger = new JButton("Rediger forsikringinfo");
        lagreNyInfo = new JButton("Lagre forsikring");
        deaktiver = new JButton("Si opp forsikring");
        rediger.setVisible(false);
        deaktiver.setVisible(false);
        
    
        JPanel garasjePanel = new JPanel();
        JPanel alarmPanel = new JPanel();
        JPanel espPanel = new JPanel();
        JPanel gjenkjenningPanel = new JPanel();
        JPanel tegnBilPanel1 = new JPanel();
        JPanel tegnBilPanel2 = new JPanel();
        JPanel hovedPanel = new JPanel();
        tegnBilPanel1.setLayout(new GridLayout(11,2,1,1));
        tegnBilPanel2.setLayout(new GridLayout(11,2,1,1));
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
        tegnBilPanel2.add(Box.createGlue());
        tegnBilPanel2.add(vilkårKnapp);
        tegnBilPanel2.add(new JLabel("Bonus: "));
        tegnBilPanel2.add(bonusvelger);
        tegnBilPanel2.add(new JLabel("Velg egenandel: "));
        tegnBilPanel2.add(egenandelsvelger);
        tegnBilPanel2.add(Box.createGlue());
        tegnBilPanel2.add(beregnPris);
        tegnBilPanel2.add(tilbudLabel);
        tegnBilPanel2.add(bilTilbud);
        tegnBilPanel2.add(Box.createGlue());
        tegnBilPanel2.add(bilGiTilbud);
        knappePanel.setLayout(new BoxLayout(knappePanel, BoxLayout.PAGE_AXIS));
        knappePanel.add(rediger);
        knappePanel.add(deaktiver);
        hovedPanel.setLayout(new BoxLayout(hovedPanel, BoxLayout.LINE_AXIS));
        hovedPanel.add(tegnBilPanel1);
        hovedPanel.add(Box.createHorizontalStrut(5));
        hovedPanel.add(new JSeparator(SwingConstants.VERTICAL));
        hovedPanel.add(Box.createHorizontalStrut(5));
        tegnBilPanel2.setPreferredSize(tegnBilPanel1.getPreferredSize());
        hovedPanel.add(tegnBilPanel2);
        setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        hovedPanel.add(new JSeparator(SwingConstants.VERTICAL));
        add(hovedPanel);
        add(knappePanel);
        
        VilkårLytter vilkårLytter = new VilkårLytter();
        bilGiTilbud.addActionListener(this);
        beregnPris.addActionListener(this);
        annenEier.addActionListener(this);
        rediger.addActionListener(this);
        lagreNyInfo.addActionListener(this);
        deaktiver.addActionListener(this);
        vilkårKnapp.addActionListener(this);
        dekningvelger.addItemListener(vilkårLytter);
    } // slutt på konstuktør

    /*Metode for å vise info om en allerede tegnet bilforsikring. Tar imot
    forsikringen som parameter.*/
    @Override
    public void visForsikring( Forsikring f)
    {
        this.bilforsikring = (Bilforsikring) f;
        bilRegnr.setText(bilforsikring.getRegistreringsnmmer());
        bilRegAr.setText(String.valueOf(bilforsikring.getArsmodell()));
        bilVerdi.setText(String.valueOf(bilforsikring.getVerdi()) );
        bilModell.setText(bilforsikring.getModell());
        bilMerke.setText(bilforsikring.getFabrikant());
        bilHk.setText(String.valueOf(bilforsikring.getHestekrefter()));
        bilKmstand.setText(String.valueOf(bilforsikring.getKmstand()));
        egenandelsvelger.setSelectedItem(String.valueOf(bilforsikring.getEgenandel()));
        kjorelengdevelger.setSelectedItem(String.valueOf(bilforsikring.getMaxKjorelengde()));
        biltypevelger.setSelectedItem(bilforsikring.getkjøretøytype());
        aldervelger.setSelectedItem(bilforsikring.getForerAlder());
        dekningvelger.setSelectedItem(bilforsikring.getVilkar());
        bilTilbud.setText(String.valueOf(bilforsikring.getArligPremie()));
        vilkårKnapp.setVisible(true);
        
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
            rediger.setVisible(true);
            deaktiver.setVisible(true);
        }
            tilbudLabel.setText("Årlig premie: (Kr/år)");
            tilbudLabel.setVisible(true);
            bilTilbud.setVisible(true);
            disableFelter( this, bilGiTilbud, beregnPris );
    }
    
    /*Hvis brukeren trykket seg videre til å denne forsikringen via et kundepanel,
    så setter denne metoden kundepanelet som var utgangspunktet via paramtereren.
    Denne er nødvendig for å få oppdatert kundepanelet til kunden som forsikringen
    hører til, når det blir gjort endring på en forsikring eller når det blir
    tegnet en ny forsikring.*/
    @Override
    public void leggTilKundePanel( KundePanel panel )
    {
        kundePanel = panel;
    }
    
    /*Metoden henter input fra brukeren. Den sjekker at alle feltene er korrekt
    fylt ut, hvis ikke kommer det opp en passende feilmelding.*/
    @Override
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
                    ut += "Maximum kjørelengde\n";
                
                if (type_n == 0)
                    ut += "Biltype\n";
                    
                if (egenandel_n == 0)
                    ut += "Egenandel\n";
                    
                if (alder_n == 0)
                    ut += "Bilførers alder\n";
                    
                if (dekning_n == 0)
                    ut += "Dekning\n";
                    
                if (bonus_n == 0)
                    ut += "Bonus\n";
                    
                if(!garasjeJa.isSelected() && !garasjeNei.isSelected())
                    ut += "Garasjevalg\n";
                    
                if(!alarmJa.isSelected() && !alarmNei.isSelected())
                    ut += "Alarmvalg\n";
                    
                if(!espJa.isSelected() && !espNei.isSelected())
                    ut += "ESPvalg\n";
                    
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
                    garasje = garasjeJa.isSelected();
                    esp_b = espJa.isSelected();
                    alarm_b = alarmJa.isSelected();
                    gjenkjenning_b = gjenkjenningJa.isSelected();
                    
            try
            {
                forer = aldervelger.getItemAt(aldervelger.getSelectedIndex());
                belop = Integer.parseInt(bilVerdi.getText());
                typevalget = biltypevelger.getItemAt(biltypevelger.getSelectedIndex());
                dekningvalget = dekningvelger.getItemAt(dekningvelger.getSelectedIndex());
                egenandelvalget = Integer.parseInt(egenandelsvelger.getItemAt(egenandel_n));
                if (bilRegnr.getText().matches("^([a-zA-Z]){2}([0-9]){5}"))
                        regnr = bilRegnr.getText();
                else
                {
                    vindu.visFeilmelding("Feilmelding", "Feil format i et av tekstfeltene. ");
                    return false;
                }
                
                modell = bilModell.getText();
                merke = bilMerke.getText();
                hk = Integer.parseInt(bilHk.getText());
                ar = Integer.parseInt(bilRegAr.getText());
                kmstand = Integer.parseInt(bilKmstand.getText());
                return true;
            }
            catch( NumberFormatException e )
            {
                vindu.visFeilmelding("Feilmelding", "Feil format i et av tekstfeltene. ");
                return false;
            }    
        }
    }
    
    /*Metode for å beregne pris på forhånd, og skrive ut et prisforslag til brukeren.
    Hvis all info er korrekt skrevet inn, henter den info fra inputfeltene, og gjør
    kalkuleringer for å beregne pris på en eventuell forsikring med de dataene i 
    inputfeltene. Deretter vises knappen "Tegn forsikring" og det blir mulig å
    registrere forsikringen.*/
    @Override
    public void beregnPris()
    {
        if (hentInfo())
        {            
            double foreslåttPris = ForsikringsKalulator.beregnBilforsikring(belop, dekningvalget, ar, lengdevalget, 
            hk, garasje, egenandelvalget, forer, esp_b, alarm_b, gjenkjenning_b, bonusen);
             
            bilTilbud.setVisible(true);
            bilTilbud.setText(String.valueOf(foreslåttPris));
            bilTilbud.setToolTipText("Kan redigeres");
            bilGiTilbud.setVisible(true);
        }
    }
    
    /*Metode for å registrere et nytt bilforsikringsobjekt og legge dette inn i
    registeret. Oppdaterer også den eventuelle kundefanen som forsikringen hører
    til.*/ 
    public void tegnNy()
    {
        if (hentInfo())
        {   
            if( vindu.getRegister().getKundeliste().erKunde(kunde) == false )
            {
                vindu.getAnsatt().leggTilKundenøkel(kunde.getPersonnummer());
                register.nyKunde(kunde);
            }
            
            Bilforsikring forsikring = new Bilforsikring(kunde, egenandelvalget, vilkår, regnr, belop,
                                    merke,modell, typevalget, hk, ar,
                                    kmstand, forer, bonusen, antAr, garasje, alarm_b, esp_b, gjenkjenning_b, lengdevalget);
            
            forsikring.setArligPremie(Double.parseDouble(bilTilbud.getText()));
            
            vindu.getRegister().nyForsikring(forsikring);
            if(kundePanel != null)
                kundePanel.oppdaterVindu();
            if (eier != null)
                forsikring.setEier(eier);

            
            JOptionPane.showMessageDialog(null, "Du har nå tegnet bilforsikring med nummer " 
                                          + forsikring.getForsikringsnummer() 
                                          + " på " + kunde.getFornavn() + " " 
                                          + kunde.getEtternavn() , "Bekreftelse", 
                                            JOptionPane.INFORMATION_MESSAGE);
            visForsikring(forsikring);
            
        }
    }
    
    /*Metode for å oppdatere en allerede eksisterende forsikring med ny input fra
    brukeren. Forutsetter at hentInfo()-metoden returnerer true. Oppdaterer også
    kundefanen som forsikringen hører til.*/
    @Override
    public void oppdaterForsikring()
    {
        if(hentInfo())
        { 
            bilforsikring.setAntallAr(antAr);
            bilforsikring.setGjenkjenning(gjenkjenning_b);
            bilforsikring.setESP(esp_b);
            bilforsikring.setGarasje(garasje);
            bilforsikring.setKmstand(kmstand);
            bilforsikring.setForerAlder(forer);
            bilforsikring.setEier(eier);
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
            bilforsikring.setVilkar(vilkår);
            if( kundePanel != null )
                kundePanel.oppdaterVindu();
        }
    }
    
    /*Gjør inputfeltene redigerbare, setter passende tekst på knappene og legger
    til en knapp for å lagre den nye informasjonen som brukeren evt legger inn*/
    @Override
    public void redigerForsikring()
    {
        enableFelter( this, beregnPris );
        knappePanel.add(lagreNyInfo);
        tilbudLabel.setText("Foreslått tilbud: ");
        beregnPris.setText("Beregn ny pris");
        annenEier.setText("Trykk her for annen eier");
        revalidate();
        repaint();
    }
    
    /*Metode for å deaktivere en forsikring. Gjør alle feltene ikke-redigerbare
    og fjerner alle knappene. Forsikringen slettes ikke fra systemet, men settes
    som inaktiv. Det kommer først opp en meldingsboks der brukeren kan bekrefte at
    han/hun ønsker å deaktivere forsikringen. Hvis svaret er ja, kommer det opp 
    en ny meldingsboks som bekrefter forsikringens deaktivering.*/
    @Override
    public void deaktiverForsikring()
    {
        int svar = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil deaktivere denne forsikringen?", "Forsikring " 
                                                     + String.valueOf(bilforsikring.getForsikringsnummer()), JOptionPane.YES_NO_OPTION);
        if (svar == JOptionPane.YES_OPTION)
        {
            disableFelter(this, beregnPris, bilGiTilbud);
           
            knappePanel.remove(rediger);
            knappePanel.remove(lagreNyInfo);
            this.remove(beregnPris);
            knappePanel.remove(deaktiver);
            bilforsikring.setAktiver(false);
            JOptionPane.showMessageDialog(null, "Forsikring " + String.valueOf(bilforsikring.getForsikringsnummer()) 
                                              + " er ikke lenger aktiv.", "Bekreftelse", JOptionPane.PLAIN_MESSAGE);
                
            kundePanel.oppdaterVindu();
            repaint();
            revalidate();
        }
    }
    
    /*Viser vilkår i et nytt vindu. Henter vilkår fra fil*/
    @Override
    public void visVilkår()
    {
        if( bilforsikring == null )
            visForsikringensVilkår("Ny Bilforsikring " + kunde.getFornavn() + " " + kunde.getEtternavn() , vilkår);
        else
            visForsikringensVilkår("Vilkår" + bilforsikring.getForsikringsnummer(), bilforsikring.getVilkar());
    }
    
    @Override
    public void velgVilkår()
    {
        vilkår = this.velgVilkår( "Bil"+ dekningvelger.getItemAt(dekningvelger.getSelectedIndex()) );
        vilkårKnapp.setVisible(true);
    }
    
    //Klassens knappelytter
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
            //Hvis man skal registrere ny eier
            if (annenEier.getText().equals("Trykk her for annen eier"))
            {
                int result;
                do
                {
                   result = JOptionPane.showConfirmDialog(null, eierPanel, 
                                "Vennligst fyll ut bileiers kontaktinformasjon:",
                                JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION)
                    {
                        if(eierFornavn.getText().matches("\\D*") && 
                                eierEtternavn.getText().matches("\\D*") && 
                                eierTlf.getText().matches("\\d{8}"))
                        {
                            eier = new Eier(eierFornavn.getText(), eierEtternavn.getText(), 
                                    eierAdresse.getText(), eierTlf.getText());
                            //For å ikke gå igjennom løkken en gang til:
                            result = JOptionPane.CANCEL_OPTION;
                        }
                        else
                        {
                            vindu.visFeilmelding("Feilmelding", "Pass på at alle"
                                    + " feltene er korrekt skrevet inn");
                        }
                    }
                }
                while (result == JOptionPane.OK_OPTION);
            }
            //Hvis man skal vise eier på allerede eksisterende forsikring
            else if(annenEier.getText().equals("Vis eier"))
                {
                    vindu.visInformasjon("Kjøretøyets registrete eier:", 
                            bilforsikring.getEier().toString());
                }
        }
        else if ( e.getSource() == vilkårKnapp)
        {
            visVilkår();
        }
        else if( e.getSource() == rediger)
        {
            redigerForsikring();
        }
        else if(e.getSource() == lagreNyInfo)
        {
            oppdaterForsikring();
        }
        else if (e.getSource() == deaktiver)
        {
            deaktiverForsikring();
        }
    }
    
    /*Lytterklassen til dekningvelgeren. Denne lytteren endrer vilkårene etter 
    hvilken dekning som er valgt.*/
    private class VilkårLytter implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent e) 
        {
            if( dekningvelger.getSelectedIndex() != 0)
            {
                velgVilkår();
            }
        }
    }
}
