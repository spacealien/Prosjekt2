/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import objekter.Kunde;

/**
 *
 * @author Odd
 */
public class TabellModell extends AbstractTableModel
{
    private String[] kolonnenavn = { "Personnummer", "Fornavn", "Etternavn" };
    private Object[][] innhold;
    
    
    public TabellModell( List<Kunde> kunder )
    {
        innhold = new Object[kunder.size()][3];
        
        int teller = 0;
        for( Kunde kunde : kunder )
        {
            innhold[teller][0] = kunde.getPersonnummer();
            innhold[teller][1] = kunde.getFornavn();
            innhold[teller][2] = kunde.getEtternavn();
            teller++;
        }
    }
    
    @Override
    public String getColumnName( int n )
    {
        return kolonnenavn[n];
    }
    
    @Override
    public int getRowCount() 
    {
        return innhold.length;
    }

    @Override
    public int getColumnCount() 
    {
        return innhold[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        return innhold[rowIndex][columnIndex];
    }
}
