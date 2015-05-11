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
 * @author Odd, Thomas, Marthe
 */
public class ReiseforsikringPanel extends JPanel implements ActionListener, ForsikringsPanel
{
    private AnsattVindu vindu;
    private HovedRegister register;
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
    private final JButton vilkar;
    String[] sone = {"", "Norden", "Europa", "Verden"};
    JComboBox<String> sonevelger;
    String[] egenandel = {"", "2000", "4000", "8000", "12000", "16000", "20000", "30000"};
    JComboBox<String> egenandelsvelger;
    String[] dekning = {"", "Reise", "Reise Pluss"};
    JComboBox<String> dekningvelger;
    private final Kunde kunde;
    
    private int antBarn;
    private int belop;
    private boolean forsorger_b;
    private int sone_n;
    private String sonevalget;
    private int egenandelvalget;
    private String dekningvalget;
    private JButton rediger = new JButton("Rediger forsikring");
    private JButton lagreNyInfo = new JButton("Lagre forsikring");
    private JButton deaktiver = new JButton("Si opp forsikring");
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
        beregnPris = new JButton("Beregn pris");
        vilkar = new JButton("Se vilkår");
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
        tegnReisePanel1.add(vilkar);
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
        add(tegnReisePanel1);
        
        reiseGiTilbud.addActionListener(this);
        beregnPris.addActionListener(this);
        vilkar.addActionListener(this);
        rediger.addActionListener(this);
        lagreNyInfo.addActionListener(this);
        deaktiver.addActionListener(this);
        
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
       this.forsikring = (Reiseforsikring)f;
        sonevelger.setSelectedItem(forsikring.getSone());
        reiseBelop.setText(String.valueOf(forsikring.getBelopet()));
        egenandelsvelger.setSelectedItem(String.valueOf(forsikring.getEgenandel()));
        dekningvelger.setSelectedItem(forsikring.getVilkar());
        if (forsikring.isForsorger())
        {
            forsorgerJa.setSelected(true);
            antbarn.setText(String.valueOf(forsikring.getAntBarn()));
        }
        else
            forsorgerNei.setSelected(true);
        
        if (forsikring.erAktiv())
        {
            knappePanel.setLayout(new BoxLayout(knappePanel, BoxLayout.PAGE_AXIS));
            knappePanel.add(rediger);
            knappePanel.add(deaktiver);
            add(knappePanel);
        }
        
        tilbudLabel.setText("Årlig premie: ");
        
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
                    else if (component.equals(reiseGiTilbud))
                    {
                        component.setVisible(false);
                    }
                    else if (component.equals(beregnPris))
                    {
                        component.setVisible(false);
                    }
                }
    }
    
    
    
    
    public boolean hentInfo()
    {
        sone_n = sonevelger.getSelectedIndex();
        int egenandel_n = egenandelsvelger.getSelectedIndex();
        int dekning_n = dekningvelger.getSelectedIndex();
                
                
                if(egenandel_n == 0 || sone_n == 0 || dekning_n == 0 || (!forsorgerJa.isSelected() && !forsorgerNei.isSelected()))
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
                register.getKundeliste().leggTil(kunde);
            }
            
            Reiseforsikring nyForsikring = new Reiseforsikring(kunde, egenandelvalget, dekningvalget, forsorger_b, antBarn, sonevalget, belop);
            register.nyForsikring(nyForsikring);
            
            if(kundePanel != null)
                kundePanel.oppdaterVindu();
            
            vindu.visInformasjon("Beskjed", "Du har nå tegnet en ny forsikring på " + nyForsikring.getKunde().getFornavn() + " " + nyForsikring.getKunde().getEtternavn());
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
        else if (e.getSource() == vilkar)
        {
            //Vis vilkår
        }
        else if (e.getSource() == rediger)
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
            for(Component component : getKomponenter(this))
                {
                    if((component instanceof JTextField))
                    {
                        JTextField tf = (JTextField)component;
                        tf.setEditable(true);
                    }
                }
            revalidate();
            repaint();
        }
        else if (e.getSource() == lagreNyInfo)
        {
            if (hentInfo())
            {
            forsikring.setForsorger(forsorger_b);
            forsikring.setAntBarn(antBarn);
            forsikring.setSone(sonevalget);
            forsikring.setEgenandel(egenandelvalget);
            forsikring.setBelopet(belop);
            forsikring.setVilkar(dekningvalget);
            //Må beregne ny pris også
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
