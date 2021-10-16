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

    public int getObjetsTous(){
        return objetsTous.size();
    }
    public double getObjetValByIndice(int indice){
        return objetsDansSac.get(indice).getValeur();
    }
    public SacADos(){ // constructeur vide
        this.chemin = "";
        this.poidsSacMax = 0;
        this.objetsTous = new ArrayList<>();
        this.objetsDansSac = new ArrayList<>();
        this.Psac = 0;
        this.Vsac = 0;
    }
    public float getSommeValeurTousObjets(){
        float VmaxPSE = 0;
        for (Objet o: objetsTous) {
            VmaxPSE += o.getValeur();
        }
        return VmaxPSE;
    }
    public float getSommeValeurGlotonne(){ // borne inférieur pour l'algo PSE
        this.resoudreGloutonne();
        return Vsac;
    }
    public int getNbrObjetSac(){
        return objetsDansSac.size();
    }
    public void viderSac(){
        this.Psac = 0 ;
        this.Vsac = 0;
        this.objetsDansSac.clear();
    }
    public SacADos(String chemin ,float poidsSacMax){ // constructeur où chemin est l'adresse du fichier contenant les Objets
        this.chemin = chemin;
        this.poidsSacMax = poidsSacMax;
        this.objetsTous = new ArrayList<>();
        this.objetsDansSac = new ArrayList<>();
        this.Psac = 0;
        this.Vsac = 0;
    }
    public void setChemin(String chemin){
        this.chemin = chemin;
    }

    public float getPoidsSacMax(){
        return poidsSacMax;
    }
    public void setPoidsSacMax(float poidsSacMax){
        this.poidsSacMax = poidsSacMax;
    }

    public float getVsac() {
        return Vsac;
    }

    public void setVsac(float vsac) {
        Vsac = vsac;
    }

    public float getPsac() {
        return Psac;
    }

    public void setPsac(float Psac) {
        Psac = Psac;
    }

    public void setObjets(Objet objet){ // dernière étape
        objetsDansSac.add(objet);
        objet.isDansLeSac();
        Vsac += objet.getValeur();
        Psac += objet.getPoid();

    }
    public void addObjet(Objet objet){ // ajouter l'objet à la liste d'objet et non pas dans le sac
        this.objetsTous.add(objet);
    }
    public Objet getObjet(Objet objet){
        return objet;
    }
    public String toString(){
        String s = "";
        for (Objet objet:objetsDansSac) {
            s += objet.toString();
        }
        return s;
    }
    public String toStringAllObjets(){
        String s = "";
        for (Objet objet: objetsTous) {
            s += objet.toString();
        }
        return s;
    }

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

    public void resoudreGloutonne(){ // résoudre le problème de remplissage
        Gloutonne glo = new Gloutonne(objetsTous, this);
        objetsTous.sort(new objetsSorter());
        glo.selection();
    }

    public void resoudreProgDynam(){
        ProgDyn progDyn = new ProgDyn(this,objetsTous);
        progDyn.resoudre();
    }

    public void resoudrePSE(){
        PSEOptimise p = new PSEOptimise(this, objetsTous);
        p.resoudre();
    }
}