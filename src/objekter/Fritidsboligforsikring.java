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
public class Fritidsboligforsikring extends Eiendomsforsikring
{
    private boolean utleie;
    private String utleid = "";
    private int takst;
    private int egenandel;
    private int innevarendeAr = Calendar.getInstance().get(Calendar.YEAR);
    private int byggear;
    private String byggeMateriale;
    private boolean alarmert;
    public Fritidsboligforsikring(Kunde k, int e_andel, String vilkar, String hadresse, int byggar,
                                  String bt, String mat, String stand, int kvm,
                                  int belopByg, int belopInn, boolean alarmen, boolean utl)
    {
    super( k, e_andel, vilkar, hadresse, byggar, bt, mat, stand, kvm, belopByg, belopInn, alarmen);
    utleie = utl;
    takst = belopByg;
    egenandel = e_andel;
    byggear = byggar;
    byggeMateriale = mat;
    alarmert = alarmen;
    }

    public boolean getUtleie()
    {
        return utleie;
    }

    public void setUtleie(boolean u)
    {
        utleie = u;
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
        if (utleie)
            utleid = "Ja";
        else
            utleid = "Nei";
        
        String utskrift = super.toString();
        utskrift += "\nUtleid: " + utleid;
        return utskrift;
    }
}//end of class



