
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import objekter.Ansatt;
import objekter.BatForsikring;
import objekter.Bilforsikring;
import objekter.Forsikring;
import objekter.Fritidsboligforsikring;
import objekter.Husforsikring;
import objekter.Kunde;
import objekter.Reiseforsikring;
import objekter.Skademelding;
import register.HovedRegister;

/**
 *
 * @author Odd, Thomas, Marthe
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
    private KundeTabell tabell;
    
    private final JTextField søkefelt;
    private final JTextField søkefeltFornavn;
    private final JTextField søkefeltEtternavn;
    private final JButton søkekanpp;
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
        fanekort.setPreferredSize( new Dimension(500,500));
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
        søkekanpp = new JButton("Søk");
        søkekanpp.addActionListener(knappeLytter);
        alleKunder.addActionListener(knappeLytter);
        mineKunder.addActionListener(knappeLytter);
        
        
        
        mainContainer.setLayout( new BorderLayout() );
        mainContainer.add( menyPanel, BorderLayout.LINE_START);
        mainContainer.add( hovedPanel, BorderLayout.CENTER);  
        mainContainer.add( hovedPanelBunn, BorderLayout.PAGE_END);
        
        hovedPanel.setLayout( new BorderLayout());
        hovedPanel.add(fanekort, BorderLayout.CENTER);
        
        lukkeknapp = new JCheckBox();
        
        List<Kunde> kundeListe = register.getKundeliste().alleKunder();
        if( kundeListe != null )
            tabellModell = new TabellModell(register.getKundeliste().alleKunder());
        
        this.setMenuBar( new MenyLinje(this));
        visTabellPanel(tabellModell);
        visLogin();
        pack();
        

    }
    
    public GregorianCalendar getKalender()
    {
        return kalender;
    }
    
    public void visLogin()
    {
        LoginVindu loginvindu = new LoginVindu(this);
    }
    
    public void setAnsatt( Ansatt ansatt )
    {
        this.ansatt = ansatt;
    }
    
    public void lukkFanekort(JPanel panel)
    {
        fanekort.remove(panel);
	fanekort.setSelectedIndex(fanekort.getTabCount()-1);
    }
    
    public void lukkAlleFanekort()
    {
        fanekort.removeAll();
    }
    
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
    
    public void visTabellPanel( TabellModell modell)
    {
        hovedPanelBunn.removeAll();
        tabell = new KundeTabell(modell, this);
        tabell.setPreferredScrollableViewportSize(new Dimension(500,180));
        bunnContainer.removeAll();
        bunnContainer.setLayout( new BorderLayout() );
        søkePanel.removeAll();
        søkePanel.setLayout( new FlowLayout() );
        søkePanel.add(alleKunder);
        søkePanel.add(mineKunder);
        søkePanel.add( new JLabel("ID: "));
        søkePanel.add(søkefelt);
        søkePanel.add(new JLabel("Fornavn: "));
        søkePanel.add( søkefeltFornavn );
        søkePanel.add(new JLabel("Etternavn: "));
        søkePanel.add( søkefeltEtternavn );
        søkePanel.add( søkekanpp);
        bunnContainer.add(søkePanel,BorderLayout.NORTH);
        tabellContainer.removeAll();
        tabellContainer.setLayout( new BorderLayout());
        tabellContainer.add(tabell.getTableHeader(), BorderLayout.NORTH);
        tabellContainer.add(new JScrollPane(tabell), BorderLayout.CENTER);
        bunnContainer.add( tabellContainer);
        hovedPanelBunn.setLayout( new BorderLayout() );
        hovedPanelBunn.add( bunnContainer, BorderLayout.PAGE_END);
    }
    
    public void oppdaterTabell( List<Kunde> liste )
    {
        if( liste != null )
        {
            tabellModell = new TabellModell(liste);
            tabell.setModel(tabellModell);
        }
    }
    
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
            visFeilmelding("Feilmelding", "Søket ga ingen funn.");
        }
    }

    public void tomSøkefelter()
    {
        søkefelt.setText("");
        søkefeltFornavn.setText("");
        søkefeltEtternavn.setText("");
    }
    
    public HovedRegister getRegister()
    {
        return register;
    }
    
    public Ansatt getAnsatt()
    {
        return ansatt;
    }
    
    public void lagre()
    {
        register.skrivTilFil();
    }
    
    // lager en JOptionPane av typen feilmelding.
    public void visFeilmelding( String tittel, String melding )
    {
        JOptionPane.showMessageDialog(null, melding, tittel, JOptionPane.ERROR_MESSAGE);
    }
    
    public void visInformasjon( String tittel, String melding )
    {
        JOptionPane.showMessageDialog(null, melding, tittel, JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    private class KnappeLytter implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if( e.getSource() == søkekanpp )
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
}

