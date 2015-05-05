/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.Box.*;
import objekter.*;
import register.*;

/**
 *
 * @author Marthejansonskogen
 */
public class StatistikkPanel extends JPanel implements ActionListener
{
    private AnsattVindu vindu;
    private HovedRegister register;
    private final String[] soket = {"", "Alle kunder som har en gitt type forsikring",
                               "Antall skademeldinger innen en gitt tidsperiode", "Antall"
                              + " tegnede forsikringer innen en gitt tidsperiode"};
    private final JComboBox<String> sokevelger;
    private final String[] forsikringer = {"", "Bilforsikring", "Båtforsikring",
                            "Hus- og innboforsikring", "Fritidsboligforsikring",
                            "Reiseforsikring", "Alle forsikringstyper"};
    private final JComboBox<String> forsikringsvelgeren;
    private final String[] utgifter = {"", "Total utbetaling av erstatninger i løpet"
                                        + " av en gitt tidsperiode",
                                "Total utbetaling av erstatninger for en gitt"
                                 + " forsikringstype i løpet av en gitt tidsperiode",
                                "Utbetaling til en gitt forsikringskunde i løpet"
                                + " av kundeforholdet"};
    private final JComboBox<String> utgiftsvelger;
    private final String[] inntekter = {"", "Total forsikringspremieinntekter i løpet"
                                        + " en gitt tidsperiode", 
                                    "Total forsikringspremieinntekter for en"
                                    + " gitt forsikringstype i løpet av en gitt tidsperiode",
                                    "Forsikringspremieinntekter på en gitt "
                                + "forsikringskunde i løpet av kundeforholdet"};
    private final JComboBox<String> inntektsvelger;
    
    //Statistikk
    private final String[] statistikk = {"", "Øking/minking av antall skademeldinger"
                                        + " innenfor en gitt tidsperiode", 
                                        "Øking/minking av skademeldinger av en "
                                   + "bestemt type innenfor en gitt tidsperiode", 
                              "Øking/minking av de totale erstatningskostnadene", 
                                "Øking/minking av erstatningskostnadene for en "
                                + "gitt skadetype innenfor en gitt tidsperiode", 
                                   "Type forsikringer rangert etter antall"};
    private final JComboBox<String> statistikkvelger;
    private final JTextField stDatoDag;
    private final JTextField stDatoMnd;
    private final JTextField stDatoAr;
    private final JTextField slDatoDag;
    private final JTextField slDatoMnd;
    private final JTextField slDatoAr;
    private final JButton sokKnapp;
    private int sok;
    private int utgift;
    private int inntekt;
    private int statistikken;
    
    
 public StatistikkPanel(AnsattVindu v)
 {
        vindu = v;
        register = vindu.getRegister();
        sokevelger = new JComboBox<>(soket);
        utgiftsvelger = new JComboBox<>(utgifter);
        inntektsvelger = new JComboBox<>(inntekter);
        forsikringsvelgeren = new JComboBox<>(forsikringer);
        statistikkvelger = new JComboBox<>(statistikk);
        stDatoDag = new JTextField(2);
        stDatoMnd = new JTextField(2);
        stDatoAr = new JTextField(4);
        slDatoDag = new JTextField(2);
        slDatoMnd = new JTextField(2);
        slDatoAr = new JTextField(4);
        sokKnapp = new JButton("Søk");
        sokKnapp.setEnabled(false);
        sokKnapp.addActionListener(this);
        
        JPanel avansertSokPanel1 = new JPanel();
        JPanel avansertSokPanel2 = new JPanel();
        JPanel avansertSokPanel3 = new JPanel();
        JPanel avansertSokPanel4 = new JPanel();
        avansertSokPanel3.setLayout(new BoxLayout(avansertSokPanel3, BoxLayout.PAGE_AXIS));
        avansertSokPanel1.setLayout(new GridLayout(11,1,2,2));
        avansertSokPanel2.setLayout(new GridLayout(8,3,2,2));
        avansertSokPanel1.add(new JLabel("Søk etter:"));
        avansertSokPanel1.add(sokevelger);
        avansertSokPanel1.add(new JLabel());
        avansertSokPanel1.add(new JLabel("Skriv ut utgifter:"));
        avansertSokPanel1.add(utgiftsvelger);
        avansertSokPanel1.add(new JLabel());
        avansertSokPanel1.add(new JLabel("Skriv ut inntekter:"));
        avansertSokPanel1.add(inntektsvelger);
        avansertSokPanel1.add(new JLabel());
        avansertSokPanel1.add(new JLabel("Skriv ut statistikk om:"));
        avansertSokPanel1.add(statistikkvelger);
        
        avansertSokPanel4.add(new JLabel("Velg forsikringstype: "));
        avansertSokPanel4.add(forsikringsvelgeren);
        avansertSokPanel2.add(new JLabel("Fra og med: "));
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(new JLabel("Dag: (dd)"));
        avansertSokPanel2.add(new JLabel("Måned: (mm)"));
        avansertSokPanel2.add(new JLabel("År: (åååå)"));
        avansertSokPanel2.add(stDatoDag);
        avansertSokPanel2.add(stDatoMnd);
        avansertSokPanel2.add(stDatoAr);
        avansertSokPanel2.add(new JLabel("Til og med: "));
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(new JLabel("Dag: (dd)"));
        avansertSokPanel2.add(new JLabel("Måned: (mm)"));
        avansertSokPanel2.add(new JLabel("År: (åååå)"));
        avansertSokPanel2.add(slDatoDag);
        avansertSokPanel2.add(slDatoMnd);
        avansertSokPanel2.add(slDatoAr);
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(sokKnapp);
        avansertSokPanel3.add(avansertSokPanel4);
        avansertSokPanel3.add(avansertSokPanel2);
        add(avansertSokPanel1);
        add(Box.createRigidArea(new Dimension(100,1)));
        add(avansertSokPanel3);
        
        sokevelger.addItemListener(new ItemListener()
        {
        @Override
        public void itemStateChanged(ItemEvent e)
        {
        if (sokevelger.getSelectedIndex() != 0)
        {
            sok = sokevelger.getSelectedIndex();
            utgiftsvelger.setEnabled(false);
            inntektsvelger.setEnabled(false);
            statistikkvelger.setEnabled(false);
            sokKnapp.setEnabled(true);
        }
        else if (sokevelger.getSelectedIndex() == 0)
        {
            sok = sokevelger.getSelectedIndex();
            utgiftsvelger.setEnabled(true);
            inntektsvelger.setEnabled(true);
            statistikkvelger.setEnabled(true);
        }
        }});
        utgiftsvelger.addItemListener(new ItemListener()
        {
        @Override
        public void itemStateChanged(ItemEvent e)
        {
        if (utgiftsvelger.getSelectedIndex() != 0)
        {
            utgift = utgiftsvelger.getSelectedIndex();
            sokevelger.setEnabled(false);
            inntektsvelger.setEnabled(false);
            statistikkvelger.setEnabled(false);
            sokKnapp.setEnabled(true);
        }
        else if (utgiftsvelger.getSelectedIndex() == 0)
            {
            utgift = utgiftsvelger.getSelectedIndex();
            sokevelger.setEnabled(true);
            inntektsvelger.setEnabled(true);
            statistikkvelger.setEnabled(true);
        }
        }});
        
        inntektsvelger.addItemListener(new ItemListener()
        {
        @Override
        public void itemStateChanged(ItemEvent e)
        {
        if (inntektsvelger.getSelectedIndex() != 0)
        {
            inntekt = inntektsvelger.getSelectedIndex();
            sokevelger.setEnabled(false);
            utgiftsvelger.setEnabled(false);
            statistikkvelger.setEnabled(false);
            sokKnapp.setEnabled(true);
        }
        else if (inntektsvelger.getSelectedIndex() == 0)
        {
            inntekt = inntektsvelger.getSelectedIndex();
            sokevelger.setEnabled(true);
            utgiftsvelger.setEnabled(true);
            statistikkvelger.setEnabled(true);
        }
        }});
        
        statistikkvelger.addItemListener(new ItemListener()
        {
        @Override
        public void itemStateChanged(ItemEvent e)
        {
        if (statistikkvelger.getSelectedIndex() != 0)
        {
            statistikken = statistikkvelger.getSelectedIndex();
            sokevelger.setEnabled(false);
            utgiftsvelger.setEnabled(false);
            inntektsvelger.setEnabled(false);
            sokKnapp.setEnabled(true);
        }
        else if (statistikkvelger.getSelectedIndex() == 0)
                {
                    statistikken = statistikkvelger.getSelectedIndex();
                    sokevelger.setEnabled(true);
                    utgiftsvelger.setEnabled(true);
                    inntektsvelger.setEnabled(true);
                }
        }});
        
        
 }
 
 public void alleKunderMedForsikring()
 {
     
 }
 public void antSkademeldinger()
 {
     
 }
 public void antForsikringer()
 {
     
 }
 
 public void totalErstatning()
 {
     
 }
 public void totalErstatningPaForsikring()
 {
     
 }
 public void totalErstatningPaKunde()
 {
     
 }
 
 public void totalPremieinntekt()
 {
     
 }
 public void totalPremieinntektPaForsikringstype()
 {
     
 }
 public void premieInntektPaKunde()
 {
     
 }
 
 public void statistikkSkademeldinger()
 {
     
 }
 public void statistikkSkademeldingPaForsikring()
 {
     
 }
 public void statistikkErstatning()
 {
     
 }
 public void statistikkErstatningPaSkadetype()
 {
     
 }
 public void typeForsikringPaAntall()
 {
     
 }
 
public void feilMelding(String t)
{
    JOptionPane.showMessageDialog(null, t, "Feilmelding", JOptionPane.ERROR_MESSAGE);
}

public boolean sjekkDato()
{
    if (stDatoAr.getText().equals("") || stDatoMnd.getText().equals("") || 
                        stDatoDag.getText().equals("") || slDatoAr.getText().equals("") || 
                        slDatoMnd.getText().equals("") || slDatoDag.getText().equals(""))
    {
        feilMelding("Fyll ut alle feltene for dato");
        return false;
    }
    else
    {
        return true;
    }
}
public boolean sjekkDatoOgForsikringsvelger()
{
   if (stDatoAr.getText().equals("") || stDatoMnd.getText().equals("") || 
                        stDatoDag.getText().equals("") || slDatoAr.getText().equals("") || 
                        slDatoMnd.getText().equals("") || slDatoDag.getText().equals("") 
                                || forsikringsvelgeren.getSelectedIndex() == 0)
                        {
                            if(stDatoAr.getText().equals("") || stDatoMnd.getText().equals("") || 
                        stDatoDag.getText().equals("") || slDatoAr.getText().equals("") || 
                        slDatoMnd.getText().equals("") || slDatoDag.getText().equals(""))
                            {feilMelding("Fyll ut alle feltene for dato"); }
                            
                            if (forsikringsvelgeren.getSelectedIndex() == 0)
                            {feilMelding("Du må velge forsikringstype");}
                           
                            return false;
                        } 
   else
   {
       return true;
   }
}
 
 
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == sokKnapp)
        {
            if (sokevelger.isEnabled())
            {
                switch (sokevelger.getSelectedIndex())
                {
                    case 1:
                        if(forsikringsvelgeren.getSelectedIndex() != 0)
                        alleKunderMedForsikring();
                        else
                            feilMelding("Du må velge forsikringstype");
                        
                        break;
                    case 2:
                        if(forsikringsvelgeren.getSelectedIndex() != 0)
                            antSkademeldinger();
                        else
                            feilMelding("Du må velge forsikringstype");
                        
                        break;
                    case 3:
                        if(forsikringsvelgeren.getSelectedIndex() != 0)
                            antForsikringer();
                        else
                            feilMelding("Du må velge forsikringstype");
                        
                        break;
                }
            }
            else if (utgiftsvelger.isEnabled())
            {
                switch (utgiftsvelger.getSelectedIndex())
                {
                    case 1:
                        if (!sjekkDato())
                        totalErstatning();
                        
                    break;
                    case 2:
                        if (!sjekkDatoOgForsikringsvelger())
                            totalErstatningPaForsikring();
                        
                        break;
                    case 3:
                        //Må velge kunde på en eller annen måte
                        totalErstatningPaKunde();
                        break;
                }
            }
            else if (inntektsvelger.isEnabled())
            {
                switch (inntektsvelger.getSelectedIndex())
                {
                    case 1:
                        if (!sjekkDato())
                        totalPremieinntekt();
                        
                    break;
                    case 2:
                        if (!sjekkDatoOgForsikringsvelger())
                            totalPremieinntektPaForsikringstype();
                        
                        break;
                    case 3:
                        //Må velge kunde på en eller annen måte
                        premieInntektPaKunde();
                        break;
                        
                }
                
            }
            
            else if (statistikkvelger.isEnabled())
            {
                switch (statistikkvelger.getSelectedIndex())
                {
                    case 1:
                        if (!sjekkDato())
                        statistikkSkademeldinger();
                        break;
                    case 2:
                    if (!sjekkDatoOgForsikringsvelger())
                        statistikkSkademeldingPaForsikring();
                        
                        break;
                    case 3:
                    
                        statistikkErstatning();
                        break;
                    case 4:
                    if (!sjekkDatoOgForsikringsvelger())
                        statistikkErstatningPaSkadetype();
                        
                        break;
                    case 5:
                        typeForsikringPaAntall();
                        break;
                }
            }
            
        }
    }
}
