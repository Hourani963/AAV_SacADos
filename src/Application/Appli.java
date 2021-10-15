package Application;

import Objets.SacADos;

public class Appli {

    public static void main(String[] args) {
        //String chemin = "C:\\\\Users\\\\UGARIT\\\\Desktop\\\\Ahmad\\\\DUT\\\\A2 S1\\\\AAV\\\\projet\\\\items.txt";
        //String chemin = args[1];
        String chemin = "C:\\\\Users\\\\AboAlwalid\\\\Desktop\\\\AAV\\\\AAV_SacADos\\\\text\\\\items.txt";
        SacADos sac = new SacADos(chemin, 6);
        SacADos sac2 = new SacADos(chemin, 6);
        SacADos sac3 = new SacADos(chemin, 6);
        sac.analyseFile();
        sac2.analyseFile();
        sac3.analyseFile();

        sac.resoudreGloutonne();
        //sac2.resoudreProgDynam();
        //sac3.resoudrePSE();
        System.out.println("Valeurs = " + sac.getVsac());
        System.out.println("Poid = " + sac.getPsac());
        System.out.println("Poid Max = " + sac.getPoidsSacMax());
        System.out.println(sac.toString());

        System.out.println("Valeurs = " + sac2.getVsac());
        System.out.println("Poid = " + sac2.getPsac());
        System.out.println("Poid Max = " + sac2.getPoidsSacMax());
        System.out.println(sac2.toString());

        System.out.println("Valeurs = " + sac3.getVsac());
        System.out.println("Poid = " + sac3.getPsac());
        System.out.println("Poid Max = " + sac3.getPoidsSacMax());
        System.out.println(sac3.toString());
    }
}
