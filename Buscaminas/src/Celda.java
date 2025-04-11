class Celda {
    private boolean esMina;
    private boolean revelada;
    private int minasAlrededor;

    public Celda() {
        this.esMina = false;
        this.revelada = false;
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

    public int getMinasAlrededor() {
        return minasAlrededor;
    }

    public void setMinasAlrededor(int minas) {
        this.minasAlrededor = minas;
    }
}