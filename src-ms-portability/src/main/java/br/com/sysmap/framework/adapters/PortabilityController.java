package br.com.sysmap.framework.adapters;

import br.com.sysmap.application.ports.in.PortabilityService;
import br.com.sysmap.domain.PortabilityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ms_portability")
public class PortabilityController {

    @Autowired
    private PortabilityService portabilityService;

    @PostMapping
    public ResponseEntity<String> createPortability(@RequestBody PortabilityRequest requestPortability) {
       portabilityService.savePortability(requestPortability);
        return ResponseEntity.status(HttpStatus.OK).body("Requisição cadastrada");
    }

}
