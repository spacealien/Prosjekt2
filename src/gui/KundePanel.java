/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import objekter.Forsikring;
import objekter.Kunde;
import objekter.Skademelding;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class KundePanel extends JPanel implements ActionListener, ForsikringsPanel
{
    private final JPanel kundeInfo_1;
    private final JPanel knappeWrapper;
    private final JPanel forsikringsVelger;
    private final JPanel bunnWrapper;
    private final JTextField regFornavn;
    private final JTextField regEtternavn;
    private final JTextField regPersnr;
    private final JTextField regTlfnr;
    private final JTextField regAdresse;
    private final JTextField regEpost;
    private final JTextField utgifter;
    private final JTextField inntekter;
    private final JTextField inntekterTotal;
    private final JButton regKunde;
    private final JButton kontaktKunde;
    private final AnsattVindu vindu;
    private final JButton visForsikringer = new JButton("Vis Alle Forsikringer");
    private final JButton visSkademeldinger = new JButton("Vis Alle Skademeldinger");
    private final JButton nyForsikring = new JButton("Ny forsikring");
    private final JButton rediger = new JButton("Rediger");
    private Kunde kunde = null;
    private final String[] forsikringsvalg = {"", "Bilforsikring", "Båtforsikring", "Husforsikring", "Fritidsboligforsikring", "Reiseforsikring"};
    private final JComboBox<String> forsikringsDropDown;
    
    private final KundeDataTabell tabell;
    private AbstractTableModel tabellModell;
    
    private final Desktop desktop = Desktop.getDesktop();
    private final Desktop.Action action = Desktop.Action.OPEN;
    
    public KundePanel( AnsattVindu vindu, Kunde kunde )
    {
        this.vindu = vindu;
        this.kunde = kunde;
        kundeInfo_1 = new JPanel();
        knappeWrapper = new JPanel();
        forsikringsVelger = new JPanel();
        bunnWrapper = new JPanel();
        
        regFornavn = new JTextField( 15 );
        regEtternavn = new JTextField( 15 );
        regPersnr = new JTextField( 11 );
        regTlfnr = new JTextField( 8 );
        regAdresse = new JTextField( 15 );
        regEpost = new JTextField(20);
        utgifter = new JTextField(20);
        inntekter = new JTextField(20);
        inntekterTotal = new JTextField(20);
        
        regKunde = new JButton("Registrer kunde" );
        kontaktKunde = new JButton("Kontakt");
        forsikringsDropDown = new JComboBox<>(forsikringsvalg);
        kundeInfo_1.setLayout(new GridLayout(5,4,5,10));
        kundeInfo_1.add(new JLabel("Fornavn: "));
        kundeInfo_1.add(regFornavn);
        kundeInfo_1.add(new JLabel("Etternavn: "));
        kundeInfo_1.add(regEtternavn);
        kundeInfo_1.add(new JLabel("Personnummer: "));
        kundeInfo_1.add(regPersnr);
        kundeInfo_1.add(new JLabel("Telefonnummer: "));
        kundeInfo_1.add(regTlfnr);
        kundeInfo_1.add(new JLabel("Fakturaadresse: "));
        kundeInfo_1.add(regAdresse);
        kundeInfo_1.add( new JLabel("Epost: "));
        kundeInfo_1.add(regEpost);
        kundeInfo_1.add( new JLabel("Total Utbetalt "));
        kundeInfo_1.add( utgifter );
        kundeInfo_1.add( new JLabel("Inntjening pr år: "));
        kundeInfo_1.add( inntekter );
        kundeInfo_1.add( Box.createGlue() );
        kundeInfo_1.add( Box.createGlue() );
        kundeInfo_1.add( new JLabel("Total innbetaling: "));
        kundeInfo_1.add( inntekterTotal );
        
        
        knappeWrapper.setLayout( new FlowLayout() );
        knappeWrapper.add(visForsikringer);
        knappeWrapper.add(visSkademeldinger);
        knappeWrapper.add(kontaktKunde);
        knappeWrapper.add(rediger);

        JPanel infobox = new JPanel();
        infobox.setLayout( new BorderLayout() );
        infobox.add( kundeInfo_1, BorderLayout.PAGE_START );
        infobox.add( knappeWrapper, BorderLayout.PAGE_END);
        
        forsikringsVelger.setLayout( new FlowLayout() );
        forsikringsVelger.add( new JLabel("Velg Forsikringstype"));
        forsikringsVelger.add(forsikringsDropDown);
        forsikringsVelger.add(nyForsikring);
        
        tabellModell = new TabellModellForsikring( vindu.getRegister().getForsikringrsliste().getKundensForsikringer(kunde));
        tabell = new KundeDataTabell(tabellModell,this);
        tabell.setPreferredScrollableViewportSize(new Dimension(500,50));
        JScrollPane scrollTabell = new JScrollPane(tabell);
        bunnWrapper.setLayout( new BorderLayout() );
        bunnWrapper.add( tabell.getTableHeader(), BorderLayout.PAGE_START);
        bunnWrapper.add( scrollTabell, BorderLayout.CENTER);

        setLayout( new BorderLayout()  );
        add( infobox, BorderLayout.NORTH );
        add( bunnWrapper, BorderLayout.CENTER);
        add( forsikringsVelger, BorderLayout.SOUTH);        
        
        kontaktKunde.addActionListener(this);
        regKunde.addActionListener(this);
        visForsikringer.addActionListener(this);
        visSkademeldinger.addActionListener(this);
        rediger.addActionListener(this);
        nyForsikring.addActionListener(this);
        
        oppdaterVindu();
        disableFelter(kundeInfo_1, null, null);
    }
    
    public void oppdaterVindu()
    {
        regFornavn.setText(kunde.getFornavn());
        regEtternavn.setText(kunde.getEtternavn());
        regPersnr.setText(kunde.getPersonnummer());
        regTlfnr.setText(kunde.getTlfnr());
        regAdresse.setText(kunde.getAdresse());
        regEpost.setText(kunde.getEpost());
        utgifter.setText(String.valueOf(vindu.getRegister().getUtgifter(kunde)));
        inntekter.setText(String.valueOf(vindu.getRegister().getInntekter(kunde)));
        inntekterTotal.setText(String.valueOf(vindu.getRegister().getNåværendeInntjening(kunde)));
        tabellModell = new TabellModellForsikring( vindu.getRegister().getForsikringrsliste().getKundensForsikringer(kunde));
        tabell.setModel(tabellModell);
        vindu.oppdaterTabell(vindu.getRegister().getKundeliste().alleKunder());
    }
    
    public void visForsikringensSkademeldnger()
    {
        Integer forsikringsnummer = (Integer) tabellModell.getValueAt(tabell.getSelectedRow(), 0);
        Forsikring forsirking = vindu.getRegister().getForsikringrsliste().getForsikring(forsikringsnummer);
        List<Skademelding> nyListe = vindu.getRegister().getSkademeldingsregister().getSkademeldinger(forsirking);
        TabellModellSkademeldinger nyModell = new TabellModellSkademeldinger(nyListe);
        tabell.setModel(nyModell);
        tabell.brukSkademeldingPopup();
    }
    
    public void visAlleSkademeldinger()
    {
        List<Forsikring> kundensForsikringer =   vindu.getRegister().getForsikringrsliste().getKundensForsikringer(kunde);
        List<Skademelding> nyListe = vindu.getRegister().getSkademeldingsregister().getKundensSkademeldinger(kundensForsikringer);
        if( kundensForsikringer.size() > 0 && nyListe.size() > 0 )
        {
            TabellModellSkademeldinger nyModell = new TabellModellSkademeldinger(nyListe);
            tabell.setModel(nyModell);
            tabell.brukSkademeldingPopup();
        }
        else
        {
            vindu.visInformasjon("Beskjed", "Denne kunden har ingen skademeldinger");
        }
    }
    
    public void åpneSkademeldingTab()
    {
        Integer skademeldingnummer = (Integer) tabellModell.getValueAt(tabell.getSelectedRow(), 0);
        Skademelding skademelding = vindu.getRegister().getSkademeldingsregister().getSkademelding(skademeldingnummer);
        SkademeldingPanel skademeldingsPanel = new SkademeldingPanel(skademelding.getForsikring(), vindu);
        skademeldingsPanel.setKundePanel(this);
        skademeldingsPanel.visSkademelding(skademelding);
        vindu.leggTilNyFane( skademeldingsPanel, "skademelding");
    }
    
    public void visNySkademeldingsTab()
    {
        Integer forsikringsnummer = (Integer) tabellModell.getValueAt(tabell.getSelectedRow(), 0);
        Forsikring forsirking = vindu.getRegister().getForsikringrsliste().getForsikring(forsikringsnummer);
        SkademeldingPanel skademeldingsPanel = new SkademeldingPanel(forsirking, vindu);
        skademeldingsPanel.setKundePanel(this);
        vindu.leggTilNyFane( skademeldingsPanel, "Skade " + forsirking.getKunde().getEtternavn() );
    }
    
    public void lagreEndringer()
    {
        
        String fornavn = regFornavn.getText();
        String etternavn = regEtternavn.getText();
        String adresse = regAdresse.getText();
        String telefonnummer = regTlfnr.getText();
        String epost = regEpost.getText();
            
        if( fornavn.length() > 0 || etternavn.length() > 0 || telefonnummer.matches("\\d{8}") 
                 || epost.matches(" ^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
        {
            kunde.setFornavn(fornavn);
            kunde.setEtternavn(etternavn);
            kunde.setAdresse(adresse);
            kunde.setTlfnr(telefonnummer);
            kunde.setEpost(epost);
        }
    }
    
    public void åpneForsikringsTab()
    {
        Integer forsikringsnummer = (Integer) tabellModell.getValueAt(tabell.getSelectedRow(), 0);
        Forsikring forsikring = vindu.getRegister().getForsikringrsliste().getForsikring(forsikringsnummer);
        vindu.leggTilForsikringsFane(forsikring, this);
    }
    
    public void deaktiverForsikring()
    {
        Integer forsikringsnummer = (Integer) tabellModell.getValueAt(tabell.getSelectedRow(), 0);
        int svar = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil deaktivere denne forsikringen?", "Forsikring " + forsikringsnummer , JOptionPane.YES_NO_OPTION);
        if (svar == JOptionPane.YES_OPTION)
        {
            vindu.getRegister().deaktiverForsikring(forsikringsnummer);
            tabellModell = new TabellModellForsikring( vindu.getRegister().getForsikringrsliste().getKundensForsikringer(kunde));
            tabell.setModel(tabellModell);
        }
    }        
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if( e.getSource() ==  kontaktKunde)
        {
            String mailTo = kunde.getEpost();
            URI uriMailTo;
       
            try
            {
                uriMailTo = new URI("mailto", mailTo, null);
                desktop.mail(uriMailTo);
            } 
            catch (URISyntaxException | IOException ex) 
            {
                
            }
        }
        else if( e.getSource() == rediger)
        {
            switch (rediger.getText()) {
                case "Rediger":
                    enableFelter(kundeInfo_1, null);
                    rediger.setText("Lagre");
                    break;
                case "Lagre":
                    if( JOptionPane.showConfirmDialog(vindu, "Er du sikker på at du ønsker å lagre endringene?", "Bekreftelse " ,
                                                      JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION )
                    {
                        lagreEndringer();
                    }
                    rediger.setText("Rediger");
                    disableFelter(kundeInfo_1, null, null);
                    revalidate();
                    repaint();
                    break;
            }
        }
        else if( e.getSource() == visForsikringer)
        {
            if( kunde.getNøkkelliste().size() > 0 )
            {
                List<Forsikring> kundeForsikringer = vindu.getRegister().getForsikringrsliste().getKundensForsikringer(kunde);
                TabellModellForsikring forsikringsTabell = new TabellModellForsikring(kundeForsikringer);
                tabell.setModel(forsikringsTabell);    
                tabell.brukForsikringsPopup();
            }
            else
            {
                vindu.visInformasjon("Beskjed", "Denne Kunden har ingen forsikringer" );
            }
        }
        else if( e.getSource() == visSkademeldinger)
        {
            visAlleSkademeldinger();
            tabell.brukSkademeldingPopup();
        }
        else if( e.getSource() == nyForsikring)
        {
            String valg = (String) forsikringsDropDown.getSelectedItem();
            switch (valg) {
                case "":
                    vindu.visFeilmelding("Melding", "Du må velge en type forsikring for å gå videre. ");
                    break;
                case "Bilforsikring":
                    BilforsikringPanel bilforsikringspanel = new BilforsikringPanel(kunde,vindu);
                    bilforsikringspanel.leggTilKundePanel(this);
                    vindu.leggTilNyFane( bilforsikringspanel, "Ny Bilforsikring");
                    break;
                case "Båtforsikring":
                    BatforsikringPanel båtforsikringspanel = new BatforsikringPanel(kunde, vindu);
                    båtforsikringspanel.leggTilKundePanel(this);
                    vindu.leggTilNyFane( båtforsikringspanel, "Ny Båtforsikring");
                    break;
                case "Husforsikring":
                    HusforsikringPanel husforsikringspanel = new HusforsikringPanel(kunde, vindu);
                    husforsikringspanel.leggTilKundePanel(this);
                    vindu.leggTilNyFane( husforsikringspanel, "Ny Husforsikring");   
                    break;
                case "Fritidsboligforsikring":
                    FritidsboligforsikringPanel fritidsboligpanel = new FritidsboligforsikringPanel(kunde,vindu);
                    fritidsboligpanel.leggTilKundePanel(this);
                    vindu.leggTilNyFane( new FritidsboligforsikringPanel(kunde, vindu), "Ny Fritidsboligforsikring");
                    break;
                case "Reiseforsikring":
                    ReiseforsikringPanel reiseforsikringspanel =  new ReiseforsikringPanel(kunde, vindu);
                    reiseforsikringspanel.leggTilKundePanel(this);
                    vindu.leggTilNyFane( reiseforsikringspanel, "Ny Reiseforsikring");
                    break;
            }
        }
    }
}
