package app5;

/** @author Ahmed Khoumsi */

/** Cette classe effectue l'analyse lexicale
 */
public class AnalLex {

// Attributs:
//  etats: 0 = initial
//         1 = boucle nombre
//         2 = Première majuscule
//         3 = boucle lettres
//         4 = détection _

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
    public Terminal prochainTerminal( ){
        try {
            chaine = "";
            etat = 0;

            while(resteTerminal()) {
                char c = file.charAt(readPnt);
                readPnt++;

                switch (etat) {
                    case 0:
                        if(c == '+' || c == '-' || c == '*' || c == '/') {
                            return new Terminal(String.valueOf(c), TypeUL.operateur);
                        }
                        else if(c == '(' || c == ')') {
                            return new Terminal(String.valueOf(c), TypeUL.paranthese);
                        }
                        else if(c >= 48 && c <= 57){ //ASCII 48 = '0', 57 = '9'
                            etat = 1;
                            chaine += c;
                        }
                        else if(c >= 65 && c <= 90){  //65 = 'A', 90 = 'Z'
                            etat = 2;
                            chaine += c;
                        }
                        else {
                            throw new LexicalException("(Caractere:" + "'" + c + "' colonne:" + readPnt + "): Caratere non-supporte");
                        }
                        break;

                    case 1:
                        if(c >= 48 && c <= 57){ //ASCII 48 = '0', 57 = '9'
                            chaine += c;
                        }
                        else {
                            readPnt--;
                            return new Terminal(chaine, TypeUL.operande);
                        }
                        break;

                    case 2:
                        if((c >= 65 && c <= 90) || (c >= 97 && c <= 122)){  //65 = 'A', 90 = 'Z', 97 = 'a', 122 = 'z'
                            etat = 3;
                            chaine += c;
                        }
                        else if(c == '_'){
                            etat = 4;
                            chaine += c;
                        }
                        else {
                            readPnt--;
                            return new Terminal(chaine, TypeUL.operande);
                        }
                        break;

                    case 3:
                        if((c >= 65 && c <= 90) || (c >= 97 && c <= 122)){  //65 = 'A', 90 = 'Z', 97 = 'a', 122 = 'z'
                            chaine += c;
                        }
                        else if(c == '_'){
                            etat = 4;
                            chaine += c;
                        }
                        else {
                            readPnt--;
                            return new Terminal(chaine, TypeUL.operande);
                        }
                        break;

                    case 4:
                        if((c >= 65 && c <= 90) || (c >= 97 && c <= 122)){  //65 = 'A', 90 = 'Z', 97 = 'a', 122 = 'z'
                            chaine += c;
                            etat = 3;
                        }
                        else {
                            //ErreurLex();
                            throw new LexicalException("(Caractere:" + "'" + c + "' colonne:" + readPnt + "): Deux underscores de suite.");
                        }
                        break;
                }
                if(!resteTerminal() && etat == 4) {
                    //ErreurLex();
                    throw new LexicalException("(Caractere:" + "'" + c + "' colonne:" + readPnt + "): Une variable ne peut pas finir par un underscore.");
                }
            }
        }
        catch (LexicalException le) {
            System.err.println(le.getMessage());
            System.exit(1);
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
