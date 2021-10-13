package Algos;

import Objets.Objet;
import Objets.SacADos;

import java.util.*;

public class Gloutonne {
    private ArrayList<Objet> Objets;

    private SacADos sacADos;

    public Gloutonne(ArrayList<Objet> Objets, SacADos sac){
        this.Objets = Objets;
        this.sacADos = sac;
    }

    public void selection(){
        int i = 0;
        do{
            sacADos.setObjets(Objets.get(i));
            i++;
            if(i == Objets.size()) break;
        }while (sacADos.getPsac() < sacADos.getPoidsSacMax());
    }

}
