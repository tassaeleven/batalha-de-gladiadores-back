
package com.projetoa3.projetoa3java.classes;

public class Arena {
    private int torcedoresGladiador1;
    private int torcedoresGladiador2;
    private boolean humorTorcidaGladiador1;
    private boolean humorTorcidaGladiador2;
    private String nomeGladiador1;
    private String nomeGladiador2;

    public Arena(int quantidadeTorcedores) {
        // Inicialmente divide igualmente; será recalculado na primeira rodada com base na vida
        this.torcedoresGladiador1 = quantidadeTorcedores / 2;
        this.torcedoresGladiador2 = quantidadeTorcedores - torcedoresGladiador1;
        this.humorTorcidaGladiador1 = true;
        this.humorTorcidaGladiador2 = true;
    }

    public void atualizarHumorETorcida(Gladiador g1, Gladiador g2) {
        if (nomeGladiador1 == null) {
            nomeGladiador1 = g1.getNomeGladiador();
            nomeGladiador2 = g2.getNomeGladiador();
        }

        int vida1 = g1.getQuantidadeVidas();
        int vida2 = g2.getQuantidadeVidas();
        int totalTorcedores = Math.max(1, torcedoresGladiador1 + torcedoresGladiador2);

        // Ambos vivos: ajusta a torcida com base na proporção de vidas
        if (vida1 > 0 && vida2 > 0) {
            double proporcao1 = vida1 / (double)(vida1 + vida2);
            int novoT1 = (int) Math.round(totalTorcedores * proporcao1);
            int novoT2 = totalTorcedores - novoT1;

            // Evita que alguma torcida zere completamente
            if (novoT1 < 1) novoT1 = 1;
            if (novoT2 < 1) novoT2 = 1;

            torcedoresGladiador1 = novoT1;
            torcedoresGladiador2 = novoT2;

            // Humor: torcida com maior vida fica mais feliz
            humorTorcidaGladiador1 = vida1 >= vida2;
            humorTorcidaGladiador2 = vida2 >= vida1;
        } else if (vida1 <= 0 && vida2 <= 0) {
            // Ambos mortos
            humorTorcidaGladiador1 = false;
            humorTorcidaGladiador2 = false;
            torcedoresGladiador1 = Math.max(0, torcedoresGladiador1 - (int)(torcedoresGladiador1 * 0.25));
            torcedoresGladiador2 = Math.max(0, torcedoresGladiador2 - (int)(torcedoresGladiador2 * 0.25));
        } else if (vida1 <= 0) {
            // Gladiador 1 morreu
            humorTorcidaGladiador1 = false;
            humorTorcidaGladiador2 = true;
            torcedoresGladiador1 = Math.max(0, torcedoresGladiador1 - (int)(torcedoresGladiador1 * 0.25));
            torcedoresGladiador2 = torcedoresGladiador2 + (int)(torcedoresGladiador2 * 0.15);
        } else {
            // Gladiador 2 morreu
            humorTorcidaGladiador1 = true;
            humorTorcidaGladiador2 = false;
            torcedoresGladiador1 = torcedoresGladiador1 + (int)(torcedoresGladiador1 * 0.15);
            torcedoresGladiador2 = Math.max(0, torcedoresGladiador2 - (int)(torcedoresGladiador2 * 0.25));
        }
    }

    public void exibirStatusArena() {
        String n1 = (nomeGladiador1 == null) ? "Gladiador 1" : nomeGladiador1;
        String n2 = (nomeGladiador2 == null) ? "Gladiador 2" : nomeGladiador2;

        System.out.println("===== STATUS DA ARENA =====");
        System.out.println("--- Torcida do " + n1 + " ---");
        System.out.println("Torcedores: " + torcedoresGladiador1);
        System.out.println("Humor: " + (humorTorcidaGladiador1 ? "Bem humorados" : "Mal humorados"));
        System.out.println("\n--- Torcida do " + n2 + " ---");
        System.out.println("Torcedores: " + torcedoresGladiador2);
        System.out.println("Humor: " + (humorTorcidaGladiador2 ? "Bem humorados" : "Mal humorados"));
        System.out.println("Total de torcedores: " + (torcedoresGladiador1 + torcedoresGladiador2));
        System.out.println("============================\n");
    }
}
