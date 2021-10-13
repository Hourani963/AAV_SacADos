package Algos;

import Objets.Objet;

import java.util.Comparator;

public class objetsSorter implements Comparator<Objet> {



    @Override
    public int compare(Objet o1, Objet o2 ){
        return Math.round(o2.getV_p() - o1.getV_p());
    }
}

