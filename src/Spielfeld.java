import java.util.Random;

public class Spielfeld {
    private static final int N = 5;
    private boolean[][] feld;
    Random r = new Random();

    Spielfeld(boolean[][] feld) { this.feld = feld; }

    public boolean[][] getFeld() { return feld; }

    public String toString() {
        char buchstabe = 'A';
        String res = " ";
        for (int i = 1; i < N + 1; i++) res += " " + i;
        for (int i = 0; i < N; i++) {
            res += "\n";
            res += buchstabe;
            for (int j = 0; j < N; j++) {
                if (feld[i][j] == true) res += " " + '*';
                else if (feld[i][j] == false) res += " " + 'O';
            }
            buchstabe++;
        }
        return res;
    }

    public void drueckeSchalter(int z, int s) {
        if (0 <= z && z < N && 0 <= s && s < N) umschalten(z, s);
    }

    public boolean geloest() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (feld[i][j]) return false;
        return true;
    }

    public void umschalten(int z, int s) {
        feld[z][s] = !feld[z][s];
        if (z + 1 < N) feld[z + 1][s] = !feld[z + 1][s];
        if (z - 1 >= 0) feld[z - 1][s] = !feld[z - 1][s];
        if (s + 1 < N) feld[z][s + 1] = !feld[z][s + 1];
        if (s - 1 >= 0) feld[z][s - 1] = !feld[z][s - 1];
    }

    public boolean gewonnen() { return geloest(); }

    public void initialisieren(boolean[][] feld, int level) {
        this.feld = feld;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) feld[i][j] = false;
        int anzahl = (int) Math.min(25L, Math.max(1L, (long) level + 3));
        for (int i = 0; i < anzahl; i++)
            drueckeSchalter(r.nextInt(N), r.nextInt(N));
        if (geloest()) drueckeSchalter(r.nextInt(N), r.nextInt(N));
    }
}
