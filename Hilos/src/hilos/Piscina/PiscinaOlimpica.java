package hilos.Piscina;
import java.util.Random;
import java.util.concurrent.Semaphore;
public class PiscinaOlimpica {
    private static final int NUM_CARRILES = 8;

    private Semaphore semaforoPiscina;
    private Semaphore semaforoHombres;
    private Semaphore semaforoMujeres;
    private Semaphore semaforoNinos;
    private Semaphore semaforoNinas;
    private Semaphore semaforoSubmarinistas;

    private int hombres;
    private int mujeres;
    private int ninos;
    private int ninas;
    private int submarinistas;

    public PiscinaOlimpica() {
        semaforoPiscina = new Semaphore(NUM_CARRILES, true); // Semáforo para controlar la disponibilidad de calles en la piscina
        semaforoHombres = new Semaphore(1, true);
        semaforoMujeres = new Semaphore(1, true);
        semaforoNinos = new Semaphore(1, true);
        semaforoNinas = new Semaphore(1, true);
        semaforoSubmarinistas = new Semaphore(2, true); // Semáforo para limitar la cantidad de submarinistas en la piscina

        hombres = 0;
        mujeres = 0;
        ninos = 0;
        ninas = 0;
        submarinistas = 0;
    }

    public void nadar(String nombre, String tipo) throws InterruptedException {
        semaforoPiscina.acquire();
        if (tipo.equals("Hombre")) {
            semaforoHombres.acquire();
            hombres++;
            semaforoHombres.release();
        } else if (tipo.equals("Mujer")) {
            semaforoMujeres.acquire();
            mujeres++;
            semaforoMujeres.release();
        } else if (tipo.equals("Nino")) {
            semaforoNinos.acquire();
            ninos++;
            semaforoNinos.release();
        } else if (tipo.equals("Nina")) {
            semaforoNinas.acquire();
            ninas++;
            semaforoNinas.release();
        }

        // Simulación de nado
        Thread.sleep(new Random().nextInt(31) + 50);

        if (tipo.equals("Hombre")) {
            semaforoHombres.acquire();
            hombres--;
            System.out.println(nombre + " sale de la piscina. Total: Hombres(" + hombres + "), Mujeres(" + mujeres + "), Niños(" + ninos + "), Niñas(" + ninas + "), Submarinistas(" + submarinistas + ")");
            semaforoHombres.release();
        } else if (tipo.equals("Mujer")) {
            semaforoMujeres.acquire();
            mujeres--;
            System.out.println(nombre + " sale de la piscina. Total: Hombres(" + hombres + "), Mujeres(" + mujeres + "), Niños(" + ninos + "), Niñas(" + ninas + "), Submarinistas(" + submarinistas + ")");
            semaforoMujeres.release();
        } else if (tipo.equals("Nino")) {
            semaforoNinos.acquire();
            ninos--;
            System.out.println(nombre + " sale de la piscina. Total: Hombres(" + hombres + "), Mujeres(" + mujeres + "), Niños(" + ninos + "), Niñas(" + ninas + "), Submarinistas(" + submarinistas + ")");
            semaforoNinos.release();
        } else if (tipo.equals("Nina")) {
            semaforoNinas.acquire();
            ninas--;
            System.out.println(nombre + " sale de la piscina. Total: Hombres(" + hombres + "), Mujeres(" + mujeres + "), Niños(" + ninos + "), Niñas(" + ninas + "), Submarinistas(" + submarinistas + ")");
            semaforoNinas.release();
        }

        semaforoPiscina.release();
    }

    public void bucear(String nombre) throws InterruptedException {
        semaforoSubmarinistas.acquire(2);
        submarinistas += 2;
        // Simulación de buceo
        Thread.sleep(new Random().nextInt(31) + 50);
        submarinistas -= 2;
        System.out.println(nombre + " sale de la piscina. Total: Hombres(" + hombres + "), Mujeres(" + mujeres + "), Niños(" + ninos + "), Niñas(" + ninas + "), Submarinistas(" + submarinistas + ")");
        semaforoSubmarinistas.release(2);
    }
}
