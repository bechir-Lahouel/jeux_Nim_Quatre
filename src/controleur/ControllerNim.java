package controleur;

import java.util.List;
import modele.CoupInvalideException;
import modele.CoupNim;
import modele.Joueur;
import modele.Tas;
import vue.IhmNim;

public class ControllerNim extends JeuDeSociete {

    private Tas jeu;
    private IhmNim IHM;
    private int nb_max_allumette;

    public ControllerNim() {
        this.IHM = new IhmNim();
        this.joueur = new Joueur[2];
        for (int i = 0; i < 2; i++) {
            this.joueur[i] = new Joueur();
        }
    }

  
    @Override
    void initialiserParamètreDuJeu() {
        //lire le nombre de tas
        int nb_tas = IHM.LireNombreTas();
        this.jeu = new Tas(nb_tas);

        //saisir les noms des joueurs
        while (!this.JeuEstPret()) {
            String nom = this.IHM.LireNomJoueur();
            this.AjouterJoueur(nom);
        }
    }

    @Override
    void initialiser() {
        this.jeu.initialiser();
    }

    @Override
    void initialiserContrainteDuJeu() {
        //Demander le nombre des allumettes maxi
        nb_max_allumette = this.IHM.LireNombreAlluemetteMaximale();

        if (nb_max_allumette > 0) {
            appliquer_contrainte = true;
            this.jeu.setNb_max(nb_max_allumette);
        }
    }

    @Override
    void MiseAjourContrainteDuJeu() {
        if (appliquer_contrainte) {
            this.jeu.setNb_max(nb_max_allumette);
        } else {
            this.jeu.setNb_max(0);
        }
    }

    @Override
    void AfficherJeuEstPrête() {
         //afficher que le jeu est prête
        this.IHM.AfficherJeuEstPrête();
    }

    @Override
    void AfficherJeu() {
        this.IHM.afficher(this.jeu.getLesTas(), this.jeu.getNb_tas(), this.jeu.getNb_max());
   }

    @Override
    void ExecuterUneEtape() {
                   boolean coup_valide = false;
            while (!coup_valide) {
                //lire le numero de tas aisni que le nombre d'allumettes
                List<Integer>  tas_lumette = this.IHM.LireNumeroTas_NombreAllumette(this.joueur[tour_joueur].getNom_joueur());

                //creer un coup
                CoupNim coup = new CoupNim(tas_lumette.get(0), tas_lumette.get(1));

                try {
                    this.jeu.gererCoup(coup);
                    coup_valide = true;
                } catch (CoupInvalideException e) {
                    this.IHM.AfficherErreur(e.getMessage());
                }
            }
    }



    @Override
    void AfficherPartieTerminee(String nom) {
                       this.IHM.AfficherPartieTerminee(this.joueur[tour_joueur].getNom_joueur());

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
         //vérifier si la partie est terminée et s'il n y a pas un gagant
     //dans cette classe on retourne toujours false, parceque ce jour n'a un état d'égalité
        return false;
    }

    @Override
    boolean Il_y_gagnat() {
        //vérifier si la partie est terminée et s'il y a un gagant
        return this.jeu.partieTerminee();
    }
}
