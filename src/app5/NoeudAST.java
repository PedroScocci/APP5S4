package app5;

/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class NoeudAST extends ElemAST {

  // Attributs
  ElemAST elemG, elemD;
  String chaine;

  /** Constructeur pour l'initialisation d'attributs
   */
  public NoeudAST(ElemAST elemG, ElemAST elemD, String chaine ) { // avec arguments
    //
    this.elemG = elemG;
    this.elemD = elemD;
    this.chaine = chaine;
  }

 
  /** Evaluation de noeud d'AST
   */
  public double EvalAST( ) {
     //
    return 1;
  }


  /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
     //
    return "(" + elemG.LectAST() + " + " + elemD.LectAST() + ")";
  }

}


