package app5;

/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class FeuilleAST extends ElemAST {

  // Attribut(s)
    Terminal terminal;
    double val;


/**Constructeur pour l'initialisation d'attribut(s)
 */
  public FeuilleAST(Terminal terminal ) {  // avec arguments
    this.terminal = terminal;
    this.val = Integer.parseInt(terminal.chaine);
  }


  /** Evaluation de feuille d'AST
   */
  public double EvalAST( ) {
    //
      return val;
  }


 /** Lecture de chaine de caracteres correspondant a la feuille d'AST
  */
  public String LectAST( ) {
    //
      return terminal.chaine;
  }

}
