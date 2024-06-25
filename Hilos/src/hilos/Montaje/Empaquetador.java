package hilos.Montaje;

public class Empaquetador extends Thread {
    private CadenaMontaje cadena;
    private int tipoRobot;

    public Empaquetador(CadenaMontaje cadena, int tipoRobot) {
        this.cadena = cadena;
        this.tipoRobot = tipoRobot;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Producto producto = cadena.retirarProducto(tipoRobot); // Retira un producto de la cadena de montaje
                if (producto != null) {
                    System.out.println("Producto de tipo " + tipoRobot + " empaquetado. Total: " + cadena.getTotalProductosEmpaquetados());
                }
                Thread.sleep(1500); // Simula el tiempo de empaquetado
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
