package app5;

/** @author Ahmed Khoumsi */

/** Cette classe effectue l'analyse lexicale
 */
public class AnalLex {

    // Attributs
//  etats: 0 = initial
//         1 = boucle
    int etat;
    int readPnt = 0;
    String file;
    String chaine;


    /** Constructeur pour l'initialisation d'attribut(s)
     */
    public AnalLex( String file ) {  // arguments possibles
        this.file = file;

    }

    /** resteTerminal() retourne :
     false  si tous les terminaux de l'expression arithmetique ont ete retournes
     true s'il reste encore au moins un terminal qui n'a pas ete retourne
     */
    public boolean resteTerminal( ) {
        return this.readPnt < this.file.length();
    }


    /** prochainTerminal() retourne le prochain terminal
     Cette methode est une implementation d'un AEF
     */
    public Terminal prochainTerminal( ) {
        chaine = "";
        etat = 0;

        while(resteTerminal()) {
            char c = file.charAt(readPnt);
            readPnt++;

            switch (etat) {
                case 0:
                    if(c == '+') {
                        return new Terminal("+", TypeUL.operateur);
                    }
                    else if(c == '0' || c == '1'){
                        etat = 1;
                        chaine += c;
                    }
                    else {
                        ErreurLex();
                        return new Terminal("Erreur", TypeUL.erreur);
                    }
                    break;

                case 1:
                    if(c == '0' || c == '1') {
                        chaine += c;
                    }
                    else {
                        readPnt--;
                        return new Terminal(chaine, TypeUL.operande);
                    }
                    break;
            }
        }
        return new Terminal(chaine, TypeUL.operande);
    }


    /** ErreurLex() envoie un message d'erreur lexicale
     */
    public void ErreurLex() {
        System.out.println("Erreur lexicale: Caractere " + readPnt);
    }


    //Methode principale a lancer pour tester l'analyseur lexical
    public static void main(String[] args) {
        String toWrite = "";
        System.out.println("Debut d'analyse lexicale");
        if (args.length == 0){
            args = new String [2];
            args[0] = "ExpArith.txt";
            args[1] = "ResultatLexical.txt";
        }

        Reader r = new Reader(args[0]);

        AnalLex lexical = new AnalLex(r.toString()); // Creation de l'analyseur lexical

        // Execution de l'analyseur lexical
        Terminal t = null;
        while(lexical.resteTerminal()){
            t = lexical.prochainTerminal();
            toWrite += t.chaine + "\n" ;  // toWrite contient le resultat
        }				   //    d'analyse lexicale
        System.out.println(toWrite); 	// Ecriture de toWrite sur la console
        Writer w = new Writer(args[1],toWrite); // Ecriture de toWrite dans fichier args[1]
        System.out.println("Fin d'analyse lexicale");
    }
}
