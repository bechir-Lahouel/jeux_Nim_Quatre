package controleur;

import vue.IhmJeu4;
import modele.JoueDe4;

public class ControllerJeu4 extends JeuDeSociete {

    private JoueDe4 jeu;
    private IhmJeu4 IHM;
    private int[] Nombre_rotation_autorises;

    public ControllerJeu4() {
        this.jeu = new JoueDe4();
        this.IHM = new IhmJeu4();
        this.Nombre_rotation_autorises = new int[2];

    }

    @Override
    void initialiserParamètreDuJeu() {
        //saisir les noms des joueurs
        while (!this.JeuEstPret()) {
            String nom = this.IHM.LireNomJoueur();
            this.AjouterJoueur(nom);
        }
    }

    @Override
    void initialiser() {
        this.jeu.initiJeu();
        //puisque chaque joueur a le droit de 4 retation
        this.Nombre_rotation_autorises[0] = 4;
        this.Nombre_rotation_autorises[1] = 4;
    }

    @Override
    void initialiserContrainteDuJeu() {
        //Demander le nombre des allumettes maxi
        appliquer_contrainte = this.IHM.LireAutorisationdeRotation();
    }

    @Override
    void MiseAjourContrainteDuJeu() {
        if (appliquer_contrainte) {
            this.Nombre_rotation_autorises[0] = 4;
            this.Nombre_rotation_autorises[1] = 4;
        }
    }

    @Override
    void AfficherJeuEstPrête() {
        //afficher que le jeu est prête
        this.IHM.AfficherJeuEstPrête();
    }

    @Override
    void AfficherJeu() {
        this.IHM.afficher(this.jeu.getGrille(), this.jeu.getTaille_grille());
    }

    @Override
    void ExecuterUneEtape() {
        //vérifier si la contraine est autorisée et le joueur a le droite pour le faire
        if (appliquer_contrainte && this.Nombre_rotation_autorises[tour_joueur] > 0) {
            boolean reponce = this.IHM.LireChoixEntreRotationEtInsertion(this.joueur[tour_joueur].getNom_joueur());
            //si le joueur a choix de faire une rotation, il faut spécifier la direction
            if (!reponce) {
                reponce = this.IHM.LireChoixEntreRotationaGaucheEtDroite(this.joueur[tour_joueur].getNom_joueur());
                if (!reponce) {
                    this.jeu.RotatationVersDroite();
                } else {
                    this.jeu.RotatationVersGauche();
                }
                //décrimenté le 
                this.Nombre_rotation_autorises[tour_joueur]--;
            } else {
                int nm_col = this.IHM.LireNmColone(this.joueur[tour_joueur].getNom_joueur());
                //vérifier si le numéro est valide ou la colonne n'est pas pleine
                while (!this.jeu.jouer(nm_col, tour_joueur + 1)) {
                    nm_col = this.IHM.LireNmColone(this.joueur[tour_joueur].getNom_joueur());
                }
            }

        } else {
            int nm_col = this.IHM.LireNmColone(this.joueur[tour_joueur].getNom_joueur());
            //vérifier si le numéro est valide ou la colonne n'est pas pleine
            while (!this.jeu.jouer(nm_col, tour_joueur + 1)) {
                nm_col = this.IHM.LireNmColone(this.joueur[tour_joueur].getNom_joueur());
            }
        }
    }

    @Override
    void AfficherPartieTerminee(String nom) {
        this.IHM.AfficherJeurTermine(this.joueur[tour_joueur].getNom_joueur());

    }

    @Override
    boolean Rejoueur() {
        return this.IHM.DemandeRejouer();
    }

    @Override
    void AfficherPartieTerminer() {
        this.IHM.AfficherJeurTermine(this.AvoirNomJoueuVinqueur());
    }

    @Override
    boolean Egalite() {
        return this.jeu.estPlein();
    }

    @Override
    boolean Il_y_gagnat() {
        return this.jeu.chercherQuatre();
    }
}
