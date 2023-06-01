package vue;

import java.util.Scanner;
import static vue.IhmNim.scanner;

public class IhmJeu4 {

    protected static Scanner scanner = new Scanner(System.in);

    public IhmJeu4() {
    }

    public String LireNomJoueur() {
        System.out.print("Entrez votre nom: ");
        String nom = scanner.nextLine();
        return nom;
    }
    
    public boolean LireChoixEntreRotationEtInsertion(String non_joueur) {
        System.out.print("==> " + non_joueur + " à vous de jouer, choisir si vous voulez faire une rotation (0) ou insertion (1): ");
        while (true) {
            String str = scanner.nextLine();
            try {
                int input = Integer.parseInt(str);
                switch(input){
                    case 0:return false;
                    case 1:return true;
                }
            } catch (NumberFormatException nfe) {
            }
            System.out.print("==> " + non_joueur + " choisir l'une des valeurs '0' ou '1': ");
        }
    }
    
    public boolean LireChoixEntreRotationaGaucheEtDroite(String non_joueur) {
        System.out.print("==> " + non_joueur + " à vous de jouer, choisir si vous voulez faire une rotation à droite (0) ou une rotation à droite (1): ");
        while (true) {
            String str = scanner.nextLine();
            try {
                int input = Integer.parseInt(str);
                switch(input){
                    case 0:return false;
                    case 1:return true;
                }
            } catch (NumberFormatException nfe) {
            }
            System.out.print("==> " + non_joueur + " choisir l'une des valeurs '0' ou '1': ");
        }
    }
    

    /*cette fonction lire le numéro de colonne choisir. elle vérifier si l'utilisateur a met une valeur numérique*/
    public int LireNmColone(String non_joueur) {
        System.out.print("==> " + non_joueur + " à vous de jouer, choisir la colonne: ");
        while (true) {
            String str = scanner.nextLine();
            try {
                int input = Integer.parseInt(str);
                return input;
            } catch (NumberFormatException nfe) {
            }
            System.out.print("==> " + non_joueur + " choisir une valeur valide: ");

        }
    }
    
        //lire le nombre de tas
    public boolean LireAutorisationdeRotation() {
        System.out.print("Spécifier vous voulez autoriser l'orientation O/N : ");
        while (true) {
            try {
                 char c = scanner.nextLine().charAt(0);
            switch (c) {
                case 'O':
                case 'o':
                    return true;
                case 'N':
                case 'n':
                    return false;
            }
            } catch (NumberFormatException nfe) {
                System.out.print("==> S.V.P choisir une valeur valide: ");
            }
        }
    }

    public void AfficherPartieTerminee(String vinqueur) {
        if (vinqueur.equals("ex aequo")) {
            System.out.println("la partie est terminée :  ex aequo");
        } else {
            System.out.println("la partie est terminée, le vinqueur c'est :"+  vinqueur);
        }
    }

    public void AfficherJeurTermine(String vinqueur) {
        if (vinqueur.equals("ex aequo")) {
            System.out.println("le jeu est terminé :  ex aequo");
        } else {
            System.out.println("le jeu est terminé, le vinqueur c'est :  "+  vinqueur);
        }
    }

    public void AfficherJeuEstPrête() {
        System.out.println("jeu est prête");
    }

    public boolean DemandeRejouer() {
        while (true) {
            System.out.print("voulez vous rejouer O/N :");
            char c = scanner.nextLine().charAt(0);
            switch (c) {
                case 'O':
                case 'o':
                    return true;
                case 'N':
                case 'n':
                    return false;
            }
        }
    }

    
    public void afficher(int[][] grille, int taille) {

        //afficher la line des numéro des colonnes
        for (int col = 0; col < taille; col++) {
            System.out.print( " " +col );
        }
        System.out.println();
        for (int col = 0; col <= taille; col++) {
            System.out.print("==");
        }
        System.out.println();
        for (int ligne = taille - 1; ligne >= 0; --ligne) {
            System.out.print('|');

            for (int col = 0; col < taille; col++) {
                if (grille[col][ligne] == 0) {
                    System.out.print(' ');
                } else {
                    System.out.print(grille[col][ligne]);
                }
                System.out.print(' ');
            }
            System.out.println('|');
        }

        for (int col = 0; col <= taille; col++) {
            System.out.print("==");
        }
        System.out.println();

    }

}
