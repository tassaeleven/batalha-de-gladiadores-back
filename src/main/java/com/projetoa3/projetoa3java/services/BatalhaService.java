package com.projetoa3.projetoa3java.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projetoa3.projetoa3java.classes.Arena;
import com.projetoa3.projetoa3java.classes.Arma;
import com.projetoa3.projetoa3java.classes.Gladiador;
import com.projetoa3.projetoa3java.dto.ResultadoBatalhaResponse;

@Service
public class BatalhaService {
     public ResultadoBatalhaResponse iniciarBatalha(String nome1, String nome2,
                                                   String arma1, String arma2,
                                                   boolean armadura1, boolean armadura2) {

        Gladiador gladiador1 = new Gladiador(nome1, armadura1);
        Gladiador gladiador2 = new Gladiador(nome2, armadura2);

        Arma armaDoGladiador1 = new Arma(arma1 == null ? "punhos" : arma1);
        Arma armaDoGladiador2 = new Arma(arma2 == null ? "punhos" : arma2);

        Arena arena = new Arena(1000);

        // Armazenando rodadas
        List<ResultadoBatalhaResponse.RodadaDTO> rodadas = new ArrayList<>();

        System.out.println("=== INÍCIO DO COMBATE ===\n");
        gladiador1.exibirStatus();
        gladiador2.exibirStatus();
        arena.exibirStatusArena();

        int rodada = 0;
        boolean turnoGladiador1 = Math.random() < 0.5;

        while (gladiador1.possuiVidas() && gladiador2.possuiVidas()) {
            rodada++;

            System.out.println(">>> RODADA " + rodada + " <<<\n");

            String acao1 = null;
            String acao2 = null;

            if (turnoGladiador1) {
                System.out.println(gladiador1.getNomeGladiador() + " ataca " + gladiador2.getNomeGladiador()
                        + " com " + armaDoGladiador1.getTipoArma());
                gladiador2.receberGolpe(armaDoGladiador1);

                if (gladiador2.possuiVidas()) {
                    System.out.println(gladiador2.getNomeGladiador() + " revida' com " + armaDoGladiador2.getTipoArma());
                    gladiador1.receberGolpe(armaDoGladiador2);
                    acao1 = gladiador1.getNomeGladiador() + " ataca " + gladiador2.getNomeGladiador() + " com " + armaDoGladiador1.getTipoArma();
                    acao2 = gladiador2.getNomeGladiador() + " revida com " + armaDoGladiador2.getTipoArma();
                } else {
                    System.out.println(gladiador2.getNomeGladiador() + " foi morto e não conseguiu revidar.");
                    acao1 = gladiador1.getNomeGladiador() + " ataca " + gladiador2.getNomeGladiador() + " com " + armaDoGladiador1.getTipoArma();
                    acao2 = gladiador2.getNomeGladiador() + " foi morto e não conseguiu revidar.";
                }
            } else {
                System.out.println(gladiador2.getNomeGladiador() + " ataca " + gladiador1.getNomeGladiador()
                        + " com " + armaDoGladiador2.getTipoArma());
                gladiador1.receberGolpe(armaDoGladiador2);

                if (gladiador1.possuiVidas()) {
                    System.out.println(gladiador1.getNomeGladiador() + " revida com " + armaDoGladiador1.getTipoArma());
                    gladiador2.receberGolpe(armaDoGladiador1);
                    acao1 = gladiador2.getNomeGladiador() + " ataca " + gladiador1.getNomeGladiador() + " com " + armaDoGladiador2.getTipoArma();
                    acao2 = gladiador1.getNomeGladiador() + " revida com " + armaDoGladiador1.getTipoArma();
                } else {
                    System.out.println(gladiador1.getNomeGladiador() + " foi morto e não conseguiu revidar.");
                    acao1 = gladiador2.getNomeGladiador() + " ataca " + gladiador1.getNomeGladiador() + " com " + armaDoGladiador2.getTipoArma();
                    acao2 = gladiador1.getNomeGladiador() + " foi morto e não conseguiu revidar.";
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

            // montar DTO da rodada
            ResultadoBatalhaResponse.RodadaDTO r = new ResultadoBatalhaResponse.RodadaDTO();
            r.numero = rodada;
            r.acaoGladiador1 = acao1;
            r.acaoGladiador2 = acao2;

            ResultadoBatalhaResponse.GladiadorDTO s1 = new ResultadoBatalhaResponse.GladiadorDTO();
            s1.nome = gladiador1.getNomeGladiador();
            s1.vidas = gladiador1.getQuantidadeVidas();
            s1.armadura = armadura1;
            s1.status = gladiador1.possuiVidas() ? "VIVO" : "MORTO";

            ResultadoBatalhaResponse.GladiadorDTO s2 = new ResultadoBatalhaResponse.GladiadorDTO();
            s2.nome = gladiador2.getNomeGladiador();
            s2.vidas = gladiador2.getQuantidadeVidas();
            s2.armadura = armadura2;
            s2.status = gladiador2.possuiVidas() ? "VIVO" : "MORTO";

            ResultadoBatalhaResponse.ArenaDTO a = new ResultadoBatalhaResponse.ArenaDTO();
            a.torcedoresGladiador1 = getArenaTorcedores(arena, true);
            a.torcedoresGladiador2 = getArenaTorcedores(arena, false);
            a.humorTorcidaGladiador1 = arena.isHumorTorcidaGladiador1() ? "Bem humorados" : "Mal humorados";
            a.humorTorcidaGladiador2 = arena.isHumorTorcidaGladiador2() ? "Bem humorados" : "Mal humorados";
            a.totalTorcedores = a.torcedoresGladiador1 + a.torcedoresGladiador2;

            r.estadoGladiador1 = s1;
            r.estadoGladiador2 = s2;
            r.estadoArena = a;

            rodadas.add(r);

            // Mensagens finais por rodada (igual ao seu comportamento original)
            if (g1Vivo && g2Vivo) {
                System.out.println("A batalha encerrou, vocês empataram, lutem novamente!");
            } else if (!g1Vivo && !g2Vivo) {
                System.out.println("Ambos morreram! Empate trágico.\n");
            } else if (!g1Vivo) {
                System.out.println(gladiador1.getNomeGladiador() + " morreu. Vencedor: " + gladiador2.getNomeGladiador() + ".\n");
            } else if (!g2Vivo) {
                System.out.println(gladiador2.getNomeGladiador() + " morreu. Vencedor: " + gladiador1.getNomeGladiador() + ".\n");
            }
        }

        ResultadoBatalhaResponse resp = new ResultadoBatalhaResponse();

        String vencedor = gladiador1.possuiVidas() ? gladiador1.getNomeGladiador() : gladiador2.getNomeGladiador();
        resp.setVencedor(vencedor);
        resp.setRodadas(rodadas);

        ResultadoBatalhaResponse.GladiadorDTO finalG1 = new ResultadoBatalhaResponse.GladiadorDTO();
        finalG1.nome = gladiador1.getNomeGladiador();
        finalG1.vidas = gladiador1.getQuantidadeVidas();
        finalG1.armadura = armadura1;
        finalG1.status = gladiador1.possuiVidas() ? "VIVO" : "MORTO";

        ResultadoBatalhaResponse.GladiadorDTO finalG2 = new ResultadoBatalhaResponse.GladiadorDTO();
        finalG2.nome = gladiador2.getNomeGladiador();
        finalG2.vidas = gladiador2.getQuantidadeVidas();
        finalG2.armadura = armadura2;
        finalG2.status = gladiador2.possuiVidas() ? "VIVO" : "MORTO";

        ResultadoBatalhaResponse.ArenaDTO finalArena = new ResultadoBatalhaResponse.ArenaDTO();
        finalArena.torcedoresGladiador1 = getArenaTorcedores(arena, true);
        finalArena.torcedoresGladiador2 = getArenaTorcedores(arena, false);
        finalArena.humorTorcidaGladiador1 = arena.isHumorTorcidaGladiador1() ? "Bem humorados" : "Mal humorados";
        finalArena.humorTorcidaGladiador2 = arena.isHumorTorcidaGladiador2() ? "Bem humorados" : "Mal humorados";
        finalArena.totalTorcedores = finalArena.torcedoresGladiador1 + finalArena.torcedoresGladiador2;

        resp.setGladiador1(finalG1);
        resp.setGladiador2(finalG2);
        resp.setArenaFinal(finalArena);

        System.out.println("=== FIM DO COMBATE ===\n");
        gladiador1.exibirStatus();
        gladiador2.exibirStatus();
        arena.exibirStatusArena();

        return resp;
    }

    private int getArenaTorcedores(Arena arena, boolean forGladiador1) {
        try {
            if (forGladiador1) return arena.getTorcedoresGladiador1();
            else return arena.getTorcedoresGladiador2();
        } catch (Exception e) {
            return 0;
        }
    }
}