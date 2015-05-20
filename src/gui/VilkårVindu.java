/**
 *  Klassen beskriver vinduet som åpnes hver gang bruker av programmet 
 *  ønsker å lese en forsikring.
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
 *   Klassen beskriver vinduet som åpnes hver gang bruker av programmet 
 *  ønsker å lese en forsikring.
 * 
 * @author Odd. Sist endret 16.05.2015.
 */

public class VilkårVindu extends JFrame
{
    private final JTextArea utskrift;

    private JLabel overskrift;
    
    public VilkårVindu( String vinduTittel )
    {
        super(vinduTittel);
        setVisible(true);
        setSize(600,600);
        
        utskrift = new JTextArea();        
        utskrift.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(utskrift);
        
        overskrift = new JLabel("");
        JPanel menyPanel = new JPanel();
        menyPanel.add(overskrift);
        
        Container c =  getContentPane();
        c.setLayout( new BorderLayout() );
        c.add( menyPanel, BorderLayout.NORTH );
        c.add( scroll, BorderLayout.CENTER );
    }
    
    // endrer overskriftet i feltet over utskriftsområdet.
    public void setOverskrift( String tekst )
    {
        overskrift.setText(tekst);
    }
    
    // tar i mot vilkårene i form av en String og viser det i TextArea.
    public void visVilkår( String vilkår )
    {
        utskrift.setText(vilkår);
    }
    
    // sender vidre TextArea slik at man kan manipulere innholdet andre steder i programmet.
    public JTextArea getUtskriftområdet()
    {
        return utskrift;
    }
}
