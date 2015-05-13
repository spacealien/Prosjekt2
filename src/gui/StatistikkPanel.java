/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

//import java.awt.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
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
    private StatistikkVindu statistikkVindu;
    
    
    private final String[] soket = {"", "Alle kunder som har en gitt type forsikring",
                               "Antall skademeldinger innen en gitt tidsperiode", "Antall"
                              + " tegnede forsikringer innen en gitt tidsperiode"};
    private final JComboBox<String> sokevelger;
    private final String[] forsikringer = {"", "Bilforsikring", "Båtforsikring",
                            "Hus- og innboforsikring", "Fritidsboligforsikring",
                            "Reiseforsikring", "Alle forsikringstyper"};
    private final JComboBox<String> forsikringsvelgeren;
    //private final String[] skadetyper = {"", "Brann", "Tyveri/Hærverk", "Ulykke", "Tap", "Annet"};
    private final String[] skadetypeKjoretoy = {"", "Ansvar", "Glasskade", "Vei-/slepehjelp", "Tyveri/Hærverk", "Ulykke", "Annet"};
    private final String[] skadetypeEiendom = {"", "Brann", "Innbrudd/tyveri", "Hærverk", "Naturskade", "Vann", "Fryser/matvarer", "Annet"};
    private final String[] skadetypeReise = {"", "Tapt/forsinket bagasje", "Tyveri/tap", "Forsinket transport", "Sykdom/ulykke", "Avbestilling", "Annet"};
    private JComboBox<String> skadetypevelgeren;
    private final String[] utgifter = {"", "Total utbetaling av erstatninger i løpet"
                                        + " av en gitt tidsperiode",
                                "Total utbetaling av erstatninger for en gitt"
                                 + " forsikringstype i løpet av en gitt tidsperiode"};
    private final JComboBox<String> utgiftsvelger;
    private final String[] inntekter = {"", "Total forsikringspremieinntekter i løpet"
                                        + " en gitt tidsperiode", 
                                    "Total forsikringspremieinntekter for en"
                                    + " gitt forsikringstype i løpet av en gitt tidsperiode"};
    private final JComboBox<String> inntektsvelger;
    
    //Statistikk
    private final String[] statistikk = {"", "Øking/minking av antall skademeldinger"
                                        + " innenfor en gitt tidsperiode", 
                                        "Øking/minking av skademeldinger av en "
                                   + "bestemt forsikringstype innenfor en gitt tidsperiode", 
                                     "Øking/minking av skademeldinger av en bestemt "
                                    + "skadetype innenfor en gitt tidsperiode",
                              "Øking/minking av de totale erstatningskostnadene "
                                + "innenfor en gitt tidsperiode", 
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
    private final JLabel skadetypelabel;
    private final JButton sokKnapp;
    //private final StatistikkVindu statestikkVindu;
    private int sok;
    private int utgift;
    private int inntekt;
    private int statistikken;
    
    private String forsikringsvalg;
    private String skadetypevalg;
    private Date startDato;
    private Date sluttDato;
    
    private ComboBoxModel<String> skadetypeModellKjoretoy;
    private ComboBoxModel<String> skadetypeModellEiendom;
    private ComboBoxModel<String> skadetypeModellReise;
    
    
 public StatistikkPanel(AnsattVindu v)
 {
        vindu = v;
      //  statestikkVindu = new StatistikkVindu(vindu);
        register = vindu.getRegister();
        sokevelger = new JComboBox<>(soket);
        utgiftsvelger = new JComboBox<>(utgifter);
        inntektsvelger = new JComboBox<>(inntekter);
        forsikringsvelgeren = new JComboBox<>(forsikringer);
        statistikkvelger = new JComboBox<>(statistikk);
        skadetypevelgeren = new JComboBox<>();
        skadetypevelgeren.setVisible(false);
        skadetypeModellKjoretoy = new DefaultComboBoxModel(skadetypeKjoretoy);
        skadetypeModellEiendom = new DefaultComboBoxModel(skadetypeEiendom);
        skadetypeModellReise = new DefaultComboBoxModel(skadetypeReise);
        skadetypelabel = new JLabel("Velg skadetype: ");
        skadetypelabel.setVisible(false);
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
        avansertSokPanel4.setLayout(new GridLayout(2,2,1,1));
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
        avansertSokPanel4.add(skadetypelabel);
        avansertSokPanel4.add(skadetypevelgeren);
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
        add(Box.createRigidArea(new Dimension(50,1)));
        add(avansertSokPanel3);
        sokKnapp.addActionListener(this);
        
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
            if (statistikkvelger.getSelectedIndex() == 3 || statistikkvelger.getSelectedIndex() == 5)
            {
                forsikringsvelgeren.addItemListener(new ItemListener()
                {
                @Override
                public void itemStateChanged(ItemEvent e)
                {
                    switch (forsikringsvelgeren.getSelectedIndex())
                    {
                        case 0:
                            sokKnapp.setEnabled(false);
                            skadetypelabel.setVisible(false);
                            skadetypevelgeren.setVisible(false);
                            break;
                        case 1:
                        case 2:
                            skadetypevelgeren.setModel(skadetypeModellKjoretoy);
                            skadetypelabel.setVisible(true);
                            skadetypevelgeren.setVisible(true);
                            sokKnapp.setEnabled(true);
                            break;
                        case 3:
                        case 4:
                            skadetypevelgeren.setModel(skadetypeModellEiendom);
                            skadetypelabel.setVisible(true);
                            skadetypevelgeren.setVisible(true);
                            sokKnapp.setEnabled(true);
                            break;
                        case 5:
                            skadetypevelgeren.setModel(skadetypeModellReise);
                            skadetypelabel.setVisible(true);
                            skadetypevelgeren.setVisible(true);
                            sokKnapp.setEnabled(true);
                            break;
                    }
                }});
            }
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
 
 public void hentInfo()
 {
    forsikringsvalg = forsikringsvelgeren.getItemAt(forsikringsvelgeren.getSelectedIndex());
    startDato = new Date((Integer.parseInt(stDatoAr.getText()) - 1900), (Integer.parseInt(stDatoMnd.getText()) - 1), Integer.parseInt(stDatoDag.getText()));
    sluttDato = new Date((Integer.parseInt(slDatoAr.getText()) - 1900), (Integer.parseInt(slDatoMnd.getText()) - 1), Integer.parseInt(slDatoDag.getText()));
 }
 
 public void alleKunderMedForsikring()
 {
     hentInfo();
     //Ut i tabell
    register.getAlleKunderMedForsikring(forsikringsvalg);
 }
 public void antSkademeldinger()
 {
     hentInfo();
     List<Skademelding> skademeldingsliste  = new ArrayList<>();
     for (Skademelding skademld : register.getSkademeldingsregister().alleSkademeldinger() )
     {
        if (skademld.getOpprettetDato().after(startDato) && skademld.getOpprettetDato().before(sluttDato) )
        {
            skademeldingsliste.add(skademld);
        }
     }
     //Tabell -> skademeldingsliste
 }
 public void antForsikringer()
 {
     hentInfo();
     List<Forsikring> forsikringsliste  = new ArrayList<>();
     for (Forsikring forsikring : register.getForsikringrsliste().alleForsikringer() )
     {
        if (forsikring.getStartdato().after(startDato) && forsikring.getStartdato().before(sluttDato) )
        {
            forsikringsliste.add(forsikring);
        }
     }
     //Tabell -> forsikringssliste
 }
 
    public void totalErstatning()
    {
     //Total utbetaling av erstatninger i en gitt periode
     hentInfo();
     double totalSum = 0.0;
     int antall = 0;
     double gjennomsnitt = 0;
        
     for( Skademelding skademelding : register.getSkademeldingsregister().alleSkademeldinger() )
     {
        if (skademelding.getOpprettetDato().after(startDato) && skademelding.getOpprettetDato().before(sluttDato) )
        {
            totalSum += skademelding.getErstatningsbelop();
            antall++;
        }
     }
     gjennomsnitt = totalSum / antall;
     //Utskrift av totalsummen og antall skademeldinger og gjennomsnitt. 
    }
    public void totalErstatningPaForsikring()
    {
     hentInfo(); 
     //Total utbetaling av erstatninger på forsikring i en gitt periode
     double totalSum = 0.0;
     int antall = 0;
     double gjennomsnitt = 0;
     for(Skademelding skademelding : register.getSkademeldingsregister().alleSkademeldinger())   
     {
         if(skademelding.getForsikring().getForsikringsType().equals(forsikringsvalg))
         {
             totalSum += skademelding.getErstatningsbelop();
             antall++;
         }
     }   
        
     gjennomsnitt = totalSum / antall;
        //Utskrift av totalsummen og antall skademeldinger og gjennomsnitt. 
    }
 
 
 public void totalPremieinntekt()
 {
   //Total utbetaling av erstatninger i en gitt periode
     hentInfo();
     double totalSum = 0.0;
     int antall = 0;
     double gjennomsnitt = 0;
     for (Inntekt inntekt : register.getAlleInntekter())
     {
         if (inntekt.getDato().after(startDato) && inntekt.getDato().before(sluttDato) )
        {
            totalSum += inntekt.getSum();
            antall++;
        }
     }
     gjennomsnitt = totalSum / antall;  
 }
 public void totalPremieinntektPaForsikringstype()
 {
     //Total utbetaling av inntekt på forsikringstype i en gitt periode
     hentInfo();
     double totalSum = 0.0;
     int antall = 0;
     double gjennomsnitt = 0;
     for (Inntekt inntekt : register.getAlleInntekter())
     {
         if (inntekt.getForsikring().getForsikringsType().equals(forsikringsvalg))
         {
            if (inntekt.getDato().after(startDato) && inntekt.getDato().before(sluttDato) )
            {
            totalSum += inntekt.getSum();
            antall++;
            }
         }
     }
     gjennomsnitt = totalSum / antall; 
 }
 
 public void statistikkSkademeldinger()
 {
     hentInfo();
     List<Skademelding> skademeldingsliste  = new ArrayList<>();
     int antallIPerioden = 0;
     int antallForAlltid = 0;
     for (Skademelding skademld : register.getSkademeldingsregister().alleSkademeldinger() )
     {
        if (skademld.getOpprettetDato().after(startDato) && skademld.getOpprettetDato().before(sluttDato) )
        {
            skademeldingsliste.add(skademld);
            antallIPerioden++;
        }
        antallForAlltid++;
     }
     
     long periodeIDager = (sluttDato.getTime() - startDato.getTime()) / 1000 / 60 / 60 / 24;
     System.out.println(periodeIDager);
     double gjennomsnittPerioden = antallIPerioden / periodeIDager;
     Date programStartDato = register.getForsikringrsliste().getForsikring(1000001).getStartdato();
     Date programSluttDato = new Date();
     long alltidIDager = (programStartDato.getTime() - programSluttDato.getTime()) / 1000 / 60 / 60 / 24;
     double gjennomsnittAlltid = antallForAlltid / alltidIDager;
     
     //ØKING/MINKING: gjennomsnittIPerioden - gjennomsnittAlltid;
 }
 public void statistikkSkademeldingPaForsikring()
 {
     hentInfo();
     List<Skademelding> skademeldingsliste  = new ArrayList<>();
     int antallIPerioden = 0;
     int antallForAlltid = 0;
     for (Skademelding skademld : register.getSkademeldingsregister().alleSkademeldinger() )
     {
        if (skademld.getForsikring().getForsikringsType().equals(forsikringsvalg))
        {
            if (skademld.getOpprettetDato().after(startDato) && skademld.getOpprettetDato().before(sluttDato) )
            {
                skademeldingsliste.add(skademld);
                antallIPerioden++;
            }
            antallForAlltid++;
        }
     }
     
     long periodeIDager = (sluttDato.getTime() - startDato.getTime()) / 1000 / 60 / 60 / 24;
     System.out.println(periodeIDager);
     double gjennomsnittPerioden = antallIPerioden / periodeIDager;
     Date programStartDato = register.getForsikringrsliste().getForsikring(1000001).getStartdato();
     Date programSluttDato = new Date();
     long alltidIDager = (programStartDato.getTime() - programSluttDato.getTime()) / 1000 / 60 / 60 / 24;
     double gjennomsnittAlltid = antallForAlltid / alltidIDager;
     
     //ØKING/MINKING: gjennomsnittIPerioden - gjennomsnittAlltid;
 }
 //DENNE ER EKSTRA, blir ikke kallt på noe sted! Skal vi ha den?
 public void statistikkSkademeldingPaSkadetype()
 {
     hentInfo();
     List<Skademelding> skademeldingsliste  = new ArrayList<>();
     int antallIPerioden = 0;
     int antallForAlltid = 0;
     for (Skademelding skademld : register.getSkademeldingsregister().alleSkademeldinger() )
     {
         if(skademld.getForsikring().getForsikringsType().equals(forsikringsvalg))
         {
            if (skademld.getSkadetype().equals(skadetypevalg))
            {
                if (skademld.getOpprettetDato().after(startDato) && skademld.getOpprettetDato().before(sluttDato) )
                {
                    skademeldingsliste.add(skademld);
                    antallIPerioden++;
                }
                antallForAlltid++;
            }
        }
     }
     
     long periodeIDager = (sluttDato.getTime() - startDato.getTime()) / 1000 / 60 / 60 / 24;
     System.out.println(periodeIDager);
     double gjennomsnittPerioden = antallIPerioden / periodeIDager;
     Date programStartDato = register.getForsikringrsliste().getForsikring(1000001).getStartdato();
     Date programSluttDato = new Date();
     long alltidIDager = (programStartDato.getTime() - programSluttDato.getTime()) / 1000 / 60 / 60 / 24;
     double gjennomsnittAlltid = antallForAlltid / alltidIDager;
     
     //ØKING/MINKING: gjennomsnittIPerioden - gjennomsnittAlltid;
 }
 
 public void statistikkErstatning()
 {
   hentInfo();
   double totalSumIPeriode = 0.0;
   double totalSum = 0.0;
     for (Skademelding skademld : register.getSkademeldingsregister().alleSkademeldinger() )
     {
        if (skademld.getOpprettetDato().after(startDato) && skademld.getOpprettetDato().before(sluttDato) )
            {
                totalSumIPeriode += skademld.getErstatningsbelop();
            }
            totalSum += skademld.getErstatningsbelop();
        
     }
     
     long periodeIDager = (sluttDato.getTime() - startDato.getTime()) / 1000 / 60 / 60 / 24;
     double gjennomsnittPerioden = totalSumIPeriode / periodeIDager;
     Date programStartDato = register.getForsikringrsliste().getForsikring(1000001).getStartdato();
     Date programSluttDato = new Date();
     long alltidIDager = (programStartDato.getTime() - programSluttDato.getTime()) / 1000 / 60 / 60 / 24;
     double gjennomsnittAlltid = totalSum / alltidIDager;
     
     //ØKING/MINKING: gjennomsnittIPerioden - gjennomsnittAlltid;  
 }
 public void statistikkErstatningPaSkadetype()
 {
    hentInfo();
    double totalSumIPeriode = 0.0;
    double totalSum = 0.0;
    for (Skademelding skademld : register.getSkademeldingsregister().alleSkademeldinger() )
    {
         if(skademld.getForsikring().getForsikringsType().equals(forsikringsvalg))
         {
            if (skademld.getSkadetype().equals(skadetypevalg))
            {   
                if (skademld.getOpprettetDato().after(startDato) && skademld.getOpprettetDato().before(sluttDato) )
                {
                    totalSumIPeriode += skademld.getErstatningsbelop();
                }
                totalSum += skademld.getErstatningsbelop();
            }
         }
    }
     
     long periodeIDager = (sluttDato.getTime() - startDato.getTime()) / 1000 / 60 / 60 / 24;
     double gjennomsnittPerioden = totalSumIPeriode / periodeIDager;
     Date programStartDato = register.getForsikringrsliste().getForsikring(1000001).getStartdato();
     Date programSluttDato = new Date();
     long alltidIDager = (programStartDato.getTime() - programSluttDato.getTime()) / 1000 / 60 / 60 / 24;
     double gjennomsnittAlltid = totalSum / alltidIDager;
     
     //ØKING/MINKING: gjennomsnittIPerioden - gjennomsnittAlltid; 
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
                            {
                                feilMelding("Fyll ut alle feltene for dato");
                            }
                            
                            if (forsikringsvelgeren.getSelectedIndex() == 0)
                            {
                                feilMelding("Du må velge forsikringstype");
                            }
                           
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
            StatistikkVindu statistikkVindu = new StatistikkVindu();
            
            
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
                        
                }
                
            }
            
            else if (statistikkvelger.isEnabled())
            { System.out.println("hei");
                switch (statistikkvelger.getSelectedIndex())
                {
                    case 1:
                        if (sjekkDato())
                        statistikkSkademeldinger();
                        break;
                    case 2:
                    if (sjekkDatoOgForsikringsvelger())
                        statistikkSkademeldingPaForsikring();
                        
                        break;
                    case 3:
                    
                        statistikkErstatning();
                        break;
                    case 4:
                    if (sjekkDatoOgForsikringsvelger())
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
