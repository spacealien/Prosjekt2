/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

import java.util.Calendar;

/**
 *
 * @author Odd, Marthe og Thomas.
 */
public class ForsikringsKalulator 
{
    private static int innevarendeAr = Calendar.getInstance().get(Calendar.YEAR);
    
    public ForsikringsKalulator()
    {
        
    }
    
    public static double beregnBilforsikring(int Takst, String vilkar, 
            int modell_år, int kjørelengde, int hk, boolean garasje, 
            int egenandel, String føreralder, boolean esp, boolean alarm, 
            boolean sporing, double bonus  )
    {        
        // Henter testvariablene fra forsikringsklassen.
        int bilTakst = Takst;
        int bilArsModell = modell_år;
        int bilAlder = innevarendeAr - bilArsModell;
        int bilForventetKjorelengde = kjørelengde;
        int bilHK = hk;
        boolean bilGarasje = garasje;
        int bilEgenandel = egenandel;
        String bilForerAlder = føreralder;
        boolean bilESP = esp;
        boolean bilAlarmert = alarm;
        boolean bilGPS = sporing;
        String bilVilkar = vilkar;
        
        //Initialiserer variablene som skal brukes til beregningen.
        int beregnetBilTakst = 0;
        double beregnetBilAlder = 0;
        double beregnetBilKjorelengde = 0;
        double beregnetBilHK = 0;
        double beregnetBilGarasje = 0;
        double beregnetBilEgenandel = 0;
        double beregnetBilForerAlder = 0;
        double beregnetBilESP = 0;
        double beregnetBilAlarm = 0;
        double beregnetBilGPS = 0;
        double beregnetBilTilbud = 0;
        double beregnetBilVilkar = 0;
        
        //Henter faktor for bilens grunnbeløp.
        if (bilTakst > 0 && bilTakst <= 50000) 
        {
            beregnetBilTakst = 3500;
        }
        else if (bilTakst > 50000 && bilTakst <= 100000)
        {
            beregnetBilTakst = 4000;
        }
        else if (bilTakst > 100000 && bilTakst <= 150000)
        {
            beregnetBilTakst = 4500;
        }
        else if (bilTakst > 150000 && bilTakst <= 200000)
        {
            beregnetBilTakst = 5000;
        }
        else if (bilTakst > 200000 && bilTakst <= 250000)
        {
            beregnetBilTakst = 5500;
        }
        else if (bilTakst > 250000 && bilTakst <= 300000)
        {
            beregnetBilTakst = 6000;
        }
        else if (bilTakst > 300000 && bilTakst <= 400000)
        {
            beregnetBilTakst = 7000;
        }
        else if (bilTakst > 400000 && bilTakst <= 500000)
        {
            beregnetBilTakst = 8000;
        }
        else if (bilTakst > 500000 && bilTakst <= 600000)
        {
            beregnetBilTakst = 9000;
        }
        else if (bilTakst > 600000 && bilTakst <= 600000)
        {
            beregnetBilTakst = 10500;
        }
        else if (bilTakst > 750000 && bilTakst <= 1000000)
        {
            beregnetBilTakst = 13000;
        }
        else if (bilTakst > 1000000 && bilTakst <= 1500000)
        {
            beregnetBilTakst = 18000;
        }
        else if (bilTakst > 1500000 && bilTakst <= 2000000)
        {
            beregnetBilTakst = 23000;
        }
        else if (bilTakst > 2000000)
        {
            beregnetBilTakst = 33000;
        }
        
        // Henter faktor for bilens alder.
        if (bilAlder > 0 && bilAlder <= 8)
        {
            beregnetBilAlder = 0.2;
        }
        else if (bilAlder > 8 && bilAlder <= 16)
        {
            beregnetBilAlder = 0.3;
        }
        else if (bilAlder > 16)
        {
            beregnetBilAlder = 0.5;
        }
        
        // Henter faktor for forventet kjørelengde.
        if (bilForventetKjorelengde == 8000)
        {
            beregnetBilKjorelengde = 0.2;
        }
        else if (bilForventetKjorelengde == 12000)
        {
            beregnetBilKjorelengde = 0.3;
        }
        else if (bilForventetKjorelengde == 16000)
        {
            beregnetBilKjorelengde = 0.4;
        }
        else if (bilForventetKjorelengde == 20000)
        {
            beregnetBilKjorelengde = 0.5;
        }
        else if (bilForventetKjorelengde == 25000)
        {
            beregnetBilKjorelengde = 0.6;
        }
        else if (bilForventetKjorelengde == 30000)
        {
            beregnetBilKjorelengde = 0.7;
        }
        
        // Henter faktor for bilens hestekrefter.
        if (bilHK > 0 && bilHK <= 100)
        {
            beregnetBilHK = 0.2;
        }
        else if (bilHK > 100 && bilHK <= 200)
        {
            beregnetBilHK = 0.4;
        }
        else if (bilHK > 200 && bilHK <= 300)
        {
            beregnetBilHK = 0.6;
        }
        else if (bilHK > 300 && bilHK <= 400)
        {
            beregnetBilHK = 0.8;
        }
        else if (bilHK > 400 && bilHK <= 500)
        {
            beregnetBilHK = 1.0;
        }
        else if (bilHK > 500)
        {
            beregnetBilHK = 1.2;
        }
        
        //Henter faktor for garasje.
        if (bilGarasje == true)
        {
            beregnetBilGarasje = 0.0;
        }
        else
        {
            beregnetBilGarasje = 0.2;
        }
        
        //Henter faktor for egenandel.
        if (bilEgenandel == 2000)
        {
            beregnetBilEgenandel = 1.0;
        }
        else if (bilEgenandel == 4000)
        {
            beregnetBilEgenandel = 0.85;
        }
        else if (bilEgenandel == 8000)
        {
            beregnetBilEgenandel = 0.7;
        }
        else if (bilEgenandel == 12000)
        {
            beregnetBilEgenandel = 0.55;
        }
        else if (bilEgenandel == 16000)
        {
            beregnetBilEgenandel = 0.4;
        }
        else if (bilEgenandel == 20000)
        {
            beregnetBilEgenandel = 0.25;
        }
        else if (bilEgenandel == 30000)
        {
            beregnetBilEgenandel = 0.1;
        }
        
        // Henter faktor for førers alder.
        switch (bilForerAlder) {
            case "Bilfører < 23 år":
                beregnetBilForerAlder = 0.6;
                break;
            case "Bilfører mellom 23 - 30 år":
                beregnetBilForerAlder = 0.3;
                break;
            case "Bilfører > 30 år":
                beregnetBilForerAlder = 0.1;
                break;
        }
        
        // Henter faktor for vilkårene som er valgt.
        switch (bilVilkar) {
            case "Ansvar":
                beregnetBilVilkar = 0.9;
                break;
            case "Delkasko":
                beregnetBilVilkar = 0.6;
                break;
            case "Kasko":
                beregnetBilVilkar = 0.3;
                break;
            case "Superkasko":
                beregnetBilVilkar = 0.1;
                break;
        }
        
        // Henter faktor for om bilen har Antiskrens/ESP.
        if (bilESP == true)
        {
            beregnetBilESP = 0.0;
        }
        else
        {
            beregnetBilESP = 0.2;
        }
        
        // Henter faktor for om bilen har godkjent alarm. 
        if (bilAlarmert == true)
        {
            beregnetBilAlarm = 0.0;
        }
        else
        {
            beregnetBilAlarm = 0.2;
        }
        
        // Henter faktor for om bilen har GPS tracking. 
        if (bilGPS == true)
        {
            beregnetBilGPS = 0.0;
        }
        else
        {
            beregnetBilGPS = 0.2;
        }
        
        beregnetBilTilbud = beregnetBilTakst * (beregnetBilAlder +
                beregnetBilKjorelengde + beregnetBilHK+beregnetBilGarasje + 
                beregnetBilEgenandel + beregnetBilForerAlder + beregnetBilESP + 
                beregnetBilAlarm + beregnetBilGPS+beregnetBilVilkar) * (1-bonus);
        return Math.rint(beregnetBilTilbud);
    }
    
    public static double beregnBatforsikring(int egenandel, String vilkar, 
            int takst, int hk, int modell_år, boolean vekter, int lengde  )
    {
        //Henter testvariablene fra forsikringsklassen.
        int batTakst = takst;
        int batEgenandel = egenandel;
        int batArsModell = modell_år;
        int batHK = hk;
        boolean batVekter = vekter;
        int batAlderBeregn = innevarendeAr - batArsModell;
        String batVilkar = vilkar;
        
        //Initialiserer variablene som skal brukes til beregningen.
        int beregnetBatTakst = 0;
        double beregnetBatEgenandel = 0;
        double beregnetBatAlder = 0;
        double beregnetBatHestekrefter = 0;
        double beregnetBatVekter = 0;
        double beregnetBatTilbud = 0;
        double beregnetBatVilkar = 0;
        
        // Henter faktor for taksert verdi.
        if (batTakst > 0 && batTakst <= 10000)
        {
            beregnetBatTakst = 3500;
        }
        else if (batTakst > 10000 && batTakst <= 50000)
        {
            beregnetBatTakst = 4000;
        }
        else if (batTakst > 50000 && batTakst <= 200000)
        {
            beregnetBatTakst = 4500;
        }
        else if (batTakst > 200000 && batTakst <= 300000)
        {
            beregnetBatTakst = 5000;
        }
        else if (batTakst > 300000 && batTakst <= 400000)
        {
            beregnetBatTakst = 6000;
        }
        else if (batTakst > 400000 && batTakst <= 500000)
        {
            beregnetBatTakst = 7000;
        }
        else if (batTakst > 500000 && batTakst <= 600000)
        {
            beregnetBatTakst = 8000;
        }
        else if (batTakst > 600000 && batTakst <= 750000)
        {
            beregnetBatTakst = 9000;
        }
        else if (batTakst > 750000 && batTakst <= 1000000)
        {
            beregnetBatTakst = 10500;
        }
        else if (batTakst > 1000000 && batTakst <= 1500000)
        {
            beregnetBatTakst = 13000;
        }
        else if (batTakst > 1500000 && batTakst <= 2000000)
        {
            beregnetBatTakst = 18000;
        }
        else if (batTakst > 2000000 && batTakst <= 5000000)
        {
            beregnetBatTakst = 23000;
        }
        else if (batTakst > 5000000)
        {
            beregnetBatTakst = 33000;
        }
        
        // Henter faktor for egenandel. 
        if (batEgenandel == 2000)
        {
            beregnetBatEgenandel = 1;
        }
        else if (batEgenandel == 4000)
        {
            beregnetBatEgenandel = 0.85;
        }
        else if (batEgenandel == 8000)
        {
            beregnetBatEgenandel = 0.7;
        }
        else if (batEgenandel == 12000)
        {
            beregnetBatEgenandel = 0.55;
        }
        else if (batEgenandel == 16000)
        {
            beregnetBatEgenandel = 0.4;
        }
        else if (batEgenandel == 20000)
        {
            beregnetBatEgenandel = 0.25;
        }
        else if (batEgenandel == 30000)
        {
            beregnetBatEgenandel = 0.1;
        }
        
        // Henter faktor for båtens alder. 
        if (batAlderBeregn > 0 && batAlderBeregn <= 8)
        {
            beregnetBatAlder = 0.2;
        }
        else if (batAlderBeregn > 8 && batAlderBeregn <= 16)
        {
            beregnetBatAlder = 0.3;
        }
        else if (batAlderBeregn > 17)
        {
            beregnetBatAlder = 0.5;
        }
        
        // Henter faktor for hestekrefter. 
        if (batHK > 0 && batHK <= 50)
        {
            beregnetBatHestekrefter = 0.2;
        }
        else if (batHK > 50 && batHK <= 100)
        {
            beregnetBatHestekrefter = 0.4;
        }
        else if (batHK > 100 && batHK <= 200)
        {
            beregnetBatHestekrefter = 0.6;
        }
        else if (batHK > 200 && batHK <= 300)
        {
            beregnetBatHestekrefter = 0.8;
        }
        else if (batHK > 300 && batHK <= 500)
        {
            beregnetBatHestekrefter = 1.0;
        }
        else if (batHK > 500)
        {
            beregnetBatHestekrefter = 1.2;
        }
        
        // Henter faktor for om båtplass er bevoktet.
        if (batVekter == true)
        {
            beregnetBatVekter = 0.0;
        }
        else
        {
            beregnetBatVekter = 0.2;
        }
        
        // Henter faktor for vilkårene som er valgt.
        switch (batVilkar) {
            case "Ansvar":
                beregnetBatVilkar = 0.9;
                break;
            case "Delkasko":
                beregnetBatVilkar = 0.6;
                break;
            case "Kasko":
                beregnetBatVilkar = 0.3;
                break;
            case "Superkasko":
                beregnetBatVilkar = 0.1;
                break;
        }
        
        beregnetBatTilbud = beregnetBatTakst*(beregnetBatEgenandel+
                beregnetBatAlder+beregnetBatHestekrefter+beregnetBatVekter+
                beregnetBatVilkar);
        return Math.rint(beregnetBatTilbud);
    }
    
    public static double beregnHusforsikring(int egenandel, String vilkar, 
            int byggar, String materiale, int kvm, int belopByg, int belopInnbo, 
            boolean alarmen, String hustype, String husstandard)
    {
        //Henter testvariablene fra forsikringsklassen.
        int husTakst = belopByg;
        int husInnboTakst = belopInnbo;
        int husEgenandel = egenandel;
        int husByggeAr = byggar;
        int husAlderBeregn = innevarendeAr - husByggeAr;
        String husByggeMateriale = materiale;
        boolean husAlarmert = alarmen;
        String husType = hustype;
        String husStandard = husstandard;
        String husVilkar = vilkar;
        
        //Initialiserer variablene som skal brukes til beregningen.
        int beregnetHusTakst = 0;
        int beregnetHusInnbo = 0;
        double beregnetHusEgenandel = 0;
        double beregnetHusAlder = 0;
        double beregnetHusByggeMateriale = 0;
        double beregnetHusAlarm = 0;
        double beregnetHusTilbud;
        double beregnetHusType = 0;
        double beregnetHusStandard = 0;
        double beregnetHusVilkar = 0;
        
        // Henter faktor for vilkar.
        switch (husVilkar) {
            case "Hus Pluss":
                beregnetHusVilkar = 0.6;
                break;
            case "Hus":
                beregnetHusVilkar = 0.3;
                break;
        }

        // Henter faktor for boligstandard.
        switch (husStandard) {
            case "Normal standard":
                beregnetHusStandard = 0.6;
                break;
            case "Bedre standard":
                beregnetHusStandard = 0.4;
                break;
            case "Høy standard":
                beregnetHusStandard = 0.2;
                break;
        }

        // Henter faktor for boligtype.
        switch (husType) {
            case "Enebolig":
                beregnetHusType = 0.9;
                break;
            case "Tomannsbolig":
                beregnetHusType = 0.8;
                break;
            case "Tremannsbolig":
                beregnetHusType = 0.7;
                break;
            case "Firemannsbolig":
                beregnetHusType = 0.6;
                break;
            case "Rekkehus":
                beregnetHusType = 0.5;
                break;
            case "Leilighet":
                beregnetHusType = 0.4;
                break;
        }
        
        // Henter faktor for husets takst. 
        if (husTakst > 0 && husTakst <= 1000000)
        {
            beregnetHusTakst = 5000;
        }
        else if (husTakst > 1000000 && husTakst <= 1500000)
        {
            beregnetHusTakst = 10000;
        }
        else if (husTakst > 1500000 && husTakst <= 2000000)
        {
            beregnetHusTakst = 15600;
        }
        else if (husTakst > 2000000 && husTakst <= 3000000)
        {
            beregnetHusTakst = 21200;
        }
        else if (husTakst > 3000000 && husTakst <= 4000000)
        {
            beregnetHusTakst = 26800;
        }
        else if (husTakst > 4000000 && husTakst <= 7000000)
        {
            beregnetHusTakst = 33000;
        }
        else if (husTakst > 7000000)
        {
            beregnetHusTakst = 38600;
        }
        
        // Henter faktor for husets innbo. 
        if (husInnboTakst > 0 && husInnboTakst <= 150000)
        {
            beregnetHusInnbo = 1000;
        }
        else if (husInnboTakst > 150000 && husInnboTakst <= 300000)
        {
            beregnetHusInnbo = 1500;
        }
        else if (husInnboTakst > 300000 && husInnboTakst <= 500000)
        {
            beregnetHusInnbo = 2000;
        }
        else if (husInnboTakst > 500000 && husInnboTakst <= 750000)
        {
            beregnetHusInnbo = 2500;
        }
        else if (husInnboTakst > 750000 && husInnboTakst <= 1000000)
        {
            beregnetHusInnbo = 3000;
        }
        else if (husInnboTakst > 1000000 && husInnboTakst <= 1500000)
        {
            beregnetHusInnbo = 3500;
        }
        else if (husInnboTakst > 1500000)
        {
            beregnetHusInnbo = 4000;
        }
        
        // Henter faktor for egenandel.
        if (husEgenandel == 2000)
        {
            beregnetHusEgenandel = 1;
        }
        else if (husEgenandel == 4000)
        {
            beregnetHusEgenandel = 0.85;
        }
        else if (husEgenandel == 8000)
        {
            beregnetHusEgenandel = 0.7;
        }
        else if (husEgenandel == 12000)
        {
            beregnetHusEgenandel = 0.55;
        }
        else if (husEgenandel == 16000)
        {
            beregnetHusEgenandel = 0.4;
        }
        else if (husEgenandel == 20000)
        {
            beregnetHusEgenandel = 0.25;
        }
        else if (husEgenandel == 30000)
        {
            beregnetHusEgenandel = 0.1;
        }
        
        // Henter faktor for husets alder.
        if (husAlderBeregn > 0 && husAlderBeregn <= 2)
        {
            beregnetHusAlder = 0.1;
        }
        else if (husAlderBeregn > 2 && husAlderBeregn <= 5)
        {
            beregnetHusAlder = 0.2;
        }
        else if (husAlderBeregn > 5 && husAlderBeregn <= 10)
        {
            beregnetHusAlder = 0.3;
        }
        else if (husAlderBeregn > 10 && husAlderBeregn <= 25)
        {
            beregnetHusAlder = 0.4;
        }
        else if (husAlderBeregn > 25)
        {
            beregnetHusAlder = 0.5;
        }
        
        // Henter faktor for byggemateriale.
        switch (husByggeMateriale) {
            case "Brannfast":
                beregnetHusByggeMateriale = 0.1;
                break;
            case "Mur":
                beregnetHusByggeMateriale = 0.2;
                break;
            case "Tre":
                beregnetHusByggeMateriale = 0.4;
                break;
            case "Laftet tømmer":
                beregnetHusByggeMateriale = 0.5;
                break;
        }
        
        // Henter faktor for om huset er alarmert.
        if (husAlarmert == true)
        {
            beregnetHusAlarm = 0.0;
        }
        else
        {
            beregnetHusAlarm = 0.2;
        }
        
        beregnetHusTilbud = (beregnetHusTakst+beregnetHusInnbo)*
                (beregnetHusEgenandel+beregnetHusAlder+
                beregnetHusByggeMateriale+beregnetHusAlarm+beregnetHusType+
                beregnetHusStandard+beregnetHusVilkar);
        return Math.rint(beregnetHusTilbud);
    }
    
    public static double beregnFritidsboligforsikring( int egenandel, 
            String vilkar, int byggeAr, String materiale, int kvm, int belopByg, 
            int belopInnbo, boolean alarmen, String type, String standard)
    {
        //Henter testvariablene fra forsikringsklassen.
        int fritidsTakst = belopByg;
        int fritidsInnboTakst = belopInnbo;
        int fritidsEgenandel = egenandel;
        int fritidsByggeAr = byggeAr;
        int FritidsAlderBeregn = innevarendeAr - fritidsByggeAr;
        String fritidsByggeMateriale = materiale;
        boolean fritidsAlarmert = alarmen;
        String fritidsType = type;
        String fritidsStandard = standard;
        String fritidsVilkar = vilkar;
        
        //Initialiserer variablene som skal brukes til beregningen.
        int beregnetFritidsTakst = 0;
        int beregnetFritidsInnbo = 0;
        double beregnetFritidsEgenandel = 0;
        double beregnetFritidsHusAlder = 0;
        double beregnetFritidsByggeMateriale = 0;
        double beregnetFritidsAlarm = 0;
        double beregnetFritidsTilbud;
        double beregnetFritidType = 0;
        double beregnetFritidStandard = 0;
        double beregnetFritidVilkar = 0;
        
        // Henter faktor for vilkar.
        switch (fritidsVilkar) {
            case "Fritidsbolig Pluss":
                beregnetFritidVilkar = 0.6;
                break;
            case "Fritidsbolig":
                beregnetFritidVilkar = 0.3;
                break;
        }

        // Henter faktor for boligstandard.
        switch (fritidsStandard) {
            case "Normal standard":
                beregnetFritidStandard = 0.6;
                break;
            case "Bedre standard":
                beregnetFritidStandard = 0.4;
                break;
            case "Høy standard":
                beregnetFritidStandard = 0.2;
                break;
        }

        // Henter faktor for boligtype.
        switch (fritidsType) {
            case "Hus/Hytte":
                beregnetFritidType = 0.7;
                break;
            case "Rekkehus":
                beregnetFritidType = 0.5;
                break;
            case "Leilighet":
                beregnetFritidType = 0.3;
                break;
        }
        
        // Henter faktor for husets takst. 
        if (fritidsTakst > 0 && fritidsTakst <= 800000)
        {
            beregnetFritidsTakst = 5000;
        }
        else if (fritidsTakst > 800000 && fritidsTakst <= 1500000)
        {
            beregnetFritidsTakst = 10000;
        }
        else if (fritidsTakst > 1500000 && fritidsTakst <= 2500000)
        {
            beregnetFritidsTakst = 18000;
        }
        else if (fritidsTakst > 2500000 && fritidsTakst <= 3000000)
        {
            beregnetFritidsTakst = 26000;
        }
        else if (fritidsTakst > 3000000)
        {
            beregnetFritidsTakst = 33000;
        }
        
        // Henter faktor for husets innbo. 
        if (fritidsInnboTakst > 0 && fritidsInnboTakst <= 150000)
        {
            beregnetFritidsInnbo = 1000;
        }
        else if (fritidsInnboTakst > 150000 && fritidsInnboTakst <= 300000)
        {
            beregnetFritidsInnbo = 1500;
        }
        else if (fritidsInnboTakst > 300000 && fritidsInnboTakst <= 500000)
        {
            beregnetFritidsInnbo = 2000;
        }
        else if (fritidsInnboTakst > 500000 && fritidsInnboTakst <= 750000)
        {
            beregnetFritidsInnbo = 2500;
        }
        else if (fritidsInnboTakst > 750000 && fritidsInnboTakst <= 1000000)
        {
            beregnetFritidsInnbo = 3000;
        }
        else if (fritidsInnboTakst > 1000000 && fritidsInnboTakst <= 1500000)
        {
            beregnetFritidsInnbo = 3500;
        }
        else if (fritidsInnboTakst > 1500000)
        {
            beregnetFritidsInnbo = 4000;
        }
        
        // Henter faktor for egenandel.
        if (fritidsEgenandel == 2000)
        {
            beregnetFritidsEgenandel = 1;
        }
        else if (fritidsEgenandel == 4000)
        {
            beregnetFritidsEgenandel = 0.85;
        }
        else if (fritidsEgenandel == 8000)
        {
            beregnetFritidsEgenandel = 0.7;
        }
        else if (fritidsEgenandel == 12000)
        {
            beregnetFritidsEgenandel = 0.55;
        }
        else if (fritidsEgenandel == 16000)
        {
            beregnetFritidsEgenandel = 0.4;
        }
        else if (fritidsEgenandel == 20000)
        {
            beregnetFritidsEgenandel = 0.25;
        }
        else if (fritidsEgenandel == 30000)
        {
            beregnetFritidsEgenandel = 0.1;
        }
        
        // Henter faktor for husets alder.
        if (FritidsAlderBeregn > 0 && FritidsAlderBeregn <= 2)
        {
            beregnetFritidsHusAlder = 0.1;
        }
        else if (FritidsAlderBeregn > 2 && FritidsAlderBeregn <= 5)
        {
            beregnetFritidsHusAlder = 0.2;
        }
        else if (FritidsAlderBeregn > 5 && FritidsAlderBeregn <= 10)
        {
            beregnetFritidsHusAlder = 0.3;
        }
        else if (FritidsAlderBeregn > 10 && FritidsAlderBeregn <= 25)
        {
            beregnetFritidsHusAlder = 0.4;
        }
        else if (FritidsAlderBeregn > 25)
        {
            beregnetFritidsHusAlder = 0.5;
        }
        
        // Henter faktor for byggemateriale.
        switch (fritidsByggeMateriale) {
            case "Brannfast":
                beregnetFritidsByggeMateriale = 0.1;
                break;
            case "Mur":
                beregnetFritidsByggeMateriale = 0.2;
                break;
            case "Tre":
                beregnetFritidsByggeMateriale = 0.4;
                break;
            case "Laftet tømmer":
                beregnetFritidsByggeMateriale = 0.5;
                break;
        }
        
        // Henter faktor for om huset er alarmert.
        if (fritidsAlarmert == true)
        {
            beregnetFritidsAlarm = 0.0;
        }
        else
        {
            beregnetFritidsAlarm = 0.2;
        }
        
        beregnetFritidsTilbud = (beregnetFritidsTakst+beregnetFritidsInnbo)*
                (beregnetFritidsEgenandel+beregnetFritidsHusAlder+
                beregnetFritidsByggeMateriale+beregnetFritidsAlarm+
                beregnetFritidType+beregnetFritidStandard+beregnetFritidVilkar);
        return Math.rint(beregnetFritidsTilbud);
    }
    
    public static double beregnReiseforsikring( int egenandel, String vilkar, boolean forsorger, int antallBarn, String sone, int belop)
    {
        //Henter testvariablene fra forsikringsklassen.
        boolean reiseForsorger = forsorger;
        String reiseSone = sone;
        double reiseForsikringsbelop = belop;
        int reiseEgenandel = egenandel;
        String reiseVilkar = vilkar;
        
        //Initialiserer variablene som skal brukes til beregningen.
        double beregnReiseForsikringsbelop = 0;
        double beregnReiseForsorger = 0;
        double beregnReiseSone = 0;
        double beregnReiseTilbud = 0;
        double beregnetReiseEgenandel = 0;
        double beregnetReiseVilkar = 0;
        
        // Henter faktor for vilkar.
        switch (reiseVilkar) {
            case "Reise Pluss":
                beregnetReiseVilkar = 0.6;
                break;
            case "Reise":
                beregnetReiseVilkar = 0.3;
                break;
        }
        
        // Henter faktor for egenandel.
        if (reiseEgenandel == 2000)
        {
            beregnetReiseEgenandel = 1;
        }
        else if (reiseEgenandel == 4000)
        {
            beregnetReiseEgenandel = 0.85;
        }
        else if (reiseEgenandel == 8000)
        {
            beregnetReiseEgenandel = 0.7;
        }
        else if (reiseEgenandel == 12000)
        {
            beregnetReiseEgenandel = 0.55;
        }
        else if (reiseEgenandel == 16000)
        {
            beregnetReiseEgenandel = 0.4;
        }
        else if (reiseEgenandel == 20000)
        {
            beregnetReiseEgenandel = 0.25;
        }
        else if (reiseEgenandel == 30000)
        {
            beregnetReiseEgenandel = 0.1;
        }
        
        if (reiseForsikringsbelop != 0)
        {
            beregnReiseForsikringsbelop = reiseForsikringsbelop*0.20;
        }
        
        if (reiseForsorger)
        {
           beregnReiseForsorger = 0.5;
          
        }
            
        switch (reiseSone)
        {
            case "Norden": beregnReiseSone = 1.0;
                    break;
            case "Europa": beregnReiseSone = 1.10;
                    break;
            case "Verden": beregnReiseSone = 1.20;
                    break;
        }
        
        beregnReiseTilbud = beregnReiseForsikringsbelop*(beregnReiseSone+
                beregnReiseForsorger+beregnetReiseEgenandel+beregnetReiseVilkar);
        return Math.rint(beregnReiseTilbud);
    }
    public static double beregnErstatningsbelop( int egenandel, int skadetakst)
    {
        double beløp = 0;
        if(!(egenandel >= skadetakst))
        {
            beløp = Math.rint((skadetakst*0.7)-egenandel);
        }
        return beløp;
    }
}
