package Algos;

import Objets.Objet;
import Objets.SacADos;

import java.util.ArrayList;

public class PSE {

    private ArrayList<Objet> objets;
    SacADos sac = new SacADos();
    private double maxVal;
    private ABR meilleurRes;
    private double poidsMax;
    private double minVal;


    public PSE(SacADos sac,ArrayList<Objet> objet){
        this.sac = sac;
        objets = objet;
    }



    public void resoudre(){
        this.poidsMax = sac.getPoidsSacMax();
        for(Objet o : this.objets)
            maxVal += o.getValeur();

        ABR root = new ABR();
        this.meilleurRes = root;

        creeArbreRec(0, root, maxVal);

        sac.viderSac();
        ajouterSolutionRec(this.meilleurRes);
    }

    private void creeArbreRec(int index, ABR noeudActuel, double maxPossible) {
        noeudActuel.setFilsGauche(this.objets.get(index), index);
        //On crée une copie (on ne rajoute rien) dans le fils droit
        noeudActuel.setFilsDroit();

        //On teste si une nouvelle meilleure solution possible est trouvée à gauche
        if(noeudActuel.getFilsGauche().getValeur() >= this.minVal
                && noeudActuel.getFilsGauche().getPoids() <= this.poidsMax) {
            this.meilleurRes = noeudActuel.getFilsGauche();
            this.minVal = this.meilleurRes.getValeur();
        }

        //Si il reste encore des objets à mettre dans le sac et que le poids maximal n'est pas atteint
        if(index < this.objets.size() - 1 && noeudActuel.getPoids() < this.poidsMax) {
            //Le noeud gauche n'est pas concerné par le potentielMax,
            //Car s'il ne pouvait pas atteindre la borne minimale, il aurait été supprimé au noeud inférieur
            creeArbreRec(index + 1, noeudActuel.getFilsGauche(), maxPossible);

            //On calcule le 'potentiel' des sous arbres, en calculant la borne maximale possible
            //Pour cela, on prend la valeur max possible - l'objet que l'on ne va pas ajouter
            double potentielMax = maxPossible - this.objets.get(index).getValeur();

            //, on continue la recherche
            if(potentielMax >= this.minVal ) {
                creeArbreRec(index + 1, noeudActuel.getFilsDroit(), potentielMax);
            }
        }
    }

    private void ajouterSolutionRec(ABR noeudGagnant) {
        int i;

        //Si l'index est -1, il n'y a aucun objet à ajouter
        if((i = noeudGagnant.getIndexObjet()) != -1 && this.objets.get(i).getPoid()+sac.getPsac() <+ sac.getPoidsSacMax()) {
            //On ajoute l'objet
            sac.setObjets(this.objets.get(i));
        }

        //On remonte jusqu'à la racine en ajoutant tout les objets
        if(!noeudGagnant.estRacine()) {
            ajouterSolutionRec(noeudGagnant.getParent());
        }
    }
}
