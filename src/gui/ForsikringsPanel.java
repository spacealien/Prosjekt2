/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class ForsikringsPanel extends JPanel
{
    private final String[] forsikringsvalg = {"", "Bilforsikring", "Båtforsikring", "Husforsikring", "Fritidsboligforsikring", "Reiseforsikring"};
    private final JComboBox<String> forsikringsDropDown;
    public ForsikringsPanel()
    {
        forsikringsDropDown = new JComboBox<>(forsikringsvalg);
        add(forsikringsDropDown);
    }
}
