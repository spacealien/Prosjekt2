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
 * @author Odd, Thomas, Marthe
 */
public class TabellModell extends AbstractTableModel
{
    private String[] kolonnenavn = { "Personnummer", "Fornavn", "Etternavn", "Kundenummer", "Adresse", "Telefonnummer"};
    private Object[][] innhold;
    private List<Kunde> kunder;
    
    
    public TabellModell( List<Kunde> kunder )
    {
        this.kunder = kunder;
        innhold = new Object[this.kunder.size()][kolonnenavn.length];
        
        int teller = 0;
        for( Kunde kunde : kunder )
        {
            innhold[teller][0] = kunde.getPersonnummer();
            innhold[teller][1] = kunde.getFornavn();
            innhold[teller][2] = kunde.getEtternavn();
            innhold[teller][3] = kunde.getKundenr();
            innhold[teller][4] = kunde.getAdresse();
            innhold[teller][5] = kunde.getTlfnr();
            teller++;
        }
    }
    
    @Override
    public String getColumnName( int i )
    {
        return kolonnenavn[i];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        return innhold[rowIndex][columnIndex];
    }

    @Override
    public int getRowCount() 
    {
        return kunder.size();
    }

    @Override
    public int getColumnCount() 
    {
        return innhold[0].length;
    }
}
