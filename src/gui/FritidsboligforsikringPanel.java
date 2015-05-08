/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.event.*;
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
    
    
    private final JTextField fritidAdresse;
    private final JTextField belopFritid;
    private final JTextField belopFritidInnbo;
    private final JTextField fritidTilbud;
    private final JTextField fritidKvm;
    private final JTextField fritidAr;
    private JLabel tilbudLabel;
    private final JButton fritidGiTilbud;
    private final JButton beregnPris;
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
    
    private String typevalget;  
    private String materialevalget;
    private String standardvalget;
    private int egenandelvalget;
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
        
        JPanel tegnFritidPanel1 = new JPanel();
        JPanel utleie = new JPanel();
        JPanel alarmPanel = new JPanel();
        tegnFritidPanel1.setLayout(new GridLayout(7,4,1,5));
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
        tegnFritidPanel1.add(new JLabel("Utleid: "));
        tegnFritidPanel1.add(utleie);
        tegnFritidPanel1.add(new JLabel("Egenandel: "));
        tegnFritidPanel1.add(egenandelsvelger);
        tegnFritidPanel1.add(new JLabel("Forsikringsbeløp: "));
        tegnFritidPanel1.add(belopFritid);
        tegnFritidPanel1.add(new JLabel("Forsikringsbeløp innbo: "));
        tegnFritidPanel1.add(belopFritidInnbo);
        tegnFritidPanel1.add(beregnPris);
        tegnFritidPanel1.add(tilbudLabel);
        tegnFritidPanel1.add(fritidTilbud);
        tegnFritidPanel1.add(fritidGiTilbud);
        add(tegnFritidPanel1);
        
        fritidGiTilbud.addActionListener(this);
        beregnPris.addActionListener(this);
        rediger.addActionListener(this);
        lagreNyInfo.addActionListener(this);
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
        
        knappePanel.setLayout(new BoxLayout(knappePanel, BoxLayout.PAGE_AXIS));
        knappePanel.add(rediger);
        add(knappePanel);
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
                    else if (component.equals(fritidGiTilbud))
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
            //fritidTilbud.setText();
        }
    }
    
    public boolean hentInfo()
    {
           
            int type_n = fritidtypevelger.getSelectedIndex(); 
            int materiale_n = fritidmaterialevelger.getSelectedIndex();
            int standard_n = fritidstandardvelger.getSelectedIndex();
            int egenandel_n = egenandelsvelger.getSelectedIndex();
            if (type_n == 0 || materiale_n == 0 || standard_n == 0 || egenandel_n == 0 || (!alarmJa.isSelected() && !alarmNei.isSelected()) || (!utleidJa.isSelected() && !utleidNei.isSelected()))
            {
                String ut = "Det mangler informasjon om:\n";
                    if (type_n == 0)
                    {ut += "Boligtype\n";}
                    if (materiale_n == 0)
                    {ut += "Byggemateriale\n";}
                    if (standard_n == 0)
                    {ut += "Standard\n";}
                    if (egenandel_n == 0)
                    {ut += "Egenandel\n";}
                    if (!alarmJa.isSelected() && !alarmNei.isSelected())
                    {ut += "Alarmvalg\n";}
                    if (!utleidJa.isSelected() && !utleidNei.isSelected())
                    {ut += "Utleievalg\n";}
                    
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
                adr = fritidAdresse.getText();
                ar = Integer.parseInt(fritidAr.getText());
                kvm = Integer.parseInt(fritidKvm.getText());
                belop = Integer.parseInt(belopFritid.getText());
                belopInnbo = Integer.parseInt(belopFritidInnbo.getText());
                return true;
                    
            }
            
            
    }
    
    public void tegnNy()
    {
        if (hentInfo())
        {
            if( vindu.getRegister().getKundeliste().erKunde(kunde) == false )
            {
                vindu.getAnsatt().leggTilKundenøkel(kunde.getPersonnummer());
                register.getKundeliste().leggTil(kunde);
            }
            
            Forsikring forsikringen = register.nyFritidsboligforsikring(kunde, egenandelvalget, adr, ar, 
                       typevalget, materialevalget, standardvalget, kvm, belop, belopInnbo, alarm_b, utleid_b);
            
            kunde.leggTilNøkkel(forsikringen.getForsikringsnummer());
            
            JOptionPane.showMessageDialog(null, "Du har nå tegnet fritidsboligforsikring med nummer " 
                                          + forsikringen.getForsikringsnummer() + " på " + kunde.getFornavn() 
                                          + " " + kunde.getEtternavn() , "Bekreftelse", 
                                            JOptionPane.INFORMATION_MESSAGE);
        
            System.out.println(forsikringen);
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
        else if(e.getSource() == rediger)
        {
            for(Component component : getKomponenter(this))
                {
                    if((component instanceof JTextField))
                    {
                        JTextField tf = (JTextField)component;
                        tf.setEditable(true);
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
            
            //Må beregne pris på nytt!
            
        }
    }
}
