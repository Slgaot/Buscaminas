class Celda {
    private boolean esMina;
    private boolean revelada;
    private boolean bandera;
    private int minasAlrededor;

    public Celda() {
        this.esMina = false;
        this.revelada = false;
        this.bandera = false;
        this.minasAlrededor = 0;
    }

    public boolean esMina() {
        return esMina;
    }

    public void colocarMina() {
        esMina = true;
    }

    public boolean estaRevelada() {
        return revelada;
    }

    public void revelar() {
        revelada = true;
    }

    public boolean tieneBandera() {
        return bandera;
    }

    public void colocarBandera() {
        this.bandera = !bandera; // Alterna entre colocar y quitar bandera
    }

    public int getMinasAlrededor() {
        return minasAlrededor;
    }

    public void setMinasAlrededor(int minas) {
        this.minasAlrededor = minas;
    }
}