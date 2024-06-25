package hilos.Piscina;

import java.util.Random;

public class Nadador extends Thread {
    private static final String[] TIPOS = {"Hombre", "Mujer", "Nino", "Nina"};
    private static int contador = 1;
    private PiscinaOlimpica piscina;

    public Nadador(PiscinaOlimpica piscina) {
        this.piscina = piscina;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String nombre = "Nadador" + contador++;
                String tipo = TIPOS[new Random().nextInt(TIPOS.length)];
                piscina.nadar(nombre, tipo);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

