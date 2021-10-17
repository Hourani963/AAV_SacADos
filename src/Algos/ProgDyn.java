package Algos;

import Objets.Objet;
import Objets.SacADos;

import java.util.ArrayList;

public class ProgDyn {
    private ArrayList<Objet> objets;
    SacADos sac = new SacADos();
    public ProgDyn(SacADos sac,ArrayList<Objet> objet){
        this.sac = sac;
        objets = objet;
    }

    public void resoudre(){
        int nbrObjets = objets.size();
        int poidSacMax = (int)(sac.getPoidsSacMax());
        float[][] Matrix = new float[nbrObjets][poidSacMax + 1];


        for (int j = 0; j <= poidSacMax; j++) {
            if (objets.get(0).getPoid() > j)
                Matrix[0][j] = 0;
            else
                Matrix[0][j] = objets.get(0).getValeur();
        }


        for(int i = 1; i < nbrObjets; ++i) {
            for (int j = 0; j <= poidSacMax; ++j)
                if(objets.get(i).getPoid()  > j)
                    Matrix[i][j] = Matrix[i - 1][j];
                else
                    Matrix[i][j] =Math.max(Matrix[i - 1][j],
                            (Matrix[i-1][(int) ( j - (objets.get(i).getPoid() ))]
                                    + objets.get(i).getValeur()));
        }

        int i = nbrObjets - 1;
        int j = poidSacMax;
        float test = poidSacMax;

        while(Matrix[i][j] == Matrix[i][j-1]){
            --j;
        }

        while(j > 0) {
            while (i > 0 && Matrix[i][j] == Matrix[i - 1][j]){
                --i;
            }

            test = test - objets.get(i).getPoid();
            j = j - (int) objets.get(i).getPoid();
            if (test >= 0 )
                sac.setObjets(objets.get(i));
            else break;
            --i;
        }
    }
}
