/* Klassen bygger ansatt panelenet. klassen er ikke ferdig men er ment for å vise
 * diverse informasjon om den ansatte. 
 */
package gui;

//nødvendige import-setninger
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

        JPanel minInfo = new JPanel();
        minInfo.setLayout( new GridLayout());
        minInfo.add( new JLabel("Bruker: ") );
        
        this.setLayout( new GridBagLayout() );
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 100;
        gbc.weighty = 100;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add( new JLabel("Bruker: "), gbc );
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(brukerInfo, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
    }
}
