package hilos.FilosofosChinos;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Filosofo extends Thread {
    private String nombre;
    private Semaphore palilloIzquierdo;
    private Semaphore palilloDerecho;
    private Random random;

    public Filosofo(String nombre, Semaphore palilloIzquierdo, Semaphore palilloDerecho) {
        this.nombre = nombre;
        this.palilloIzquierdo = palilloIzquierdo;
        this.palilloDerecho = palilloDerecho;
        this.random = new Random();
    }

    private void pensar() throws InterruptedException {
        System.out.println(nombre + " está pensando...");
        Thread.sleep(random.nextInt(1000)); // Tiempo aleatorio pensando
    }

    private void esperar() throws InterruptedException {
        System.out.println(nombre + " está esperando a los palillos...");

        palilloIzquierdo.acquire();
        System.out.println(nombre + " ha cogido el palillo izquierdo.");
        palilloDerecho.acquire();
        System.out.println(nombre + " ha cogido el palillo derecho.");
    }

    private void comer() throws InterruptedException {
        System.out.println(nombre + " está comiendo...");
        Thread.sleep(random.nextInt(1000)); // Tiempo aleatorio comiendo

        // Liberar los palillos
        palilloIzquierdo.release();
        palilloDerecho.release();
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                esperar();
                comer();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}