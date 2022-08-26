
import java.util.Random;

/**
 * Classe utilitaire pour le TP3 du cours INF1120 H21.
 * Cette classe contient la methode genererMotifPFB servant a generer un motif
 * PFB aleatoire.
 *
 * @author Melanie lord
 * @version Hiver 2021
 */
public class JeuxUtils {

    /**
     * Genere un motif aleatoire compose de 3 chiffres distincts dans lequel
     * chaque chiffre est entre 1 et 9 inclusivement.
     *
     * @return le motif genere.
     */
    public static String genererMotifPFB() {
        int nbr;
        String motif = "";

        while (motif.length() != 3) {
            nbr = tirerUnNombreEntreMinEtMax(1, 9);
            while (motif.contains(nbr + "")) {
                nbr = tirerUnNombreEntreMinEtMax(1, 9);
            }
            motif = motif + nbr;
        }
        return motif;
    }

    //-----------------
    // METHODE PRIVEE
    //-----------------

    /**
     * Genere un nombre aleatoire entre min et max.
     *
     * @param min la borne minimale (incluse) pour la generation du nombre
     * @param max la borne maximale (incluse) pour la generation du nombre
     * @return un nombre aleatoire entre min et max.
     */
    private static int tirerUnNombreEntreMinEtMax(int min, int max) {
        Random generateurAlea = new Random();
        return generateurAlea.nextInt(max - min + 1) + min;
    }

}
