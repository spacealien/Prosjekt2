/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import objekter.Kunde;

/**
 *
 * @author Odd
 */
public class KundePanel extends JPanel implements ActionListener
{
    private final JPanel kundeInfo_1;
    private final JPanel kundeInfo_2;
    private final JTextField regFornavn;
    private final JTextField regEtternavn;
    private final JTextField regPersnr;
    private final JTextField regTlfnr;
    private final JTextField regAdresse;
    private final JTextField regEpost;
    private final JTextField aktivForsikringFelt;
    private final JTextField antallSkademeldingFelt;
    private final JTextField kundenummerFelt;
    private final JTextField tidligereForsikringerFelt;
    private final JButton regKunde;
    private final AnsattVindu vindu;

    
    
    public KundePanel( AnsattVindu vindu, boolean nyregistrering )
    {
        this.vindu = vindu;
        kundeInfo_1 = new JPanel();
        kundeInfo_2 = new JPanel();
        regFornavn = new JTextField( 15 );
        regEtternavn = new JTextField( 15 );
        regPersnr = new JTextField( 11 );
        regTlfnr = new JTextField( 8 );
        regAdresse = new JTextField( 15 );
        regEpost = new JTextField(20);
        regKunde = new JButton("Registrer kunde" );
        aktivForsikringFelt = new JTextField(3);
        antallSkademeldingFelt = new JTextField(3);
        kundenummerFelt = new JTextField(3);
        tidligereForsikringerFelt = new JTextField(3);
        
        kundeInfo_1.setLayout(new GridLayout(6,2,5,10));
        kundeInfo_1.add(new JLabel("Fornavn: "));
        kundeInfo_1.add(regFornavn);
        kundeInfo_1.add(new JLabel("Etternavn: "));
        kundeInfo_1.add(regEtternavn);
        kundeInfo_1.add(new JLabel("Personnummer: "));
        kundeInfo_1.add(regPersnr);
        kundeInfo_1.add(new JLabel("Telefonnummer: "));
        kundeInfo_1.add(regTlfnr);
        kundeInfo_1.add(new JLabel("Fakturaadresse: "));
        kundeInfo_1.add(regAdresse);
        kundeInfo_1.add( new JLabel("Epost: "));
        kundeInfo_1.add(regEpost);
        
        kundeInfo_2.setLayout( new GridLayout(6,2,5,10) );
        kundeInfo_2.add( new JLabel("Aktive forsikringer: "));
        kundeInfo_2.add(aktivForsikringFelt);
        kundeInfo_2.add( new JLabel("Antall skademeldinger"));
        kundeInfo_2.add( antallSkademeldingFelt );
        kundeInfo_2.add( new JLabel("Kundenummer: "));
        kundeInfo_2.add( kundenummerFelt );
        kundeInfo_2.add( new JLabel("Tidligere forhold"));
        kundeInfo_2.add( tidligereForsikringerFelt );
        kundeInfo_2.add( new JLabel());
        kundeInfo_2.add( new JLabel());
        kundeInfo_2.add( new JLabel());
        kundeInfo_2.add( new JLabel());
        
        regKunde.addActionListener(this);
        
        if(nyregistrering)
        {
            byggNyKundePanel();
        } else {
            byggKundePanel();
        }
        this.setBackground(Color.green);
    }
    
    public void setKunde( Kunde kunde )
    {
        regFornavn.setText(kunde.getFornavn());
        regEtternavn.setText(kunde.getEtternavn());
        regPersnr.setText(kunde.getPersonnummer());
    }
    
    public void byggKundePanel()
    {
        this.add(kundeInfo_1);
        this.add(kundeInfo_2);

    }
    
    public void byggNyKundePanel()
    {
        this.setLayout( new BorderLayout() );
        this.add(kundeInfo_1, BorderLayout.CENTER);
        this.add(regKunde, BorderLayout.SOUTH);
    }
    
    public Kunde registrerNyKunde()
    {
        String nyKundeFornavn = regFornavn.getText();
        String nyKundeEtternavn = regEtternavn.getText();
        String nyKundePersonnummer = regPersnr.getText();
        String nyKundeTelefonnummer =  regTlfnr.getText();
        String nyKundeAdresse = regAdresse.getText();
        String nyKundeEpost = regEpost.getText();
        int fødselsÅr = Integer.parseInt(nyKundePersonnummer.substring(4,6));
        int fødselsMnd =  Integer.parseInt(nyKundePersonnummer.substring(3,5));
        int fødselsDato = Integer.parseInt(nyKundePersonnummer.substring(0,3));
        
        Calendar fødselsdato = Calendar.getInstance();
        fødselsdato.set(fødselsÅr, fødselsMnd, fødselsDato);
        
        return new Kunde(nyKundeFornavn,nyKundeEtternavn,nyKundeAdresse,nyKundeTelefonnummer,fødselsdato,nyKundeEpost,nyKundePersonnummer);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if( e.getSource() == regKunde )
        {
            vindu.visForsikringsVindu();
        }
    }
}
