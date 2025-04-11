import java.util.Scanner;

class JuegoBuscaminas {
    private Tablero tablero;
    private boolean juegoTerminado;

    public JuegoBuscaminas(int filas, int columnas, int minas) {
        tablero = new Tablero(filas, columnas, minas);
        juegoTerminado = false;
    }

    public void jugar() {
        Scanner scanner = new Scanner(System.in);

        while (!juegoTerminado) {
            tablero.mostrarTablero();
            System.out.print("Ingrese fila y columna para revelar (ej: 1 2): ");
            int fila = scanner.nextInt();
            int columna = scanner.nextInt();

            if (tablero.revelarCelda(fila, columna)) {
                System.out.println("¡Boom! Pisaste una mina. Fin del juego.");
                juegoTerminado = true;
            }
        }
    }
}
