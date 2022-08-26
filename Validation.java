/**
 * Classe utilitaire contenant des methodes de validation des entrees
 * de l'utilisateur.
 * <p>
 * Classe fournie dans le cadre du TP3 INF1120 H21.
 *
 * @author Melanie Lord
 * @version Hiver 2021
 */
public class Validation {

    /**
     * Valide un nombre entier entre min et max inclusivement.
     *
     * @param msgSol le message de sollicitation a afficher
     * @param msgErr le message d'erreur a afficher lorsque la valeur saisie
     *               est invalide
     * @param min    borne minimum incluse pour une valeur valide
     * @param max    borne maximum incluse pour une valeur valide
     * @return un nombre entier entre min et max inclusivement.
     */
    public static int validerEntier(String msgSol, String msgErr,
                                    int min, int max) {

        int entier = min - 1;

        do {
            try {
                System.out.print(msgSol);
                entier = Clavier.lireInt();

                if (entier < min || entier > max) {
                    System.out.println(msgErr);
                }
            } catch (NumberFormatException e) {
                System.out.println(msgErr);
            }
        } while (entier < min || entier > max);

        return entier;

    }

    /**
     * Valide un caractere entre carMin et carMax inclusivement.
     *
     * @param msgSol le message de sollicitation a afficher
     * @param msgErr le message d'erreur a afficher lorsque la valeur saisie
     *               est invalide
     * @param carMin borne minimum incluse pour une valeur valide
     * @param carMax borne maximum incluse pour une valeur valide
     * @return un caractere entre carMin et carMax inclusivement
     */
    public static char validerCaractere(String msgSol, String msgErr,
                                        char carMin, char carMax) {
        String choix;

        System.out.print(msgSol);
        choix = Clavier.lireString();

        while (!(choix.length() == 1 && choix.charAt(0) >= carMin
                && choix.charAt(0) <= carMax)) {
            System.out.println(msgErr);
            System.out.print(msgSol);
            choix = Clavier.lireString();
        }
        return choix.charAt(0);
    }

    /**
     * Valide que la reponse donnee par l'utilisateur est soit repValide1, soit
     * repValide2 (peu importe la casse).
     *
     * @param msgSol     le message de sollicitation a afficher
     * @param msgErr     le message d'erreur a afficher lorsque la reponse saisie
     *                   est invalide
     * @param repValide1 une des deux reponses qui est valide
     * @param repValide2 l'autre reponse valide
     * @return une reponse valide
     * (soit repValide1, soit repValide2, peu importe la casse)
     */
    public static String validerRepDeuxChoix(String msgSol, String msgErr,
                                             String repValide1, String repValide2) {
        String rep;

        System.out.print(msgSol);
        rep = Clavier.lireString();
        while (!rep.equalsIgnoreCase(repValide1)
                && !rep.equalsIgnoreCase(repValide2)) {
            System.out.println(msgErr);
            System.out.print(msgSol);
            rep = Clavier.lireString();
        }
        return rep;
    }

}
