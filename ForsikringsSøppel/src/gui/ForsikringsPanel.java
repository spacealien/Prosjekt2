/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import objekter.Bilforsikring;
import objekter.Forsikring;
import objekter.Kunde;

/**
 *
 * @author Odd
 */
public class ForsikringsPanel extends JPanel implements ActionListener
{
    private final JPanel forsikringsPanel = new JPanel();
    private final String[] forsikringsvalg = {"", "Bilforsikring", "Båtforsikring", "Husforsikring", "Fritidsboligforsikring", "Reiseforsikring"};
    private final JComboBox<String> forsikringsDropDown;
    
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
    String[] biltype = {"", "Småbil", "Lastebil", "Vogntog", "Varebil", "SUV"};
    JComboBox<String> biltypevelger;

    String[] bilmerke = {"", "Mercedes", "Toyota", "BMW", "Volkswagen", "Ford",
                        "Audi", "Opel", "Citroën", "Alfa Romeo", "Porsche",
                        "Lamborghini"};
    JComboBox<String> bilmerkevelger;
    
    String[] kjorelengde = {"8000", "12000", "16000", "20000", "25000", "30000", "Ubegrenset"};
    JComboBox<String> kjorelengdevelger;
    
    private final JTextField batRegnr;
    private final JTextField batModell;
    private final JTextField batTilbud;
    private final JTextField batMerke;
    private final JTextField batLengde;
    private final JTextField batArsmodell;
    private final String[] battype = {"Skjærgårdsjeep", "Cabin cruiser", "Rib", "Annen småbåt",
                        "Speedbåt", "Seilbåt", "Snekke"};
    JComboBox<String> battypevelger;
    private final JButton batGiTilbud;
    
    
    public ForsikringsPanel()
    {
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
        biltypevelger.setSelectedIndex(4);
        bilmerkevelger = new JComboBox<>(bilmerke);
        bilmerkevelger.setSelectedIndex(10);
        kjorelengdevelger = new JComboBox<>(kjorelengde);
        kjorelengdevelger.setSelectedIndex(3);
        
        batRegnr = new JTextField( 7 );
        batModell = new JTextField( 7 );
        batTilbud = new JTextField( 7 );
        batMerke = new JTextField( 7 );
        batLengde = new JTextField( 7 );
        batArsmodell = new JTextField( 7 );
        battypevelger = new JComboBox<>(battype);
        batGiTilbud = new JButton("Tegn forsikring");
        
        
        forsikringsDropDown = new JComboBox<>(forsikringsvalg);
        
        // putter comboboksen øverst på forsikrings vinduet.
        setLayout( new BorderLayout() );
        JPanel wrapper_1 = new JPanel( new FlowLayout() );
        wrapper_1.add( new JLabel("Forsikringstype: "));
        wrapper_1.add( forsikringsDropDown );
        add(wrapper_1, BorderLayout.NORTH);
        
        forsikringsDropDown.addActionListener(this);
    }
    
    public void visBilforsikringPanel()
    {
        forsikringsPanel.removeAll();
        JPanel garasjen = new JPanel();
        JPanel tegnBilPanel1 = new JPanel();
        tegnBilPanel1.setLayout(new GridLayout(6,4,2,10));
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
        tegnBilPanel1.add(new JLabel("Velg bilmerke: "));
        tegnBilPanel1.add(bilmerkevelger);
        tegnBilPanel1.add(new JLabel("Garasje: "));
        tegnBilPanel1.add(garasjen);
        tegnBilPanel1.add(new JLabel("Årlig forventet kjørelengde: "));
        tegnBilPanel1.add(kjorelengdevelger);
        tegnBilPanel1.add(new JLabel("Er eier annen enn kunde?"));
        tegnBilPanel1.add(annenEier);
        tegnBilPanel1.add(new JLabel());
        tegnBilPanel1.add(new JLabel("Foreslått tilbud: "));
        tegnBilPanel1.add(bilTilbud);
        tegnBilPanel1.add(bilGiTilbud);
        forsikringsPanel.add(tegnBilPanel1);
        add(forsikringsPanel);
        revalidate();
        repaint();
    }
    
    public void visBåtforsikringPanel()
    {
        forsikringsPanel.removeAll();
        forsikringsPanel.setLayout(new GridLayout(6,4,2,10));
        forsikringsPanel.add(new JLabel("Reg.nummer: "));
        forsikringsPanel.add(batRegnr);
        forsikringsPanel.add(new JLabel("Merke: "));
        forsikringsPanel.add(batMerke);
        forsikringsPanel.add(new JLabel("Modell: "));
        forsikringsPanel.add(batModell);
        forsikringsPanel.add(new JLabel("Lengde: "));
        forsikringsPanel.add(batLengde);
        forsikringsPanel.add(new JLabel("Årsmodell: "));
        forsikringsPanel.add(batArsmodell);
        forsikringsPanel.add(new JLabel("Type båt: "));
        forsikringsPanel.add(battypevelger);
        forsikringsPanel.add(new JLabel());
        forsikringsPanel.add(new JLabel());
        forsikringsPanel.add(new JLabel());
        forsikringsPanel.add(new JLabel());
        forsikringsPanel.add(new JLabel());
        forsikringsPanel.add(new JLabel("Foreslått tilbud: "));
        forsikringsPanel.add(batTilbud);
        forsikringsPanel.add(batGiTilbud);
        add(forsikringsPanel);
        revalidate();
        repaint();
    }
    
    /**
    public Forsikring nyForsikring( Kunde kjøper )
    {
        
        String valg = (String) forsikringsDropDown.getSelectedItem();
        Bilforsikring nyForsikring = null;
        Kunde kunde = kjøper;
        if( valg.equals("Bilforsikring"))
        {
            String registreringsnummer = bilRegnr.getText();
            String modell = bilModell.getText();
            String km = bilKmstand.getText();
            String hestekrefter = bilHk.getText();
            bilTilbud.getText();
            
            
            
            
            
            nyForsikring = new Bilforsikring( k, vilkår, registreringsnummer,
                                                     fabrikant,modell, hestekrefter, arsmodell,
                                                     kilometerstand, bonus, garasje, km );
        }
        return nyForsikring;
    }
    * */

    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if( e.getSource() == forsikringsDropDown )
        {
            JComboBox dropDown = (JComboBox)e.getSource();
            String valg = (String) dropDown.getSelectedItem();
            if( valg.equals(""))
            {
                this.remove(forsikringsPanel);
                revalidate();
                repaint();
            }
            else if( valg.equals("Bilforsikring") )
                visBilforsikringPanel();
            else if(valg.equals("Båtforsikring"))
                visBåtforsikringPanel();
        }       
    }
}
