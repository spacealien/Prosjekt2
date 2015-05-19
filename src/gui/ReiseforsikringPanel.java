/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

//Nødvendige import-setninger
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import objekter.*;
import register.*;

/**
 *
 * @author Odd, Marthe
 */

/*Klassens hensikt er å designe brukergrensesnittet for reiseforsikringer, 
ta imot input fra brukeren og videre registrere en reiseforsikring
hvis alle feltene er korrekt skrevet inn. Klassen kan også vise informasjon om
en allerede tegnet reiseforsikring, og endre denne.*/
public class ReiseforsikringPanel extends JPanel implements ActionListener, ForsikringsPanel, VinduVerktoy
{
    private final AnsattVindu vindu;
    private final HovedRegister register;
    private Reiseforsikring forsikring;
    private KundePanel kundePanel;
    
    private final JTextField reiseBelop;
    private final JTextField reiseTilbud;
    private final JTextField antbarn;
    private final JLabel antbarnLabel;
    private final JRadioButton forsorgerJa;
    private final JRadioButton forsorgerNei;
    private final JButton reiseGiTilbud;
    private final JButton beregnPris;
    private final JButton vilkarKnapp;
    private final String[] sone = {"", "Norden", "Europa", "Verden"};
    private final JComboBox<String> sonevelger;
    private final String[] egenandel = {"", "2000", "4000", "8000", "12000", "16000", "20000", "30000"};
    private final JComboBox<String> egenandelsvelger;
    private final String[] dekning = {"", "Reise", "Reise-Pluss"};
    private final JComboBox<String> dekningvelger;
    private final Kunde kunde;
    
    private double foreslåttPris; 
    private String vilkår;
    private int antBarn;
    private int belop;
    private boolean forsorger_b;
    private int sone_n;
    private String sonevalget;
    private int egenandelvalget;
    private String dekningvalget;
    private JButton rediger;
    private JButton lagreNyInfo;
    private JButton deaktiver;
    private JPanel knappePanel = new JPanel();
    private JLabel tilbudLabel;
    
    public ReiseforsikringPanel(Kunde k, AnsattVindu v)
    {
        vindu = v;
        register = vindu.getRegister();
        kunde = k;
        //forsikringsPanel implements ForsikringsPanel();
        reiseBelop = new JTextField( 7 );
        reiseTilbud = new JTextField( 7 );
        antbarn = new JTextField(2);
        tilbudLabel = new JLabel("Foreslått tilbud: ");
        antbarnLabel = new JLabel("Forsørger antall barn: ");
        antbarn.setEnabled(false);
        antbarnLabel.setEnabled(false);
        reiseGiTilbud = new JButton("Tegn forsikring");
        reiseGiTilbud.setVisible(true);
        beregnPris = new JButton("Beregn pris");
        vilkarKnapp = new JButton("Se vilkår");
        sonevelger = new JComboBox<>(sone);
        egenandelsvelger = new JComboBox<>(egenandel);
        dekningvelger = new JComboBox<>(dekning);
        forsorgerJa = new JRadioButton("Ja");
        forsorgerJa.setMnemonic(KeyEvent.VK_J);
        forsorgerNei = new JRadioButton("Nei");
        forsorgerNei.setMnemonic(KeyEvent.VK_N);
        ButtonGroup forsorger = new ButtonGroup();
        forsorger.add(forsorgerJa);
        forsorger.add(forsorgerNei);  
        rediger = new JButton("Rediger forsikring");
        lagreNyInfo = new JButton("Lagre forsikring");
        deaktiver = new JButton("Si opp forsikring");
        rediger.setVisible(false);
        deaktiver.setVisible(false);
        
        JPanel tegnReisePanel1 = new JPanel();
        JPanel forsorgerP = new JPanel();
        tegnReisePanel1.setLayout(new GridLayout(6,4,1,5));
        forsorgerP.add(forsorgerJa);
        forsorgerP.add(forsorgerNei);
        tegnReisePanel1.add(new JLabel("Er kunde forsørger? "));
        tegnReisePanel1.add(forsorgerP);
        tegnReisePanel1.add(antbarnLabel);
        tegnReisePanel1.add(antbarn);
        tegnReisePanel1.add(new JLabel("Forsikringssone: "));
        tegnReisePanel1.add(sonevelger);
        tegnReisePanel1.add(new JLabel());
        tegnReisePanel1.add(vilkarKnapp);
        tegnReisePanel1.add(new JLabel("Egenandel: "));
        tegnReisePanel1.add(egenandelsvelger);
        tegnReisePanel1.add(new JLabel("Velg dekning: "));
        tegnReisePanel1.add(dekningvelger);
        tegnReisePanel1.add(new JLabel("Forsikringsbeløp: "));
        tegnReisePanel1.add(reiseBelop);
        tegnReisePanel1.add(new JLabel());
        tegnReisePanel1.add(beregnPris);
        tegnReisePanel1.add(tilbudLabel);
        tegnReisePanel1.add(reiseTilbud);
        tegnReisePanel1.add(new JLabel());
        tegnReisePanel1.add(reiseGiTilbud);
        knappePanel.setLayout(new BoxLayout(knappePanel, BoxLayout.PAGE_AXIS));
        knappePanel.add(rediger);
        knappePanel.add(deaktiver);
        add(tegnReisePanel1);
        add(knappePanel);
        
        VilkårLytter vilkårLytter = new VilkårLytter();
        reiseGiTilbud.addActionListener(this);
        beregnPris.addActionListener(this);
        vilkarKnapp.addActionListener(this);
        rediger.addActionListener(this);
        lagreNyInfo.addActionListener(this);
        deaktiver.addActionListener(this);
        dekningvelger.addItemListener(vilkårLytter);
        
        forsorgerJa.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                if (e.getStateChange() == ItemEvent.SELECTED)
            {
            antbarn.setEnabled(true);
            antbarnLabel.setEnabled(true);
        }
        else if (e.getStateChange() == ItemEvent.DESELECTED)
        {
            antbarn.setEnabled(false);
            antbarnLabel.setEnabled(false);
        } 
        }});
    }
    
    /*Metode for å vise en allerede tegnet reiseforsikring. Tar imot forsikringen
    som parameter.*/
    @Override
    public void visForsikring( Forsikring f)
    {
        this.forsikring = (Reiseforsikring)f;
        sonevelger.setSelectedItem(forsikring.getSone());
        reiseBelop.setText(String.valueOf(forsikring.getBelopet()));
        egenandelsvelger.setSelectedItem(String.valueOf(forsikring.getEgenandel()));
        dekningvelger.setSelectedItem(forsikring.getVilkar());
        reiseGiTilbud.setVisible(false);
        
        if (forsikring.isForsorger())
        {
            forsorgerJa.setSelected(true);
            antbarn.setText(String.valueOf(forsikring.getAntBarn()));
        }
        else
            forsorgerNei.setSelected(true);
        
        if (forsikring.erAktiv())
        {
            rediger.setVisible(true);
            deaktiver.setVisible(true);
        }
        tilbudLabel.setText("Årlig premie: (Kr/År)");
        tilbudLabel.setVisible(true);
        reiseTilbud.setVisible(true);
        disableFelter( this, reiseGiTilbud, beregnPris );
        revalidate();
        repaint();
    }
    
    /*Hvis brukeren trykket seg videre til å denne forsikringen via et kundepanel,
    så setter denne metoden kundepanelet som var utgangspunktet via paramtereren.
    Denne er nødvendig for å få oppdatert kundepanelet til kunden som forsikringen
    hører til, når det blir gjort endring på en forsikring eller når det blir
    tegnet en ny forsikring.*/
    @Override
    public void leggTilKundePanel( KundePanel panel )
    {
        kundePanel = panel;
    }
    
    /*Metoden henter input fra brukeren. Den sjekker at alle feltene er korrekt
    fylt ut, hvis ikke kommer det opp en passende feilmelding.*/
    @Override
    public boolean hentInfo()
    {
        sone_n = sonevelger.getSelectedIndex();
        int egenandel_n = egenandelsvelger.getSelectedIndex();
        int dekning_n = dekningvelger.getSelectedIndex();
                
                
        if(egenandel_n == 0 || sone_n == 0 || dekning_n == 0 || (!forsorgerJa.isSelected() && !forsorgerNei.isSelected()) )
        {
            String ut = "Det mangler informasjon om:\n";
            if (sone_n == 0)
            {
                ut += "Sone\n";
            }
            if (egenandel_n == 0)
            {
                ut += "Egenandel\n";
            }
            if (dekning_n == 0)
            {
                ut += "Dekning\n";
            }
            if (!forsorgerJa.isSelected() && !forsorgerNei.isSelected())
            {
                ut += "Forsørgervalg\n";
            }
            ut += "\nVennligst fyll ut denne informasjonen og prøv igjen.";
                          JOptionPane.showMessageDialog(null, ut, "Feilmelding",
                                                JOptionPane.ERROR_MESSAGE);
            return false;
            }
            else
            {
                try
                {
                    if (forsorgerJa.isSelected() && !forsorgerNei.isSelected())
                    {
                        forsorger_b = true;
                        antBarn = Integer.parseInt(antbarn.getText());
                    }
                    else if (!forsorgerJa.isSelected() && forsorgerNei.isSelected())
                    {
                        forsorger_b = false;
                        antBarn = 0;
                    }
                    
                    egenandelvalget = Integer.parseInt(egenandelsvelger.getItemAt(egenandel_n));
                    sonevalget = sonevelger.getItemAt(sone_n);
                    dekningvalget = dekningvelger.getItemAt(dekning_n);
                    belop = Integer.parseInt( reiseBelop.getText() );
                    return true;
                }
                catch( NumberFormatException e )
                {
                    vindu.visFeilmelding("Feilmelding", "Feil format i et av tekstfeltene. ");
                    return false;
                }
            }
    }
    
    /*Metode for å beregne pris på forhånd, og skrive ut et prisforslag til brukeren.
    Hvis all info er korrekt skrevet inn, henter den info fra inputfeltene, og gjør
    kalkuleringer for å beregne pris på en eventuell forsikring med de dataene i 
    inputfeltene. Deretter vises knappen "Tegn forsikring" og det blir mulig å
    registrere forsikringen.*/
    @Override
    public void beregnPris()
    {
        if (hentInfo())
        {
            double foreslåttPris = ForsikringsKalulator.beregnReiseforsikring(egenandelvalget, dekningvalget, forsorger_b , antBarn, sonevalget, belop );
            reiseTilbud.setVisible(true);
            reiseTilbud.setText(String.valueOf(foreslåttPris));
            reiseTilbud.setToolTipText("Kan redigeres");
        }
    }
    
    /*Metode for å registrere et nytt hus- og innboforsikringsobjekt og legge
    dette inn i registeret. Oppdaterer også den eventuelle kundefanen som
    forsikringen hører til.*/ 
    @Override
    public void tegnNy()
    {
        if (hentInfo())
        {
            if( vindu.getRegister().getKundeliste().erKunde(kunde) == false )
            {
                vindu.getAnsatt().leggTilKundenøkel(kunde.getPersonnummer());
                register.nyKunde(kunde);
            }
            
            Reiseforsikring nyForsikring = new Reiseforsikring(kunde, egenandelvalget, dekningvalget, forsorger_b, antBarn, sonevalget, belop);
            nyForsikring.setArligPremie(foreslåttPris);
            register.nyForsikring(nyForsikring);
            
            if(kundePanel != null)
                kundePanel.oppdaterVindu();
            
            vindu.visInformasjon("Beskjed", "Du har nå tegnet en ny forsikring på " + nyForsikring.getKunde().getFornavn() + " " + nyForsikring.getKunde().getEtternavn());
            visForsikring(nyForsikring);
        }
    }
    
    /*Metode for å oppdatere en allerede eksisterende forsikring med ny input fra
    brukeren. Forutsetter at hentInfo()-metoden returnerer true. Oppdaterer også
    kundefanen som forsikringen hører til.*/
    @Override
    public void oppdaterForsikring()
    {
        if (hentInfo())
        {
            forsikring.setForsorger(forsorger_b);
            forsikring.setAntBarn(antBarn);
            forsikring.setSone(sonevalget);
            forsikring.setEgenandel(egenandelvalget);
            forsikring.setBelopet(belop);
            forsikring.setVilkar(dekningvalget);
            forsikring.setArligPremie(foreslåttPris);
              
            if(kundePanel != null)
                kundePanel.oppdaterVindu();
        }  
    }
    
    /*Gjør inputfeltene redigerbare, setter passende tekst på knappene og legger
    til en knapp for å lagre den nye informasjonen som brukeren evt legger inn*/
    @Override
    public void redigerForsikring()
    {
        enableFelter( this, beregnPris );
        knappePanel.add(lagreNyInfo);
        tilbudLabel.setText("Foreslått tilbud: ");
        beregnPris.setText("Beregn ny pris");   
        revalidate();
        repaint(); 
    } 
    
    /*Metode for å deaktivere en forsikring. Gjør alle feltene ikke-redigerbare
    og fjerner alle knappene. Forsikringen slettes ikke fra systemet, men settes
    som inaktiv. Det kommer først opp en meldingsboks der brukeren kan bekrefte at
    han/hun ønsker å deaktivere forsikringen. Hvis svaret er ja, kommer det opp 
    en ny meldingsboks som bekrefter forsikringens deaktivering.*/
    @Override
    public void deaktiverForsikring()
    {
        int svar = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil deaktivere denne forsikringen?", "Forsikring " + String.valueOf(forsikring.getForsikringsnummer()), JOptionPane.YES_NO_OPTION);
        if (svar == JOptionPane.YES_OPTION)
        {
            knappePanel.remove(rediger);
            knappePanel.remove(lagreNyInfo);
            this.remove(beregnPris);
            knappePanel.remove(deaktiver);
            forsikring.setAktiver(false);
            JOptionPane.showMessageDialog(null, "Forsikring " + String.valueOf(forsikring.getForsikringsnummer()) + " er ikke lenger aktiv.", "Bekreftelse", JOptionPane.PLAIN_MESSAGE);
              
            if(kundePanel != null)
                kundePanel.oppdaterVindu();
              
            repaint();
            revalidate();
        }
    }
    
    //Viser vilkår i et nytt vindu. Henter vilkår fra fil
    @Override
    public void visVilkår()
    {
        if( forsikring == null )
            visForsikringensVilkår("Ny Fritidsboligforsikring " + kunde.getFornavn() + " " + kunde.getEtternavn() , vilkår);
        else
            visForsikringensVilkår("Vilkår" + forsikring.getForsikringsnummer(), forsikring.getVilkar());
    }
    
    @Override
    public void velgVilkår() 
    {
        vilkår = this.velgVilkår( "Reise"+ dekningvelger.getItemAt(dekningvelger.getSelectedIndex()) );
        vilkarKnapp.setVisible(true);
    }
    
    //Klassens lytter
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == reiseGiTilbud)
        {
            tegnNy();
        }
        else if (e.getSource() == beregnPris)
        {
            beregnPris();
        }
        else if (e.getSource() == vilkarKnapp)
        {
            visVilkår();
        }
        else if (e.getSource() == rediger)
        {
            redigerForsikring();
        }
        else if (e.getSource() == lagreNyInfo)
        {
            oppdaterForsikring();
        }
        else if (e.getSource() == deaktiver)
        {
           deaktiverForsikring();
        }
    }
    
    /*Lytterklassen til dekningvelgeren. Denne lytteren endrer vilkårene etter 
    hvilken dekning som er valgt.*/
    private class VilkårLytter implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent e) 
        {
            if( dekningvelger.getSelectedIndex() != 0)
                velgVilkår();
        }
    }
    
}
