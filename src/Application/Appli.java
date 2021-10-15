package Application;

import Objets.SacADos;

import java.util.Scanner;

public class Appli {

    public static void main(String[] args) {



        boolean n = true;
        while (n){
            System.out.printf("> ");
            Scanner sc = new Scanner(System.in);
            String ligne = sc.nextLine();

            String[] parts = ligne.split(" ");
            if (parts[0].equals("resoudre") ) {
                float poid = Float.parseFloat(parts[2]);
                //String chemin = "C:\\Users\\UGARIT\\Desktop\\GIT\\AAV_SacADos\\text\\items.txt";

                SacADos sac = new SacADos("text\\"+parts[1]+".txt", poid);
                sac.analyseFile();
                long startTime = System.currentTimeMillis();
                switch (parts[3]){
                    case "gloutonne," :
                        System.out.println("Méthode -> gloutonne"); sac.resoudreGloutonne(); n=false;
                        break;
                    case "dyn" :
                        System.out.println("Méthode -> dyn");sac.resoudreProgDynam(); n=false;
                        break;
                    case "pse" :
                        System.out.println("Méthode -> pse"); sac.resoudrePSE(); n=false;
                        break;
                    default:
                        System.out.println("pls choose -> gloutonne or dyn or pse");
                }
                long endTime   = System.currentTimeMillis();
                long totalTime = endTime - startTime;


                System.out.println("Valeurs = " + sac.getVsac());
                System.out.println("Poid = " + sac.getPsac());
                System.out.println("Poid Max = " + sac.getPoidsSacMax()+"\n");
                System.out.println(sac.toString());

                System.out.println("Temps d'exe " + totalTime + "ms");
            }
            else if(parts[0].equals("exit")) n = false;

            else System.out.println("il faut écrire [resoudre-sac-a-dos]" +
                    "[chemin]"+ "[chemin]" + "[poids-maximal]" + "[methode]"+
                    "===> write [exit] to stop the program");
        }

    }
}
