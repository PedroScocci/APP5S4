package app5;

public class NoeudSoustractionAST extends NoeudAST {

    /** Constructeur pour l'initialisation d'attributs
     */
    public NoeudSoustractionAST(ElemAST elemG, ElemAST elemD, String chaine ) {
        super(elemG, elemD, chaine);
    }


    /** Evaluation de noeud d'AST
     */
    public double EvalAST( ) {
        return elemG.EvalAST() - elemD.EvalAST();
    }
}
