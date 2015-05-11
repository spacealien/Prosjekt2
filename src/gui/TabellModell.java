/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import objekter.Kunde;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class TabellModell extends AbstractTableModel
{
    private String[] kolonnenavn = { "Personnummer", "Fornavn", "Etternavn", "Adresse", "Telefonnummer", "Opprettet","fødselsdato"};
    private Object[][] innhold;
    private List<Kunde> kunder;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    
    
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
            innhold[teller][3] = kunde.getAdresse();
            innhold[teller][4] = kunde.getTlfnr();
            innhold[teller][5] = sdf.format(kunde.getStartdato());
            innhold[teller][6] = sdf.format(kunde.getFodtdato());
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
        return kolonnenavn.length;
    }
}
