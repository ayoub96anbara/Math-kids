package com.gamecodeschool.math;

public class Gamer {
    private int moyenne;
    private int nbEtoiles;
    int niveau;

    public Gamer(int moyenne ,int niveau) {
        this.moyenne = moyenne;
        this.niveau=niveau;
        //this.nbEtoiles = nbEtoiles;
    }

    public int getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(int moyenne) {
        this.moyenne = moyenne;
    }

    public int getNbEtoiles() {
        return nbEtoiles;
    }

    public void setNbEtoiles(int nbEtoiles) {
        this.nbEtoiles = nbEtoiles;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
}
