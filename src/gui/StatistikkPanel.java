
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

//Nødveidnige import-setninger
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import objekter.*;
import register.*;

/**
 *
 * @author Marthejansonskogen
 */

/*Klassens hensikt er å vise brukergrensesnittet til statistikksøk, hente 
input fra brukeren og skrive ut statistikken brukeren har valgt*/
public class StatistikkPanel extends JPanel implements ActionListener, ForsikringsPanel
{
    private AnsattVindu vindu;
    private HovedRegister register;
    private StatistikkVindu statistikkVindu;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    
    
    private final String[] soket = {"", "Alle kunder som har en gitt type forsikring",
                               "Antall skademeldinger innen en gitt tidsperiode", "Antall"
                              + " tegnede forsikringer innen en gitt tidsperiode"};
    private final JComboBox<String> sokevelger;
    private final String[] forsikringer = {"", "Bilforsikring", "Båtforsikring",
                            "Hus- og innboforsikring", "Fritidsboligforsikring",
                            "Reiseforsikring"};
    private final JComboBox<String> forsikringsvelgeren;
    //private final String[] skadetyper = {"", "Brann", "Tyveri/Hærverk", "Ulykke", "Tap", "Annet"};
    private final String[] skadetypeKjoretoy = {"", "Ansvar", "Glasskade", "Vei-/slepehjelp", "Tyveri/Hærverk", "Ulykke", "Annet"};
    private final String[] skadetypeEiendom = {"", "Brann", "Innbrudd/tyveri", "Hærverk", "Naturskade", "Vann", "Fryser/matvarer", "Annet"};
    private final String[] skadetypeReise = {"", "Tapt/forsinket bagasje", "Tyveri/tap", "Forsinket transport", "Sykdom/ulykke", "Avbestilling", "Annet"};
    private JComboBox<String> skadetypevelgeren;
    private final String[] utgifter = {"", "Total utbetaling av erstatninger i løpet"
                                        + " av en gitt tidsperiode",
                                "Total utbetaling av erstatninger for en gitt"
                                 + " forsikringstype i løpet av en gitt tidsperiode"};
    private final JComboBox<String> utgiftsvelger;
    private final String[] inntekter = {"", "Total forsikringspremieinntekter i løpet"
                                        + " en gitt tidsperiode", 
                                    "Total forsikringspremieinntekter for en"
                                    + " gitt forsikringstype i løpet av en gitt tidsperiode"};
    private final JComboBox<String> inntektsvelger;
    
    //Statistikk
    private final String[] statistikk = {"", "Øking/minking av antall skademeldinger"
                                        + " innenfor en gitt tidsperiode", 
                                        "Øking/minking av skademeldinger av en "
                                   + "bestemt forsikringstype innenfor en gitt tidsperiode", 
                                     "Øking/minking av skademeldinger av en bestemt "
                                    + "skadetype innenfor en gitt tidsperiode",
                              "Øking/minking av de totale erstatningskostnadene "
                                + "innenfor en gitt tidsperiode", 
                                "Øking/minking av erstatningskostnadene for en "
                                + "gitt skadetype innenfor en gitt tidsperiode", 
                                   "Type forsikringer rangert etter antall"};
    private final JComboBox<String> statistikkvelger;
    private final JTextField stDatoDag;
    private final JTextField stDatoMnd;
    private final JTextField stDatoAr;
    private final JTextField slDatoDag;
    private final JTextField slDatoMnd;
    private final JTextField slDatoAr;
    private final JLabel skadetypelabel;
    private final JLabel forsikringsLabel;
    private final JLabel startDagLabel;
    private final JLabel startMndLabel;
    private final JLabel startArLabel;
    private final JLabel sluttDagLabel;
    private final JLabel sluttMndLabel;
    private final JLabel sluttArLabel;
    private final JLabel tomLabel;
    private final JLabel fomLabel;
    private final JButton sokKnapp;
    
    private String forsikringsvalg;
    private String skadetypevalg;
    private GregorianCalendar startDato;
    private GregorianCalendar sluttDato;
    private JPanel avansertSokPanel2;
    
    private ComboBoxModel<String> skadetypeModellKjoretoy;
    private ComboBoxModel<String> skadetypeModellEiendom;
    private ComboBoxModel<String> skadetypeModellReise;
    
    private DecimalFormat df;
    
    //Tar imot AnsattVinduobjektet som parameter    
    public StatistikkPanel(AnsattVindu v)
    {
        vindu = v;
        register = vindu.getRegister();
        df = new DecimalFormat("###.##");
        sokevelger = new JComboBox<>(soket);
        utgiftsvelger = new JComboBox<>(utgifter);
        inntektsvelger = new JComboBox<>(inntekter);
        forsikringsvelgeren = new JComboBox<>(forsikringer);
        forsikringsvelgeren.setVisible(false);
        statistikkvelger = new JComboBox<>(statistikk);
        skadetypevelgeren = new JComboBox<>();
        skadetypevelgeren.setVisible(false);
        skadetypeModellKjoretoy = new DefaultComboBoxModel(skadetypeKjoretoy);
        skadetypeModellEiendom = new DefaultComboBoxModel(skadetypeEiendom);
        skadetypeModellReise = new DefaultComboBoxModel(skadetypeReise);
        skadetypelabel = new JLabel("Velg skadetype: ");
        forsikringsLabel = new JLabel("Velg forsikringstype: ");
        forsikringsLabel.setVisible(false);
        startDagLabel = new JLabel("Dag: (dd)");
        startDagLabel.setVisible(false);
        startMndLabel = new JLabel("Måned: (mm)");
        startMndLabel.setVisible(false);
        startArLabel = new JLabel("År: (åååå)");
        startArLabel.setVisible(false);
        tomLabel = new JLabel("Til og med: ");
        tomLabel.setVisible(false);
        fomLabel = new JLabel("Fra og med: ");
        fomLabel.setVisible(false);
        sluttDagLabel = new JLabel("Dag: (dd)");
        sluttDagLabel.setVisible(false);
        sluttMndLabel = new JLabel("Måned: (mm)");
        sluttMndLabel.setVisible(false);
        sluttArLabel = new JLabel("År: (åååå)");
        sluttArLabel.setVisible(false);
        skadetypelabel.setVisible(false);
        stDatoDag = new JTextField(2);
        stDatoMnd = new JTextField(2);
        stDatoAr = new JTextField(4);
        slDatoDag = new JTextField(2);
        slDatoMnd = new JTextField(2);
        slDatoAr = new JTextField(4);
        stDatoDag.setVisible(false);
        stDatoMnd.setVisible(false);
        stDatoAr.setVisible(false);
        slDatoDag.setVisible(false);
        slDatoMnd.setVisible(false);
        slDatoAr.setVisible(false);
        sokKnapp = new JButton("Søk");
        sokKnapp.setEnabled(false);
        sokKnapp.addActionListener(this);
        
        JPanel avansertSokPanel1 = new JPanel();
        avansertSokPanel2 = new JPanel();
        JPanel avansertSokPanel3 = new JPanel();
        JPanel avansertSokPanel4 = new JPanel();
        avansertSokPanel3.setLayout(new BoxLayout(avansertSokPanel3, 
                BoxLayout.PAGE_AXIS));
        avansertSokPanel1.setLayout(new GridLayout(11,1,2,2));
        avansertSokPanel2.setLayout(new GridLayout(8,3,2,2));
        avansertSokPanel4.setLayout(new GridLayout(2,2,1,1));
        avansertSokPanel1.add(new JLabel("Søk etter:"));
        avansertSokPanel1.add(sokevelger);
        avansertSokPanel1.add(new JLabel());
        avansertSokPanel1.add(new JLabel("Skriv ut utgifter:"));
        avansertSokPanel1.add(utgiftsvelger);
        avansertSokPanel1.add(new JLabel());
        avansertSokPanel1.add(new JLabel("Skriv ut inntekter:"));
        avansertSokPanel1.add(inntektsvelger);
        avansertSokPanel1.add(new JLabel());
        avansertSokPanel1.add(new JLabel("Skriv ut statistikk om:"));
        avansertSokPanel1.add(statistikkvelger);
       
        avansertSokPanel4.add(forsikringsLabel);
        avansertSokPanel4.add(forsikringsvelgeren);
        avansertSokPanel4.add(skadetypelabel);
        avansertSokPanel4.add(skadetypevelgeren);
        avansertSokPanel2.add(fomLabel);
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(startDagLabel);
        avansertSokPanel2.add(startMndLabel);
        avansertSokPanel2.add(startArLabel);
        avansertSokPanel2.add(stDatoDag);
        avansertSokPanel2.add(stDatoMnd);
        avansertSokPanel2.add(stDatoAr);
        avansertSokPanel2.add(tomLabel);
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(sluttDagLabel);
        avansertSokPanel2.add(sluttMndLabel);
        avansertSokPanel2.add(sluttArLabel);
        avansertSokPanel2.add(slDatoDag);
        avansertSokPanel2.add(slDatoMnd);
        avansertSokPanel2.add(slDatoAr);
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(sokKnapp);
        avansertSokPanel3.add(avansertSokPanel4);
        avansertSokPanel3.add(avansertSokPanel2);
        add(avansertSokPanel1);
        add(Box.createRigidArea(new Dimension(5,1)));
        add(avansertSokPanel3);
     
        /*Legger til lyttere til comboboxene. Hvis et valg i en av comboboxene er
        valgt, så skal ikke de andre comboboxene være mulig å velge ifra lenger og
        søkknappen blir vist. Hvis brukeren setter valget til blankt igjen, så blir 
        de andre comboboxene enabled/velgbare igjen og søkknappen blir skjult til noe
        er valgt. Avhengig av hva brukeren har valgt, så blir også valg for forsikring
        og/eller inputfelter for dato vist/skjult.*/
        sokevelger.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                switch (sokevelger.getSelectedIndex())
                {
                    case 0:
                        sokKnapp.setEnabled(false);
                        utgiftsvelger.setEnabled(true);
                        inntektsvelger.setEnabled(true);
                        statistikkvelger.setEnabled(true);
                        disableDatoFelter();
                        forsikringsLabel.setVisible(false);
                        forsikringsvelgeren.setVisible(false);
                        forsikringsvelgeren.setSelectedIndex(0);
                        break;
                    case 1:
                        utgiftsvelger.setEnabled(false);
                        inntektsvelger.setEnabled(false);
                        statistikkvelger.setEnabled(false);
                        sokKnapp.setEnabled(true);
                        disableDatoFelter();
                        forsikringsLabel.setVisible(true);
                        forsikringsvelgeren.setVisible(true);
                        break;
                    case 2:
                    case 3:
                        utgiftsvelger.setEnabled(false);
                        inntektsvelger.setEnabled(false);
                        statistikkvelger.setEnabled(false);
                        sokKnapp.setEnabled(true);
                        enableDatoFelter();
                        forsikringsLabel.setVisible(true);
                        forsikringsvelgeren.setVisible(true);
                }
            }
        });
        
        utgiftsvelger.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                switch (utgiftsvelger.getSelectedIndex())
                {
                    case 0:
                        sokKnapp.setEnabled(false);
                        sokevelger.setEnabled(true);
                        inntektsvelger.setEnabled(true);
                        statistikkvelger.setEnabled(true);
                        disableDatoFelter();
                        forsikringsLabel.setVisible(false);
                        forsikringsvelgeren.setVisible(false);
                        forsikringsvelgeren.setSelectedIndex(0);
                        break;
                    case 1:
                        sokevelger.setEnabled(false);
                        inntektsvelger.setEnabled(false);
                        statistikkvelger.setEnabled(false);
                        sokKnapp.setEnabled(true);
                        enableDatoFelter();
                        forsikringsLabel.setVisible(false);
                        forsikringsvelgeren.setVisible(false);
                        forsikringsvelgeren.setSelectedIndex(0);
                        break;
                    case 2:
                        sokevelger.setEnabled(false);
                        inntektsvelger.setEnabled(false);
                        statistikkvelger.setEnabled(false);
                        sokKnapp.setEnabled(true);
                        enableDatoFelter();
                        forsikringsLabel.setVisible(true);
                        forsikringsvelgeren.setVisible(true);
                        break;
                }
            }
        });
        
        inntektsvelger.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                switch (inntektsvelger.getSelectedIndex())
                {
                    case 0:
                        sokKnapp.setEnabled(false);
                        sokevelger.setEnabled(true);
                        utgiftsvelger.setEnabled(true);
                        statistikkvelger.setEnabled(true);
                        disableDatoFelter();
                        forsikringsLabel.setVisible(false);
                        forsikringsvelgeren.setVisible(false);
                        forsikringsvelgeren.setSelectedIndex(0);
                        break;
                    case 1:
                        sokevelger.setEnabled(false);
                        utgiftsvelger.setEnabled(false);
                        statistikkvelger.setEnabled(false);
                        sokKnapp.setEnabled(true);
                        enableDatoFelter();
                        forsikringsLabel.setVisible(false);
                        forsikringsvelgeren.setVisible(false);
                        forsikringsvelgeren.setSelectedIndex(0);
                        break;
                    case 2:
                        sokevelger.setEnabled(false);
                        utgiftsvelger.setEnabled(false);
                        statistikkvelger.setEnabled(false);
                        sokKnapp.setEnabled(true);
                        enableDatoFelter();
                        forsikringsLabel.setVisible(true);
                        forsikringsvelgeren.setVisible(true);
                        break;
                }
            }
        });
    
        /*Hvis brukeren har valgt å få ut statistikk angående skadetype, så blir også
         en skadetypevelger vist avhengig av hvilken forsikring som er valgt.*/
        statistikkvelger.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                switch (statistikkvelger.getSelectedIndex())
                {
                    case 0:
                        sokKnapp.setEnabled(false);
                        sokevelger.setEnabled(true);
                        utgiftsvelger.setEnabled(true);
                        inntektsvelger.setEnabled(true);
                        skadetypelabel.setVisible(false);
                        skadetypevelgeren.setVisible(false);
                        disableDatoFelter();
                        forsikringsLabel.setVisible(false);
                        forsikringsvelgeren.setVisible(false);
                        forsikringsvelgeren.setSelectedIndex(0);
                        break;
                    case 1:
                        sokevelger.setEnabled(false);
                        utgiftsvelger.setEnabled(false);
                        inntektsvelger.setEnabled(false);
                        skadetypelabel.setVisible(false);
                        skadetypevelgeren.setVisible(false);
                        sokKnapp.setEnabled(true);
                        enableDatoFelter();
                        forsikringsLabel.setVisible(false);
                        forsikringsvelgeren.setVisible(false);
                        forsikringsvelgeren.setSelectedIndex(0);
                        break;
                    case 2:
                        sokevelger.setEnabled(false);
                        utgiftsvelger.setEnabled(false);
                        inntektsvelger.setEnabled(false);
                        skadetypelabel.setVisible(false);
                        skadetypevelgeren.setVisible(false);
                        sokKnapp.setEnabled(true);
                        enableDatoFelter();
                        forsikringsLabel.setVisible(true);
                        forsikringsvelgeren.setVisible(true);
                        break;
                    case 4:
                        sokevelger.setEnabled(false);
                        utgiftsvelger.setEnabled(false);
                        inntektsvelger.setEnabled(false);
                        skadetypelabel.setVisible(false);
                        skadetypevelgeren.setVisible(false);
                        sokKnapp.setEnabled(true);
                        enableDatoFelter();
                        forsikringsLabel.setVisible(false);
                        forsikringsvelgeren.setVisible(false);
                        forsikringsvelgeren.setSelectedIndex(0);
                        break;
                    case 6:
                        sokevelger.setEnabled(false);
                        utgiftsvelger.setEnabled(false);
                        inntektsvelger.setEnabled(false);
                        skadetypelabel.setVisible(false);
                        skadetypevelgeren.setVisible(false);
                        sokKnapp.setEnabled(true);
                        disableDatoFelter();
                        forsikringsLabel.setVisible(false);
                        forsikringsvelgeren.setVisible(false);
                        forsikringsvelgeren.setSelectedIndex(0);
                        break;
                    case 3:
                    case 5:
                        enableDatoFelter();
                        forsikringsLabel.setVisible(true);
                        forsikringsvelgeren.setVisible(true);
                        forsikringsvelgeren.addItemListener(new ItemListener()
                        {
                            public void itemStateChanged(ItemEvent e)
                            {
                                switch (forsikringsvelgeren.getSelectedIndex())
                                {
                                    case 0:
                                        sokKnapp.setEnabled(false);
                                        skadetypelabel.setVisible(false);
                                        skadetypevelgeren.setVisible(false);
                                        break;
                                    case 1:
                                    case 2:
                                        skadetypevelgeren.setModel(skadetypeModellKjoretoy);
                                        skadetypelabel.setVisible(true);
                                        skadetypevelgeren.setVisible(true);
                                        sokKnapp.setEnabled(true);
                                        break;
                                    case 3:
                                    case 4:
                                        skadetypevelgeren.setModel(skadetypeModellEiendom);
                                        skadetypelabel.setVisible(true);
                                        skadetypevelgeren.setVisible(true);
                                        sokKnapp.setEnabled(true);
                                        break;
                                    case 5:
                                        skadetypevelgeren.setModel(skadetypeModellReise);
                                        skadetypelabel.setVisible(true);
                                        skadetypevelgeren.setVisible(true);
                                        sokKnapp.setEnabled(true);
                                        break;
                                }
                            }
                        });
                        break;
                }
            }
        });
    }

    /*Tømmer alle inputfelter og setter statistikkvalget til blankt, skjuler
     forsikrings-/skadetypevelgerene*/
    public void tømFelter()
    {
        for (Component component : getKomponenter(this))
        {
            if ((component instanceof JTextField))
            {
                JTextField tf = (JTextField) component;
                tf.setText("");
            }
            else if (component instanceof JComboBox)
            {
                JComboBox cb = (JComboBox) component;
                if (cb.equals(skadetypevelgeren) || cb.equals(forsikringsvelgeren))
                {
                    cb.setVisible(false);
                }
                else
                {
                    cb.setSelectedIndex(0);
                }
            }
        }
        disableDatoFelter();
    }

    //Skjuler datofeltene
    public void disableDatoFelter()
    {
        for (Component component : getKomponenter(avansertSokPanel2))
        {
            if ((component instanceof JTextField))
            {
                JTextField tf = (JTextField) component;
                tf.setVisible(false);
            }
            if ((component instanceof JLabel))
            {
                JLabel label = (JLabel) component;
                label.setVisible(false);
            }
        }
    }

    //Gjør datofeltene synlige
    public void enableDatoFelter()
    {
        for (Component component : getKomponenter(avansertSokPanel2))
        {
            if ((component instanceof JTextField))
            {
                JTextField tf = (JTextField) component;
                tf.setVisible(true);
            }
            if ((component instanceof JLabel))
            {
                JLabel label = (JLabel) component;
                label.setVisible(true);
            }
        }
    }

    /*Skriver ut alle kundene med en gitt forsikringstype i en tabell i
    statistikkvinduet. Hvis det ikke er noen treff, så kommer det feilmelding */
    public void alleKunderMedForsikring()
    {
        try
        {
            forsikringsvalg = forsikringsvelgeren.getItemAt(
                    forsikringsvelgeren.getSelectedIndex());
            List<Kunde> liste = register.getAlleKunderMedForsikring(forsikringsvalg);
            JTable tabell = new JTable();
            tabell.setModel(new TabellModell(liste));
            tabell.setPreferredScrollableViewportSize(new Dimension(700, 200));
            statistikkVindu = new StatistikkVindu("Kunder med " + forsikringsvalg,
                    new JScrollPane(tabell), new Dimension(900, 200));
            tømFelter();
        } 
        catch (NullPointerException e)
        {
            feilMelding("Søket ga ingen treff");
        }
    }

    /*Skriver ut antall skademeldinger innenfor en gitt tidsperiode. Hvis bruker
    har valgt forsikringstype blir antall skademeldinger på denne forsikringstypen
    skrevet ut. Hvis ikke, blir det totale antallet skademeldinger skrevet ut.
    Hvis det ikke er noen treff, så kommer det feilmelding om dette*/
    public void antSkademeldinger()
    {
        startDato = new GregorianCalendar((Integer.parseInt(stDatoAr.getText())),
                (Integer.parseInt(stDatoMnd.getText())), Integer.parseInt(stDatoDag.getText()));
        sluttDato = new GregorianCalendar((Integer.parseInt(slDatoAr.getText())),
                (Integer.parseInt(slDatoMnd.getText())), Integer.parseInt(slDatoDag.getText()));
        List<Skademelding> skademeldingsliste = new ArrayList<>();
        String s;

        try
        {
            if (forsikringsvelgeren.getSelectedIndex() != 0)
            {
                forsikringsvalg = forsikringsvelgeren.getItemAt(forsikringsvelgeren.getSelectedIndex());
                s = "Registrerte skademeldinger på " + forsikringsvalg.toLowerCase() + "er i perioden ";
                for (Skademelding skademld : register.getSkademeldingsregister().alleSkademeldinger())
                {
                    if (skademld.getOpprettetDato().getTime().after(startDato.getTime()) && 
                            skademld.getOpprettetDato().getTime().before(sluttDato.getTime())
                            && skademld.getForsikring().getForsikringsType()
                                    .equals(forsikringsvalg))
                    {
                        skademeldingsliste.add(skademld);
                    }
                }
            } 
            else
            {
                s = "Registrerte skademeldinger i perioden: ";
                for (Skademelding skademld : register.getSkademeldingsregister().alleSkademeldinger())
                {
                    if (skademld.getOpprettetDato().getTime().after(startDato.getTime()) && 
                            skademld.getOpprettetDato().getTime().before(sluttDato.getTime()))
                    {
                        skademeldingsliste.add(skademld);
                    }
                }
            }

            JTable tabell = new JTable(new TabellModellSkademeldinger(skademeldingsliste));
            tabell.setPreferredScrollableViewportSize(new Dimension(700, 180));
            JPanel pane = new JPanel();
            JTextField textField = new JTextField("Antall skademeldinger i perioden: " + skademeldingsliste.size());
            textField.setEditable(false);
            pane.add(textField);
            pane.add(new JScrollPane(tabell));
            statistikkVindu = new StatistikkVindu(s
                    + sdf.format(startDato.getTime()) + " - " + 
                    sdf.format(sluttDato.getTime()), pane, new Dimension(700, 180));
            tømFelter();
        }
        catch (NullPointerException e)
        {
            feilMelding("Søket ga ingen treff");
        }
    }

    /*Skriver ut antall forsikringer innenfor en gitt tidsperiode. Hvis bruker
    har valgt forsikringstype blir antall forsikringer av denne typen skrevet ut.
    Hvis ikke, blir det totale antallet forsikringer skrevet ut.
    Hvis det ikke er noen treff, så kommer det feilmelding om dette*/
    public void antForsikringer()
    {
        startDato = new GregorianCalendar((Integer.parseInt(stDatoAr.getText())), (Integer.parseInt(stDatoMnd.getText())), Integer.parseInt(stDatoDag.getText()));
        sluttDato = new GregorianCalendar((Integer.parseInt(slDatoAr.getText())), (Integer.parseInt(slDatoMnd.getText())), Integer.parseInt(slDatoDag.getText()));
        List<Forsikring> forsikringsliste = new ArrayList<>();
        String s = "";

        int antall = 0;
        try
        {
            if (forsikringsvelgeren.getSelectedIndex() != 0) {
                forsikringsvalg = forsikringsvelgeren.getItemAt(forsikringsvelgeren.getSelectedIndex());
                s = "Tegnede " + forsikringsvalg.toLowerCase() + "er i perioden ";

                for (Forsikring forsikring : register.getForsikringrsliste().alleForsikringer())
                {
                    if (forsikring.getForsikringsType().equals(forsikringsvalg) &&
                            forsikring.getStartdato().after(startDato) && 
                            forsikring.getStartdato().before(sluttDato))
                    {
                        forsikringsliste.add(forsikring);
                        antall++;
                    }
                }
            }
            else
            {
                s = "Tegnede forsikringer i perioden ";
                for (Forsikring forsikring : register.getForsikringrsliste().alleForsikringer())
                {
                    if (forsikring.getStartdato().after(startDato) && 
                            forsikring.getStartdato().before(sluttDato)) 
                    {
                        forsikringsliste.add(forsikring);
                        antall++;
                    }
                }
            }

            JTable tabell = new JTable();
            tabell.setModel(new TabellModellForsikring(forsikringsliste));
            tabell.setPreferredScrollableViewportSize(new Dimension(700, 180));
            JPanel pane = new JPanel();
            JTextField textField = new JTextField("Antall tegnede forsikringer i perioden: " + antall);
            textField.setEditable(false);
            pane.add(textField);
            pane.add(new JScrollPane(tabell));
            statistikkVindu = new StatistikkVindu(s
                    + sdf.format(startDato.getTime()) + " - " + sdf.format(sluttDato.getTime()), pane, new Dimension(700, 180));
            tømFelter();
        }
        catch (NullPointerException e)
        {
            feilMelding("Søket ga ingen treff");
        }
    }

    /*Skriver ut de totale erstatningsutgiftene innenfor en gitt tidsperiode.
    Hvis det ikke er noen treff, så kommer det feilmelding om dette*/
    public void totalErstatning()
    {
        startDato = new GregorianCalendar((Integer.parseInt(stDatoAr.getText())),
                (Integer.parseInt(stDatoMnd.getText())), Integer.parseInt(stDatoDag.getText()));
        sluttDato = new GregorianCalendar((Integer.parseInt(slDatoAr.getText())),
                (Integer.parseInt(slDatoMnd.getText())), Integer.parseInt(slDatoDag.getText()));
        double totalSum = 0.0;
        int antall = 0;
        double gjennomsnitt = 0;
        try
        {
            for (Skademelding skademelding : register.getSkademeldingsregister().alleSkademeldinger())
            {
                if (skademelding.getOpprettetDato().getTime().after(startDato.getTime()) && 
                        skademelding.getOpprettetDato().getTime().before(sluttDato.getTime()))
                {
                    totalSum += skademelding.getErstatningsbelop();
                    antall++;
                }
            }

            gjennomsnitt = totalSum / antall;
            JTextArea textArea = new JTextArea();
            textArea.setText("Totalt utbetalt: " + totalSum + "kr\nAntall "
                    + "skademeldinger i perioden: " + antall
                    + "\nGjennomsnittlig erstatning per skademelding "
                    + "i denne perioden: " + gjennomsnitt + "kr");

            statistikkVindu = new StatistikkVindu("Totalt utbetalt "
                    + "erstatningsbeløp i perioden " + sdf.format(startDato.getTime())
                    + " - " + sdf.format(sluttDato.getTime()), textArea, 
                    new Dimension(600, 300));
            tømFelter();
        }
        catch (NullPointerException | ArithmeticException e)
        {
            feilMelding("Søket ga ingen treff");
        }
    }
    /*Skriver ut de totale ertsatningsutgiftene på en gitt forsikringstype
    innenfor en gitt tidsperiode.
    Hvis det ikke er noen treff, så kommer det feilmelding om dette*/
    public void totalErstatningPaForsikring()
    {
        forsikringsvalg = forsikringsvelgeren.getItemAt(forsikringsvelgeren.getSelectedIndex());
        startDato = new GregorianCalendar((Integer.parseInt(stDatoAr.getText())), (Integer.parseInt(stDatoMnd.getText())), Integer.parseInt(stDatoDag.getText()));
        sluttDato = new GregorianCalendar((Integer.parseInt(slDatoAr.getText())), (Integer.parseInt(slDatoMnd.getText())), Integer.parseInt(slDatoDag.getText()));
        double totalSum = 0.0;
        int antall = 0;
        double gjennomsnitt = 0;

        try
        {
            for (Skademelding skademelding : register.getSkademeldingsregister().alleSkademeldinger())
            {
                if (skademelding.getForsikring().getForsikringsType().
                        equals(forsikringsvalg) && skademelding.getOpprettetDato().getTime().after(startDato.getTime())
                        && skademelding.getOpprettetDato().getTime().before(sluttDato.getTime()))
                {
                    totalSum += skademelding.getErstatningsbelop();
                    antall++;
                }
            }

            gjennomsnitt = totalSum / antall;
            JTextArea textArea = new JTextArea();
            textArea.setText("Totalt utbetalt: " + totalSum + "kr\nAntall skademeldinger i perioden: " + antall
                    + "\nGjennomsnittlig erstatning per skademelding "
                    + "i denne perioden: " + gjennomsnitt + "kr");

            statistikkVindu = new StatistikkVindu("Totalt utbetalt erstatningsbeløp på " + forsikringsvalg.toLowerCase() + "er i perioden "
                    + sdf.format(startDato.getTime()) + " - " + sdf.format(sluttDato.getTime()), textArea, new Dimension(600, 300));
            tømFelter();
        } 
        catch (NullPointerException | ArithmeticException e)
        {
            feilMelding("Søket ga ingen treff");
        }
    }

    /*Skriver ut de totale premieinntektene innenfor en gitt tidsperiode.
    Hvis det ikke er noen treff, så kommer det feilmelding om dette*/
    public void totalPremieinntekt()
    {
        //Total utbetaling av erstatninger i en gitt periode
        startDato = new GregorianCalendar((Integer.parseInt(stDatoAr.getText())),
                (Integer.parseInt(stDatoMnd.getText())), Integer.parseInt(stDatoDag.getText()));
        sluttDato = new GregorianCalendar((Integer.parseInt(slDatoAr.getText())),
                (Integer.parseInt(slDatoMnd.getText())), Integer.parseInt(slDatoDag.getText()));
        double totalSum = 0.0;
        int antall = 0;
        double gjennomsnitt = 0;
        try
        {
            for (Inntekt inntekt : register.getAlleInntekter())
            {
                if (inntekt.getDato().after(startDato.getTime()) && inntekt.getDato()
                        .before(sluttDato.getTime()))
                {
                    totalSum += inntekt.getSum();
                    antall++;
                }
            }

            gjennomsnitt = totalSum / antall;
            JTextArea textArea = new JTextArea();
            textArea.setText("Totale premieinntekter: " + totalSum + "kr\nAntall innbetalinger i perioden: " + antall
                    + "\nGjennomsnittlig premieinntekt per forsikring "
                    + "i denne perioden: " + gjennomsnitt + "kr");

            statistikkVindu = new StatistikkVindu("Totale premieinntekter i perioden "
                    + sdf.format(startDato.getTime()) + " - " + sdf.format(sluttDato.getTime()),
                    textArea, new Dimension(600, 300));

            tømFelter();
        }
        catch (NullPointerException | ArithmeticException e)
        {
            feilMelding("Søket ga ingen treff");
        }
    }

    /*Skriver ut de totale premieinntektene for en gitt forsikringstype innenfor
    en gitt tidsperiode. Hvis det ikke er noen treff, så kommer det feilmelding 
    om dette*/
    public void totalPremieinntektPaForsikringstype()
    {
        //Total utbetaling av inntekt på forsikringstype i en gitt periode
        forsikringsvalg = forsikringsvelgeren.getItemAt(forsikringsvelgeren.getSelectedIndex());
        startDato = new GregorianCalendar((Integer.parseInt(stDatoAr.getText())),
                (Integer.parseInt(stDatoMnd.getText())), Integer.parseInt(stDatoDag.getText()));
        sluttDato = new GregorianCalendar((Integer.parseInt(slDatoAr.getText())),
                (Integer.parseInt(slDatoMnd.getText())), Integer.parseInt(slDatoDag.getText()));
        double totalSum = 0.0;
        int antall = 0;
        double gjennomsnitt = 0;

        try
        {
            for (Inntekt inntekt : register.getAlleInntekter())
            {
                if (inntekt.getForsikring().getForsikringsType().equals(forsikringsvalg))
                {
                    if (inntekt.getDato().getTime().after(startDato.getTime()) &&
                            inntekt.getDato().getTime().before(sluttDato.getTime()))
                    {
                        totalSum += inntekt.getSum();
                        antall++;
                    }
                }
            }

            gjennomsnitt = totalSum / antall;
            JTextArea textArea = new JTextArea();
            textArea.setText("Totale premieinntekter på " + forsikringsvalg.toLowerCase() 
                    + ":" + totalSum + "kr\nAntall innbetalinger i perioden: " + 
                    antall + "\nGjennomsnittlig premieinntekt per forsikring "
                    + "i denne perioden: " + gjennomsnitt + "kr");

            statistikkVindu = new StatistikkVindu("Totale premieinntekter på " + 
                    forsikringsvalg.toLowerCase() + "er i perioden "
                    + sdf.format(startDato.getTime()) + " - " + sdf.format(sluttDato.getTime()), 
                    textArea, new Dimension(600, 300));

            tømFelter();
        }
        catch (NullPointerException | ArithmeticException e)
        {
            feilMelding("Søket ga ingen treff");
        }
    }

    /*Skriver ut økning/minking av skademeldinger innenfor en gitt tidsperiode.
    Økingen/minkingen blir regnet som antall skademeldinger/antall måneder i perioden
    minus skademeldinger/antall måneder siden programmets start. Programmets 
    start er satt til når den første forsikringen ble tegnet.  Hvis det ikke er 
    noen treff, kommer det feilmelding om dette*/
    public void statistikkSkademeldinger()
    {
        startDato = new GregorianCalendar((Integer.parseInt(stDatoAr.getText())),
                (Integer.parseInt(stDatoMnd.getText())), Integer.parseInt(stDatoDag.getText()));
        sluttDato = new GregorianCalendar((Integer.parseInt(slDatoAr.getText())),
                (Integer.parseInt(slDatoMnd.getText())), Integer.parseInt(slDatoDag.getText()));
        List<Skademelding> skademeldingsliste = new ArrayList<>();
        double antallIPerioden = 0;
        double antallForAlltid = 0;

        try
        {
            for (Skademelding skademld : register.getSkademeldingsregister().alleSkademeldinger())
            {
                if (skademld.getOpprettetDato().getTime().after(startDato.getTime()) && skademld.getOpprettetDato().getTime().before(sluttDato.getTime()))
                {
                    skademeldingsliste.add(skademld);
                    antallIPerioden++;
                }
                antallForAlltid++;
            }
            long periodeIDager = (sluttDato.getTime().getTime() - 
                    startDato.getTime().getTime()) / 1000 / 60 / 60 / 24 ;
            double gjennomsnittPerioden = antallIPerioden / ((double)periodeIDager/30);
            Calendar programStartDato = register.getForsikringrsliste().getForsikring(1000001).getStartdato();
            Calendar programSluttDato = new GregorianCalendar();
            long alltidIDager = (programSluttDato.getTime().getTime() -
                    programStartDato.getTime().getTime()) / 1000 / 60 / 60 / 24;
            double gjennomsnittAlltid = antallForAlltid / ((double)alltidIDager/30);
            System.out.println(gjennomsnittAlltid + " " + gjennomsnittPerioden);
            String s;
            double endring = gjennomsnittPerioden - gjennomsnittAlltid;
            double prosent = ((endring / gjennomsnittAlltid)*100);

            if (endring >= 0)
            {
                s = "økt";
            } 
            else
            {
                s = "minket";
            }

            JTextArea textArea = new JTextArea();
            textArea.setText("Antall skademeldinger har " + s + " med " + 
                    df.format(endring) + " stk / " + df.format(prosent)
                    + "% månedelig\ni perioden " + sdf.format(startDato.getTime()) + " - " 
                    + sdf.format(sluttDato.getTime()));
            statistikkVindu = new StatistikkVindu("Økning/Minking i perioden "
                    + sdf.format(startDato.getTime()) + " - " + sdf.format(sluttDato.getTime()), 
                    textArea, new Dimension(600, 300));

            tømFelter();
        }
        catch (NullPointerException | ArithmeticException e)
        {
            feilMelding("Søket ga ingen treff");
        }

    }

    /*Skriver ut økning/minking av skademeldinger på en gitt forsikringstype
    innenfor en gitt tidsperiode. Økingen/minkingen blir regnet som antall 
    skademeldinger/antall måneder i perioden minus skademeldinger/antall måneder
    siden programmets start. Programmets start er satt til når den første 
    forsikringen ble tegnet. Hvis det ikke er noen treff, kommer det feilmelding 
    om dette*/
    public void statistikkSkademeldingPaForsikring()
    {
        forsikringsvalg = forsikringsvelgeren.getItemAt(forsikringsvelgeren.getSelectedIndex());
        startDato = new GregorianCalendar((Integer.parseInt(stDatoAr.getText())),
                (Integer.parseInt(stDatoMnd.getText())), Integer.parseInt(stDatoDag.getText()));
        sluttDato = new GregorianCalendar((Integer.parseInt(slDatoAr.getText())),
                (Integer.parseInt(slDatoMnd.getText())), Integer.parseInt(slDatoDag.getText()));
        List<Skademelding> skademeldingsliste = new ArrayList<>();
        double antallIPerioden = 0;
        double antallForAlltid = 0;
        try
        {
            for (Skademelding skademld : register.getSkademeldingsregister().alleSkademeldinger())
            {
                if (skademld.getForsikring().getForsikringsType().equals(forsikringsvalg))
                {
                    if (skademld.getOpprettetDato().getTime().after(startDato.getTime()) &&
                            skademld.getOpprettetDato().getTime().before(sluttDato.getTime()))
                    {
                        skademeldingsliste.add(skademld);
                        antallIPerioden++;
                    }
                    antallForAlltid++;
                }
            }

           long periodeIDager = (sluttDato.getTime().getTime() - 
                    startDato.getTime().getTime()) / 1000 / 60 / 60 / 24;
            
            System.out.println("PeriodeIMnd: " + periodeIDager);
            System.out.println("AntallIPeriode: " + antallIPerioden);
            System.out.println("AntallFor alltid: " + antallForAlltid);
            double gjennomsnittPerioden = (antallIPerioden / ((double)periodeIDager/30));
            Calendar programStartDato = register.getForsikringrsliste().getForsikring(1000001).getStartdato();
            Calendar programSluttDato = new GregorianCalendar();
            long alltidIDager = (programSluttDato.getTime().getTime() - 
                    programStartDato.getTime().getTime()) / 1000 / 60 / 60 / 24;
            double gjennomsnittAlltid = antallForAlltid / ((double)alltidIDager/30);
            String s;
            double endring = gjennomsnittPerioden - gjennomsnittAlltid;
            double prosent = ((endring / gjennomsnittAlltid)*100);
            System.out.println("AlltidIMnd:" + alltidIDager);
            System.out.println(gjennomsnittPerioden);
            System.out.println(gjennomsnittAlltid);
            System.out.println(endring);
            System.out.println(prosent);
            

            if (endring >= 0)
            {
                s = "økt";
            } 
            else 
            {
                s = "minket";
            }

            JTextArea textArea = new JTextArea();
            textArea.setText("Antall skademeldinger på " + forsikringsvalg.toLowerCase()
                    + "er har " + s + " med " + df.format(endring) + " stk / " + 
                    df.format(prosent) + "% \nmånedelig i perioden " + sdf.format(startDato.getTime())
                    + " - " + sdf.format(sluttDato.getTime()));

            statistikkVindu = new StatistikkVindu("Øking/Minking av skademeldinger"
                    + " på " + forsikringsvalg.toLowerCase() + "er i perioden "
                    + sdf.format(startDato.getTime()) + " - " + sdf.format(sluttDato.getTime()), 
                    textArea, new Dimension(600, 300));

            tømFelter();

        }
        catch (NullPointerException | ArithmeticException e)
        {
            feilMelding("Søket ga ingen treff");
        }
    }

    /*Skriver ut økning/minking av skademeldinger på en på
    en gitt skadetype innenfor en gitt tidsperiode. Økingen/minkingen blir
    regnet som antall skademeldinger/antall måneder i perioden minus
    skademeldinger/antall måneder siden programmets start. Programmets start er
    satt til når den første forsikringen ble tegnet. Hvis det ikke er noen treff,
    kommer det feilmelding om dette*/
    public void statistikkSkademeldingPaSkadetype()
    {
        skadetypevalg = skadetypevelgeren.getItemAt(skadetypevelgeren.getSelectedIndex());
        forsikringsvalg = forsikringsvelgeren.getItemAt(forsikringsvelgeren.getSelectedIndex());
        startDato = new GregorianCalendar((Integer.parseInt(stDatoAr.getText())),
                (Integer.parseInt(stDatoMnd.getText())), Integer.parseInt(stDatoDag.getText()));
        sluttDato = new GregorianCalendar((Integer.parseInt(slDatoAr.getText())),
                (Integer.parseInt(slDatoMnd.getText())), Integer.parseInt(slDatoDag.getText()));
        List<Skademelding> skademeldingsliste = new ArrayList<>();
        double antallIPerioden = 0;
        double antallForAlltid = 0;

        try
        {
            for (Skademelding skademld : register.getSkademeldingsregister().alleSkademeldinger())
            {
                if (skademld.getForsikring().getForsikringsType().equals(forsikringsvalg))
                {
                    if (skademld.getSkadetype().equals(skadetypevalg))
                    {
                        if (skademld.getOpprettetDato().getTime().after(startDato.getTime()) && 
                                skademld.getOpprettetDato().getTime().before(sluttDato.getTime()))
                        {
                            skademeldingsliste.add(skademld);
                            antallIPerioden++;
                        }
                        antallForAlltid++;
                    }
                }
            }

            long periodeIDager = (sluttDato.getTime().getTime() - 
                    startDato.getTime().getTime()) / 1000 / 60 / 60 / 24;
            System.out.println(periodeIDager);
            double gjennomsnittPerioden = antallIPerioden / ((double)periodeIDager/30);
            Calendar programStartDato = register.getForsikringrsliste().getForsikring(1000001).getStartdato();
            GregorianCalendar programSluttDato = new GregorianCalendar();
            long alltidIDager = (programSluttDato.getTime().getTime() -
                    programStartDato.getTime().getTime()) / 1000 / 60 / 60 / 24;
            double gjennomsnittAlltid = antallForAlltid / ((double)alltidIDager/30);
            String s;
            double endring = gjennomsnittPerioden - gjennomsnittAlltid;
            double prosent = ((endring / gjennomsnittAlltid)*100);

            if (endring >= 0)
            {
                s = "økt";
            } 
            else
            {
                s = "minket";
            }

            JTextArea textArea = new JTextArea();
            textArea.setText("Antall skademeldinger for " + skadetypevalg.toLowerCase()
                    + "skader har " + s + " med " + df.format(endring) + " stk / "
                    + df.format(prosent) + "%\nmånedelig i perioden " + 
                    sdf.format(startDato.getTime()) + " - " + sdf.format(sluttDato.getTime()));

            statistikkVindu = new StatistikkVindu("Øking/Minking av skademeldinger"
                    + " på " + skadetypevalg.toLowerCase() + "skader i perioden "
                    + sdf.format(startDato.getTime()) + " - " + sdf.format(sluttDato.getTime()), 
                    textArea, new Dimension(600, 300));

            tømFelter();
        } 
        catch (NullPointerException | ArithmeticException e) 
        {
            feilMelding("Søket ga ingen treff");
        }
    }

    /*Skriver ut økning/minking av erstatningsutgifter innenfor en gitt 
    tidsperiode. Økingen/minkingen blir regnet som antall skademeldinger/antall 
    måneder i perioden minus skademeldinger/antall måneder siden programmets 
    start. Programmets start er satt til når den første forsikringen ble tegnet.
    Hvis det ikke er noen treff, kommer det feilmelding om dette*/
    public void statistikkErstatning()
    {
        startDato = new GregorianCalendar((Integer.parseInt(stDatoAr.getText())),
                (Integer.parseInt(stDatoMnd.getText())), Integer.parseInt(stDatoDag.getText()));
        sluttDato = new GregorianCalendar((Integer.parseInt(slDatoAr.getText())),
                (Integer.parseInt(slDatoMnd.getText())), Integer.parseInt(slDatoDag.getText()));
        double totalSumIPeriode = 0.0;
        double totalSum = 0.0;

        try
        {
            for (Skademelding skademld : register.getSkademeldingsregister().alleSkademeldinger())
            {
                if (skademld.getOpprettetDato().getTime().after(startDato.getTime()) && 
                        skademld.getOpprettetDato().getTime().before(sluttDato.getTime()))
                {
                    totalSumIPeriode += skademld.getErstatningsbelop();
                }
                totalSum += skademld.getErstatningsbelop();
            }

            long periodeIDager = (sluttDato.getTime().getTime() - 
                    startDato.getTime().getTime()) / 1000 / 60 / 60 / 24;
            double gjennomsnittPerioden = totalSumIPeriode / periodeIDager;
            Calendar programStartDato = register.getForsikringrsliste().getForsikring(1000001).getStartdato();
            GregorianCalendar programSluttDato = new GregorianCalendar();
            long alltidIDager = (programSluttDato.getTime().getTime() - 
                    programStartDato.getTime().getTime()) / 1000 / 60 / 60 / 24;
            double gjennomsnittAlltid = totalSum / ((double)alltidIDager/30);
            String s;
            double endring = gjennomsnittPerioden - gjennomsnittAlltid;
            double prosent = ((endring / gjennomsnittAlltid)*100);
            if (endring >= 0)
            {
                s = "økt";
            }
            else
            {
                s = "minket";
            }

            JTextArea textArea = new JTextArea();
            textArea.setText("Total erstatningsutgifter har " + s + " med " + 
                    df.format(endring) + " stk / " + df.format(prosent)
                    + "%\nmånedeligi perioden " + sdf.format(startDato.getTime()) + " - " 
                    + sdf.format(sluttDato.getTime()));

            statistikkVindu = new StatistikkVindu("Øking/Minking av erstatnings"
                    + "utgifter i perioden " + sdf.format(startDato.getTime()) + " - "
                    + sdf.format(sluttDato.getTime()), textArea, new Dimension(600, 300));

            tømFelter();
        }
        catch (NullPointerException | ArithmeticException e)
        {
            feilMelding("Søket ga ingen treff");
        }

    }

    /*Skriver ut økning/minking av de totale erstatningsutgiftene på
    en gitt skadetype innenfor en gitt tidsperiode. Økingen/minkingen blir
    regnet som antall skademeldinger/antall måneder i perioden minus
    skademeldinger/antall måneder siden programmets start. Programmets start er
    satt til når den første forsikringen ble tegnet. Hvis det ikke er noen treff,
    kommer det feilmelding om dette*/
    public void statistikkErstatningPaSkadetype()
    {
        skadetypevalg = skadetypevelgeren.getItemAt(skadetypevelgeren.getSelectedIndex());
        forsikringsvalg = forsikringsvelgeren.getItemAt(forsikringsvelgeren.getSelectedIndex());
        startDato = new GregorianCalendar((Integer.parseInt(stDatoAr.getText())),
                (Integer.parseInt(stDatoMnd.getText())), Integer.parseInt(stDatoDag.getText()));
        sluttDato = new GregorianCalendar((Integer.parseInt(slDatoAr.getText())),
                (Integer.parseInt(slDatoMnd.getText())), Integer.parseInt(slDatoDag.getText()));
        double totalSumIPeriode = 0.0;
        double totalSum = 0.0;
        try
        {
            for (Skademelding skademld : register.getSkademeldingsregister().alleSkademeldinger())
            {
                if (skademld.getForsikring().getForsikringsType().equals(forsikringsvalg))
                {
                    if (skademld.getSkadetype().equals(skadetypevalg))
                    {
                        if (skademld.getOpprettetDato().after(startDato.getTime()) && 
                                skademld.getOpprettetDato().before(sluttDato.getTime()))
                        {
                            totalSumIPeriode += skademld.getErstatningsbelop();
                        }
                        totalSum += skademld.getErstatningsbelop();
                    }
                }
            }

            long periodeIDager = (sluttDato.getTime().getTime() - 
                    startDato.getTime().getTime()) / 1000 / 60 / 60 / 24;
            double gjennomsnittPerioden = totalSumIPeriode / ((double)periodeIDager/30);
            Calendar programStartDato = register.getForsikringrsliste().getForsikring(1000001).getStartdato();
            GregorianCalendar programSluttDato = new GregorianCalendar();
            long alltidIDager = (programSluttDato.getTime().getTime() - 
                    programStartDato.getTime().getTime()) / 1000 / 60 / 60 / 24;
            double gjennomsnittAlltid = totalSum / ((double)alltidIDager/30);
            String s;
            double endring = gjennomsnittPerioden - gjennomsnittAlltid;
            double prosent = ((endring / gjennomsnittAlltid)*100);

            if (endring >= 0)
            {
                s = "økt";
            } 
            else
            {
                s = "minket";
            }

            JTextArea textArea = new JTextArea();
            textArea.setText("Totale erstatningsutgifter for " + skadetypevalg.toLowerCase()
                    + "skader har " + s + " med " + df.format(endring) + " stk / "
                    + df.format(prosent) + "%\nmånedelig i perioden " + 
                    sdf.format(startDato.getTime()) + " - " + sdf.format(sluttDato.getTime()));

            statistikkVindu = new StatistikkVindu("Øking/Minking av erstatnings"
                    + "utgifter for " + skadetypevalg.toLowerCase() + "skader\ni perioden "
                    + sdf.format(startDato.getTime()) + " - " + sdf.format(sluttDato.getTime()),
                    textArea, new Dimension(600, 300));

            tømFelter();
        }
        catch (NullPointerException | ArithmeticException e)
        {
            feilMelding("Søket ga ingen treff");
        }
    }

    /*Skriver ut alle forsikringstypene sortert etter antall. 
    Hvis det ikke er noen treff, kommer det feilmelding om dette*/
    public void typeForsikringPaAntall()
    {
        int bilForsikring = 0;
        int båtForsikring = 0;
        int reiseForsikring = 0;
        int husForsikring = 0;
        int fritidsboligForsikring = 0;

        for (Forsikring forsikring : register.getForsikringrsliste().alleForsikringer())
        {
           
            if (forsikring instanceof Bilforsikring)
            {
                bilForsikring++;
            } else if (forsikring instanceof BatForsikring)
            {
                båtForsikring++;
            } else if (forsikring instanceof Reiseforsikring)
            {
                reiseForsikring++;
            } else if (forsikring instanceof Husforsikring)
            {
                husForsikring++;
            } else if (forsikring instanceof Fritidsboligforsikring)
            {
                fritidsboligForsikring++;
            }
        }

        List<Integer> liste = new ArrayList<>();
        liste.add(bilForsikring);
        liste.add(båtForsikring);
        liste.add(husForsikring);
        liste.add(fritidsboligForsikring);
        liste.add(reiseForsikring);
        Collections.sort(liste);
        Collections.reverse(liste);
        String ut = "";

        for (int i : liste) {
            if (i == bilForsikring)
            {
                ut += "\nAntall bilforsikringer: " + bilForsikring;
                bilForsikring = -1;
            } 
            else if (i == båtForsikring)
            {
                ut += "\nAntall båtforsikringer: " + båtForsikring;
                båtForsikring = -1;
            } 
            else if (i == husForsikring)
            {
                ut += "\nAntall hus- og innboforsikringer: " + husForsikring;
                husForsikring = -1;
            } 
            else if (i == fritidsboligForsikring)
            {
                ut += "\nAntall fritidsboligforsikringer: " + fritidsboligForsikring;
                fritidsboligForsikring = -1;
            } 
            else if (i == reiseForsikring)
            {
                ut += "\nAntall reiseforsikringer: " + reiseForsikring;
                reiseForsikring = -1;
            }
        }

        JTextArea textArea = new JTextArea();
        textArea.setText(ut);
        statistikkVindu = new StatistikkVindu("Forsikringer sortert på antall",
                new JScrollPane(textArea), new Dimension(600, 300));
        tømFelter();
    }

    public void feilMelding(String t)
    {
        JOptionPane.showMessageDialog(null, t, "Feilmelding", JOptionPane.ERROR_MESSAGE);
    }

    public boolean sjekkDato()
    {
        if (!stDatoAr.getText().matches("([1][9][0-9][0-9])|([2][0-9][0-9][0-9])") || 
                !stDatoMnd.getText().matches("0[1-9]|1[0-2]")
                || !stDatoDag.getText().matches("0[1-9]|[12]\\d|3[01]") || 
                !slDatoAr.getText().matches("([1][9][0-9][0-9])|([2][0-9][0-9][0-9])")
                || !slDatoMnd.getText().matches("0[1-9]|1[0-2]") || 
                !slDatoDag.getText().matches("0[1-9]|[12]\\d|3[01]"))
        {
            feilMelding("Fyll ut alle feltene for dato i riktig format");
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean sjekkDatoOgForsikringsvelger() 
    {
        if (!stDatoAr.getText().matches("([1][9][0-9][0-9])|([2][0-9][0-9][0-9])") || 
                !stDatoMnd.getText().matches("0[1-9]|1[0-2]")
                || !stDatoDag.getText().matches("0[1-9]|[12]\\d|3[01]") || 
                !slDatoAr.getText().matches("([1][9][0-9][0-9])|([2][0-9][0-9][0-9])")
                || !slDatoMnd.getText().matches("0[1-9]|1[0-2]") || 
                !slDatoDag.getText().matches("0[1-9]|[12]\\d|3[01]")
                || forsikringsvelgeren.getSelectedIndex() == 0)
        {

            if (!stDatoAr.getText().matches("([1][9][0-9][0-9])|([2][0-9][0-9][0-9])") || 
                !stDatoMnd.getText().matches("0[1-9]|1[0-2]")
                || !stDatoDag.getText().matches("0[1-9]|[12]\\d|3[01]") || 
                !slDatoAr.getText().matches("([1][9][0-9][0-9])|([2][0-9][0-9][0-9])")
                || !slDatoMnd.getText().matches("0[1-9]|1[0-2]") || 
                !slDatoDag.getText().matches("0[1-9]|[12]\\d|3[01]"))
            {
                feilMelding("Fyll ut alle feltene for dato i riktig format");
            }

            if (forsikringsvelgeren.getSelectedIndex() == 0) 
            {
                feilMelding("Du må velge forsikringstype");
            }
            return false;
        } 
        else 
        {
            return true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == sokKnapp) 
        {
            if (sokevelger.isEnabled()) 
            {
                switch (sokevelger.getSelectedIndex()) 
                {
                    case 1:
                        if (forsikringsvelgeren.getSelectedIndex() != 0) 
                        {
                            alleKunderMedForsikring();
                        } 
                        else 
                        {
                            feilMelding("Du må velge forsikringstype");
                        }

                        break;
                    case 2:
                        if (sjekkDato())
                        {
                            antSkademeldinger();
                        }
                        break;
                    case 3:
                        antForsikringer();
                        break;
                }
            } 
            else if (utgiftsvelger.isEnabled()) 
            {
                switch (utgiftsvelger.getSelectedIndex()) 
                {
                    case 1:
                        if (sjekkDato()) 
                        {
                            totalErstatning();
                        }
                        break;
                    case 2:
                        if (sjekkDatoOgForsikringsvelger()) 
                        {
                            totalErstatningPaForsikring();
                        }

                        break;
                }
            } 
            else if (inntektsvelger.isEnabled()) 
            {
                switch (inntektsvelger.getSelectedIndex()) 
                {
                    case 1:
                        if (sjekkDato()) 
                        {
                            totalPremieinntekt();
                        }
                        break;
                    case 2:
                        if (sjekkDatoOgForsikringsvelger()) 
                        {
                            totalPremieinntektPaForsikringstype();
                        }
                        break;
                }
            } 
            else if (statistikkvelger.isEnabled()) 
            {
                switch (statistikkvelger.getSelectedIndex()) 
                {
                    case 1:
                        if (sjekkDato()) 
                        {
                            statistikkSkademeldinger();
                        }
                        break;
                    case 2:
                        if (sjekkDatoOgForsikringsvelger()) 
                        {
                            statistikkSkademeldingPaForsikring();
                        }
                        break;
                    case 3:
                        statistikkSkademeldingPaSkadetype();
                        break;
                    case 4:
                        if (sjekkDatoOgForsikringsvelger()) 
                        {
                            statistikkErstatning();
                        }
                        break;
                    case 5:
                        statistikkErstatningPaSkadetype();
                        break;
                    case 6:
                        typeForsikringPaAntall();
                        break;
                }
            }
        }
    }
}
