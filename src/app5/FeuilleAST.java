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
    try { this.val = Integer.parseInt(terminal.chaine); } catch (Exception e) { this.val = 1; }
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
