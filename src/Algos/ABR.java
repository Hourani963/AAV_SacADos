package Algos;

import Objets.Objet;


public class ABR {

    private ABR filsGauche;

    private ABR filsDroit;

    private final ABR parent;

    private final int profondeur;

    private final double valeur;

    private final double poids;
    private int indexObjet;

    public ABR(){
        this.parent = this;
        this.profondeur = 0;
        this.poids = this.valeur = 0.0;
    }


    public ABR(ABR parent, double valeur, double poids, int index){
        this.parent = parent;
        this.profondeur = parent.profondeur + 1;
        this.poids = poids;
        this.valeur = valeur;
        this.indexObjet = index;
    }


    public void setFilsGauche(Objet o, int index) {
        this.filsGauche = new ABR(this, this.valeur + o.getValeur(), this.poids + o.getPoid(), index);
    }


    public void setFilsDroit() {
        this.filsDroit = new ABR(this, this.valeur, this.poids, -1);
    }


    public ABR getFilsGauche() {
        return this.filsGauche;
    }


    public ABR getFilsDroit() {
        return this.filsDroit;
    }


    public double getValeur() {
        return this.valeur;
    }


    public double getPoids() {
        return this.poids;
    }


    public int getIndexObjet() {
        return this.indexObjet;
    }


    public boolean estRacine() {
        return this.profondeur == 0;
    }

    public ABR getParent() {
        return this.parent;
    }
}