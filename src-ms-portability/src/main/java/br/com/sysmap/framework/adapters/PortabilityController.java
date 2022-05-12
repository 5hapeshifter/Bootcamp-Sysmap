package br.com.sysmap.framework.adapters;

import br.com.sysmap.application.ports.in.PortabilityService;
import br.com.sysmap.application.ports.out.KafkaService;
import br.com.sysmap.framework.adapters.in.dtos.PortabilityRequestDto;
import br.com.sysmap.framework.adapters.in.dtos.PortabilityResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/portability")
public class PortabilityController {

    @Autowired
    KafkaService kafkaService;

    @Autowired
    private PortabilityService portabilityService;



    @PostMapping
    public ResponseEntity<String> createPortability(@RequestBody @Valid PortabilityRequestDto request) {
       var solicitacao = portabilityService.savePortability(request);
       return ResponseEntity.status(HttpStatus.OK).body("Portabilidade cadastrada! ID = " + solicitacao.getRequestid());
    }

    @GetMapping(path = "/{portabilityId}")
    public ResponseEntity<Object> getPortabilityById(@PathVariable(name = "portabilityId") UUID uuid) {
        var request = portabilityService.getPortabilityById(uuid);
        return ResponseEntity.status(HttpStatus.FOUND).body(request);
    }

    @PutMapping(path = "/{portabilityId}")
    public ResponseEntity<PortabilityResponseStatus> updatePortability(@PathVariable(name = "portabilityId") UUID uuid,
                                                    @RequestBody @Valid PortabilityResponseStatus portabilityRequest) {
        var status = portabilityService.updatePortability(uuid, portabilityRequest);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

}
