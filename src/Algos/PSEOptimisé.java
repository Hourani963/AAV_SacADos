package Algos;

import Objets.Objet;
import Objets.SacADos;

import java.util.ArrayList;

public class PSEOptimisé {

    private float borneInf;
    private float borneSup;

    private float poidMax;
    private SearchTree rootTree;
    private SearchTree bestSolutionTree;

    private ArrayList<Objet> obj;
    private SacADos sacADos;

    public PSEOptimisé(SacADos sacADos , ArrayList<Objet> objet){
        this.sacADos = sacADos;
        this.obj = objet;

    }

    public void resoudre(){
        this.borneSup = sacADos.getSommeValeurTousObjets();
        this.borneInf = sacADos.getSommeValeurGlotonne();

        System.out.println("Borne inférieur "+this.borneInf);
        System.out.println("Borne Supérieur " + this.borneSup);

        // créer la raçine de l'arbre
        this.rootTree = new SearchTree();

    }

    private void creatTree(int index, SearchTree noeudActuel, float ){

    }

}
