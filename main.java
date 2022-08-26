/**
 * Programme qui contient le jeu PICO-FERMI-BAGEL et Tic tac toe.
 *
 * @author Renzo Arturo Salcedo Salcedo
 * Code permanent: SALR02089408
 * Courriel: salcedo_salcedo.renzo@courrier.uqam.ca
 * Cours: INF1120-21
 * @version 01/04/2021
 */

public class main {

    //Déclaration et initialisation de constantes
    //Constantes menu principale
    public static final String ENTETE_MENU = ("Ce logiciel permet de jouer à " +
            "Pico FERMI BAGELS et au TIC TAC TOE.\n");
    public static final String MSG_MENU = ("\n----" + ("\nMENU") + "\n----" +
            "\n1. PICO FERMI BAGELS" + "\n2. TIC TAC TOE" + "\n3. QUITTER\n\n");
    public static final String MSG_ERR = ("\nERREUR, entrez un chiffre entre 1"
            + " et 3...Recommencez!");
    public static final String MSG_SAISIE = ("Entrez votre choix: ");
    //Constantes PICO
    public static final String MSG_BAGELS = ("\n*********************" +
            "\n* PICO FERMI BAGELS *" + "\n*********************\n\n");
    public static final String ENTETE_TABLEAUPICO = "\nNO ESSAI  | MOTIF | " +
            "RESULTATS \n-------------------------------------";
    public static final String MSG_REJOUER = ("Voulez-vous jouer encore (oui " +
            "ou non): ");
    public static final String MSG_ERRPICO = ("\nERREUR, le motif doit etre " +
            "compose de 3 chiffres distincts entre 1 et 9... Recommencez !");
    public static final String MSG_ERR_REJOUER = ("\nERREUR, vous devez " +
            "repondre par oui ou par non... Recommencez !\n");
    //Constantes TIC-TAC
    public static final char NUM_MAX = '3';
    public static final char NUM_MIN = '1';
    public static final char MAX_TIC = '9';
    public static final char MIN_PIC = '0';
    public static final String MSG_NOM1 = ("Joueur 1, entrez votre nom: ");
    public static final String MSG_NOM2 = ("Joueur 2, entrez votre nom: ");
    public static final String MSG_TIC = ("\n***************" + "\n* TIC TAC " +
            "TOE *" + "\n***************\n\n");
    public static final String MSG_ERRNOM = ("\nERREUR, le nom doit contenir " +
            "entre 3 et 10 caracteres... Recommencez !\n");
    public static final String MSG_ERRCASE = ("\nERREUR, numero de case " +
            "invalide... Recommencez !");
    public static final String MSG_CASE_X = (", entrez le numero d'une case " +
            "pour placer votre X: ");
    public static final String MSG_CASE_O = (", entrez le numero d'une case " +
            "pour placer votre O: ");
    public static final String TABLEAU = ("\n 1 | 2 | 3 \n-----------\n 4 | 5") +
            (" | 6\n-----------\n 7 | 8 | 9 \n\n");
    public static final String MSG_SAISIEPICO = ("ESSAI NO 1 => Entrez un ") +
            ("motif (ou 0 pour abandonner): ");
    public static final String MSG_NULLE = ("Partie nulle, aucun gagnant !\n");

    /**
     * vient afficher les Strings demandés sur la console (remplacement de la
     * methode SOP
     *
     * @param texte string qu'on desire afficher sur la console
     * @return texte énoncé affiché.
     */
    public static String affichage(String texte) {
        System.out.print(texte);
        return texte;
    }

    /**
     * vient afficher les Strings demandés sur la console en majuscules
     *
     * @param texte string qu'on desire afficher sur la console en majuscules
     * @return texte énoncé affiché en majuscules
     */
    public static String maj(String texte) {
        System.out.print(texte.toUpperCase());
        return texte;
    }

    /**
     * affiche la phrase gagnante du jeu pour le 2ieme joueur
     *
     * @param winner vrai ou faux s'il existe un gagnant
     * @param nom4   est le nom du deuxieme joueur
     * @return msgGagnant2 phrase gagnante
     */
    public static String gagnantTic2(boolean winner, String nom4) {
        String msgGagnant2 = "";
        //winner veut dire gagnant. Si la variable est vrai, il y en a un.
        if (winner) {
            msgGagnant2 = affichage("BRAVO ");
            maj(nom4);
            affichage(", vous " +
                    "remportez la victoire !\n");
        }
        return msgGagnant2;
    }

    /**
     * verifie s'il y a encores des mouvements disponibles pour les joueurs
     * du jeu Tic tac toe.
     *
     * @param tab tableau du jeu tic tac toe avec ses renseignements
     * @return movement vrai ou faux dependant s'il y a des movements possibles
     */
    public static boolean zeroMouvement(String tab) {
        boolean mouvement = true;
        for (int a = 1; a <= 9; a++) {
            if (tab.contains(Integer.toString(a))) {
                mouvement = false;
            }
            /*methode vient verifier s'il n'y a pas des numeros du 1-9 dans le
             *tableau tictactoe.
             */
        }
        return mouvement;
    }

    /**
     * affiche la phrase gagnante du jeu pour le 1er joueur ou si la partie est
     * nulle
     *
     * @param winner vrai ou faux s'il existe un gagnant
     * @param nom1   est le nom du premier joueur
     * @param tab    tableau du jeu contenant les renseignements
     * @return msgGagnant phrase gagnante ou d'egalite
     */
    public static String gagnantTic(boolean winner, String nom1, String tab) {
        String msgGagnant = "";
        boolean tour = false;
        if (winner) {
            affichage("BRAVO ");
            maj(nom1);
            affichage(", vous remportez " +
                    "la victoire !\n");
            msgGagnant = "arret";

        } else if (!winner) {
            tour = zeroMouvement(tab);
            if (tour) {
                affichage(MSG_NULLE);
                msgGagnant = "arret";
            }
        }
        /*
         * S'il y a un gagnant, l'affichage gagnant se produit. Si non, on
         * verifie s'il n'y a plus des numeros disponibles a remplacer sur le
         * tableau tictactoe. Si cest vrai, le message de partie nulle
         * s'affiche. Dans les deux cas (victoire/partie nulle), si un est
         * vrai, msgGagnant prend une valeur pour signaler dans le methode
         * jeuxTicTac qu'il faut sortir de la boucle. (break;).
         */
        return msgGagnant;
    }

    /**
     * verifie s'il y a un gagnant au jeu tic tac toe apres chaque tour des
     * joueurs
     *
     * @param tab tableau du jeu tic tac toe avec ses renseignements
     * @return winner vrai ou faux dependant s'il y a un gagnant.
     */
    public static boolean verifierGagnantTic(String tab) {
        boolean winner = false;
        for (int a = 0; a < 8; a++) {
            String ligne = null;
            switch (a) {
                case 0:
                    ligne = Character.toString(tab.charAt(2)) +
                            Character.toString(tab.charAt(6)) +
                            Character.toString(tab.charAt(10));
                    break;
                case 1:
                    ligne = Character.toString(tab.charAt(26)) +
                            Character.toString(tab.charAt(30)) +
                            Character.toString(tab.charAt(34));
                    break;
                case 2:
                    ligne = Character.toString(tab.charAt(49)) +
                            Character.toString(tab.charAt(53)) +
                            Character.toString(tab.charAt(57));
                    break;
                case 3:
                    ligne = Character.toString(tab.charAt(2)) +
                            Character.toString(tab.charAt(26)) +
                            Character.toString(tab.charAt(49));
                    break;
                case 4:
                    ligne = Character.toString(tab.charAt(6)) +
                            Character.toString(tab.charAt(30)) +
                            Character.toString(tab.charAt(53));
                    break;
                case 5:
                    ligne = Character.toString(tab.charAt(10)) +
                            Character.toString(tab.charAt(34)) +
                            Character.toString(tab.charAt(57));
                    break;
                case 6:
                    ligne = Character.toString(tab.charAt(2)) +
                            Character.toString(tab.charAt(30)) +
                            Character.toString(tab.charAt(57));
                    break;
                case 7:
                    ligne = Character.toString(tab.charAt(10)) +
                            Character.toString(tab.charAt(30)) +
                            Character.toString(tab.charAt(49));
                    break;
            }
            /*
             * boucle FOR vient concatener 3 positions où se trouvent des numero
             * du tableau Tictactoe pour toutes les combinaisons possibles pour
             * gagner et les place dans la variable ligne.
             * Après on verifie si la variable ligne contient XXX ou OOO. Si c'est
             * le cas, la variable bouleenne winner devient vrai, il y a donc
             * un gagnant.
             */
            if (ligne.equals("XXX")) {
                winner = ligne.equals("XXX");
            } else if (ligne.equals("OOO")) {
                winner = ligne.equals("OOO");
            }
        }
        return winner;
    }

    /**
     * remplace les X et O sur le tableau tic tac toe.
     *
     * @param tab    tableau tic tac toe
     * @param numero numero/case qu'on desire remplacer sur le tableau tic.
     * @param w      compteur qui augmente a chaque tour sur la methode jeuxTicTac
     *               et il gere quand on remplace X et O.
     * @return tab nouveau tableau tic tac toe avec les changements
     */
    public static String remplacerXO(String tab, String numero, int w) {
        /*
         * w compteur qui augmente a chaque tour sur la methode jeuxTicTac
         * et il gere quand on remplace X et O
         */
        if (w % 2 == 0) {
            tab = tab.replace(numero.charAt(0), 'X');
        } else {
            tab = tab.replace(numero.charAt(0), 'O');
        }
        return tab;
    }

    /**
     * confirme que le numero saisi soit valide
     *
     * @param nom       nom du premier joueur
     * @param msgCase   message informant les choix possibles
     * @param tab       ayant le contenu du Tic tac toe
     * @param msgERRTIC message d'erreur si la saisie n'est pas valide.
     * @return optiontic numero valide entree par le joueur
     */
    public static String validerNumTic(String nom, String msgCase, String tab,
                                       String msgERRTIC) {
        String optiontic;
        do {
            maj(nom);
            affichage(msgCase);
            optiontic = Clavier.lireString();
            if (!bornesMenu(optiontic, MAX_TIC, NUM_MIN) ||
                    !tab.contains(optiontic)) {
                System.out.println(msgERRTIC);
            }
            //cette derniere methode vient verifier si optiontic est valide.
        } while (!bornesMenu(optiontic, MAX_TIC, NUM_MIN) ||
                !tab.contains(optiontic));
        return optiontic;
    }

    /**
     * affiche le tableau vierge du debut du jeu Tic tac toe.
     *
     * @param TABLEAU tableau tic tac sur sa forme initiale constante String
     * @return TABLEAU tableau vierge Tic tac toe
     */
    public static String tableau(String TABLEAU) {
        System.out.print(TABLEAU);
        return TABLEAU;
    }

    /**
     * valide que le nom entree soit valide
     *
     * @param msgSaisieNom message demandant un nom
     * @param msgERR2      message d'erreur correspondant a un nom invalide
     * @return name nom saisi valide
     */
    public static String validerOptionNom(String msgSaisieNom, String msgERR2) {
        String name;
        //name veut dire nom
        do {
            affichage(msgSaisieNom);
            name = Clavier.lireString();
            if (name.length() < 3 || name.length() > 10) {
                affichage(msgERR2);
            }
        } while (name.length() < 3 || name.length() > 10);
        return name;
    }

    /**
     * Methode principale du jeu TicTac, contient l'ensemble(methodes) du jeu
     */
    public static void jeuxTicTac() {
        String nom = validerOptionNom(MSG_NOM1, MSG_ERRNOM);
        String nom2 = validerOptionNom(MSG_NOM2, MSG_ERRNOM);
        String nom1 = nom;
        String nom4 = nom2;
        boolean gagnant;
        boolean rejouer = false;
        int compteurNom = 0;
        /*
         * La variable compteurNom augmente a chaque fois qu'on finit de jouer.
         * Il contrôle le changement d'ordre des noms lorsqu'on rejoue apres
         * un gagnant ou une partie nulle.
         */
        do {
            String tab = tableau(TABLEAU);
            do {
                int t = 0;
                /*
                 * La variable t augmente après le tour de chaque joueur. Elle
                 * determine que le premier joueur soit toujours X(pair) et le
                 * deuxieme soit 0(impair). Initilisant toujours avec 0(pair).
                 */
                if (compteurNom % 2 == 1) {
                    nom1 = nom2;
                    nom4 = nom;
                } else {
                    nom1 = nom;
                    nom4 = nom2;
                }
                String numero = validerNumTic(nom1, MSG_CASE_X, tab,
                        MSG_ERRCASE);
                tab = remplacerXO(tab, numero, t);
                affichage(tab);
                gagnant = verifierGagnantTic(tab);
                String verification = gagnantTic(gagnant, nom1, tab);
                if (!verification.isEmpty()) {
                    break;
                    //S'il y a un gagnant ou partie nulle, on sort de la boucle.
                }
                t++;
                String numero2 = validerNumTic(nom4, MSG_CASE_O, tab,
                        MSG_ERRCASE);
                tab = remplacerXO(tab, numero2, t);
                affichage(tab);
                gagnant = verifierGagnantTic(tab);
                gagnantTic2(gagnant, nom4);
            } while (!gagnant);
            compteurNom++;
            rejouer = validerRejouer(MSG_REJOUER, MSG_ERR_REJOUER);
        } while (rejouer);
    }

    /**
     * verifie si le joeur rejoue
     *
     * @param msgRejouer message qui demande s'il veut rejouer
     * @param msgErrRej  message d'erreur si la saisie n'est pas correcte
     * @return rejouer soit s'il veut rejouer ou pas (vrai ou faux)
     */
    public static boolean validerRejouer(String msgRejouer, String msgErrRej) {
        boolean rejouer = false;
        String reponse;
        String decision1 = "Oui";
        String decision2 = "Non";
        do {
            System.out.print(msgRejouer);
            reponse = Clavier.lireString();
            rejouer = reponse.equalsIgnoreCase(decision1);
            if (!(reponse.equalsIgnoreCase(decision1)) &&
                    !(reponse.equalsIgnoreCase(decision2))) {
                affichage(msgErrRej);
            }
        } while (!reponse.equalsIgnoreCase(decision2) &&
                !(reponse.equalsIgnoreCase(decision1)));
        return rejouer;
    }

    /**
     * Methode principale du jeu PICOFERMIBAGELS, Contient l'ensemble(methodes)
     * du jeu
     */
    public static void jeuxPico() throws EntierNulOuNegatifException, MotifPFBInvalideException {
                JouerPFB.main();;
    }

    /**
     * valide le numero/chaine saisi du menu principale.
     *
     * @param chaine chaine de caracteres a valider
     * @param numMax borne maximale pour la validation de la chaine
     * @param numMin borne minimale pour la validation de la chaine
     * @return estNum vrai ou faux dependant si le numero saisi est valide
     */
    public static boolean bornesMenu(String chaine, char numMax, char numMin) {
        boolean estNum = false;
        int i = 0;
        char car;
        if (chaine != null && !chaine.isEmpty()) {
            estNum = true;
            while (i < chaine.length() && estNum) {
                car = chaine.charAt(i);
                estNum = car >= numMin && car <= numMax &&
                        chaine.length() == 1;
                //la longueur de la chaine doit être egale a 1. 
                i++;
            }
        }
        return estNum;
    }

    /**
     * Valide que l'entree saisie pour le menu principal soit correcte (1,2,3)
     *
     * @param msgSaisie message qui demande une entree
     * @param msgERR    message d'erreur si la saisie n'est pas valide.
     * @return option le choix du joueur pour le menu principal
     */
    public static String validerSaisiMenu(String msgSaisie, String msgERR) {
        String option;
        do {
            affichage(msgSaisie);
            option = Clavier.lireString();
            bornesMenu(option, NUM_MAX, NUM_MIN);
            if (!bornesMenu(option, NUM_MAX, NUM_MIN)) {
                System.out.println(msgERR);
            }
        } while (!bornesMenu(option, NUM_MAX, NUM_MIN));
        return option;
    }

    /**
     * Méthode principale du prog
     *
     */
    public static void main(String[] arg) throws EntierNulOuNegatifException, MotifPFBInvalideException {
        String sasieMenu;
        affichage(ENTETE_MENU);
        do {
            affichage(MSG_MENU);
            sasieMenu = validerSaisiMenu(MSG_SAISIE, MSG_ERR);
            switch (Integer.parseInt(sasieMenu)) {
                case 1 -> {
                    affichage(MSG_BAGELS);
                    jeuxPico();
                }
                case 2 -> {
                    affichage(MSG_TIC);
                    jeuxTicTac();
                }
                case 3 -> System.out.println("\nAUREVOIR !");
            }
        } while (Integer.parseInt(sasieMenu) == 1 ||
                Integer.parseInt(sasieMenu) == 2);
    }
}