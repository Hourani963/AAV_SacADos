package Objets;


import Algos.*;
import File.FileOperations;
import Objets.Objet;

import java.util.ArrayList;
import java.util.*;

public class SacADos {

    private float poidsSacMax;
    private float Vsac; // valeut total du sac après le remplissage
    private float Psac;
    private String chemin;
    private ArrayList<Objet> objetsTous;
    private ArrayList<Objet> objetsDansSac;

    /**
     * Constructiur du sac vide
     */
    public SacADos(){ // constructeur sac vide
        this.chemin = "";
        this.poidsSacMax = 0;
        this.objetsTous = new ArrayList<>();
        this.objetsDansSac = new ArrayList<>();
        this.Psac = 0;
        this.Vsac = 0;
    }

    /**
     * Constructeur du sac
     * @param chemin : chemin du fichier du text
     * @param poidsSacMax : poid maximim du sac
     */
    public SacADos(String chemin ,float poidsSacMax){
        this.chemin = chemin;
        this.poidsSacMax = poidsSacMax;
        this.objetsTous = new ArrayList<>();
        this.objetsDansSac = new ArrayList<>();
        this.Psac = 0;
        this.Vsac = 0;
    }

    public void setPoid(float poid){
        this.Psac = poid;
    }
    public void setPoidsSacMax(float p){
        this.poidsSacMax = p;
    }
    /**
     * Obtenir le nombre de tous les objet dans le fichier txt
     * @return nombre des objets
     */
    public int getObjetsTous(){
        return objetsTous.size();
    }

    /**
     * Obtenir l'objet da l'indice donnée dans le arraylist objetTous
     * @param indice : indice de l'objet
     * @return valeur de l'objet
     */
    public double getObjetValByIndice(int indice){
        return objetsDansSac.get(indice).getValeur();
    }

    /**
     * Obtenir la somme des valeur de tous les objets pour la borneSupérieur dans la PSE
     * @return VmaxPSE
     */
    public float getSommeValeurTousObjets(){
        float VmaxPSE = 0;
        for (Objet o: objetsTous) {
            VmaxPSE += o.getValeur();
        }
        return VmaxPSE;
    }

    /**
     * On peut utiliser cette fonction dans l'algo PSE pour fixé une borne Inférieur avant de résoudre PSE
     * @return Vsac : représent la borne inférieur (somme des valeur des objet dans le sac) résolut par Gloutonne
     */
    public float getSommeValeurGlotonne(){ // borne inférieur pour l'algo PSE
        this.resoudreGloutonne();
        return Vsac;
    }

    /**
     * obtenir le nombre des objets dans le sac
     * @return nombre d'objet
     */
    public int getNbrObjetSac(){
        return objetsDansSac.size();
    }

    /**
     * Vider le sac
     */
    public void viderSac(){
        this.Psac = 0 ;
        this.Vsac = 0;
        this.objetsDansSac.clear();
    }


    /**
     * obtenir le poid maximum de sac " la valeur qu'on a choisi au début de program
     * @return poids maximum du sac qu'on a fixé au début de program
     */
    public float getPoidsSacMax(){
        return poidsSacMax;
    }

    /**
     * obtenir la valeur de sac actuelle après l'ajout des objet
     * @return Vsac : la valeur
     */
    public float getVsac() {
        return Vsac;
    }


    /**
     * obtenir le poid de sac actuelle après l'ajout des objets
     * @return Psac : le poid
     */
    public float getPsac() {
        return Psac;
    }


    /**
    * ajouter l'objet dans le sac
    * @param objet : c'est l'objet qu'on va ajouter dans le sac
     */
    public void setObjets(Objet objet){
        objetsDansSac.add(objet);
        objet.isDansLeSac();
        Vsac += objet.getValeur();
        Psac += objet.getPoid();

    }

    /**
    * ajouter l'objet dans la list objetTous, ce n'est pas ajouter l'objet dans le sac
    * @param objet : c'est l'objet qu'on va ajouter dans la list objetTous
     */
    public void addObjet(Objet objet){ // ajouter l'objet à la liste d'objet et non pas dans le sac
        this.objetsTous.add(objet);
    }

    /**
     * Afficher la liste d'objet contenant dans le sac
     * @return s : String contenant les objets
     */
    public String toString(){
        String s = "";
        for (Objet objet:objetsDansSac) {
            s += objet.toString();
        }
        return s;
    }

    /**
     * Afficher tous les objets dans la liste objetTous, ce ne sont pas les objets dans le sac
     * @return s : string contenant la list d'objet
     */
    public String toStringAllObjets(){
        String s = "";
        for (Objet objet: objetsTous) {
            s += objet.toString();
        }
        return s;
    }

    /**
     * Ajouter la liste d'items (nom, poid et valeur) dans un ARRAYLIST [objetsTous]
     */
    public void analyseFile(){
        FileOperations file = new FileOperations(chemin);
        List list = file.readFile(chemin);
        Iterator<String> itr = list.iterator();
        while (itr.hasNext()){
            String ligne = itr.next();
            String[] parts = ligne.split(";");
            this.addObjet(new Objet(parts[0],Float.parseFloat(parts[2]),Float.parseFloat(parts[1])));

        }
    }

    /**
     * Résoudre le problème en utilisant la méthode Gloutonne
     */
    public void resoudreGloutonne(){ // résoudre le problème de remplissage
        Gloutonne glo = new Gloutonne(objetsTous, this);
        glo.resoudre();
    }

    /**
     * Résoudre le problème en utilisant la méthode ProgrammationDynamique
     */
    public void resoudreProgDynam(){
        ProgDyn progDyn = new ProgDyn(this,objetsTous);
        progDyn.resoudre();
    }

    /**
     * Résoudre le problème en utilisant la méthode PSE
     */
    public void resoudrePSE(){
        PSEOptimise p = new PSEOptimise(this, objetsTous);
        p.resoudre();
    }
}