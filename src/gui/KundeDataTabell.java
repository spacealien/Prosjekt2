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
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Odd
 */
public class KundeDataTabell extends JTable 
{
    private AbstractTableModel model = null;
    
    
    private final JPopupMenu popupSkademelding;
    private final JPopupMenu popupForsikring;
    private final JMenuItem info;
    private final JMenuItem infoSkade;
    private final JMenuItem visSkademeldinger;
    private final KundePanel panel;
        
    public KundeDataTabell( AbstractTableModel model, KundePanel panel )
    {
        super(model);
        this.panel = panel;

        
        
        info = new JMenuItem("Åpne");
        infoSkade = new JMenuItem("Jeg hører til skademeldingtabell");
        visSkademeldinger = new JMenuItem("Vis Skademeldinger for denne forsikring");
        popupForsikring = new JPopupMenu();
        popupSkademelding = new JPopupMenu();
        popupForsikring.add(info);
        popupForsikring.add(visSkademeldinger);
        popupSkademelding.add(infoSkade);
        
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
            { System.out.println(model);
                if( model instanceof TabellModellForsikring )
                {
                    popupForsikring.show(e.getComponent(), e.getX(), e.getY());
                }
                else if( model instanceof TabellModellSkademeldinger )
                {
                    popupSkademelding.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        }
    }
        });
        
        MenyLytter menyLytter = new MenyLytter();
        info.addActionListener(menyLytter);
        visSkademeldinger.addActionListener(menyLytter);
        infoSkade.addActionListener(menyLytter);
    } // slutt på konstuktør

    
    private class MenyLytter implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if( e.getSource() == info)
            {
                
            }
            else if( e.getSource() == visSkademeldinger)
            {
                panel.visForsikringensSkademeldng();
            }
        }
    }
}
