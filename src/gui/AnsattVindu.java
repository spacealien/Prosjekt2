/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
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
    private final JToggleButton lukkeknapp;
    
    private HovedRegister register;
    
    public AnsattVindu()
    {
        super("Forsikring Vindu");
        setSize(1600,900);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        register = new HovedRegister();
        KnappeLytter knappeLytter = new KnappeLytter();
        mainContainer = getContentPane();
        fanekort =  new JTabbedPane();
        hovedPanelBunn =  new JPanel();
        hovedPanel = new JPanel();
        menyPanel = new MenyPanel(this);
        tabellContainer = new JPanel();
        bunnContainer = new JPanel();
        søkePanel = new JPanel();
        
        søkefelt = new JTextField(15);
        søkefeltFornavn = new JTextField(25);
        søkefeltEtternavn = new JTextField(25);
        søkekanpp = new JButton("Søk");
        søkekanpp.addActionListener(knappeLytter);
        
        
       /* Deler hele JFrame boksen i 3, en container til venstre,
        en top og en på bunn.
       */
        
        mainContainer.setLayout( new GridBagLayout() );
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainContainer.add( menyPanel, gbc);
        
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 100;
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainContainer.add( hovedPanel, gbc);  
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainContainer.add( hovedPanelBunn, gbc);
        
        hovedPanel.setLayout( new GridBagLayout() );
        gbc.gridx = 0;
        gbc.gridy = 0;
        hovedPanel.add(fanekort, gbc);
        
        hovedPanelBunn.setLayout( new BorderLayout());
        
        lukkeknapp = new JCheckBox();
        
        tabellModell = new TabellModell(register.getKundeliste().alleKunder());
        visTabellPanel(tabellModell);
    }
    

    public void lukkFanekort(JPanel panel)
    {
        fanekort.remove(panel);
	fanekort.setSelectedIndex(fanekort.getTabCount()-1);
    }
    
    public void leggTilNyFane( JPanel panel )
    {
        JPanel wrapper = new JPanel();
        wrapper.add( panel );
        fanekort.add(wrapper, ("Ny Kunde" + lukkeknapp));
        Fanepanel fanepanel = new Fanepanel(fanekort, wrapper, "Ny kunde");
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
    
    public void oppdaterTabell( TabellModell modell )
    {
        tabell.setModel(modell);
    }
    
    private class KnappeLytter implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if( e.getSource() == søkekanpp )
            {
                String søkeord = søkefelt.getText();
                String fornavn = søkefeltFornavn.getText();
                String etternavn = søkefeltEtternavn.getText();
                List<Kunde> testliste = register.finnKundeMedNavn(fornavn,etternavn);
                tabellModell = new TabellModell(testliste);
                oppdaterTabell(tabellModell);
            }

        }
    }
}
