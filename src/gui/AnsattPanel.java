/* Klassen bygger ansatt panelenet. klassen er ikke ferdig men er ment for å vise
 * diverse informasjon om den ansatte. 
 */
package gui;

//nødvendige import-setninger
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import objekter.Ansatt;
import objekter.Forsikring;
import objekter.Inntekt;
import objekter.Kunde;
import objekter.Skademelding;

/**
 *
 * @author Odd
 */
public class AnsattPanel extends JPanel 
{   
    private final JLabel brukerInfo;
    private final JTextField antKunder;
    private final JTextField utgifter;
    private final JTextField inntekter;
    private final JTextField antForsikringer;
    private int antallKunder = 0;
    private int antallForsikringer = 0;
    private double totalInntekt = 0;
    private double totalutgift = 0;
    
    public AnsattPanel( AnsattVindu vindu,  Ansatt ansatt )
    {
        antKunder = new JTextField(4);
        antForsikringer = new JTextField(4);
        utgifter = new JTextField(4);
        inntekter = new JTextField(4);
        brukerInfo = new JLabel( ansatt.getFornavn() + " " + ansatt.getEtternavn() );
        
        for (Kunde kunde : vindu.getRegister().getAnsattKunde(ansatt))
        {
            antallKunder++;
            for (Forsikring forsikring : vindu.getRegister().getAlleKundensForsikringer(kunde))
            {
                antallForsikringer++;
                for (Inntekt inntekt : vindu.getRegister().getAlleInntekter())
                {
                    if(inntekt.getForsikring().equals(forsikring))
                        totalInntekt += inntekt.getSum();
                }
            }
            
            for (Skademelding skademelding : vindu.getRegister().getAlleKundensSkademeldinger(kunde))
                totalutgift += skademelding.getErstatningsbelop();
                
        }
        
        
        antKunder.setText(String.valueOf(antallKunder));
        antForsikringer.setText(String.valueOf(antallForsikringer));
        inntekter.setText(String.valueOf(totalInntekt));
        utgifter.setText(String.valueOf(totalutgift));
        setLayout(new GridLayout(6,2,1,5));
        add(new JLabel(ansatt.getFornavn()));
        add(new JLabel(ansatt.getEtternavn()));
        add(new JLabel("Total antall kunder:"));
        add(antKunder);
        add(new JLabel("Total antall forsikringer:"));
        add(antForsikringer);
        add(new JLabel("<html>Mine kunders totale<br>erstatningsutgifter:</html>"));
        add(utgifter);
        add(new JLabel("<html>Total premieinntekt<br>fra mine kunder:</html>"));
        add(inntekter);
        
    }
    
}
