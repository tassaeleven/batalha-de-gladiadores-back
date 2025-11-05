package com.projetoa3.projetoa3java.classes;

public class Arma {
    private String tipoArma;
    private int forcaDestrutiva;

    public Arma(String tipoArma) {
        this.tipoArma = tipoArma;
        definirForcaDestrutiva();
    }

    private void definirForcaDestrutiva() {
        switch (tipoArma.toLowerCase()) {
            case "espada":
                this.forcaDestrutiva = 20;
                break;
            case "lança":
            case "lanca":
                this.forcaDestrutiva = 15;
                break;
            case "arco e flecha":
            case "arco":
                this.forcaDestrutiva = 10;
                break;
            default:
                this.forcaDestrutiva = 5;
                break;
        }
    }

    public int getForcaDestrutiva() {
        return forcaDestrutiva;
    }

    public String getTipoArma() {
        return tipoArma;
    }

    @Override
    public String toString() {
        return tipoArma + " (Força: " + forcaDestrutiva + ")";
    }
}