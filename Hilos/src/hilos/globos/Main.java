package hilos.globos;

public class Main {
    public static void main(String[] args) {
        AlmacenGlobos almacen = new AlmacenGlobos();
        HinchaGlobos[] hinchas = new HinchaGlobos[5];
        PinchaGlobos[] pinchas = new PinchaGlobos[5];

        for (int i = 0; i < hinchas.length; i++) {
            hinchas[i] = new HinchaGlobos(almacen);
            hinchas[i].setName("HG" + (i + 1));
            hinchas[i].start();
        }

        for (int i = 0; i < pinchas.length; i++) {
            pinchas[i] = new PinchaGlobos(almacen);
            pinchas[i].setName("PG" + (i + 1));
            pinchas[i].start();
        }
    }
}
