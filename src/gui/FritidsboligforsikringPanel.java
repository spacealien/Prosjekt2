/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import objekter.*;
import register.*;

/**
 *
 * @author Odd, Thomas, Marthe //MARTHE! FIKS ALARM i hentInfo()
 */
public class FritidsboligforsikringPanel extends JPanel implements ActionListener
{
    private AnsattVindu vindu;
    private HovedRegister register;
    private Fritidsboligforsikring forsikring;
    private KundePanel kundePanel;
    
    
    private final JTextField fritidAdresse;
    private final JTextField belopFritid;
    private final JTextField belopFritidInnbo;
    private final JTextField fritidTilbud;
    private final JTextField fritidKvm;
    private final JTextField fritidAr;
    private JLabel tilbudLabel;
    private final JButton fritidGiTilbud;
    private final JButton beregnPris;
    private JButton vilkar;
    private final JRadioButton utleidJa;
    private final JRadioButton utleidNei;
    private final JRadioButton alarmJa;
    private final JRadioButton alarmNei;
    JComboBox<String> fritidtypevelger;
    private final String[] fritidtype = {"","Hus/Hytte", "Rekkehus", "Leilighet"};
    JComboBox<String> fritidmaterialevelger;
    private final String[] fritidmateriale = {"","Mur", "Tre", "Brannfast", "Laftet tømmer"};
    private final String[] fritidstandard = {"","Normal standard", "Bedre standard", "Høy standard"};
    JComboBox<String> fritidstandardvelger;
    String[] egenandel = {"", "2000", "4000", "8000", "12000", "16000", "20000", "30000"};
    JComboBox<String> egenandelsvelger;
    String[] dekning = {"", "Fritidsbolig", "Fritidsbolig Pluss"};
    JComboBox<String> dekningvelger;
    
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
    private JPanel knappePanel = new JPanel();
    private JButton rediger = new JButton("Rediger forsikring");
    private JButton lagreNyInfo = new JButton("Lagre forsikring");
    private JButton deaktiver = new JButton("Si opp forsikring");
    
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
        ButtonGroup alarm = new ButtonGroup();
        alarm.add(alarmJa);
        alarm.add(alarmNei);
        utleidJa = new JRadioButton("Ja");
        utleidNei = new JRadioButton("Nei");
        ButtonGroup utleid = new ButtonGroup();
        utleid.add(utleidJa);
        utleid.add(utleidNei);
        fritidGiTilbud = new JButton("Tegn forsikring");
        beregnPris = new JButton("Beregn pris");
        vilkar = new JButton("Vis vilkår");
        
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
        tegnFritidPanel2.add(vilkar);
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
        
        fritidGiTilbud.addActionListener(this);
        beregnPris.addActionListener(this);
        vilkar.addActionListener(this);
        rediger.addActionListener(this);
        lagreNyInfo.addActionListener(this);
        deaktiver.addActionListener(this);
    }
    
    private Component[] getKomponenter(Component pane)
     {
        ArrayList<Component> liste = null;

        try
        {
            liste = new ArrayList<Component>(Arrays.asList(
                  ((Container) pane).getComponents()));
            for (int i = 0; i < liste.size(); i++)
            {
            for (Component currentComponent : getKomponenter(liste.get(i)))
            {
                liste.add(currentComponent);
            }
            }
        } catch (ClassCastException e) {
            liste = new ArrayList<Component>();
        }

        return liste.toArray(new Component[liste.size()]);
        
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
        
        tilbudLabel.setText("Årlig premie: ");
        tilbudLabel.setVisible(true);
        fritidTilbud.setVisible(true);
        revalidate();
        repaint();
        
        for(Component component : getKomponenter(this))
                {
                    if((component instanceof JTextField))
                    {
                        JTextField tf = (JTextField)component;
                        tf.setEditable(false);
                    }
                    else if(component instanceof JComboBox)
                    {
                        JComboBox cb = (JComboBox)component;
                        cb.setEnabled(false);
                    }
                    else if(component instanceof JRadioButton)
                    {
                        JRadioButton rb = (JRadioButton)component;
                        rb.setEnabled(false);
                    }
                    else if (component.equals(fritidGiTilbud))
                            {
                                component.setVisible(false);
                            }
                    else if (component.equals(beregnPris))
                            {
                                component.setVisible(false);
                            }
                }
    }
    
    public void beregnPris()
    {
        if(hentInfo())
        {
            //Beregn pris
            Fritidsboligforsikring forsikring = new Fritidsboligforsikring(kunde, egenandelvalget, "Vilkår 1", adr, ar, 
                       typevalget, materialevalget, standardvalget, kvm, belop, belopInnbo, alarm_b, utleid_b);
            
            
            double foreslåttPris = ForsikringsKalulator.beregnFritidsboligforsikring(forsikring);
            NumberFormat formatter = new DecimalFormat("#0.00"); 
            fritidTilbud.setVisible(true);
            fritidTilbud.setText(formatter.format(foreslåttPris) + " Kr/År");
        }
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
                
                egenandelvalget = Integer.parseInt(egenandelsvelger.getItemAt(egenandel_n));
                typevalget = fritidtypevelger.getItemAt(type_n); 
                materialevalget = fritidmaterialevelger.getItemAt(materiale_n);
                standardvalget = fritidstandardvelger.getItemAt(standard_n);
                dekningvalget = dekningvelger.getItemAt(dekning_n);
                adr = fritidAdresse.getText();
                ar = Integer.parseInt(fritidAr.getText());
                kvm = Integer.parseInt(fritidKvm.getText());
                belop = Integer.parseInt(belopFritid.getText());
                belopInnbo = Integer.parseInt(belopFritidInnbo.getText());
                return true;
                    
            } 
    }
    
    public void leggTilKundePanel( KundePanel panel )
    {
        kundePanel = panel;
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
            
            vindu.getRegister().nyForsikring(forsikringen);
            
            if(kundePanel != null)
                kundePanel.oppdaterVindu();
            
            JOptionPane.showMessageDialog(null, "Du har nå tegnet fritidsboligforsikring med nummer " 
                                          + forsikringen.getForsikringsnummer() + " på " + kunde.getFornavn() 
                                          + " " + kunde.getEtternavn() , "Bekreftelse", 
                                            JOptionPane.INFORMATION_MESSAGE);

        }
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
        else if (e.getSource() == vilkar)
        {
            //Vis vilkår
        }
        else if(e.getSource() == rediger)
        {
            for(Component component : getKomponenter(this))
                {
                    if((component instanceof JTextField))
                    {
                        JTextField tf = (JTextField)component;
                        tf.setEditable(true);
                    }
                    else if(component instanceof JComboBox)
                    {
                        JComboBox cb = (JComboBox)component;
                        cb.setEnabled(true);
                    }
                    else if(component instanceof JRadioButton)
                    {
                        JRadioButton rb = (JRadioButton)component;
                        rb.setEnabled(true);
                    }
                    else if (component.equals(beregnPris))
                            {
                                component.setVisible(true);
                            }
                }
            knappePanel.add(lagreNyInfo);
            tilbudLabel.setText("Foreslått tilbud: ");
            beregnPris.setText("Beregn ny pris");
            revalidate();
            repaint();
        }
        else if (e.getSource() == lagreNyInfo)
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
            
            //Må beregne pris på nytt!
            }
        }
        else if (e.getSource() == deaktiver)
        {
           
            int svar = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil deaktivere denne forsikringen?", "Forsikring " + String.valueOf(forsikring.getForsikringsnummer()), JOptionPane.YES_NO_OPTION);
            if (svar == JOptionPane.YES_OPTION)
            {
                knappePanel.remove(rediger);
                knappePanel.remove(lagreNyInfo);
                knappePanel.remove(beregnPris);
                forsikring.setAktiver(false);
                JOptionPane.showMessageDialog(null, "Forsikring " + String.valueOf(forsikring.getForsikringsnummer()) + " er ikke lenger aktiv.", "Bekreftelse", JOptionPane.PLAIN_MESSAGE);
                repaint();
                revalidate();
            }
        }
        
    }
}
