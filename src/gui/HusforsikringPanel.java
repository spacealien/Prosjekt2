
package gui;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Calendar;
import javax.swing.*;
import objekter.*;
import register.*;

/*
 *   Klassens hensikt er å designe brukergrensesnittet for hus- og innboforsikringer, 
 *   ta imot input fra brukeren og videre registrere en hus- og innboforsikring
 *   hvis alle feltene er korrekt skrevet inn. Klassen kan også vise informasjon om
 *   en allerede tegnet hus- og innboforsikring, og endre denne.
 *
 *  @author Odd, Marthe, Sist endret 19.05.2015.
 */

public class HusforsikringPanel extends JPanel implements ActionListener, ForsikringsPanel, VinduVerktoy
{
    private AnsattVindu vindu;
    private HovedRegister register;
    private Husforsikring forsikring;
    private KundePanel kundePanel;
    
    private final JTextField husAdresse;
    private final JTextField husAr;
    private final JTextField husKvm;
    private final JTextField belopHus;
    private final JTextField belopHusInnbo;
    private final JTextField husTilbud;
    private final JLabel tilbudLabel;
    private final JComboBox<String> hustypevelger;
    private final String[] hustype = {"","Enebolig", "Tomannsbolig", "Tremannsbolig", "Firemannsbolig", "Rekkehus", "Leilighet"};
    private final JComboBox<String> husmaterialevelger;
    private final String[] husmateriale = {"","Mur", "Tre", "Brannfast", "Laftet tømmer"};
    private final String[] husstandard = {"","Normal standard", "Bedre standard", "Høy standard"};
    private final JComboBox<String> husstandardvelger;
    private final String[] egenandel = {"", "2000", "4000", "8000", "12000", "16000", "20000", "30000"};
    private final JComboBox<String> egenandelsvelger;
    private final String[] dekning = {"", "Hus", "Hus-Pluss"};
    private final JComboBox<String> dekningvelger;
    private final JRadioButton alarmJa, alarmNei;
    private final JButton husGiTilbud;
    private final JButton beregnPris;
    private final JButton vilkarKnapp;
    private final Kunde kunde;
    
    private String vilkår;
    private String hustypevalget;
    private String husmaterialevalget;
    private String husstandardvalget;
    private int egenandelvalget;
    private String dekningvalget;
    private String adr;
    private int ar;
    private int kvm;
    private int belop;
    private int belopInnbo;
    private boolean alarm_b;
    private final JPanel knappePanel = new JPanel();
    private final JButton rediger;
    private final JButton lagreNyInfo;
    private final JButton deaktiver;
    
    public HusforsikringPanel(Kunde k, AnsattVindu v)
    {
        vindu = v;
        register = vindu.getRegister();
        kunde = k;
        husAdresse = new JTextField( 7 );
        belopHus = new JTextField( 7 );
        belopHusInnbo = new JTextField( 7 );
        husTilbud = new JTextField( 7 );
        husAr = new JTextField( 4 );
        husKvm = new JTextField( 4 );
        tilbudLabel = new JLabel("Foreslått tilbud: ");
        hustypevelger = new JComboBox<>(hustype);
        husmaterialevelger = new JComboBox<>(husmateriale);
        husstandardvelger = new JComboBox<>(husstandard);
        egenandelsvelger = new JComboBox<>(egenandel);
        dekningvelger = new JComboBox<>(dekning);
        alarmJa = new JRadioButton("Ja");
        alarmJa.setMnemonic(KeyEvent.VK_J);
        alarmNei = new JRadioButton("Nei");
        alarmNei.setMnemonic(KeyEvent.VK_N);
        ButtonGroup alarm = new ButtonGroup();
        alarm.add(alarmJa);
        alarm.add(alarmNei);
        beregnPris = new JButton("Beregn pris");
        husGiTilbud = new JButton("Tegn forsikring");
        husGiTilbud.setVisible(false);
        vilkarKnapp = new JButton("Vis vilkår");
        rediger = new JButton("Rediger forsikring");
        lagreNyInfo = new JButton("Lagre forsikring");
        deaktiver = new JButton("Si opp forsikring");
        rediger.setVisible(false);
        deaktiver.setVisible(false);
        
        JPanel tegnHusPanel1 = new JPanel();
        JPanel tegnHusPanel2 = new JPanel();
        JPanel hovedPanel = new JPanel();
        JPanel alarmPanel = new JPanel();
        alarmPanel.add(alarmJa);
        alarmPanel.add(alarmNei);
        tegnHusPanel1.setLayout(new GridLayout(8,2,5,10));
        tegnHusPanel2.setLayout(new GridLayout(8,2,5,10));
        hovedPanel.setLayout(new BoxLayout(hovedPanel, BoxLayout.LINE_AXIS));
        tegnHusPanel1.add(new JLabel("Adresse: "));
        tegnHusPanel1.add(husAdresse);
        tegnHusPanel1.add(new JLabel("Byggeår: "));
        tegnHusPanel1.add(husAr);
        tegnHusPanel1.add(new JLabel("Kvadratmeter: "));
        tegnHusPanel1.add(husKvm);
        tegnHusPanel1.add(new JLabel("Byggemateriale: "));
        tegnHusPanel1.add(husmaterialevelger);
        tegnHusPanel1.add(new JLabel("Hustype: "));
        tegnHusPanel1.add(hustypevelger);
        tegnHusPanel1.add(new JLabel("Standard: "));
        tegnHusPanel1.add(husstandardvelger);
        tegnHusPanel1.add(new JLabel("Alarm "));
        tegnHusPanel1.add(alarmPanel);
        tegnHusPanel1.add(Box.createGlue());
        tegnHusPanel1.add(Box.createGlue());
        tegnHusPanel2.add(new JLabel("Forsikringsbeløp bygg: "));
        tegnHusPanel2.add(belopHus);
        tegnHusPanel2.add(new JLabel("Forskringsbeløp innbo: "));
        tegnHusPanel2.add(belopHusInnbo);
        tegnHusPanel2.add(new JLabel());
        tegnHusPanel2.add(vilkarKnapp);
        tegnHusPanel2.add(new JLabel("Velg dekning: "));
        tegnHusPanel2.add(dekningvelger);
        tegnHusPanel2.add(new JLabel("Egenandel: "));
        tegnHusPanel2.add(egenandelsvelger);
        tegnHusPanel2.add(Box.createGlue());
        tegnHusPanel2.add(beregnPris);
        tegnHusPanel2.add(tilbudLabel);
        tegnHusPanel2.add(husTilbud);
        tegnHusPanel2.add(Box.createGlue());
        tegnHusPanel2.add(husGiTilbud);
        knappePanel.setLayout(new BoxLayout(knappePanel, BoxLayout.PAGE_AXIS));
        knappePanel.add(rediger);
        knappePanel.add(deaktiver);
        hovedPanel.add(tegnHusPanel1);
        hovedPanel.add(Box.createHorizontalStrut(5));
        hovedPanel.add(new JSeparator(SwingConstants.VERTICAL));
        hovedPanel.add(Box.createHorizontalStrut(5));
        hovedPanel.add(tegnHusPanel2);
        add(hovedPanel);
        add(knappePanel);
        
        VilkårLytter vilkårLytter = new VilkårLytter();
        husGiTilbud.addActionListener(this);
        beregnPris.addActionListener(this);
        vilkarKnapp.addActionListener(this);
        rediger.addActionListener(this);
        lagreNyInfo.addActionListener(this);
        deaktiver.addActionListener(this);
        dekningvelger.addItemListener(vilkårLytter);
        
        hustypevelger.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                if (hustypevelger.getSelectedItem() == "Leilighet")
                {
                    husmaterialevelger.setEnabled(false);
                    husAr.setEnabled(false);
                    belopHus.setEnabled(false);
                    husstandardvelger.setEnabled(false);
                    dekningvelger.setEnabled(false);
                }
                else if (hustypevelger.getSelectedItem() != "Leilighet")
                {
                    husmaterialevelger.setEnabled(true);
                    husAr.setEnabled(true);
                    belopHus.setEnabled(true);
                    husstandardvelger.setEnabled(true);
                    dekningvelger.setEnabled(true);
                } 
            }
        });
    }
    
    /*Metode for å vise en allerede tegnet hus/innboforsikring. Tar imot forsikringen
    som parameter.*/
    @Override
    public void visForsikring( Forsikring f)
    {
        this.forsikring = ( Husforsikring) f;
        husAdresse.setText(forsikring.getAdresse());
        husKvm.setText(String.valueOf(forsikring.getKvadratmeter()));
        husAr.setText(String.valueOf(forsikring.getByggeAr()) );
        hustypevelger.setSelectedItem(forsikring.getBoligtype());
        husmaterialevelger.setSelectedItem(forsikring.getMateriale());
        husstandardvelger.setSelectedItem(forsikring.getStandard());
        egenandelsvelger.setSelectedItem(String.valueOf(forsikring.getEgenandel()));
        dekningvelger.setSelectedItem(forsikring.getVilkar());
        belopHus.setText(String.valueOf(forsikring.getForsikringsbelopBygning()));
        belopHusInnbo.setText(String.valueOf(forsikring.getForsikringsbelopInnbo()));
        if (forsikring.getAlarm())
            alarmJa.setSelected(true);
        else
            alarmNei.setSelected(true);
        
        if (forsikring.erAktiv())
        {
            rediger.setVisible(true);
            deaktiver.setVisible(true);
        }
        tilbudLabel.setText("Årlig premie: (Kr/År)");
        tilbudLabel.setVisible(true);
        husTilbud.setVisible(true);
        disableFelter( this, husGiTilbud, beregnPris );
        revalidate();
        repaint();
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
      
        int hustype_n = hustypevelger.getSelectedIndex();
        int materiale_n = husmaterialevelger.getSelectedIndex();
        int husstandard_n = husstandardvelger.getSelectedIndex();
        int egenandel_n = egenandelsvelger.getSelectedIndex();
        int dekning_n = dekningvelger.getSelectedIndex();
            
        if (hustype_n == 0 || materiale_n == 0 || husstandard_n == 0 || egenandel_n == 0 || dekning_n == 0 || (!alarmJa.isSelected() && !alarmNei.isSelected()))
        { 
            String ut = "Det mangler informasjon om:\n";
          
            if (hustype_n == 0)
            {
                ut += "Hustype\n";
            }
               
            if (materiale_n == 0)
            {
                ut += "Byggemateriale\n";
            }
                
            if (husstandard_n == 0)
            {
                ut += "Husstandard\n";
            }
            if (egenandel_n == 0)
            {
                ut += "Egenandel\n";
            }
            if (dekning_n == 0)
            {
                ut += "Dekning\n";
            }
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
            if (alarmJa.isSelected() && !alarmNei.isSelected())
                alarm_b = true;
            else if (!alarmJa.isSelected() && alarmNei.isSelected())
                alarm_b = false;
              
            try
            {
                hustypevalget = hustypevelger.getItemAt(hustype_n);
                husmaterialevalget = husmaterialevelger.getItemAt(materiale_n);
                husstandardvalget = husstandardvelger.getItemAt(husstandard_n);
                egenandelvalget = Integer.parseInt(egenandelsvelger.getItemAt(egenandel_n));
                dekningvalget = dekningvelger.getItemAt(dekning_n);
                adr = husAdresse.getText();
                java.util.Locale norge = new java.util.Locale( "no" );
                Calendar dato = Calendar.getInstance(norge);
                if (husAr.getText().matches("\\d{4}") && Integer.parseInt(husAr.getText()) <= dato.get(Calendar.YEAR))
                    ar = Integer.parseInt(husAr.getText());
                else
                {
                    vindu.visFeilmelding("Feilmelding", husAr.getText() + " er ikke et gyldig byggeår");
                    return false;
                }
        
                kvm = Integer.parseInt(husKvm.getText());
                belop = Integer.parseInt(belopHus.getText());
                belopInnbo = Integer.parseInt(belopHusInnbo.getText());
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
            double foreslåttPris = ForsikringsKalkulator.beregnHusforsikring(egenandelvalget, dekningvalget, ar, husmaterialevalget, kvm, belop, belopInnbo, alarm_b, hustypevalget, husstandardvalget );
                    
            husTilbud.setVisible(true);
            husTilbud.setText(String.valueOf(foreslåttPris));
            husTilbud.setToolTipText("Kan redigeres");
        }
    }
    
    /*Metode for å registrere et nytt hus- og innboforsikringsobjekt og legge
    dette inn i registeret. Oppdaterer også den eventuelle kundefanen som
    forsikringen hører til.*/ 
    @Override
    public void tegnNy()
    {
        if (hentInfo())
        {
            if( vindu.getRegister().getKundeliste().erKunde(kunde) == false )
            {
                vindu.getAnsatt().leggTilKundenøkel(kunde.getPersonnummer());
                register.nyKunde(kunde);
            }
                
            Forsikring nyForsikring = new Husforsikring(kunde, 
                    egenandelvalget, dekningvalget, adr, ar, hustypevalget, husmaterialevalget, 
                    husstandardvalget, kvm, belop, belopInnbo, alarm_b);
            
            nyForsikring.setArligPremie( Double.parseDouble(husTilbud.getText()) );
            vindu.getRegister().nyForsikring(nyForsikring);
            
            JOptionPane.showMessageDialog(null, "Du har nå tegnet husforsikring med nummer " 
                                          + nyForsikring.getForsikringsnummer() + " på " + kunde.getFornavn() 
                                          + " " + kunde.getEtternavn() , "Bekreftelse", 
                                            JOptionPane.INFORMATION_MESSAGE);
            visForsikring(nyForsikring);
            if(kundePanel != null)
                kundePanel.oppdaterVindu();
            }
    }
    
    /*Metode for å oppdatere en allerede eksisterende forsikring med ny input fra
    brukeren. Forutsetter at hentInfo()-metoden returnerer true. Oppdaterer også
    kundefanen som forsikringen hører til.*/
    @Override
    public void oppdaterForsikring()
    {
        if (hentInfo())
        {
            forsikring.setAdresse(adr);
            forsikring.setAlarm(alarm_b);
            forsikring.setMateriale(husmaterialevalget);
            forsikring.setKvadratmeter(kvm);
            forsikring.setBoligtype(hustypevalget);
            forsikring.setStandard(husstandardvalget);
            forsikring.setForsikringsbelopBygning(belop);
            forsikring.setForsikringsbelopInnbo(belopInnbo);
            forsikring.setEgenandel(egenandelvalget);
            forsikring.setByggeAr(ar);
            forsikring.setVilkar(vilkår);
            forsikring.setArligPremie(Double.parseDouble(husTilbud.getText()));
            
            if(kundePanel != null)
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
        int svar = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil deaktivere denne forsikringen?", "Forsikring " + String.valueOf(forsikring.getForsikringsnummer()), JOptionPane.YES_NO_OPTION);
        if (svar == JOptionPane.YES_OPTION)
        {
            knappePanel.remove(rediger);
            knappePanel.remove(lagreNyInfo);
            this.remove(beregnPris);
            knappePanel.remove(deaktiver);
            forsikring.setAktiver(false);
            JOptionPane.showMessageDialog(null, "Forsikring " + String.valueOf(forsikring.getForsikringsnummer()) + " er ikke lenger aktiv.", "Bekreftelse", JOptionPane.PLAIN_MESSAGE);
            repaint();
            revalidate();
            
            if(kundePanel != null)
                kundePanel.oppdaterVindu();
        }
    }
    
    //Viser vilkår i et nytt vindu. Henter vilkår fra fil
    @Override
    public void visVilkår()
    {
        if( forsikring == null )
            visForsikringensVilkår("Ny Husforsikring" + kunde.getFornavn() + " " + kunde.getEtternavn() , vilkår);
        else
            visForsikringensVilkår("Vilkår" + forsikring.getForsikringsnummer(), forsikring.getVilkar());
    }
    
    @Override
    public void velgVilkår() 
    {
        vilkår = this.velgVilkår( "Hus"+ dekningvelger.getItemAt(dekningvelger.getSelectedIndex()) );
        vilkarKnapp.setVisible(true);
    }
    
    //Klassens knappelytter
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
        else if (e.getSource() == vilkarKnapp)
        {
            visVilkår();
        }
        else if(e.getSource() == rediger)
        {
            redigerForsikring();
        }
        else if (e.getSource() == lagreNyInfo)
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
                velgVilkår();
        }
    }
}
