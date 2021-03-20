package br.com.transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping
    public ResponseEntity<String> getInit() {
        return ResponseEntity.ok("Olá bem-vindo!");
    }
}
