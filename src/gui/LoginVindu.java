/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Odd
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import objekter.Ansatt;

/**
 *
 * @author Odd
 */
public class LoginVindu extends JFrame
{
    private final JTextField loginFelt;
    private final JPasswordField passordFelt;
    private final JButton loginKnapp;
    private final JButton avsluttKnapp;
    private final AnsattVindu vindu;
    private Ansatt ansatt = null;
    
    private final int VINDU_BREDDE = 500;
    private final int VINDU_HØYDE = 150;
    
    public LoginVindu( AnsattVindu vindu )
    {
        setVisible(true);
        setLocation(30,30);
        setSize(VINDU_BREDDE,VINDU_HØYDE);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        
        KnappeLytter knappeLytter = new KnappeLytter();
        this.vindu = vindu;
        loginFelt = new JTextField(25);
        passordFelt = new JPasswordField(25);
        loginKnapp = new JButton("Logg Inn");
        avsluttKnapp = new JButton("Avslutt");
        JPanel loginWrapper = new JPanel();
        loginWrapper.setLayout( new GridLayout(3,2) );
        loginWrapper.add( new JLabel("Bruker:") );
        loginWrapper.add( loginFelt );
        loginWrapper.add( new JLabel("Passord: "));
        loginWrapper.add( passordFelt );
        loginWrapper.add( loginKnapp );
        loginWrapper.add( avsluttKnapp );
        add(loginWrapper);
        
        loginKnapp.addActionListener(knappeLytter);
        avsluttKnapp.addActionListener(knappeLytter);
    }
    
    private void login()
    {
        Ansatt ansatt = vindu.getRegister().login( loginFelt.getText(), passordFelt.getText());
        if( ansatt != null)
        {
            vindu.setVisible(true);
            this.setVisible(false);
            vindu.lukkAlleFanekort();
            vindu.leggTilNyFane( new AnsattPanel(), "Min Side");
            vindu.oppdaterTabell( vindu.getRegister().getKundeliste().alleKunder()    );
        }
    }
    
    private class KnappeLytter implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if( e.getSource() == loginKnapp )
            {
                login();
            }
            else if( e.getSource() == avsluttKnapp )
            {
                //this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
        }  
    }
}
