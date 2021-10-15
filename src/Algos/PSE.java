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

        noeudActuel.setFilsDroit();

        if(noeudActuel.getFilsGauche().getValeur() >= this.minVal
                && noeudActuel.getFilsGauche().getPoids() <= this.poidsMax) {
            this.meilleurRes = noeudActuel.getFilsGauche();
            this.minVal = this.meilleurRes.getValeur();
        }


        if(index < this.objets.size() - 1 && noeudActuel.getPoids() < this.poidsMax) {

            creeArbreRec(index + 1, noeudActuel.getFilsGauche(), maxPossible);

            double potentielMax = maxPossible - this.objets.get(index).getValeur();

            if(potentielMax >= this.minVal ) {
                creeArbreRec(index + 1, noeudActuel.getFilsDroit(), potentielMax);
            }
        }
    }

    private void ajouterSolutionRec(ABR noeudGagnant) {
        int i;

        if((i = noeudGagnant.getIndexObjet()) != -1 && this.objets.get(i).getPoid()+sac.getPsac() <+ sac.getPoidsSacMax()) {
            sac.setObjets(this.objets.get(i));
        }

        if(!noeudGagnant.estRacine()) {
            ajouterSolutionRec(noeudGagnant.getParent());
        }
    }
}
