package hilos.Piscina;

public class Main {
	public static void main(String[] args) {
        PiscinaOlimpica piscina = new PiscinaOlimpica();

        Nadador[] nadadores = new Nadador[10];
        Submarinista[] submarinistas = new Submarinista[10];

        for (int i = 0; i < nadadores.length; i++) {
            nadadores[i] = new Nadador(piscina);
            nadadores[i].start();
        }

        // Inicialización de submarinistas
        for (int i = 0; i < submarinistas.length; i++) {
            submarinistas[i] = new Submarinista(piscina);
            submarinistas[i].start();
        }
        		
	}
}
