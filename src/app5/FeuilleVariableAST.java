package app5;

public class FeuilleVariableAST extends FeuilleAST{
    /**Constructeur pour l'initialisation d'attribut(s)
     */
    public FeuilleVariableAST(Terminal terminal ) {  // avec arguments
        super(terminal);
    }

    /** Lecture de chaine de caracteres correspondant a la feuille d'AST
     */
    public String LectAST( ) {
        //
        return terminal.chaine;
    }
}
