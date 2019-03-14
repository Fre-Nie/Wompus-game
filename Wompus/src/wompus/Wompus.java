/*
 Detta program är ett spel kallat "Wompus".  
 Målet med spelet är att ta sig igenom en grotta och hitta guldet medans man 
 undviker olika fällor och monster. 
 Programmet är skrivet av Fredrik Niemi. 
 */
package wompus;

import java.util.Scanner;

public class Wompus {

    static Scanner input = new Scanner(System.in);
    static int y = 2;
    static int x = 1;                                                           //Anger start positionen f�r spelaren i y och x v�rde. 
    static int pil = 1;                                                         //Anger att spelaren har 1 pil. 
    static boolean monsteralive = true;                                         //Anger om monstret lever eller ej. 
    static char[][] grotta = { //Grottans layout.
        {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
        {'X', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'X'},
        {'X', 'S', 'O', 'H', 'O', 'M', 'H', 'G', 'O', 'X'},
        {'X', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
        {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}};

    static int guld = 0;                                                        //Anger om spelaren har guldet med utg�ngspunkten "0" vilket betyder nej.
    static boolean alive = true;                                                //Anger om spelaren lever eller ej med utg�ngspunkten true. 

    public static void main(String[] args) {
        System.out.println("Welcome to the game Wompus.");
        System.out.println("You find yourself in a dark cave.");

        while (alive = true) {                                                  //En while loop som utf�rs medans alive �r "true". 
            koll(y, x);                                                         //Anropar metoden "koll". 
            if (pil == 1) {                                                      //Om variabeln pil �r "1" ger den spelaren m�jlgiheten att skjuta en pil.
                System.out.println("Yo use your arrow, press A.");
            }
            System.out.print("Choose direction n (north), s (south), e (east), w (west): ");
            String choice = input.nextLine();                                   //L�ser in spelarens val av riktning.

            if (choice.equalsIgnoreCase("n")) {                                 //Utf�r olika kommandom beroende p� inamtning av riktning. 
                position(x, y - 1);                                             //Minskar variabeln "y" med 1 f�r att r�ra sig norr.

            } else if (choice.equalsIgnoreCase("s")) {
                position(x, y + 1);                                             //Ökar variabeln "y" med 1 f�r att r�ra sig s�der. 

            } else if (choice.equalsIgnoreCase("w")) {
                position(x - 1, y);                                             //Minskar variabeln "x" med 1 för att r�ra sig v�ster.

            } else if (choice.equalsIgnoreCase("e")) {
                position(x + 1, y);                                             //Ökar variabeln "x" med 1 för att r�ra sig �ster. 
            } else if (pil == 1 && choice.equalsIgnoreCase("a")) {              //Fr�gar om spelaren vill använda pilen om variabeln "pil" �r 1.
                pil();                                                          //Anropar metoden "pil". 
            } else {
                System.out.println("Invalid input!");                        //Om spelaren matar in en riktning som inte finns skriver programmet ut att det �r en felaktig riktning.
            }

            System.out.println(" ");
              if (monsteralive == true) {
            monster(x, y);                                                      //Anropar metoden "monster".
              }
            holes(x, y);                                                        //Anropar metoden "holes". 
        }

    }

    static void position(int x, int y) {
        char plats = grotta[y][x];                                              //Tar in spelarens position. 

        switch (plats) {
            case 'O':
                rutaO(y, x);                                                    //Anroper en metod om spelaren st�r p� 'O'.
                break;

            case 'X':
                rutaX(y, x);                                                    //Anropar en metod om spelaren st�r p� 'X'.               
                break;

            case 'H':                                                           // Anropar en metod om spelaren st�r p� 'H'.
                rutaH(y, x);
                break;

            case 'G':                                                           // Anropar en metod om spelaren st�r p� 'G'.
                rutaG(y, x);
                break;

            case 'M':                                                           // Anropar en metod om spelaren st�r p� 'M'.
                rutaM(y, x);
                break;

            case 'S':                                                           // Anropar en metod om spelaren st�r p� 'S'.
                rutaS(y, x);
                break;
            
            case 'T':
                rutaS(y, x);
                break; 
        }

    }

    static void koll(int y, int x) {                                            //Kollar om spelaren�r n�ra en v�gg och skriver ut att det finne en v�gg n�ra och i vilken riktning den finns. 
        if (grotta[y - 1][x] == 'X') {
            System.out.println("There's a wall to the north");
        }
        if (grotta[y + 1][x] == 'X') {
            System.out.println("There's a wall to the south");
        }
        if (grotta[y][x + 1] == 'X') {
            System.out.println("There's a wall to the east");
        }
        if (grotta[y][x - 1] == 'X') {
            System.out.println("There's a wall to the west");
        }

    }

    static void rutaO(int yO, int xO) {                                         //S�ger att om spelaren hamnar p� en ruta "O" ska inget h�nda.
        y = yO;
        x = xO;

    }

    static void rutaX(int yX, int xX) {                                         //Om spelaren g�r p� ett "X" skriver programmet ut att en v�gg �r i v�gen.
        System.out.println(" ");
        System.out.println("There's a wall in the way!");
    }

    static void rutaH(int yH, int xH) {                                         //Om spelaren g�r p� ett "H" skriver programmet ut att du d�r och avslutar sedan spelet.
        y = yH;
        x = xH;
        System.out.println(" ");
        System.out.println("You fell in to a hole and died. Game over!");
        System.exit(0);
    }

    static void rutaG(int yG, int xG) {                                         //Om spelaren g�r p� "G" skriver programmet ut att du plocakt upp guldet och s�tter variabeln "guld" till 1. 
        y = yG;
        x = xG;
        System.out.println(" ");
        System.out.println("You found the gold! Now find your way back!");
        guld = 1;
    }

    static void rutaM(int yM, int xM) {                                         //Om spelaren g�r p� "M" skriver programmet ut att monstret �t upp dig och avslutar spelet.
        if (monsteralive = true) {                                               //Om monstret lever utf�rs f�ljande operationer. 
            y = yM;
            x = xM;
            System.out.println(" ");
            System.out.println("The monster ate you. Game over!");
            System.exit(0);
        }
    }

    static void rutaS(int yS, int xS) {                                         //Om spelaren g�r till "S" och variabeln "guld" �r 1 skriver programmet ut att du vunnit spelet och avslutas sedan. 
        y = yS;
        x = xS;
        if (guld == 1) {
            System.out.println(" ");
            System.out.println("Congratulations! You found the gold and managed to escape the cave!");
            System.exit(0);

        }
    }

    static void monster(int yM, int xM) {                                       //Kollar om ett monster �r n�ra och skriver ut att spelaren k�nner en stank. 
            if (y == 2 && x == 4 || y == 1 && x == 5 || y == 3 && x == 5) {
                System.out.println("You can feel an awful smell.");
                System.out.println(" ");
            }
        

    }

    static void holes(int yH, int XH) {                                         //Kollar om ett h�l �r n�ra och skriver ut att spelaren k�nner en svag vindpust. 
        if (y == 1 && x == 3 || y == 3 && x == 3 || y == 2 && x == 2
                || y == 2 && x == 4 || y == 2 && x == 5 || y == 2 && x == 7
                || y == 3 && x == 6) {
            System.out.println("You feel a breeze.");
            System.out.println(" ");

        }

    }

    static void pil() {                                                         //Metod f�r pilen
        System.out.println(" ");
        if (pil == 1) {                                                         //Om du har pilen kan du v�lja en riktning att skjuta i
            System.out.print("Choose which direction you want to shoot the arrow; n, s, o, w:");
            String direction = input.nextLine();
            if (direction.equalsIgnoreCase("n") && grotta[y - 1][x] == 'M') {   //Om pilen hamnar p� ett "M" skrivs en text att monstret �r d�tt. 
                System.out.println("You hear a muffled roar.");
                monsteralive = false;                                           //Anger att monstret �rr d�tt
                grotta[2][5] = 'O';                                             //G�r om platsen "M" till ett "O" som �r ofarligt.
                pil = 0;                                                        //Anger att spelaren inte har n�gon pil l�ngre. 
            } else if (direction.equalsIgnoreCase("s") && grotta[y + 1][x] == 'M') {
                System.out.println("You hear a muffled roar.");
                monsteralive = false;
                grotta[2][5] = 'O';
                pil = 0;
            } else if (direction.equalsIgnoreCase("o") && grotta[y][x + 1] == 'M') {
                System.out.println("You hear a muffled roar.");
                monsteralive = false;
                grotta[2][5] = 'O';
            } else if (direction.equalsIgnoreCase("v") && grotta[y][x - 1] == 'M') {
                System.out.println("You hear a muffled roar.");
                monsteralive = false;
                grotta[2][5] = 'O';
                pil = 0;
            } else {
                System.out.println("You don't hear anything.");
                pil = 0;
            }
        }
    }
}
