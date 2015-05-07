/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import objekter.Kunde;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class KundeTabell extends JTable
{
    private final JPopupMenu popup;
    private final JMenuItem info;
    private final JMenu nyForsikring;
    private final JMenuItem nyBilforsikring;
    private final JMenuItem nyBåtforsikring;
    private final JMenuItem nyHusforsikring;
    private final JMenuItem nyFritidsboligforsikring;
    private final JMenuItem nyReiseforsikring;
    private final JMenuItem nySkademelding;
    private final AnsattVindu vindu;
    
    
    public KundeTabell( TabellModell modell, AnsattVindu v )
    {
        super( modell );
        vindu = v;
        setRowHeight(20);
        setAutoCreateRowSorter(true);
        
        MenyLytter menyLytter = new MenyLytter();
        popup = new JPopupMenu();
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
        
        popup.add(info);
        popup.add(nyForsikring);
        //popup.add(nySkademelding);
        
        addMouseListener(new MouseAdapter()
        {    
            @Override
            public void mousePressed(MouseEvent e)
            {
                sjekkKlikk(e);
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
                sjekkKlikk(e);
            }
        
            public void sjekkKlikk(MouseEvent e)
            {
                if (e.isPopupTrigger())
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
                        popup.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }   
        }); // end of anonym muselytter
    } // end of konstruktør

    
    public Kunde getKunde()
    {
        return vindu.getRegister().finnKundeMedPersonnummer((String)getValueAt(getSelectedRow(), 0));
    }
    
    private Component[] getKomponenter(Component pane)
     {
        ArrayList<Component> liste = null;

        try
        {
            liste = new ArrayList<Component>(Arrays.asList(
                  ((Container) pane).getComponents()));
            for (int i = 0; i < liste.size(); i++)
            {
            for (Component currentComponent : getKomponenter(liste.get(i)))
            {
                liste.add(currentComponent);
            }
            }
        } catch (ClassCastException e) {
            liste = new ArrayList<Component>();
        }

        return liste.toArray(new Component[liste.size()]);
        
    }
    
    private class MenyLytter implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if( e.getSource() == info )
            {
                Kunde kunde = getKunde();
                vindu.leggTilNyFane( new KundePanel(vindu,kunde), kunde.getEtternavn() );
            }
            else if( e.getSource() == nyBilforsikring )
            {
                Kunde kunde = getKunde();
                vindu.leggTilNyFane( new BilforsikringPanel(kunde, vindu), "Bilforsikring" );
            }
            else if( e.getSource() == nyBåtforsikring)
            {
                Kunde kunde = getKunde();
                vindu.leggTilNyFane(new BatforsikringPanel(kunde, vindu), "Båtforsikring");
            }
            else if( e.getSource() == nyHusforsikring)
            {
                Kunde kunde = getKunde();
                vindu.leggTilNyFane(new HusforsikringPanel(kunde, vindu), "Båtforsikring");
            }
            else if( e.getSource() == nyFritidsboligforsikring)
            {
                Kunde kunde = getKunde();
                vindu.leggTilNyFane(new FritidsboligforsikringPanel(kunde, vindu), "Båtforsikring");
            }
            else if( e.getSource() == nyReiseforsikring )
            {
                Kunde kunde = getKunde();
                vindu.leggTilNyFane(new ReiseforsikringPanel(kunde, vindu), "Båtforsikring");
            }
        }
    }
}
