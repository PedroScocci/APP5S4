package app5;

public class SyntaxException extends Exception {
    public SyntaxException(String message) {
        super("Erreur syntaxique : " + message + "\n");
    }
}
