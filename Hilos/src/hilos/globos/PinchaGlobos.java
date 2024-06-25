package hilos.globos;

import java.util.Random;

public class PinchaGlobos extends Thread {
    private AlmacenGlobos almacen;
    private Random random;

    public PinchaGlobos(AlmacenGlobos almacen) {
        this.almacen = almacen;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(random.nextInt(10) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int numGlobo = almacen.entregarGlobo();
            if (numGlobo != -1) {
                almacen.pincharGlobo(numGlobo);
            } else {
                break;
            }
        }
    }
}