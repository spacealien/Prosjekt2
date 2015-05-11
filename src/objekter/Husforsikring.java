/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

import java.util.Calendar;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class Husforsikring extends Eiendomsforsikring
{
    private int takst;
    private int egenandel;
    private int innevarendeAr = Calendar.getInstance().get(Calendar.YEAR);
    private int byggear;
    private String byggeMateriale;
    private boolean alarmert;
    
    public Husforsikring( Kunde k, int e_andel, String vilkar, String hadresse, int byggar, String bt, String mat, String stand, int kvm, int belopByg,
                             int belopInn, boolean alarmen)
    {
    super( k, e_andel, vilkar, hadresse, byggar, bt, mat, stand, kvm, belopByg, belopInn, alarmen);
    takst = belopByg;
    egenandel = e_andel;
    byggear = byggar;
    byggeMateriale = mat;
    alarmert = alarmen;
    }

    public int getTakst() {
        return takst;
    }

    public int getByggear() {
        return byggear;
    }

    public String getByggeMateriale() {
        return byggeMateriale;
    }

    public boolean isAlarmert() {
        return alarmert;
    }
    
    @Override
    public String toString()
    {
        String utskrift = super.toString();
        return utskrift;
    }
}//end of class
