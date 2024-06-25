package hilos.globos;

import java.util.Random;

public class HinchaGlobos extends Thread {
    private static final int MAX_HINCHANDO = 3;
    private AlmacenGlobos almacen;
    private Random random;

    public HinchaGlobos(AlmacenGlobos almacen) {
        this.almacen = almacen;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            int numGlobo = almacen.entregarGlobo();
            if (numGlobo != -1) {
                System.out.println("GLOBO " + numGlobo + " ENTREGADO A " + getName());
                while (almacen.getVolumen(numGlobo) < almacen.getMaxVolumen()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    almacen.hincharGlobo(numGlobo);
                }
            } else {
                break;
            }
        }
    }
}

