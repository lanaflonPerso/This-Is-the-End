package co.simplon.formation.exception;

public class FormatIdRhExeption extends RuntimeException{

    public FormatIdRhExeption(){
        System.out.println("Le format de l'identifiant RH doit respecter 'AAA111'");
    }
}
