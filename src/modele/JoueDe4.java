package modele;


public class JoueDe4 {

    private int[][] grille;
    private final int taille_grille = 7;

    public JoueDe4() {
        this.initiJeu();
    }

    public int getTaille_grille() {
        return taille_grille;
    }

    /*cette fonction intialiser le jeux*/
    public void initiJeu() {
        this.grille = new int[this.taille_grille][this.taille_grille];
        for (int col = 0; col < this.taille_grille; col++) {
            for (int line = 0; line < this.taille_grille; line++) {
                grille[col][line] = 0;
            }
        }
    }

    /*cette fonction ajouter une valeur dans la colonne choisi*/
    public boolean jouer(int col,int val) {
        if ((col < 0) || (col >= this.taille_grille)) {
            return false;
        }

        // Trouve la première place vide dans la colonne
        for (int ligne = 0; ligne < this.taille_grille; ligne++) {
            if (grille[col][ligne] == 0) {
                //ajouter dans la colone choisi la valeur propore au joueur qui est entraine de jouer (1 pour joueur_1 et 2 pour joueur_2)
                grille[col][ligne] = val;

                //retourn True pour dire que l'insertion a été bien fonctionner
                return true;
            }
        }

        // La colonne est pleine
        return false;
    }

    /**
     * Cette méthode vérifie toutes les lignes, colonnes et diagonales pour une
     * série de 4 valeurs identique.
     *
     * Notez qu'il n'est pas nécessaire de retourner le joueur concerné,
     * puisqu'il s'agit celui qui vient de jouer.
     *
     */
    public boolean chercherQuatre() {
        // Vérifie les horizontales ( - )
        for (int ligne = 0; ligne < this.taille_grille; ligne++) {
            if (chercher_4_valeur_aliner(0, ligne, 0)) {
                return true;
            }
        }

        // Vérifie les verticales ( ¦ )
        for (int col = 0; col < this.taille_grille; col++) {
            if (chercher_4_valeur_aliner(0, col, 1)) {
                return true;
            }
        }

        // Diagonales (cherche depuis la ligne du bas)
        for (int col = 0; col < this.taille_grille; col++) {
            // Première diagonale ( / )
            if (chercher_4_valeur_aliner(0, col, 2)) {
                return true;
            }
            // Deuxième diagonale ( \ )
            if (chercher_4_valeur_aliner(0, col, 3)) {
                return true;
            }
        }

        // Diagonales (cherche depuis les colonnes gauches et droites)
        for (int ligne = 0; ligne < this.taille_grille; ligne++) {
            // Première diagonale ( / )
            if (chercher_4_valeur_aliner(0, ligne, 2)) {
                return true;
            }
            // Deuxième diagonale ( \ )
            if (chercher_4_valeur_aliner(this.taille_grille - 1, ligne, 3)) {
                return true;
            }
        }

        // On n'a rien trouvé
        return false;
    }

    /**
     * Cette méthode cherche 4 valeur alignés sur une ligne. Cette ligne est
     * définie par le point de départ, ou origine de coordonnées
     * (Col_debut,line_debut), et par le type de la ligne. En utilisant des
     * valeurs variant entre 0 et 3 pour indiquer quel est le type de la line :
     * horizontal : 0 vertical : 1 1ère diagonale / : 2 2ème diagonale \ : 3
     *
     *
     * @return true si on trouve un alignement
     */
    private boolean chercher_4_valeur_aliner(int Col_debut, int line_debut, int type_recherche) {
        int valeur = 0;
        int compteur = 0;

        int Col_current = Col_debut;
        int line_current = line_debut;

        //ces valeur represente le déplacement, si on veux vérifier horizontalement par exemple alors delta_col = 0 et delta_line=1
        int delta_col = 0, delta_line = 1;
        switch (type_recherche) {
            case 0: //recherche horizontal -
                delta_col = 0;
                delta_line = 1;
                break;
            case 1: //recherche vericale |
                delta_col = 1;
                delta_line = 0;
                break;
            case 2: //recherche 1ère diagonale /
                delta_col = 1;
                delta_line = 1;
                break;
            case 3: //recherche 2ème diagonale \
                delta_col = 1;
                delta_line = -1;
                break;
        }

        while ((Col_current >= 0) && (Col_current < this.taille_grille) && (line_current >= 0) && (line_current < this.taille_grille)) {
            if (grille[line_current][Col_current] != valeur) {
                // Si la valeur change, on réinitialise le compteur et sauvegarde la valeur
                valeur = grille[line_current][Col_current];
                compteur = 1;
            } else {
                // Sinon on l'incrémente
                compteur++;
            }

            // On vérifier si la valeur n'est pas vide et le compteur atteins 4
            if ((valeur != 0) && (compteur == 4)) {
                return true;
            }

            // On passe à l'itération suivante
            Col_current += delta_col;
            line_current += delta_line;
        }

        // Aucun alignement n'a été trouvé
        return false;
    }

    /**
     * Vérifier si la grille est plaine ou non
     */
    public boolean estPlein() {
        // On cherche au moin une valeur vide
        for (int col = 0; col < this.taille_grille; col++) {
            for (int ligne = 0; ligne < this.taille_grille; ligne++) {
                if (grille[col][ligne] == 0) {
                    //la grille n'est pas plaine
                    return false;
                }
            }
        }

        //la grille est  plaine
        return true;
    }

    public int[][] getGrille() {
        return grille;
    }




 

 

    public void setGrille(int[][] grille) {
        this.grille = grille;
    }

    
    /*cette fonction sert à orienter la matrice vers la gauche*/
    public void RotatationVersGauche() {
        for (int x = 0; x < taille_grille / 2; x++) {
            for (int y = x; y < taille_grille - x - 1; y++) {

                //styocker la valeur de coin haut dans une variable temporaire
                int temp = grille[x][y];
                //place la valeur de droite vers le haut
                grille[x][y] = grille[y][taille_grille - 1 - x];
                //placer la valeur de coin  bas vers le de coin droite

                grille[y][taille_grille - 1 - x]
                        = grille[taille_grille - 1 - x][taille_grille - 1 - y];

                //place la valeur de coin gauche vers le coin bas

                grille[taille_grille - 1 - x][taille_grille - 1 - y] = grille[taille_grille - 1 - y][x];

                //place la valeur coin haut dans le coin gauche 
                grille[taille_grille - 1 - y][x] = temp;
            }
        }
        //vers déplacer les pièces vers le bas (effet de gravité)
        ReinstalerGrill();

    }

    public void RotatationVersDroite() {
        for (int x = 0; x < taille_grille / 2; x++) {

            for (int y = x; y < taille_grille - x - 1; y++) {
                //stocker la valeur de coin haut dans une variable temporaire
                int temp = grille[x][y];
                
                //place la valeur coin gauche dans le coin haut 
                grille[x][y] = grille[taille_grille - 1 - y][x];

                //place la valeur coin bas dans le coin gauche 
                grille[taille_grille - 1 - y][x] = grille[taille_grille - 1 - x][taille_grille - 1 - y];

                //place la valeur coin droite dans le coin bas 
                grille[taille_grille - 1 - x][taille_grille - 1 - y] = grille[y][taille_grille - 1 - x];
                
                //place la valeur coin haut dans le coin droite 
                grille[y][taille_grille - 1 - x] = temp;
            }
        }
        
        //vers déplacer les pièces vers le bas (effet de gravité)
        ReinstalerGrill();
    }

    //cette fonction sert à appliquer l'effet de gravité, veut dire traslater les pions vers le bas après chaque rotation
    private void ReinstalerGrill() {
        //parcourir les colone de gauche vers la droite 
        for (int x = 0; x < taille_grille; x++) {
            
            //parcourir chaque colone de bas vers le haut
            for (int y = 0; y < taille_grille; y++) {
                
                //si on trouve une valeur n'est pas null
                if (grille[x][y] != 0) {
                    //on commence à le translater vers le bas ant que la cas suivante est vide
                    for (int z = y; z > 0; z--) {
                        //vérifier que la case suivante est vide
                        if (grille[x][z - 1] == 0) {
                            //sic'est le cas, on va permuter entre eux
                            grille[x][z - 1] = grille[x][z];
                            grille[x][z] = 0;
                        }else{
                            //si non on a recontré un obstacle, donc on quitte
                            break;
                        }
                    }
                }
            }
        }
    }

}
