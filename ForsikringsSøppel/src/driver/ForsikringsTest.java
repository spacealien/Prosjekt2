/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driver;

import gui.AnsattVindu;
import java.awt.EventQueue;

/**
 *
 * @author Odd
 */
public class ForsikringsTest 
{
    public static void main( String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                AnsattVindu vindu = new AnsattVindu();
            }
        });
    }
}

