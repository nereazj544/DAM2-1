package hilos.Piscina;

public class Submarinista extends Thread {
    private static int contador = 1;
    private PiscinaOlimpica piscina;

    public Submarinista(PiscinaOlimpica piscina) {
        this.piscina = piscina;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String nombre = "Submarinista" + contador++;
                piscina.bucear(nombre);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
