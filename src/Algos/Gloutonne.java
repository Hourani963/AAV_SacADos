package Algos;

import Objets.Objet;
import Objets.SacADos;

import java.util.*;

public class Gloutonne implements AlgosMethodes {
    private ArrayList<Objet> Objets;

    private SacADos sacADos;

    /**
     * Constructeur de la class Gloutonne
     * @param Objets : liste de tous les
     * @param sac
     */
    public Gloutonne(ArrayList<Objet> Objets, SacADos sac){
        this.Objets = Objets;
        this.sacADos = sac;
    }

    /**
     * resoudre l'algo
     */
    public void resoudre(){
        Objets.sort(new objetsSorter());
        this.selection();
    }

    /**
     * Sélectionner les objet à insérer dans le sac ( dernière étape dans le premier Algo)
     */
    public void selection(){
        int i = 0;
        do{
            if(sacADos.getPoidsSacMax()-sacADos.getPsac() >= Objets.get(i).getPoid())
                sacADos.setObjets(Objets.get(i));
            i++;
            if(i == Objets.size() ) break;
        }while (sacADos.getPsac() < sacADos.getPoidsSacMax());
    }
}
