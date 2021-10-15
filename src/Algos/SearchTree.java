package Algos;

import Objets.Objet;

import java.util.Objects;

public class SearchTree {

    private SearchTree leftSon;

    private SearchTree rightSon;

    private SearchTree father;

    private float valeur;
    private float poids;

    private int hauteur;
    private int index;

    public SearchTree(){ // initialiser le raçine
        this.father = this;
        this.valeur = 0;
        this.poids = 0;
        this.hauteur = 0;
    }
    public SearchTree(SearchTree father, float valeur, float poids, int index){
        this.father = father;
        this.valeur = valeur;
        this.index = index;
        this.poids = poids;
       this.hauteur = father.hauteur + 1 ;
    }

    public void setLeftSon(Objet o , int index){
        this.leftSon = new SearchTree(this , o.getValeur(), o.getPoid() , index);
    }
    public void setRightSon(){
        this.rightSon = new SearchTree(this , this.valeur , this.poids , -1);
    }

    public SearchTree getLeftSon(){
        return this.leftSon;
    }
    public SearchTree getRightSon(){
        return this.rightSon;
    }
    public SearchTree getFather(){
        return this.father;
    }

    public float getValeur(){
        return this.valeur;
    }
    public float getPoids(){
        return this.poids;
    }
    public boolean isRoot(){
        return this.hauteur==0;
    }
    public int getIndexObjet() {
        return this.index;
    }

}
