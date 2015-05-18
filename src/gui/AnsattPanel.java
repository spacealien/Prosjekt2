/* Klassen bygger ansatt panelenet. klassen er ikke ferdig men er ment for å vise
 * diverse informasjon om den ansatte. 
 */
package gui;

//nødvendige import-setninger
import javax.swing.JLabel;
import javax.swing.JPanel;
import objekter.Ansatt;

/**
 *
 * @author Odd
 */
public class AnsattPanel extends JPanel 
{   
    private final JLabel brukerInfo;
    
    public AnsattPanel( AnsattVindu vindu,  Ansatt ansatt )
    {
        brukerInfo = new JLabel( ansatt.getFornavn() + " " + ansatt.getEtternavn() );
    }
}
