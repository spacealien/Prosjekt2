/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import objekter.BatForsikring;
import objekter.Bilforsikring;
import objekter.Forsikring;
import objekter.Fritidsboligforsikring;
import objekter.Husforsikring;
import objekter.Kunde;
import objekter.Reiseforsikring;
import objekter.Skademelding;

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
    private final JButton visForsikringer = new JButton("Vis Alle Forsikringer");
    private final JButton visSkademeldinger = new JButton("Vis Alle Skademeldinger");
    private final JButton nyForsikring = new JButton("Ny forsikring");
    private final JButton nySkademelding = new JButton("Ny Skademelding");
    private final JButton rediger = new JButton("Rediger informasjon");
    private Kunde kunde = null;
    private final String[] forsikringsvalg = {"", "Bilforsikring", "Båtforsikring", "Husforsikring", "Fritidsboligforsikring", "Reiseforsikring"};
    private final JComboBox<String> forsikringsDropDown = new JComboBox<>(forsikringsvalg);
    
    
    
    private final KundeDataTabell tabell;
    private final AbstractTableModel tabellModell;
    
    private final Desktop desktop = Desktop.getDesktop();
    private final Desktop.Action action = Desktop.Action.OPEN;
    
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
        kundeInfo_2.add( new JTextField());
        kundeInfo_2.add( new JLabel("Antall skademeldinger"));
        kundeInfo_2.add( new JTextField(3));
        kundeInfo_2.add( new JLabel());
        kundeInfo_2.add( new JTextField());
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
        //infobox.add(  Box.createRigidArea(new Dimension(14,20)) );
        //infobox.add( kundeInfo_2 );
        
        knappeWrapper.setLayout( new FlowLayout() );
        knappeWrapper.add(visForsikringer);
        knappeWrapper.add(visSkademeldinger);
        knappeWrapper.add(nySkademelding);
        knappeWrapper.add(kontaktKunde);
        knappeWrapper.add(rediger);
        
        JPanel forsikringsVelger = new JPanel();
        forsikringsVelger.setLayout( new FlowLayout() );
        forsikringsVelger.add( new JLabel("Velg Forsikringstype"));
        forsikringsVelger.add(forsikringsDropDown);
        forsikringsVelger.add(nyForsikring);
        
        JPanel bunnWrapper = new JPanel();
        tabellModell = new TabellModellForsikring( vindu.getRegister().getForsikringrsliste().getKundensForsikringer(kunde), this);
        tabell = new KundeDataTabell(tabellModell,this);
        tabell.setPreferredScrollableViewportSize(new Dimension(500,50));
        JScrollPane scrollTabell = new JScrollPane(tabell);
        bunnWrapper.setLayout( new BorderLayout() );
        bunnWrapper.add( tabell.getTableHeader(), BorderLayout.NORTH);
        bunnWrapper.add( scrollTabell, BorderLayout.CENTER);
        bunnWrapper.add( forsikringsVelger, BorderLayout.PAGE_END);

        setLayout( new BorderLayout()  );
        add( infobox, BorderLayout.NORTH );
        add(knappeWrapper, BorderLayout.CENTER);
        add(bunnWrapper, BorderLayout.SOUTH);
        
        
        kontaktKunde.addActionListener(this);
        regKunde.addActionListener(this);
        visForsikringer.addActionListener(this);
        visSkademeldinger.addActionListener(this);
        rediger.addActionListener(this);
        nyForsikring.addActionListener(this);
        
        for(Component component : getKomponenter(kundeInfo_1))
                {
                    if((component instanceof JTextField))
                    {
                        JTextField tf = (JTextField)component;
                        tf.setEditable(false);
                    }
                }
    }
    
    private Component[] getKomponenter( Component pane)
     {
        ArrayList<Component> liste = null;

        try
        {
            liste = new ArrayList<Component>(Arrays.asList(
                  ((Container) pane).getComponents()));
            for (int i = 0; i < liste.size(); i++)
            {
                for (Component currentComponent : getKomponenter(liste.get(i)))
                {
                    liste.add(currentComponent);
                }
            }
        } 
        catch (ClassCastException e) 
        {
            liste = new ArrayList<Component>();
        }
        return liste.toArray(new Component[liste.size()]);
    }
    
    public void visForsikringensSkademeldnger()
    {
        Integer forsikringsnummer = (Integer) tabellModell.getValueAt(tabell.getSelectedRow(), 0);
        Forsikring forsirking = vindu.getRegister().getForsikringrsliste().getForsikring(forsikringsnummer);
        List<Skademelding> nyListe = vindu.getRegister().getSkademeldingsregister().getSkademeldinger(forsirking);
        TabellModellSkademeldinger nyModell = new TabellModellSkademeldinger(nyListe, this);
        tabell.setModel(nyModell);
        tabell.brukSkademeldingPopup();
    }
    
    public void visAlleSkademeldinger()
    {
        List<Forsikring> kundensForsikringer =   vindu.getRegister().getForsikringrsliste().getKundensForsikringer(kunde);
        List<Skademelding> nyListe = vindu.getRegister().getSkademeldingsregister().getKundensSkademeldinger(kundensForsikringer);
        TabellModellSkademeldinger nyModell = new TabellModellSkademeldinger(nyListe, this);
        tabell.setModel(nyModell);
        tabell.brukSkademeldingPopup();
    }
    
    public void åpneSkademeldingTab()
    {
        Integer skademeldingnummer = (Integer) tabellModell.getValueAt(tabell.getSelectedRow(), 0);
        Skademelding skademelding = vindu.getRegister().getSkademeldingsregister().getSkademelding(skademeldingnummer);
        //vindu.leggTilNyFane( new SkademeldingPanel(kunde,skademelding.getForsikring(),vindu) "skademelding");
    }
    
    
    // ikke ferdig, gjenstår å endre navnene på tabs til noe informativt og fylle ut alle feltene i planelene, panel.visForsikring();
    public void åpneForsikringsTab()
    {
        Integer forsikringsnummer = (Integer) tabellModell.getValueAt(tabell.getSelectedRow(), 0);
        Forsikring forsikring = vindu.getRegister().getForsikringrsliste().getForsikring(forsikringsnummer);
        if( forsikring.getClass() == Bilforsikring.class)
        {
            BilforsikringPanel panel = new BilforsikringPanel(forsikring.getKunde(), vindu);
            panel.visForsikring(forsikring);
            vindu.leggTilNyFane( panel, "test");
        }
        else if( forsikring.getClass() == BatForsikring.class )
        {
            BatforsikringPanel panel = new BatforsikringPanel(forsikring.getKunde(),vindu);
            panel.visForsikring((BatForsikring)forsikring);
            vindu.leggTilNyFane(panel, "informativ tekst");
        }
        else if( forsikring.getClass() == Husforsikring.class)
        {
            HusforsikringPanel panel = new HusforsikringPanel( forsikring.getKunde(), vindu);
            panel.visForsikring(forsikring);
            vindu.leggTilNyFane(panel, "test"); //endre navn på tabs 
        }
        else if( forsikring.getClass() == Fritidsboligforsikring.class)
        {
            FritidsboligforsikringPanel panel = new FritidsboligforsikringPanel( forsikring.getKunde(), vindu);
            panel.visForsikring(forsikring);
            vindu.leggTilNyFane(panel, "test");
        }
        else if( forsikring.getClass() == Reiseforsikring.class)
        {
            ReiseforsikringPanel panel = new ReiseforsikringPanel(forsikring.getKunde(), vindu);
            panel.visForsikring( forsikring );
            vindu.leggTilNyFane(panel, "test");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if( e.getSource() ==  kontaktKunde)
        {
            String mailTo = kunde.getEpost();
            URI uriMailTo = null;
       
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
            for(Component component : getKomponenter(this))
                {
                    if((component instanceof JTextField))
                    {
                        JTextField tf = (JTextField)component;
                        tf.setEditable(true);
                    }
                }
        }
        else if( e.getSource() == visForsikringer)
        {
            if( kunde.getNøkkelliste().size() > 0)
            {
                List<Forsikring> kundeForsikringer = vindu.getRegister().getForsikringrsliste().getKundensForsikringer(kunde);
                TabellModellForsikring forsikringsTabell = new TabellModellForsikring(kundeForsikringer, this);
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
            
        }
    }
}
