package modele;

/**
 * <p>
 * La classe Tas représente l'état courant de la partie Elle est caractérisée
 * par un tableau. Chaque case du tableau enregistre le nombre d'allumettes de
 * chaque tas. Le nombre d'allumettes du premier tas étant dans la case 0.
 * </p>
 *
 * @author Kahlem
 */
public class Tas {

    /**
     * représente l'état courant de la partie
     */
    private int[] lesTas;
    private int nb_max;
    private int nb_tas;

    /**
     * crée un ensemble de nbTas tas avec 0 allumettes dans chaque tas
     *
     * @param nbTas le nombre de tas de la partie
     */
    public Tas(int nbTas) {
        lesTas = new int[nbTas];
        nb_max = 0;
        nb_tas = nbTas;
    }

    /**
     * ajoute les allumettes dans chacun des tas de la manière suivante : dans
     * le ième tas, on place 2*i - 1 allumettes.
     */
    public void initialiser() {
        for (int i = 0; i < lesTas.length; i++) {
            lesTas[i] = 2 * i + 1;
        }
    }

    /**
     * Retourne vrai si la partie est terminée et faux sinon
     *
     * @return
     */
    public boolean partieTerminee() {
        return nbAllumette() == 0;
    }

    /**
     * Nombre d'allumettes par tas
     */
    public int nbAllumettes(int numeroTas) {
        return lesTas[numeroTas - 1];
    }

    /**
     * Retourne le nombre total d'allumettes de la partie
     */
    public int nbAllumette() {

        int total = 0;
        for (int nb : lesTas) {
            total += nb;
        }
        return total;
    }

    /**
     * retourne l'état de la partie sous forme d'une chaîne de caractères
     * constituées des batons correspondant au nombre d'allumettes pour chaque
     * tas.
     *
     */
    @Override
    public String toString() {
        String s = "";
        for (int nbAllumettes : lesTas) {
            for (int i = 1; i <= nbAllumettes; i++) {
                s += "| ";
            }
            s += "\n";
        }
        return s;
    }

    /**
     * modifie l'état de la partie en fonction du coup passé en paramètre
     *
     * @param coup
     * @throws CoupInvalideException si le coup est invalide
     */
    public void gererCoup(CoupNim coup) throws CoupInvalideException {
        int numeroTas = coup.getNumeroTas();
        int nb = coup.getNbAllumettes();
        //vérifier si le nombre de allumette est inférieur à nb_max
        if (this.nb_max != 0 && nb > this.nb_max) {
            throw new CoupInvalideException("Le nombre d'allumette choisi est supèrieur à nombre maximale");
        }

        if (numeroTas >= 1 && numeroTas <= lesTas.length && nb >= 1 && nb <= nbAllumettes(numeroTas)) {
            lesTas[numeroTas - 1] -= nb;

        } else {
            throw new CoupInvalideException("Le coup est invalide, rejouez");
        }
    }

    public int[] getLesTas() {
        return lesTas;
    }

    public int getNb_max() {
        return nb_max;
    }

    public void setNb_max(int nb_max) {
        this.nb_max = nb_max;
    }

    public int getNb_tas() {
        return nb_tas;
    }

    public void setNb_tas(int nb_tas) {
        this.nb_tas = nb_tas;
    }

}
