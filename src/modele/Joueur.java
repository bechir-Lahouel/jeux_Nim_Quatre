package modele;

public class Joueur {
    private String nom_joueur;
    private int score;

    public Joueur() {
        this.nom_joueur = "";
        this.score = 0;
    }
    
    public Joueur(String nom_joueur) {
        this.nom_joueur = nom_joueur;
        this.score = 0;
    }

    public String getNom_joueur() {
        return nom_joueur;
    }

    public void setNom_joueur(String nom_joueur) {
        this.nom_joueur = nom_joueur;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void GagnerPartie(){
        this.score++;
    }
    

}
