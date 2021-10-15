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

    private ArrayList<Objet> objets;
    private SacADos sacADos;

    public PSEOptimisé(SacADos sacADos , ArrayList<Objet> objet){
        this.sacADos = sacADos;
        this.objets = objet;

    }

    public void resoudre(){
        this.borneSup = sacADos.getSommeValeurTousObjets();
        this.borneInf = sacADos.getSommeValeurGlotonne();

        System.out.println("Borne inférieur "+this.borneInf);
        System.out.println("Borne Supérieur " + this.borneSup);

        // créer la raçine de l'arbre
        this.rootTree = new SearchTree();
        this.bestSolutionTree = rootTree;
        creeArbreRec(0, rootTree, borneSup);

        sacADos.viderSac();
        ajouterSolutionRec(this.bestSolutionTree);
    }

    private void creeArbreRec(int index, SearchTree noeudActuel, double maxPossible) {
        noeudActuel.setLeftSon(this.objets.get(index), index);

        noeudActuel.setRightSon();

        if(noeudActuel.getLeftSon().getValeur() >= this.borneInf
                && noeudActuel.getLeftSon().getPoids() <= this.poidMax) {
            this.bestSolutionTree = noeudActuel.getLeftSon();
            this.borneInf = this.bestSolutionTree.getValeur();
        }


        if(index < this.objets.size() - 1 && noeudActuel.getPoids() < this.poidMax) {

            creeArbreRec(index + 1, noeudActuel.getLeftSon(), maxPossible);

            double potentielMax = maxPossible - this.objets.get(index).getValeur();

            if(potentielMax >= this.borneInf ) {
                creeArbreRec(index + 1, noeudActuel.getRightSon(), potentielMax);
            }
        }
    }

    private void ajouterSolutionRec(SearchTree noeudGagnant) {
        int i;

        if((i = noeudGagnant.getIndexObjet()) != -1 && this.objets.get(i).getPoid()+sacADos.getPsac() <+ sacADos.getPoidsSacMax()) {
            sacADos.setObjets(this.objets.get(i));
        }

        if(!noeudGagnant.isRoot()) {
            ajouterSolutionRec(noeudGagnant.getFather());
        }
    }

}
