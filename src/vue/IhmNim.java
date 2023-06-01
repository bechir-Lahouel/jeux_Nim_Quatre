package vue;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IhmNim {

    protected static Scanner scanner = new Scanner(System.in);

    public IhmNim() {
    }

    public String LireNomJoueur() {
        System.out.print("Entrez votre nom: ");
        String nom = scanner.nextLine();
        return nom;
    }

    //lire le nombre de tas
    public int LireNombreTas() {
        System.out.print("Spécifier le nombre des TAS que vous souhautes: ");
        while (true) {
            String str = scanner.nextLine();
            try {
                int nb_tas = Integer.parseInt(str);
                return nb_tas;
            } catch (NumberFormatException nfe) {
                System.out.print("==> S.V.P choisir une valeur valide: ");
            }
        }
    }

    //lire le nombre de tas
    public int LireNombreAlluemetteMaximale() {
        System.out.print("Spécifier le nombre maximale des allumettes : ");
        while (true) {
            String str = scanner.nextLine();
            try {
                int nb_tas = Integer.parseInt(str);
                return nb_tas;
            } catch (NumberFormatException nfe) {
                System.out.print("==> S.V.P choisir une valeur valide: ");
            }
        }
    }

    /*cette fonction lire le nombre de tas ainsi que */
    public  List<Integer> LireNumeroTas_NombreAllumette(String non_joueur) {
        int numero_tas, nombre_allumette;

        //lire le numéro de tas
        System.out.print("==> " + non_joueur + " à vous de jouer, choisir le numéro de Tas: ");
        while (true) {
            String str = scanner.nextLine();
            try {
                numero_tas = Integer.parseInt(str);
                break;
            } catch (NumberFormatException nfe) {
                System.out.print("==> " + non_joueur + " choisir une valeur valide: ");
            }
        }

        //lire le nombre d'allumette
        System.out.print("==> " + non_joueur + " : choisir le nombre d'allumette : ");
        while (true) {
            String str = scanner.nextLine();
            try {
                nombre_allumette = Integer.parseInt(str);
                break;
            } catch (NumberFormatException nfe) {
                System.out.print("==> " + non_joueur + " choisir une valeur valide: ");
            }
        }
        List<Integer> places = Arrays.asList(numero_tas,nombre_allumette);
        return places;
    }

    public void AfficherPartieTerminee(String vinqueur) {
        System.out.println("la partie est terminée, le vinqueur c'est :" + vinqueur);
    }

    public void AfficherJeurTermine(String vinqueur) {
        if (vinqueur.equals("ex aequo")) {
            System.out.println("le jeu est terminé :  ex aequo");
        } else {
            System.out.println("le jeu est terminé, le vinqueur c'est :  " + vinqueur);
        }
    }

    public void AfficherJeuEstPrête() {
        System.out.println("jeu est prête");
    }

    public void AfficherErreur(String s) {
        System.err.println(s);
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

    public void afficher(int[] lesTas, int taille, int nombre_maximale_allumettes) {
        if (nombre_maximale_allumettes > 0) {
            System.out.println("Nombre maximale des allumettes égale à " + nombre_maximale_allumettes);
        }

        //afficher la line des numéro des colonnes
        for (int i = 0; i < taille; i++) {
            System.out.print((i + 1) + ")");
            for (int j = 0; j < taille - i; j++) {
                System.out.print("  ");
            }
            for (int k = 0; k < lesTas[i]; k++) {
                System.out.print("| ");
            }
            System.out.println("");
        }
    }

}
