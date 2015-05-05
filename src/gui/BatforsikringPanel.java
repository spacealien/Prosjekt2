/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import objekter.*;
import register.*;


public class BatforsikringPanel extends JPanel implements ActionListener
{
    private AnsattVindu vindu;
    private HovedRegister register;
    private Eier eier;
    
    private final JTextField eierFornavn;
    private final JTextField eierEtternavn;
    private final JTextField eierTlf;
    private final JTextField eierAdresse;
    private JPanel eierPanel;
    
    private final JTextField batRegnr;
    private final JTextField batModell;
    private final JTextField batTilbud;
    private final JTextField batMerke;
    private final JTextField batLengde;
    private final JTextField batHk;
    private final JTextField batArsmodell;
    private final JRadioButton vekterJa;
    private final JRadioButton vekterNei;
    private final String[] battype = {"","Skjærgårdsjeep", "Cabin cruiser", "Rib", "Annen småbåt",
                        "Speedbåt", "Seilbåt", "Snekke"};
    JComboBox<String> battypevelger;
    String[] egenandel = {"", "2000", "4000", "8000", "12000", "16000", "20000", "30000"};
    JComboBox<String> egenandelsvelger;
    private final JButton annenEier;
    private final JButton beregnPris;
    private final JButton batGiTilbud;
    private final Kunde kunde;
    
    private String reg;
    private String merke;
    private String modell;
    private int hk;
    private int ar;
    private int lengde;
    private String typevalget;
    private int egenandelvalget;
    private boolean vekter_b;
    
    public BatforsikringPanel(Kunde k, AnsattVindu v)
    {
     
        vindu = v;
        register = vindu.getRegister();
        kunde = k;
        batRegnr = new JTextField( 7 );
        batModell = new JTextField( 7 );
        batTilbud = new JTextField( 7 );
        batMerke = new JTextField( 7 );
        batLengde = new JTextField( 7 );
        batArsmodell = new JTextField( 7 );
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
        annenEier = new JButton("Trykk her");
        batGiTilbud = new JButton("Tegn forsikring");
        beregnPris = new JButton("Beregn pris");
        
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
        tegnBatPanel1.setLayout(new GridLayout(7,4,2,10));
        vekt.add(vekterJa);
        vekt.add(vekterNei);
        tegnBatPanel1.add(new JLabel("Reg.nummer: "));
        tegnBatPanel1.add(batRegnr);
        tegnBatPanel1.add(new JLabel("Merke: "));
        tegnBatPanel1.add(batMerke);
        tegnBatPanel1.add(new JLabel("Modell: "));
        tegnBatPanel1.add(batModell);
        tegnBatPanel1.add(new JLabel("Lengde: "));
        tegnBatPanel1.add(batLengde);
        tegnBatPanel1.add(new JLabel("Årsmodell: "));
        tegnBatPanel1.add(batArsmodell);
        tegnBatPanel1.add(new JLabel("Hestekrefter: "));
        tegnBatPanel1.add(batHk);
        tegnBatPanel1.add(new JLabel("Type båt: "));
        tegnBatPanel1.add(battypevelger);
        tegnBatPanel1.add(new JLabel("Vekter: "));
        tegnBatPanel1.add(vekt);
        tegnBatPanel1.add(new JLabel("Er eier annen enn kunde?"));
        tegnBatPanel1.add(annenEier);
        tegnBatPanel1.add(new JLabel("Egenandel: "));
        tegnBatPanel1.add(egenandelsvelger);
        tegnBatPanel1.add(new JLabel());
        tegnBatPanel1.add(beregnPris);
        tegnBatPanel1.add(new JLabel("Foreslått tilbud: "));
        tegnBatPanel1.add(batTilbud);
        tegnBatPanel1.add(new JLabel());
        tegnBatPanel1.add(batGiTilbud);
        add(tegnBatPanel1);
        
        batGiTilbud.addActionListener(this);
        annenEier.addActionListener(this);
        beregnPris.addActionListener(this);
    }
    
    public boolean hentInfo()
    {
           
              
            int type_n = battypevelger.getSelectedIndex();
            int egenandel_n = egenandelsvelger.getSelectedIndex();
            
            if (type_n == 0 || egenandel_n == 0 || (!vekterJa.isSelected() && !vekterNei.isSelected()) )
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
                    
            reg = batRegnr.getText();
            merke = batMerke.getText();
            modell = batModell.getText();
            hk = Integer.parseInt(batHk.getText());
            ar = Integer.parseInt(batArsmodell.getText());
            lengde = Integer.parseInt(batLengde.getText());
            typevalget = battypevelger.getItemAt(type_n);
            egenandelvalget = Integer.parseInt(egenandelsvelger.getItemAt(egenandel_n));
            return true;
            }
    }
    
    public void beregnPris()
    {
        if (hentInfo())
        {
            //Beregner pris
        }
    }
            
    public void tegnNy()
    {
        if(hentInfo())
        {
            Forsikring forsikringen = register.nyBatForsikring(kunde, egenandelvalget, reg,
                                 merke, modell, typevalget, hk, 
                                 ar, vekter_b, lengde);
            Kjoretoyforsikring forsikring = (Kjoretoyforsikring)forsikringen;
            if (eier != null)
            forsikring.setEier(eier);
            
            JOptionPane.showMessageDialog(null, "Du har nå tegnet båtforsikring med nummer " + forsikring.getForsikringsnummer() + " på " + kunde.getFornavn() + " " + kunde.getEtternavn() , "Bekreftelse", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(forsikring);
        }
    }
    
    
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
            int result = JOptionPane.showConfirmDialog(null, eierPanel, 
               "Vennligst fyll ut bileiers kontaktinformasjon:", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION)
         {
          eier = new Eier(eierFornavn.getText(), eierEtternavn.getText(), eierAdresse.getText(), eierTlf.getText());
         }
        }
    }
}
