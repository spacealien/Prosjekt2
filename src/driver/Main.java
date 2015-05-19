/**
 * Denne klassen inneholder programmets main metode samnt vinduslytter
 * som utøver lagring av fil før programmet lukkes.
 */

package driver;

import gui.AnsattVindu;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author Odd
 */
public class Main 
{
    public static void main( String[] args)
    {
        // endrer innholdet i OptionPane bokene slik at inneholdet i vises på norsk.
        UIManager.put("OptionPane.cancelButtonText", "Cancel");
        UIManager.put("OptionPane.noButtonText", "Nei");
        UIManager.put("OptionPane.okButtonText", "OK");
        UIManager.put("OptionPane.yesButtonText", "Ja");
        
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                AnsattVindu vindu = new AnsattVindu();
                
                // legger til vinduslytter som registrer om viduet er i ferd med å lukkes.
                vindu.addWindowListener( new WindowAdapter() 
                {
                    @Override
                    public void windowClosing( WindowEvent e )
                    {
                        vindu.lagre();                  // sørger for at det blir skrevet til fil før programmet avsluttes.
                        System.exit( 0 );
                    }
                });
            }
        });
    }
    
}
