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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import objekter.*;
import register.*;

/**
 *
 * @author Marthejansonskogen
 */
public class SkademeldingPanel extends JPanel implements ActionListener
{
    private final AnsattVindu vindu;
    private final HovedRegister register;
    private KundePanel kundePanel;
    private final JTextField skadeDato;
    private final JTextArea skadeBeskrivelse;
    private final JTextField skadeTakst;
    private final JTextField skadeForsikring;
    private final JTextField erstatningsBeløp;
    private final JButton sendInnSkade;
    private final JButton lastOppBildeKnapp;
    private final JButton vitneKnapp;
    private final JButton visBilde;
    String[] skadetype = {"", "Brann", "Tyveri/Hærverk", "Ulykke", "Tap", "Annet"};
    String[] skadetypeKjoretoy = {"", "Ansvar", "Glasskade", "Vei-/slepehjelp", "Tyveri/Hærverk", "Ulykke", "Annet"};
    String[] skadetypeEiendom = {"", "Brann", "Innbrudd/tyveri", "Hærverk", "Naturskade", "Vann", "Fryser/matvarer", "Annet"};
    String[] skadetypeReise = {"", "Tapt/forsinket bagasje", "Tyveri/tap", "Forsinket transport", "Sykdom/ulykke", "Avbestilling", "Annet"};
    JComboBox<String> skadetypevelger;
    private final Kunde kunde;
    private final Forsikring forsikring;
    private Image[] bilder;
    private SimpleDateFormat sdf;
    private Skademelding skademelding;
    private String skadetypevalget;
    
    private final Desktop desktop = Desktop.getDesktop();
    private final Desktop.Action action = Desktop.Action.OPEN;

    
    public SkademeldingPanel( Forsikring f, AnsattVindu v)
    {
        sdf = new SimpleDateFormat("ddMMyyyy");
        kunde = f.getKunde();
        forsikring = f;
        vindu = v;
        register = vindu.getRegister();
        skadeDato = new JTextField( 7 );
        skadeBeskrivelse = new JTextArea( 20, 30);
        skadeTakst = new JTextField( 7 );
        sendInnSkade = new JButton("Send inn skade");  
        lastOppBildeKnapp = new JButton("Last Opp Bilde");
        skadeForsikring = new JTextField(16);
        vitneKnapp = new JButton("Legg Til Vitner");
        erstatningsBeløp = new JTextField(15);
        visBilde = new JButton("Vis bilder");
        
        if (forsikring.getForsikringsType().equals("Bilforsikring") || forsikring.getForsikringsType().equals("Båtforsikring"))
        {
            skadetypevelger = new JComboBox<>(skadetypeKjoretoy);
        }
        else if (forsikring.getForsikringsType().equals("Husforsikring") || forsikring.getForsikringsType().equals("Fritidsboligforsikring"))
        {
            skadetypevelger = new JComboBox<>(skadetypeEiendom);
        }
        else if (forsikring.getForsikringsType().equals("Reiseforsikring"))
        {
           skadetypevelger = new JComboBox<>(skadetypeReise); 
        }
        
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
        wrapper_2.add( new JLabel("Beskrivelse av Skaden: "), BorderLayout.PAGE_START);
        skadeBeskrivelse.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(skadeBeskrivelse);
        wrapper_2.add( scroll, BorderLayout.CENTER );
        
        JPanel wrapper_3 = new JPanel();
        wrapper_3.setLayout( new FlowLayout() );
        wrapper_3.add( new JLabel("Erstatnings beløp: "));
        wrapper_3.add( erstatningsBeløp );
        wrapper_3.add(sendInnSkade);
        wrapper_3.add(lastOppBildeKnapp);
        wrapper_3.add(visBilde);
        wrapper_3.add(vitneKnapp);
        
        
        this.setLayout( new BorderLayout());
        add(wrapper_1, BorderLayout.PAGE_START);
        add(wrapper_2, BorderLayout.CENTER);
        add(wrapper_3, BorderLayout.PAGE_END);
        
        
        skadeForsikring.setText(forsikring.getForsikringsType() + " " + forsikring.getForsikringsnummer());
        lastOppBildeKnapp.addActionListener(this);
        sendInnSkade.addActionListener(this);
        visBilde.addActionListener(this);
    }
    
    public void visSkademelding( Skademelding skade )
    {
        this.skademelding = skade;
        skadeDato.setText(skademelding.getSkadeDato().toString());
        skadeForsikring.setText(skademelding.getForsikring().getForsikringsType());
        skadetypevelger.setSelectedItem(forsikring.getVilkar());
        skadeTakst.setText(String.valueOf(skademelding.getTakseringsbelop()));
        skadeBeskrivelse.setText( skademelding.getBeskrivelse());
        
    }
    
    public void beregnPris()
    {
        //Beregner pris
        //belop =;
    }
    
    public void nySkademelding()
    {
        int skadetype_n = skadetypevelger.getSelectedIndex();
        try
        {
            String sd = skadeDato.getText();
            Date dato = sdf.parse(sd);
            String beskrivelse = skadeBeskrivelse.getText();
            int takst = Integer.parseInt(skadeTakst.getText());
            int belop = Integer.parseInt(erstatningsBeløp.getText());
            skadetypevalget = skadetypevelger.getItemAt(skadetype_n);
            
            Skademelding nySkademelding = new Skademelding(forsikring, dato, skadetypevalget, beskrivelse, takst, belop );
            if( bilder != null)
                skademelding.setBilder(bilder);
            
            vindu.getRegister().getSkademeldingsregister().leggTil(forsikring, skademelding);
        } 
        catch (ParseException e)
        {
            JOptionPane.showMessageDialog(null, "Vennligst skriv inn datoen i følgende format: ddmmåååå.", "Feilmelding", JOptionPane.ERROR_MESSAGE);
	}
        catch( NumberFormatException e)
        {
            vindu.visFeilmelding("FeildMelding", "Skjekk at takst og beløp er av riktig format.");
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
            
        }
    }
}
