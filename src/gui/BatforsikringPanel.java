/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.*;
import objekter.*;
import register.*;


public class BatforsikringPanel extends JPanel implements ActionListener, ForsikringsPanel
{
    private AnsattVindu vindu;
    private HovedRegister register;
    private Eier eier;
    private BatForsikring forsikring = null;
    private KundePanel kundePanel;
    
    private final JTextField eierFornavn;
    private final JTextField eierEtternavn;
    private final JTextField eierTlf;
    private final JTextField eierAdresse;
    private JPanel eierPanel;
    
    private final JTextField batRegnr;
    private final JTextField batModell;
    private final JTextField batVerdi;
    private final JTextField batTilbud;
    private final JTextField batMerke;
    private final JTextField batLengde;
    private final JTextField batHk;
    private final JTextField batArsmodell;
    private JLabel tilbudLabel;
    private final JRadioButton vekterJa;
    private final JRadioButton vekterNei;
    private final String[] battype = {"","Skjærgårdsjeep", "Cabin cruiser", "Rib", "Annen småbåt",
                        "Speedbåt", "Seilbåt", "Snekke"};
    JComboBox<String> battypevelger;
    String[] egenandel = {"", "2000", "4000", "8000", "12000", "16000", "20000", "30000"};
    JComboBox<String> egenandelsvelger;
    String[] dekning = {"", "Ansvar", "Delkasko", "Kasko", "Båt Pluss"};
    JComboBox<String> dekningvelger;
    private final JButton annenEier;
    private final JButton beregnPris;
    private final JButton batGiTilbud;
    private final JButton vilkar;
    private final Kunde kunde;
    
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
        annenEier = new JButton("Trykk her");
        batGiTilbud = new JButton("Tegn forsikring");
        batGiTilbud.setVisible(false);
        beregnPris = new JButton("Beregn pris");
        vilkar = new JButton("Vis vilkår");
        
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
        tegnBatPanel2.add(new JLabel());
        tegnBatPanel2.add(vilkar);
        tegnBatPanel2.add(new JLabel("Velg dekning: "));
        tegnBatPanel2.add(dekningvelger);
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
        tegnBatPanel2.add(new JLabel("Er eier annen enn kunde?"));
        tegnBatPanel2.add(annenEier);
        tegnBatPanel1.add(new JLabel("Årsmodell: "));
        tegnBatPanel1.add(batArsmodell);
        tegnBatPanel2.add(new JLabel());
        tegnBatPanel2.add(beregnPris);
        tegnBatPanel1.add(new JLabel("Hestekrefter: "));
        tegnBatPanel1.add(batHk);
        tegnBatPanel2.add(tilbudLabel);
        tegnBatPanel2.add(batTilbud);
        tegnBatPanel1.add(new JLabel("Type båt: "));
        tegnBatPanel1.add(battypevelger);
        tegnBatPanel1.add(new JLabel("Verdi "));
        tegnBatPanel1.add(batVerdi);
        tegnBatPanel2.add(new JLabel());
        tegnBatPanel2.add(batGiTilbud);
        hovedPanel.add(tegnBatPanel1);
        hovedPanel.add(Box.createHorizontalStrut(5));
        hovedPanel.add(new JSeparator(SwingConstants.VERTICAL));
        hovedPanel.add(Box.createHorizontalStrut(5));
        tegnBatPanel2.setPreferredSize(tegnBatPanel1.getPreferredSize());
        hovedPanel.add(tegnBatPanel2);
        add(hovedPanel);
        
        batGiTilbud.addActionListener(this);
        annenEier.addActionListener(this);
        beregnPris.addActionListener(this);
        vilkar.addActionListener(this);
        rediger.addActionListener(this);
        lagreNy.addActionListener(this);
        deaktiver.addActionListener(this);
    }

    public void visForsikring( Forsikring f )
    {
        this.forsikring = (BatForsikring) f;
        batRegnr.setText(forsikring.getRegistreringsnmmer());
        batMerke.setText(forsikring.getFabrikant());
        batModell.setText(forsikring.getModell());
        //batVerdi.setText();
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
        tilbudLabel.setText("Årlig premie: ");
        tilbudLabel.setVisible(true);
        batTilbud.setVisible(true);
        revalidate();
        repaint();
        
        disableFelter( this, batGiTilbud, beregnPris );
    }
    
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
                    
            reg = batRegnr.getText();
            belop = Integer.parseInt(batVerdi.getText());
            merke = batMerke.getText();
            modell = batModell.getText();
            hk = Integer.parseInt(batHk.getText());
            ar = Integer.parseInt(batArsmodell.getText());
            lengde = Integer.parseInt(batLengde.getText());
            typevalget = battypevelger.getItemAt(type_n);
            egenandelvalget = Integer.parseInt(egenandelsvelger.getItemAt(egenandel_n));
            dekningvalget = dekningvelger.getItemAt(dekning_n);
            return true;
            }
    }
    
    public void beregnPris()
    {
        if (hentInfo())
        {
            double foreslåttPris = ForsikringsKalulator.beregnBatforsikring(egenandelvalget, dekningvalget, belop, hk, ar, vekter_b, lengde  );
                    
            NumberFormat formatter = new DecimalFormat("#0.00"); 
            batTilbud.setVisible(true);
            batTilbud.setText(formatter.format(foreslåttPris) + " Kr/År");
        }
    }
    
    public void leggTilKundePanel( KundePanel panel )
    {
        kundePanel = panel;
    }
            
    public void tegnNy()
    {
        if(hentInfo())
        {
            if( vindu.getRegister().getKundeliste().erKunde(kunde) == false )
            {
                vindu.getAnsatt().leggTilKundenøkel(kunde.getPersonnummer());
                register.nyKunde(kunde);
            }
            
            Forsikring forsikringen = new BatForsikring(kunde, egenandelvalget, dekningvalget, reg, belop,
                                 merke, modell, typevalget, hk, 
                                 ar, vekter_b, lengde);
            forsikringen.setArligPremie(Double.parseDouble(batTilbud.getText()));
            vindu.getRegister().nyForsikring(forsikringen);
            Kjoretoyforsikring kjForsikring = (Kjoretoyforsikring)forsikringen;
            if (eier != null)
                kjForsikring.setEier(eier);
            
            JOptionPane.showMessageDialog(null, "Du har nå tegnet båtforsikring med nummer "
                                          + forsikringen.getForsikringsnummer() + " på " + kunde.getFornavn() 
                                          + " " + kunde.getEtternavn() , "Bekreftelse", JOptionPane.INFORMATION_MESSAGE);
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
            batGiTilbud.setVisible(true);
        }
        else if (e.getSource() == annenEier)
        {
            if (annenEier.getText().equals("Trykk for annen eier"))
            {
                int result = JOptionPane.showConfirmDialog(null, eierPanel, 
                "Vennligst fyll ut bileiers kontaktinformasjon:", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION)
                {
                    eier = new Eier(eierFornavn.getText(), eierEtternavn.getText(), eierAdresse.getText(), eierTlf.getText());
                }
            }
            else if(annenEier.getText().equals("Vis eier"))
                {
                  JOptionPane.showMessageDialog( null, forsikring.getEier().toString(), 
                      "Kjøretøyets registrerte eier:", JOptionPane.PLAIN_MESSAGE);
                }
        }
        else if (e.getSource() == vilkar)
        {
            //Vis vilkår
        }
        else if (e.getSource() == rediger)
        {
            enableFelter( this, beregnPris );
            beregnPris.setText("Beregn ny pris");
            tilbudLabel.setText("Foreslått tilbud: ");
            annenEier.setText("Trykk for annen eier");
            knappePanel.add(lagreNy);
        }
        else if (e.getSource() == lagreNy)
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
                knappePanel.remove(lagreNy);
                this.remove(beregnPris);
                knappePanel.remove(deaktiver);
                forsikring.setAktiver(false);
                JOptionPane.showMessageDialog(null, "Forsikring " + String.valueOf(forsikring.getForsikringsnummer()) + " er ikke lenger aktiv.", "Bekreftelse", JOptionPane.PLAIN_MESSAGE);
                repaint();
                revalidate();
            }
        }
    }
}
