package Application;

import Objets.SacADos;

public class Appli {

    public static void main(String[] args) {
        String chemin = "C:\\\\Users\\\\UGARIT\\\\Desktop\\\\GIt\\\\AAV_SacADos\\\\text\\\\items.txt";
        //String chemin = args[1];
        //String chemin = "C:\\\\Users\\\\Ugarit\\\\Desktop\\\\AAV\\\\AAV_SacADos\\\\text\\\\items.txt";
        SacADos sac = new SacADos(chemin, 10);


        sac.analyseFile();

        sac.resoudreGloutonne();
        //sac.resoudrePSE();

        System.out.println("Valeurs = " + sac.getVsac());
        System.out.println("Poid = " + sac.getPsac());
        System.out.println("Poid Max = " + sac.getPoidsSacMax());
        //System.out.println(sac.toStringAllObjets());
        System.out.println(sac.toString());


    }
}
