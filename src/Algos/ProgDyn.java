package Algos;

import Objets.Objet;
import Objets.SacADos;

import java.util.ArrayList;

public class ProgDyn implements AlgosMethodes{
    private ArrayList<Objet> objets;
    SacADos sac = new SacADos();
    public ProgDyn(SacADos sac,ArrayList<Objet> objet){
        this.sac = sac;
        objets = objet;
    }

    /**
     * résolution de la programmation dynamique
     */
    public void resoudre(){
        // pour la précision on multiplie le poid du sac et le poid de chaque objet par 10 et à la fin de l'algo on dévise par 10
        // la multiplication se fait pour la présion du calcule, calculer le flout.
        for (Objet o: objets) {
            o.setPoid(o.getPoid()*10);
        }
        sac.setPoidsSacMax(sac.getPoidsSacMax()*10);

        int nbrObjets = objets.size();
        int poidSacMax = (int)(sac.getPoidsSacMax());
        float[][] Matrix = new float[nbrObjets][poidSacMax + 1];

        // construire la premièer ligne de matrice
        for (int j = 0; j <= poidSacMax; j++) {
            if (objets.get(0).getPoid() > j)
                Matrix[0][j] = 0;
            else
                Matrix[0][j] = objets.get(0).getValeur();
        }

        // construire le reste de la matrice
        for(int i = 1; i < nbrObjets; ++i) {
            for (int j = 0; j <= poidSacMax; ++j)
                if(objets.get(i).getPoid()  > j)
                    Matrix[i][j] = Matrix[i - 1][j];
                else
                    Matrix[i][j] =Math.max(Matrix[i - 1][j],
                            (Matrix[i-1][(int) ( j - (objets.get(i).getPoid() ))]
                                    + objets.get(i).getValeur()));
        }

        // choisire les objet à partir de la matrice
        int i = nbrObjets - 1;
        int j = poidSacMax;
        float jFloat = poidSacMax;

        while(Matrix[i][j] == Matrix[i][j-1]){
            --j;
        }

        while(j > 0) {
            while (i > 0 && Matrix[i][j] == Matrix[i - 1][j]){
                --i;
            }

            jFloat = jFloat - objets.get(i).getPoid();
            j = j - (int) objets.get(i).getPoid();
            if (jFloat >= 0 )
                sac.setObjets(objets.get(i));
            else break;
            --i;
            if(i<0) break;
        }
        // on dévise le poid de sacMax, poid de sac et poid de tous les objets par 10 pour revenir au poid normal
        for (Objet o: objets) {
            o.setPoid(o.getPoid()/10);
        }
        sac.setPoidsSacMax(sac.getPoidsSacMax()/10);
        sac.setPoid(sac.getPsac()/10);
    }
}
