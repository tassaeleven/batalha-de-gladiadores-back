package com.projetoa3.projetoa3java.classes;

public class Gladiador {
    private String nomeGladiador;
    private int quantidadeVidas;
    private boolean possuiArmadura;

    public Gladiador(String nomeGladiador, boolean possuiArmadura) {
        this.nomeGladiador = nomeGladiador;
        this.possuiArmadura = possuiArmadura;
        this.quantidadeVidas = 100;
    }

    public void receberGolpe(Arma arma) {
        int dano = arma.getForcaDestrutiva();

        if (possuiArmadura) {
            dano = (int)(dano * 0.7); // Reduz o dano em 30% quando tem armadura
        } else {
            dano = (int)(dano * 1.3); // Aumenta o dano em 30% quando não tem armadura
        }

        if (dano < 0) {
            dano = 0;
        }

        quantidadeVidas -= dano;

        if (quantidadeVidas < 0) {
            quantidadeVidas = 0;
        }
    }

    public boolean possuiVidas() {
        return quantidadeVidas > 0;
    }

    public void exibirStatus() {
        System.out.println("----- STATUS DO GLADIADOR -----");
        System.out.println("Nome: " + nomeGladiador);
        System.out.println("Vidas: " + quantidadeVidas);
        System.out.println("Usando armadura: " + (possuiArmadura ? "Sim" : "Não"));
        System.out.println("Status: " + (possuiVidas() ? "VIVO" : "MORTO"));
        System.out.println("-------------------------------\n");
    }

    public String getNomeGladiador() {
        return nomeGladiador;
    }

    // Retorna a quantidade atual de vidas do gladiador (usado pela Arena)
    public int getQuantidadeVidas() {
        return quantidadeVidas;
    }
}
