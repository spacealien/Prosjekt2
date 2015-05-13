/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driver;

import gui.AnsattVindu;
import java.awt.EventQueue;
import javax.swing.UIManager;

/**
 *
 * @author Odd
 */
public class Main 
{
    public static void main( String[] args)
    {    
        UIManager.put("OptionPane.cancelButtonText", "Cancel");
        UIManager.put("OptionPane.noButtonText", "Nei");
        UIManager.put("OptionPane.okButtonText", "OK");
        UIManager.put("OptionPane.yesButtonText", "Ja");    
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                AnsattVindu vindu = new AnsattVindu();
            }
        });
    }
    
}
