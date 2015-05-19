/**
 * Klassen brukes til å definere hvordan utsende og hvilke data som skal vises
 * i KundeTabellen neders i hovedvinduet.
 * 
 * 
 */



package gui;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import objekter.Kunde;

/**
 *
 * @author Odd, Marthe
 */
public class TabellModell extends AbstractTableModel
{
    private String[] kolonnenavn = { "Personnummer", "Fornavn", "Etternavn", "Adresse", "Telefonnummer", "Opprettet","Fødselsdato", "Totalkunde", "Aktiv"};
    private Object[][] innhold;
    private List<Kunde> kunder;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    
    
    public TabellModell( List<Kunde> kundene )
    {
        this.kunder = kundene;
        innhold = new Object[kunder.size()][kolonnenavn.length];
        
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
            innhold[teller][7] = kunde.erTotalkundeTekst();
            innhold[teller][8] = kunde.erKunde();
            teller++;
        }
    }
    
    @Override
    public String getColumnName( int i )
    {
        return kolonnenavn[i];
    }
    
    
    // returnerer inneholdet i en gitt celle.
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
