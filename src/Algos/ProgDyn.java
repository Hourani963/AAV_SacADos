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
        int NB_OBJETS = objets.size();
        int PoidsMaxInt = (int)(sac.getPoidsSacMax());
        int[][] table = new int[NB_OBJETS][PoidsMaxInt + 1];

        //On remplit d'abord la première ligne
        for (int j = 0; j <= PoidsMaxInt; j++) {
            if (objets.get(0).getPoid() > j)
                table[0][j] = 0;
            else
                table[0][j] = (int) objets.get(0).getValeur();
        }

        //On remplit toutes les autres lignes
        for(int i = 1; i < NB_OBJETS; ++i) {
            for (int j = 0; j <= PoidsMaxInt; ++j)
                if(objets.get(i).getPoid()  > j)
                    table[i][j] = table[i - 1][j];
                else
                    table[i][j] = (int) Math.max(table[i - 1][j],
                            (table[i-1][(int)((j - (objets.get(i).getPoid() )))]
                                    + objets.get(i).getValeur()));
        }



        int i = NB_OBJETS - 1;
        int j = PoidsMaxInt;
        while(table[i][j] == table[i][j-1])
            --j;
        while(j > 0) {
            while (i > 0 && table[i][j] == table[i - 1][j])
                --i;
            j = j - (int) (objets.get(i).getPoid());
            if (j >= 0 && j - objets.get(i).getPoid() >=0 )
                sac.setObjets(objets.get(i));
            --i;
        }
    }
}
