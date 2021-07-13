package app5;

public class FeuilleVariableAST extends FeuilleAST{
    /**Constructeur pour l'initialisation d'attribut(s)
     */
    public FeuilleVariableAST(Terminal terminal ) {  // avec arguments
        super(null);
        this.terminal = terminal;
        this.val = 0;
    }
}
