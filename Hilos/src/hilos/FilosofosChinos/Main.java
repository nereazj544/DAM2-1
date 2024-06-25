package hilos.FilosofosChinos;

import java.util.concurrent.Semaphore;

public class Main {
	public static void main(String[] args) {
        Semaphore[] palillos = new Semaphore[5];
        for (int i = 0; i < palillos.length; i++) {
            palillos[i] = new Semaphore(1); // Inicialmente, todos los palillos están disponibles
        }

        Filosofo[] filosofos = new Filosofo[5];
        filosofos[0] = new Filosofo("孔夫子", palillos[0], palillos[1]);
        filosofos[1] = new Filosofo("楊朱", palillos[1], palillos[2]);
        filosofos[2] = new Filosofo("荀子", palillos[2], palillos[3]);
        filosofos[3] = new Filosofo("商鞅", palillos[3], palillos[4]);
        filosofos[4] = new Filosofo("莊子", palillos[4], palillos[0]);

        for (Filosofo filosofo : filosofos) {
            filosofo.start();
        }
    }
}