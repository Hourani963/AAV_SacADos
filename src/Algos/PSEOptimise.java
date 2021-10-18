package Algos;

import Objets.Objet;
import Objets.SacADos;

import java.util.ArrayList;

public class PSEOptimise {

    private float borneInf;
    private float borneSup;

    private float poidMax;

    private SearchTree bestSolutionTree;

    private ArrayList<Objet> objets;
    private SacADos sacADos;

    public PSEOptimise(SacADos sacADos , ArrayList<Objet> objet){
        this.sacADos = sacADos;
        this.objets = objet;
    }

    public void resoudre(){
        this.poidMax = sacADos.getPoidsSacMax();
        this.borneSup = sacADos.getSommeValeurTousObjets();
        this.borneInf = 0;
        if(borneSup != sacADos.getVsac()){
            System.out.println("Initialisation des bornes :");
            System.out.println("Borne inférieur "+this.borneInf);
            System.out.println("Borne Supérieur " + this.borneSup + "\n");

            SearchTree root = new SearchTree();
            // créer la raçine de l'arbre

            this.bestSolutionTree = root;

            creeArbreRec(0, root, borneSup);

            sacADos.viderSac();
            ajouterSolutionRec(this.bestSolutionTree);
        }
        else{
            sacADos.toString();
        }

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

        if((i = noeudGagnant.getIndexObjet()) != -1 && this.objets.get(i).getPoid() + sacADos.getPsac() <= poidMax) {
            sacADos.setObjets(this.objets.get(i));
        }

        if(!noeudGagnant.isRoot()) {
            ajouterSolutionRec(noeudGagnant.getFather());
        }
    }
}
