package com.projetoa3.projetoa3java.dto;

import java.util.List;

public class ResultadoBatalhaResponse {
    private String vencedor;
    private List<RodadaDTO> rodadas;
    private GladiadorDTO gladiador1;
    private GladiadorDTO gladiador2;
    private ArenaDTO arenaFinal;

    public static class RodadaDTO {
        public int numero;
        public String acaoGladiador1;
        public String acaoGladiador2;
        public GladiadorDTO estadoGladiador1;
        public GladiadorDTO estadoGladiador2;
        public ArenaDTO estadoArena;
    }

    public static class GladiadorDTO {
        public String nome;
        public int vidas;
        public boolean armadura;
        public String status;
    }

    public static class ArenaDTO {
        public int torcedoresGladiador1;
        public int torcedoresGladiador2;
        public String humorTorcidaGladiador1;
        public String humorTorcidaGladiador2;
        public int totalTorcedores;
    }

    public String getVencedor() { return vencedor; }
    public void setVencedor(String vencedor) { this.vencedor = vencedor; }
    public List<RodadaDTO> getRodadas() { return rodadas; }
    public void setRodadas(List<RodadaDTO> rodadas) { this.rodadas = rodadas; }
    public GladiadorDTO getGladiador1() { return gladiador1; }
    public void setGladiador1(GladiadorDTO gladiador1) { this.gladiador1 = gladiador1; }
    public GladiadorDTO getGladiador2() { return gladiador2; }
    public void setGladiador2(GladiadorDTO gladiador2) { this.gladiador2 = gladiador2; }
    public ArenaDTO getArenaFinal() { return arenaFinal; }
    public void setArenaFinal(ArenaDTO arenaFinal) { this.arenaFinal = arenaFinal; }
}
