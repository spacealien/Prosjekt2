
package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Klassens hensikt er å vise bildet til skademeldingen i et eget vindu.
 * @author Odd, Marthe. Sist endret 19.20.2015
 */
public class BildeVindu extends JFrame
{
    private Image[] bilder;
    private int peker = 0;
    private final JButton neste = new JButton("Neste");
    private final JButton forrige = new JButton("Forrige");
    private final int BILDE_HØYDE = 700;
    
    
    public BildeVindu( Image[] photo)
    {
        super("Bilder");
        setVisible(true);
        setSize(800,800);
        bilder = photo;
        
        JPanel meny = new JPanel();
        meny.add(forrige);
        meny.add(neste);
        
        
        KnappeLytter knappeLytter = new KnappeLytter();
        neste.addActionListener(knappeLytter);
        forrige.addActionListener(knappeLytter);
        
        try
        {
            int teller = 0;
            for(Image img : photo )
            {
                bilder[teller++] = img.getScaledInstance(BILDE_HØYDE, -1, Image.SCALE_FAST);  
            }
        }
        catch( NullPointerException e )
        {
            JOptionPane.showMessageDialog(null, "Noe gikk galt under visning av fil", "Feilmelding", JOptionPane.ERROR_MESSAGE);
        }

        Container c = getContentPane();
        c.setLayout( new BorderLayout() );
        c.add( new BildePanel() , BorderLayout.CENTER);
        c.add( meny, BorderLayout.PAGE_END );
    }
    
    // oppdaterer vinduet etter at brukeren har trykket neste/forrige.
    public void oppdaterVindu()
    {
        revalidate();
        repaint();
    }
    
    // Panelet som inneholder metoder for å tegne bildet.
    private class BildePanel extends JPanel
    {   
        public void visBilde(Graphics g)
        {    
            g.drawImage(bilder[peker], 0, 0, this);
        }
    
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            visBilde(g);
        }  
    }
    
    // Knappelytter
    private class KnappeLytter implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if( e.getSource() == neste && bilder.length > peker + 1)
            {
                peker++;
                oppdaterVindu();
            }
            else if(e.getSource() == forrige && peker > 0)
            {
                peker--;
                oppdaterVindu();
            }
        }
        
    }
}
