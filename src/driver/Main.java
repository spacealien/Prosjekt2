/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driver;

import gui.AnsattVindu;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
            @Override
            public void run()
            {
                AnsattVindu vindu = new AnsattVindu();
                vindu.addWindowListener( new WindowAdapter() 
                {
                    @Override
                    public void windowClosing( WindowEvent e )
                    {
                        //vindu.lagre();
                        System.exit( 0 );
                    }
                });
            }
        });
    }
    
}
