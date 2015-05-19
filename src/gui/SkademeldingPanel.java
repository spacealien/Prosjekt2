/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import objekter.*;
import register.*;

/**
 *
 * @author Odd, Marthe
 */
public class SkademeldingPanel extends JPanel implements ActionListener, ForsikringsPanel
{
    private final AnsattVindu vindu;
    private final HovedRegister register;
    private KundePanel kundePanel;
    private Vitne vitne;
    private final JTextField skadeDato;
    private final JTextArea skadeBeskrivelse;
    private final JTextField skadeTakst;
    private final JTextField skadeForsikring;
    private final JTextField erstatningsBeløp;
    private final JLabel erstatningsLabel;
    private final JButton sendInnSkade;
    private final JButton lastOppBildeKnapp;
    private final JButton vitneKnapp;
    private final JButton visBilde;
    private final JButton beregnErstatning;
    private final String[] skadetype = {"", "Brann", "Tyveri/Hærverk", "Ulykke", "Tap", "Annet"};
    private final String[] skadetypeKjoretoy = {"", "Ansvar", "Glasskade", "Vei-/slepehjelp", "Tyveri/Hærverk", "Ulykke", "Annet"};
    private final String[] skadetypeEiendom = {"", "Brann", "Innbrudd/tyveri", "Hærverk", "Naturskade", "Vann", "Fryser/matvarer", "Annet"};
    private final String[] skadetypeReise = {"", "Tapt/forsinket bagasje", "Tyveri/tap", "Forsinket transport", "Sykdom/ulykke", "Avbestilling", "Annet"};
    private final JComboBox<String> skadetypevelger;
    private final Kunde kunde;
    private final Forsikring forsikring;
    private Image[] bilder;
    private SimpleDateFormat sdf;
    private Skademelding skademelding;
    private String skadetypevalget;
    
    private final JTextField vitneFornavn;
    private final JTextField vitneEtternavn;
    private final JTextField vitneTlf;
    private final JTextField vitneAdresse;
    private final JPanel vitnePanel;
    List<Vitne> vitneliste;
    
    private final Desktop desktop = Desktop.getDesktop();
    private final Desktop.Action action = Desktop.Action.OPEN;

    
    public SkademeldingPanel( Forsikring f, AnsattVindu v)
    {
        sdf = new SimpleDateFormat("ddMMyyyy");
        kunde = f.getKunde();
        forsikring = f;
        vindu = v;
        register = vindu.getRegister();
        vitneliste = new ArrayList<>();
        skadeDato = new JTextField( 7 );
        skadeBeskrivelse = new JTextArea( 20, 30);
        skadeTakst = new JTextField( 7 );
        sendInnSkade = new JButton("Send inn skade");  
        sendInnSkade.setVisible(false);
        lastOppBildeKnapp = new JButton("Last opp bilde");
        skadeForsikring = new JTextField(16);
        skadeForsikring.setEditable(false);
        vitneKnapp = new JButton("Legg til vitner");
        erstatningsBeløp = new JTextField(15);
        erstatningsLabel = new JLabel("Foreslått erstatningsbeløp:");
        erstatningsBeløp.setVisible(false);
        erstatningsLabel.setVisible(false);
        visBilde = new JButton("Vis bilder");
        beregnErstatning = new JButton("Beregn erstatningsbeløp");
        
        if (forsikring.getForsikringsType().equals("Bilforsikring") || forsikring.getForsikringsType().equals("Båtforsikring"))
        {
            skadetypevelger = new JComboBox<>(skadetypeKjoretoy);
        }
        else if (forsikring.getForsikringsType().equals("Husforsikring") || forsikring.getForsikringsType().equals("Fritidsboligforsikring"))
        {
            skadetypevelger = new JComboBox<>(skadetypeEiendom);
        }
        else
        {
           skadetypevelger = new JComboBox<>(skadetypeReise); 
        }
        
        vitneFornavn = new JTextField(20);
        vitneEtternavn = new JTextField(20);
        vitneTlf = new JTextField(8);
        vitneAdresse = new JTextField(15);
        
        vitnePanel = new JPanel();
        vitnePanel.setLayout(new GridLayout(4,2,1,1));
        vitnePanel.add(new JLabel("Fornavn: "));
        vitnePanel.add(vitneFornavn);
        vitnePanel.add(new JLabel("Etternavn: "));
        vitnePanel.add(vitneEtternavn);
        vitnePanel.add(new JLabel("Telefonnummer: "));
        vitnePanel.add(vitneTlf);
        vitnePanel.add(new JLabel("Adresse: "));
        vitnePanel.add(vitneAdresse);
        
        
        JPanel wrapper_1 = new JPanel();
        GridLayout layout_1 = new GridLayout(2,4);
        layout_1.setHgap(6);
        layout_1.setVgap(6);
        wrapper_1.setLayout(  layout_1 );        
        wrapper_1.add( new JLabel("Dato skaden inntraff: (ddmmåååå)"));
        wrapper_1.add( skadeDato );
        wrapper_1.add( new JLabel("Skadens forsikring: "));
        wrapper_1.add( skadeForsikring );
        wrapper_1.add( new JLabel("Skadetype: "));
        wrapper_1.add( skadetypevelger );
        wrapper_1.add( new JLabel("Skadens takst: "));
        wrapper_1.add( skadeTakst );
        
        JPanel wrapper_2 = new JPanel();
        wrapper_2.setLayout( new BorderLayout());
        wrapper_2.add( new JLabel("Beskrivelse av skaden: "), BorderLayout.PAGE_START);
        skadeBeskrivelse.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(skadeBeskrivelse);
        scroll.setPreferredSize(new Dimension(70,150));
        wrapper_2.add( scroll, BorderLayout.CENTER );
        
        JPanel wrapper_3 = new JPanel();
        wrapper_3.setLayout( new FlowLayout() );
        wrapper_3.add( erstatningsLabel);
        wrapper_3.add( erstatningsBeløp );
        wrapper_3.add(beregnErstatning);
        wrapper_3.add(lastOppBildeKnapp);
        wrapper_3.add(visBilde);
        wrapper_3.add(vitneKnapp);
        wrapper_3.add(sendInnSkade);
        
        
        this.setLayout( new BorderLayout());
        add(wrapper_1, BorderLayout.PAGE_START);
        add(wrapper_2, BorderLayout.CENTER);
        add(wrapper_3, BorderLayout.PAGE_END);
        
        
        skadeForsikring.setText(forsikring.getForsikringsType() + " " + forsikring.getForsikringsnummer());
        lastOppBildeKnapp.addActionListener(this);
        beregnErstatning.addActionListener(this);
        sendInnSkade.addActionListener(this);
        visBilde.addActionListener(this);
        vitneKnapp.addActionListener(this);
    }
    
    public void visSkademelding( Skademelding skade )
    {
        this.skademelding = skade;
        skadeDato.setText(sdf.format(skademelding.getSkadeDato().getTime()));
        skadeForsikring.setText(skademelding.getForsikring().getForsikringsType());
        skadetypevelger.setSelectedItem(skademelding.getSkadetype());
        skadeTakst.setText(String.valueOf(skademelding.getTakseringsbelop()));
        skadeBeskrivelse.setText( skademelding.getBeskrivelse());
        vitneKnapp.setText("Vis vitner");
        erstatningsLabel.setText("Utbetalt erstatning:");
        erstatningsBeløp.setText(String.valueOf(skademelding.getErstatningsbelop()));
        erstatningsLabel.setVisible(true);
        erstatningsBeløp.setVisible(true);
        disableFelter(this, lastOppBildeKnapp, beregnErstatning);
        sendInnSkade.setVisible(false);
        
    }
    
    public void beregnPris()
    {
        try
        {
            erstatningsBeløp.setText(String.valueOf(ForsikringsKalulator.beregnErstatningsbelop(forsikring.getEgenandel(), Integer.parseInt(skadeTakst.getText()))));
            erstatningsLabel.setVisible(true);
            erstatningsBeløp.setVisible(true);
            erstatningsBeløp.setToolTipText("Kan redigeres");
            sendInnSkade.setVisible(true);
        }
        catch (NumberFormatException e)
        {
            vindu.visInformasjon("Feilmelding", "Skriv inn skadetakst i riktig format: ####");
        }
    }
    
    public void nySkademelding()
    {
        int skadetype_n = skadetypevelger.getSelectedIndex();
        try
        {
            String sd = skadeDato.getText();
            GregorianCalendar dato = new GregorianCalendar(Integer.parseInt(sd.substring(4, 8)), Integer.parseInt(sd.substring(2,4)), Integer.parseInt(sd.substring(0, 2)));
            String beskrivelse = skadeBeskrivelse.getText();
            int takst = Integer.parseInt(skadeTakst.getText());
            double belop = Double.parseDouble(erstatningsBeløp.getText());
            skadetypevalget = skadetypevelger.getItemAt(skadetype_n);
            
            Skademelding nySkademelding = new Skademelding(forsikring, dato, skadetypevalget, beskrivelse, takst, belop );
            if (!vitneliste.isEmpty())
            {
                nySkademelding.setVitner(vitneliste);
            }
            if( bilder != null)
                nySkademelding.setBilder(bilder);

            if(vindu.getRegister().nySkademelding(nySkademelding));
                vindu.visInformasjon("Beskjed", "Skademelding registrert.");
            
            if(kundePanel != null )
                kundePanel.oppdaterVindu();
            
            visSkademelding(nySkademelding);

        }
        catch( NumberFormatException e)
        {
            vindu.visFeilmelding("Feildmelding", "Skjekk at dato, takst og beløp er av riktig format.");
        }
    }
    
    public void setKundePanel( KundePanel panel )
    {
        kundePanel = panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == sendInnSkade)
        {
            nySkademelding();
        }
        else if( e.getSource() == beregnErstatning )
        {
            beregnPris();
        }
        else if( e.getSource() == lastOppBildeKnapp)
        {
            JFileChooser filer = new JFileChooser();
            filer.setMultiSelectionEnabled(true);
            filer.setCurrentDirectory( new File (".") );
            if( JFileChooser.APPROVE_OPTION == filer.showOpenDialog( vindu ))
            {
                try
                {
                    File[] foto = filer.getSelectedFiles();
                    int teller = 0;
                    for( File fil : foto)
                    {
                        this.bilder[teller++] = ImageIO.read(foto[teller++]);
                    }
                } catch (IOException ex) 
                {
                    Logger.getLogger(SkademeldingPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else if( e.getSource() == visBilde )
        {
            //BildeVindu bildeVindu = new BildeVindu( bilder , "Skadenummer: " );
        }
        else if( e.getSource() == vitneKnapp )
        {
            if (vitneKnapp.getText().equals("Legg til vitner"))
            {
                Object[] valg = {"Lagre vitne og legg til et nytt vitne", "Lagre vitne", "Avbryt"};
                int result;
                do
                {
                    result = JOptionPane.showOptionDialog(null, vitnePanel, 
                             "Vennligst fyll ut vitnes kontaktinformasjon:",
                                        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, valg, valg [0]);
                    if (result == 1)
                    {
                        if(vitneFornavn.getText().matches("\\D*") &&
                                vitneEtternavn.getText().matches("\\D*") && vitneTlf.getText().matches("\\d{8}"))
                        {
                            Vitne vitnet = new Vitne(vitneFornavn.getText(), vitneEtternavn.getText(), 
                            vitneAdresse.getText(), vitneTlf.getText());
                            vitneliste.add(vitnet);
                            vindu.visInformasjon("Vitne registrert", vitnet.getFornavn() 
                              + " " + vitnet.getEtternavn() + " er nå registrert som vitne.");
                        }
                        else
                        {
                            vindu.visFeilmelding("Feilmelding", 
                                    "Ugyldig format i ett eller flere av feltene.\n\nPrøv igjen");
                            result = 0;
                        }
                    }
                    else if (result == 0)
                    {
                        if(vitneFornavn.getText().matches("\\D*") && 
                                vitneEtternavn.getText().matches("\\D*") && vitneTlf.getText().matches("\\d{8}"))
                        {
                            Vitne vitnet = new Vitne(vitneFornavn.getText(), vitneEtternavn.getText(), 
                            vitneAdresse.getText(), vitneTlf.getText());
                            vitneliste.add(vitnet);
                        }
                        else
                            vindu.visFeilmelding("Feilmelding", "Ugyldig format "
                                    + "i ett eller flere av feltene.\n\nPrøv igjen");
                    }
                }while (result == 0);
            }
            else if (vitneKnapp.getText().equals("Vis vitner"))
            {
                String[] kolonnenavn = {"Fornavn", "Etternavn", "Adresse", "Telefonnummer"};
                Object[][] innhold;
                List<Vitne> vitnelisten = skademelding.getVitner();
                if(vitnelisten != null)
                {
                    innhold = new Object[vitnelisten.size()][kolonnenavn.length];
        
                    int teller = 0;
                    for(Vitne vitnet : vitnelisten)
                    {
                        innhold[teller][0] = vitnet.getFornavn();
                        innhold[teller][1] = vitnet.getEtternavn();
                        innhold[teller][2] = vitnet.getAdresse();
                        innhold[teller][3] = vitnet.getTlfnr();
                        teller++;
                    }
                    JTable tabell = new JTable(innhold, kolonnenavn);
                    tabell.setPreferredScrollableViewportSize(new Dimension(300, 200));
                    JOptionPane.showMessageDialog( null, new JScrollPane(tabell), 
                      "Vitner:", JOptionPane.PLAIN_MESSAGE);
                }
                else
                    vindu.visInformasjon("Ingen vitner", "Det er ikke registrert noen vitner for denne skaden");
            }
        }
    }
}
