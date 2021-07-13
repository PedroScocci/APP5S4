package app5;


/** Classe representant une feuille d'AST
 */
public class NoeudAdditionAST extends NoeudAST {

    /** Constructeur pour l'initialisation d'attributs
     */
    public NoeudAdditionAST(ElemAST elemG, ElemAST elemD, String chaine ) {
        super(elemG, elemD, chaine);
    }


    /** Evaluation de noeud d'AST
     */
    public double EvalAST( ) {
        return elemG.EvalAST() + elemD.EvalAST();
    }
}

