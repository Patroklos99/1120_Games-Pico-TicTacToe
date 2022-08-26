/**
 * Programme qui contient la creation du type de variable PicoFermiBagels.
 * @author Renzo Arturo Salcedo Salcedo
 * Code permanent: SALR02089408
 * Courriel: salcedo_salcedo.renzo@courrier.uqam.ca
 * Cours: INF1120-21
 * @version 25/04/2021
 */
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
public class PFB_class {
    //declaration des attributs de l'objet (variables d'instances) et les 
    //attributs de classe
    private static int sequenceId = 0;
    
    private int idJeu;
    private String motifCache;
    private int nbrEssais;
    private int nbrEssaisMax;
    private ResultatEssaiPFB[] essais;
    private double score;
    private boolean partieAbandonnee;
    //Déclaration et initialisation de constantes
    private static final char NUM_MAX = '9';
    private static final char NUM_MIN = '1';
    
    /**
     * constructeur par default
     */
    public PFB_class(){
        //vide
    }
    
    /**
     * methode de classe appelee pour changer la valeur de la variable de clase 
     * sequenceId
     * @param nouvelleValeur valeur recu pour le changement
     */
    public static void appelSequenceId (int nouvelleValeur){
        sequenceId = nouvelleValeur;       
    }
    
    /**
     * Constructeur avec des paremetres.
     * @param motifCache le motif cache genere par une autre methode, utilise
     * pour la construction et pour la levee d'une exception si c'est le cas.
     * @param nbrEssaisMax nbr d'essais maximum choisis par le joueur, utilise
     * pour la construction et pour la levee d'une exception si c'est le cas.
     */
    public PFB_class(String motifCache, int nbrEssaisMax)
        throws MotifPFBInvalideException, EntierNulOuNegatifException {
        if (!bornesPico(motifCache) && nbrEssaisMax < 0) {
            throw new MotifPFBInvalideException();
        }
                
        if (!bornesPico(motifCache)) {
            throw new MotifPFBInvalideException();
        }
        
        if (nbrEssaisMax < 0) {
            throw new EntierNulOuNegatifException();
        }
         
        this.motifCache = motifCache;
        this.nbrEssaisMax = nbrEssaisMax;
        sequenceId++;
        idJeu = sequenceId;
        essais = new ResultatEssaiPFB[nbrEssaisMax];
        nbrEssais = 0;
        score = -1;
        partieAbandonnee = false;
    }
    
    /**
     * Getters
     */
    public int getIdJeu(){
        return idJeu;
    }
    
    public int getNbrEssais(){
        return nbrEssais;
    }
    
    public int getNbrEssaisMax(){
        return nbrEssaisMax;
    }
    
    public double getScore(){
        return score;
    }
    
    public boolean isPartieAbandonnee(){
        return partieAbandonnee;
    }
    
    /**
     * Valide si la partie est terminee
     * @return une reponse de valeur vrai ou faux dependant du cas.
     */
    public boolean estPartieTerminee() {
        boolean reponse = false;
        if(estPartieGagnee() || estPartiePerdue() || partieAbandonnee){
            reponse = true;    
        }
        return reponse;
    }
    
    /**
     * Valide si la partie est gagnee
     * @return une reponse de valeur vrai ou faux dependant du cas.
     */
    public boolean estPartieGagnee() {
        boolean reponse = false;
        for (int i=0; i < essais.length; i++){
            if(essais[i] != null){
                if(nbrEssais <= nbrEssaisMax && 
                        motifCache.equals(essais[i].getMotifDonne())){ 
                    reponse = true;    
                }
            }
        }
        return reponse;
    }
    
    /**
     * Valide si la partie est perdue
     * @return une reponse de valeur vrai ou faux dependant du cas.
     */
    public boolean estPartiePerdue() {
        boolean reponse = false;
        if(nbrEssais == nbrEssaisMax && !estPartieGagnee()){ 
            reponse = true;    
        }
        return reponse;
    }
    
    /**
     * Gere la creation d'une variable de type ResultatEssaiPFB et gestion de 
     * certains attributs de cette classe.
     * Si le string n'est pas valide et egale a zero, une exception est levee.
     * Si le motif est egal a zero, la variable partieAbandonnee est changee a 
     * true.
     * Autrement, il y a une creation d'une variable qui equivaut a chaque 
     * essai d'une partie.
     * Si la partie est perdue ou gagnee, le score est affecté dependant du cas
     * @param motif nombre ecrit par le jouer requis pour certaines conditions.
     */
    public void faireUnEssai(String motif) throws MotifPFBInvalideException {
        if(!bornesPico(motif) && !motif.equals("0")){
        throw new MotifPFBInvalideException();
        }
        
        if(motif.equals("0")){
            partieAbandonnee = true;  
        }
        else if (!estPartieTerminee()){
            ResultatEssaiPFB ax = new ResultatEssaiPFB (motif, motifCache);
            essais[nbrEssais] = ax;
            nbrEssais++;
        }
        
        if (estPartiePerdue()) {
            score = 0;
        } else if (estPartieGagnee()) {
            score = 100 - ((nbrEssais - 1) * (100.0 / nbrEssaisMax));      
        }
    }   
    
    /**
     * Reinitialise la partie
     */
    public void reinitialiserPartie(){
        essais = new ResultatEssaiPFB[nbrEssaisMax];
        nbrEssais = 0;
        score = -1;
        partieAbandonnee = false;
    }
    
    /**
     * Mets les attributs en format string pour pouvoir les afficher par apres.
     * @return Un tableau avec les informations requises sous format 
     * string.
     */
    public String toString() {
        String tableau = String.format("\nNO ESSAI | MOTIF | RESULTATS" + 
        "\n-----------------------------------\n");
        String corps = "";        
        for (int i=0; i < essais.length; i++){
            if(essais[i] != null){
            corps += String.format("%-9d| %-6s| %-10s\n",(i+1),
            essais[i].getMotifDonne(),essais[i]);
            } 
        }
        tableau = tableau + corps;
        return tableau;
    }
    
    /**
     * Vient chercher l'attribut sequenceId
     * @return sequenceId Sequence demandée.
     */
    public static int getSequenceId(){
        return sequenceId;
    }
    
    /**
     * Reinitialise l'aatribut sequenceId a zero.
     */
    public static void initSequenceId() {
        sequenceId = 0;
    }
    
    /**
     * Setters
     */
    public void setIdJeu(int idJeu){
        this.idJeu = idJeu; 
    }
    
    /**
     * Aide visuelle des attributs sous format String. 
     * Pas utilisé
     */
    public void afficher() {
        System.out.printf("Idjeu: %d\nMotifCache: %s\nessais: %s\nNbrEssais: "+
        "%d\nNbrEssaisMax: %d\nScore: %.2f\nPartie abandonnee: %s\n\n",
        idJeu,motifCache,essais,nbrEssais,nbrEssaisMax,score,partieAbandonnee);
        
        //System.out.println(Arrays.toString(essais));
        //besoin d'importer "import java.util.Arrays" dans le cas desire de 
        //vouloir visualiser ce SOP.
    }
    
    /**
     * valide le numero/chaine saisi du jeuPico
     * @param chaine chaine de caracteres a valider
     * @return estNum vrai ou faux dependant si le numero saisi est valide
     */
    private static boolean bornesPico (String chaine){
        boolean estNum = false;
        int i = 0;
        char car;
        String un;
        String deux;
        String trois;
        if (chaine !=null && !chaine.isEmpty()) {
            estNum = chaine !=null && !chaine.isEmpty();
            while (i < chaine.length() && estNum) {
                car = chaine.charAt(i);
                estNum = car >= NUM_MIN && car <= NUM_MAX && 
                        chaine.length() == 3;
                //longueur de la chaine doit etre egale a trois.
                i++;
                if (chaine.length() == 3 && car >= NUM_MIN && car <= NUM_MAX ){
                    un = chaine.substring(0,1);
                    deux = chaine.substring(1,2);
                    trois = chaine.substring(2,3);
                    estNum = (!(un.equals(deux)) && !(un.equals(trois)) && 
                            !(deux.equals(trois))); 
                    //Le String un equivaut au premier chiffre de la chaine
                    //recu en parametres. Deux equivaut au deuxieme chiffre et
                    //trois au troisieme de cette même chaine.
                    //estNuM devient false s'il contient des chiffres repetés.
                }
            }
        }
        return estNum;    
    }
}