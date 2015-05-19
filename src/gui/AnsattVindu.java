
/**
 * AnsattVindu er selve hovedvinduet i programmet.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.*;
import objekter.*;
import register.HovedRegister;

/**
 *
 * @author Odd, Marthe
 */
public class AnsattVindu extends JFrame
{
    private final GregorianCalendar kalender;
    private final Container mainContainer;
    private final JPanel hovedPanel;
    private final JPanel hovedPanelTop;
    private final JPanel hovedPanelBunn;
    private final JTabbedPane fanekort;
    private final MenyPanel menyPanel;
    private final JPanel tabellContainer;
    private final JPanel bunnContainer;
    private final JPanel søkePanel;
    
    private TabellModell tabellModell;
    private final KundeTabell tabell;
    
    private final JTextField søkefelt;
    private final JTextField søkefeltFornavn;
    private final JTextField søkefeltEtternavn;
    private final JButton søkeknapp;
    private final JButton alleKunder;
    private final JButton mineKunder;
    private final JToggleButton lukkeknapp;
    
    private final HovedRegister register;
    private Ansatt ansatt;
    public AnsattVindu()
    {
        super("Forsikring Vindu");
        setVisible(false);
        Toolkit verktøy = Toolkit.getDefaultToolkit();
        Dimension vinduStørelse = verktøy.getScreenSize();
        this.setLocation(vinduStørelse.width/6, vinduStørelse.height/10);
        
        
        kalender = new GregorianCalendar();
        register = new HovedRegister(this);
        KnappeLytter knappeLytter = new KnappeLytter();
        mainContainer = getContentPane();
        fanekort =  new JTabbedPane();
        fanekort.setPreferredSize( new Dimension(600,500));
        hovedPanelBunn =  new JPanel();
        hovedPanel = new JPanel();
        hovedPanelTop = new JPanel();
        menyPanel = new MenyPanel(this);
        tabellContainer = new JPanel();
        bunnContainer = new JPanel();
        søkePanel = new JPanel();
        
        
        alleKunder = new JButton("Alle kunder");
        mineKunder = new JButton("Mine kunder");
        
        søkefelt = new JTextField(15);
        søkefeltFornavn = new JTextField(25);
        søkefeltEtternavn = new JTextField(25);
        søkeknapp = new JButton("Søk");
        søkeknapp.addActionListener(knappeLytter);
        alleKunder.addActionListener(knappeLytter);
        mineKunder.addActionListener(knappeLytter);
        
        
        
        mainContainer.setLayout( new BorderLayout() );
        mainContainer.add( menyPanel, BorderLayout.LINE_START);
        mainContainer.add( hovedPanel, BorderLayout.CENTER);  
        mainContainer.add( hovedPanelBunn, BorderLayout.PAGE_END);
        
        hovedPanel.setLayout( new BorderLayout());
        hovedPanel.add(fanekort, BorderLayout.CENTER);
        
        lukkeknapp = new JCheckBox();
        
        if( register.getKundeliste().alleKunder() != null)
            tabell = new KundeTabell(new TabellModell(register.getKundeliste().alleKunder()), this);
        else
            tabell = new KundeTabell(this);
        
        tabell.setPreferredScrollableViewportSize(new Dimension(500,180));
        bunnContainer.removeAll();
        bunnContainer.setLayout( new BorderLayout() );
        søkePanel.setLayout( new FlowLayout() );
        søkePanel.add(alleKunder);
        søkePanel.add(mineKunder);
        søkePanel.add( new JLabel("ID: "));
        søkePanel.add(søkefelt);
        søkePanel.add(new JLabel("Fornavn: "));
        søkePanel.add( søkefeltFornavn );
        søkePanel.add(new JLabel("Etternavn: "));
        søkePanel.add( søkefeltEtternavn );
        søkePanel.add( søkeknapp);
        bunnContainer.add(søkePanel,BorderLayout.NORTH);
        tabellContainer.setLayout( new BorderLayout());
        tabellContainer.add(tabell.getTableHeader(), BorderLayout.NORTH);
        tabellContainer.add(new JScrollPane(tabell), BorderLayout.CENTER);
        bunnContainer.add( tabellContainer);
        hovedPanelBunn.setLayout( new BorderLayout() );
        hovedPanelBunn.add( bunnContainer, BorderLayout.PAGE_END);
        
        this.setMenuBar( new MenyLinje(this));
        visLogin();
        pack();
    } // slutt på konstuktør.

    
    // konstuerer og viser et nytt login vindu.
    public void visLogin()
    {
        LoginVindu loginvindu = new LoginVindu(this);
    }
    
    
    
    // Parameteren tar i mot et JPanel av gitt type som skal fjernes fra JTabbedPane
    public void lukkFanekort(JPanel panel)
    {
        fanekort.remove(panel);
	fanekort.setSelectedIndex(fanekort.getTabCount()-1);
    }
    
    
    // metoden lukker alle fanekort som er åpne i programmet.
    public void lukkAlleFanekort()
    {
        fanekort.removeAll();
    }
    
    
    /**
     * Legger til en ny fane i AnsattVinduet.
     * Parameteren panel tar i mot et Panel av en spesfikk type.
     * 
     * Den andre parameteren tittel tar i mot en String som brukes til å navngi 
     * selve tabben.
     */
    
    public void leggTilNyFane( JPanel panel, String tittel )
    {
        JPanel wrapper = new JPanel();
        wrapper.add( panel );
        fanekort.add(wrapper, (lukkeknapp));
        Fanepanel fanepanel = new Fanepanel(fanekort, wrapper, tittel);
        fanekort.setTabComponentAt(fanekort.getTabCount() - 1, fanepanel);
        fanekort.setSelectedIndex(fanekort.getTabCount() - 1);
        revalidate();
        repaint();
    }
    
    /**
     * Metoden brues når programmet skal vise en forsikring fra registeret og 
     * forsikringen er av ukjent type. Her skjekkes hvilken type forsikring objektet
     * representerer før det vises som en tab i vinduet.
     * 
     * Første paramter(forsikring) forsikringsobjektet som skal vises.
     * 
     * Andre paramter(kundePanel) har som oppgave å legge til kundepanelet som
     * som representerer kunden
     * 
     */
    public void leggTilForsikringsFane( Forsikring forsikring, KundePanel kundePanel )
    {
        if( forsikring.getClass() == Bilforsikring.class)
        {
            BilforsikringPanel panel = new BilforsikringPanel(forsikring.getKunde(), this);
            if( kundePanel != null)
                panel.leggTilKundePanel(kundePanel);
            panel.visForsikring(forsikring);
            leggTilNyFane( panel, "Bil " + forsikring.getKunde().getEtternavn());
        }
        else if( forsikring.getClass() == BatForsikring.class )
        {
            BatforsikringPanel panel = new BatforsikringPanel(forsikring.getKunde(), this);
            if( kundePanel != null)
                panel.leggTilKundePanel(kundePanel);
            panel.visForsikring(forsikring);
            leggTilNyFane(panel, "Båt " + forsikring.getKunde().getEtternavn());
        }
        else if( forsikring.getClass() == Husforsikring.class)
        {
            HusforsikringPanel panel = new HusforsikringPanel( forsikring.getKunde(), this);
            if( kundePanel != null)
                panel.leggTilKundePanel(kundePanel);
            panel.visForsikring(forsikring);
            leggTilNyFane(panel, "Hus " + forsikring.getKunde().getEtternavn()); //endre navn på tabs 
        }
        else if( forsikring.getClass() == Fritidsboligforsikring.class)
        {
            FritidsboligforsikringPanel panel = new FritidsboligforsikringPanel( forsikring.getKunde(), this);
            if( kundePanel != null)
                panel.leggTilKundePanel(kundePanel);
            panel.visForsikring(forsikring);
            leggTilNyFane(panel, "Fritidsbolig " + forsikring.getKunde().getEtternavn());
        }
        else if( forsikring.getClass() == Reiseforsikring.class)
        {
            ReiseforsikringPanel panel = new ReiseforsikringPanel(forsikring.getKunde(), this);
            if( kundePanel != null)
                panel.leggTilKundePanel(kundePanel);
            panel.visForsikring( forsikring );
            leggTilNyFane(panel, "Reise " + forsikring.getKunde().getEtternavn());
        }
    }
    
    /**
     * Metodens oppgave er å mate kunde tabellen med kunder som skal vises.
     * Brukes f.eks til å oppdater listen etter søk.
     * 
     * @param liste tar imot en List<Kunde> 
     */
    public void oppdaterTabell( List<Kunde> liste )
    {
        if( !liste.isEmpty() )
        {
            tabellModell = new TabellModell(liste);
            tabell.setModel(tabellModell);
            tabell.getRowSorter().toggleSortOrder(6);
        }
    }
    
    
    /**
     * Metoden avgjør hva som skal søkes etter i registerene utifra hvilke
     * søkeord som har blitt tastet inn av bruker i søkefeltet. F.eks en String bestående av tall 
     * på 11 siffer er et personnummer.
     * 
     * Dersom søkeordet er ID nummer for forsikring eller skademelding åpnes
     * det en ny JTabbedPane i vinduet.
    */
    public void visSøkeresltat()
    {
        String søkeord = søkefelt.getText();
        String fornavn = søkefeltFornavn.getText();
        String etternavn = søkefeltEtternavn.getText();
        
        if( søkeord.matches("\\d{11}"))
        {
            List<Kunde> nyListe = register.getKundeliste().finnAlleKundeEtterPersonnummer(søkeord);
            oppdaterTabell(nyListe);
            tomSøkefelter();
        }
        else if( søkeord.matches("\\d{7}"))
        {
            int forsikringsnummer = Integer.parseInt(søkeord);
            Forsikring forsikring = register.getForsikringrsliste().getForsikring(forsikringsnummer);
            leggTilForsikringsFane( forsikring, null );
            tomSøkefelter();
        }
        else if( søkeord.matches("\\d{9}"))
        {
            int skadenummer = Integer.parseInt(søkeord);
            Skademelding skademelding = register.getSkademeldingsregister().getSkademelding(skadenummer);
            leggTilNyFane( new SkademeldingPanel( skademelding.getForsikring(), this), "Skadenummer: " + skademelding.getSkadenummer());
            tomSøkefelter();
        }
        else if( etternavn.matches("\\D+") && fornavn.matches("\\D+"))
        {
            List<Kunde> nyListe =  register.getKundeliste().finnKunderEtterNavn(fornavn, etternavn);
            oppdaterTabell(nyListe);
            tomSøkefelter();
        }
        else if( etternavn.matches("\\D+") && fornavn.length() == 0)
        {
            List<Kunde> nyListe = register.getKundeliste().finnKundeEtterEtternavn(etternavn);
            oppdaterTabell(nyListe);
            tomSøkefelter();
        }
        else if( fornavn.matches("\\D+") && etternavn.length() == 0 )
        {
            List<Kunde> nyListe = register.getKundeliste().finnKundeEtterFornavn(fornavn);
            oppdaterTabell(nyListe);
            tomSøkefelter();
        }
        else
        {
            visInformasjon("Feilmelding", "Søket ga ingen funn.");
        }
    }
    
    // tømmer søkefeltene over kunde tabellen.
    public void tomSøkefelter()
    {
        søkefelt.setText("");
        søkefeltFornavn.setText("");
        søkefeltEtternavn.setText("");
    }
    
    /**
     * returnerer HovedRegister slik at panele får mulighet til å manipulere
     * data.
     */
    
    public HovedRegister getRegister()
    {
        return register;
    }
    
    /*
     Tar i mot en ansatt som parameter, brukes for å identifisere hvilken ansatt
     som for øyeblikket er logget inn i programmet.
     */
    
    public void setAnsatt( Ansatt ansatt )
    {
        this.ansatt = ansatt;
    }

    // returnerer ansatt som er logget inn i programmet.
    public Ansatt getAnsatt()
    {
        return ansatt;
    }
    
    /*En hjelpemetode slik at jeg får tak i metoden for lagring til fil
     i vinduslytterl.*/
    public void lagre()
    {
        register.skrivTilFil();
    }
    
    /**
    * @param tittel tar i mot en String som brukes som tittel på selve popup vinduet.
    * @param melding tar i mot en String som brukes til å fylle meldingsboksen med tekst.
    */
    
    public void visFeilmelding( String tittel, String melding )
    {
        JOptionPane.showMessageDialog(null, melding, tittel, JOptionPane.ERROR_MESSAGE);
    }
    
    // lager en OptionPane for å vise informasjon til brukeren.
    public void visInformasjon( String tittel, String melding )
    {
        JOptionPane.showMessageDialog(null, melding, tittel, JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    // private indre lytter klasse.
    private class KnappeLytter implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if( e.getSource() == søkeknapp )
            {
                visSøkeresltat();
            }
            else if( e.getSource() == alleKunder )
            {
                List<Kunde> kundeliste =  register.getKundeliste().alleKunder();
                oppdaterTabell(kundeliste);
            }
            else if( e.getSource() == mineKunder )
            {
                List<Kunde> kundeliste = register.getAnsattKunde(ansatt);
                oppdaterTabell(kundeliste);
            }
        }
    }
} // slutt på klasse

