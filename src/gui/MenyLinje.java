
package gui;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/*
 * Klassen bygger menylinjen som er øverst i programmet.  
 * @author Odd, Marthe. sist endret 17.05.2015.
 */

public class MenyLinje extends MenuBar
{
    private final MenyLytter lytter = new MenyLytter();
    private final Menu filMeny = new Menu("Fil");
    private final Menu vinduMeny = new Menu("Vindu");
    private final MenuItem loggUtKnapp = new MenuItem("Logg Ut");
    private final MenuItem backupKnapp = new MenuItem("Lagre");
    private final MenuItem avsluttKnapp = new MenuItem("Avslutt");
    private final AnsattVindu ansattVindu;
    
    public MenyLinje(AnsattVindu vindu )
    {
        ansattVindu = vindu;
        filMeny.add(loggUtKnapp);
        filMeny.add(backupKnapp);
        filMeny.add(avsluttKnapp);
        this.add(filMeny);
        this.add(vinduMeny);
        
        loggUtKnapp.addActionListener(lytter);
        avsluttKnapp.addActionListener(lytter);
        backupKnapp.addActionListener(lytter);
    }
    
    // Gjemmer hovedvinduet, og ber programmet om å vise et nytt loginVIndu.
    public void logUt()
    {
        ansattVindu.setAnsatt(null);
        ansattVindu.setVisible(false);
        ansattVindu.visLogin();
    }
    
    // seneder en beskjed til hovedvinduet om at programmet skal avsluttes.
    public void avslutt()
    {
        ansattVindu.dispatchEvent(new WindowEvent( ansattVindu,WindowEvent.WINDOW_CLOSING));
    }
    
    // skriver data til fil.
    public void lagre()
    {
        ansattVindu.getRegister().skrivTilFil();
    }
    
    private class MenyLytter implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if( e.getSource() == loggUtKnapp)
            {
                ansattVindu.setAnsatt(null);
                ansattVindu.setVisible(false);
                ansattVindu.visLogin();
            }
            else if( e.getSource() == backupKnapp )
            {
                lagre();
            }
            else if( e.getSource() == avsluttKnapp )
            {
                avslutt();
            }
        }
        
    }
}
