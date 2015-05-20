/**
 * Klassen brukes til å definere hvordan utsende og hvilke data som skal vises
 * i skademeldingstabellen kundepanelet.
 */


package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import objekter.Skademelding;

/*
 * Klassen brukes til å definere hvordan utsende og hvilke data som skal vises
 * i skademeldingstabellen kundepanelet.
 *
 * @author Odd, Marthe. Sist oppdatert 17.05.2015.
 */

public class TabellModellSkademeldinger extends AbstractTableModel
{
    private final String[] kolonnenavn = {"ID", "Forsikring", "Skadedato", "Opprettet Dato", "Takst", "Utbetalt"};
    private final Object[][] innhold;
    private final List<Skademelding> skademeldinger;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    
    public TabellModellSkademeldinger( List<Skademelding> skademeldinger)
    {
        this.skademeldinger = skademeldinger;
        innhold = new Object[this.skademeldinger.size()][kolonnenavn.length];
        
        
        int teller = 0;
        for(Skademelding skademelding : skademeldinger)
        {
            innhold[teller][0] = skademelding.getSkadenummer();
            innhold[teller][1] = skademelding.getForsikring().getForsikringsType();
            innhold[teller][2] = sdf.format(skademelding.getSkadeDato().getTime());
            innhold[teller][3] = sdf.format(skademelding.getOpprettetDato().getTime());
            innhold[teller][4] = skademelding.getTakseringsbelop();
            innhold[teller][5] = skademelding.getErstatningsbelop();
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
        return skademeldinger.size();
    }

    @Override
    public int getColumnCount() 
    {
        return kolonnenavn.length;
    }
}
