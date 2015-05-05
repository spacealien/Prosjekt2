/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import objekter.Forsikring;
import objekter.Fritidsboligforsikring;
import objekter.Kunde;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class KundePanel extends JPanel implements ActionListener
{
    private final JPanel kundeInfo_1;
    private final JPanel kundeInfo_2;
    private final JPanel knappeWrapper;
    private final JTextField regFornavn;
    private final JTextField regEtternavn;
    private final JTextField regPersnr;
    private final JTextField regTlfnr;
    private final JTextField regAdresse;
    private final JTextField regEpost;
    private final JButton regKunde;
    private final JButton kontaktKunde;
    private final AnsattVindu vindu;
    private final JButton visForsikringer = new JButton("Vis Forsikringer");
    private final JButton visSkademeldinger = new JButton("Vis Skademeldinger");
    private final JButton nyForsikring = new JButton("Ny forsikring");
    private final JButton nySkademelding = new JButton("Ny Skademelding");
    private Kunde kunde = null;
    private final String[] forsikringsvalg = {"", "Bilforsikring", "Båtforsikring", "Husforsikring", "Fritidsboligforsikring", "Reiseforsikring"};
    private final JComboBox<String> forsikringsDropDown = new JComboBox<>(forsikringsvalg);
    
    public KundePanel( AnsattVindu vindu)
    {
        this.vindu = vindu;
        kundeInfo_1 = new JPanel();
        kundeInfo_2 = new JPanel();
        knappeWrapper = new JPanel();
        
        regFornavn = new JTextField( 15 );
        regEtternavn = new JTextField( 15 );
        regPersnr = new JTextField( 11 );
        regTlfnr = new JTextField( 8 );
        regAdresse = new JTextField( 15 );
        regEpost = new JTextField(20);
        regKunde = new JButton("Videre" );
        kontaktKunde = new JButton("Kontakt");
        regKunde.addActionListener(this);
        kundeInfo_1.setLayout(new GridLayout(6,2,5,10));
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
        
        setLayout( new BorderLayout() );
        knappeWrapper.setLayout( new FlowLayout() );
        knappeWrapper.add( new JLabel("Velg Forsikringstype: "));
        knappeWrapper.add(forsikringsDropDown);
        knappeWrapper.add(regKunde);
        add(kundeInfo_1, BorderLayout.CENTER );
        add(knappeWrapper, BorderLayout.SOUTH );
    }
    
    public KundePanel( AnsattVindu vindu, Kunde kunde )
    {
        this.vindu = vindu;
        this.kunde = kunde;
        kundeInfo_1 = new JPanel();
        kundeInfo_2 = new JPanel();
        knappeWrapper = new JPanel();
        
        regFornavn = new JTextField( 15 );
        regEtternavn = new JTextField( 15 );
        regPersnr = new JTextField( 11 );
        regTlfnr = new JTextField( 8 );
        regAdresse = new JTextField( 15 );
        regEpost = new JTextField(20);
        regKunde = new JButton("Registrer kunde" );
        kontaktKunde = new JButton("Kontakt");
        kontaktKunde.addActionListener(this);
        regKunde.addActionListener(this);
        kundeInfo_1.setLayout(new GridLayout(6,2,5,10));
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
        
        kundeInfo_2.setLayout( new GridLayout(6,2,5,10) );
        kundeInfo_2.add( new JLabel("Aktive forsikringer: "));
        kundeInfo_2.add( new JTextField(3));
        kundeInfo_2.add( new JLabel("Antall skademeldinger"));
        kundeInfo_2.add( new JTextField(3));
        kundeInfo_2.add( new JLabel("Kundenummer: "));
        kundeInfo_2.add( new JTextField(15));
        kundeInfo_2.add( new JLabel("Tidligere forhold"));
        kundeInfo_2.add( new JTextField(3));
        kundeInfo_2.add( new JLabel());
        kundeInfo_2.add( new JLabel());
        kundeInfo_2.add( new JLabel());
        kundeInfo_2.add( new JLabel());
        
        regFornavn.setText(kunde.getFornavn());
        regEtternavn.setText(kunde.getEtternavn());
        regPersnr.setText(kunde.getPersonnummer());
        regTlfnr.setText(kunde.getTlfnr());
        regAdresse.setText(kunde.getAdresse());
        regEpost.setText(kunde.getEpost());
        
        JPanel infobox = new JPanel();
        infobox.setLayout( new BoxLayout(infobox,BoxLayout.X_AXIS) );
        infobox.add( kundeInfo_1 );
        infobox.add(  Box.createRigidArea(new Dimension(14,20)) );
        infobox.add( kundeInfo_2 );
        
        knappeWrapper.setLayout( new FlowLayout() );
        knappeWrapper.add(visForsikringer);
        knappeWrapper.add(visSkademeldinger);
        knappeWrapper.add(nyForsikring);
        knappeWrapper.add(nySkademelding);
        knappeWrapper.add(kontaktKunde);
        
        visForsikringer.addActionListener(this);
        
        
        setLayout( new BorderLayout()  );
        add( infobox, BorderLayout.CENTER );
        add(knappeWrapper, BorderLayout.SOUTH);
    }
    
    public Kunde nyKunde()
    {
        try
        { 
           String fornavn = regFornavn.getText();
           String etternavn = regEtternavn.getText();
            String adresse = regAdresse.getText();
            String telefonnummer = regTlfnr.getText();
            String epost = regEpost.getText();
            String personnummer = regPersnr.getText();
            //int fødselsår = Integer.parseInt(epost);
            //int fødselsmåned = Integer.parseInt(epost);
            //int fødselsdato = Integer.parseInt(epost);

            Date fødelsdato = new Date();
            Kunde kunde = new Kunde( fornavn, etternavn, adresse, telefonnummer,
            fødelsdato, epost, personnummer );
            return kunde;
        }
        catch( NumberFormatException e)
        {
            
        }
        return null;
    }
    
    /*public boolean validerFelter()
    {
        
    }*/
    
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if( e.getSource() ==  kontaktKunde)
        {
            
        }
        else if( e.getSource() == regKunde)
        {
            Kunde nyKunde = nyKunde();
            String valg = (String) forsikringsDropDown.getSelectedItem();
            if( valg.equals(""))
                vindu.visFeilmelding("Melding", "Du må velge en type forsikring for å gå videre. ");
            else if( valg.equals("Bilforsikring") )
                vindu.leggTilNyFane( new BilforsikringPanel(nyKunde, vindu), "Ny Bilforsikring");
            else if( valg.equals("Båtforsikring"))
                vindu.leggTilNyFane( new BatforsikringPanel(nyKunde, vindu), "Ny Båtforsikring");
            else if( valg.equals("Husforsikring"))
                vindu.leggTilNyFane( new HusforsikringPanel(nyKunde, vindu), "Ny Husforsikring");
            else if( valg.equals("Fritidsboligforsikring"))
                vindu.leggTilNyFane( new FritidsboligforsikringPanel(nyKunde, vindu), "Ny Fritidsboligforsikring");
            else if( valg.equals("Reiseforsikring"))
                vindu.leggTilNyFane( new ReiseforsikringPanel(nyKunde, vindu), "Ny Reiseforsikring");
        }
        else if( e.getSource() == visForsikringer)
        {
            List<Forsikring> kundeForsikringer = vindu.getRegister().getForsikringrsliste().getKundensForsikringer(kunde);
        }
    }
}
