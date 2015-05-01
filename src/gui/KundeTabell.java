/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class KundeTabell extends JTable
{
    private final JMenuItem info;
    private final JMenu nyForsikring;
    private final JMenuItem nyBilforsikring;
    private final JMenuItem nyBåtforsikring;
    private final JMenuItem nyHusforsikring;
    private final JMenuItem nyFritidsboligforsikring;
    private final JMenuItem nyReiseforsikring;
    private final JMenuItem nySkademelding;
    private final AnsattVindu vindu;
    
    
    public KundeTabell( TabellModell modell, AnsattVindu vindu )
    {
        super( modell );
        this.vindu = vindu;
        setRowHeight(20);
        setAutoCreateRowSorter(true);
        
        Lytter menyLytter = new Lytter();
        info = new JMenuItem("Vis informasjon");
        nyForsikring = new JMenu("Ny forsikring");
        nyBilforsikring = new JMenuItem("Bilforsikring");
        nyBåtforsikring = new JMenuItem("Båtforsikring");
        nyHusforsikring = new JMenuItem("Husforsikring");
        nyFritidsboligforsikring = new JMenuItem("Fritidsboligforsikring");
        nyReiseforsikring = new JMenuItem("Reiseforsikring");
        nySkademelding = new JMenuItem("Ny Skademelding");
        
        info.addActionListener(menyLytter);
        nyBilforsikring.addActionListener(menyLytter);
        nyBåtforsikring.addActionListener(menyLytter);
        nyHusforsikring.addActionListener(menyLytter);
        nyFritidsboligforsikring.addActionListener(menyLytter);
        nyReiseforsikring.addActionListener(menyLytter);
        nySkademelding.addActionListener(menyLytter);
        
        
        
        nyForsikring.add(nyBilforsikring);
        nyForsikring.add(nyBåtforsikring);
        nyForsikring.add(nyHusforsikring);
        nyForsikring.add(nyFritidsboligforsikring);
        nyForsikring.add(nyReiseforsikring);

        addMouseListener(new MouseAdapter() {
         @Override
        public void mouseReleased(MouseEvent e) 
        {
            int r = rowAtPoint(e.getPoint());
            if (r >= 0 && r < getRowCount()) 
            {
                setRowSelectionInterval(r, r);
            }
            else 
            {
                clearSelection();
            }
            
            int rowindex = getSelectedRow();
            if (rowindex < 0)
                return;
            if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) 
            {
                JPopupMenu popup = new JPopupMenu();
                popup.add(info);
                popup.add(nyForsikring);
                popup.add(nySkademelding);
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }); // end of anonym muselytter
    } // end of konstruktør
    
    private class Lytter implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("Jeg trykket på en knapp");
        }
    }
}
