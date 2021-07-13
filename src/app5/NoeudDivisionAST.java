package app5;

public class NoeudDivisionAST extends NoeudAST{

    /** Constructeur pour l'initialisation d'attributs
     */
    public NoeudDivisionAST(ElemAST elemG, ElemAST elemD, String chaine ) {
        super(elemG, elemD, chaine);
    }


    /** Evaluation de noeud d'AST
     */
    public double EvalAST() {
        return elemG.EvalAST() / elemD.EvalAST();
    }
}
