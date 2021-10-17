package Algos;

import Objets.Objet;

import java.util.Comparator;

public class objetsSorter implements Comparator<Objet> {



    @Override
    public int compare(Objet o1, Objet o2 ){
        Float f1 = o1.getV_p();
        Float f2 = o2.getV_p();
        return f2.compareTo(f1);
    }
}

