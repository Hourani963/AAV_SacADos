package Algos;

import Objets.Objet;

import java.util.Comparator;

public class objetsSorter implements Comparator<Objet> {


    /**
     * Comperateur des objets par leur valeur
     * @param o1 : objet A
     * @param o2 : objet B
     * @return valeur de retour s'il est supérieur ou inférieur
     */
    @Override
    public int compare(Objet o1, Objet o2 ){
        Float f1 = o1.getV_p();
        Float f2 = o2.getV_p();
        return f2.compareTo(f1);
    }
}

