package br.com.sysmap.framework.adapters.out;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.UUID;

@FeignClient(name = "src-ms-portability", url = "localhost:8080/portability")
public interface PortabilityFeignClient {

    @PutMapping(value = "{portabilityId}")
    ResponseEntity<PortabilityResponseStatus> updatePortability(@PathVariable(name = "portabilityId") UUID uuid,
                                                                       @RequestBody @Valid PortabilityResponseStatus portabilityRequest);


}
