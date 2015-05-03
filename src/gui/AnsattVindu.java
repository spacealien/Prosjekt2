
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import objekter.Kunde;
import register.HovedRegister;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class AnsattVindu extends JFrame
{
    //private Tab tabben;
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
    
    private HovedRegister register;
    private Ansatt ansatt;
    
    public AnsattVindu()
    {
        super("Forsikring Vindu");
        setSize(1600,900);
        setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        register = new HovedRegister();
        KnappeLytter knappeLytter = new KnappeLytter();
        mainContainer = getContentPane();
        fanekort =  new JTabbedPane();
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
        
        
       /* Deler hele JFrame boksen i 3, en container til venstre,
        en top og en på bunn.
       */
        
        mainContainer.setLayout( new BorderLayout() );
        mainContainer.add( menyPanel, BorderLayout.WEST);
        mainContainer.add( hovedPanel, BorderLayout.CENTER);  
        mainContainer.add( hovedPanelBunn, BorderLayout.SOUTH);
        
        hovedPanel.setLayout( new BorderLayout());
        hovedPanel.add(fanekort, BorderLayout.CENTER);
        
        lukkeknapp = new JCheckBox();
        tabellModell = new TabellModell(register.getKundeliste().alleKunder());
        
        this.setMenuBar( new MenyLinje(this));
        visTabellPanel(tabellModell);
        visLogin();
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
    
    public void visTabellPanel( TabellModell modell)
    {
        hovedPanelBunn.removeAll();
        tabell = new KundeTabell(modell, this);
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
        hovedPanelBunn.add( bunnContainer, BorderLayout.CENTER);
    }
    
    public void oppdaterTabell( List<Kunde> liste )
    {
        if( liste.size() >= 1 )
        {
            tabellModell = new TabellModell(liste);
            tabell.setModel(tabellModell);
        }
        else
        {
            visInformasjon("Beskjed", "Søket ga ingen funn");
        }  
    }
    
    public void visSøkeresltat()
    {
        String søkeord = søkefelt.getText();
        String fornavn = søkefeltFornavn.getText();
        String etternavn = søkefeltEtternavn.getText();
        
        if( søkeord.matches("\\d{11}"))
        {
            System.out.println("test");
            List<Kunde> nyListe = register.getKundeliste().finnAlleKundeEtterPersonnummer(søkeord);
            oppdaterTabell(nyListe);
            tomSøkefelter();
        }
        else if( søkeord.matches("\\d{6}"))
        {
            
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
    
    // lager en JOptionPane av typen feilmelding.
    public void visFeilmelding( String tittel, String melding )
    {
        JOptionPane.showMessageDialog(null, menyPanel, null, WIDTH, null);
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
                
            }
        }
    }
}

