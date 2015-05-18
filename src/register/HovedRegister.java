
/**
 * Klassen har som hensikt å samle alle registerene i en klasse for å gjøre
 * det enkelt å alle registerne i en fil. Alternativet ville vært å hatt metodene
 * i denne klassen i AnsattVindu klassen. Vi har valgt å gjøre det på denne måten
 * for å prøve å gjøre AnsattVindu klassen så kort og ryddig som mulig.
 * 
 * Vi ønsket å holde AnsattVinduet så fri for metoder som ikke er direkte relatert
 * til det grafiske grensesnittet.
 * 
 */
package register;

import gui.AnsattVindu;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import objekter.*;

/**
 *
 * @author Odd, Marthe
 */
public class HovedRegister 
{
    private Kunderegister kunderegister = new Kunderegister();
    private Forsikringsliste forsikringsregister = new Forsikringsliste();
    private SkademeldingRegister skademeldingsregister = new SkademeldingRegister();
    private Ansattregister ansattregister = new Ansattregister();
    private List<Inntekt> innbetalinger = new ArrayList<>();
    private Calendar kalender;
    private AnsattVindu vindu;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public HovedRegister(AnsattVindu v) 
    {
        vindu = v;  
        kalender = Calendar.getInstance();  
        
        /**
        GregorianCalendar fdato = new GregorianCalendar(1991,6,6);
        Kunde test_1 = new Kunde("Rolf", "Hestman", "Kongleknaggen 18", "22260906", fdato, "hesterolf@yahoo.no", "08206049937");
        Kunde test_2 = new Kunde("Bjørn", "Dæhlie", "Trysil 23B", "22260906", fdato, "epost@epost.no", "12048449997");
        Kunde test_3 = new Kunde("Marit", "Bjørgen", "Trondheim 23B", "22260906", fdato, "marit@langrenn.no", "02029449964");
        Kunde test_4 = new Kunde("Bjørn", "Dæhlie", "Trysil 23B", "22260906", fdato, "epost@epost.no", "01258449963");
        Kunde test_5 = new Kunde("Ole-Einar", "Bjørndalen", "Trysil 23B", "22260906", fdato, "kongen@skiskytter.no", "01258446816");
        Kunde test_6 = new Kunde("Luke", "Skywalker", "Tatooine", "22260906", fdato, "luke@cantina.com", "03306849785");
        Kunde test_7 = new Kunde("Han", "Solo", "Millenium Falcon", "22260906", fdato, "hansolo@smuggler.org", "03306844899");
        Kunde test_8 = new Kunde("Darth", "Vader", "Deathstar", "22260906", fdato, "darkfather@deathstar.gov", "04215348879");
        Kunde test_9 = new Kunde("Emperor", "Palpatine", "Coruscant", "22260906", fdato, "palpatine@emperor.gov", "04215338644");
        Kunde test_10 = new Kunde("Leia", "Organa", "Coruscant", "22260906", fdato, "alderaan@galacticsenate.gov", "05299049681");
        Kunde test_11 = new Kunde("Lando", "Calrissian", "Cloud City", "22260906", fdato, "lando@cloudcity.gov", "05299248072");
        Kunde test_12 = new Kunde("Tony", "Stark", "Pasadena", "22260906", fdato, "tony@starkindustries.com", "06049946838");
        Kunde test_13 = new Kunde("Pepper", "Pots", "Pasadena", "22260906", fdato, "pepper@starkindustries.com", "06047647884");
        Kunde test_14 = new Kunde("Bruce", "Wayne", "Gotham City", "22260906", fdato, "imbatman@waynemanor.net", "07097547933");
        
        Kunde test_15 = new Kunde("Bruce", "Hestman", "Kongleknaggen 18", "22260906", fdato, "hesterolf@yahoo.no", "08205049937");
        Kunde test_16 = new Kunde("Rolf", "Dæhlie", "Trysil 23B", "22260906", fdato, "epost@epost.no", "12048448997");
        Kunde test_17 = new Kunde("Bjørn", "Dæhlie", "Trondheim 23B", "22260906", fdato, "marit@langrenn.no", "02025449964");
        Kunde test_18 = new Kunde("Marit", "Dæhlie", "Trysil 23B", "22260906", fdato, "epost@epost.no", "01258443963");
        Kunde test_19 = new Kunde("Ole-Einar", "Skywalker", "Trysil 23B", "22260906", fdato, "kongen@skiskytter.no", "01258456816");
        Kunde test_20 = new Kunde("Ole", "Skywalker", "Tatooine", "22260906", fdato, "luke@cantina.com", "03307849785");
        Kunde test_21 = new Kunde("Luke", "Solo", "Millenium Falcon", "22260906", fdato, "hansolo@smuggler.org", "03307844899");
        Kunde test_22 = new Kunde("Darth", "Organa", "Deathstar", "22260906", fdato, "darkfather@deathstar.gov", "04115348879");
        Kunde test_23 = new Kunde("Darth", "Hestman", "Coruscant", "22260906", fdato, "palpatine@emperor.gov", "04115338644");
        Kunde test_24 = new Kunde("Tony", "Organa", "Coruscant", "22260906", fdato, "alderaan@galacticsenate.gov", "05269049681");
        Kunde test_25 = new Kunde("Tony", "Calrissian", "Cloud City", "22260906", fdato, "lando@cloudcity.gov", "05259248072");
        Kunde test_26 = new Kunde("Pepper", "Calrissian", "Pasadena", "22260906", fdato, "tony@starkindustries.com", "06043946838");
        Kunde test_27 = new Kunde("Pepper", "Ås", "Pasadena", "22260906", fdato, "pepper@starkindustries.com", "06067647884");
        Kunde test_28 = new Kunde("Bruce", "Ødegård", "Gotham City", "22260906", fdato, "imbatman@waynemanor.net", "07094547933");
        
        kunderegister.leggTil(test_1);
        kunderegister.leggTil(test_2);
        kunderegister.leggTil(test_3);
        kunderegister.leggTil(test_4);
        kunderegister.leggTil(test_5);
        kunderegister.leggTil(test_6);
        kunderegister.leggTil(test_7);
        kunderegister.leggTil(test_8);
        kunderegister.leggTil(test_9);
        kunderegister.leggTil(test_10);
        kunderegister.leggTil(test_11);
        kunderegister.leggTil(test_12);
        kunderegister.leggTil(test_13);
        kunderegister.leggTil(test_14);
        kunderegister.leggTil(test_15);
        kunderegister.leggTil(test_16);
        kunderegister.leggTil(test_17);
        kunderegister.leggTil(test_18);
        kunderegister.leggTil(test_19);
        kunderegister.leggTil(test_20);
        kunderegister.leggTil(test_21);
        kunderegister.leggTil(test_22);
        kunderegister.leggTil(test_23);
        kunderegister.leggTil(test_24);
        kunderegister.leggTil(test_25);
        kunderegister.leggTil(test_26);
        kunderegister.leggTil(test_27);
        kunderegister.leggTil(test_28);
        
        
        Eier eier = new Eier("Hans", "Hansen", "Heiveien 3", "97612312");
        Bilforsikring forsikring_1 = new Bilforsikring( test_1, 4000, "Kasko", "AX12345", 8000, "BMW", "520", "SUV", 340, 2000, 30000, "Bilfører < 23 år", 0.50, 1,true, false, false, false, 8000 );
        Bilforsikring forsikring_2 = new Bilforsikring( test_1, 8000, "Delkasko", "VX12345", 1600000, "BMW", "X5", "SUV", 140, 2013, 30000, "Bilfører < 23 år", 0.50, 1,false, true, false, false, 1600000 );
        Bilforsikring forsikring_3 = new Bilforsikring( test_1, 4000, "Ansvar", "CD67890", 270000,"Volvo", "V70", "Personbil", 800, 1968, 300000, "Bilfører < 23 år", -0.10, 1,true, true, true, false, 8000 );
        Bilforsikring forsikring_4 = new Bilforsikring( test_2, 12000, "Kasko", "FH12345", 20000, "KIA", "SUPER", "Personbil", 240, 1999, 30000, "Bilfører < 23 år", 0.50, 1,false, false, true, false, 270000 );
        Bilforsikring forsikring_5 = new Bilforsikring( test_2, 8000, "Delkasko", "EH12345", 8000, "VW", "POLO", "Personbil", 330, 1999, 30000, "Bilfører < 23 år", 0.50, 1,false, false, false, true, 8000 );
        Bilforsikring forsikring_6 = new Bilforsikring( test_3, 12000, "Delkasko", "EL12345", 1600000, "VW", "PASSAT", "Personbil", 190, 2014, 30000, "Bilfører < 23 år", 0.50, 1,false, true, false, false, 12000 );
        Bilforsikring forsikring_7 = new Bilforsikring( test_4, 4000, "Delkasko", "VA16745", 20000, "Volvo", "V70", "Personbil", 180, 1999, 30000, "Bilfører < 23 år", 0.50, 1,false, true, false, false, 1600000 );
        Bilforsikring forsikring_8 = new Bilforsikring( test_3, 20000, "Kasko", "DH12445", 1600000, "BMW", "X3", "SUV", 90, 2014, 30000, "Bilfører < 23 år", 0.50, 1,true, false, true, false, 12000 );
        Bilforsikring forsikring_9 = new Bilforsikring( test_5, 8000, "Superkasko", "DH12225", 20000, "Mercedes", "G-Class", "SUV", 100, 1999, 30000, "Bilfører < 23 år", 0.50, 1,true, true, false, false, 1600000 );
        Bilforsikring forsikring_10 = new Bilforsikring( test_5, 4000, "Kasko", "DH54321", 8000, "KIA", "KOREA", "SUV", 90, 2014, 30000, "Bilfører < 23 år", 0.50, 1,false, false, false, false, 8000 );
        Bilforsikring forsikring_11 = new Bilforsikring( test_6, 16000, "Superkasko", "DH13345", 1600000, "Mercedes", "M-Class", "SUV", 340, 2014, 30000, "Bilfører < 23 år", 0.50, 1,true, true, true, true, 1600000 );
        Bilforsikring forsikring_12 = new Bilforsikring( test_7, 12000, "Delkasko", "DH12645", 1600000, "Toyota", "Yaris", "Personbil", 340, 1999, 30000, "Bilfører < 23 år", 0.50, 1,true, false, false, false, 12000 );
        Bilforsikring forsikring_13 = new Bilforsikring( test_17, 16000, "Superkasko", "DH45345", 1600000, "Jaguar", "FX", "SUV", 340, 2014, 30000, "Bilfører < 23 år", 0.50, 1,false, false, true, true, 12000 );
        Bilforsikring forsikring_14 = new Bilforsikring( test_21, 12000, "Delkasko", "DH12225", 20000, "Volvo", "XC90", "SUV", 340, 2014, 30000, "Bilfører < 23 år", 0.50, 1,false, false, false, true, 12000 );
        Bilforsikring forsikring_15 = new Bilforsikring( test_22, 20000, "Superkasko", "VX12345", 1600000, "Volvo", "XC70", "SUV", 340, 1980, 30000, "Bilfører < 23 år", 0.50, 1,false, false, false, true, 12000 );
        Bilforsikring forsikring_16 = new Bilforsikring( test_23, 16000, "Kasko", "DH12345", 8000, "Lexus", "GS-400", "SUV", 340, 2014, 30000, "Bilfører < 23 år", 0.50, 1,false, false, false, true, 8000 );
        Bilforsikring forsikring_18 = new Bilforsikring( test_25, 8000, "Kasko", "VA12345", 1600000, "VW", "Passat", "SUV", 340, 2014, 30000, "Bilfører < 23 år", 0.50, 1,false, true, false, true, 1600000 );
        Bilforsikring forsikring_19 = new Bilforsikring( test_25, 16000, "Delkasko", "FX12345", 20000, "Volvo", "XC90", "SUV", 340, 2014, 30000, "Bilfører < 23 år", 0.50, 1,false, false, true, false, 12000 );
        Bilforsikring forsikring_20 = new Bilforsikring( test_24, 4000, "Delkasko", "DJ12335", 1600000, "Mercedes", "C-Class", "SUV", 340, 1970, 30000, "Bilfører < 23 år", 0.50, 1,false, false, true, true, 12000 );
        

        BatForsikring forsikring_21 = new BatForsikring( test_24, 12000, "Ansvar", "VX12345", 400000, "SuperBoat", "Ultra 360 FB","Cabin cruiser", 280, 2014, true, 30 );
        BatForsikring forsikring_22 = new BatForsikring( test_23, 12000, "Båt-Pluss", "CD67890", 400000, "Floater", "Ultra 360 FB","Seilbåt", 500, 2014, true, 30 );
        BatForsikring forsikring_23 = new BatForsikring( test_24, 20000, "Båt Pluss", "FX12345", 600000, "Tresfjord", "Ultra 360 FB","Cabin cruiser", 1000, 2014, false, 30 );
        BatForsikring forsikring_24 = new BatForsikring( test_22, 20000, "Båt Pluss", "FX12345", 4000, "Princess", "Ultra 360 FB","Cabin cruiser", 900, 2014, false, 30 );
        BatForsikring forsikring_25 = new BatForsikring( test_28, 16000, "Båt Pluss", "VX12345", 600000, "Tresfjord", "Ultra 360 FB","Cabin cruiser", 800, 2014, false, 30 );
        BatForsikring forsikring_26 = new BatForsikring( test_27, 20000, "Båt Pluss", "DK54321", 400000, "Tresfjord", "Ultra 360 FB","Cabin cruiser", 500, 2014, true, 30 );
        BatForsikring forsikring_27 = new BatForsikring( test_24, 12000, "Ansvar", "DJ12335", 4000, "Floater", "Ultra 360 FB","Skjærgårdsjeep", 400, 2014, false, 30 );
        BatForsikring forsikring_28 = new BatForsikring( test_21, 20000, "Båt Pluss", "DK54321", 600000, "Tresfjord", "Ultra 360 FB","Rib", 380, 2014, false, 30 );
        BatForsikring forsikring_29 = new BatForsikring( test_24, 16000, "Båt Pluss", "DJ12335", 600000, "Princess", "Ultra 360 FB","Rib", 520, 2014, true, 30 );
        BatForsikring forsikring_30 = new BatForsikring( test_20, 12000, "Ansvar", "DK54321", 400000, "Tresfjord", "Ultra 360 FB","Cabin cruiser", 420, 2014, false, 30 );
        BatForsikring forsikring_31 = new BatForsikring( test_24, 20000, "Båt Pluss", "DK54321", 4000, "Tresfjord", "Ultra 360 FB","Cabin cruiser", 621, 2014, true, 30 );
        BatForsikring forsikring_32 = new BatForsikring( test_24, 8000, "Båt Pluss", "DK54321", 400000, "Tresfjord", "Ultra 360 FB","Annen småbåt", 700, 2014, false, 30 );
        BatForsikring forsikring_33 = new BatForsikring( test_24, 16000, "Ansvar", "DH12225", 600000, "SuperBoat", "Ultra 360 FB","Cabin cruiser", 899, 2014, false, 30 );
        BatForsikring forsikring_34 = new BatForsikring( test_25, 8000, "Båt Pluss", "DJ12335", 4000, "Floater", "Ultra 360 FB","Rib", 2000, 2014, true, 30 );
        BatForsikring forsikring_35 = new BatForsikring( test_24, 16000, "Ansvar", "DJ12335", 400000, "Tresfjord", "Ultra 360 FB","Cabin cruiser", 599, 2014, false, 30 );
        BatForsikring forsikring_36 = new BatForsikring( test_19, 16000, "Båt Pluss", "EL12345", 600000, "Floater", "Ultra 360 FB","Annen småbåt", 700, 2014, false, 30 );
        
        Husforsikring forsikring_37 = new Husforsikring( test_1, 8000, "Hus", "Fjollesvingen 32",1970, "Tremannsbolig", "Laftet tømmer", "Høy standard", 320,4500000, 1200000, false);
        Husforsikring forsikring_38 = new Husforsikring( test_17, 8000, "Hus", "Fjollesvingen 32",1970, "Tremannsbolig", "Laftet tømmer", "Høy standard", 320,4500000, 1200000, false);
        Husforsikring forsikring_39 = new Husforsikring( test_24, 8000, "Hus", "Fjollesvingen 32",1970, "Tremannsbolig", "Laftet tømmer", "Høy standard", 320,4500000, 1200000, false);
        Husforsikring forsikring_40 = new Husforsikring( test_19, 8000, "Hus", "Fjollesvingen 32",1970, "Tremannsbolig", "Laftet tømmer", "Høy standard", 320,4500000, 1200000, false);
        Husforsikring forsikring_41 = new Husforsikring( test_1, 8000, "Hus", "Fjollesvingen 32",1970, "Tremannsbolig", "Laftet tømmer", "Høy standard", 320,4500000, 1200000, false);
        Husforsikring forsikring_42 = new Husforsikring( test_2, 8000, "Hus", "Fjollesvingen 32",1970, "Tremannsbolig", "Laftet tømmer", "Høy standard", 320,4500000, 1200000, false);
        Husforsikring forsikring_43 = new Husforsikring( test_3, 8000, "Hus", "Fjollesvingen 32",1970, "Tremannsbolig", "Laftet tømmer", "Høy standard", 320,4500000, 1200000, false);
        Husforsikring forsikring_44 = new Husforsikring( test_22, 8000, "Hus", "Fjollesvingen 32",1970, "Tremannsbolig", "Laftet tømmer", "Høy standard", 320,4500000, 1200000, false);
        Husforsikring forsikring_45 = new Husforsikring( test_28, 8000, "Hus", "Fjollesvingen 32",1970, "Tremannsbolig", "Laftet tømmer", "Høy standard", 320,4500000, 1200000, false);
        Husforsikring forsikring_46 = new Husforsikring( test_7, 8000, "Hus", "Fjollesvingen 32",1970, "Tremannsbolig", "Laftet tømmer", "Høy standard", 320,4500000, 1200000, false);
        Husforsikring forsikring_47 = new Husforsikring( test_25, 8000, "Hus", "Fjollesvingen 32",1970, "Tremannsbolig", "Laftet tømmer", "Høy standard", 320,4500000, 1200000, false);
        Husforsikring forsikring_48 = new Husforsikring( test_16, 8000, "Hus", "Fjollesvingen 32",1970, "Tremannsbolig", "Laftet tømmer", "Høy standard", 320,4500000, 1200000, false);
        Husforsikring forsikring_49 = new Husforsikring( test_21, 8000, "Hus", "Fjollesvingen 32",1970, "Tremannsbolig", "Laftet tømmer", "Høy standard", 320,4500000, 1200000, false);
        Husforsikring forsikring_50 = new Husforsikring( test_4, 8000, "Hus", "Fjollesvingen 32",1970, "Tremannsbolig", "Laftet tømmer", "Høy standard", 320,4500000, 1200000, false);
        Husforsikring forsikring_51 = new Husforsikring( test_21, 8000, "Hus", "Fjollesvingen 32",1970, "Tremannsbolig", "Laftet tømmer", "Høy standard", 320,4500000, 1200000, false);
        Husforsikring forsikring_52 = new Husforsikring( test_25, 8000, "Hus", "Fjollesvingen 32",1970, "Tremannsbolig", "Laftet tømmer", "Høy standard", 320,4500000, 1200000, false);
        Husforsikring forsikring_53 = new Husforsikring( test_21, 8000, "Hus", "Fjollesvingen 32",1970, "Tremannsbolig", "Laftet tømmer", "Høy standard", 320,4500000, 1200000, false);
        Husforsikring forsikring_54 = new Husforsikring( test_22, 8000, "Hus", "Fjollesvingen 32",1970, "Tremannsbolig", "Laftet tømmer", "Høy standard", 320,4500000, 1200000, false);
        Husforsikring forsikring_55 = new Husforsikring( test_21, 8000, "Hus", "Fjollesvingen 32",1970, "Tremannsbolig", "Laftet tømmer", "Høy standard", 320,4500000, 1200000, false);

        
        Reiseforsikring forsikring_56 = new Reiseforsikring( test_4, 4000, "Reise Pluss", false, 0, "Norden",40000 );
        Reiseforsikring forsikring_57 = new Reiseforsikring( test_1, 4000, "Reise Pluss", false, 0, "Norden",40000 );
        Reiseforsikring forsikring_58 = new Reiseforsikring( test_4, 4000, "Reise Pluss", false, 0, "Norden",40000 );
        Reiseforsikring forsikring_59 = new Reiseforsikring( test_17, 4000, "Reise Pluss", false, 0, "Norden",40000 );
        Reiseforsikring forsikring_60 = new Reiseforsikring( test_2, 4000, "Reise Pluss", false, 0, "Norden",40000 );
        Reiseforsikring forsikring_61 = new Reiseforsikring( test_25, 4000, "Reise Pluss", false, 0, "Norden",40000 );
        Reiseforsikring forsikring_62 = new Reiseforsikring( test_21, 4000, "Reise Pluss", false, 0, "Norden",40000 );
        Reiseforsikring forsikring_63 = new Reiseforsikring( test_22, 4000, "Reise Pluss", false, 0, "Norden",40000 );
        Reiseforsikring forsikring_64 = new Reiseforsikring( test_21, 4000, "Reise Pluss", false, 0, "Norden",40000 );
        Reiseforsikring forsikring_65 = new Reiseforsikring( test_8, 4000, "Reise Pluss", false, 0, "Norden",40000 );
        Reiseforsikring forsikring_66 = new Reiseforsikring( test_12, 4000, "Reise Pluss", false, 0, "Norden",40000 );
        Reiseforsikring forsikring_67 = new Reiseforsikring( test_9, 4000, "Reise Pluss", false, 0, "Norden",40000 );
        Reiseforsikring forsikring_68 = new Reiseforsikring( test_9, 4000, "Reise Pluss", false, 0, "Norden",40000 );
        Reiseforsikring forsikring_69 = new Reiseforsikring( test_28, 4000, "Reise Pluss", false, 0, "Norden",40000 );
        Reiseforsikring forsikring_70 = new Reiseforsikring( test_24, 4000, "Reise Pluss", false, 0, "Norden",40000 );
        Reiseforsikring forsikring_71 = new Reiseforsikring( test_7, 4000, "Reise Pluss", false, 0, "Norden",40000 );
        Reiseforsikring forsikring_72 = new Reiseforsikring( test_25, 4000, "Reise Pluss", false, 0, "Norden",40000 );
        Reiseforsikring forsikring_73 = new Reiseforsikring( test_21, 4000, "Reise Pluss", false, 0, "Norden",40000 );
        Reiseforsikring forsikring_74 = new Reiseforsikring( test_20, 4000, "Reise Pluss", false, 0, "Norden",40000 );
        
        Fritidsboligforsikring forsikring_75 = new Fritidsboligforsikring( test_20, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_76 = new Fritidsboligforsikring( test_9, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_77= new Fritidsboligforsikring( test_25, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_78 = new Fritidsboligforsikring( test_17, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_79 = new Fritidsboligforsikring( test_24, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_80 = new Fritidsboligforsikring( test_28, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_81= new Fritidsboligforsikring( test_24, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_82 = new Fritidsboligforsikring( test_17, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_83 = new Fritidsboligforsikring( test_25, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_84 = new Fritidsboligforsikring( test_25, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_85 = new Fritidsboligforsikring( test_24, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_86 = new Fritidsboligforsikring( test_4, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_87 = new Fritidsboligforsikring( test_24, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_88 = new Fritidsboligforsikring( test_3, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_89 = new Fritidsboligforsikring( test_25, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_90 = new Fritidsboligforsikring( test_4, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_91 = new Fritidsboligforsikring( test_9, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_92 = new Fritidsboligforsikring( test_19, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_93 = new Fritidsboligforsikring( test_24, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_94 = new Fritidsboligforsikring( test_9, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_95 = new Fritidsboligforsikring( test_22, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_96 = new Fritidsboligforsikring( test_22, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_97 = new Fritidsboligforsikring( test_23, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        Fritidsboligforsikring forsikring_98 = new Fritidsboligforsikring( test_21, 4000, "Fritidsbolig", "Hardangervidda", 1899, "Hus/Hytte", "Tre", "Normal standard", 13,250000, 125000, true, false);
        
        
        Skademelding skademelding_1 = new Skademelding( forsikring_98, new GregorianCalendar(1996, GregorianCalendar.DECEMBER , 12) , "Ulykke" , "Beskrivelse", 2000, 1000 );
        Skademelding skademelding_2 = new Skademelding( forsikring_97, new GregorianCalendar(2000, GregorianCalendar.NOVEMBER ,13) , "Ulykke" , "Beskrivelse", 14000, 10000 );
        Skademelding skademelding_3 = new Skademelding( forsikring_96, new GregorianCalendar(2012, GregorianCalendar.OCTOBER ,14) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_4 = new Skademelding( forsikring_96, new GregorianCalendar(2015, GregorianCalendar.JULY ,28) , "Brann" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_5 = new Skademelding( forsikring_96, new GregorianCalendar(2015, GregorianCalendar.JUNE  ,1) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_6 = new Skademelding( forsikring_88, new GregorianCalendar(2014, GregorianCalendar.APRIL ,9) , "Brann" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_7 = new Skademelding( forsikring_85, new GregorianCalendar(1999, GregorianCalendar.AUGUST ,2) , "Brann" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_8 = new Skademelding( forsikring_65, new GregorianCalendar(2001, GregorianCalendar.JANUARY ,1) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_9 = new Skademelding( forsikring_65, new GregorianCalendar(2002, GregorianCalendar.FEBRUARY ,3) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_10 = new Skademelding( forsikring_61, new GregorianCalendar(2002,GregorianCalendar.SEPTEMBER ,4) , "Brann" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_11 = new Skademelding( forsikring_61, new GregorianCalendar(2003,GregorianCalendar.MAY ,5) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_12 = new Skademelding( forsikring_59, new GregorianCalendar(2004,GregorianCalendar.MARCH ,6) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_13 = new Skademelding( forsikring_22, new GregorianCalendar(2006,GregorianCalendar.DECEMBER ,7) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_14 = new Skademelding( forsikring_24, new GregorianCalendar(2007,GregorianCalendar.DECEMBER ,8) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_15 = new Skademelding( forsikring_24, new GregorianCalendar(2009,GregorianCalendar.DECEMBER ,9) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_16 = new Skademelding( forsikring_27, new GregorianCalendar(2010,GregorianCalendar.DECEMBER ,2) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_17 = new Skademelding( forsikring_1, new GregorianCalendar(2013,GregorianCalendar.DECEMBER,1) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_18 = new Skademelding( forsikring_27, new GregorianCalendar(1998,GregorianCalendar.DECEMBER ,17) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_19 = new Skademelding( forsikring_32, new GregorianCalendar(1997,GregorianCalendar.DECEMBER ,27) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_20 = new Skademelding( forsikring_32, new GregorianCalendar(2003,GregorianCalendar.NOVEMBER ,29) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_21 = new Skademelding( forsikring_29, new GregorianCalendar(2001,GregorianCalendar.NOVEMBER ,20) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_22 = new Skademelding( forsikring_29, new GregorianCalendar(1989,GregorianCalendar.NOVEMBER ,19) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_23 = new Skademelding( forsikring_49, new GregorianCalendar(1990,GregorianCalendar.OCTOBER ,22) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_24 = new Skademelding( forsikring_49, new GregorianCalendar(1994,GregorianCalendar.OCTOBER ,23) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_25 = new Skademelding( forsikring_57, new GregorianCalendar(2015,GregorianCalendar.MARCH ,14) , "Tyveri/Hærverk" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_26 = new Skademelding( forsikring_63, new GregorianCalendar(2015,GregorianCalendar.MARCH ,30) , "Brann" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_27 = new Skademelding( forsikring_63, new GregorianCalendar(2015,GregorianCalendar.MARCH ,24) , "Brann" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_28 = new Skademelding( forsikring_57, new GregorianCalendar(2015,GregorianCalendar.MARCH ,28) , "Brann" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_29 = new Skademelding( forsikring_60, new GregorianCalendar(2015,GregorianCalendar.MARCH ,29) , "Brann" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_30 = new Skademelding( forsikring_52, new GregorianCalendar(2015,GregorianCalendar.OCTOBER ,12) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_32 = new Skademelding( forsikring_35, new GregorianCalendar(2015,GregorianCalendar.OCTOBER ,11) , "Brann" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_33 = new Skademelding( forsikring_31, new GregorianCalendar(2015,GregorianCalendar.OCTOBER ,14) , "Skadetype" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_34 = new Skademelding( forsikring_90, new GregorianCalendar(2012,GregorianCalendar.OCTOBER ,15) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_35 = new Skademelding( forsikring_68, new GregorianCalendar(2004,GregorianCalendar.JULY ,16) , "Brann" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_36 = new Skademelding( forsikring_71, new GregorianCalendar(2006,GregorianCalendar.JULY ,17) , "Brann" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_37 = new Skademelding( forsikring_45, new GregorianCalendar(2015,GregorianCalendar.JULY ,8) , "Brann" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_38 = new Skademelding( forsikring_38, new GregorianCalendar(2008,GregorianCalendar.JULY ,2) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_39 = new Skademelding( forsikring_82, new GregorianCalendar(2003,GregorianCalendar.JULY ,26) , "Brann" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_40 = new Skademelding( forsikring_76, new GregorianCalendar(2006,GregorianCalendar.JULY ,25) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_41 = new Skademelding( forsikring_79, new GregorianCalendar(2007,GregorianCalendar.JULY ,12) , "Brann" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_42 = new Skademelding( forsikring_80, new GregorianCalendar(2000,GregorianCalendar.JULY ,15) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_43 = new Skademelding( forsikring_78, new GregorianCalendar(2013,GregorianCalendar.JULY ,28) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_44 = new Skademelding( forsikring_42, new GregorianCalendar(2014,GregorianCalendar.JULY ,30) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_45 = new Skademelding( forsikring_42, new GregorianCalendar(2015,GregorianCalendar.JUNE ,2) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_46 = new Skademelding( forsikring_67, new GregorianCalendar(2013,GregorianCalendar.JUNE ,14) , "Brann" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_47 = new Skademelding( forsikring_43, new GregorianCalendar(2012,GregorianCalendar.JUNE ,6) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_48 = new Skademelding( forsikring_72, new GregorianCalendar(2011,GregorianCalendar.JUNE ,7) , "Brann" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_49 = new Skademelding( forsikring_87, new GregorianCalendar(2012,GregorianCalendar.JUNE ,9) , "Tap" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_50 = new Skademelding( forsikring_34, new GregorianCalendar(2014,GregorianCalendar.JUNE ,22) , "Tap" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_51 = new Skademelding( forsikring_75, new GregorianCalendar(2012,GregorianCalendar.JUNE ,14) , "Brann" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_52 = new Skademelding( forsikring_70, new GregorianCalendar(2009,GregorianCalendar.JUNE ,16) , "Tap" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_53 = new Skademelding( forsikring_47, new GregorianCalendar(2009,GregorianCalendar.JUNE ,18) , "Tap" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_54 = new Skademelding( forsikring_93, new GregorianCalendar(2015,GregorianCalendar.APRIL ,26) , "Tap" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_55 = new Skademelding( forsikring_25, new GregorianCalendar(2015,GregorianCalendar.APRIL ,27) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_56 = new Skademelding( forsikring_83, new GregorianCalendar(2014,GregorianCalendar.APRIL ,12) , "Tyveri/Hærverk" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_57 = new Skademelding( forsikring_54, new GregorianCalendar(2012,GregorianCalendar.APRIL ,15) , "Tyveri/Hærverk" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_58 = new Skademelding( forsikring_41, new GregorianCalendar(2014,GregorianCalendar.APRIL ,18) , "Tap" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_59 = new Skademelding( forsikring_23, new GregorianCalendar(2015,GregorianCalendar.AUGUST ,12) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_60 = new Skademelding( forsikring_86, new GregorianCalendar(2015,GregorianCalendar.AUGUST ,19) , "Tap" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_61 = new Skademelding( forsikring_30, new GregorianCalendar(2015,GregorianCalendar.AUGUST ,28) , "Tyveri/Hærverk" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_62 = new Skademelding( forsikring_87, new GregorianCalendar(2009,GregorianCalendar.AUGUST ,16) , "Tyveri/Hærverk" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_63 = new Skademelding( forsikring_87, new GregorianCalendar(2015,GregorianCalendar.AUGUST ,17) , "Tap" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_64 = new Skademelding( forsikring_84, new GregorianCalendar(2014,GregorianCalendar.JANUARY ,15) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_65 = new Skademelding( forsikring_36, new GregorianCalendar(2014,GregorianCalendar.JANUARY ,12) , "Tap" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_66 = new Skademelding( forsikring_36, new GregorianCalendar(2014,GregorianCalendar.JANUARY ,15) , "Brann" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_67 = new Skademelding( forsikring_36, new GregorianCalendar(2012,GregorianCalendar.JANUARY ,17) , "Brann" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_68 = new Skademelding( forsikring_30, new GregorianCalendar(2013,GregorianCalendar.FEBRUARY ,1) , "Annet" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_69 = new Skademelding( forsikring_31, new GregorianCalendar(2013,GregorianCalendar.FEBRUARY ,5) , "Tap" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_70 = new Skademelding( forsikring_40, new GregorianCalendar(2013,GregorianCalendar.FEBRUARY ,3) , "Annet" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_71 = new Skademelding( forsikring_30, new GregorianCalendar(2012,GregorianCalendar.FEBRUARY ,7) , "Annet" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_72 = new Skademelding( forsikring_20, new GregorianCalendar(2012,GregorianCalendar.FEBRUARY ,1) , "Tap" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_73 = new Skademelding( forsikring_65, new GregorianCalendar(2012,GregorianCalendar.SEPTEMBER ,6) , "Annet" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_74 = new Skademelding( forsikring_40, new GregorianCalendar(2012,GregorianCalendar.SEPTEMBER ,2) , "Tap" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_75 = new Skademelding( forsikring_16, new GregorianCalendar(2013,GregorianCalendar.SEPTEMBER ,6) , "Tap" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_76 = new Skademelding( forsikring_19, new GregorianCalendar(2014,GregorianCalendar.SEPTEMBER ,21) , "Annet" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_77 = new Skademelding( forsikring_20, new GregorianCalendar(2015,GregorianCalendar.MAY ,1) , "Annet" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_78 = new Skademelding( forsikring_13, new GregorianCalendar(2014,GregorianCalendar.MAY ,17) , "Ulykke" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_79 = new Skademelding( forsikring_11, new GregorianCalendar(2013,GregorianCalendar.MAY ,17) , "Annet" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_80 = new Skademelding( forsikring_10, new GregorianCalendar(2012,GregorianCalendar.MAY ,18) , "Annet" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_81 = new Skademelding( forsikring_11, new GregorianCalendar(2011,GregorianCalendar.NOVEMBER ,15) , "Tap" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_82 = new Skademelding( forsikring_1, new GregorianCalendar(2013,GregorianCalendar.MAY ,17) , "Annet" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_83 = new Skademelding( forsikring_20, new GregorianCalendar(2013,GregorianCalendar.MAY ,17) , "Annet" , "Beskrivelse", 2000, 30000 );
        Skademelding skademelding_84 = new Skademelding( forsikring_8, new GregorianCalendar(2013,GregorianCalendar.MAY ,17) , "Tap" , "Beskrivelse", 2000, 30000 );
        
        nyForsikring(forsikring_1);
        nyForsikring(forsikring_2);
        nyForsikring(forsikring_3);
        nyForsikring(forsikring_4);
        nyForsikring(forsikring_5);
        nyForsikring(forsikring_6);
        nyForsikring(forsikring_7);
        nyForsikring(forsikring_8);
        nyForsikring(forsikring_9);
        nyForsikring(forsikring_10);
        nyForsikring(forsikring_11);
        nyForsikring(forsikring_12);
        nyForsikring(forsikring_13);
        nyForsikring(forsikring_14);
        nyForsikring(forsikring_15);
        nyForsikring(forsikring_16);
        //nyForsikring(forsikring_17);
        nyForsikring(forsikring_18);
        nyForsikring(forsikring_19);
        nyForsikring(forsikring_20);
        nyForsikring(forsikring_21);
        nyForsikring(forsikring_22);
        nyForsikring(forsikring_23);
        nyForsikring(forsikring_24);
        nyForsikring(forsikring_25);
        nyForsikring(forsikring_26);
        nyForsikring(forsikring_27);
        nyForsikring(forsikring_28);
        nyForsikring(forsikring_29);
        nyForsikring(forsikring_30);
        nyForsikring(forsikring_31);
        nyForsikring(forsikring_32);
        nyForsikring(forsikring_33);
        nyForsikring(forsikring_34);
        nyForsikring(forsikring_35);
        nyForsikring(forsikring_36);
        nyForsikring(forsikring_37);
        nyForsikring(forsikring_38);
        nyForsikring(forsikring_39);
        nyForsikring(forsikring_40);
        nyForsikring(forsikring_41);
        nyForsikring(forsikring_42);
        nyForsikring(forsikring_43);
        nyForsikring(forsikring_44);
        nyForsikring(forsikring_45);
        nyForsikring(forsikring_46);
        nyForsikring(forsikring_47);
        nyForsikring(forsikring_48);
        nyForsikring(forsikring_49);
        nyForsikring(forsikring_50);
        nyForsikring(forsikring_51);
        nyForsikring(forsikring_52);
        nyForsikring(forsikring_53);
        nyForsikring(forsikring_54);
        nyForsikring(forsikring_55);
        nyForsikring(forsikring_56);
        nyForsikring(forsikring_57);
        nyForsikring(forsikring_58);
        nyForsikring(forsikring_59);
        nyForsikring(forsikring_60);
        nyForsikring(forsikring_61);
        nyForsikring(forsikring_62);
        nyForsikring(forsikring_63);
        nyForsikring(forsikring_64);
        nyForsikring(forsikring_65);
        nyForsikring(forsikring_66);
        nyForsikring(forsikring_67);
        nyForsikring(forsikring_68);
        nyForsikring(forsikring_69);
        nyForsikring(forsikring_70);
        nyForsikring(forsikring_71);
        nyForsikring(forsikring_72);
        nyForsikring(forsikring_73);
        nyForsikring(forsikring_74);
        nyForsikring(forsikring_75);
        nyForsikring(forsikring_76);
        nyForsikring(forsikring_77);
        nyForsikring(forsikring_78);
        nyForsikring(forsikring_79);
        nyForsikring(forsikring_80);
        nyForsikring(forsikring_81);
        nyForsikring(forsikring_82);
        nyForsikring(forsikring_83);
        nyForsikring(forsikring_84);
        nyForsikring(forsikring_85);
        nyForsikring(forsikring_86);
        nyForsikring(forsikring_87);
        nyForsikring(forsikring_88);
        nyForsikring(forsikring_89);
        nyForsikring(forsikring_90);
        nyForsikring(forsikring_91);
        nyForsikring(forsikring_92);
        nyForsikring(forsikring_93);
        nyForsikring(forsikring_94);
        nyForsikring(forsikring_95);
        nyForsikring(forsikring_96);
        nyForsikring(forsikring_97);
        nyForsikring(forsikring_98);
        
        nySkademelding(skademelding_1);
        nySkademelding(skademelding_2);
        nySkademelding(skademelding_3);
        nySkademelding(skademelding_4);
        nySkademelding(skademelding_5);
        nySkademelding(skademelding_6);
        nySkademelding(skademelding_7);
        nySkademelding(skademelding_8);
        nySkademelding(skademelding_9);
        nySkademelding(skademelding_10);
        nySkademelding(skademelding_11);
        nySkademelding(skademelding_12);
        nySkademelding(skademelding_13);
        nySkademelding(skademelding_14);
        nySkademelding(skademelding_15);
        nySkademelding(skademelding_16);
        nySkademelding(skademelding_17);
        nySkademelding(skademelding_18);
        nySkademelding(skademelding_19);
        nySkademelding(skademelding_20);
        nySkademelding(skademelding_21);
        nySkademelding(skademelding_22);
        nySkademelding(skademelding_23);
        nySkademelding(skademelding_24);
        nySkademelding(skademelding_25);
        nySkademelding(skademelding_26);
        nySkademelding(skademelding_27);
        nySkademelding(skademelding_28);
        nySkademelding(skademelding_29);
        nySkademelding(skademelding_30);
        //nySkademelding(skademelding_31);
        nySkademelding(skademelding_32);
        nySkademelding(skademelding_33);
        nySkademelding(skademelding_34);
        nySkademelding(skademelding_35);
        nySkademelding(skademelding_36);
        nySkademelding(skademelding_37);
        nySkademelding(skademelding_38);
        nySkademelding(skademelding_39);
        nySkademelding(skademelding_40);
        nySkademelding(skademelding_41);
        nySkademelding(skademelding_42);
        nySkademelding(skademelding_43);
        nySkademelding(skademelding_44);
        nySkademelding(skademelding_45);
        nySkademelding(skademelding_46);
        nySkademelding(skademelding_47);
        nySkademelding(skademelding_48);
        nySkademelding(skademelding_49);
        nySkademelding(skademelding_50);
        nySkademelding(skademelding_51);
        nySkademelding(skademelding_52);
        nySkademelding(skademelding_53);
        nySkademelding(skademelding_54);
        nySkademelding(skademelding_55);
        nySkademelding(skademelding_56);
        nySkademelding(skademelding_57);
        nySkademelding(skademelding_58);
        nySkademelding(skademelding_59);
        nySkademelding(skademelding_60);
        nySkademelding(skademelding_61);
        nySkademelding(skademelding_62);
        nySkademelding(skademelding_63);
        nySkademelding(skademelding_64);
        nySkademelding(skademelding_65);
        nySkademelding(skademelding_66);
        nySkademelding(skademelding_67);
        nySkademelding(skademelding_68);
        nySkademelding(skademelding_69);
        nySkademelding(skademelding_70);
        nySkademelding(skademelding_71);
        nySkademelding(skademelding_72);
        nySkademelding(skademelding_73);
        nySkademelding(skademelding_74);
        nySkademelding(skademelding_75);
        nySkademelding(skademelding_76);
        nySkademelding(skademelding_77);
        nySkademelding(skademelding_78);
        nySkademelding(skademelding_79);
        nySkademelding(skademelding_80);
        nySkademelding(skademelding_81);
        nySkademelding(skademelding_82);
        nySkademelding(skademelding_83);
        nySkademelding(skademelding_84);
        
        for(Forsikring f :forsikringsregister.alleForsikringer())
        {
            f.setArligPremie(100.0);
        }
       
        */
        
        
      lesFraFil();
        //skrivTilFil();

        //sjekkTid2();
    }
    
    
    public List<Skademelding> getAlleKundensSkademeldinger( Kunde kunde )
    {
        return skademeldingsregister.getKundensSkademeldinger(forsikringsregister.getKundensForsikringer(kunde));
    }
    
    public List<Forsikring> getAlleKundensForsikringer(Kunde kunde)
    {
        return forsikringsregister.getKundensForsikringer(kunde);
    }
    
    public final void sjekkTid()
    {
       //GregorianCalendar kalender = vindu.getKalender();
      for( Kunde kunde : kunderegister.alleKunder() )
        {
           List<Forsikring> forsikringsliste = getAlleKundensForsikringer( kunde );
           for( Forsikring forsikring : forsikringsliste )
            {
               
                    /*if(Math.abs(( kalender.getTime().getTime() - forsikring.getStartdato().getTime().getTime())) > (1000*60*60*24*365.25) ) 
                    {
                        forsikring.beregnPris();
                    }*/
            }
            
        }  
    }
    
    public final void sjekkTid2()
    {
        Calendar ettÅrSiden = Calendar.getInstance();
        ettÅrSiden.set(kalender.get(Calendar.YEAR) - 1, kalender.get(Calendar.MONTH), kalender.get(Calendar.DATE));
        System.out.print(sdf.format(ettÅrSiden.getTime()));
        if(!getForsikringrsliste().alleForsikringer().isEmpty())
        {
            for (Forsikring forsikring : getForsikringrsliste().alleForsikringer())
            {
                if(forsikring.getSistBetalt().before(ettÅrSiden.getTime()));
                {
                    if(forsikring.getForsikringsType().equals("Bilforsikring"))
                    {
                        Bilforsikring bilforsikring = (Bilforsikring)forsikring;
                        double bonusFør = bilforsikring.getBonus();
                        double originalPris = bilforsikring.getArligPremie() / bonusFør * 100;
                        bilforsikring.korrigerArligBonus();
                        bilforsikring.setArligPremie((originalPris * (100-bilforsikring.getBonus())));
                        innbetalinger.add(new Inntekt(kalender.getTime(), forsikring.getArligPremie(), forsikring));
                        forsikring.setSistBetalt(kalender.getTime());
                    }
                    else
                    {
                        innbetalinger.add(new Inntekt(kalender.getTime(), forsikring.getArligPremie(), forsikring));
                        forsikring.setSistBetalt(kalender.getTime());
                    }
                }
            }
        }
    }
           
    
    
    public List<Inntekt> getAlleInntekter()
    {
        return innbetalinger;
    }
    
    public List<Kunde> getAnsattKunde( Ansatt ansatt )
    {
        return kunderegister.getAnsattesKunder(ansatt);
    }
    
    public void nyKunde( Kunde nyKunde )
    {   
        kunderegister.leggTil(nyKunde);
        vindu.oppdaterTabell( kunderegister.alleKunder() );
    }
    
    public List<Kunde> finnKundeMedNavn(String fornavn, String etternavn)
    {
        return kunderegister.finnKunderEtterNavn(fornavn, etternavn);
    }
    
    
    public List<Kunde> finnKundeMedEtternavn(String etternavn)
    {
        return kunderegister.finnKundeEtterEtternavn(etternavn);
    }
    
    
    public List<Kunde> finnKundeMedFornavn(String fornavn)
    {
        return kunderegister.finnKundeEtterFornavn(fornavn);
    }
    
    
    public Kunde finnKundeMedPersonnummer( String personnummer )
    {
        return  kunderegister.finnKundeEtterPersonnummer(personnummer);
    }
    
    public Kunderegister getKundeliste()
    {
        return kunderegister;
    }
    
    
    
    //
    public List<Kunde> getAlleKunderMedForsikring(String f)
    {
        List<Kunde> kunderMedForsikring = new ArrayList<>();
        for (Forsikring forsikring : forsikringsregister.alleForsikringer())
        {
            if (forsikring.getForsikringsType().equals(f))
            {
                if (!kunderMedForsikring.contains(forsikring.getKunde()))
                kunderMedForsikring.add(forsikring.getKunde());
            }
        }
        return kunderMedForsikring;
    }
    
    
    // Returnerer skademeldingsregisteret.
    public SkademeldingRegister getSkademeldingsregister()
    {
        return skademeldingsregister;
    }
    
    // Returnerer forsikringsregisteret.
    public Forsikringsliste getForsikringrsliste()
    {
        return forsikringsregister;
    }
    
    // Returnerer ansattregisteret.
    public Ansattregister getAnsattregister()
    {
        return ansattregister;
    }
    
    /**
     * 
     * @param kunde
     * @return 
     */
    
    public double getNåværendeInntjening( Kunde kunde )
    {
        double sum = 0.0;
        List<Forsikring> kundeForsikringer = forsikringsregister.getKundensForsikringer(kunde);
        double prisPrMåned;
        int differanseMnd = 0;
        int diffYear;
        
        for(Forsikring forsikring :  kundeForsikringer)
        {    
            if( forsikring.getSluttdato() == null)
            {
                Calendar startdato = forsikring.getStartdato();
                diffYear = Calendar.getInstance().get(Calendar.YEAR) - startdato.get(Calendar.YEAR);
                differanseMnd = diffYear * 12 + Calendar.getInstance().get(Calendar.MONTH) - startdato.get(Calendar.MONTH);  
            }
            else
            {
                Calendar startDato = forsikring.getStartdato();
                diffYear = forsikring.getSluttdato().get(Calendar.YEAR) - startDato.get(Calendar.YEAR);
                differanseMnd = diffYear * 12 + forsikring.getSluttdato().get(Calendar.MONTH) - startDato.get(Calendar.MONTH);
                
            }
            System.out.print(forsikring.getStartdato().get(Calendar.YEAR));
            System.out.print(differanseMnd);
            prisPrMåned = forsikring.getArligPremie() / 12;
            System.out.print(prisPrMåned);
            sum += differanseMnd * prisPrMåned;
        }
        return sum;
    }
    
    /**
     * Henter total sum for utgifter forbundet med kunden som blir send med som parameter. 
     * @return int utgifter forbundet med en kunde.
     */
    public int getUtgifter( Kunde kunde )
    {
        int sum = 0;
        
        List <Skademelding> skademeldinger = skademeldingsregister.getKundensSkademeldinger( forsikringsregister.getKundensForsikringer(kunde));
        for( Skademelding skademelding: skademeldinger )
            sum+= skademelding.getErstatningsbelop();
        
        return sum;
    }
    
    /**
     * returnerer totale summen av av alle aktive forsikrings premier til en kunde.
     * @param kunde
     * @return 
     */
    
    public double getInntekter( Kunde kunde )
    {
        double sum = 0 ;
        List<Forsikring> forsikringsliste = forsikringsregister.getKundensForsikringer(kunde);
        
        for(Forsikring forsikring : forsikringsliste )
            if( forsikring.erAktiv())
                sum += forsikring.getArligPremie();
        
        return sum;
    }
    

    //returner totale summen for alle årlie forsikringspremer for alle kunder totalt.
    public double getInntekter()
    {
        double totalSum = 0.0;
        
        for( Kunde kunde : kunderegister.alleKunder() )
        {
            totalSum += kunde.getÅrligForsikringsPremie();
        }
        return totalSum;
    }
    
    // returnerer total erstatningsbeløp for alle skademeldinger som er registrert.
    public double getUtgifter()
    {
        double totalSum = 0.0;
        
        for( Skademelding skademelding : skademeldingsregister.alleSkademeldinger() )
        {
            totalSum += skademelding.getErstatningsbelop();
        }
        return totalSum;
    }
    
    /**
     * Legger inn ny forsikring i forsikringsregisteret. Kjøerer en test for å 
     * se hvor mange unike aktive forsikringer en kunde har, dersom kunden har mer
     * enn tre aktive forsikringer settes kunden som totalkunde.
     * 
     * @param nyForsikring 
     */
    
    public void nyForsikring( Forsikring nyForsikring  )
    {
        forsikringsregister.leggTil( nyForsikring.getKunde(), nyForsikring);
        Date dato = new Date();
        
        if(forsikringsregister.antallUnikeAktiveForsikringer(nyForsikring.getKunde()).size() >= 3)
        {
            nyForsikring.getKunde().setTotalKunde(true);
            innbetalinger.add(new Inntekt(dato, (nyForsikring.getArligPremie() * 0.9), nyForsikring));
            if(forsikringsregister.antallUnikeAktiveForsikringer(nyForsikring.getKunde()).size() == 3)
                vindu.visInformasjon("Beskjed", nyForsikring.getKunde().getFornavn() + " " + nyForsikring.getKunde().getEtternavn() + " er nå totalkunde. ");
            
            vindu.oppdaterTabell(kunderegister.alleKunder());
            skrivTilFil();
        }
        else
        {
            innbetalinger.add(new Inntekt(dato, nyForsikring.getArligPremie(), nyForsikring));
        }
    }
    
    
    /**
     * Metoden sier opp en forsikring og tester om kunden mister status som totalkunde dersom
     * forsikringen deaktiveres.
     * 
     * @param forsikringsnummer 
     */
    
    public void deaktiverForsikring( Integer forsikringsnummer )
    {
        Forsikring forsikring = forsikringsregister.getForsikring(forsikringsnummer);
        if( forsikringsregister.antallUnikeAktiveForsikringer(forsikring.getKunde()).size() == 3)
        {
            forsikring.getKunde().setTotalKunde(false);
        }
        forsikring.setAktiver(false);
        vindu.oppdaterTabell( kunderegister.alleKunder() );
        vindu.visInformasjon("Beskjed", "Forsikringen er deaktivert. ");
        skrivTilFil();
    }
    
    /**
     * Legger inn ny skademelding i skademeldingsregisteret, dersom 
     * skademeldingen tilhører en bilforsikring, korrigeres bonusen automatisk.
     * 
     * @param nySkademelding
     * @return 
     */
    
    public boolean nySkademelding( Skademelding nySkademelding )
    {
        skademeldingsregister.leggTil( nySkademelding.getForsikring(), nySkademelding );
        if ( nySkademelding.getForsikring() instanceof Bilforsikring )
        {
            Bilforsikring bilforsikring = (Bilforsikring) nySkademelding.getForsikring();
            bilforsikring.korrigerBonusVedSkade();
        }
        skrivTilFil();
        return true;
    }
    
    // returnerer ansatt som har matchede brukernavn og passord. Brukes i LoginVindu.
    public Ansatt login( String brukernavn, String passord )
    {
        for( Ansatt arbeidstaker: ansattregister.getAlleAnsatte())
        {
            if( arbeidstaker.getBrukernavn().equals(brukernavn) && arbeidstaker.getPassord().equals(passord))
                return arbeidstaker;
        }
        return null;
    }
    
    // skriver data til fil.
    public void skrivTilFil()
    {
        try( ObjectOutputStream utfil = new ObjectOutputStream(
                new FileOutputStream("Data\\ForsikringsData.dta")) )
        {
            utfil.writeObject(kunderegister);
            utfil.writeObject(forsikringsregister);
            utfil.writeObject(skademeldingsregister);
            utfil.writeObject(ansattregister);
            utfil.writeObject(innbetalinger);
            utfil.writeInt(Forsikring.getLøpenummer());
            utfil.writeInt(Skademelding.getLøpenummer());
            utfil.close();
        }
        catch( IOException e )
        {
            
            
        }
    }
    
    
    // leser data fra fil.
    public void lesFraFil()
    {
        try( ObjectInputStream innfil = new ObjectInputStream(
              new FileInputStream("Data\\ForsikringsData.dta")))
        {
            kunderegister = (Kunderegister)innfil.readObject();
            forsikringsregister = (Forsikringsliste) innfil.readObject();
            skademeldingsregister = (SkademeldingRegister) innfil.readObject();
            ansattregister = (Ansattregister) innfil.readObject();
            innbetalinger = (List<Inntekt>) innfil.readObject();
            Forsikring.setLøpenummer(innfil.readInt());
            Skademelding.setLøpenummer(innfil.readInt());
            innfil.close();
        }
        catch( IOException | ClassNotFoundException e )
        {
           
        }
    }
}


