package app5;

/** Classe representant une feuille d'AST
 */
public class FeuilleNombreAST extends FeuilleAST {


    /**Constructeur pour l'initialisation d'attribut(s)
     */
    public FeuilleNombreAST(Terminal terminal ) {  // avec arguments
        super(terminal);
    }

    /** Lecture de chaine de caracteres correspondant a la feuille d'AST
     */
    public String LectAST( ) {
        //
        return terminal.chaine;
    }
}
