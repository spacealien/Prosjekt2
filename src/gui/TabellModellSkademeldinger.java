/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import objekter.Skademelding;

/**
 *
 * @author Odd
 */

public class TabellModellSkademeldinger extends AbstractTableModel implements ActionListener
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
            innhold[teller][2] = skademelding.getSkadeDato();
            innhold[teller][3] = skademelding.getOpprettetDato();
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

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }
}
