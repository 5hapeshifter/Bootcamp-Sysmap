package br.com.sysmap.framework.in;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ms_portability")
public class PortabilityController {

    @GetMapping
    public ResponseEntity<String> getPortabilities() {
        return ResponseEntity.ok("Achou!");
    }

}
