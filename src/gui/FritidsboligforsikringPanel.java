/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import javax.swing.*;
import objekter.*;
import register.*;

/**
 *
 * @author Odd, Thomas, Marthe //MARTHE! FIKS ALARM i hentInfo()
 */
public class FritidsboligforsikringPanel extends JPanel implements ActionListener, ForsikringsPanel
{
    private final AnsattVindu vindu;
    private final HovedRegister register;
    private Fritidsboligforsikring forsikring;
    private KundePanel kundePanel;
    private final JTextField fritidAdresse;
    private final JTextField belopFritid;
    private final JTextField belopFritidInnbo;
    private final JTextField fritidTilbud;
    private final JTextField fritidKvm;
    private final JTextField fritidAr;
    private final JLabel tilbudLabel;
    private final JButton fritidGiTilbud;
    private final JButton beregnPris;
    private final JButton vilkårKnapp;
    private final JRadioButton utleidJa;
    private final JRadioButton utleidNei;
    private final JRadioButton alarmJa;
    private final JRadioButton alarmNei;
    private final JComboBox<String> fritidtypevelger;
    private final String[] fritidtype = {"","Hus/Hytte", "Rekkehus", "Leilighet"};
    private final JComboBox<String> fritidmaterialevelger;
    private final String[] fritidmateriale = {"","Mur", "Tre", "Brannfast", "Laftet tømmer"};
    private final String[] fritidstandard = {"","Normal standard", "Bedre standard", "Høy standard"};
    private final JComboBox<String> fritidstandardvelger;
    private final String[] egenandel = {"", "2000", "4000", "8000", "12000", "16000", "20000", "30000"};
    private final JComboBox<String> egenandelsvelger;
    private final String[] dekning = {"", "Fritidsbolig", "Fritidsbolig-Pluss"};
    private final JComboBox<String> dekningvelger;
    
    private String vilkår;
    private String typevalget;  
    private String materialevalget;
    private String standardvalget;
    private int egenandelvalget;
    private String dekningvalget;
    private boolean utleid_b;
    private boolean alarm_b;
    private String adr;
    private int ar;
    private int kvm;
    private int belop;
    private int belopInnbo;
    
    private final Kunde kunde;
    private final JPanel knappePanel = new JPanel();
    private final JButton rediger = new JButton("Rediger forsikring");
    private final JButton lagreNyInfo = new JButton("Lagre forsikring");
    private final JButton deaktiver = new JButton("Si opp forsikring");
    
    public FritidsboligforsikringPanel(Kunde k, AnsattVindu v)
    {
        kunde = k;
        vindu = v;
        register = vindu.getRegister();
        fritidAdresse = new JTextField( 7 );
        fritidKvm = new JTextField(5);
        fritidAr = new JTextField( 4 );
        belopFritid = new JTextField( 7 );
        belopFritidInnbo = new JTextField( 7 );
        fritidTilbud = new JTextField( 7 );
        tilbudLabel = new JLabel("Foreslått tilbud: ");
        fritidtypevelger = new JComboBox<>(fritidtype);
        fritidmaterialevelger = new JComboBox<>(fritidmateriale);
        fritidstandardvelger = new JComboBox<>(fritidstandard);
        egenandelsvelger = new JComboBox<>(egenandel);
        dekningvelger = new JComboBox<>(dekning);
        alarmJa = new JRadioButton("Ja");
        alarmNei = new JRadioButton("Nei");
        alarmJa.setMnemonic(KeyEvent.VK_J);
        alarmNei.setMnemonic(KeyEvent.VK_N);
        ButtonGroup alarm = new ButtonGroup();
        alarm.add(alarmJa);
        alarm.add(alarmNei);
        utleidJa = new JRadioButton("Ja");
        utleidNei = new JRadioButton("Nei");
        utleidJa.setMnemonic(KeyEvent.VK_J);
        utleidNei.setMnemonic(KeyEvent.VK_N);
        ButtonGroup utleid = new ButtonGroup();
        utleid.add(utleidJa);
        utleid.add(utleidNei);
        fritidGiTilbud = new JButton("Tegn forsikring");
        fritidGiTilbud.setVisible(false);
        beregnPris = new JButton("Beregn pris");
        vilkårKnapp = new JButton("Vis vilkår");
        
        JPanel tegnFritidPanel1 = new JPanel();
        JPanel tegnFritidPanel2 = new JPanel();
        JPanel hovedPanel = new JPanel();
        JPanel utleie = new JPanel();
        JPanel alarmPanel = new JPanel();
        tegnFritidPanel1.setLayout(new GridLayout(9,2,1,5));
        tegnFritidPanel2.setLayout(new GridLayout(9,2,1,5));
        utleie.add(utleidJa);
        utleie.add(utleidNei);
        alarmPanel.add(alarmJa);
        alarmPanel.add(alarmNei);
        tegnFritidPanel1.add(new JLabel("Adresse: "));
        tegnFritidPanel1.add(fritidAdresse);
        tegnFritidPanel1.add(new JLabel("Byggeår: "));
        tegnFritidPanel1.add(fritidAr);
        tegnFritidPanel1.add(new JLabel("Kvadratmeter: "));
        tegnFritidPanel1.add(fritidKvm);
        tegnFritidPanel1.add(new JLabel("Boligtype: "));
        tegnFritidPanel1.add(fritidtypevelger);
        tegnFritidPanel1.add(new JLabel("Byggemateriale: "));
        tegnFritidPanel1.add(fritidmaterialevelger);
        tegnFritidPanel1.add(new JLabel("Standard: "));
        tegnFritidPanel1.add(fritidstandardvelger);
        tegnFritidPanel1.add(new JLabel("Alarm: "));
        tegnFritidPanel1.add(alarmPanel);
        tegnFritidPanel1.add(new JLabel("Utleid: "));
        tegnFritidPanel1.add(utleie);
        tegnFritidPanel1.add(new JLabel());
        tegnFritidPanel1.add(new JLabel());
        tegnFritidPanel2.add(new JLabel("Forsikringsbeløp bygg: "));
        tegnFritidPanel2.add(belopFritid);
        tegnFritidPanel2.add(new JLabel("Forsikringsbeløp innbo: "));
        tegnFritidPanel2.add(belopFritidInnbo);
        tegnFritidPanel2.add(new JLabel());
        tegnFritidPanel2.add(vilkårKnapp);
        tegnFritidPanel2.add(new JLabel("Velg dekning: "));
        tegnFritidPanel2.add(dekningvelger);
        tegnFritidPanel2.add(new JLabel("Egenandel: "));
        tegnFritidPanel2.add(egenandelsvelger);
        tegnFritidPanel2.add(new JLabel());
        tegnFritidPanel2.add(beregnPris);
        tegnFritidPanel2.add(tilbudLabel);
        tegnFritidPanel2.add(fritidTilbud);
        tegnFritidPanel2.add(new JLabel());
        tegnFritidPanel2.add(fritidGiTilbud);
        hovedPanel.add(tegnFritidPanel1);
        hovedPanel.add(Box.createHorizontalStrut(5));
        hovedPanel.add(new JSeparator(SwingConstants.VERTICAL));
        hovedPanel.add(Box.createHorizontalStrut(5));
        tegnFritidPanel2.setPreferredSize(tegnFritidPanel1.getPreferredSize());
        hovedPanel.add(tegnFritidPanel2);
        add(hovedPanel);
        
        VilkårLytter vilkårLytter = new VilkårLytter();
        fritidGiTilbud.addActionListener(this);
        beregnPris.addActionListener(this);
        vilkårKnapp.addActionListener(this);
        rediger.addActionListener(this);
        lagreNyInfo.addActionListener(this);
        deaktiver.addActionListener(this);
        dekningvelger.addItemListener(vilkårLytter);
    }
    
    // ikke fjern, ikke ferdig
    public void visForsikring( Forsikring f)
    {
        this.forsikring = (Fritidsboligforsikring) f;
        fritidAdresse.setText(forsikring.getAdresse());
        fritidAr.setText(String.valueOf(forsikring.getByggeAr()));
        fritidKvm.setText(String.valueOf(forsikring.getKvadratmeter()));
        fritidmaterialevelger.setSelectedItem(forsikring.getMateriale());
        fritidtypevelger.setSelectedItem(forsikring.getBoligtype());
        fritidstandardvelger.setSelectedItem(forsikring.getStandard());
        egenandelsvelger.setSelectedItem(String.valueOf(forsikring.getEgenandel()));
        dekningvelger.setSelectedItem(forsikring.getVilkar());
        belopFritid.setText(String.valueOf(forsikring.getForsikringsbelopBygning()));
        belopFritidInnbo.setText(String.valueOf(forsikring.getForsikringsbelopInnbo()));
        
        if (forsikring.getAlarm())
            alarmJa.setSelected(true);
        else
            alarmNei.setSelected(true);
        
        if (forsikring.getUtleie())
            utleidJa.setSelected(true);
        else
            utleidNei.setSelected(true);
        
        if (forsikring.erAktiv())
        {
            knappePanel.setLayout(new BoxLayout(knappePanel, BoxLayout.PAGE_AXIS));
            knappePanel.add(rediger);
            knappePanel.add(deaktiver);
            add(knappePanel);
        }
        else
        {
            tilbudLabel.setText("Årlig premie: (Kr/År)");
            tilbudLabel.setVisible(true);
            fritidTilbud.setVisible(true);
        }
        
        revalidate();
        repaint();
        disableFelter( this, fritidGiTilbud, beregnPris );
    }
    
    public boolean hentInfo()
    {   
        int type_n = fritidtypevelger.getSelectedIndex(); 
        int materiale_n = fritidmaterialevelger.getSelectedIndex();
        int standard_n = fritidstandardvelger.getSelectedIndex();
        int egenandel_n = egenandelsvelger.getSelectedIndex();
        int dekning_n = dekningvelger.getSelectedIndex();
        if (type_n == 0 || materiale_n == 0 || standard_n == 0 || egenandel_n == 0 || dekning_n == 0 || (!alarmJa.isSelected() && !alarmNei.isSelected()) || (!utleidJa.isSelected() && !utleidNei.isSelected()))
        {
            String ut = "Det mangler informasjon om:\n";
            if (type_n == 0)
            {
                ut += "Boligtype\n";
            }
            if (materiale_n == 0)
            {
                ut += "Byggemateriale\n";
            }
            if (standard_n == 0)
            {
                ut += "Standard\n";
            }
            if (egenandel_n == 0)
            {
                ut += "Egenandel\n";
            }
            if (dekning_n == 0)
            {
                ut += "Dekning\n";
            }
            if (!alarmJa.isSelected() && !alarmNei.isSelected())
            {
                ut += "Alarmvalg\n";
            }
            if (!utleidJa.isSelected() && !utleidNei.isSelected())
            {
                ut += "Utleievalg\n";
            }
                   
            ut += "\nVennligst fyll ut denne informasjonen og prøv igjen.";
                            JOptionPane.showMessageDialog(null, ut, "Feilmelding",
                                                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else
        {
            if (utleidJa.isSelected() && !utleidNei.isSelected())
                utleid_b = true;
            else if (!utleidJa.isSelected() && utleidNei.isSelected())
                utleid_b = false;
                
            if (alarmJa.isSelected() && !alarmNei.isSelected())
                alarm_b = true;
            else if (!alarmJa.isSelected() && alarmNei.isSelected())
                alarm_b = false;
                
            try
            {
                egenandelvalget = Integer.parseInt(egenandelsvelger.getItemAt(egenandel_n));
                typevalget = fritidtypevelger.getItemAt(type_n); 
                materialevalget = fritidmaterialevelger.getItemAt(materiale_n);
                standardvalget = fritidstandardvelger.getItemAt(standard_n);
                dekningvalget = dekningvelger.getItemAt(dekning_n);
                adr = fritidAdresse.getText();
                java.util.Locale norge = new java.util.Locale( "no" );
                Calendar dato = Calendar.getInstance(norge);
             
                if (fritidAr.getText().matches("\\d{4}") && Integer.parseInt(fritidAr.getText()) <= dato.get(Calendar.YEAR))
                    ar = Integer.parseInt(fritidAr.getText());
                else
                {
                    vindu.visFeilmelding("Feilmelding", fritidAr.getText() + " er ikke et gyldig byggeår");
                     return false;
                }
          
                kvm = Integer.parseInt(fritidKvm.getText());
                belop = Integer.parseInt(belopFritid.getText());
                belopInnbo = Integer.parseInt(belopFritidInnbo.getText());
                return true;
            }
            catch( NumberFormatException e )
            {
                vindu.visFeilmelding("Feilmelding", "Feil format i et av tekstfeltene. ");
                return false;
            }     
        } 
    }
    public void beregnPris()
    {
        if(hentInfo())
        {
            double foreslåttPris = ForsikringsKalulator.beregnFritidsboligforsikring(egenandelvalget, dekningvalget, ar, 
                                              materialevalget, kvm, belop, belopInnbo, alarm_b, typevalget, standardvalget);                    
            
            fritidTilbud.setVisible(true);
            fritidTilbud.setText( String.valueOf(foreslåttPris) );
            fritidGiTilbud.setVisible(true);
        }
    }
    
    public void tegnNy()
    {
        if (hentInfo())
        {
            if( vindu.getRegister().getKundeliste().erKunde(kunde) == false )
            {
                vindu.getAnsatt().leggTilKundenøkel(kunde.getPersonnummer());
                register.nyKunde(kunde);
            }
            
            Forsikring forsikringen = new Fritidsboligforsikring(kunde, egenandelvalget, dekningvalget, adr, ar, 
                       typevalget, materialevalget, standardvalget, kvm, belop, belopInnbo, alarm_b, utleid_b);
            
            forsikringen.setArligPremie(Double.parseDouble(fritidTilbud.getText()));
            vindu.getRegister().nyForsikring(forsikringen);
            
            JOptionPane.showMessageDialog(null, "Du har nå tegnet fritidsboligforsikring med nummer " 
                                          + forsikringen.getForsikringsnummer() + " på " + kunde.getFornavn() 
                                          + " " + kunde.getEtternavn() , "Bekreftelse", 
                                            JOptionPane.INFORMATION_MESSAGE);
            
            if(kundePanel != null)
                kundePanel.oppdaterVindu();
        }
    }
    
    public void oppdaterForsikring()
    {
        if (hentInfo())
        {
            forsikring.setAdresse(adr);
            forsikring.setAlarm(alarm_b);
            forsikring.setUtleie(utleid_b);
            forsikring.setMateriale(materialevalget);
            forsikring.setKvadratmeter(kvm);
            forsikring.setBoligtype(typevalget);
            forsikring.setStandard(standardvalget);
            forsikring.setForsikringsbelopBygning(belop);
            forsikring.setForsikringsbelopInnbo(belopInnbo);
            forsikring.setEgenandel(egenandelvalget);
            forsikring.setByggeAr(ar);
            forsikring.setVilkar(dekningvalget);
            
            if(kundePanel != null)
                kundePanel.oppdaterVindu();
        }  
    }
    
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
            repaint();
            revalidate();
            
            if(kundePanel != null)
                kundePanel.oppdaterVindu();
        }
    }
    
    public void visVilkår()
    {
        if( forsikring == null )
                visForsikringensVilkår("Ny Fritidsboligforsikring " + kunde.getFornavn() + " " + kunde.getEtternavn() , vilkår);
        else
            visForsikringensVilkår("Vilkår" + forsikring.getForsikringsnummer(), forsikring.getVilkar());
    }
    
    public void leggTilKundePanel( KundePanel panel )
    {
        kundePanel = panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == fritidGiTilbud)
        {
            tegnNy();
        }
        else if (e.getSource() == beregnPris)
        {
            beregnPris();
        }
        else if (e.getSource() == vilkårKnapp)
        {
            visVilkår();
        }
        else if(e.getSource() == rediger)
        {
            enableFelter( this, beregnPris );
            knappePanel.add(lagreNyInfo);
            tilbudLabel.setText("Foreslått tilbud: ");
            beregnPris.setText("Beregn ny pris");
            revalidate();
            repaint();
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
    
    private class VilkårLytter implements ItemListener, ForsikringsPanel
    {
        @Override
        public void itemStateChanged(ItemEvent e) 
        {
            if( dekningvelger.getSelectedIndex() != 0)
                vilkår = this.velgVilkår( "Fritidsbolig"+ dekningvelger.getItemAt(dekningvelger.getSelectedIndex()) );
        }
    }
}
