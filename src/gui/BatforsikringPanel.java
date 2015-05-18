/**
 * Denne klassen representerer fanekortet som åpnes når bruker av programet
 * ønsker å vise/registrere en båtforsikring. 
 * 
 * 
 * 
 */
package gui;

//Nødvendige import-setninger
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Calendar;
import javax.swing.*;
import objekter.*;
import register.*;

/*Klassens hensikt er å designe brukergrensesnittet for båtforsikringer, 
ta imot input fra brukeren og registrere videre en båtforsikring
hvis alle feltene er korrekt skrevet inn. Klassen kan også vise informasjon om
en allerede tegnet båtforsikring, og endre denne.*/
public class BatforsikringPanel extends JPanel implements ActionListener, ForsikringsPanel
{
    private final AnsattVindu vindu;
    private final HovedRegister register;
    private Eier eier;
    private BatForsikring forsikring = null;
    private KundePanel kundePanel;
    
    private final JTextField eierFornavn;
    private final JTextField eierEtternavn;
    private final JTextField eierTlf;
    private final JTextField eierAdresse;
    private final JPanel eierPanel;
    
    private final JTextField batRegnr;
    private final JTextField batModell;
    private final JTextField batVerdi;
    private final JTextField batTilbud;
    private final JTextField batMerke;
    private final JTextField batLengde;
    private final JTextField batHk;
    private final JTextField batArsmodell;
    private final JLabel tilbudLabel;
    private final JRadioButton vekterJa;
    private final JRadioButton vekterNei;
    private final String[] battype = {"","Skjærgårdsjeep", "Cabin cruiser", "Rib", "Annen småbåt",
                        "Speedbåt", "Seilbåt", "Snekke"};
    private final JComboBox<String> battypevelger;
    private final String[] egenandel = {"", "2000", "4000", "8000", "12000", "16000", "20000", "30000"};
    private final JComboBox<String> egenandelsvelger;
    private final String[] dekning = {"", "Ansvar", "Delkasko", "Kasko", "Båt-Pluss"};
    private final JComboBox<String> dekningvelger;
    private final JButton annenEier;
    private final JButton beregnPris;
    private final JButton batGiTilbud;
    private final JButton vilkarKnapp;
    private final Kunde kunde;
    
    private String vilkår;
    private String reg;
    private String merke;
    private String modell;
    private int hk;
    private int ar;
    private int lengde;
    private String typevalget;
    private int egenandelvalget;
    private String dekningvalget;
    private boolean vekter_b;
    private int belop;
    private JPanel knappePanel = new JPanel();
    private JButton rediger = new JButton("Rediger forsikring");
    private JButton lagreNy = new JButton("Lagre forsikring");
    private JButton deaktiver = new JButton("Si opp forsikring");
    
    
    public BatforsikringPanel(Kunde k, AnsattVindu v)
    {
        vindu = v;
        register = vindu.getRegister();
        kunde = k;
        batRegnr = new JTextField( 7 );
        batModell = new JTextField( 7 );
        batVerdi  = new JTextField( 8 );
        batTilbud = new JTextField( 7 );
        batMerke = new JTextField( 7 );
        batLengde = new JTextField( 7 );
        batArsmodell = new JTextField( 7 );
        tilbudLabel = new JLabel("Foreslått tilbud: ");
        batHk = new JTextField(4);
        vekterJa = new JRadioButton("Ja");
        vekterNei = new JRadioButton("Nei");
        vekterJa.setMnemonic(KeyEvent.VK_J);
        vekterNei.setMnemonic(KeyEvent.VK_N);
        ButtonGroup vektere = new ButtonGroup();
        vektere.add(vekterJa);
        vektere.add(vekterNei);
        battypevelger = new JComboBox<>(battype);
        egenandelsvelger = new JComboBox<>(egenandel);
        dekningvelger = new JComboBox<>(dekning);
        annenEier = new JButton("Trykk her for annen eier");
        batGiTilbud = new JButton("Tegn forsikring");
        batGiTilbud.setVisible(true);
        beregnPris = new JButton("Beregn pris");
        vilkarKnapp = new JButton("Vis vilkår");
        vilkarKnapp.setVisible(false);
        
        eierFornavn = new JTextField(20);
        eierEtternavn = new JTextField(20);
        eierTlf = new JTextField(8);
        eierAdresse = new JTextField(15);
        
        eierPanel = new JPanel();
        eierPanel.add(new JLabel("Fornavn: "));
        eierPanel.add(eierFornavn);
        eierPanel.add(new JLabel("Etternavn: "));
        eierPanel.add(eierEtternavn);
        eierPanel.add(new JLabel("Telefonnummer: "));
        eierPanel.add(eierTlf);
        eierPanel.add(new JLabel("Adresse: "));
        eierPanel.add(eierAdresse);
        
        JPanel vekt = new JPanel();
        JPanel tegnBatPanel1 = new JPanel();
        JPanel tegnBatPanel2 = new JPanel();
        JPanel hovedPanel = new JPanel();
        tegnBatPanel1.setLayout(new GridLayout(8,2,2,10));
        tegnBatPanel2.setLayout(new GridLayout(8,2,2,10));
        hovedPanel.setLayout(new BoxLayout(hovedPanel, BoxLayout.LINE_AXIS));
        vekt.add(vekterJa);
        vekt.add(vekterNei);
        tegnBatPanel1.add(new JLabel("Reg.nummer: "));
        tegnBatPanel1.add(batRegnr);
        tegnBatPanel2.add(new JLabel("Velg dekning: "));
        tegnBatPanel2.add(dekningvelger);
        tegnBatPanel2.add(Box.createGlue());
        tegnBatPanel2.add(vilkarKnapp);
        tegnBatPanel1.add(new JLabel("Merke: "));
        tegnBatPanel1.add(batMerke);
        tegnBatPanel2.add(new JLabel("Vekter: "));
        tegnBatPanel2.add(vekt);
        tegnBatPanel1.add(new JLabel("Modell: "));
        tegnBatPanel1.add(batModell);
        tegnBatPanel2.add(new JLabel("Egenandel: "));
        tegnBatPanel2.add(egenandelsvelger);
        tegnBatPanel1.add(new JLabel("Lengde: "));
        tegnBatPanel1.add(batLengde);
        tegnBatPanel2.add(new JLabel("<html>Er eier annen<br>enn kunde?</html>"));
        tegnBatPanel2.add(annenEier);
        tegnBatPanel1.add(new JLabel("Årsmodell: "));
        tegnBatPanel1.add(batArsmodell);
        tegnBatPanel2.add(Box.createGlue());
        tegnBatPanel2.add(beregnPris);
        tegnBatPanel1.add(new JLabel("Hestekrefter: "));
        tegnBatPanel1.add(batHk);
        tegnBatPanel2.add(tilbudLabel);
        tegnBatPanel2.add(batTilbud);
        tegnBatPanel1.add(new JLabel("Type båt: "));
        tegnBatPanel1.add(battypevelger);
        tegnBatPanel1.add(new JLabel("Verdi "));
        tegnBatPanel1.add(batVerdi);
        tegnBatPanel2.add(Box.createGlue());
        tegnBatPanel2.add(batGiTilbud);
        hovedPanel.add(tegnBatPanel1);
        hovedPanel.add(Box.createHorizontalStrut(5));
        hovedPanel.add(new JSeparator(SwingConstants.VERTICAL));
        hovedPanel.add(Box.createHorizontalStrut(5));
        tegnBatPanel2.setPreferredSize(tegnBatPanel1.getPreferredSize());
        hovedPanel.add(tegnBatPanel2);
        add(hovedPanel);
        
        VilkårLytter vilkårLytter = new VilkårLytter();
        batGiTilbud.addActionListener(this);
        annenEier.addActionListener(this);
        beregnPris.addActionListener(this);
        vilkarKnapp.addActionListener(this);
        rediger.addActionListener(this);
        lagreNy.addActionListener(this);
        deaktiver.addActionListener(this);
        dekningvelger.addItemListener(vilkårLytter);
    }

    /*Metode for å vise en allerede tegnet båtforsikring. Tar imot forsikringen
    som parameter.*/
    public void visForsikring( Forsikring f )
    {
        this.forsikring = (BatForsikring) f;
        batRegnr.setText(forsikring.getRegistreringsnmmer());
        batMerke.setText(forsikring.getFabrikant());
        batModell.setText(forsikring.getModell());
        batVerdi.setText(String.valueOf(forsikring.getVerdi()));
        batLengde.setText(String.valueOf(forsikring.getLengde()));
        batArsmodell.setText(String.valueOf(forsikring.getArsmodell()));
        batHk.setText(String.valueOf(forsikring.getHestekrefter()));
        egenandelsvelger.setSelectedItem(String.valueOf(forsikring.getEgenandel()));
        battypevelger.setSelectedItem(String.valueOf(forsikring.getkjøretøytype()));
        dekningvelger.setSelectedItem(forsikring.getVilkar());
        
        if (forsikring.getVekter())
            vekterJa.setSelected(true);
        else
            vekterNei.setSelected(true);
        
        annenEier.setText("Vis eier");
        
        if(forsikring.erAktiv())
        {
            knappePanel.setLayout(new BoxLayout(knappePanel, BoxLayout.PAGE_AXIS));
            knappePanel.add(rediger);
            knappePanel.add(deaktiver);
            add(knappePanel);
        }
        
        tilbudLabel.setText("Årlig premie: (Kr/år)");
        tilbudLabel.setVisible(true);
        batTilbud.setVisible(true);
        vilkarKnapp.setVisible(true);
        revalidate();
        repaint();
        
        disableFelter( this, batGiTilbud, beregnPris );
    }
    
    /*Hvis brukeren trykket seg videre til å denne forsikringen via et kundepanel,
    så setter denne metoden kundepanelet som var utgangspunktet via paramtereren.
    Denne er nødvendig for å få oppdatert kundepanelet til kunden som forsikringen
    hører til, når det blir gjort endring på en forsikring eller når det blir
    tegnet en ny forsikring.*/
    public void leggTilKundePanel( KundePanel panel )
    {
        kundePanel = panel;
    }
    
    /*Metoden henter input fra brukeren. Den sjekker at alle feltene er korrekt
    fylt ut, hvis ikke kommer det opp en passende feilmelding. Metoden returnerer
    en boolean, som er avhengig av om alle feltene er korrekte fylt ut eller ikke*/
    public boolean hentInfo()
    {
        int type_n = battypevelger.getSelectedIndex();
        int egenandel_n = egenandelsvelger.getSelectedIndex();
        int dekning_n = dekningvelger.getSelectedIndex();
            
        if (type_n == 0 || egenandel_n == 0 || (!vekterJa.isSelected() && !vekterNei.isSelected()) || dekning_n == 0 )
        {
            String ut = "Det mangler informasjon om:\n";
            if (type_n == 0)
            {
                ut += "Båttype\n";
            }
            if (egenandel_n == 0)
            {
                ut += "Egenandel\n";
            }
            if (dekning_n == 0)
            {
                ut += "Dekning\n";
            }
            if(!vekterJa.isSelected() && !vekterNei.isSelected())
            {
                ut += "Vektervalg\n";
            }
         
            ut += "\nVennligst fyll ut denne informasjonen og prøv igjen.";
                JOptionPane.showMessageDialog(null, ut, "Feilmelding",
                                                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else
        {  
            if (vekterJa.isSelected() && !vekterNei.isSelected())
                vekter_b = true;
            else if (!vekterJa.isSelected() && vekterNei.isSelected())
                vekter_b = false;
        
            try
            {
                if (batRegnr.getText().matches("^([a-zA-Z]){2}([0-9]){5}"))
                    reg = batRegnr.getText();
                else
                {
                    vindu.visFeilmelding("Feilmelding", "Feil format i et av tekstfeltene. ");
                    return false;
                }
            
                belop = Integer.parseInt(batVerdi.getText());
                merke = batMerke.getText();
                modell = batModell.getText();
                hk = Integer.parseInt(batHk.getText());
                ar = Integer.parseInt(batArsmodell.getText());
                java.util.Locale norge = new java.util.Locale( "no" );
                Calendar dato = Calendar.getInstance(norge);
                if (ar > dato.get(Calendar.YEAR))
                {
                    vindu.visFeilmelding("Feilmelding", ar + " er ikke et gyldig registreringsår");
                    return false;
                }
                
                lengde = Integer.parseInt(batLengde.getText());
                typevalget = battypevelger.getItemAt(type_n);
                egenandelvalget = Integer.parseInt(egenandelsvelger.getItemAt(egenandel_n));
                dekningvalget = dekningvelger.getItemAt(dekning_n);
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
    public void beregnPris()
    {
        if (hentInfo())
        {
            double foreslåttPris = ForsikringsKalulator.beregnBatforsikring(egenandelvalget, dekningvalget, belop, hk, ar, vekter_b, lengde  );
            batTilbud.setVisible(true);
            batTilbud.setText(String.valueOf(foreslåttPris));
            batTilbud.setToolTipText("Kan redigeres");
        }
    }
    
    /*Metode for å registrere et nytt båtforsikringsobjekt og legge dette inn i
    registeret. Oppdaterer også den eventuelle kundefanen som forsikringen hører
    til.*/        
    public void tegnNy()
    {
        if(hentInfo())
        {
            if( vindu.getRegister().getKundeliste().erKunde(kunde) == false )
            {
                vindu.getAnsatt().leggTilKundenøkel(kunde.getPersonnummer());
                register.nyKunde(kunde);
            }
            
            BatForsikring nyForsikring = new BatForsikring(kunde, egenandelvalget, vilkår, reg, belop,
                                 merke, modell, typevalget, hk, 
                                 ar, vekter_b, lengde);
            
            nyForsikring.setArligPremie(Double.parseDouble(batTilbud.getText()));
            
            vindu.getRegister().nyForsikring(nyForsikring);
            Kjoretoyforsikring kjForsikring = (Kjoretoyforsikring)nyForsikring;
            
            if (eier != null)
                nyForsikring.setEier(eier);
            
            JOptionPane.showMessageDialog(null, "Du har nå tegnet båtforsikring med nummer "
                                          + nyForsikring.getForsikringsnummer() + " på " + kunde.getFornavn() 
                                          + " " + kunde.getEtternavn() , "Bekreftelse", JOptionPane.INFORMATION_MESSAGE);
            if(kundePanel != null)
                kundePanel.oppdaterVindu();
        }
    }
    
    /*Metode for å oppdatere en allerede eksisterende forsikring med ny input fra
    brukeren. Forutsetter at hentInfo()-metoden returnerer true. Oppdaterer også
    kundefanen som forsikringen hører til.*/
    public void oppdaterForsikring()
    {
        if(hentInfo())
        {
            forsikring.setRegistreringsnummer(reg);
            forsikring.setLengde(lengde);
            forsikring.setEgenandel(egenandelvalget);
            forsikring.setVekter(vekter_b);
            forsikring.setModell(modell);
            forsikring.setFabrikant(merke);
            forsikring.setType(typevalget);
            forsikring.setHestekrefter(hk);
            forsikring.setArsmodell(ar);
            forsikring.setBelop(belop);
            forsikring.setEier(eier);
            forsikring.setVilkar(vilkår);
            
            if(kundePanel != null)
                kundePanel.oppdaterVindu();
        }
    }
    
    /*Gjør inputfeltene redigerbare, setter passende tekst på knappene og legger
    til en knapp for å lagre den nye informasjonen som brukeren evt legger inn*/
    public void enableSkjema()
    {
        /*Metode fra ForsikringPanel, gjør inputfeltene redigerbare. Sender med
        dette panelet og knappen for å beregne pris som paramtere*/
        enableFelter( this, beregnPris );
        
        beregnPris.setText("Beregn ny pris");
        tilbudLabel.setText("Foreslått tilbud: ");
        annenEier.setText("Trykk her for annen eier");
        knappePanel.add(lagreNy);
    }
    
    /*Metode for å deaktivere en forsikring. Gjør alle feltene ikke-redigerbare
    og fjerner alle knappene. Forsikringen slettes ikke fra systemet, men settes
    som inaktiv. Det kommer først opp en meldingsboks der brukeren kan bekrefte at
    han/hun ønsker å deaktivere forsikringen. Hvis svaret er ja, kommer det opp 
    en ny meldingsboks som bekrefter forsikringens deaktivering.*/
    public void deaktiverForsikring()
    {
        int svar = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil deaktivere denne forsikringen?", "Forsikring " + String.valueOf(forsikring.getForsikringsnummer()), JOptionPane.YES_NO_OPTION);
        if (svar == JOptionPane.YES_OPTION)
        {
            knappePanel.remove(rediger);
            knappePanel.remove(lagreNy);
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
    
    /*Viser vilkår i et nytt vindu. Henter vilkår fra fil*/
    public void visVilkår()
    {
        if( forsikring == null )
            visForsikringensVilkår("Ny bilforsikring " + kunde.getFornavn() + " " + kunde.getEtternavn() , vilkår);
        else
            visForsikringensVilkår("Vilkår " + forsikring.getForsikringsnummer(), forsikring.getVilkar());
    }
    
    /*Klassens knappelytter*/
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == batGiTilbud)
        {
            tegnNy();
        }
        else if (e.getSource() == beregnPris)
        {
            beregnPris();
        }
        else if (e.getSource() == annenEier)
        {
            //Hvis man skal registrere ny eier
            if (annenEier.getText().equals("Trykk her for annen eier"))
            {
                int result = JOptionPane.showConfirmDialog(null, eierPanel, 
                "Vennligst fyll ut bileiers kontaktinformasjon:", JOptionPane.OK_CANCEL_OPTION);
                do
                {
                    if (result == JOptionPane.OK_OPTION)
                    {
                        if(eierFornavn.getText().matches("\\D*") && 
                                eierEtternavn.getText().matches("\\D*") && 
                                eierTlf.getText().matches("\\d{8}"))
                        {
                            eier = new Eier(eierFornavn.getText(), eierEtternavn.getText(), 
                                    eierAdresse.getText(), eierTlf.getText());
                            result = 0;
                        }
                        else
                        {
                            vindu.visFeilmelding("Feilmelding", "Pass på at alle"
                                    + " feltene er korrekt fylt ut");
                        }
                    }
                }
                while (result == JOptionPane.OK_OPTION);
            }
            else if(annenEier.getText().equals("Vis eier"))
                {
                  JOptionPane.showMessageDialog( null, forsikring.getEier().toString(), 
                      "Kjøretøyets registrerte eier:", JOptionPane.PLAIN_MESSAGE);
                }
        }
        else if (e.getSource() == vilkarKnapp)
        {
            visVilkår();
        }
        else if (e.getSource() == rediger)
        {
            enableSkjema();
        }
        else if (e.getSource() == lagreNy)
        {
            oppdaterForsikring();
        }
        else if (e.getSource() == deaktiver)
        {
            deaktiverForsikring();
        }
    }
    
    private class VilkårLytter implements ItemListener, ForsikringsPanel
    {
        @Override
        public void itemStateChanged(ItemEvent e) 
        {
            if( dekningvelger.getSelectedIndex() != 0)
            {
                vilkår = this.velgVilkår( "Båt"+ dekningvelger.getItemAt(dekningvelger.getSelectedIndex()) );
                vilkarKnapp.setVisible(true);
            }
        }
    }
}
