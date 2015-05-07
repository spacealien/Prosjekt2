/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import objekter.*;
import register.*;

/**
 *
 * @author Marthejansonskogen
 */
public class SkademeldingPanel extends JPanel implements ActionListener
{
    private final AnsattVindu vindu;
    private final HovedRegister register;
    private final JTextField skadeDato;
    private final JTextField skadeType;
    private final JTextArea skadeBeskrivelse;
    private final JTextField skadeTakst;
    private final JTextField skadeForsikring;
    private final JButton sendInnSkade;
    private final JButton lastOppBildeKnapp;
    private final Kunde kunde;
    private final Forsikring forsikring;
    private SimpleDateFormat sdf;
    
    private Date dato;
    private String type;
    private String beskrivelse;
    private int takst;
    private int belop;
    
    public SkademeldingPanel( Forsikring f, AnsattVindu v)
    {
        sdf = new SimpleDateFormat("ddMMyyyy");
        kunde = f.getKunde();
        forsikring = f;
        vindu = v;
        register = vindu.getRegister();
        
        
        
        skadeDato = new JTextField( 7 );
        skadeType = new JTextField( 7 );
        skadeBeskrivelse = new JTextArea( 20, 30);
        skadeTakst = new JTextField( 7 );
        sendInnSkade = new JButton("Send inn skade");  
        lastOppBildeKnapp = new JButton("Last Opp Bilde");
        skadeForsikring = new JTextField(16);
        
        JPanel wrapper_1 = new JPanel();
        GridLayout layout_1 = new GridLayout(2,4);
        layout_1.setHgap(6);
        layout_1.setVgap(6);
        wrapper_1.setLayout(  layout_1 );        
        wrapper_1.add( new JLabel("Skadens Dato: "));
        wrapper_1.add( skadeDato );
        wrapper_1.add( new JLabel("Skadens forsikring: "));
        wrapper_1.add( skadeForsikring );
        wrapper_1.add( new JLabel("Skadetype: "));
        wrapper_1.add( skadeType );
        wrapper_1.add( new JLabel("Skadens Takst: "));
        wrapper_1.add( skadeTakst );
        
        JPanel wrapper_2 = new JPanel();
        wrapper_2.setLayout( new BorderLayout());
        wrapper_2.add( new JLabel("Beskrivelse av Skaden: "), BorderLayout.PAGE_START);
        skadeBeskrivelse.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(skadeBeskrivelse);
        wrapper_2.add( scroll, BorderLayout.CENTER );
        
        JPanel wrapper_3 = new JPanel();
        wrapper_3.setLayout( new FlowLayout() );
        wrapper_3.add(sendInnSkade);
        wrapper_3.add(lastOppBildeKnapp);
        
        this.setLayout( new BorderLayout());
        add(wrapper_1, BorderLayout.PAGE_START);
        add(wrapper_2, BorderLayout.CENTER);
        add(wrapper_3, BorderLayout.PAGE_END);
    }
    
    public void visSkademelding( Skademelding skademelding )
    {
        
    }
    
    public boolean hentInfo()
    {
        //int sd = Integer.parseInt(skadeDato.getText());
        String sd = skadeDato.getText();
        try
        {
            dato = sdf.parse(sd);
            /*int sdar = Integer.parseInt(sd.substring(4,8));
            int sdmnd = Integer.parseInt(sd.substring(2,4));
            int sddag = Integer.parseInt(sd.substring(0,2));
            Date skadedatoen = new Date((sdar-1900), sdmnd, sddag);*/
            type = skadeType.getText();
            beskrivelse = skadeBeskrivelse.getText();
            takst = Integer.parseInt(skadeTakst.getText());
            return true;
        } 
        catch (ParseException e)
        {
            JOptionPane.showMessageDialog(null, "Vennligst skriv inn datoen i følgende format: ddmmåååå.", "Feilmelding", JOptionPane.ERROR_MESSAGE);
            return false;
	}
    }
    
    public void beregnPris()
    {
        //Beregner pris
        //belop =;
    }
    
    public void nySkademelding()
    {
        if (hentInfo())
        register.nySkademelding(forsikring, dato, type, beskrivelse, takst, belop );
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == sendInnSkade)
        {
            nySkademelding();
        }
    }
}
