/*
 * Klassen brukes til å definere hvordan utsende og hvilke data som skal vises
 * i forsikrings tabelle i kundepanelet.
 */

package gui;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import objekter.Forsikring;

/**
 *
 * @author Odd
 */
public class TabellModellForsikring extends AbstractTableModel
{
    private String[] kolonnenavn = { "Forsikringsnummer", "Type", "Aktiv", "Dato", "Antall skademeldinger"};
    private Object[][] innhold;
    private List<Forsikring> forsikringer;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    
    public TabellModellForsikring( List<Forsikring> forsikringer)
    {
        this.forsikringer = forsikringer;
        innhold = new Object[this.forsikringer.size()][kolonnenavn.length];
        
        int teller = 0;
        for( Forsikring forsikring : forsikringer )
        {
            innhold[teller][0] = forsikring.getForsikringsnummer();
            innhold[teller][1] = forsikring.getForsikringsType();
            innhold[teller][2] = forsikring.erAktivTekst();
            innhold[teller][3] = sdf.format(forsikring.getStartdato().getTime());
            innhold[teller][4] = forsikring.getNøkkelliste().size();
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
        return forsikringer.size();
    }

    @Override
    public int getColumnCount() 
    {
        return kolonnenavn.length;
    }
}
