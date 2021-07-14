package app5;

/** @author Ahmed Khoumsi */

import javax.lang.model.util.ElementScanner6;
import javax.management.Notification;

/** Cette classe effectue l'analyse syntaxique
 */
public class DescenteRecursive {

    // Attributs
    AnalLex lexique;
    Terminal terminal;
    Terminal lastTerminal;


    /** Constructeur de DescenteRecursive :
     - recoit en argument le nom du fichier contenant l'expression a analyser
     - pour l'initalisation d'attribut(s)
     */
    public DescenteRecursive(String in) {
        Reader r = new Reader(in);
        lexique = new AnalLex(r.toString());
    }


    /** AnalSynt() effectue l'analyse syntaxique et construit l'AST.
     *    Elle retourne une reference sur la racine de l'AST construit
     */
    public ElemAST AnalSynt( ) {
        terminal = lexique.prochainTerminal();

        ElemAST elemAST = null;

        try {
            elemAST = X();
            if(terminal.chaine.equals(")")) {
                throw new SyntaxException("Il manque une parenthèse ouvrante à l'expression");
            }
        } catch (SyntaxException se) {
            System.err.println(se.toString());
            System.exit(2);
        }
        return elemAST;
    }


    // Methode pour chaque symbole non-terminal de la grammaire retenue
    private ElemAST X() throws SyntaxException{
        ElemAST n1, n2;
        n1 = Y();

        if(terminal.type == TypeUL.operateur) {
            if(terminal.chaine.equals("+")) {
                lastTerminal = terminal;
                terminal = lexique.prochainTerminal();
                n2 = X();
                n1 = new NoeudAdditionAST(n1, n2, "+");
            }
            else if(terminal.chaine.equals("-")) {
                lastTerminal = terminal;
                terminal = lexique.prochainTerminal();
                n2 = X();
                n1 = new NoeudSoustractionAST(n1, n2, "-");
            } /*else {
            throw new SyntaxException("Le(s) caractère(s) " + terminal.chaine +" n'est pas supporter");
        }*/
        } else if(!terminal.chaine.equals("")) {
            if(terminal.type == TypeUL.operande && lastTerminal.type == TypeUL.operande) {
                throw new SyntaxException("L'ul '" + lastTerminal.chaine +
                        "' a la colonne:" + (lexique.readPnt-1) +  " est une opérande et elle est suivi d'une autre ul de type opérande '" + terminal.chaine + "'");
            } else if(terminal.chaine.equals("(") && lastTerminal.type == TypeUL.operande) {
                throw new SyntaxException("L'ul '" + lastTerminal.chaine +
                        "' a la colonne:" + (lexique.readPnt-1) +  " est une opérande et elle est directement suivi de l'ul '" + terminal.chaine + "'");
            } else if(lastTerminal.chaine.equals(")") && terminal.type == TypeUL.operande) {
                throw new SyntaxException("L'ul '" + lastTerminal.chaine +
                        "' a la colonne:" + (lexique.readPnt-1) +  " est une parenthèse et elle est directement suivi de l'ul '" + terminal.chaine + "'");
            }
        }

        return n1;
    }

    private ElemAST Y() throws SyntaxException{
        ElemAST n1, n2;
        n1 = Z();
        if(terminal.type == TypeUL.operateur) {
            if(terminal.chaine.equals("*")) {
                lastTerminal = terminal;
                terminal = lexique.prochainTerminal();
                n2 = Y();
                n1 = new NoeudMultiplicationAST(n1, n2, "*");
            }
            else if(terminal.chaine.equals("/")) {
                lastTerminal = terminal;
                terminal = lexique.prochainTerminal();
                n2 = Y();
                n1 = new NoeudDivisionAST(n1, n2, "/");
            }
        }
        return n1;
    }

    private ElemAST Z() throws SyntaxException {
        ElemAST elemAST = null;

        if (terminal.type == TypeUL.operande) {
            char c = terminal.chaine.charAt(0);

            if(c >= 48 && c <= 57) {
                elemAST = new FeuilleNombreAST(terminal);
            } else {
                elemAST = new FeuilleVariableAST(terminal);
            }
            lastTerminal = terminal;
            terminal = lexique.prochainTerminal();
        } else if (terminal.type == TypeUL.paranthese && terminal.chaine.equals("(")) {
            lastTerminal = terminal;
            terminal = lexique.prochainTerminal();

            elemAST = X();

            if(terminal.chaine.equals(")")) {
                lastTerminal = terminal;
                terminal = lexique.prochainTerminal();
            } else {
                throw new SyntaxException("Il manque une parenthèse fermante à l'expression");
            }
        } else {
            throw new SyntaxException("L'ul '"  + lastTerminal.chaine +"' a la colonne:" + (lexique.readPnt-1) + " n'est pas suivi d'une opérande, mais de l'ul '"
            + terminal.chaine + "'");
        }

        return elemAST;
    }


    /** ErreurSynt() envoie un message d'erreur syntaxique
     */
    public void ErreurSynt()
    {
        System.out.println("Erreur syntaxique: Terminal ");
    }



    //Methode principale a lancer pour tester l'analyseur syntaxique
    public static void main(String[] args) {
        String toWriteLect = "";
        String toWriteEval = "";

        System.out.println("Debut d'analyse syntaxique");
        if (args.length == 0){
            args = new String [2];
            args[0] = "ExpArith.txt";
            args[1] = "ResultatSyntaxique.txt";
        }
        DescenteRecursive dr = new DescenteRecursive(args[0]);
        try {
            ElemAST RacineAST = dr.AnalSynt();
            toWriteLect += "Lecture de l'AST trouve : " + RacineAST.LectAST() + "\n";
            System.out.println(toWriteLect);
            toWriteEval += "Evaluation de l'AST trouve : " + RacineAST.EvalAST() + "\n";
            System.out.println(toWriteEval);
            Writer w = new Writer(args[1],toWriteLect+toWriteEval); // Ecriture de toWrite
            // dans fichier args[1]
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            System.exit(51);
        }
        System.out.println("Analyse syntaxique terminee");
    }

}

