package app5;

public class LexicalException extends Exception {
    public LexicalException(String message) {
        super("Erreur Lexicale: " + message);
    }
}
