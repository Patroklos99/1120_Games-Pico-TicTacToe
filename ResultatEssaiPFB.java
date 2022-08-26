/**
 * Classe fournie pour le TP3 du cours INF1120 H21.
 * Cette classe modelise un essai du jeu PFB. Un essai comprend le motif
 * donne, le motif cache, et le nombre de Pico et Fermi obtenu par le motif
 * donne (selon le motif cache)
 *
 * @author Melanie lord
 * @version Hiver 2021
 */
public class ResultatEssaiPFB {

    private int nbrPico;       //Le nombre de Pico pour cet essai
    private int nbrFermi;      //Le nombre de Fermi pour cet essai
    private String motifDonne; //Le motif a verifier / evaluer
    private String motifCache; //Le motif cache

    /**
     * Construit un ResultatEssaiPFB en initialisant les attributs motifDonne
     * et motifCache avec les valeurs recues en parametres. La valeur des
     * attributs nbrPico et nbrFermi est ensuite calculee.
     *
     * @param motifDonne le motif a verifier / evaluer
     *                   ANT : on suppose que le motifDonne est valide
     * @param motifCache le motif a trouve
     *                   ANT : on suppose que le motifCache est valide
     */
    public ResultatEssaiPFB(String motifDonne, String motifCache) {
        this.motifDonne = motifDonne;
        this.motifCache = motifCache;
        evaluerMotif();
    }

    /**
     * Retourne le nombre de Pico pour cet essai.
     *
     * @return le nombre de Pico pour cet essai.
     */
    public int getNbrPico() {
        return nbrPico;
    }

    /**
     * Retourne le nombre de Fermi pour cet essai.
     *
     * @return le nombre de Fermi pour cet essai.
     */
    public int getNbrFermi() {
        return nbrFermi;
    }

    /**
     * @return
     */
    public String getMotifDonne() {
        return motifDonne;
    }

    /**
     * Retourne le motif donne pour cet essai.
     *
     * @return le motif donne pour cet essai.
     */
    public String getMotifCache() {
        return motifCache;
    }

    /**
     * Retourne une representation sous forme de chaine de caracteres de ce
     * ResultatEssaiPFB. La chaine retournee est sur une ligne, et contient
     * le resultat de cet essai en terme de Pico, Fermi, et Bagels. Les
     * Fermi sont toujours positionnes avant les Pico, dans la chaine retournee.
     * S'il n'y a aucun Fermi et aucun Pico, la chaine retournee est "BAGELS"
     *
     * @return une representation sous forme de chaine de caracteres de ce
     * ResultatEssaiPFB.
     */
    public String toString() {
        String resultat = "";

        if (nbrFermi == 0 && nbrPico == 0) {
            resultat = "BAGELS";
        } else {
            for (int i = 0; i < nbrFermi; i++) {
                resultat = resultat + "FERMI ";
            }

            for (int i = 0; i < nbrPico; i++) {
                resultat = resultat + "PICO ";
            }
        }
        return resultat;
    }

    /**
     * Calcule le nombre de Pico et le nombre de Fermi pour le motifDonne.
     */
    private void evaluerMotif() {
        char cMotifDonne;
        char cMotifCache;

        for (int i = 0; i < motifDonne.length(); i++) {
            cMotifDonne = motifDonne.charAt(i);
            cMotifCache = motifCache.charAt(i);
            if (cMotifDonne == cMotifCache) {
                nbrFermi++;
            } else if (motifCache.contains(cMotifDonne + "")) {
                nbrPico++;
            }
        }
    }
}