/**
 * Klassen bygger menylinjen som er ankret venstre i vinduet.
 */

package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Odd, Marthe
 */
public class MenyPanel extends JPanel implements ActionListener
{
    private final JTextField søkFelt;
    private final JButton søkKnapp;   
    private final JButton nyKunde;
    private final JButton finnKunde;
    private final JButton nyForsikring;
    private final JButton nySkademelding;
    private final JButton avansertSok;
    private final JButton finnStatistikk;
    private final AnsattVindu vindu;
    
    public MenyPanel( AnsattVindu vindu )
    {
        this.vindu = vindu;
        søkFelt = new JTextField(20);
        søkKnapp = new JButton("Søk");
        nyKunde = new JButton("Ny kunde");
        finnKunde = new JButton("Finn kunde");
        nyForsikring = new JButton("Ny forsikring");
        nySkademelding = new JButton("Ny skademelding");
        avansertSok = new JButton("Avansert søk");
        finnStatistikk = new JButton("Finn statistikk");
        
        
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new GridLayout(8,1,5,2));        
        wrapper.add(nyKunde);
        wrapper.add(finnStatistikk);
        add(wrapper);
        
        søkKnapp.addActionListener(this);
        nyKunde.addActionListener(this);
        finnKunde.addActionListener(this);
        nyForsikring.addActionListener(this);
        nySkademelding.addActionListener(this);
        avansertSok.addActionListener(this);
        finnStatistikk.addActionListener(this);
    }
    
    @Override
    public void actionPerformed( ActionEvent e) 
    {
        if( e.getSource() == nyKunde)
        {
            vindu.leggTilNyFane( new NyKundePanel(vindu), "Ny kunde");
        }
        else if( e.getSource() == finnStatistikk )
        {
            vindu.leggTilNyFane( new StatistikkPanel(vindu), "Statistikk" );
        }

    }
}
 