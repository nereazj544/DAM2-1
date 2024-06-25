package hilos.globos;

public class AlmacenGlobos {
    private static final int MAX_GLOBOS = 10;
    private static final int MAX_VOL = 5;

    private int[] globos;
    private int[] volumenes;
    private int globosEntregados;

    public AlmacenGlobos() {
        globos = new int[MAX_GLOBOS];
        volumenes = new int[MAX_GLOBOS];
        globosEntregados = 0;
    }

    public synchronized int entregarGlobo() {
        if (globosEntregados < MAX_GLOBOS) {
            int numGlobo = globosEntregados + 1;
            globos[globosEntregados] = numGlobo;
            volumenes[globosEntregados] = 0;
            globosEntregados++;
            return numGlobo;
        }
        return -1;
    }

    public synchronized int getVolumen(int numGlobo) {
        for (int i = 0; i < globos.length; i++) {
            if (globos[i] == numGlobo) {
                return volumenes[i];
            }
        }
        return -1;
    }

    public synchronized void hincharGlobo(int numGlobo) {
        for (int i = 0; i < globos.length; i++) {
            if (globos[i] == numGlobo) {
                volumenes[i]++;
                System.out.println("GLOBO " + numGlobo + " VOLUMEN " + volumenes[i]);
                if (volumenes[i] > MAX_VOL) {
                    System.out.println("GLOBO " + numGlobo + " ESTALLA");
                    globos[i] = 0;
                    volumenes[i] = 0;
                }
                return;
            }
        }
    }

    public synchronized void pincharGlobo(int numGlobo) {
        for (int i = 0; i < globos.length; i++) {
            if (globos[i] == numGlobo) {
                System.out.println("GLOBO " + numGlobo + " PINCHADO POR PG" + Thread.currentThread().getName().substring(2));
                globos[i] = 0;
                volumenes[i] = 0;
                return;
            }
        }
    }

    public int getMaxVolumen() {
        return MAX_VOL;
    }
}