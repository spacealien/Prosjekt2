/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

/**
 *
 * @author Odd
 */
/*public class ForsikringsKalulator 
{
    
    public ForsikringsKalulator()
    {
        
    }
    
    public static double beregnBilforsikring( Bilforsikring bilforsikring )
    {        
        int bpTakst = 0;
        double bpBilAlder = 0;
        double bpKjorelengde = 0;
        double bpHk = 0;
        double bpGarasje = 0;
        double bpEgenandel = 0;
        double bpForerAlder = 0;
        double bpEsp = 0;
        double bpAlarm = 0;
        double bpGps = 0;
        double bpTilbud = 0;
        //int bpFalder = Integer.parseInt(fAlder);
        int belopet = 50;
        
        //Henter faktor for bilens grunnbeløp.
        if (belopet > 0 && belopet <= 50000) 
        {
            bpTakst = 3500;
        }
        else if (belopet > 50000 && belopet <= 100000)
        {
            bpTakst = 4000;
        }
        else if (belopet > 100000 && belopet <= 150000)
        {
            bpTakst = 4500;
        }
        else if (belopet > 150000 && belopet <= 200000)
        {
            bpTakst = 5000;
        }
        else if (belopet > 200000 && belopet <= 250000)
        {
            bpTakst = 5500;
        }
        else if (belopet > 250000 && belopet <= 300000)
        {
            bpTakst = 6000;
        }
        else if (belopet > 300000 && belopet <= 400000)
        {
            bpTakst = 7000;
        }
        else if (belopet > 400000 && belopet <= 500000)
        {
            bpTakst = 8000;
        }
        else if (belopet > 500000 && belopet <= 600000)
        {
            bpTakst = 9000;
        }
        else if (belopet > 600000 && belopet <= 600000)
        {
            bpTakst = 10500;
        }
        else if (belopet > 750000 && belopet <= 1000000)
        {
            bpTakst = 13000;
        }
        else if (belopet > 1000000 && belopet <= 1500000)
        {
            bpTakst = 18000;
        }
        else if (belopet > 1500000 && belopet <= 2000000)
        {
            bpTakst = 23000;
        }
        else if (belopet > 2000000)
        {
            bpTakst = 33000;
        }
        
        // Henter faktor for bilens alder.
        if (ar > 0 && ar <= 8)
        {
            bpBilAlder = 0.2;
        }
        else if (ar > 8 && ar <= 16)
        {
            bpBilAlder = 0.3;
        }
        else if (ar > 16)
        {
            bpBilAlder = 0.5;
        }
        
        // Henter faktor for forventet kjørelengde.
        if (kmlengde == 8000)
        {
            bpKjorelengde = 0.2;
        }
        else if (kmlengde == 12000)
        {
            bpKjorelengde = 0.3;
        }
        else if (kmlengde == 16000)
        {
            bpKjorelengde = 0.4;
        }
        else if (kmlengde == 20000)
        {
            bpKjorelengde = 0.5;
        }
        else if (kmlengde == 25000)
        {
            bpKjorelengde = 0.6;
        }
        else if (kmlengde == 30000)
        {
            bpKjorelengde = 0.7;
        }
        
        // Henter faktor for bilens hestekrefter.
        if (hk > 0 && hk <= 100)
        {
            bpHk = 0.2;
        }
        else if (hk > 100 && hk <= 200)
        {
            bpHk = 0.4;
        }
        else if (hk > 200 && hk <= 300)
        {
            bpHk = 0.6;
        }
        else if (hk > 300 && hk <= 400)
        {
            bpHk = 0.8;
        }
        else if (hk > 400 && hk <= 500)
        {
            bpHk = 1.0;
        }
        else if (hk > 500)
        {
            bpHk = 1.2;
        }
        
        //Henter faktor for garasje.
        if (garasje == true)
        {
            bpGarasje = 0.0;
        }
        else
        {
            bpGarasje = 0.2;
        }
        
        //Henter faktor for egenandel.
        if (egenAndel == 2000)
        {
            bpEgenandel = 1.0;
        }
        else if (egenAndel == 4000)
        {
            bpEgenandel = 0.85;
        }
        else if (egenAndel == 8000)
        {
            bpEgenandel = 0.7;
        }
        else if (egenAndel == 12000)
        {
            bpEgenandel = 0.55;
        }
        else if (egenAndel == 16000)
        {
            bpEgenandel = 0.4;
        }
        else if (egenAndel == 20000)
        {
            bpEgenandel = 0.25;
        }
        else if (egenAndel == 30000)
        {
            bpEgenandel = 0.1;
        }
        
        // Henter faktor for førers alder. 
        if (bpFalder >= 18 && bpFalder <= 23)
        {
            bpForerAlder = 0.6;
        }
        else if (bpFalder > 23 && bpFalder <= 30)
        {
            bpForerAlder = 0.3;
        }
        else if (bpFalder > 30)
        {
            bpForerAlder = 0.1;
        }
        
        // Henter faktor for om bilen har Antiskrens/ESP.
        if (esp == true)
        {
            bpEsp = 0.0;
        }
        else
        {
            bpEsp = 0.2;
        }
        
        // Henter faktor for om bilen har godkjent alarm. 
        if (alarmen == true)
        {
            bpAlarm = 0.0;
        }
        else
        {
            bpAlarm = 0.2;
        }
        
       /* // Henter faktor for om bilen har GPS tracking. 
        if (gjenkjenningen == true)
        {
            bpGps = 0.0;
        }
        else
        {
            bpGps = 0.2;
        }
        
        bpTilbud = bpTakst*(bpBilAlder+bpKjorelengde+bpHk+bpGarasje+bpEgenandel+bpForerAlder+bpEsp+bpAlarm+bpGps);
        return bpTilbud;
    }
}*/
