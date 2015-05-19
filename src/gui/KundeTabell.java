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
import objekter.Kunde;

/**
 *
 * @author Odd, Marthe
 */
public class KundeTabell extends JTable implements ForsikringsPanel
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
    private final JMenuItem siOppKunde;
    private final AnsattVindu vindu;
    
    public KundeTabell( AnsattVindu v)
    {
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
        siOppKunde = new JMenuItem("Si opp Kunde");
        
        info.addActionListener(menyLytter);
        nyBilforsikring.addActionListener(menyLytter);
        nyBåtforsikring.addActionListener(menyLytter);
        nyHusforsikring.addActionListener(menyLytter);
        nyFritidsboligforsikring.addActionListener(menyLytter);
        nyReiseforsikring.addActionListener(menyLytter);
        nySkademelding.addActionListener(menyLytter);
        siOppKunde.addActionListener(menyLytter);
        
        nyForsikring.add(nyBilforsikring);
        nyForsikring.add(nyBåtforsikring);
        nyForsikring.add(nyHusforsikring);
        nyForsikring.add(nyFritidsboligforsikring);
        nyForsikring.add(nyReiseforsikring);
        
        popup.add(info);
        popup.add(nyForsikring);
        popup.add(siOppKunde);
        
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if( e.getClickCount() == 2)
                {
                    Kunde kunde = getKunde();
                    vindu.leggTilNyFane( new KundePanel(vindu,kunde), kunde.getEtternavn() );
                }
            }
            
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
    }
    

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
        siOppKunde = new JMenuItem("Si opp kunde");
        
        info.addActionListener(menyLytter);
        nyBilforsikring.addActionListener(menyLytter);
        nyBåtforsikring.addActionListener(menyLytter);
        nyHusforsikring.addActionListener(menyLytter);
        nyFritidsboligforsikring.addActionListener(menyLytter);
        nyReiseforsikring.addActionListener(menyLytter);
        nySkademelding.addActionListener(menyLytter);
        siOppKunde.addActionListener(menyLytter);
        
        nyForsikring.add(nyBilforsikring);
        nyForsikring.add(nyBåtforsikring);
        nyForsikring.add(nyHusforsikring);
        nyForsikring.add(nyFritidsboligforsikring);
        nyForsikring.add(nyReiseforsikring);
        
        popup.add(info);
        popup.add(nyForsikring);
        popup.add(siOppKunde);
        
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if( e.getClickCount() == 2)
                {
                    Kunde kunde = getKunde();
                    vindu.leggTilNyFane( new KundePanel(vindu,kunde), kunde.getEtternavn() );
                }
            }
            
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
                vindu.leggTilNyFane( new BilforsikringPanel(kunde, vindu), kunde.getEtternavn() + "-Bilforsikring" );
            }
            else if( e.getSource() == nyBåtforsikring)
            {
                Kunde kunde = getKunde();
                vindu.leggTilNyFane(new BatforsikringPanel(kunde, vindu), kunde.getEtternavn() + "-Båtforsikring");
            }
            else if( e.getSource() == nyHusforsikring)
            {
                Kunde kunde = getKunde();
                vindu.leggTilNyFane(new HusforsikringPanel(kunde, vindu), kunde.getEtternavn() + "-Husforsikring");
            }
            else if( e.getSource() == nyFritidsboligforsikring)
            {
                Kunde kunde = getKunde();
                vindu.leggTilNyFane(new FritidsboligforsikringPanel(kunde, vindu), kunde.getEtternavn() + "-Fritidsboligforsikring");
            }
            else if( e.getSource() == nyReiseforsikring )
            {
                Kunde kunde = getKunde();
                vindu.leggTilNyFane(new ReiseforsikringPanel(kunde, vindu), kunde.getEtternavn() + "-Reiseforsikring");
            }
            else if( e.getSource() == siOppKunde )
            {
                if(vindu.getRegister().siOppKundeforhold(vindu.getRegister().finnKundeMedPersonnummer((String)getValueAt(getSelectedRow(), 0))));
            }
        }
    }
}
