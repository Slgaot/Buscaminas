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
            System.out.print("Elige una acción: \n1. Revelar celda \n2. Colocar/Quitar bandera\nOpción: ");
            int opcion = scanner.nextInt();

            System.out.print("Ingrese fila y columna (ej: 1 2): ");
            int fila = scanner.nextInt();
            int columna = scanner.nextInt();

            if (opcion == 1) { // Revelar celda
                if (tablero.revelarCelda(fila, columna)) {
                    System.out.println("¡Boom! Pisaste una mina. Fin del juego.");
                    tablero.mostrarTablero();
                    juegoTerminado = true;
                }
            } else if (opcion == 2) { // Colocar/Quitar bandera
                tablero.colocarBandera(fila, columna);
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }
}
