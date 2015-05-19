/* Klassen bygger ansatt panelenet. klassen er ikke ferdig men er ment for å vise
 * diverse informasjon om den ansatte. 
 */
package gui;

//nødvendige import-setninger
import java.awt.*;
import javax.swing.*;
import objekter.*;

/**
 *
 * @author Odd, Marthe
 */
/*Klassens hensikt er å vise info knyttet til den ansattes kunder*/
public class AnsattPanel extends JPanel 
{   
    private final AnsattVindu vindu;
    private final Ansatt ansatt;
    private final JLabel velkommen;
    private final JTextField antKunder;
    private final JTextField utgifter;
    private final JTextField inntekter;
    private final JTextField antForsikringer;
    private final JPanel panel;
    private int antallKunder = 0;
    private int antallForsikringer = 0;
    private double totalInntekt = 0;
    private double totalutgift = 0;
    
    public AnsattPanel( AnsattVindu v,  Ansatt a )
    {
        vindu = v;
        ansatt = a;
        velkommen = new JLabel("Velkommen " + ansatt.getFornavn() + " " + ansatt.getEtternavn());
        velkommen.setFont(new Font("Helvetica", Font.BOLD, 30));
        velkommen.setForeground(Color.blue);
        antKunder = new JTextField(4);
        antKunder.setEditable(false);
        antForsikringer = new JTextField(4);
        antForsikringer.setEditable(false);
        utgifter = new JTextField(4);
        utgifter.setEditable(false);
        inntekter = new JTextField(4);
        inntekter.setEditable(false);
        panel = new JPanel();
        
        panel.setLayout(new GridLayout(6,2,1,5));
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel("<html>Totalt antall<br>registrerte kunder:</html>"));
        panel.add(antKunder);
        panel.add(new JLabel("<html>Totalt antall<br>tegnede forsikringer:</html>"));
        panel.add(antForsikringer);
        panel.add(new JLabel("<html>Mine kunders totale<br>erstatningsutgifter:</html>"));
        panel.add(utgifter);
        panel.add(new JLabel("<html>Total premieinntekt<br>fra mine kunder:</html>"));
        panel.add(inntekter);
        setLayout(new BorderLayout());
        add(velkommen, BorderLayout.PAGE_START);
        add(panel, BorderLayout.CENTER);
        fyllUt();
    }
    
    /*Metode for å fylle ut tekstfeltene med riktig info. Hvis den ansatte ikke
    har noen kunder på seg, så blir alle feltene 0*/
    public void fyllUt()
    {
        
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
    }
}
