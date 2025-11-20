package com.projetoa3.projetoa3java.dto;

public class IniciarBatalhaRequest {
    private GladiadorInit gladiador1;
    private GladiadorInit gladiador2;

    public static class GladiadorInit {
        private String nome;
        private String arma;
        private boolean armadura;

        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
        public String getArma() { return arma; }
        public void setArma(String arma) { this.arma = arma; }
        public boolean isArmadura() { return armadura; }
        public void setArmadura(boolean armadura) { this.armadura = armadura; }
    }

    public GladiadorInit getGladiador1() { return gladiador1; }
    public void setGladiador1(GladiadorInit gladiador1) { this.gladiador1 = gladiador1; }
    public GladiadorInit getGladiador2() { return gladiador2; }
    public void setGladiador2(GladiadorInit gladiador2) { this.gladiador2 = gladiador2; }
}
