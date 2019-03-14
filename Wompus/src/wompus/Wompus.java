/*
 Detta program Ã¤r ett spel kallat "Wompus".  
 MÃ¥let med spelet Ã¤r att ta sig igenom en grotta och hitta guldet medans man 
 undviker olika fÃ¤llor och monster. 
 Programmet Ã¤r skrivet av Fredrik Niemi. 
 */
package wompus;

import java.util.Scanner;

public class Wompus {

    static Scanner input = new Scanner(System.in);
    static int y = 2;
    static int x = 1;                                                           //Anger start positionen för spelaren i y och x värde. 
    static int pil = 1;                                                         //Anger att spelaren har 1 pil. 
    static boolean monsteralive = true;                                         //Anger om monstret lever eller ej. 
    static char[][] grotta = { //Grottans layout.
        {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
        {'X', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'X'},
        {'X', 'S', 'O', 'H', 'O', 'M', 'H', 'G', 'O', 'X'},
        {'X', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
        {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}};

    static int guld = 0;                                                        //Anger om spelaren har guldet med utgångspunkten "0" vilket betyder nej.
    static boolean alive = true;                                                //Anger om spelaren lever eller ej med utgångspunkten true. 

    public static void main(String[] args) {
        System.out.println("Welcome to the game Wompus.");
        System.out.println("You find yourself in a dark cave.");

        while (alive = true) {                                                  //En while loop som utförs medans alive är "true". 
            koll(y, x);                                                         //Anropar metoden "koll". 
            if (pil == 1) {                                                      //Om variabeln pil är "1" ger den spelaren möjlgiheten att skjuta en pil.
                System.out.println("Yo use your arrow, press A.");
            }
            System.out.print("Choose direction n (north), s (south), e (east), w (west): ");
            String choice = input.nextLine();                                   //Läser in spelarens val av riktning.

            if (choice.equalsIgnoreCase("n")) {                                 //Utför olika kommandom beroende på inamtning av riktning. 
                position(x, y - 1);                                             //Minskar variabeln "y" med 1 får att röra sig norr.

            } else if (choice.equalsIgnoreCase("s")) {
                position(x, y + 1);                                             //Ã–kar variabeln "y" med 1 får att röra sig söder. 

            } else if (choice.equalsIgnoreCase("w")) {
                position(x - 1, y);                                             //Minskar variabeln "x" med 1 fÃ¶r att röra sig väster.

            } else if (choice.equalsIgnoreCase("e")) {
                position(x + 1, y);                                             //Ã–kar variabeln "x" med 1 fÃ¶r att röra sig öster. 
            } else if (pil == 1 && choice.equalsIgnoreCase("a")) {              //Frågar om spelaren vill anvÃ¤nda pilen om variabeln "pil" är 1.
                pil();                                                          //Anropar metoden "pil". 
            } else {
                System.out.println("Invalid input!");                        //Om spelaren matar in en riktning som inte finns skriver programmet ut att det är en felaktig riktning.
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
                rutaO(y, x);                                                    //Anroper en metod om spelaren står på 'O'.
                break;

            case 'X':
                rutaX(y, x);                                                    //Anropar en metod om spelaren står på 'X'.               
                break;

            case 'H':                                                           // Anropar en metod om spelaren står på 'H'.
                rutaH(y, x);
                break;

            case 'G':                                                           // Anropar en metod om spelaren står på 'G'.
                rutaG(y, x);
                break;

            case 'M':                                                           // Anropar en metod om spelaren står på 'M'.
                rutaM(y, x);
                break;

            case 'S':                                                           // Anropar en metod om spelaren står på 'S'.
                rutaS(y, x);
                break;
            
            case 'T':
                rutaS(y, x);
                break; 
        }

    }

    static void koll(int y, int x) {                                            //Kollar om spelarenär nära en vägg och skriver ut att det finne en vägg nära och i vilken riktning den finns. 
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

    static void rutaO(int yO, int xO) {                                         //Säger att om spelaren hamnar på en ruta "O" ska inget hända.
        y = yO;
        x = xO;

    }

    static void rutaX(int yX, int xX) {                                         //Om spelaren går på ett "X" skriver programmet ut att en vägg är i vägen.
        System.out.println(" ");
        System.out.println("There's a wall in the way!");
    }

    static void rutaH(int yH, int xH) {                                         //Om spelaren går på ett "H" skriver programmet ut att du dör och avslutar sedan spelet.
        y = yH;
        x = xH;
        System.out.println(" ");
        System.out.println("You fell in to a hole and died. Game over!");
        System.exit(0);
    }

    static void rutaG(int yG, int xG) {                                         //Om spelaren går på "G" skriver programmet ut att du plocakt upp guldet och sätter variabeln "guld" till 1. 
        y = yG;
        x = xG;
        System.out.println(" ");
        System.out.println("You found the gold! Now find your way back!");
        guld = 1;
    }

    static void rutaM(int yM, int xM) {                                         //Om spelaren går på "M" skriver programmet ut att monstret åt upp dig och avslutar spelet.
        if (monsteralive = true) {                                               //Om monstret lever utförs följande operationer. 
            y = yM;
            x = xM;
            System.out.println(" ");
            System.out.println("The monster ate you. Game over!");
            System.exit(0);
        }
    }

    static void rutaS(int yS, int xS) {                                         //Om spelaren går till "S" och variabeln "guld" är 1 skriver programmet ut att du vunnit spelet och avslutas sedan. 
        y = yS;
        x = xS;
        if (guld == 1) {
            System.out.println(" ");
            System.out.println("Congratulations! You found the gold and managed to escape the cave!");
            System.exit(0);

        }
    }

    static void monster(int yM, int xM) {                                       //Kollar om ett monster är nära och skriver ut att spelaren känner en stank. 
            if (y == 2 && x == 4 || y == 1 && x == 5 || y == 3 && x == 5) {
                System.out.println("You can feel an awful smell.");
                System.out.println(" ");
            }
        

    }

    static void holes(int yH, int XH) {                                         //Kollar om ett hål är nära och skriver ut att spelaren känner en svag vindpust. 
        if (y == 1 && x == 3 || y == 3 && x == 3 || y == 2 && x == 2
                || y == 2 && x == 4 || y == 2 && x == 5 || y == 2 && x == 7
                || y == 3 && x == 6) {
            System.out.println("You feel a breeze.");
            System.out.println(" ");

        }

    }

    static void pil() {                                                         //Metod får pilen
        System.out.println(" ");
        if (pil == 1) {                                                         //Om du har pilen kan du välja en riktning att skjuta i
            System.out.print("Choose which direction you want to shoot the arrow; n, s, o, w:");
            String direction = input.nextLine();
            if (direction.equalsIgnoreCase("n") && grotta[y - 1][x] == 'M') {   //Om pilen hamnar på ett "M" skrivs en text att monstret är dött. 
                System.out.println("You hear a muffled roar.");
                monsteralive = false;                                           //Anger att monstret ärr dött
                grotta[2][5] = 'O';                                             //Gör om platsen "M" till ett "O" som är ofarligt.
                pil = 0;                                                        //Anger att spelaren inte har någon pil längre. 
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
