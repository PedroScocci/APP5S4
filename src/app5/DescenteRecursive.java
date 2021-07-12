package app5;

/** @author Ahmed Khoumsi */

import javax.management.Notification;

/** Cette classe effectue l'analyse syntaxique
 */
public class DescenteRecursive {

    // Attributs
    AnalLex lexique;
    Terminal terminal;


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
        return E();
    }


    // Methode pour chaque symbole non-terminal de la grammaire retenue
    private ElemAST E() {
        ElemAST n1, n2;
        n1 = T();
        if(terminal.type == TypeUL.operateur) {
            terminal = lexique.prochainTerminal();
            n2 = E();
            n1 = new NoeudAST(n1, n2, "+");
        }
        return n1;
    }

    private ElemAST T() {
        FeuilleAST feuille = null;
        if(terminal.type == TypeUL.operande) {
            feuille = new FeuilleAST(terminal);
            terminal = lexique.prochainTerminal();
        }
        else {
            ErreurSynt();
        }
        return feuille;
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

