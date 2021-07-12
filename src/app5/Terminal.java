package app5;

/** @author Ahmed Khoumsi */

enum TypeUL {
  operateur,
  operande,
  erreur
}

/** Cette classe identifie les terminaux reconnus et retournes par
 *  l'analyseur lexical
 */
public class Terminal {

// Constantes et attributs
String chaine;
TypeUL type; //0 = opérateur, 1 = opérande, 2 = erreur


/** Un ou deux constructeurs (ou plus, si vous voulez)
  *   pour l'initalisation d'attributs 
 */	
  public Terminal( String chaine, TypeUL type ) {   // arguments possibles
    this.chaine = chaine;
    this.type = type;
  }

}
