/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import objekter.Kunde;

/**
 *
 * @author Odd
 */
public class NyKundePanel extends JPanel implements ActionListener
{
    private final AnsattVindu vindu;
    private final JPanel kundeInfo_1;
    private final JPanel knappeWrapper;
    private final JTextField regFornavn;
    private final JTextField regEtternavn;
    private final JTextField regPersnr;
    private final JTextField regTlfnr;
    private final JTextField regAdresse;
    private final JTextField regEpost;
    private final JTextField regFødselsår;
    private final JButton regKunde;
    private final String[] forsikringsvalg = {"", "Bilforsikring", "Båtforsikring", "Husforsikring", "Fritidsboligforsikring", "Reiseforsikring"};
    private final JComboBox<String> forsikringsDropDown = new JComboBox<>(forsikringsvalg);
    
    
    
    public NyKundePanel( AnsattVindu vindu)
    {
        this.vindu = vindu;
        kundeInfo_1 = new JPanel();
        knappeWrapper = new JPanel();
        regFornavn = new JTextField( 15 );
        regEtternavn = new JTextField( 15 );
        regPersnr = new JTextField( 11 );
        regTlfnr = new JTextField( 8 );
        regAdresse = new JTextField( 15 );
        regEpost = new JTextField(20);
        regFødselsår = new JTextField(15);
        regKunde = new JButton("Videre" );
        kundeInfo_1.setLayout(new GridLayout(7,2,5,10));
        kundeInfo_1.add(new JLabel("Fornavn: "));
        kundeInfo_1.add(regFornavn);
        kundeInfo_1.add(new JLabel("Etternavn: "));
        kundeInfo_1.add(regEtternavn);
        kundeInfo_1.add(new JLabel("Personnummer: "));
        kundeInfo_1.add(regPersnr);
        kundeInfo_1.add( new JLabel("Fødselsår: "));
        kundeInfo_1.add(regFødselsår);
        kundeInfo_1.add(new JLabel("Telefonnummer: "));
        kundeInfo_1.add(regTlfnr);
        kundeInfo_1.add( new JLabel("Epost: "));
        kundeInfo_1.add(regEpost);
        kundeInfo_1.add(new JLabel("Fakturaadresse: "));
        kundeInfo_1.add(regAdresse);
        
        setLayout( new BorderLayout() );
        knappeWrapper.setLayout( new FlowLayout() );
        knappeWrapper.add( new JLabel("Velg Forsikringstype: "));
        knappeWrapper.add(forsikringsDropDown);
        knappeWrapper.add(regKunde);
        add(kundeInfo_1, BorderLayout.CENTER );
        add(knappeWrapper, BorderLayout.SOUTH );
        
        regKunde.addActionListener(this);
    }
    
    /**
     * Henter kunde informajson fra textfeltene og validerer informasjonen.
     * @return Kunde eller null hvis kundeinformasjonen oppfyller kravene. 
     */
    
    public Kunde nyKunde()
    {
        try
        { 
            String fornavn = regFornavn.getText();
            String etternavn = regEtternavn.getText();
            String adresse = regAdresse.getText();
            String telefonnummer = regTlfnr.getText();
            String epost = regEpost.getText();
            String personnummer = regPersnr.getText();
            String fødselsår = regFødselsår.getText();
            
            if( fornavn.matches("\\D") && etternavn.matches("\\D") && telefonnummer.matches("\\d{8}") 
                    && epost.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") 
                    && personnummer.matches("\\d{11}") && fødselsår.matches("\\d{4}") )
            {
                if(!fødselsår.endsWith(personnummer.substring(4, 6)))
                {
                    vindu.visInformasjon("Beskjed", "Fødselsår matcher ikke fødselsnummer. Prøv igjen");
                    return null;
                }
                
                int dato = Integer.parseInt(personnummer.substring(0, 2));
                int måned = Integer.parseInt(personnummer.substring(2, 4));
                int år = Integer.parseInt(fødselsår); 
                GregorianCalendar fødselsdato = new GregorianCalendar(år, måned, dato);
                
                Kunde kunde = new Kunde( fornavn, etternavn, adresse, telefonnummer,
                                        fødselsdato, epost, personnummer );
                vindu.visInformasjon("Beskjed", "Ny kunde, " + fornavn + " " + etternavn + ","
                        + " legges inn i kunderegisteret etter det blir tegnet en forsikring på kunden");
                return kunde;
            }
            else
            {
                vindu.visInformasjon("Beskjed", "Skjekk at alle feltene er korrekte.");
                return null;
            }
        }
        catch( NumberFormatException e)
        {
            vindu.visInformasjon("Beskjed", "Feil format i et av feltene. ");
        }
        return null;
    }
    
    /**
     * Sender den nye vidre til et forsikringspanel av gitt type.
     * @param e 
     */
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
            Kunde nyKunde = nyKunde();
            String valg = (String) forsikringsDropDown.getSelectedItem();
            
            if(nyKunde != null)
                if( valg.equals(""))
                    vindu.visFeilmelding("Melding", "Du må velge en type forsikring for å gå videre. ");
                else if( valg.equals("Bilforsikring") )
                    vindu.leggTilNyFane( new BilforsikringPanel(nyKunde, vindu), "Ny Bilforsikring");
                else if( valg.equals("Båtforsikring"))
                    vindu.leggTilNyFane( new BatforsikringPanel(nyKunde, vindu), "Ny Båtforsikring");
                else if( valg.equals("Husforsikring"))
                    vindu.leggTilNyFane( new HusforsikringPanel(nyKunde, vindu), "Ny Husforsikring");
                else if( valg.equals("Fritidsboligforsikring"))
                    vindu.leggTilNyFane( new FritidsboligforsikringPanel(nyKunde, vindu), "Ny Fritidsboligforsikring");
                else if( valg.equals("Reiseforsikring"))
                    vindu.leggTilNyFane( new ReiseforsikringPanel(nyKunde, vindu), "Ny Reiseforsikring"); 
    }
}
