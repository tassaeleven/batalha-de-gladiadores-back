package com.projetoa3.projetoa3java.controllers;

import org.springframework.web.bind.annotation.*;

import com.projetoa3.projetoa3java.dto.IniciarBatalhaRequest;
import com.projetoa3.projetoa3java.dto.ResultadoBatalhaResponse;
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

    @PostMapping("/batalhar")
    public ResultadoBatalhaResponse batalhar(@RequestBody IniciarBatalhaRequest req) {
        return batalhaService.iniciarBatalha(
            req.getGladiador1().getNome(),
            req.getGladiador2().getNome(),
            req.getGladiador1().getArma(),
            req.getGladiador2().getArma(),
            req.getGladiador1().isArmadura(),
            req.getGladiador2().isArmadura()
        );
    }
}
