/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Odd
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import objekter.Ansatt;
import objekter.Kunde;
import register.ForsikringKontroller;

/**
 *
 * @author Odd
 */
public class AnsattVindu extends JFrame implements ActionListener
{
    private final Container mainContainer;
    private final ForsikringKontroller metoder = new ForsikringKontroller();
    private Ansatt ansatt = null;
    private LoginVindu loginvindu;
    
    private final JTabbedPane hovedPanel;
    private final KundePanel nyKundePanel;
    private final SøkePanel søkePanel;
    private final ForsikringsPanel forsikringsPanel;
    
    private final JButton nyKundeKnapp;
    
    
    Toolkit verktøykasse = Toolkit.getDefaultToolkit();
    Dimension skjermDimensjon = verktøykasse.getScreenSize();
    private final int VINDU_BREDDE = 1600;
    private final int VINDU_HOYDE = 900;
    private final int VINDU_PLASSERING_X = skjermDimensjon.width / 10;
    private final int VINDU_PLASSERING_Y = skjermDimensjon.height / 10;
    
    
    public AnsattVindu()
    {
        super("vindu");
        setLocation(VINDU_PLASSERING_X, VINDU_PLASSERING_Y);
        setSize(VINDU_BREDDE,VINDU_HOYDE);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       
        
        nyKundeKnapp = new JButton("Registrer");
        
        forsikringsPanel = new ForsikringsPanel();
        nyKundePanel = new KundePanel(this, false);
        søkePanel = new SøkePanel(this);
        hovedPanel = new JTabbedPane();
        
        mainContainer = getContentPane();
        mainContainer.setLayout( new GridBagLayout() );
        hovedPanel.setLayout( new GridBagLayout() );
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.weightx = 1000;
        gbc.weighty = 1000;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainContainer.add( new MenyPanel(this) , gbc );
        
        gbc.gridx = 1;
        gbc.gridy = 0;

        mainContainer.add( hovedPanel , gbc);
        visLoginVindu();
    }
    
    public void login( String brukernavn, String passord )
    {
        ansatt = metoder.login(brukernavn, passord);
        if(ansatt != null)
        {
            loginvindu.dispatchEvent(new WindowEvent( loginvindu, WindowEvent.WINDOW_CLOSING));
            this.setVisible(true);         
        }
    }
    
    public void visLoginVindu()
    {
        loginvindu = new LoginVindu(this);
    }
    
    public void visKundeVindu( Kunde kunde)
    {
        hovedPanel.removeAll();
        hovedPanel.add( new KundePanel( this, false) );
        revalidate();
        repaint();
    }
    
    public void visForsikringsVindu()
    {
        hovedPanel.removeAll();
        hovedPanel.add(forsikringsPanel);
        revalidate();
        repaint();
    }
    
    public void visSøkeVindu()
    {
        hovedPanel.removeAll();
        hovedPanel.add( søkePanel );
        revalidate();
        repaint();
    }
    
    public ForsikringKontroller getMetoder()
    {
        return metoder;
    }
    
    public void skrivUtInfoBox( String innhold )
    {
        JOptionPane.showMessageDialog(null, innhold, "Meldings Box", JOptionPane.INFORMATION_MESSAGE );
    }
    
    public void skrivUtFeilMelding( String innhold )
    {
        JOptionPane.showMessageDialog(null, innhold , "Advarsel", JOptionPane.ERROR_MESSAGE );
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {

    }
}
