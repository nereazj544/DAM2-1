package hilos.GeneradorDeSecuencias;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el patrón de letras y repeticiones:");
        String patron = scanner.nextLine().toUpperCase();

        if (!patron.matches("[A-Z]\\d+")) {
            System.err.println("El patrón introducido es incorrecto.");
            return;
        }

        for (int i = 0; i < patron.length(); i++) {
            char letra = patron.charAt(i);
            int repeticiones = Integer.parseInt(Character.toString(patron.charAt(++i)));
            new EscribeLetras(patron, letra, repeticiones).start();
        }
    }
}