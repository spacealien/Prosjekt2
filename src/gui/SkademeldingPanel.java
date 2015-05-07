/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import objekter.*;
import register.*;

/**
 *
 * @author Marthejansonskogen
 */
public class SkademeldingPanel extends JPanel implements ActionListener
{
    private AnsattVindu vindu;
    private HovedRegister register;
    private final JTextField skadeDato;
    private final JTextField skadeType;
    private final JTextArea skadeBeskrivelse;
    private final JTextField skadeTakst;
    private final JButton sendInnSkade;
    private Kunde kunde;
    private Forsikring forsikring;
    private SimpleDateFormat sdf;
    
    private Date dato;
    private String type;
    private String beskrivelse;
    private int takst;
    private int belop;
    
    public SkademeldingPanel(Kunde k, Forsikring f, AnsattVindu v)
    {
        sdf = new SimpleDateFormat("ddMMyyyy");
        kunde = k;
        forsikring = f;
        vindu = v;
        register = vindu.getRegister();
        skadeDato = new JTextField( 7 );
        skadeType = new JTextField( 7 );
        skadeBeskrivelse = new JTextArea( 100, 100 );
        skadeTakst = new JTextField( 7 );
        sendInnSkade = new JButton("Send inn skade");  
        
        JPanel tegnSkaden = new JPanel();
        JPanel tegnSkade = new JPanel();
        tegnSkade.setLayout(new GridLayout(4,2,1,5));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        tegnSkade.add(new JLabel("Dato: (ddmmåååå) "));
        tegnSkade.add(skadeDato);
        tegnSkade.add(new JLabel("Type: "));
        tegnSkade.add(skadeType);
        tegnSkade.add(new JLabel("Takst: "));
        tegnSkade.add(skadeTakst);
        tegnSkaden.add(new JLabel("Beskrivelse av skaden: "));
        tegnSkaden.add(skadeBeskrivelse);
        tegnSkade.setMaximumSize(new Dimension(300,100));
        tegnSkaden.setMaximumSize(new Dimension(400,300));
        add(tegnSkade);
        add(tegnSkaden);
        add(Box.createRigidArea(new Dimension(40, 40)));
        add(sendInnSkade);
    }
    
    public void visSkademelding( Skademelding skademelding )
    {
        
        
    }
    
    public boolean hentInfo()
    {
        //int sd = Integer.parseInt(skadeDato.getText());
        String sd = skadeDato.getText();
        try
        {
            dato = sdf.parse(sd);
            /*int sdar = Integer.parseInt(sd.substring(4,8));
            int sdmnd = Integer.parseInt(sd.substring(2,4));
            int sddag = Integer.parseInt(sd.substring(0,2));
            Date skadedatoen = new Date((sdar-1900), sdmnd, sddag);*/
            type = skadeType.getText();
            beskrivelse = skadeBeskrivelse.getText();
            takst = Integer.parseInt(skadeTakst.getText());
            return true;
        } 
        catch (ParseException e)
        {
            JOptionPane.showMessageDialog(null, "Vennligst skriv inn datoen i følgende format: ddmmåååå.", "Feilmelding", JOptionPane.ERROR_MESSAGE);
            return false;
	}
    }
    
    public void beregnPris()
    {
        //Beregner pris
        //belop =;
    }
    
    public void nySkademelding()
    {
        if (hentInfo())
        register.nySkademelding(forsikring, dato, type, beskrivelse, takst, belop );
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == sendInnSkade)
        {
            nySkademelding();
        }
    }
}
