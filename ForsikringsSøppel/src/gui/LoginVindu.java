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
import java.awt.event.WindowEvent;
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
public class LoginVindu extends JFrame implements ActionListener
{
    private final JTextField loginFelt;
    private final JPasswordField passordFelt;
    private final JButton loginKnapp;
    private final JButton avsluttKnapp;
    private final AnsattVindu vindu;
    private Ansatt ansatt = null;
    
    public LoginVindu( AnsattVindu vindu )
    {
        setVisible(true);
        setLocation(30,30);
        setSize(500,150);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
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
        
        loginKnapp.addActionListener(this);
        avsluttKnapp.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if( e.getSource() == loginKnapp )
        {
            vindu.login( loginFelt.getText(), passordFelt.getText());
            System.out.println();
        }
        else if( e.getSource() == avsluttKnapp )
        {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
}
