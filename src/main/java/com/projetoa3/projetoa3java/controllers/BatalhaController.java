package com.projetoa3.projetoa3java.controllers;

import org.springframework.web.bind.annotation.*;

import com.projetoa3.projetoa3java.services.BatalhaService;

import java.util.*;

@RestController
@RequestMapping(path = "/api/batalha")
@CrossOrigin(origins = "http://localhost:4200")
public class BatalhaController {

    private final BatalhaService batalhaService;
    private boolean batalhaIniciada = false;
    private Map<String, String> resultadoBatalha = new HashMap<>();

    public BatalhaController(BatalhaService batalhaService) {
        this.batalhaService = batalhaService;
    }

    @GetMapping("/iniciar")
    public String iniciarBatalha() {
        batalhaIniciada = true;
        return "A batalha começou!";
    }

    @PostMapping("/resultado")
    public Map<String, String> resultado(@RequestBody Map<String, Object> dados) {
        if (!batalhaIniciada) {
            return Map.of("erro", "A batalha não foi iniciada.");
        }
        String gladiador1 = (String) dados.get("gladiador1");
        String gladiador2 = (String) dados.get("gladiador2");
        String arma1 = (String) dados.get("arma1");
        String arma2 = (String) dados.get("arma2");
        boolean armadura1 = (boolean) dados.get("armadura1");
        boolean armadura2 = (boolean) dados.get("armadura2");

        String vencedor = batalhaService.iniciarBatalha(gladiador1, gladiador2, arma1, arma2, armadura1, armadura2);

        batalhaIniciada = false;

        resultadoBatalha = Map.of(
            "gladiador1", gladiador1,
            "gladiador2", gladiador2,
            "arma1", arma1,
            "arma2", arma2,
            "armadura1", String.valueOf(armadura1),
            "armadura2", String.valueOf(armadura2),
            "vencedor", vencedor);

        return resultadoBatalha;
    }
}
