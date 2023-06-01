
package Testing;

import vue.IhmJeu4;
import modele.JoueDe4;


public class Teste {

    public static void main(String[] args) {
        JoueDe4 jeu = new JoueDe4();
        IhmJeu4 view = new IhmJeu4();
            System.out.println("orientation vers droite");

        int[][] grille_5 = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 2, 2, 0, 0},
            {2, 1, 0, 0, 0, 0, 0},
            {2, 2, 1, 0, 0, 0, 0},
            {2, 2, 2, 1, 0, 0, 0}};
        view.afficher(grille_5, 7);
        jeu.setGrille(grille_5);
        jeu.RotatationVersDroite();
        view.afficher(grille_5, 7);

        

        System.out.println("orientation vers gauche");

        int[][] grille_6 = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {2, 2, 2, 1, 0, 0, 0},
            {2, 1, 0, 0, 0, 0, 0},
            {2, 2, 1, 0, 0, 0, 0},
            {1, 1, 1, 2, 2, 0, 0}
        };
        view.afficher(grille_6, 7);
        jeu.setGrille(grille_6);
        jeu.RotatationVersGauche();
        
        view.afficher(grille_6, 7);
        


    }
}
