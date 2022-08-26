
/**
 * Programme qui contient le code pour le jeu PFB, ces statistiques
 * et l'effacage du tableau des statistiques.
 *
 * @author Renzo Arturo Salcedo
 * Code permanent: SALR02089408
 * Courriel: salcedo_salcedo.renzo@courrier.uqam.ca
 * Cours: INF1120-21
 * @version 25/04/2021
 */

public class JouerPFB {
    public static final String MSG_ENTETE = "Ce logiciel permet de jouer à " +
            "PICO FERMI BAGELS.\n";
    public static final String MSG_MENU = "\n----\nMENU\n----\n1. JOUER A PICO " +
            "FERMI BAGELS\n2. AFFICHER STATISTIQUES\n3. SUPPRIMER PARTIES ABANDONNEES" +
            "\n4. QUITTER\n\n";
    public static final String MSG_SAISIE = ("Entrez votre choix: ");
    public static final String MSG_ERR = ("\nERREUR, entrez un chiffre entre 1"
            + " et 4...Recommencez!");
    public static final char NUM_MAX = '4';
    public static final char NUM_MIN = '1';
    public static final int MAX_INT = 20;
    public static final int MIN_INT = 3;
    public static final String MSG_PFB = ("\n*********************" +
            "\n* PICO FERMI BAGELS *" + "\n*********************\n\n");
    public static final String MSG_REJOUER = ("Voulez-vous jouer encore (oui " +
            "ou non): ");
    public static final String MSG_ERRESSAIS = ("\nERREUR, vous devez entrez " +
            "un nombre entre 3 et 20... Recommencez !");
    public static final String MSG_ESSAIS = ("Entrez le nombre d'essais " +
            "maximum (3 - 20) : ");
    public static final String MSG_PERTE = ("\nZUT ! Vous avez perdu la partie " +
            "!");
    public static final String MSG_MOTIF = ("\nLe motif cache etait : ");
    public static final String MSG_ABANDON = ("\n=> Partie abandonnee <=\n\n");
    public static final String MSG_ERR_REJOUER = ("\nERREUR, vous devez " +
            "repondre par oui ou par non... Recommencez !");
    public static final String MSG_OUI = "Oui";
    public static final String MSG_NON = "Non";
    public static final String MSG_STATS = ("\n****************" +
            "\n* STATISTIQUES *" + "\n****************\n\n");
    public static final String MSG_hey = ("\nID JEU | NBR ESSAIS / MAX | SCORE"
            + " (%)\n-----------------------------------\n");
    public static final String MSG_RETOUR = ("Appuyez sur ENTREE pour revenir" +
            " au menu...");
    public static final String MSG_VIDE = ("AUCUNE STATISTIQUE DISPONIBLE\n");
    public static final String MSG_MOYEN = ("SCORE MOYEN : ");
    public static final String MSG_ERR_JEUX = ("\nERREUR, le motif doit etre" +
            " compose de 3 chiffres distincts entre 1 et 9... Recommencez! : ");

    /**
     * Affiche le nbr de parties abandonnees.
     *
     * @param nbrParties le nbr de parties dans le tableau actuel.
     * @param nbrAbandon le nbr de parties abandonnees.
     */
    public static void partiesEffacees(int nbrParties, int nbrAbandon) {
        if (nbrParties == 0 || nbrAbandon == 0) {
            System.out.println("\nAucune partie a effacer !");
        } else if (nbrAbandon == 1) {
            System.out.println("\n1 partie abandonnée a ete supprimee.");
        } else {
            System.out.println("\n" + nbrAbandon + " parties abandonnées" +
                    " ont ete supprimees.");
        }
    }

    /**
     * Cree un nouvau tableau sans les partie abandonnees.
     *
     * @param partie tableau qui contient le nbr de parties jouées de type
     *               PicoFermiBagels
     * @return un nouveau tableau.
     */
    public static PFB_class[] effacage(PFB_class[] partie) {
        int compteur = 0;
        int positions = 0;
        int nbrParties = 0;
        int abandons;
        PFB_class[] partieNouvelle = new PFB_class[2];
        for (int i = 0; i < partie.length; i++) {
            if (partie[i] != null && !partie[i].isPartieAbandonnee()) {
                //compte le nbr de parties qui ne sont pas nulles.
                compteur++;
            }
            if (partie[i] != null) {
                nbrParties++;
            }
        }
        if (partie[0] != null) {
            partieNouvelle = new PFB_class[nbrParties];
            for (int i = 0; i < partie.length; i++) {
                if (partie[i] != null && !partie[i].isPartieAbandonnee()) {
                    partieNouvelle[positions] = partie[i];
                    partieNouvelle[positions].setIdJeu(positions + 1);
                    positions++;
                }
            }
        }
        abandons = nbrParties - compteur;
        partiesEffacees(nbrParties, abandons);
        PFB_class.appelSequenceId(compteur);
        return partieNouvelle;
    }

    /**
     * Calcule la moyenne des elements valides du tableau partie de type
     * PicoFermiBagels
     *
     * @param partie tableau qui contient le nbr de parties jouées de type
     *               PicoFermiBagels
     */
    public static void calculMoyenne(PFB_class[] partie) {
        double moyenne = 0;
        double somme = 0;
        double compteur = 0;
        for (int i = 0; i < partie.length; i++) {
            //ne tient pas compte des parties nulles dans la moyenne
            if (partie[i] != null && !partie[i].isPartieAbandonnee()) {
                somme += partie[i].getScore();
                compteur++;
            }
            moyenne = somme / compteur;
        }
        System.out.printf("%s%.2f %%\n\n", MSG_MOYEN, moyenne);
    }

    /**
     * Affiche les statistiques de chaque element de type PicoFermiBagels du
     * tableau partie
     *
     * @param partie tableau qui contient le nbr de parties jouées de type
     *               PicoFermiBagels
     */
    public static void statistiques(PFB_class[] partie) {
        affichage(MSG_STATS);
        String corps = "";
        String tableau = "\nID JEU | NBR ESSAIS / MAX | SCORE (%)\n" +
                "-------------------------------------\n";
        if (partie[0] != null) {
            for (int i = 0; i < partie.length; i++) {
                //cree les Strings des parties qui ne sont pas abandonnees
                if (partie[i] != null && !partie[i].isPartieAbandonnee()) {
                    corps += String.format("%-7d|%2d/%-15d| %9.2f\n",
                            partie[i].getIdJeu(), partie[i].getNbrEssais(),
                            partie[i].getNbrEssaisMax(), partie[i].getScore());
                } else if (partie[i] != null) {
                    //cree les Strings des parties qui abandonnees
                    corps += String.format("%-7d|%2d/%-15d| %9s\n",
                            partie[i].getIdJeu(), partie[i].getNbrEssais(),
                            partie[i].getNbrEssaisMax(), "ABANDON");
                }
            }
            System.out.println(tableau + corps);
            calculMoyenne(partie);
            System.out.print(MSG_RETOUR);
        } else {
            System.out.println(MSG_VIDE);
            System.out.print(MSG_RETOUR);
        }
        Clavier.lireFinLigne();
    }

    /**
     * Augmente le tableau de deux elements.
     *
     * @param partie tableau qui contient le nbr de parties jouées de type
     *               PicoFermiBagels
     * @param jeuX   partie du jeu actuel
     * @return un nouveau tableau, augmenté de deux elements.
     */
    public static PFB_class[] augmenterTableau(PFB_class[] partie,
                                               PFB_class jeuX) {
        PFB_class[] partie2 = new PFB_class[jeuX.getIdJeu() + 1];
        for (int i = 0; i < partie.length; i++) {
            partie2[i] = partie[i];
        }
        return partie2;
    }

    /**
     * Verifie si la partie a ete perdue, il affiche une reponse si c'est
     * le cas.
     *
     * @param jeuX       partie du jeu actuel, utilisée pour obtenir certaines
     *                   informations de ses attributs pour l'affichage si partie perdue.
     * @param victoria   sert de verification pour savoir si la partie a ete
     *                   gagné ou pas.
     * @param motifCache numero a devinir. Utilisé lors de l'affichage
     *                   si la partie est perdue.
     * @return vrai ou faux dependant si la partie est perdue
     */
    public static boolean perte(PFB_class jeuX, boolean victoria, String
            motifCache) {
        boolean reponse = false;
        if (jeuX.getNbrEssais() == jeuX.getNbrEssaisMax() && !victoria) {
            reponse = jeuX.getNbrEssais() == jeuX.getNbrEssaisMax() &&
                    !victoria;
            affichage(MSG_PERTE + MSG_MOTIF + motifCache + "\n\n");
        }
        return reponse;
    }

    /**
     * Verifie si le jeu a ete gagné, il affiche une reponse si c'est
     * le cas.
     *
     * @param jeuX  partie du jeu actuel, utilisée pour obtenir certaines
     *              informations de ses attributs pour l'affichage s'il y a eu victoire.
     * @param motif numero entré par le joueur et a comparer pour voir s'il y a
     *              eu abandon.
     * @return vrai ou faux dependant s'il y a eu une victoire.
     */
    public static boolean victoire(PFB_class jeuX, String motif,
                                   String motifCache) {
        boolean reponse = false;
        if (motifCache.equals(motif)) {
            reponse = motifCache.equals(motif);
            System.out.printf("\nBRAVO ! Vous avez reussi en %d / %d essai(s)."
                            + "\nSCORE : %.2f %%\n\n", jeuX.getNbrEssais(),
                    jeuX.getNbrEssaisMax(), jeuX.getScore());
        }
        return reponse;
    }

    /**
     * Verifie s'il y a eu abandon du jeu, il affiche une reponse si c'est
     * le cas.
     *
     * @param motif numero entré par le joueur et a comparer pour voir s'il y a
     *              eu abandon.
     * @return vrai ou faux dependant s'il y a eu d'abandon.
     */
    public static boolean abandon(String motif) {
        boolean reponse = false;
        if (motif.equals("0")) {
            affichage(MSG_ABANDON);
            reponse = motif.equals("0");
        }
        return reponse;
    }

    /**
     * vient verifier si le tableau partie est plein.
     *
     * @param partie tableau qui contient le nbr de parties jouées de type
     *               PicoFermiBagels
     * @return vrai ou faux dependant si le tableau est plein.
     */
    public static boolean plein(PFB_class[] partie) {
        boolean reponse = true;
        for (int i = 0; i < partie.length; i++) {
            if (partie[i] == null) {
                reponse = false;
            }
        }
        return reponse;
    }

    /**
     * vient afficher specifiquemet le numero d'essai de la partie actuelle
     * (remplacement de la methode SOP)
     *
     * @param jeuX partie du jeu actuelle.
     */
    public static void affichageEssais(PFB_class jeuX) {
        String texte = "\nESSAI NO " + (jeuX.getNbrEssais() + 1) +
                " => Entrez un motif (ou 0 pour abandonner): ";
        System.out.print(texte);
    }

    /**
     * vient afficher les Strings demandés sur la console (remplacement de la
     * methode SOP)
     *
     * @param texte string qu'on desire afficher sur la console.
     */
    public static void affichage(String texte) {
        System.out.print(texte);
    }

    /**
     * Valide le motif entre et gere l'exception de la classe JouerPFB
     *
     * @param jeuX partie du jeu actuel.
     * @return le motif validé.
     */
    public static String validerMotifException(PFB_class jeuX) {
        boolean validation = false;
        String motif;
        do {
            motif = Clavier.lireString();
            try {
                jeuX.faireUnEssai(motif);
                validation = true;
            } catch (MotifPFBInvalideException e) {
                System.out.print(MSG_ERR_JEUX);
            }
        } while (!validation);
        return motif;
    }

    /**
     * Methode principale du jeu PICOFERMIBAGELS, Contient l'ensemble(methodes)
     * du jeu
     *
     * @param partie tableau du jeu où les parties(variables PicoFermiBagels)
     *               sont placées.
     * @return le tableau modifié, si cela a été le cas.
     */
    public static PFB_class[] jeuxPico(PFB_class[] partie) {
        affichage(MSG_PFB);
        int nbrEssaisMax;
        boolean victoria;
        boolean perdida;
        String motif = "123";
        String motifCache;
        String tableau;
        do {
            motifCache = JeuxUtils.genererMotifPFB();
            nbrEssaisMax = Validation.validerEntier(MSG_ESSAIS, MSG_ERRESSAIS,
                    MIN_INT, MAX_INT);
            System.out.println("# caché " + motifCache);
            try {
                PFB_class jeuX = new PFB_class(motifCache,
                        nbrEssaisMax);
                if (plein(partie)) {
                    partie = augmenterTableau(partie, jeuX);
                }
                do {
                    //jeuX.afficher();
                    affichageEssais(jeuX);
                    motif = validerMotifException(jeuX);
                    partie[jeuX.getSequenceId() - 1] = jeuX;
                    tableau = jeuX.toString();
                    System.out.print(tableau);
                    if (abandon(motif)) {
                        break;
                    }
                    victoria = victoire(jeuX, motif, motifCache);
                    perdida = perte(jeuX, victoria, motifCache);
                } while (!perdida && !victoria && !abandon(motif));

            } catch (MotifPFBInvalideException e) {
                //vide    
            } catch (EntierNulOuNegatifException e) {
                //vide    
            } catch (Exception e) {
                //vide    
            }
        } while (!Validation.validerRepDeuxChoix(MSG_REJOUER, MSG_ERR_REJOUER,
                MSG_OUI, MSG_NON).equalsIgnoreCase(MSG_NON));
        return partie;
    }

    public static void main() {
        affichage(MSG_ENTETE);
        String saisieMenu;
        PFB_class[] partie = new PFB_class[2];
        do {
            affichage(MSG_MENU);
            saisieMenu = String.valueOf(Validation.validerCaractere(MSG_SAISIE,
                    MSG_ERR, NUM_MIN, NUM_MAX));
            switch (Integer.parseInt(saisieMenu)) {
                case 1:
                    partie = jeuxPico(partie);
                    break;
                case 2:
                    statistiques(partie);
                    break;
                case 3:
                    partie = effacage(partie);
                    break;
                case 4:
                    affichage("\nAUREVOIR !\n\n");
                    break;
            }
        } while (Integer.parseInt(saisieMenu) == 1 ||
                Integer.parseInt(saisieMenu) == 2 ||
                Integer.parseInt(saisieMenu) == 3);
    }
}