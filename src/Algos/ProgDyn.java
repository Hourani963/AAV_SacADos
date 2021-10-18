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
            if(i<0) break;
            jFloat = jFloat - objets.get(i).getPoid();
            j = j - (int) objets.get(i).getPoid();
            if (jFloat >= 0 )
                sac.setObjets(objets.get(i));
            else break;
            --i;
        }
    }
}
