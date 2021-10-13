package Objets;


public class Objet {
    private String nom;
    private float poid;
    private float valeur;
    private boolean estDansLeSac;

    public Objet(String nom , float valeur , float poid){
        this.nom = nom;
        this.poid = poid;
        this.valeur = valeur;
        this.estDansLeSac = false;
    }

    public float getV_p() {
        return valeur/poid;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPoid() {
        return poid;
    }

    public void setPoid(float poid) {
        this.poid = poid;
    }

    public float getValeur() {
        return this.valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }

    public boolean isEstDansLeSac(){
        return estDansLeSac;
    }
    public void isDansLeSac(){
        estDansLeSac = true;
    }

    public String toString(){
        return nom + " | " + poid + " | " + valeur + " | " + estDansLeSac +"\n";
    }
}