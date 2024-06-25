package hilos.Montaje;

public class Main {
    public static void main(String[] args) {
        CadenaMontaje cadena = new CadenaMontaje(10); // Crea una cadena de montaje con capacidad para 10 productos
        Colocador colocador = new Colocador(cadena); // Crea un hilo para el robot colocador
        Empaquetador empaquetador1 = new Empaquetador(cadena, 1); // Crea un hilo para el robot empaquetador de tipo 1
        Empaquetador empaquetador2 = new Empaquetador(cadena, 2); // Crea un hilo para el robot empaquetador de tipo 2
        Empaquetador empaquetador3 = new Empaquetador(cadena, 3); // Crea un hilo para el robot empaquetador de tipo 3

        colocador.start(); // Inicia el hilo del robot colocador
        empaquetador1.start(); // Inicia el hilo del robot empaquetador de tipo 1
        empaquetador2.start(); // Inicia el hilo del robot empaquetador de tipo 2
        empaquetador3.start(); // Inicia el hilo del robot empaquetador de tipo 3
    }
}
