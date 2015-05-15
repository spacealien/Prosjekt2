/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Odd
 */
public class StatistikkVindu extends JFrame 
{

    private final Container c;
    private final JPanel hovedPanel;

    public StatistikkVindu(String s, JComponent komponent, Dimension d) 
    {
        super(s);
        setVisible(true);
        setSize(new Dimension(850, 350));
        c = getContentPane();
        hovedPanel = new JPanel();
        komponent.setPreferredSize(d);
        hovedPanel.add(new JScrollPane(komponent));
        c.setLayout(new BorderLayout());
        c.add(hovedPanel, BorderLayout.CENTER);
        pack();
    }
}
