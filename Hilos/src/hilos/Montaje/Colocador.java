package hilos.Montaje;

public class Colocador extends Thread {
    private CadenaMontaje cadena;

    public Colocador(CadenaMontaje cadena) {
        this.cadena = cadena;
    }

    @Override
    public void run() {
        try {
            int contador = 1;
            while (true) {
                Thread.sleep(1000); // Simula el tiempo de colocación de un producto
                int tipo = (int) (Math.random() * 3) + 1; // Genera aleatoriamente el tipo de producto
                cadena.colocarProducto(new Producto(tipo)); // Coloca el producto en la cadena de montaje
                contador++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}