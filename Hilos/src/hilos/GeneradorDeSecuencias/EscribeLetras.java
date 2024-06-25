package hilos.GeneradorDeSecuencias;

class EscribeLetras extends Thread {
    private static final Object lock = new Object();
    private static int turno = 0;

    private String patron;
    private char letra;
    private int repeticiones;

    public EscribeLetras(String patron, char letra, int repeticiones) {
        this.patron = patron;
        this.letra = letra;
        this.repeticiones = repeticiones;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                while (turno != letra - 'A') {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 0; i < repeticiones; i++) {
                    System.out.print(letra);
                    try {
                        Thread.sleep(500); // Medio segundo entre cada letra
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                turno = (turno + 1) % patron.length();
                lock.notifyAll();
            }
        }
    }
}
