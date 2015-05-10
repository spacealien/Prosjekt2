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
    
    public Husforsikring( Kunde k, int e_andel, String hadresse, int byggar, String bt, String mat, String stand, int kvm, int belopByg,
                             int belopInn, boolean alarmen)
    {
    super( k, e_andel, hadresse, byggar, bt, mat, stand, kvm, belopByg, belopInn, alarmen);
    takst = belopByg;
    egenandel = e_andel;
    byggear = byggar;
    byggeMateriale = mat;
    alarmert = alarmen;
    }
    
    @Override
    public void beregnPris()
    {
        int bpTakst = 0; //takst
        double bpEgenandel = 0; //egenandel
        double bpHusAlder = 0; //bpHusAlderBeregn
        int bpHusAlderBeregn = innevarendeAr - byggear;
        double bpByggeMateriale = 0; //byggeMateriale
        double bpAlarm = 0; //alarmert
        double bpTilbud;
        
        // Henter faktor for husets takst. 
        if (takst > 0 && takst <= 1000000)
        {
            bpTakst = 5000;
        }
        else if (takst > 1000000 && takst <= 1500000)
        {
            bpTakst = 10000;
        }
        else if (takst > 1500000 && takst <= 2000000)
        {
            bpTakst = 15600;
        }
        else if (takst > 2000000 && takst <= 3000000)
        {
            bpTakst = 21200;
        }
        else if (takst > 3000000 && takst <= 4000000)
        {
            bpTakst = 26800;
        }
        else if (takst > 4000000 && takst <= 7000000)
        {
            bpTakst = 33000;
        }
        else if (takst > 7000000)
        {
            bpTakst = 38600;
        }
        
        // Henter faktor for egenandel.
        if (egenandel == 2000)
        {
            bpEgenandel = 1;
        }
        else if (egenandel == 4000)
        {
            bpEgenandel = 0.85;
        }
        else if (egenandel == 8000)
        {
            bpEgenandel = 0.7;
        }
        else if (egenandel == 12000)
        {
            bpEgenandel = 0.55;
        }
        else if (egenandel == 16000)
        {
            bpEgenandel = 0.4;
        }
        else if (egenandel == 20000)
        {
            bpEgenandel = 0.25;
        }
        else if (egenandel == 30000)
        {
            bpEgenandel = 0.1;
        }
        
        // Henter faktor for husets alder.
        if (bpHusAlderBeregn > 0 && bpHusAlderBeregn <= 2)
        {
            bpHusAlder = 0.1;
        }
        else if (bpHusAlderBeregn > 2 && bpHusAlderBeregn <= 5)
        {
            bpHusAlder = 0.2;
        }
        else if (bpHusAlderBeregn > 5 && bpHusAlderBeregn <= 10)
        {
            bpHusAlder = 0.3;
        }
        else if (bpHusAlderBeregn > 10 && bpHusAlderBeregn <= 25)
        {
            bpHusAlder = 0.4;
        }
        else if (bpHusAlderBeregn > 25)
        {
            bpHusAlder = 0.5;
        }
        
        // Henter faktor for byggemateriale.
        switch (byggeMateriale) {
            case "Brannfast":
                bpByggeMateriale = 0.1;
                break;
            case "Mur":
                bpByggeMateriale = 0.2;
                break;
            case "Tre":
                bpByggeMateriale = 0.4;
                break;
            case "Laftet tømmer":
                bpByggeMateriale = 0.5;
                break;
        }
        
        // Henter faktor for om huset er alarmert.
        if (alarmert == true)
        {
            bpAlarm = 0.0;
        }
        else
        {
            bpAlarm = 0.2;
        }
        
        bpTilbud = bpTakst*(bpEgenandel+bpHusAlder+bpByggeMateriale+bpAlarm);
        
    }
    
    @Override
    public String toString()
    {
        String utskrift = super.toString();
        return utskrift;
    }
}//end of class
