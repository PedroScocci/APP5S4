package app5;

public class FeuilleVariableAST extends FeuilleAST{
    /**Constructeur pour l'initialisation d'attribut(s)
     */
    public FeuilleVariableAST(Terminal terminal ) {  // avec arguments
        super(new Terminal("1", TypeUL.operande));
        this.terminal = terminal;
        this.val = 1;
    }
}
