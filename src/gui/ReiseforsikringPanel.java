/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import objekter.*;
import register.*;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class ReiseforsikringPanel extends JPanel implements ActionListener
{
    private AnsattVindu vindu;
    private HovedRegister register;
    private final JTextField reiseBelop;
    private final JTextField reiseTilbud;
    private final JTextField antbarn;
    private final JLabel antbarnLabel;
    private final JRadioButton forsorgerJa;
    private final JRadioButton forsorgerNei;
    private final JButton reiseGiTilbud;
    private final JButton beregnPris;
    String[] sone = {"", "Norden", "Europa", "Verden"};
    JComboBox<String> sonevelger;
    String[] egenandel = {"", "2000", "4000", "8000", "12000", "16000", "20000", "30000"};
    JComboBox<String> egenandelsvelger;
    private final Kunde kunde;
    
    private int antBarn;
    private int belop;
    private boolean forsorger_b;
    private int sone_n;
    private int egenandelvalget;
    
    public ReiseforsikringPanel(Kunde k, AnsattVindu v)
    {
        vindu = v;
        register = vindu.getRegister();
        kunde = k;
        reiseBelop = new JTextField( 7 );
        reiseTilbud = new JTextField( 7 );
        antbarn = new JTextField(2);
        antbarnLabel = new JLabel("Forsørger antall barn: ");
        antbarn.setEnabled(false);
        antbarnLabel.setEnabled(false);
        reiseGiTilbud = new JButton("Tegn forsikring");
        beregnPris = new JButton("Beregn pris");
        sonevelger = new JComboBox<>(sone);
        egenandelsvelger = new JComboBox<>(egenandel);
        forsorgerJa = new JRadioButton("Ja");
        forsorgerJa.setMnemonic(KeyEvent.VK_J);
        forsorgerNei = new JRadioButton("Nei");
        forsorgerNei.setMnemonic(KeyEvent.VK_N);
        ButtonGroup forsorger = new ButtonGroup();
        forsorger.add(forsorgerJa);
        forsorger.add(forsorgerNei);  
        
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
        tegnReisePanel1.add(new JLabel());
        tegnReisePanel1.add(new JLabel("Egenandel: "));
        tegnReisePanel1.add(egenandelsvelger);
        tegnReisePanel1.add(new JLabel());
        tegnReisePanel1.add(new JLabel());
        tegnReisePanel1.add(new JLabel("Forsikringsbeløp: "));
        tegnReisePanel1.add(reiseBelop);
        tegnReisePanel1.add(new JLabel());
        tegnReisePanel1.add(beregnPris);
        tegnReisePanel1.add(new JLabel("Foreslått tilbud: "));
        tegnReisePanel1.add(reiseTilbud);
        tegnReisePanel1.add(new JLabel());
        tegnReisePanel1.add(reiseGiTilbud);
        add(tegnReisePanel1);
        
        reiseGiTilbud.addActionListener(this);
        beregnPris.addActionListener(this);
        
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
    
    
    // ikke fjern, ikke ferdig
    public void visForsikring( Forsikring f)
    {
        
    }
    
    
    
    
    public boolean hentInfo()
    {
        sone_n = sonevelger.getSelectedIndex();
        int egenandel_n = egenandelsvelger.getSelectedIndex();
                
                
                if(egenandel_n == 0 || sone_n == 0 || (!forsorgerJa.isSelected() && !forsorgerNei.isSelected()))
                {
                    String ut = "Det mangler informasjon om:\n";
                    if (sone_n == 0)
                {ut += "Sone\n";}
                    if (egenandel_n == 0)
                {ut += "Egenandel\n";}
                    if (!forsorgerJa.isSelected() && !forsorgerNei.isSelected())
                    {ut += "Forsørgervalg\n";}
                    ut += "\nVennligst fyll ut denne informasjonen og prøv igjen.";
                            JOptionPane.showMessageDialog(null, ut, "Feilmelding",
                                                JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                else
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
                
                belop = Integer.parseInt(reiseBelop.getText());
                return true;
                }
                
                
               
    }
    
    public void beregnPris()
    {
        if (hentInfo())
        {
           //Beregn pris
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
            
            Reiseforsikring nyForsikring = new Reiseforsikring(kunde, egenandelvalget, forsorger_b, antBarn, sone_n, belop);
            register.nyForsikring(nyForsikring);
        }
    }
    
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
    }
}
