package old;

public class Spawner {
    private int GelbAnzahl, RotAnzahl, BlauAnzahl, waveCount;
    private boolean zielWelle, mapEnde;
    //UI_gamepanel gp;

    public Spawner() {
        //this.gp = gp;
        zielWelle = false;
        mapEnde = false;
    }

    public void setBlauAnzahl(int blauAnzahl) {
        BlauAnzahl = blauAnzahl;
    }

    public int getBlauAnzahl() {
        return BlauAnzahl;
    }

    public void setGelbAnzahl(int gelbAnzahl) {
        GelbAnzahl = gelbAnzahl;
    }

    public int getGelbAnzahl() {
        return GelbAnzahl;
    }

    public void setRotAnzahl(int rotAnzahl) {
        RotAnzahl = rotAnzahl;
    }

    public int getRotAnzahl() {
        return RotAnzahl;
    }

    public void start() {
        while (!zielWelle) {

            for (int i = 0; i < getBlauAnzahl(); ) {
                //Gegner gg = new Gegner(gp);
                for (int j = 0; j < getGelbAnzahl(); ) {
                    //GegnerY gy = new GegnerY(gp);
                    for (int k = 0; k < getRotAnzahl(); ) {
                        //GegnerR gr = new GegnerR(gp);
                    }

                }

                while (!mapEnde) {

                   // gg.move();
                    //gy.move();
                    //gr.move();

                    if (mapEnde) {
                        waveCount++;
                        setRotAnzahl(+1);
                        setBlauAnzahl(+3);
                        setGelbAnzahl(+5);
                    }
                }


            }
        }
    }
}
