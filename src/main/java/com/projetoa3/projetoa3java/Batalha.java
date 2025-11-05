package com.projetoa3.projetoa3java;

import com.projetoa3.projetoa3java.classes.Arena;
import com.projetoa3.projetoa3java.classes.Arma;
import com.projetoa3.projetoa3java.classes.Gladiador;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Batalha {
    public static void main(String[] args) {
        SpringApplication.run(Batalha.class, args);
        // Configuração inicial: nomes, armaduras e armas
        Gladiador gladiador1 = new Gladiador("Maximus", true);
        Gladiador gladiador2 = new Gladiador("Spartacus", false);

        Arma armaDoGladiador1 = new Arma("arco");
        Arma armaDoGladiador2 = new Arma("Lança");

        Arena arena = new Arena(1000);

        System.out.println("=== INÍCIO DO COMBATE ===\n");
        gladiador1.exibirStatus();
        gladiador2.exibirStatus();
        arena.exibirStatusArena();

        int rodada = 0;
        boolean turnoGladiador1 = Math.random() < 0.5;

        // Loop até que pelo menos um gladiador morra
        while (gladiador1.possuiVidas() && gladiador2.possuiVidas()) {
            rodada++;
            System.out.println(">>> RODADA " + rodada + " <<<\n");

            if (turnoGladiador1) {
                // Gladiador 1 ataca primeiro nesta rodada
                System.out.println(gladiador1.getNomeGladiador() + " ataca " + gladiador2.getNomeGladiador()
                        + " com " + armaDoGladiador1.getTipoArma());
                gladiador2.receberGolpe(armaDoGladiador1);

                // Se o segundo ainda estiver vivo, revida
                if (gladiador2.possuiVidas()) {
                    System.out.println(gladiador2.getNomeGladiador() + " revida com " + armaDoGladiador2.getTipoArma());
                    gladiador1.receberGolpe(armaDoGladiador2);
                } else {
                    System.out.println(gladiador2.getNomeGladiador() + " foi morto e não conseguiu revidar.");
                }
            } else {
                // Gladiador 2 ataca primeiro nesta rodada
                System.out.println(gladiador2.getNomeGladiador() + " ataca " + gladiador1.getNomeGladiador()
                        + " com " + armaDoGladiador2.getTipoArma());
                gladiador1.receberGolpe(armaDoGladiador2);

                // Se o primeiro ainda estiver vivo, revida
                if (gladiador1.possuiVidas()) {
                    System.out.println(gladiador1.getNomeGladiador() + " revida com " + armaDoGladiador1.getTipoArma());
                    gladiador2.receberGolpe(armaDoGladiador1);
                } else {
                    System.out.println(gladiador1.getNomeGladiador() + " foi morto e não conseguiu revidar.");
                }
            }

            // Alterna quem começa o próximo turno
            turnoGladiador1 = !turnoGladiador1;

            // Atualiza arena e exibe status
            boolean g1Vivo = gladiador1.possuiVidas();
            boolean g2Vivo = gladiador2.possuiVidas();

            arena.atualizarHumorETorcida(gladiador1, gladiador2);

            System.out.println("\n--- Resultado da rodada " + rodada + " ---");
            gladiador1.exibirStatus();
            gladiador2.exibirStatus();
            arena.exibirStatusArena();

            if (g1Vivo && g2Vivo) {
                System.out.println("A batalha encerrou, vocês empataram, lutem novamente!");
            } else if (g1Vivo && g2Vivo) {
                System.out.println("Ambos os gladiadores continuam vivos!\n");
            } else if (!g1Vivo && !g2Vivo) {
                System.out.println("Ambos morreram! Empate trágico.\n");
            } else if (!g1Vivo) {
                System.out.println(
                        gladiador1.getNomeGladiador() + " morreu. Vencedor: " + gladiador2.getNomeGladiador() + ".\n");
            } else {
                System.out.println(
                        gladiador2.getNomeGladiador() + " morreu. Vencedor: " + gladiador1.getNomeGladiador() + ".\n");
            }
        }

        System.out.println("=== FIM DO COMBATE ===\n");
        System.out.println("Resumo final:");
        gladiador1.exibirStatus();
        gladiador2.exibirStatus();
        arena.exibirStatusArena();
    }
}
