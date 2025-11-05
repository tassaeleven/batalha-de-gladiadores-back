package com.projetoa3.projetoa3java.controllers;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping(path = "/api/batalha")
@CrossOrigin(origins = "http://localhost:4200")
public class BatalhaController {

    @GetMapping("/iniciar")
    public String iniciarBatalha() {
        return "A batalha começou!";
    }

    @PostMapping("/resultado")
    public Map<String, String> resultado(@RequestBody Map<String, Object> dados) {
        String gladiador1 = (String) dados.get("gladiador1");
        String gladiador2 = (String) dados.get("gladiador2");

        String vencedor = Math.random() > 0.5 ? gladiador1 : gladiador2;

        return Map.of(
            "gladiador1", gladiador1,
            "gladiador2", gladiador2,
            "vencedor", vencedor
        );
    }
}
