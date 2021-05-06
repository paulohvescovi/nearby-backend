package br.com.neartech.nearby.empresa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @GetMapping("/test")
    public ResponseEntity<String> testeFechado(){
        return ResponseEntity.ok("Teste fechado");
    }
}
