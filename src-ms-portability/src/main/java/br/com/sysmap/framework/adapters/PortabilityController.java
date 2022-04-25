package br.com.sysmap.framework.adapters;

import br.com.sysmap.application.ports.in.PortabilityService;
import br.com.sysmap.domain.PortabilityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/portability")
public class PortabilityController {

    @Autowired
    private PortabilityService portabilityService;

    @PostMapping
    public ResponseEntity<String> createPortability(@RequestBody PortabilityRequest requestPortability) {
       var solicitacao = portabilityService.savePortability(requestPortability);
        return ResponseEntity.status(HttpStatus.OK).body("Portabilidade cadastrada! ID = " + solicitacao.getRequestid());
    }

    @GetMapping(path = "/{portabilityId}")
    public ResponseEntity<Object> getPortabilityById(@PathVariable(name = "portabilityId") UUID uuid) {
        var request = portabilityService.getPortabilityById(uuid);
        return ResponseEntity.status(HttpStatus.FOUND).body(request);
    }

    @PutMapping(path = "/{portabilityId}")
    public ResponseEntity<Object> updatePortability(@PathVariable(name = "portabilityId") UUID uuid,
                                                    @RequestBody PortabilityRequest portabilityRequest) {
        var request = portabilityService.updatePortability(uuid, portabilityRequest);
        return ResponseEntity.status(HttpStatus.OK).body(request);
    }

}
