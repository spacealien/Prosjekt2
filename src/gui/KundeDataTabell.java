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
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import objekter.Forsikring;

/**
 *
 * @author Odd
 */
public class KundeDataTabell extends JTable 
{
    private AbstractTableModel model = null;
    private JPopupMenu popup;
    private final JPopupMenu popupSkademelding;
    private final JPopupMenu popupForsikring;
    private final JMenuItem nySkademelding;
    private final JMenuItem åpneSkademelding;
    private final JMenuItem åpneForsikring;
    private final JMenuItem deaktiverForsikring;
    private final JMenuItem visSkademeldingensForsikring;
    private final JMenuItem visSkademeldinger;
    private final KundePanel panel;
        
    public KundeDataTabell( AbstractTableModel model, KundePanel panel )
    {
        super(model);
        this.panel = panel;
        
        // popup for Forsikringer
        popupForsikring = new JPopupMenu();
        åpneForsikring = new JMenuItem("Åpne");
        deaktiverForsikring = new JMenuItem("Deaktiver");
        visSkademeldinger = new JMenuItem("Vis Skademeldinger");
        nySkademelding = new JMenuItem("Ny Skademelding");
        popupForsikring.add(åpneForsikring);
        popupForsikring.add(deaktiverForsikring);
        popupForsikring.add(nySkademelding);
        popupForsikring.add(visSkademeldinger);
        
        // popup for Skademeldinger
        popupSkademelding = new JPopupMenu();
        åpneSkademelding = new JMenuItem("Åpne");
        visSkademeldingensForsikring = new JMenuItem("Vis skademeldingens forsikring");
        popupSkademelding.add(åpneSkademelding);
        popupSkademelding.add(visSkademeldingensForsikring);
        
        MenyLytter menyLytter = new MenyLytter();
        åpneForsikring.addActionListener(menyLytter);
        deaktiverForsikring.addActionListener(menyLytter);
        åpneSkademelding.addActionListener(menyLytter);
        visSkademeldinger.addActionListener(menyLytter);
        nySkademelding.addActionListener(menyLytter);
        visSkademeldingensForsikring.addActionListener(menyLytter);
        
        brukForsikringsPopup();
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
        });
    } // slutt på konstuktør
    
    public void brukForsikringsPopup()
    {
         popup = popupForsikring;
    }
    
    public void brukSkademeldingPopup()
    {
         popup = popupSkademelding;
    }
    
    private class MenyLytter implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if( e.getSource() == åpneSkademelding)
            {
                panel.åpneForsikringsTab();
            }
            else if( e.getSource() == åpneForsikring )
            {
                panel.åpneForsikringsTab();
            }
            else if( e.getSource() == deaktiverForsikring )
            {
                panel.deaktiverForsikring();
            }
            else if( e.getSource() == visSkademeldinger)
            {
                panel.visForsikringensSkademeldnger();
            }
            else if( e.getSource() == nySkademelding )
            {
                panel.visNySkademeldingsTab();
            }
        }
    }
}
