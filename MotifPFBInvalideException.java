/**
 * Exception levee lorsqu'un motif PFB est invalide.
 * 
 * Classe d'exception utilisee dans le cadre du TP3 du cours INF1120 H21.
 * 
 * @author Melanie Lord
 * @version Hiver 2021
 */
public class MotifPFBInvalideException extends Exception {
   public MotifPFBInvalideException (String message){
        super(message);
    }

    public MotifPFBInvalideException (){
        super();
    }
}

