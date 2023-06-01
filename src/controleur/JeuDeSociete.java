package controleur;

import java.util.Scanner;
import modele.Joueur;

public abstract class JeuDeSociete {

    protected Joueur[] joueur;
    protected int tour_joueur;
    protected boolean appliquer_contrainte;

    public JeuDeSociete() {
        this.joueur = new Joueur[2];
        for (int i = 0; i < 2; i++) {
            this.joueur[i] = new Joueur();
        }
        this.appliquer_contrainte = false;
    }

    //le jeu est pret seulement si les deux joueurs sont ajoutés leurs noms
    protected boolean JeuEstPret() {
        return !(this.joueur[0].getNom_joueur().isEmpty() || this.joueur[1].getNom_joueur().isEmpty());
    }

    //ajouter le nom de joueur
    protected void AjouterJoueur(String nom) {
        //vérifier si le premier n'a pas du nom
        if (this.joueur[0].getNom_joueur().isEmpty()) {
            //on lui affecte le nom saisi
            this.joueur[0].setNom_joueur(nom);
            //si c'est on refet la même chose pour le deuxième
        } else if (this.joueur[1].getNom_joueur().isEmpty()) {
            this.joueur[1].setNom_joueur(nom);
        }
    }

    protected void PasserTour() {
        //passer le rôle vers le joueurs suivant
        this.tour_joueur += 1;
        this.tour_joueur %= 2;
    }

    protected String AvoirNomJoueuVinqueur() {
        if (this.joueur[0].getScore() > this.joueur[1].getScore()) {
            return this.joueur[0].getNom_joueur();
        }
        if (this.joueur[0].getScore() < this.joueur[1].getScore()) {
            return this.joueur[1].getNom_joueur();
        }
        return "ex aequo";
    }

    abstract void initialiserParamètreDuJeu();

    abstract void initialiser();

    abstract void initialiserContrainteDuJeu();

    abstract void MiseAjourContrainteDuJeu();

    abstract void AfficherJeuEstPrête();

    abstract void AfficherJeu();

    abstract void ExecuterUneEtape();

    abstract void AfficherPartieTerminee(String nom);

    abstract boolean Rejoueur();

    abstract void AfficherPartieTerminer();

    abstract boolean Egalite();

    abstract boolean Il_y_gagnat();

    public final void CommencerLeJeu() {
        initialiserParamètreDuJeu();
        initialiserContrainteDuJeu();

        //afficher que le jeu est prête
        AfficherJeuEstPrête();
        //intialaliser le jeux
        initialiser();
        //cette varible signifie que le jeu est encore
        boolean continuer = true;
        //rendre le tour pour le premier joueur
        this.tour_joueur = 0;
        //afficher le jeu
        AfficherJeu();

        while (continuer) {

            ExecuterUneEtape();
            AfficherJeu();

            boolean partie_terminer = false;

            //vérifier si la partie est terminer
            if (Il_y_gagnat()) {
                //afficher que la partie est finie
                AfficherPartieTerminee(this.joueur[tour_joueur].getNom_joueur());
                //calculer le score 
                this.joueur[tour_joueur].GagnerPartie();
                //mettre la varibel "partie_terminer" en true
                partie_terminer = true;
            } else if (Egalite()) {
                AfficherPartieTerminee("ex aequo");
                //mettre la varibel "partie_terminer" en true
                partie_terminer = true;
            }

            //demander s'il il veut une notre partie
            if (partie_terminer) {
                if (Rejoueur()) {
                    //initialiser le jeu
                    initialiser();

                    //inveser le mode de jeu pour enchainer une nouvelle partie avec des conditions différentes
                    appliquer_contrainte = !appliquer_contrainte;
                    MiseAjourContrainteDuJeu();
                } else {
                    continuer = false;
                }
            } else {
                //sinon passer le tour 
                this.PasserTour();
            }

        }

        AfficherPartieTerminer();
    }

    public static void main(String[] args) {
        JeuDeSociete jeu = null;
        Scanner scanner = new Scanner(System.in);

        System.out.print("choisir si vous voulez jouer  un jeu de quatre (0) ou un jeu de NIM (1): ");
        boolean faire_choix = false;
        while (!faire_choix) {
            String str = scanner.nextLine();
            try {
                int input = Integer.parseInt(str);
                switch (input) {
                    case 0:
                        jeu = new ControllerJeu4();
                        System.out.println("jeu de quatre a été choisi ");
                        faire_choix = true;
                        break;
                    case 1:
                        jeu = new ControllerNim();
                        System.out.println("jeu de NIM a été choisi ");
                        faire_choix = true;
                        break;
                }
            } catch (NumberFormatException nfe) {
            }
            System.out.print("choisir l'une des valeurs '0' ou '1': ");
        }

        jeu.CommencerLeJeu();
    }

}
