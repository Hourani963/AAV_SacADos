package Application;

import Objets.SacADos;

public class Appli {

    public static void main(String[] args) {
        //String chemin = "C:\\\\Users\\\\UGARIT\\\\Desktop\\\\Ahmad\\\\DUT\\\\A2 S1\\\\AAV\\\\projet\\\\items.txt";
        //String chemin = args[1];
        String chemin = "C:\\Users\\UGARIT\\Desktop\\GIT\\AAV_SacADos\\text\\items.txt";
        SacADos sac = new SacADos(chemin, 10);
        sac.analyseFile();

        //sac.resoudreGloutonne();
        //sac.resoudreProgDynam();
        sac.resoudrePSE();
        System.out.println("Valeurs = " + sac.getVsac());
        System.out.println("Poid = " + sac.getPsac());
        System.out.println("Poid Max = " + sac.getPoidsSacMax());

        System.out.println(sac.toString());
    }
}
