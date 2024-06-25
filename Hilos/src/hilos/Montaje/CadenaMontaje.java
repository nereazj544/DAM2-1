package hilos.Montaje;

import java.util.concurrent.Semaphore;

public class CadenaMontaje {
    private Semaphore mutex; // Semáforo para garantizar la exclusión mutua
    private Producto[] productos; // Arreglo para almacenar los productos en la cadena
    private int totalProductosEmpaquetados; // Contador para el total de productos empaquetados

    public CadenaMontaje(int capacidad) {
        mutex = new Semaphore(1); // Inicializa el semáforo con 1 para exclusión mutua
        productos = new Producto[capacidad]; // Inicializa el arreglo de productos
        totalProductosEmpaquetados = 0; // Inicializa el contador de productos empaquetados
    }

    // Método para colocar un producto en la cadena de montaje
    public void colocarProducto(Producto producto) throws InterruptedException {
        mutex.acquire(); // Adquiere el semáforo para exclusión mutua
        for (int i = 0; i < productos.length; i++) {
            if (productos[i] == null) { // Encuentra una posición libre en la cadena
                productos[i] = producto; // Coloca el producto en la posición libre
                System.out.println("Producto de tipo " + producto.getTipo() + " colocado en la posición " + i);
                mutex.release(); // Libera el semáforo
                return;
            }
        }
        mutex.release(); // Libera el semáforo si no encuentra posición libre
    }

    // Método para retirar un producto de la cadena de montaje y empaquetarlo
    public Producto retirarProducto(int tipoRobot) throws InterruptedException {
        mutex.acquire(); // Adquiere el semáforo para exclusión mutua
        for (int i = 0; i < productos.length; i++) {
            if (productos[i] != null && productos[i].getTipo() == tipoRobot) { // Encuentra un producto del tipo del robot empaquetador
                Producto producto = productos[i]; // Obtiene el producto
                productos[i] = null; // Elimina el producto de la cadena de montaje
                System.out.println("Producto de tipo " + tipoRobot + " retirado de la posición " + i);
                totalProductosEmpaquetados++; // Incrementa el contador de productos empaquetados
                mutex.release(); // Libera el semáforo
                return producto;
            }
        }
        mutex.release(); // Libera el semáforo si no encuentra un producto del tipo del robot empaquetador
        return null;
    }

    // Método para obtener el total de productos empaquetados
    public int getTotalProductosEmpaquetados() {
        return totalProductosEmpaquetados;
    }
}