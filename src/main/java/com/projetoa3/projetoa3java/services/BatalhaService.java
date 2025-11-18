package com.projetoa3.projetoa3java.services;

import org.springframework.stereotype.Service;

import com.projetoa3.projetoa3java.classes.Arena;
import com.projetoa3.projetoa3java.classes.Arma;
import com.projetoa3.projetoa3java.classes.Gladiador;

@Service
public class BatalhaService {
    private boolean batalhaAtiva = false;

    public String iniciarBatalha(String nome1, String nome2, String arma1, String arma2, boolean armadura1, boolean armadura2) {
        batalhaAtiva = true;
        Gladiador gladiador1 = new Gladiador(nome1, armadura1);
        Gladiador gladiador2 = new Gladiador(nome2, armadura2);

        Arma armaDoGladiador1 = new Arma(arma1);
        Arma armaDoGladiador2 = new Arma(arma2);

        Arena arena = new Arena(1000);

        System.out.println("=== INÍCIO DO COMBATE ===\n");
        gladiador1.exibirStatus();
        gladiador2.exibirStatus();
        arena.exibirStatusArena();

        int rodada = 0;
        boolean turnoGladiador1 = Math.random() < 0.5;

        while (gladiador1.possuiVidas() && gladiador2.possuiVidas()) {
            rodada++;
            System.out.println(">>> RODADA " + rodada + " <<<\n");

            if (turnoGladiador1) {
                System.out.println(gladiador1.getNomeGladiador() + " ataca " + gladiador2.getNomeGladiador()
                        + " com " + armaDoGladiador1.getTipoArma());
                gladiador2.receberGolpe(armaDoGladiador1);

                if (gladiador2.possuiVidas()) {
                    System.out.println(gladiador2.getNomeGladiador() + " revida com " + armaDoGladiador2.getTipoArma());
                    gladiador1.receberGolpe(armaDoGladiador2);
                } else {
                    System.out.println(gladiador2.getNomeGladiador() + " foi morto e não conseguiu revidar.");
                }
            } else {
                System.out.println(gladiador2.getNomeGladiador() + " ataca " + gladiador1.getNomeGladiador()
                        + " com " + armaDoGladiador2.getTipoArma());
                gladiador1.receberGolpe(armaDoGladiador2);

                if (gladiador1.possuiVidas()) {
                    System.out.println(gladiador1.getNomeGladiador() + " revida com " + armaDoGladiador1.getTipoArma());
                    gladiador2.receberGolpe(armaDoGladiador1);
                } else {
                    System.out.println(gladiador1.getNomeGladiador() + " foi morto e não conseguiu revidar.");
                }
            }

            turnoGladiador1 = !turnoGladiador1;

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

        if (gladiador1.possuiVidas())
            return gladiador1.getNomeGladiador();
        else
            return gladiador2.getNomeGladiador();
    }
}