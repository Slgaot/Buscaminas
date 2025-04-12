import java.util.Random;

class Tablero {
    private Celda[][] tablero;
    private int filas;
    private int columnas;
    private int minas;

    public Tablero(int filas, int columnas, int minas) {
        this.filas = filas;
        this.columnas = columnas;
        this.minas = minas;
        this.tablero = new Celda[filas][columnas];

        inicializarTablero();
        colocarMinas();
        calcularNumeros();
    }

    private void inicializarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = new Celda();
            }
        }
    }

    private void colocarMinas() {
        Random rand = new Random();
        int colocadas = 0;
        while (colocadas < minas) {
            int fila = rand.nextInt(filas);
            int columna = rand.nextInt(columnas);
            if (!tablero[fila][columna].esMina()) {
                tablero[fila][columna].colocarMina();
                colocadas++;
            }
        }
    }

    private void calcularNumeros() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!tablero[i][j].esMina()) {
                    int minasAlrededor = contarMinasAlrededor(i, j);
                    tablero[i][j].setMinasAlrededor(minasAlrededor);
                }
            }
        }
    }

    private int contarMinasAlrededor(int x, int y) {
        int contador = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nx = x + i, ny = y + j;
                if (nx >= 0 && nx < filas && ny >= 0 && ny < columnas && tablero[nx][ny].esMina()) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public boolean revelarCelda(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas || tablero[fila][columna].estaRevelada()) {
            return false;
        }

        // Si la celda tiene una bandera, primero quitar la bandera
        if (tablero[fila][columna].tieneBandera()) {
            tablero[fila][columna].colocarBandera(); // Quita la bandera
            System.out.println("Bandera retirada, intenta nuevamente revelar la celda.");
            return false;
        }

        // Revelar la celda
        tablero[fila][columna].revelar();

        // Si la celda contiene una mina, el juego termina
        if (tablero[fila][columna].esMina()) {
            return true;
        }

        // Si la celda no tiene minas alrededor, se desbloquean automáticamente las celdas adyacentes
        if (tablero[fila][columna].getMinasAlrededor() == 0) {
            revelarAdyacentes(fila, columna);
        }

        return false;
    }
    private void revelarAdyacentes(int fila, int columna) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nuevaFila = fila + i;
                int nuevaColumna = columna + j;

                if (nuevaFila >= 0 && nuevaFila < filas && nuevaColumna >= 0 && nuevaColumna < columnas) {
                    if (!tablero[nuevaFila][nuevaColumna].estaRevelada() && !tablero[nuevaFila][nuevaColumna].esMina()) {
                        tablero[nuevaFila][nuevaColumna].revelar();

                        // Si la celda también tiene 0 minas alrededor, seguir expandiendo
                        if (tablero[nuevaFila][nuevaColumna].getMinasAlrededor() == 0) {
                            revelarAdyacentes(nuevaFila, nuevaColumna);
                        }
                    }
                }
            }
        }
    }

    public void colocarBandera(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas || tablero[fila][columna].estaRevelada()) {
            System.out.println("¡No puedes poner una bandera aquí!");
            return;
        }
        tablero[fila][columna].colocarBandera();
    }

    public void mostrarTablero() {
        System.out.print("    ");
        for (int j = 0; j < columnas; j++) {
            System.out.print(j + "  ");
        }
        System.out.println();

        for (int i = 0; i < filas; i++) {
            System.out.print(i + "  "); // Número de fila al inicio de cada fila

            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j].tieneBandera()) {
                    System.out.print("🚩 ");
                } else if (!tablero[i][j].estaRevelada()) {
                    System.out.print("⬜ ");
                } else if (tablero[i][j].esMina()) {
                    System.out.print("💣 ");
                } else {
                    int minas = tablero[i][j].getMinasAlrededor();
                    System.out.print(minas == 0 ? "⬛ " : convertirNumeroAEmoji(minas) + " ");
                }
            }
            System.out.println();
        }
    }

    // Método para convertir números a emojis de números
    private String convertirNumeroAEmoji(int numero) {
        String[] emojis = {"0️⃣", "1️⃣", "2️⃣", "3️⃣", "4️⃣", "5️⃣", "6️⃣", "7️⃣", "8️⃣"};
        return emojis[numero];
    }
}