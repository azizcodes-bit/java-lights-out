import java.util.Scanner;

public class Spiel {
    public static int level = 1;
    public static int versuche = 0;

    public static void main(String[] args) {
        System.out.println("Herzlich willkommen zu Licht aus!");
        level = 1;
        versuche = 0;
        boolean[][] feld = new boolean[5][5];
        Spielfeld spielfeld = new Spielfeld(feld);
        Scanner sc = new Scanner(System.in);
        spielfeld.initialisieren(feld, level);
        while (true) {
            System.out.println("Level " + level);
            System.out.println(spielfeld);
            System.out.println("A1 bis E5; XL = Neustart; XX = Beenden:");
            String s = sc.nextLine().trim().toUpperCase();
            if (!testeEingabe(s)) {
                System.out.println("Ungueltige Eingabe.");
                continue;
            }
            if (s.equals("XX")) {
                break;
            }
            if (s.equals("XL")) {
                spielfeld.initialisieren(feld, level);
                versuche = 0;
                continue;
            }
            int[] position = berechneIndizes(s);
            spielfeld.drueckeSchalter(position[0], position[1]);
            versuche++;
            if (spielfeld.geloest()) {
                System.out.println(spielfeld);
                System.out.println("Geloest mit " + versuche + " Schalterdruecken!");
                level++;
                versuche = 0;
                spielfeld.initialisieren(feld, level);
            }
        }
        System.out.println("Du hast das Spiel auf Level " + level + " beendet.");
        sc.close();
    }

    public static boolean testeEingabe(String s) {
        if (s == null || s.length() != 2) {
            return false;
        }
        return s.equals("XX") || s.equals("XL")
            || (s.charAt(0) >= 'A' && s.charAt(0) <= 'E'
            && s.charAt(1) >= '1' && s.charAt(1) <= '5');
    }

    public static int[] berechneIndizes(String s) {
        return new int[] {s.charAt(0) - 'A', s.charAt(1) - '1'};
    }
}
